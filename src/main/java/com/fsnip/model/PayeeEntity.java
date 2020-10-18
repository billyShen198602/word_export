package com.fsnip.model;

import java.util.List;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @author billy
 */
@Data
public class PayeeEntity {

    private String name;

    private String bankAccount;

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

}
