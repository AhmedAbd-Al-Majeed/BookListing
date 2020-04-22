package com.example.booklisting;

import java.net.URI;
import java.net.URISyntaxException;

public class Book {

    private String mBookName;
    private String mBookId;
    private String mSelfLink;
    private String mThumbnail;

    public Book(String bookName, String bookId, String selfLink, String thumbnail) {
        mBookName = bookName;
        mBookId = bookId;
        mSelfLink = selfLink;
        mThumbnail = thumbnail;
    }

    public String getBookName() {
        return mBookName;
    }

    public String getBookId() {
        return mBookId;
    }

    public String getSelfLink() {
        return mSelfLink;
    }
    public String getThumbnail() {
        return mThumbnail;
    }
}
