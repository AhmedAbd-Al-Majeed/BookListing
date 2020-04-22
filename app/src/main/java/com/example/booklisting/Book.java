package com.example.booklisting;

public class Book {

    private String mBookName;
    private String mBookId;
    private String mSelfLink;

    public Book(String bookName, String bookId, String selfLink){
        mBookName = bookName;
        mBookId = bookId;
        mSelfLink = selfLink;
    }

    public String getBookName(){
        return mBookName;
    }

    public String getBookId(){
        return mBookId;
    }

    public String getSelfLink() {
        return mSelfLink;
    }
}
