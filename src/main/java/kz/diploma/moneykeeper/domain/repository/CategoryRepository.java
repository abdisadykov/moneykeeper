package kz.diploma.moneykeeper.domain.repository;

import kz.diploma.moneykeeper.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
