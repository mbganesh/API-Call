package com.netcom.lasttry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        init();
    }

    private void init() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://jsonplaceholder.typicode.com/")
                .baseUrl(JSonPlaceHolderApi.BASE_URL)
                .client(client)           // new Stuff
                .addConverterFactory(GsonConverterFactory.create(gson))     //  (gson)
                .addConverterFactory(ScalarsConverterFactory.create())          // new line
                .build();

        JSonPlaceHolderApi jSonPlaceHolderApi = retrofit.create(JSonPlaceHolderApi.class);

        Call<List<Post>> call = jSonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
//        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//            public void onResponse(Call<String> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code : " + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts){
                    String content ="";

                    content += "TVS Racing : " + post.getBike() + "\n";

//                    content += "ID : " + post.getId() + "\n";
//                    content += "UserID : " + post.getUserId() + "\n";
//                    content += "Title :  " + post.getTitle() + "\n";
//                    content += "Body : " + post.getText() + "\n\n";

                    textViewResult.append(content);             // append
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
