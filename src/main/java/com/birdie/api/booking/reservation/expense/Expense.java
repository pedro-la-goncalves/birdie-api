package com.birdie.api.booking.reservation.expense;

import com.birdie.api.booking.reservation.Reservation;
import com.birdie.api.booking.reservation.expense.dto.ExpenseCreationDTO;
import com.birdie.api.booking.reservation.expense.dto.ExpenseDTO;
import com.birdie.api.booking.reservation.expense.dto.ExpenseUpdateDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.generator.EventType;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private ExpenseType type;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private Double multiplier;

    @Column
    private Double value;

    @Column(name = "created_at")
    @CurrentTimestamp(event = EventType.INSERT)
    private LocalDateTime createdAt;

    @ManyToOne()
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public Expense(ExpenseDTO expense) {
        this.id = expense.id();
        if (expense.type() != null) this.type = expense.type();
        if (expense.title() != null) this.title = expense.title();
        if (expense.description() != null) this.description = expense.description();
        if (expense.multiplier() != null) this.multiplier = expense.multiplier();
        if (expense.value() != null) this.value = expense.value();
    }

    public Expense(ExpenseCreationDTO expense) {
        this.type = expense.type();
        this.title = expense.title();
        this.description = expense.description();
        if (expense.multiplier() != null) this.multiplier = expense.multiplier();
        if (expense.value() != null) this.value = expense.value();
    }

    public Expense update(ExpenseUpdateDTO expense) {
        if (expense.type() != null) this.type = expense.type();
        if (expense.title() != null) this.title = expense.title();
        if (expense.description() != null) this.description = expense.description();
        if (expense.multiplier() != null) this.multiplier = expense.multiplier();
        if (expense.value() != null) this.value = expense.value();

        return this;
    }

}
