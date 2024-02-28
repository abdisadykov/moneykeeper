package kz.diploma.moneykeeper.domain.entity;

import jakarta.persistence.*;
import kz.diploma.moneykeeper.model.OperationType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "operations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long operationId;

    @Column(name = "type", nullable = false)
    private OperationType type;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "created_time", updatable = false)
    private LocalDateTime createdTime;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "category_id")
    private Category category;

    @PrePersist
    public void toCreate() {
        this.setCreatedTime(LocalDateTime.now());
    }
}
