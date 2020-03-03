package com.example.movieapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Movie {
    @SerializedName("id")
    public String filmID;
    @SerializedName("title")
    public String title;
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("release_date")
    public String releaseDate;
    //  Return API fetch
    public static class ListMovie{
        @SerializedName("page")
        public int page;
        @SerializedName("total_results")
        public int totalResults;
        @SerializedName("total_pages")
        public int totalPage;
        @SerializedName("status_message")
        public String statusMessage;
        @SerializedName("success")
        public boolean success;
        @SerializedName("results")
        public ArrayList<Movie> movieList = new ArrayList<>();
    }
}
