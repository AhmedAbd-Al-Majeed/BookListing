package com.example.booklisting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, List<Book> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Book currentBook = getItem(position);
        TextView nameTextView =(TextView) listItemView.findViewById(R.id.title);
        nameTextView.setText(currentBook.getBookName());

        TextView idTextView =(TextView) listItemView.findViewById(R.id.name_id);
        idTextView.setText(currentBook.getBookId());

        return listItemView;

    }
}
