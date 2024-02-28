package kz.diploma.basqaru.service;

import kz.diploma.basqaru.model.OperationRequestDto;
import kz.diploma.basqaru.model.OperationResponseDto;

import java.util.List;

public interface IOperationService {
    List<OperationResponseDto> getAllOperations();

    OperationResponseDto getOperationById(Long id);

    void createNewOperation(Long accountId, OperationRequestDto requestDto);

    OperationResponseDto patchOperation(Long id, OperationRequestDto requestDto);

    void deleteOperationById(Long id);
}
