package com.example.retrofitgithubapi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.retrofitgithubapi.model.GitHubRepo;
import com.example.retrofitgithubapi.rest.APIClient;
import com.example.retrofitgithubapi.rest.GitHubRepoEndPoint;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RepositoriesActivity extends AppCompatActivity {

    private String mReceivedUserName;
    private TextView mUserName;
    private RecyclerView mRecyclerView;
    private List<GitHubRepo> myDataSource = new ArrayList<>();
    private RecyclerView.Adapter myadapter;
    private View v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);

        Bundle extras = getIntent().getExtras();
        mReceivedUserName = extras.getString("username");
        mUserName = findViewById(R.id.user_name);

        mUserName.setText(mReceivedUserName);

        v =findViewById(R.id.include_name);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        myadapter = new ReposAdapter(myDataSource,getApplicationContext());
        mRecyclerView.setAdapter(myadapter);


        loadRepositories();
    }

    public void loadRepositories() {

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View myview = layoutInflater.inflate(R.layout.custom_progressbar, null);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(myview).setCancelable(false);

        TextView mTextView = myview.findViewById(R.id.prg_textview);
        mTextView.setText("Loading....");
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();


        GitHubRepoEndPoint apiService = APIClient.getClient().create(GitHubRepoEndPoint.class);
        Call<List<GitHubRepo>> call = apiService.getRepo(mReceivedUserName);
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {

                v.setVisibility(View.VISIBLE);
                alertDialog.dismiss();
                myDataSource.clear();
                myDataSource.addAll(response.body());
                myadapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {

            }
        });

    }
}
