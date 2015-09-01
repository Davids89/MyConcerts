package com.example.david.example;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    private ListView mListView;
    private List<Concert> mConcertsList;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_news, container, false);
        mListView = (ListView) fragmentView.findViewById(R.id.MyListView);
        mConcertsList = new ArrayList<Concert>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Concert");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> concertList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + concertList.size() + " scores");

                    for (ParseObject parseObject : concertList) {
                        String title = (String) parseObject.get("title");
                        String link = (String) parseObject.get("link");
                        String imageLink = (String) parseObject.get("imageLink");

                        Concert concert = new Concert(title, link, imageLink);
                        mConcertsList.add(concert);
                    }

                    mListView.setAdapter(new MyAdapter());

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                openBrowser(mConcertsList.get(i).getLink());
            }
        });

        return fragmentView;
    }

    public void openBrowser(String link){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(browserIntent);
    }

    public class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mConcertsList.size();
        }

        @Override
        public Object getItem(int i) {
            return mConcertsList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            View rowView = getActivity().getLayoutInflater().inflate(R.layout.row, null);

            TextView textViewRow = (TextView) rowView.findViewById(R.id.textViewRow);
            textViewRow.setText(mConcertsList.get(i).getTitle());

            ImageView picasso = (ImageView) rowView.findViewById(R.id.imagePicasso);
            Picasso.with(getActivity()).load(mConcertsList.get(i).getImageLink()).into(picasso);

            return rowView;
        }
    }


}
