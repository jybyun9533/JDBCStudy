package com.encore.vo;

/*
 * DB Table에 대한 정보를 담고 있는 클래스
 * Book Table의 하나의 row를 인스턴스화 시킨 클래스
 * vo, domain, dto라 부른다
 */
public class Book {
	private String isbn;
	private String title;
	private String writer;
	private String publisher;
	private int price;

	public Book() {
		super();
		this.isbn = isbn;
		this.title = title;
		this.writer = writer;
		this.publisher = publisher;
		this.price = price;
	}

	public Book(String isbn, String title, String writer, String publisher, int price) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.writer = writer;
		this.publisher = publisher;
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "\nBook [isbn=" + isbn + ", title=" + title + ", writer=" + writer + ", publisher=" + publisher
				+ ", price=" + price + "]";
	}
	
	

}
