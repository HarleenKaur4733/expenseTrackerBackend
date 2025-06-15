package com.expenseTracker.DTO;

import java.time.LocalDate;
import java.util.Date;
public class FilterDate {
	LocalDate fromDate;
	LocalDate toDate;

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "FilterDate [fromDate=" + fromDate + ", toDate=" + toDate + "]";
	}

	public FilterDate(LocalDate fromDate, LocalDate toDate) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public FilterDate() {
		super();
		// TODO Auto-generated constructor stub
	}

}
