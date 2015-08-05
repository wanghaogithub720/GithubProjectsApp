package com.restaurant.tracking.githubprojectsapp.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.restaurant.tracking.githubprojectsapp.R;
import com.restaurant.tracking.githubprojectsapp.eventbus.events.Event.PostedModelEvent;
import com.restaurant.tracking.githubprojectsapp.eventbus.models.EventPageInfo;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by djzhang on 8/5/15.
 */
public class PageOneActivity extends TaskActivity {

    private TextView textView3;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_pageone);

        this.textView3 = (TextView) findViewById(R.id.textView3);
        this.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPageTwo();
            }
        });
    }

    private void showPageTwo() {
        Intent intent = new Intent(this, PageTwoActivity.class);
        this.startActivity(intent);
        EventBus.getDefault().post(new PostedModelEvent(new EventPageInfo("post", " to djzhang")));
    }

    @Subscribe
    public void onEventMainThread(PostedModelEvent ignored) {
        //we could just add this to top or replace element instead of refreshing whole list
        EventPageInfo model = ignored.getModel();
        this.textView3.setText(model.displayName + "" + model.content);
    }

}
