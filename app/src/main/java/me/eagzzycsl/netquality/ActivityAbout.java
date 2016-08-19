package me.eagzzycsl.netquality;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

public class ActivityAbout extends ActivityToolbar implements View.OnClickListener {
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab: {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(getString(R.string.url_official_website)));
                startActivity(browserIntent);
                break;
            }
        }
    }
}
