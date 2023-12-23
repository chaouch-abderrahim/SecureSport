package com.example.securesport;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.securesport.Actualite.NewsAdapter;
import com.example.securesport.Actualite.NewsItem;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private RecyclerView newsRecyclerView;
    private NewsAdapter newsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        newsRecyclerView = rootView.findViewById(R.id.newsRecyclerView);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<NewsItem> newsList = generateDummyNews(); // Replace this with your actual news data
        newsAdapter = new NewsAdapter(newsList);
        newsRecyclerView.setAdapter(newsAdapter);

        return rootView;
    }

    private List<NewsItem> generateDummyNews() {
        List<NewsItem> newsList = new ArrayList<>();
        newsList.add(new NewsItem("Title 1", "Content 1"));
        newsList.add(new NewsItem("Title 2", "Content 2"));
        // Add more news items as needed
        return newsList;
    }
}