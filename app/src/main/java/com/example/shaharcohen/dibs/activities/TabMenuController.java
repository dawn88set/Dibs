package com.example.shaharcohen.dibs.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.shaharcohen.dibs.R;
import com.example.shaharcohen.dibs.fragments.UsersListFragment;
import com.example.shaharcohen.dibs.fragments.MessagesFragment;
import com.example.shaharcohen.dibs.fragments.MyProfileFragment;

import java.util.List;

public class TabMenuController extends AppCompatActivity {

    // This section can only list global properties, but not initialize them.
    // private AHBottomNavgiation bottomNavigation;
    private List<Fragment> fragments ;
   private AHBottomNavigation bottomNavigation ;
    private final int CHAT_FRAGMENT = 0;
    private final int MATCH_FRAGMENT = 1;
    private final int PROFILE_FRAGMENT = 2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //You are missing the layout.
        setContentView(R.layout.activity_tab_menu_controller);

        // Here you initalize stuff
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        final FragmentManager fragmentManager = getSupportFragmentManager();
// using FragmentManager to replace the fragments in the tab view
        fragmentManager
                .beginTransaction()
                .replace(R.id.user_fragment_container, new UsersListFragment()) //what is this?
                .commit();

        // Create items
        AHBottomNavigationItem messagesItem = new AHBottomNavigationItem(R.string.chat, R.drawable.chat, R.color.black_overlay);
        AHBottomNavigationItem peopleItem = new AHBottomNavigationItem(R.string.connections, R.drawable.people, R.color.black_overlay);
        AHBottomNavigationItem profileItem = new AHBottomNavigationItem(R.string.profile, R.drawable.userprofile, R.color.black_overlay);

// Add items
        bottomNavigation.addItem(messagesItem);
        bottomNavigation.addItem(peopleItem);
        bottomNavigation.addItem(profileItem);

// Set background color
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#EEEEEE"));

// Disable the translation inside the CoordinatorLayout
        bottomNavigation.setBehaviorTranslationEnabled(false);

// Enable the translation of the FloatingActionButton
        //bottomNavigation.manageFloatingActionButtonBehavior(floatingActionButton);

// Change colors
        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));

// Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);

// Display color under navigation bar (API 21+)
// Don't forget these lines in your style-v21
// <item name="android:windowTranslucentNavigation">true</item>
// <item name="android:fitsSystemWindows">true</item>
        bottomNavigation.setTranslucentNavigationEnabled(true);

// Manage titles
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);

// Use colored navigation with circle reveal effect
        bottomNavigation.setColored(true);

// Set current item programmatically
        bottomNavigation.setCurrentItem(1);

// Customize notification (title, background, typeface)
      //  bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#EEEEEE"));

// Add or remove notification for each item set the round red notification for this tab
        //bottomNavigation.setNotification("1", 2);
        // OR
//        AHNotification notification = new AHNotification.Builder()
//                .setText("1")
//                .setBackgroundColor(ContextCompat.getColor(TabMenuController.this, R.color.colorPrimary))
//                .setTextColor(ContextCompat.getColor(TabMenuController.this, R.color.colorPrimary))
//                .build();
       // bottomNavigation.setNotification(notification, 1);

// Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
                                                      @Override
                                                      public boolean onTabSelected(int position, boolean wasSelected) {
                                                          // Do something cool here...
                                                          // Which bottom bar was clicked.
                                                          // take care of if the tab has been selected before for not creating a new fragment
                                                          switch (position) {
                                                              case CHAT_FRAGMENT:
                                                                      // Replace the current fragment with the ProfileUserSelfFragment fragment.
                                                                      fragmentManager.beginTransaction()
                                                                              .replace(R.id.user_fragment_container, MessagesFragment.newInstance())
                                                                              .commit();
                                                                  break;
                                                              case MATCH_FRAGMENT:
                                                                  fragmentManager.beginTransaction()
                                                                          .replace(R.id.user_fragment_container, UsersListFragment.newInstance(0))
                                                                          .commit();
                                                                 break;
                                                              case PROFILE_FRAGMENT:
                                                                  fragmentManager.beginTransaction()
                                                                          .replace(R.id.user_fragment_container, new MyProfileFragment())
                                                                          .commit();
                                                                  break;
                                                          }

                                                          return true;
                                                      }
                                                  }

        );
        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener()

                                                         {
                                                             @Override
                                                             public void onPositionChange(int y) {
                                                                 // Manage the new y position
                                                             }
                                                         }

        );

    }


}
