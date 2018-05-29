package ca.easterndevelopers.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.view.View;
import android.widget.Button;

public class LevelSelect extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select);

        final Intent mainIntent = new Intent(this, MainScreen.class);
        final Intent gameIntent = new Intent(this, GameActivity.class);

        updateBackground(findViewById(R.id.bg));

        Button backButton = (Button) findViewById(R.id.backButton);
        final Button level1 = (Button) findViewById(R.id.level1);
        final Button level2 = (Button) findViewById(R.id.level2);
        final Button level3 = (Button) findViewById(R.id.level3);

        final Button play = (Button) findViewById(R.id.buttonPlay);

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(mainIntent);
                finish();
            }
        });

        level1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainScreen.setLevel(1);
                updateBackground(findViewById(R.id.bg));
            }
        });

        level2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainScreen.setLevel(2);
                updateBackground(findViewById(R.id.bg));
            }
        });

        level3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainScreen.setLevel(3);
                updateBackground(findViewById(R.id.bg));
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainScreen.getLevel() != -1 && MainScreen.getLevel() != 0) {
                    startActivity(gameIntent);
                    finish();
                }
            }
        });
    }

    public void updateBackground(View view) {
        switch (MainScreen.getLevel()) {
            case 1:
                view.setBackgroundResource(R.drawable.level_select_level01);
                break;
            case 2:
                view.setBackgroundResource(R.drawable.level_select_level02);
                break;
            case 3:
                view.setBackgroundResource(R.drawable.level_select_level03);
                break;
            default:
                view.setBackgroundResource(R.drawable.level_select);
                break;
        }
    }
}
