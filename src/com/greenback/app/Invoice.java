package com.greenback.app;

public class Invoice {

	private String ref;
	private String date;
	private String currency;
	private String amount;

	public Invoice(String invoice, String date, String currency, String amount) {
		super();
		this.ref = invoice;
		this.date = date;
		this.currency = currency;
		this.amount = amount;
	}

	public String getInvoice() {
		return ref;
	}

	public void setInvoice(String invoice) {
		this.ref = invoice;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Invoice [invoice=" + ref + ", date=" + date + ", currency=" + currency + ", amount=" + amount + "]";
	}

}
