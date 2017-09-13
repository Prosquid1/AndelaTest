package com.example.lg.andelatask.Utilities.REST;

import com.example.lg.andelatask.Models.GithubResponse;
import com.example.lg.andelatask.R;
import com.example.lg.andelatask.Utilities.GitHubService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LG on 9/13/2017.
 */

public class UsersRepository {


    private UsersRequestInterface usersRequestInterface;


    public UsersRepository(UsersRequestInterface usersRequestInterface){

        this.usersRequestInterface = usersRequestInterface;
    }




    public void getUsersData() {

        GitHubService service = ApiClient.getClient().create(GitHubService.class);

        String javaDevsLagosQuery = "search/users?q=language:java+location:lagos";

        Call<GithubResponse> responseCall = service.getUsersByLocation(javaDevsLagosQuery);


        responseCall.enqueue(new Callback<GithubResponse>() {
            @Override
            public void onResponse(Call<GithubResponse> call, Response<GithubResponse> response) {



                if (response.isSuccessful()) {

                    usersRequestInterface.onFetchSuccess(response.body().items);

                } else {

                    usersRequestInterface.onFetchUnsuccessful();

                }

            }

            @Override
            public void onFailure(Call<GithubResponse> call, Throwable t) {

                usersRequestInterface.onFetchFailed(t.getMessage());

            }
        });
    }



}
