package com.example.shaharcohen.dibs.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.shaharcohen.dibs.services.FirebaseServices;
import com.example.shaharcohen.dibs.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FacebookConnection extends Activity {
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private SharedPreferences sp;
    private ProfilePictureView profileView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<String> permissions = new ArrayList<>();
        profileView = new ProfilePictureView(this);
        setContentView(R.layout.activity_facebook_connection);
        AppEventsLogger.activateApp(getApplication());
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("public_profile", "email", "user_friends");
        callbackManager = CallbackManager.Factory.create();
        // If using in a fragment
        // Other app specific specialization
        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // After successful connection to facebook
                Log.d("LoginResult: ", loginResult.getRecentlyGrantedPermissions().toString() + "");
                final AccessToken accessToken = loginResult.getAccessToken();
                if (accessToken != null) {
                    // creating a Facebook Graph Request to fatch all user Details
                    GraphRequestAsyncTask request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject user, GraphResponse graphResponse) {

                            Profile userProfile = Profile.getCurrentProfile();

                          //  Log.d("ver", userProfile.toString());


                            FirebaseServices fbService = new FirebaseServices();

                            ((TextView) findViewById(R.id.user_name)).setText(userProfile.getFirstName());

                            SimpleDraweeView profile_image = ((SimpleDraweeView) findViewById(R.id.user_img));

                            profile_image.setImageURI(userProfile.getProfilePictureUri(100, 100));

                                Intent tabConnectionIntent = new Intent(FacebookConnection.this,TabMenuController.class);
                                FacebookConnection.this.startActivity(tabConnectionIntent);



                            try {
                                Calendar calendar = Calendar.getInstance();
                                SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
                                String strDate = mdformat.format(calendar.getTime());
                                // fbService.addNewUser(new AppUser( userProfile.getFirstName(), userProfile.getLastName(),0,"", user.getString("gender"), 0, 0, strDate, ""));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            fbService.getAllUsers();

                        }
                    }).executeAsync();
                }

            }

            @Override
            public void onCancel() {
                // App code
                Log.d("", "onCancel: Canceled ");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("Error", error.toString());

            }
            // Add code to print out the key hash
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}



