package com.example.movieapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.model.Movie;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MovieAdapter extends PagedListAdapter<Movie, MovieAdapter.ViewHolder> {
    private ArrayList<Movie> mMovies;
    private Context context;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;
    private String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/";

    private static DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.filmID.equals(newItem.filmID);
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.equals(newItem);
        }
    };

    MovieAdapter(Context context, ArrayList<Movie> movies) {
        super(DIFF_CALLBACK);
        this.context = context;
        this.mMovies = movies;
        this.inflater = LayoutInflater.from(this.context);
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.movie_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int pos) {
        Movie movie = mMovies.get(pos);
        viewHolder.movieTitle.setText(movie.title);
        viewHolder.releaseDate.setText(movie.releaseDate);
        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + movie.posterPath).into(viewHolder.poster);
    }

    public int getCount() {
        return mMovies.size();
    }

    //CRUD movie
    public void updateMovie(ArrayList<Movie> movies) {
        this.mMovies = movies;
        notifyDataSetChanged();
    }

    public void createMovie(ArrayList<Movie> movies) {
        this.mMovies.addAll(movies);
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView movieTitle;
        TextView releaseDate;
        ImageView poster;

        ViewHolder(View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movie_title);
            releaseDate = itemView.findViewById(R.id.release_date);
            poster = itemView.findViewById(R.id.poster_path);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.onItemClick(view, getAdapterPosition());
                Toast.makeText(context, movieTitle.getText(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setClickListener(MainActivity itemClickListener) {
        this.clickListener = (ItemClickListener) itemClickListener;
    }

}
