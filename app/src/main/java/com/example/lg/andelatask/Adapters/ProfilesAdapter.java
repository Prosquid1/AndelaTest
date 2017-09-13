package com.example.lg.andelatask.Adapters;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lg.andelatask.Activities.MainActivity;
import com.example.lg.andelatask.Models.UserProfile;
import com.example.lg.andelatask.R;
import com.example.lg.andelatask.Utilities.Utility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Oyeleke Okiki on 9/12/2017.
 */

public class ProfilesAdapter extends RecyclerView.Adapter<ProfilesAdapter.ViewHolder> {


    private List<UserProfile> userProfiles;

    private MainActivity mainActivity;


    public ProfilesAdapter(MainActivity mainActivity, List<UserProfile> userProfiles){

        this.userProfiles = userProfiles;

        this.mainActivity = mainActivity;
    }

    @Override
    public void onBindViewHolder(ProfilesAdapter.ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return userProfiles.size();
    }

    @Override
    public ProfilesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_profiles, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final UserProfile userProfile = userProfiles.get(position);

        String userNameUpperCase = Utility.getSentenceCase(userProfile.login);

        holder.userName.setText(userNameUpperCase);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.showUserProfile(userProfile);
            }
        });




    }


    static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.developer_name) TextView userName;


        public ViewHolder (View v){
            super(v);
            ButterKnife.bind(this, v);
        }


    }

}
