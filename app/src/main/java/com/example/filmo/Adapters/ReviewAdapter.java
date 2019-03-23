package com.example.filmo.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.filmo.Model.reviews.ResultReview;
import com.example.filmo.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private List<ResultReview> reviewsList;
    private LayoutInflater layoutInflater;

    public ReviewAdapter(Context context, List<ResultReview> List) {
        reviewsList = List;
        Log.v("hello", reviewsList.get(0).getAuthor());

        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.review_item_rv, parent, false);
        return new ReviewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ViewHolder holder, int position) {
        holder.author.setText(reviewsList.get(position).getAuthor());
        holder.reviewContent.setText(reviewsList.get(position).getContent());
//        holder.reviewLbl.setVisibility(View.VISIBLE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return reviewsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.review_author)
        TextView author;
        @BindView(R.id.review_content)
        TextView reviewContent;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
            });
        }

    }
}
