package com.fsnip.model;

import java.util.List;

import org.jeecgframework.poi.excel.annotation.Excel;

public class PayeeEntity {

    @Excel(name = "全称")
    private String name;

    @Excel(name = "银行账号")
    private String bankAccount;

    @Excel(name = "开户银行")
    private String bankName;
    
    private int index;
    
    private List<ChartValue> chartValues;

    public PayeeEntity(String name, String bankAccount, String bankName) {
		super();
		this.name = name;
		this.bankAccount = bankAccount;
		this.bankName = bankName;
	}

	public PayeeEntity(String name, String bankAccount, String bankName,
			int index) {
		super();
		this.name = name;
		this.bankAccount = bankAccount;
		this.bankName = bankName;
		this.index = index;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<ChartValue> getChartValues() {
		return chartValues;
	}

	public void setChartValues(List<ChartValue> chartValues) {
		this.chartValues = chartValues;
	}

}
