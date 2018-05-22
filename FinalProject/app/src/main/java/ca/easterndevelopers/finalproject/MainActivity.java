package ca.easterndevelopers.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;

import ca.easterndevelopers.finalproject.level.LevelManager;

public class MainActivity extends Activity implements View.OnClickListener {

    private static Point resolution;
    //for tile size calculations
    private static float zoom = 1.0f;
    private static int tilesInWidth = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}
