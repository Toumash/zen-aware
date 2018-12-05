package com.zenmaster.zenware.api;

import com.zenmaster.zenware.model.Todo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SampleTodoApi {
    @GET("/posts/1")
    Call<Todo> getPosts();
}
