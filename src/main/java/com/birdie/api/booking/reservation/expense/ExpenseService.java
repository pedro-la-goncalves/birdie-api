package com.birdie.api.booking.reservation.expense;

import com.birdie.api.booking.reservation.expense.dto.ExpenseCreationDTO;
import com.birdie.api.booking.reservation.expense.dto.ExpenseUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    public ResponseEntity<List<Expense>> findAll() {
        List<Expense> expenses = this.expenseRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(expenses);
    }

    public ResponseEntity<Expense> findOne(Long id) {
        Expense expense = this.expenseRepository.findById(id).orElseThrow();
        return ResponseEntity.status(HttpStatus.OK).body(expense);
    }

    public ResponseEntity<Expense> create(ExpenseCreationDTO expense) {
        Expense newExpense = new Expense(expense);
        Expense createdExpense = this.expenseRepository.save(newExpense);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
    }

    public ResponseEntity<Expense> update(ExpenseUpdateDTO expense) {
        Expense oldExpense = this.expenseRepository.getReferenceById(expense.id());
        Expense updatedExpense = oldExpense.update(expense);
        return ResponseEntity.status(HttpStatus.OK).body(updatedExpense);
    }

    public ResponseEntity<Void> delete(Long id) {
        this.expenseRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
