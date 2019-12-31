package com.example.instant;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.location.Geocoder;

import java.util.Locale;

public class FetchAddressIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public FetchAddressIntentService(String name) {
        super(name);
    }

    protected void onHandleIntent(Intent intent) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        // ...
    }
}
