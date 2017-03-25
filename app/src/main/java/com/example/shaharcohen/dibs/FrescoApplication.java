package com.example.shaharcohen.dibs;

/**
 * Created by shaharcohen on 3/16/17.
 */

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class FrescoApplication  extends Application {

        @Override
        public void onCreate() {
            super.onCreate();
            Fresco.initialize(this);
        }

}
