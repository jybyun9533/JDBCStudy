package com.encore.vo;

public class Trade {
	private int tradeNum;
	private double tradeQuantity;
	private double tradePrice;
	private int ssn;
	private int stockNum;

	public Trade(int tradeNum, double tradeQuantity, double tradePrice, int ssn, int stockNum) {
		super();
		this.tradeNum = tradeNum;
		this.tradeQuantity = tradeQuantity;
		this.tradePrice = tradePrice;
		this.ssn = ssn;
		this.stockNum = stockNum;
	}

	public int getTradeNum() {
		return tradeNum;
	}

	public void setTradeNum(int tradeNum) {
		this.tradeNum = tradeNum;
	}

	public double getTradeQuantity() {
		return tradeQuantity;
	}

	public void setTradeQuantity(double tradeQuantity) {
		this.tradeQuantity = tradeQuantity;
	}

	public double getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(double tradePrice) {
		this.tradePrice = tradePrice;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public int getStockNum() {
		return stockNum;
	}

	public void setStockNum(int stockNum) {
		this.stockNum = stockNum;
	}

	@Override
	public String toString() {
		return "trade [tradeNum=" + tradeNum + ", tradeQuantity=" + tradeQuantity + ", tradePrice=" + tradePrice
				+ ", ssn=" + ssn + ", stockNum=" + stockNum + "]";
	}

}
