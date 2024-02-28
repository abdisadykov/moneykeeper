package kz.diploma.moneykeeper.domain.repository;

import kz.diploma.moneykeeper.domain.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
}
