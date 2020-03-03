package com.example.movieapp.api;

import com.example.movieapp.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Server {
    @GET("/3/movie/top_rated?api_key=TokenTheMovieDB")
    Call<Movie.ListMovie> getTopRatedMovies(@Query("language") String lang, @Query("page") int page);
}
