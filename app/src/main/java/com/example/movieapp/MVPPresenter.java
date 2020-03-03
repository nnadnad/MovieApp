package com.example.movieapp;

import com.example.movieapp.api.Client;
import com.example.movieapp.model.Movie;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class MVPPresenter {
    private MovieView movieView;
    private Client mClient;
    private int mCurrentPage;

    MVPPresenter(MovieView listMovieView) {
        this.movieView= listMovieView;
        mClient = new Client();
        mCurrentPage = 0;
    }

    void getMovies(String lang){
        mCurrentPage++;
        mClient.getTopRatedMovies(lang, mCurrentPage).enqueue(new Callback<Movie.ListMovie>() {
            @Override
            public void onResponse(@NotNull Call<Movie.ListMovie> call, @NotNull Response<Movie.ListMovie> response) {
                if(response.code()==200 && response.body()!= null){
                    ArrayList<Movie> movies = response.body().movieList;
                    movieView.success(movies,mCurrentPage);
                }
                else{
                    mCurrentPage--;
                    movieView.toast("response null");
                }
            }

            @Override
            public void onFailure(@NotNull Call<Movie.ListMovie> call, @NotNull Throwable t) {
                movieView.toast("error");
                mCurrentPage--;
                t.printStackTrace();
            }
        });
    }
}
