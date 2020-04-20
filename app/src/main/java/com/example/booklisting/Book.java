package com.example.booklisting;

public class Book {

    private String mBookName;
    private String mBookId;

    public Book(String bookName, String bookId){
        mBookName = bookName;
        mBookId = bookId;
    }

    public String getBookName(){
        return mBookName;
    }

    public String getBookId(){
        return mBookId;
    }

}
