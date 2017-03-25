package com.example.shaharcohen.dibs.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shaharcohen.dibs.Entity.AppUser;
import com.example.shaharcohen.dibs.R;
import com.example.shaharcohen.dibs.view_holders.AppUserViewHolder;

import java.util.ArrayList;

/**
 * Created by shaharcohen on 3/20/17.
 */

public class AppUserAdapter extends RecyclerView.Adapter<AppUserViewHolder> {

    private ArrayList<AppUser> appUsers;



    @Override
    public AppUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a LayoutInflater.
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {

            case 0:

                break;

            case 1:

                break;

            default:

                break;
        }
        // Inflate layout.

        // cell layout
        View view = inflater.inflate(R.layout., parent, false);

        return new AppUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AppUserViewHolder holder, int position) {
        holder.bindView(appUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return appUsers.size();
    }

    @Override
    public int getItemViewType(int position) {

        switch (appUsers.get(position).getGender()) {

            case "":

                return 0;

            case " ":

                return 1;

            default:

                return -1;
        }
    }

    public void swapArray(ArrayList<AppUser> appUsers) {
        this.appUsers = appUsers;

        notifyDataSetChanged();
    }
}
