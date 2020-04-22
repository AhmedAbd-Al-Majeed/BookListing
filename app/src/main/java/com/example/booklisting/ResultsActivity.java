package com.example.booklisting;

import android.app.LoaderManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ResultsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


    }
}
