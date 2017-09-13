package com.example.lg.andelatask.Activities;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lg.andelatask.Adapters.ProfilesAdapter;
import com.example.lg.andelatask.Models.GithubResponse;
import com.example.lg.andelatask.Models.UserProfile;
import com.example.lg.andelatask.R;
import com.example.lg.andelatask.Utilities.Constants;
import com.example.lg.andelatask.Utilities.GitHubService;
import com.example.lg.andelatask.Utilities.REST.ApiClient;
import com.example.lg.andelatask.Utilities.REST.UsersRepository;
import com.example.lg.andelatask.Utilities.REST.UsersRequestInterface;
import com.example.lg.andelatask.Utilities.Utility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lg.andelatask.Utilities.UI_Utilities.displayToast;


/**
 * Created by Oyeleke Okiki on 9/12/2017.
 * As part of Andela Test
 * Activity class showing all Java Developers in Lagos
 */

public class MainActivity extends AppCompatActivity implements UsersRequestInterface{

    @BindView(R.id.all_users_recycler)
    RecyclerView usersRecyclerView;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.refresh_msg_textview)
    TextView refreshTextView;

    private ProfilesAdapter profilesAdapter;

    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setupViews();

        //Initial Load
        swipeRefreshLayout.setRefreshing(true);
        getUserProfiles();
    }



    private void setupViews(){

        int swipeRefreshColor = ContextCompat.getColor(this, R.color.colorAccent);
        swipeRefreshLayout.setColorSchemeColors(swipeRefreshColor);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (!isLoading) getUserProfiles();

            }
        });

    }



    /**
     * Fetch user profiles from GitHub
     */
    private void getUserProfiles(){

        //If a request is already in progress, do nothing i.e return
        if (isLoading) return;

        //Hide "Pull to refresh Text"
        refreshTextView.setVisibility(View.INVISIBLE);


        //If user has no Internet, stop all refreshing activities and notify user
        if (!Utility.isNetworkAvailable(this)){

            showProfileLoadingError(getString(R.string.no_internet_connection));
            return;

        }

        isLoading = true;

        UsersRepository usersRepository = new UsersRepository(this);
        usersRepository.getUsersData();


    }



    @Override
    public void onFetchUnsuccessful(){

        completeFetchRequest();
        showProfileLoadingError(getString(R.string.generic_error));
    }


    @Override
    public void onFetchFailed(String message){

        completeFetchRequest();
        showProfileLoadingError(message);

    }


    @Override
    public void onFetchSuccess(List<UserProfile> userProfiles){

        completeFetchRequest();
        initializeRecycler(userProfiles);
    }



    private void completeFetchRequest(){
        isLoading = false;
        swipeRefreshLayout.setRefreshing(false);
    }


    /**
     * If the adapter is null, show empty state, else leave the current items as is and display error message
     * @param message Message to display in Toast
     **/
    private void showProfileLoadingError(String message){

        displayToast(MainActivity.this, message);

        if (swipeRefreshLayout.isRefreshing()) swipeRefreshLayout.setRefreshing(false);

        if (profilesAdapter == null || profilesAdapter.getItemCount() == 0){

            refreshTextView.setVisibility(View.VISIBLE);
            usersRecyclerView.setVisibility(View.INVISIBLE);

        } else {

            refreshTextView.setVisibility(View.INVISIBLE);
            usersRecyclerView.setVisibility(View.VISIBLE);

        }


    }


    /**
     * Setup recycler view, Empty state also handled
     * @param userProfiles users gotten from the items array
     */
    private void initializeRecycler(List<UserProfile> userProfiles){

        if (userProfiles == null){

            displayToast(this, getString(R.string.pull_to_refresh_msg));

        } else if (userProfiles.isEmpty()){

            displayToast(this, "No users found!");


        } else {

            //Success, display view

            //Show RecyclerView if invisible
            usersRecyclerView.setVisibility(View.VISIBLE);

            profilesAdapter = new ProfilesAdapter(this, userProfiles);
            usersRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            usersRecyclerView.setAdapter(profilesAdapter);

            profilesAdapter.notifyDataSetChanged();

        }

    }


    /**
     * Show user profile from clicked RecyclerView items in {@link ProfilesAdapter}
     * @param userProfile users gotten from the items array
     *
     */

    public void showUserProfile(UserProfile userProfile){

        Intent intent = new Intent(this, UserDetailsActivity.class);

        intent.putExtra(UserDetailsActivity.githubUrlKey, userProfile.html_url);
        intent.putExtra(UserDetailsActivity.userAvatarKey, userProfile.avatar_url);

        startActivity(intent);


    }


}
