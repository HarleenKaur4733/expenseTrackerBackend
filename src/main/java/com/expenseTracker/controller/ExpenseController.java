package com.expenseTracker.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.expenseTracker.DTO.FilterDate;
import com.expenseTracker.entities.Expense;
import com.expenseTracker.service.ExpenseService;

@Controller
public class ExpenseController {

	@Autowired
	ExpenseService expenseService;

	@GetMapping("/")
	public String home() {
		return "home";
	}

	// mai form k sath ek object bez rhi hoon
	// jb bhi mujhe kuch show krvana hai, use model and view
	@GetMapping("/addExpense")
	public ModelAndView addExpense() {
		return new ModelAndView("expenseForm", "expense", new Expense());
	}

	// model attribute to bind form date to object
	@PostMapping("/saveExpense")
	public String saveExpense(@ModelAttribute Expense expense) {
		expenseService.save(expense);
		return "redirect:/viewExpenses";
	}

//	@GetMapping("/viewExpenses")
//	public ModelAndView viewExpenses() {
//		List<Expense> expenseList = expenseService.getAll();
//		double totalExpense = expenseService.getTotalExpense();
//		ModelAndView mv = new ModelAndView("showExpenses");
//		mv.addObject("expenseList", expenseList);
//		mv.addObject("totalExpense", totalExpense);
//		return mv;
//
//		// return new ModelAndView("showExpenses", "expenseList", expenseList,
//		// "totalExpense", totalExpense);
//	}

	@GetMapping("/showExpenseByDate")
	public ModelAndView showExpenseByDate() {
		return new ModelAndView("filterExpense", "filterDate", new FilterDate());

	}

	@PostMapping("/getDateWiseExpense")
	public ModelAndView getDateWiseExpense(@ModelAttribute FilterDate filterDate) {
		List<Expense> expenseList = expenseService.findByDateBetween(filterDate.getFromDate(), filterDate.getToDate());
		return new ModelAndView("filterExpense", "expenseFilterList", expenseList);

	}

	@GetMapping("/editExpense/{id}")
	public ModelAndView editExpense(@PathVariable int id) {
		Expense expense = expenseService.getExpenseById(id);
		return new ModelAndView("editExpense", "expense", expense);

	}
	
	@GetMapping("/deleteExpense/{id}")
	public String deleteExpense(@PathVariable int id) {
		Expense expense = expenseService.getExpenseById(id);
		expenseService.deleteExpense(id);
		return "redirect:/viewExpenses";
	}
	
	@GetMapping("/searchExpense")
	public ModelAndView searchExpense(@RequestParam("keyword") String keyword) {
		List<Expense> results = expenseService.searchByTitleOrNote(keyword);
		double totalResult = expenseService.getTotalByTitleOrNote(keyword);
		ModelAndView mv = new ModelAndView("showExpenses");
		mv.addObject("expenseList", results);
		mv.addObject("totalExpense", totalResult);
		return mv;
	}
//	
//	@GetMapping("/expenses/page/{page}/size/{size}")
//	@ResponseBody 
//	public Page<Expense> getPaginatedExpenses(@PathVariable int page, @PathVariable int size) {
//	    return expenseService.findPaginatedExpenses(page, size);
//	}
//	
	@GetMapping("/viewExpenses")
	public ModelAndView getAllExpenses(
	    @RequestParam(defaultValue = "0") int page,
	    @RequestParam(defaultValue = "10") int pageSize) {

	    Page<Expense> pageView = expenseService.findPaginatedExpenses(page, pageSize);

	    ModelAndView mv = new ModelAndView("showExpenses"); 

	    mv.addObject("expenseList", pageView.getContent());
	    mv.addObject("currentPage", pageView.getNumber());
	    mv.addObject("totalPages", pageView.getTotalPages());
	    mv.addObject("pageSize", pageView.getSize());
	    mv.addObject("totalItems", pageView.getTotalElements());
	    mv.addObject("startItem", pageView.getNumber() * pageView.getSize() + 1);
	    mv.addObject("endItem", Math.min((pageView.getNumber() + 1) * pageView.getSize(), pageView.getTotalElements()));
	    mv.addObject("totalExpense", expenseService.getTotalExpense());

	    return mv;
	}


}
