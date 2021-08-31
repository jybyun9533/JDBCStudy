package com.encore.vo;

public class Stock {
	private int stockNum;
	private String stockName;
	private double stockPrice;
	private double stockQuantity;
	
	public Stock(int stockNum, String stockName, double stockPrice, double stockQuantity) {
		super();
		this.stockNum = stockNum;
		this.stockName = stockName;
		this.stockPrice = stockPrice;
		this.stockQuantity = stockQuantity;
	}

	public int getStockNum() {
		return stockNum;
	}

	public void setStockNum(int stockNum) {
		this.stockNum = stockNum;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public double getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(double stockPrice) {
		this.stockPrice = stockPrice;
	}

	public double getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(double stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	@Override
	public String toString() {
		return "Stock [stockNum=" + stockNum + ", stockName=" + stockName + ", stockPrice=" + stockPrice
				+ ", stockQuantity=" + stockQuantity + "]";
	}
	
	
}
