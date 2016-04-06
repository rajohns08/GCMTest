package com.rajohns.gcmtest;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        InstanceID instanceID = InstanceID.getInstance(this);
        try {
            String gcmRegistrationToken = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            Log.i("tagzzz", "GCM Registration Token: " + gcmRegistrationToken);
            sendRegistrationTokenToServer(gcmRegistrationToken);
            subscribeTopics(gcmRegistrationToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendRegistrationTokenToServer(String gcmRegistrationToken) {

    }

    private void subscribeTopics(String gcmRegistrationToken) throws IOException {
        GcmPubSub pubSub = GcmPubSub.getInstance(this);
        pubSub.subscribe(gcmRegistrationToken, "/topics/global", null);
    }

}
