package co.miniforge.corey.mediatracker.ui_helpers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import co.miniforge.corey.mediatracker.MyListActivity;
import co.miniforge.corey.mediatracker.R;

/**
 * Created by Brent Taylor on 11/24/2017.
 */

public class SettingsActivity extends AppCompatActivity{
    
    //reference to themehelper
    ThemeHelper themeHelper;

    //button to toggle
    Button toggleButton;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);


        textView = (TextView) findViewById(R.id.textView);
        toggleButton = (Button) findViewById(R.id.toggle);
        themeHelper = new ThemeHelper(getApplicationContext());

        //reference id for variables
        themeHelper.themeTextView(textView);
        themeHelper.themeBackground(textView.getRootView());

        //set onclick listener to button
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Onclick set theme to dark
                themeHelper.enableDarkTheme(!themeHelper.darkThemeEnabled());

                themeHelper.themeBackground(view.getRootView());
                themeHelper.themeTextView(textView);


                //Create intent to myListActivity
                Intent intent = new Intent(view.getContext(), MyListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
