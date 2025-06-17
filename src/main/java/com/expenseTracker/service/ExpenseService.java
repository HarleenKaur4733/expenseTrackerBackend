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
	
	public Expense getExpenseById(int id) {
		return expenseRepo.findById(id).get();
	}
	
	public void deleteExpense(int id) {
		expenseRepo.deleteById(id);
	}
	public List<Expense> searchByTitleOrNote(String keyword){
		return expenseRepo.findByTitleContainingIgnoreCaseOrNoteContainingIgnoreCase(keyword, keyword);
	}

	public double getTotalByTitleOrNote(String keyword) {
		List<Expense> results = expenseRepo.findByTitleContainingIgnoreCaseOrNoteContainingIgnoreCase(keyword, keyword);
		double sum = 0;
		for(int i = 0; i < results.size(); i++) {
			sum += results.get(i).getAmount();
		}
		return sum;
		
	}
}
