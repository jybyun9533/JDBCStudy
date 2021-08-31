package com.encore.vo;

public class User {
	private int ssn;
	private String userName;
	private String address;
	
	public User(int ssn, String userName, String address) {
		super();
		this.ssn = ssn;
		this.userName = userName;
		address = address;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getaddress() {
		return address;
	}

	public void setaddress(String address) {
		address = address;
	}

	@Override
	public String toString() {
		return "User [ssn=" + ssn + ", userName=" + userName + ", address=" + address + "]";
	}
	
}
