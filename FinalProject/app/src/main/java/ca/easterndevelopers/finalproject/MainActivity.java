package ca.easterndevelopers.finalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private static Point resolution;
    //for tile size calculations
    private static float zoom = 1f;
    private static int tilesInWidth = 20;
    public static boolean friendlyFire = true;

    public static SharedPreferences localPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // loading data
        localPrefs = this.getSharedPreferences("ca.easterndevelopers.finalproject", Context.MODE_PRIVATE);

        /* test saving and loading data
        load--> String data = localPrefs.getString("name", "unknown");
        System.out.println(data);
        save--> localPrefs.edit().putString("name", "justin").apply();
        */

        // display size and stuff
        Display display = getWindowManager().getDefaultDisplay();
        // Load the resolution into a Point object
        resolution = new Point();
        display.getSize(resolution);
        setContentView(R.layout.activity_main);

        final Button buttonPlay = (Button)findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, MainScreen.class);

        startActivity(i);
        finish();
    }

    public static Point getResolution() {
        return resolution;
    }

    //calculates actual size of tiles based on resolution
    public static float getTileSize() {
        return (resolution.x / tilesInWidth) * zoom;
    }

    public static SharedPreferences getLocalPrefs() {
        return localPrefs;
    }
}
