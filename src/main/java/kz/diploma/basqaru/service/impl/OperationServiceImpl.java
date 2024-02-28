package kz.diploma.basqaru.service.impl;

import jakarta.transaction.Transactional;
import kz.diploma.basqaru.domain.entity.Account;
import kz.diploma.basqaru.domain.entity.Category;
import kz.diploma.basqaru.domain.entity.Operation;
import kz.diploma.basqaru.domain.repository.AccountRepository;
import kz.diploma.basqaru.domain.repository.CategoryRepository;
import kz.diploma.basqaru.domain.repository.OperationRepository;
import kz.diploma.basqaru.exception.ElementNotFoundException;
import kz.diploma.basqaru.exception.IncorrectOperationTypeException;
import kz.diploma.basqaru.model.OperationRequestDto;
import kz.diploma.basqaru.model.OperationResponseDto;
import kz.diploma.basqaru.model.OperationType;
import kz.diploma.basqaru.service.IOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements IOperationService {

    private final OperationRepository operationRepository;
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryServiceImpl categoryService;
    private final AccountServiceImpl accountService;

    private final static String OPERATION_NOT_FOUND = "Operation not found";


    @Override
    public List<OperationResponseDto> getAllOperations() {
        return operationRepository.findAll().stream()
                .map(this::fromEntityToResponseDto)
                .toList();
    }

    @Override
    public OperationResponseDto getOperationById(Long id) {
        Operation operation = operationRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(OPERATION_NOT_FOUND));
        System.out.println(operation.getAccount());
        return fromEntityToResponseDto(operation);
    }

    @Override
    @Transactional
    public void createNewOperation(Long accountId, OperationRequestDto requestDto) {
        Operation operation = fromRequestDtoToEntity(requestDto);
        Account account = accountRepository.findById(accountId).
                orElseThrow(() -> new ElementNotFoundException("Account with ID=" + accountId + " not found"));
        operation.setAccount(account);
        if (requestDto.getType() == OperationType.EXPENSE) {
            account.setBalance(account.getBalance().subtract(requestDto.getAmount()));
        } else if (requestDto.getType() == OperationType.INCOME) {
            account.setBalance(account.getBalance().add(requestDto.getAmount()));
        } else {
            throw new IncorrectOperationTypeException("Incorrect operation type");
        }

        if (categoryRepository.findByTitle(requestDto.getCategory().getTitle()).isPresent()) {
            operation.setCategory(categoryRepository.findByTitle(requestDto.getCategory().getTitle()).get());
        }
        operationRepository.save(operation);
    }

    @Override
    @Transactional
    public OperationResponseDto patchOperation(Long id, OperationRequestDto requestDto) {
        Operation operation = operationRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(OPERATION_NOT_FOUND));
        Account account = operation.getAccount();
        if (requestDto.getType() != null && requestDto.getAmount() != null) {
            account.setBalance(operation.getType() == OperationType.INCOME
                    ? account.getBalance().subtract(operation.getAmount())
                    : account.getBalance().add(operation.getAmount()));
            if(requestDto.getType() == OperationType.INCOME) {
                account.setBalance(account.getBalance().add(requestDto.getAmount()));
            } else if(requestDto.getType() == OperationType.EXPENSE) {
                account.setBalance(account.getBalance().subtract(requestDto.getAmount()));
            } else {
                throw new IncorrectOperationTypeException("Incorrect operation type");
            }
        }

        if(requestDto.getCategory() != null) {
            Category category = categoryRepository.findByTitle(requestDto.getCategory().getTitle())
                    .orElseThrow(()-> new ElementNotFoundException("No such category"));
            operation.setCategory(category);
        }

        BeanUtils.copyProperties(requestDto, operation, getNullPropertyNames(requestDto));
        operationRepository.save(operation);
        return fromEntityToResponseDto(operation);
    }

    @Override
    @Transactional
    public void deleteOperationById(Long id) {
        Operation operation = operationRepository.findById(id).orElseThrow(()-> new ElementNotFoundException(OPERATION_NOT_FOUND));
        BigDecimal balance = operation.getAccount().getBalance();
        if (operation.getType() == OperationType.INCOME) {
            balance = balance.subtract(operation.getAmount());
        } else if(operation.getType() == OperationType.EXPENSE) {
            balance = balance.add(operation.getAmount());
        } else {
            throw new IncorrectOperationTypeException("Incorrect operation type");
        }
        operation.getAccount().setBalance(balance);
        accountRepository.save(operation.getAccount());
        operationRepository.deleteById(id);
    }

    private OperationResponseDto fromEntityToResponseDto(Operation operation) {
        return OperationResponseDto.builder()
                .operationId(operation.getOperationId())
                .type(operation.getType())
                .amount(operation.getAmount())
                .createdTime(operation.getCreatedTime())
                .account(accountService.fromEntityToResponseDto(operation.getAccount()))
                .category(categoryService.fromEntityToResponseDto(operation.getCategory()))
                .build();
    }

    private Operation fromRequestDtoToEntity(OperationRequestDto requestDto) {
        return Operation.builder()
                .amount(requestDto.getAmount())
                .type(requestDto.getType())
                .category(categoryService.fromRequestDtoToEntity(requestDto.getCategory()))
                .build();
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        Set<String> nullPropertyNames = new HashSet<>();
        for (PropertyDescriptor propertyDescriptor : src.getPropertyDescriptors()) {
            if (src.getPropertyValue(propertyDescriptor.getName()) == null) {
                nullPropertyNames.add(propertyDescriptor.getName());
            }
        }
        return nullPropertyNames.toArray(new String[0]);
    }
}
