package com.example.shaharcohen.dibs.view_holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.example.shaharcohen.dibs.Entity.AppUser;
import com.example.shaharcohen.dibs.R;

/**
 * Created by shaharcohen on 3/20/17.
 */

public class AppUserViewHolder extends RecyclerView.ViewHolder {

    private EditText editTextGonny;

    public AppUserViewHolder(View itemView) {
        super(itemView);

         editTextGonny = (EditText) itemView.findViewById(R.id.edit_text);


    }

    public void bindView(AppUser user){
        editTextGonny.setText(user.getAbout_me());
    }
}
