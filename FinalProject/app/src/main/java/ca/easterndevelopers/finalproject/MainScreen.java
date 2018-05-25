package ca.easterndevelopers.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ca.easterndevelopers.finalproject.game.Game;
import ca.easterndevelopers.finalproject.level.LevelManager;
import ca.easterndevelopers.finalproject.level.gameobject.Soldier;
import ca.easterndevelopers.finalproject.player.Player;

public class MainScreen extends Activity implements View.OnClickListener{

    private static int missionSelect = 0; // will default start on mission one if you don't manually pick one
    private static Player player = new Player();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_screen);

        final Intent z = new Intent(this, MainActivity.class);

        final Button beginMission = findViewById(R.id.beginMission);
        final Button chooseMission = findViewById(R.id.chooseMission);
        final Button changeUnits = findViewById(R.id.changeUnits);
        final Button mission1 = findViewById(R.id.Mission1);
        final Button mission2 = findViewById(R.id.Mission2);
        final Button mission3 = findViewById(R.id.Mission3);
        final Button back = findViewById(R.id.Back);

        beginMission.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent z = new Intent(MainScreen.this, MainActivity.class);;

                switch(missionSelect){

                    case 1:
                        z = new Intent(MainScreen.this, GameActivity.class);
                        break;

                    case 2:
                        z = new Intent(MainScreen.this, GameActivity.class);
                        break;

                    case 3:
                        z = new Intent(MainScreen.this, GameActivity.class);
                        break;
                }


                startActivity(z);
                finish();
            }

        });

        chooseMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mission1.setVisibility(View.VISIBLE);
                mission2.setVisibility(View.VISIBLE);
                mission3.setVisibility(View.VISIBLE);
                back.setVisibility(View.VISIBLE);
                beginMission.setVisibility(View.INVISIBLE);
                chooseMission.setVisibility(View.INVISIBLE);
                changeUnits.setVisibility(View.INVISIBLE);

            }
        });

        changeUnits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // insert link to change units page/buttons

                back.setVisibility(View.VISIBLE);
                beginMission.setVisibility(View.INVISIBLE);
                chooseMission.setVisibility(View.INVISIBLE);
                changeUnits.setVisibility(View.INVISIBLE);

            }
        });
        // end of first screen buttons

        mission1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                missionSelect = 1;

                mission1.setVisibility(View.INVISIBLE);
                mission2.setVisibility(View.INVISIBLE);
                mission3.setVisibility(View.INVISIBLE);
                back.setVisibility(View.INVISIBLE);
                beginMission.setVisibility(View.VISIBLE);
                chooseMission.setVisibility(View.VISIBLE);
                changeUnits.setVisibility(View.VISIBLE);


            }
        });

        mission2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                missionSelect = 2;

                mission1.setVisibility(View.INVISIBLE);
                mission2.setVisibility(View.INVISIBLE);
                mission3.setVisibility(View.INVISIBLE);
                back.setVisibility(View.INVISIBLE);
                beginMission.setVisibility(View.VISIBLE);
                chooseMission.setVisibility(View.VISIBLE);
                changeUnits.setVisibility(View.VISIBLE);
            }
        });

        mission3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                missionSelect = 3;

                mission1.setVisibility(View.INVISIBLE);
                mission2.setVisibility(View.INVISIBLE);
                mission3.setVisibility(View.INVISIBLE);
                back.setVisibility(View.INVISIBLE);
                beginMission.setVisibility(View.VISIBLE);
                chooseMission.setVisibility(View.VISIBLE);
                changeUnits.setVisibility(View.VISIBLE);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // return to main menu without doing anything
                mission1.setVisibility(View.INVISIBLE);
                mission2.setVisibility(View.INVISIBLE);
                mission3.setVisibility(View.INVISIBLE);
                back.setVisibility(View.INVISIBLE);
                beginMission.setVisibility(View.VISIBLE);
                chooseMission.setVisibility(View.VISIBLE);
                changeUnits.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent z = new Intent(this, MainActivity.class);;

        switch(missionSelect){

            case 1:
                z = new Intent(this, GameActivity.class);
                break;

            case 2:
                z = new Intent(this, GameActivity.class);
                break;

            case 3:
                z = new Intent(this, GameActivity.class);
                break;
        }


        startActivity(z);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public static Player getPlayer() {
        return player;
    }

    public static void endTurn(){
        getPlayer().endTurn();
        LevelManager.getLevel(0).getEnemy().startTurn();

    }

    public static int getMissionSelected() {
        return missionSelect;
    }
}
