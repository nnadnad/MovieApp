package com.example.movieapp;

import com.example.movieapp.model.Movie;

import java.util.ArrayList;

public interface MovieView {
    void success(ArrayList<Movie> movies, int pagination);
    void toast(String message);
}
