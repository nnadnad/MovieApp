package com.example.movieapp.api;

import com.example.movieapp.model.Movie;

import retrofit2.Call;

public class Client {
    private Server server;
    public Client(){
        server = apiClient.getClient().create(Server.class);
    }

    public Call<Movie.ListMovie> getTopRatedMovies(String lang, int page) {
        return server.getTopRatedMovies(lang, page);
    }
}
