package com.example.lg.andelatask.Utilities;

import com.example.lg.andelatask.Models.GithubResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface GitHubService {
  @GET
  Call<GithubResponse> getUsersByLocation(@Url String locationName);
}