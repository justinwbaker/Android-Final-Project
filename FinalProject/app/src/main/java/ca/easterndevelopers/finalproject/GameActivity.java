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

    private static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
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

    public static Context getContext() {
        return context;
    }
}
