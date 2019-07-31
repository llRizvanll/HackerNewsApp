package com.hnews.app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.hnews.app.adapters.NewsListAdapter;
import com.hnews.app.databinding.ActivityMainBinding;
import com.hnews.app.models.Hit;
import com.hnews.app.viewmodels.NewsViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NewsListAdapter.NewsAdapterListener{

    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private ActivityMainBinding binding;
    private EditText editText;
    private NewsListAdapter newsListAdapter;
    private NewsViewModel newsViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        editText = binding.autoSearchText;
        editText.addTextChangedListener(textWatcher);
        setupToolbar();

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        init();

    }

    private void addTextListener(String query){
        newsViewModel.getNewsHeadlines(query).observe(this,hitList -> {
            if(hitList!=null){
                Log.d(getClass().getName(),"data "+hitList);
                newsListAdapter.setNewsArticles(hitList);
                newsListAdapter.notifyDataSetChanged();
            }
        });
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (count > 3){
                addTextListener(s.toString());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    private void setupToolbar() {
        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.app_name));
            //Remove trailing space from toolbar
            binding.toolbar.setContentInsetsAbsolute(10, 10);
        }
    }

    private void init(){
        newsListAdapter = new NewsListAdapter(new ArrayList<Hit>(),this);
        RecyclerView recyclerView = binding.rvNewsPosts;
        recyclerView.setAdapter(newsListAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(getResources().getDrawable(R.drawable.recycler_view_divider));
        recyclerView.addItemDecoration(divider);
    }

    @Override
    public void onNewsItemClicked(Hit article) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(MainActivity.this, Uri.parse(article.getUrl()));
    }
}
