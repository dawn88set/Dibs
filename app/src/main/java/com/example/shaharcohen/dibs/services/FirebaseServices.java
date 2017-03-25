package com.example.shaharcohen.dibs.services;

import android.util.Log;

import com.example.shaharcohen.dibs.Entity.AppUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseServices {
    private final String PROFILES = "profiles";
    private final String CONNECTIONS = "connections";
    private CallbackResponseListener callbackResponseListener  ;



    public FirebaseServices(CallbackResponseListener callbackResponseListener){
        this.callbackResponseListener  = callbackResponseListener  ;
    }

    // Adding a new user to Firebase
    public void addNewUser(AppUser appUser) {
        //getting firebase reference
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.child(PROFILES).child(appUser.getUser_id() + "").setValue(appUser);

    }

    //Geting all users (for now) from Firebase
    public ArrayList<AppUser> getAllUsers() {
        FirebaseDatabase fbDb = FirebaseDatabase.getInstance();
        DatabaseReference rootRef = fbDb.getReference();
        final ArrayList<AppUser> allUsers = new ArrayList<AppUser>();
        rootRef.child(PROFILES).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.d("getting profiles :", "onDataChange: " + dataSnapshot);
                for (DataSnapshot n : dataSnapshot.getChildren()) {
                    Log.d("user", n.toString());

                    //  allUsers.add(new AppUser(n.child("first_name"), n.child("last_name"), n.child("age"), n.child("about_me"), n.child("gender"), n.child("geographic_lat"), n.child("geographic_len"), n.child("join_date"), n.child("looking_for_gender")));
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        return null;

    }

    public ArrayList<AppUser> getAllNearByUsers(float lat , float lng) {
return null;
    }

    public AppUser getUserById(String userId) {
        FirebaseDatabase fbDb = FirebaseDatabase.getInstance();
        DatabaseReference rootRef = fbDb.getReference();
        final ArrayList<AppUser> allUsers = new ArrayList<AppUser>();
        rootRef.child(PROFILES).child(userId).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            callbackResponseListener.onCallbackSuccessfulResponse(dataSnapshot,"","");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
           // callbackResponseListener.onCallbackFailureResponse();
            }
        });

        return  null;
    }


    public interface CallbackResponseListener {

        /**
         * Called when the API returned a valid response.
         *
         * @param basicResponse The response from the server.
         * @param tag           The API tag.
         * @param extraData     Extra data which might be available.
         */
        void onCallbackSuccessfulResponse(DataSnapshot basicResponse, String tag, String extraData);

        /**
         * Called when the API returned a failure response.
         *
         * @param errorMessageResource The failure message resource.
         * @param errorCode            The error code.
         * @param tag                  The API tag.
         */
        void onCallbackFailureResponse( int errorMessageResource, int errorCode, String tag);
    }

}
