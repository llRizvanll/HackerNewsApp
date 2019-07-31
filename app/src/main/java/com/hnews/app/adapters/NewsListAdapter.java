package com.hnews.app.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hnews.app.R;
import com.hnews.app.databinding.NewsItemBinding;
import com.hnews.app.models.Hit;

import java.util.ArrayList;
import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {

    private final NewsAdapterListener listener;
    private LayoutInflater layoutInflater;

    public void setNewsArticles(List<Hit> newsArticles) {
        if (newsArticles!= null) {
            this.newsArticles = newsArticles;
            //notifyDataSetChanged();
        }
    }

    private List<Hit> newsArticles = new ArrayList<>();


    public NewsListAdapter(List<Hit> articles, NewsAdapterListener listener) {
        this.newsArticles = articles;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return newsArticles.size();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        NewsItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.news_item, parent, false);
        return new NewsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {
        Log.d(getClass().getName(),"Title"+newsArticles.get(i).getTitle());
        newsViewHolder.binding.setNews(newsArticles.get(i));
        //newsViewHolder.binding.tvNewsTitle.setText(newsArticles.get(i).getTitle());
        newsViewHolder.binding.executePendingBindings();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final NewsItemBinding binding;

        public NewsViewHolder(final NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int index = this.getAdapterPosition();
            listener.onNewsItemClicked(newsArticles.get(index));
        }

    }



    public interface NewsAdapterListener {
        void onNewsItemClicked(Hit article);
    }
}
