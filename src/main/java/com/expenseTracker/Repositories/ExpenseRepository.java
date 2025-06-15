package com.expenseTracker.Repositories;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expenseTracker.entities.Expense;
import java.util.Date;
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer>{
	List<Expense> findByDateBetween(LocalDate fromDate, LocalDate toDate);
}

//Spring Data JPA uses method name conventions to derive the query. It breaks findByDateBetween down as:
//
//findBy → This tells Spring you want to query by a field.
//
//Date → This matches the date field in your Expense entity.
//
//Between → This is a keyword understood by Spring that generates a BETWEEN ? AND ? SQL condition.