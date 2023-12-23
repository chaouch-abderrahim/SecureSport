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
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private RecyclerView newsRecyclerView;
    private NewsAdapter newsAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        newsRecyclerView = rootView.findViewById(R.id.newsRecyclerView);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<NewsItem> newsList = readNewsFromJson(); // Utilisez cette méthode pour lire les actualités à partir du fichier JSON
        newsAdapter = new NewsAdapter(newsList);
        newsRecyclerView.setAdapter(newsAdapter);

        return rootView;
    }
    private List<NewsItem> readNewsFromJson() {
        List<NewsItem> newsList = new ArrayList<>();

        try {
            // Charger le fichier JSON à partir des ressources (placez votre fichier dans le dossier "res/raw")
            InputStream inputStream = getResources().openRawResource(R.raw.news_data);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, StandardCharsets.UTF_8);

            // Analyser le fichier JSON
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");
                String content = jsonObject.getString("content");
                newsList.add(new NewsItem(title, content));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return newsList;
    }

    private List<NewsItem> generateDummyNews() {
        List<NewsItem> newsList = new ArrayList<>();
        newsList.add(new NewsItem("Title 1", "L'équipe nationale de football a brillamment remporté son dernier match, écrasant son adversaire avec un score final de 4-0. Les joueurs ont démontré une performance exceptionnelle, marquant des buts impressionnants et montrant une coordination sans faille sur le terrain. Cette victoire renforce la position de l'équipe dans le classement mondial et suscite l'enthousiasme parmi les supporters qui célèbrent cette performance exceptionnelle."));
        newsList.add(new NewsItem("Title 2", "Content 2"));
        // Add more news items as needed
        return newsList;

    }
}
