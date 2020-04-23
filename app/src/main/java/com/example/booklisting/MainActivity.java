package com.example.booklisting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText mEditText;

    public static final String LOG_TAG = MainActivity.class.getName();

    private String url = "https://www.googleapis.com/books/v1/volumes?";

    String searchField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "TEST: Earthquake activity onCreate called:");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find the edit text where the user will search for a book
        mEditText = (EditText) findViewById(R.id.search_book);

        //finding the search button view
        Button searchButton = (Button) findViewById(R.id.search_button);

        Log.i(LOG_TAG, "Search field: " + url);


        //setting onClickListener for the button view
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultsIntent = new Intent(MainActivity.this, ResultsActivity.class);
                //getting the user search input
                searchField = mEditText.getText().toString();
                Log.i(LOG_TAG, "edit text: " + searchField);
                resultsIntent.putExtra("url", buildUrl());
                startActivity(resultsIntent);
            }
        });
    }

    private String buildUrl() {
        Uri baseUri = Uri.parse(url);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("q", searchField);
        String myUrl = uriBuilder.build().toString();
        Log.v(LOG_TAG, myUrl);
        return myUrl;
    }
}
