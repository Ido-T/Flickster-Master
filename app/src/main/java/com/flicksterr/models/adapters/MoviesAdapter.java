package com.flicksterr.models.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.flicksterr.DetailActivity;
import com.flicksterr.R;
import com.flicksterr.models.Movie;

import org.parceler.Parcels;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.viewHolder> {

    Context context;
    List<Movie> movies;

    public MoviesAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d("smile","onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Log.d("smile","onBindViewHolder : " + position);
        Movie movie = movies.get(position);

        // bind the movie data into the viewHolder

        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        RelativeLayout container;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);

        }

        public void bind(final Movie movie) {

            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());

            String imageURL = movie.getPosterPath();

            // Reference the backdrop path if phone is Landscape
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                imageURL = movie.getBackDropPath();
            }

            Glide.with(context).load(imageURL).into(ivPoster);
                container.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {

                        Intent i = new Intent(context, DetailActivity.class);
                        i.putExtra("title", movie.getTitle());
                        i.putExtra("movie", Parcels.wrap(movie));
                        context.startActivity(i);


                    }
                });


        }
    }
}
