package com.spring.springaop.dto;


public class Book {

    private String title;
    private String isbn;
    private int price;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public Book(String title, String isbn, int price) {
        super();
        this.title = title;
        this.isbn = isbn;
        this.price = price;
    }
    @Override
    public String toString() {
        return "Book [title=" + title + ", isbn=" + isbn + ", price=" + price + "]";
    }
}
