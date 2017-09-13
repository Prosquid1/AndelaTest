package com.example.lg.andelatask.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lg.andelatask.R;
import com.example.lg.andelatask.Utilities.UI_Utilities;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Oyeleke Okiki on 9/12/2017.
 * As part of Andela Test
 * Activity class showing individual profile details
 */

public class UserDetailsActivity extends Activity {

    public static String githubUrlKey = "GITHUB_URL", userAvatarKey = "AVATAR_KEY";


    @BindView(R.id.github_link)
    TextView githubLinkTextView;
    @BindView(R.id.developer_profile_pic)
    ImageView developerAvatarImg;
    @BindView(R.id.share_button)
    Button shareButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);
        ButterKnife.bind(this);

    }


    @Override
    protected void onResume() {
        super.onResume();
        setupViews();
    }


    private void setupViews(){

        Intent passedDataIntent = getIntent();

        if (passedDataIntent == null){

            UI_Utilities.displayToast(this, "No Data Available");

            finish();

            return;
        }



        final String githubUrlText = passedDataIntent.getStringExtra(githubUrlKey);

        final String userAvatarLink = passedDataIntent.getStringExtra(userAvatarKey);



        //TextView will display null string as blank so not necessary to check for null

        githubLinkTextView.setText(githubUrlText);

        if (userAvatarLink != null){

            Picasso.with(this).load(userAvatarLink)
                    .placeholder(R.mipmap.icon_user_profile)
                    .error(R.mipmap.icon_user_profile)
                    .into(developerAvatarImg);

        }


        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent .setType("text/plain");
                intent .putExtra(Intent.EXTRA_TEXT, githubUrlText);
                startActivity(intent);

            }
        });


    }




}
