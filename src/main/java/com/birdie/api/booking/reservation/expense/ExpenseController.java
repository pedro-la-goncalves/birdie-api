package com.birdie.api.booking.reservation.expense;

import com.birdie.api.booking.reservation.expense.dto.ExpenseCreationDTO;
import com.birdie.api.booking.reservation.expense.dto.ExpenseUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/booking/reservation/expense")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @GetMapping
    ResponseEntity<List<Expense>> findAll() { return this.expenseService.findAll(); }

    @GetMapping(value = "/{id}")
    ResponseEntity<Expense> findOne(@PathVariable(value = "id") Long id) { return this.expenseService.findOne(id); }

    @PostMapping
    @Transactional
    ResponseEntity<Expense> create(@RequestBody @Valid ExpenseCreationDTO expense) { return this.expenseService.create(expense); }

    @PutMapping
    @Transactional
    ResponseEntity<Expense> update(@RequestBody @Valid ExpenseUpdateDTO expense) { return this.expenseService.update(expense); }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable(value = "id") long id) {
        return this.expenseService.delete(id);
    }

}
