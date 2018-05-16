package ca.easterndevelopers.finalproject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.Button;

import ca.easterndevelopers.finalproject.renderer.GameRenderer;

public class GameActivity extends Activity {

    private GameRenderer renderer;
    private static Point resolution;

    private static Context context;

    //for tile size calculations
    private static float zoom = 1.0f;
    private static int tilesInWidth = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        Display display = getWindowManager().getDefaultDisplay();
        // Load the resolution into a Point object
        resolution = new Point();
        display.getSize(resolution);

        renderer = new GameRenderer(this);
        setContentView(renderer);
    }

    @Override
    protected void onPause() {
        super.onPause();
        renderer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        renderer.resume();
    }

    public static Point getResolution() {
        return resolution;
    }

    //calculates actual size of tiles based on resolution
    public static float getTileSize() {
        return (resolution.x / tilesInWidth) * zoom;
    }

    public static Context getContext() {
        return context;
    }
}
