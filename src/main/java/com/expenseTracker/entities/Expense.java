package com.expenseTracker.entities;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "expense_table")
public class Expense {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private Double amount;

	private LocalDate date;

	private String category;

	private String note;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", title=" + title + ", amount=" + amount + ", date=" + date + ", category="
				+ category + ", note=" + note + "]";
	}

	public Expense(Long id, String title, Double amount, LocalDate date, String category, String note) {
		super();
		this.id = id;
		this.title = title;
		this.amount = amount;
		this.date = date;
		this.category = category;
		this.note = note;
	}

	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}

}
