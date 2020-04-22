package com.example.booklisting;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<Book>> {
    public static final String LOG_TAG = BookLoader.class.getName();

    /**
     * Query url
     */
    private String mUrl;

    public BookLoader(Context context, String url){
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "TEST: onStartLoading() ....");
        forceLoad();
    }

    @Nullable
    @Override
    public List<Book> loadInBackground() {
        Log.i(LOG_TAG, "TEST: loadInBackground() ....");
        if(mUrl==null){
        return null;
        }
        // Perform the network request, parse the response, and extract a list of books.
        List<Book> books = QueryUtils.fetchBookData(mUrl);
        return books;
    }
}
