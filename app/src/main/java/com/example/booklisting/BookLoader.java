//package com.example.booklisting;
//
//import android.content.Context;
//
//import androidx.annotation.Nullable;
//import androidx.loader.content.AsyncTaskLoader;
//
//import java.util.List;
//
//public class BookLoader extends AsyncTaskLoader<List<Book>> {
//
//    /**
//     * Query url
//     */
//    private String mUrl;
//
//    public BookLoader(Context context, String url){
//        super(context);
//        mUrl = url;
//    }
//
//    @Override
//    protected void onStartLoading() {
//        forceLoad();
//    }
//
//    @Nullable
//    @Override
//    public List<Book> loadInBackground() {
//        if(mUrl==null){
//        return null;
//        }
//        // Perform the network request, parse the response, and extract a list of earthquakes.
//        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
//        return earthquakes;
//    }
//}
