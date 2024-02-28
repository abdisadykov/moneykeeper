package kz.diploma.moneykeeper.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.diploma.moneykeeper.model.OperationRequestDto;
import kz.diploma.moneykeeper.model.OperationResponseDto;
import kz.diploma.moneykeeper.service.IOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operations")
@RequiredArgsConstructor
@Tag(name = "user-controller", description = "CRUDs to Operation API")
public class OperationController {

    private final IOperationService operationService;

    @GetMapping
    @Operation(summary = "Get all operations", description = "Returns a list of all operations.")
    public ResponseEntity<List<OperationResponseDto>> getAllOperations() {
        return ResponseEntity.ok(operationService.getAllOperations());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get operation by ID", description = "Returns the operation with the specified ID.")
    public ResponseEntity<OperationResponseDto> getOperationById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(operationService.getOperationById(id));
    }

    @PostMapping("/{accountId}")
    @Operation(summary = "Create new operation", description = "Creates a new operation with the provided details.")
    public ResponseEntity<Void> createNewOperation(@PathVariable("accountId") Long accountId,
                                                   @RequestBody OperationRequestDto requestDto) {
        operationService.createNewOperation(accountId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update operation by ID", description = "Updates the operation with the specified ID.")
    public ResponseEntity<OperationResponseDto> patchOperation(@PathVariable Long id,
                                                               @RequestBody OperationRequestDto requestDto) {
        return ResponseEntity.ok(operationService.patchOperation(id, requestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete operation by ID", description = "Deletes the operation with the specified ID.")
    public ResponseEntity<Void> deleteOperationById(@PathVariable("id") Long id) {
        operationService.deleteOperationById(id);
        return ResponseEntity.noContent().build();
    }

}
