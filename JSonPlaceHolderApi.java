package com.netcom.lasttry;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface JSonPlaceHolderApi {

    String BASE_URL = "http://192.168.1.22:9999/";

    @GET("apache")               //relative Url            (posts)            //  extr link name
    Call<List<Post>> getPosts ();               // (@Body String car)

    //    Call<String> getPosts();
//    @GET("car")
//    Call<String> getPosts();
}
