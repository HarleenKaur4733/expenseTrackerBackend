package com.expenseTracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expenseTracker.Repositories.ExpenseRepository;
import com.expenseTracker.entities.Expense;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {
	
	@Autowired
	ExpenseRepository expenseRepo;
	
	public void save(Expense e) {
		expenseRepo.save(e);
	}
	
	public List<Expense> getAll() {
		List<Expense> expenseList= expenseRepo.findAll();
		return expenseList;
	}
	
	public double getTotalExpense() {
		List<Expense> expenseList= expenseRepo.findAll();
		double sum = 0;
		for(int i = 0; i < expenseList.size(); i++) {
			sum += expenseList.get(i).getAmount();
		}
		return sum;
	}
	
	public List<Expense> findByDateBetween(LocalDate fromDate, LocalDate toDate){
		return expenseRepo.findByDateBetween(fromDate, toDate);
	}
}
