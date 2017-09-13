package com.example.lg.andelatask.Utilities.REST;

import com.example.lg.andelatask.Models.UserProfile;

import java.util.List;

/**
 * Created by LG on 9/13/2017.
 */

public interface UsersRequestInterface {

    void onFetchFailed(String message);

    void onFetchUnsuccessful();

    void onFetchSuccess(List<UserProfile> userProfiles);

}
