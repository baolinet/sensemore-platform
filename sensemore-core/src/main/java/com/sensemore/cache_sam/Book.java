package com.sensemore.cache_sam;

public class Book {
    String isbn;
    String name;

    public Book(String isbn, String name){
        this.isbn = isbn;
        this.name = name;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
