package com.example.movieapp;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.model.Movie;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public final int PAGINATION_MARGIN = 2;
    public final String LANG = "en-US";
    @BindView(R.id.listMovie)
    RecyclerView mRecyclerView;
    MVPPresenter mPresenter;
    MovieAdapter mAdapter;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new MVPPresenter((MovieView) this);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new MovieAdapter(this, new ArrayList<Movie>());

        mAdapter.setClickListener(MainActivity.this);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = recyclerView.getLayoutManager().getChildCount();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                int firstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount - PAGINATION_MARGIN
                        && firstVisibleItemPosition >= 0) {
                    mPresenter.getMovies(LANG);
                }
            }
        });

        mPresenter.getMovies("en-US");
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));

    }

    public void onItemClick(View view, int position) {
        Log.d("ListActivity", "onItemClick: " + position);
    }

    @Override
    public void onSaveInstanceState(@NotNull Bundle outState, @NotNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    public void onSucceedGetResult(ArrayList<Movie> movies, int page) {
        Toast.makeText(this,"PAGE : "+ page, Toast.LENGTH_SHORT).show();
        mAdapter.updateMovie(movies);
    }

    public void viewToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
