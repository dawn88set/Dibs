package com.example.shaharcohen.dibs.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shaharcohen.dibs.Entity.AppUser;
import com.example.shaharcohen.dibs.R;
import com.example.shaharcohen.dibs.adapters.AppUserAdapter;
import com.example.shaharcohen.dibs.services.FirebaseServices;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;


public class UsersListFragment extends Fragment implements FirebaseServices.CallbackResponseListener {


    private AppUserAdapter appUserAdapter ;
    private int userId;

    public UsersListFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static UsersListFragment newInstance(int userId) {
        UsersListFragment fragment = new UsersListFragment();
        Bundle args = new Bundle();

        args.putInt("userId",userId);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!= null){
            userId = getArguments().getInt("userId");
            getUsersFromFireBase(userId);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users_list, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        // list
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        // 4 means how many columns will be in the grid view
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        // connection the layout manager to rhe recycleview
        recyclerView.setLayoutManager(gridLayoutManager);
        // set a divider between cells
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        appUserAdapter = new AppUserAdapter();
        recyclerView.setAdapter(appUserAdapter);

        return  view;
    }

    private void getUsersFromFireBase(int userId){
        FirebaseServices firebaseServices = new FirebaseServices(this);

       ArrayList<AppUser> appUsers = firebaseServices.getAllNearByUsers(0,0);
        //fire base aysnc method block before adding

    }


    @Override
    public void onCallbackSuccessfulResponse(DataSnapshot response, String tag, String extraData) {



        appUserAdapter.swapArray(appUsers);

    }

    @Override
    public void onCallbackFailureResponse(int errorMessageResource, int errorCode, String tag) {

    }
}
