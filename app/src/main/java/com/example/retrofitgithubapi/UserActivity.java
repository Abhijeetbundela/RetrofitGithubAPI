package com.example.retrofitgithubapi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.InputEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitgithubapi.model.GitHubUser;
import com.example.retrofitgithubapi.rest.APIClient;
import com.example.retrofitgithubapi.rest.GitHubUserEndPoints;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UserActivity extends AppCompatActivity {

    private Button mRepositoriesBtn;
    private ImageView mUserImage;
    private TextView mUser, mLogin, mFollowers, mFollowing, mEmail;
    private Bundle extras;
    private String newString;
    private Bitmap myImage;

    private View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mUser = findViewById(R.id.user);
        mLogin = findViewById(R.id.login);
        mFollowers = findViewById(R.id.followers);
        mFollowing = findViewById(R.id.following);
        mEmail = findViewById(R.id.email);

        mUserImage = findViewById(R.id.user_image);

        mRepositoriesBtn = findViewById(R.id.repo_btn);

        v = findViewById(R.id.include);

        extras = getIntent().getExtras();
        newString = extras.getString("STRING_I_NEED");

        loadData();

        mRepositoriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), RepositoriesActivity.class).putExtra("username", newString));

            }
        });
    }

    private void loadData() {

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View myview = layoutInflater.inflate(R.layout.custom_progressbar, null);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(myview).setCancelable(false);

        TextView mTextView = myview.findViewById(R.id.prg_textview);
        mTextView.setText("Loading....");
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        final GitHubUserEndPoints apiService = APIClient.getClient().create(GitHubUserEndPoints.class);

        Call<GitHubUser> call = apiService.getUser(newString);
        call.enqueue(new Callback<GitHubUser>() {
            @Override
            public void onResponse(Call<GitHubUser> call, Response<GitHubUser> response) {

                alertDialog.dismiss();

                if (response.body().getName().isEmpty() || response.body().getName() == null) {

                    mUser.setText("No name provided");

                } else {

                    mUser.setText(response.body().getName());

                }

                mFollowers.setText(response.body().getFollowers());
                mFollowing.setText(response.body().getFollowing());
                mLogin.setText(response.body().getLogin());

                if (response.body().getEmail() == null) {
                    mEmail.setText("No email provided");
                } else {
                    mEmail.setText(response.body().getEmail());
                }

                ImageDownloader task = new ImageDownloader();
                try {
                    myImage = task.execute(response.body().getAvatar()).get();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (myImage == null) {
                    mUserImage.setImageResource(R.drawable.default_profile);

                } else {

                    mUserImage.setImageBitmap(myImage);

                }

                v.setVisibility(View.VISIBLE);
                mRepositoriesBtn.setVisibility(View.VISIBLE);

            }

            @Override
            public void onFailure(Call<GitHubUser> call, Throwable t) {

            }
        });

    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
                return myBitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
