package com.fsnip.model;

import java.util.Date;
import java.util.List;

public class DataSourceBean {
	
	private String userName;
	
	private Date currDate;
	
	private List<PayeeEntity> payees;
	
	private List<Paragraph> paragraphs;
	
	private List<ChartValue> chartValues;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCurrDate() {
		return currDate;
	}

	public void setCurrDate(Date currDate) {
		this.currDate = currDate;
	}

	public List<PayeeEntity> getPayees() {
		return payees;
	}

	public void setPayees(List<PayeeEntity> payees) {
		this.payees = payees;
	}

	public List<Paragraph> getParagraphs() {
		return paragraphs;
	}

	public void setParagraphs(List<Paragraph> paragraphs) {
		this.paragraphs = paragraphs;
	}

	public List<ChartValue> getChartValues() {
		return chartValues;
	}

	public void setChartValues(List<ChartValue> chartValues) {
		this.chartValues = chartValues;
	}

}
