package com.iterable.inapp.inappmessaging;

/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

        import android.app.Activity;
        import android.app.AlertDialog;
        import android.app.Notification;
        import android.app.NotificationManager;
        import android.app.PendingIntent;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v4.app.NavUtils;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;
        import org.w3c.dom.Text;

        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.net.URLConnection;

/**
 * This sample demonstrates how to use system-provided, automatic layout transitions. Layout
 * transitions are animations that occur when views are added to, removed from, or changed within
 * a {@link ViewGroup}.
 *
 * <p>In this sample, the user can add rows to and remove rows from a vertical
 * {@link android.widget.LinearLayout}.</p>
 */
public class LayoutChangesActivity extends Activity {
    /**
     * The container view which has layout change animations turned on. In this sample, this view
     * is a {@link android.widget.LinearLayout}.
     */
    private ViewGroup mContainerView;

    private int notificationsQueued = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_changes);

        mContainerView = (ViewGroup) findViewById(R.id.layout_container);

        connectToServer();
        //addItem();

        // set onclick
        this.findViewById(R.id.add_notif_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("clicked add item");
                //addItem();
            }
        });
    }

    private void connectToServer()
    {
        String registrationUrl = "http://ilyas-mbp-2:9000/api/users/getInAppNotifications?email=david@iterable.com&api_key=iterableApiKey";
        String content = FullscreenActivity.connectToServer(registrationUrl);
        parseJson(content);
    }

    //Probably would separate out this data into a separate class
    public void parseJson(String strJson)
    {
        try {
            JSONObject jsonRootObject = new JSONObject(strJson);
            JSONArray jsonArray = jsonRootObject.optJSONArray("notifications");
            for (int i=0; i< jsonArray.length(); i++)
            {
                JSONObject jsonIndexContent = jsonArray.getJSONObject(i);
                String message = jsonIndexContent.get("message").toString();
                System.out.println("index: " + i + " = " + message);

                //store data locally or populate the notifications automatically
                addItem(message);
            }
        }
        catch (JSONException e) {e.printStackTrace();}

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.activity_layout_changes, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                // Navigate "up" the demo structure to the launchpad activity.
//                // See http://developer.android.com/design/patterns/navigation.html for more.
//                NavUtils.navigateUpTo(this, new Intent(this, FullscreenActivity.class));
//                return true;
//
//            case R.id.action_add_item:
//                // Hide the "empty" view since there is now at least one item in the list.
//                findViewById(android.R.id.empty).setVisibility(View.GONE);
//                addItem();
//                return true;
//        }

//        return super.onOptionsItemSelected(item);
//    }

    private void addItem(String messageText) {
        // Instantiate a new "row" view.
        final ViewGroup newView = (ViewGroup) LayoutInflater.from(this).inflate(
                R.layout.list_item_example, mContainerView, false);

        // Set the text in the new row to a random test notif string
        ((TextView) newView.findViewById(android.R.id.text1)).setText(messageText);
                //Test_Notifications[(int) (Math.random() * Test_Notifications.length)]);

                // Set a click listener for the "X" button in the row that will remove the row.
                newView.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mContainerView.removeView(newView);

                        String notifString = ((TextView) newView.findViewById(android.R.id.text1)).getText().toString();

                        queueNotification(notifString);

                        //TODO: bug need to fix
                        if (mContainerView.getChildCount() == 0) {
                            findViewById(android.R.id.empty).setVisibility(View.VISIBLE);
                        }
                    }
        });

        mContainerView.addView(newView, 0);
    }

    private void queueNotification(String notificationString)
    {
        Intent intent = new Intent(this, LayoutChangesActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        Notification n  = new Notification.Builder(this)
                .setContentTitle("Iterable Notification")
                .setContentText(notificationString)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .build(); //ignore api type for now

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(notificationsQueued++, n);
    }

//    private static final String[] Test_Notifications = new String[]{
//            "Notification1",
//            "Notification2",
//            "Notification3",
//            "Notification4",
//            "Notification5",
//            "Notification6",
//            "Notification7",
//            "Notification8",
//            "Notification9",
//            "Notification10",
//    };
}
