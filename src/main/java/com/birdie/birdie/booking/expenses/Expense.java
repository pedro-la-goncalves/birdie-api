package com.birdie.birdie.booking.expenses;

import com.birdie.birdie.booking.Booking;
import com.birdie.birdie.booking.expenses.dto.ExpenseCreationDTO;
import com.birdie.birdie.booking.expenses.dto.ExpenseDTO;
import com.birdie.birdie.booking.expenses.dto.ExpenseUpdateDTO;
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
    private EExpenseType type;

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
    @JoinColumn(name = "booking_id")
    private Booking booking;

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
