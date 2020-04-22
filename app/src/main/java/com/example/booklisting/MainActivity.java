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



public class MainActivity extends AppCompatActivity  {

    private  static final String GOOGLE_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?";

    public static final String LOG_TAG = MainActivity.class.getName();


    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int LOADER_ID = 1;
    private BookAdapter mAdapter;

    private TextView mEmptyStateTextView;

    private ProgressBar mProgressBar;

    private EditText mEditText;

    private Button searchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "TEST: Earthquake activity onCreate called:");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find a reference to the {@link ListView} in the layout
        ListView bookListView = (ListView) findViewById(R.id.list);

        // create a new array adapter to set on the list view
        mAdapter = new BookAdapter(this, new ArrayList<Book>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        bookListView.setAdapter(mAdapter);

        mEmptyStateTextView = findViewById(R.id.empty_view);
        bookListView.setEmptyView(mEmptyStateTextView);

        mProgressBar = findViewById(R.id.progress_bar);




        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake.
        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Find the current earthquake that was clicked on
                Book currentEarthquake = mAdapter.getItem(position);
                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW);
                // Convert the String URL into a URI object (to pass into the Intent constructor)
                websiteIntent.setData(Uri.parse(currentEarthquake.getSelfLink()));
                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
        if(isNetworkAvailable()){
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();
            Log.i(LOG_TAG, "TEST: calling initLoader:....");
            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(LOADER_ID,null,MainActivity.this);
        }
        else {
            Log.v(LOG_TAG,"invalid");
            mProgressBar.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }


        // find the edit text where the user will search for a book
        mEditText = (EditText) findViewById(R.id.search_book);

        //finding the search button view
        searchButton = (Button) findViewById(R.id.search_button);

        //setting onClickListener for the button view
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @NonNull
    @Override
    //  Create a new loader for the given URL
    public Loader<List<Book>> onCreateLoader(int id, @Nullable Bundle args) {

        // Create a new loader for the given URL
        Log.i(LOG_TAG, "TEST: onCreateLoader called() ....");

         //getting the user search input
        String bookSearch = mEditText.getText().toString();
        Log.v(LOG_TAG, bookSearch);

        Uri baseUri = Uri.parse(GOOGLE_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("q", bookSearch);
        String myUrl = uriBuilder.toString();
        Log.v(LOG_TAG, myUrl);
        return new BookLoader(this, myUrl);
    }

    @Override
    //  Update the UI with the result
    public void onLoadFinished(@NonNull Loader<List<Book>> loader, List<Book> books) {
        Log.i(LOG_TAG, "TEST: onLoadFinished called() ....");
        // Set empty state text to display "No books found."
        mEmptyStateTextView.setText(R.string.no_books);
        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if(books != null && !books.isEmpty()){
            mAdapter.addAll(books);
            mProgressBar.setVisibility(View.GONE);
        }

    }
    @Override
    //  Loader reset, so we can clear out our existing data.
    public void onLoaderReset(@NonNull Loader<List<Book>> loader) {
        Log.i(LOG_TAG, "TEST: onLoaderReset() called ....");
        mAdapter.clear();
    }
}
