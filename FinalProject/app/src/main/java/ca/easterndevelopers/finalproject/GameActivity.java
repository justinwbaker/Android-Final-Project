package ca.easterndevelopers.finalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import ca.easterndevelopers.finalproject.level.LevelManager;
import ca.easterndevelopers.finalproject.renderer.GameRenderer;

public class GameActivity extends Activity {

    private GameRenderer renderer;

    private static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        System.out.println(MainScreen.getMissionSelected());

        switch(MainScreen.getMissionSelected()){
            case 1:
                LevelManager.loadLevel(GameActivity.getContext(), R.drawable.level1_small, R.drawable.level1_small_go);
                break;
            case 2:
                LevelManager.loadLevel(GameActivity.getContext(), R.drawable.level2, R.drawable.level1_small_go);
                break;
            case 3:
                LevelManager.loadLevel(GameActivity.getContext(), R.drawable.level3, R.drawable.level1_small_go);
                break;
            default:
                LevelManager.loadLevel(GameActivity.getContext(), R.drawable.level1, R.drawable.level1_small_go);
                break;
        }

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

    public static void changeIntent(Intent intent){
        getContext().startActivity(intent);
    }
}
