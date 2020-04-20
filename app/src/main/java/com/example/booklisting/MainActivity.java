package com.example.booklisting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String GOOGLE_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=agriculture";


    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int LOADER_ID = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Book> books = QueryUtils.fetchBookData(GOOGLE_REQUEST_URL);
//        List<Book> books = new ArrayList<>();
//        books.add(new Book("anything1", "anything"));
//        books.add(new Book("anything2", "anything3"));
        //Find a reference to the {@link ListView} in the layout
        ListView BookListView = (ListView) findViewById(R.id.list);

        // create a new array adapter to set on the list view
        BookAdapter bookAdapter = new BookAdapter(this, books);

        //set the adapter on the list
        BookListView.setAdapter(bookAdapter);

        //create a new arraylist






//        if(isNetworkAvailable()){
//            // Get a reference to the LoaderManager, in order to interact with loaders.
//            LoaderManager loaderManager = getLoaderManager();
//            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
//            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
//            // because this activity implements the LoaderCallbacks interface).
//            loaderManager.initLoader(LOADER_ID,null,this);
//        }
//        else {
//
//        }


        // find the edit text where the user will search for a book
        EditText bookSearchEditText = (EditText) findViewById(R.id.search_book);
        // getting the user search input
        String bookSearch = bookSearchEditText.getText().toString();



        // finding the search button view
        Button searchButton = (Button) findViewById(R.id.search_button);

        //setting onClickListener for the button view
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }


//    private boolean isNetworkAvailable() {
//        ConnectivityManager connectivityManager
//                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
//    }

//    @NonNull
//    @Override
//    public Loader<List<Book>> onCreateLoader(int id, @Nullable Bundle args) {
//        // Create a new loader for the given URL
//        return new BookLoader(this, GOOGLE_REQUEST_URL);
//    }
//
//    @Override
//    public void onLoadFinished(@NonNull Loader<List<Book>> loader, List<Book> data) {
//
//    }
//
//    @Override
//    public void onLoaderReset(@NonNull Loader<List<Book>> loader) {
//
//    }
}
