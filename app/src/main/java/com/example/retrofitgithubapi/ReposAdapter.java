package com.example.retrofitgithubapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.retrofitgithubapi.model.GitHubRepo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ReposViewHolder> {
    private List<GitHubRepo> repos;
    private Context context;

    public ReposAdapter(List<GitHubRepo> repos, Context context) {
        this.repos = repos;
        this.context = context;
    }

    @NonNull
    @Override
    public ReposAdapter.ReposViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ReposViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReposViewHolder holder, int position) {


        holder.repoName.setText(repos.get(position).getName());


        if(repos.get(position).getDescription()==null){
            holder.repoDescription.setText("No Description Provided");
        }else{
            holder.repoDescription.setText(repos.get(position).getDescription());
        }

        if(repos.get(position).getLanguage() == null){
            holder.repoLanguage.setText("No Language Provided");
        }else{
            holder.repoLanguage.setText(repos.get(position).getLanguage());
        }



    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public static class ReposViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView repoName, repoDescription, repoLanguage;

        public ReposViewHolder(View v) {
            super(v);
            cardView = v.findViewById(R.id.card);
            repoName = v.findViewById(R.id.repo_name);
            repoDescription = v.findViewById(R.id.repo_desc);
            repoLanguage = v.findViewById(R.id.repo_language);

        }


    }
}
