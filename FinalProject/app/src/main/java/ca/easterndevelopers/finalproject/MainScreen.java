package ca.easterndevelopers.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ca.easterndevelopers.finalproject.game.Game;
import ca.easterndevelopers.finalproject.level.LevelManager;
import ca.easterndevelopers.finalproject.level.gameobject.Soldier;
import ca.easterndevelopers.finalproject.player.Player;

public class MainScreen extends Activity implements View.OnClickListener{

    private static int missionSelect = 0; // will default start on mission one if you don't manually pick one
    private static Player player = new Player();

    private static int numSoldiers = 5;
    private static int numSnipers = 2;
    private static int numTanks = 2;
    private static int costSoldier = 100;
    private static int costSniper = 100;
    private static int costTank = 100;
    private static int gold = 1000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_screen);

        final Intent z = new Intent(this, MainActivity.class);

        numSoldiers = MainActivity.localPrefs.getInt("numSoldiers", 5);
        numSnipers = MainActivity.localPrefs.getInt("numSnipers", 2);
        numTanks = MainActivity.localPrefs.getInt("numTanks", 2);
        gold = MainActivity.localPrefs.getInt("gold", 1000);


        final Button beginMission = findViewById(R.id.beginMission);
        final Button chooseMission = findViewById(R.id.chooseMission);
        final Button changeUnits = findViewById(R.id.changeUnits);
        final Button mission1 = findViewById(R.id.Mission1);
        final Button mission2 = findViewById(R.id.Mission2);
        final Button mission3 = findViewById(R.id.Mission3);
        final Button back = findViewById(R.id.Back);
        final Button addSniper = findViewById(R.id.addSniper);
        final Button addTank = findViewById(R.id.addTank);
        final Button addSoldier = findViewById(R.id.addSoldier);

        final TextView Soldiers = (TextView)findViewById(R.id.armySoldiers);
        final TextView Snipers = (TextView)findViewById(R.id.armySnipers);
        final TextView Tanks = (TextView)findViewById(R.id.armyTanks);
        final TextView Gold = (TextView)findViewById(R.id.Gold);
        final TextView InsufficientGold = (TextView)findViewById(R.id.InsufficientGold);
        Soldiers.setText("Soldiers: " + numSoldiers);
        Snipers.setText("Snipers: " + numSnipers);
        Tanks.setText("Tanks: " + numTanks);
        Gold.setText("Gold: " + gold);



        final ImageView unitsPreview = (ImageView)findViewById(R.id.unitPreview);


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
                addSniper.setVisibility(View.VISIBLE);
                addSoldier.setVisibility(View.VISIBLE);
                addTank.setVisibility(View.VISIBLE);
                unitsPreview.setVisibility(View.VISIBLE);
                Soldiers.setVisibility(View.VISIBLE);
                Tanks.setVisibility(View.VISIBLE);
                Snipers.setVisibility(View.VISIBLE);
                Gold.setVisibility(View.VISIBLE);


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

        // end of choose mission buttons

        addSniper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numSnipers < 2 || Game.debug) {
                    if (checkGold(gold, costSniper)) {
                        minusGold(costSniper);
                        numSnipers++;
                        Snipers.setText("Snipers: " + numSnipers);
                        unitsPreview.setImageResource(R.drawable.soldier);
                        Gold.setText("Gold: " + gold);
                        MainActivity.localPrefs.edit().putInt("gold", gold).apply();
                        MainActivity.localPrefs.edit().putInt("numSnipers", numSnipers).apply();
                        if (InsufficientGold.getVisibility() == View.VISIBLE) {
                            InsufficientGold.setVisibility(View.INVISIBLE);
                        }
                    } else {
                        InsufficientGold.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        addSoldier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(numSoldiers < 6 || Game.debug) {
                    if (checkGold(gold, costSoldier)) {
                        minusGold(costSoldier);
                        numSoldiers++;
                        Soldiers.setText("Soldiers: " + numSoldiers);
                        unitsPreview.setImageResource(R.drawable.soldier);
                        Gold.setText("Gold: " + gold);
                        MainActivity.localPrefs.edit().putInt("gold", gold).apply();
                        MainActivity.localPrefs.edit().putInt("numSoldiers", numSoldiers).apply();
                        if (InsufficientGold.getVisibility() == View.VISIBLE) {
                            InsufficientGold.setVisibility(View.INVISIBLE);
                        }
                    } else {
                        InsufficientGold.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        addTank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(numTanks < 2 || Game.debug) {
                    if (checkGold(gold, costTank)) {
                        minusGold(costTank);
                        numTanks++;
                        Tanks.setText("Tanks: " + numTanks);
                        unitsPreview.setImageResource(R.drawable.soldier);
                        Gold.setText("Gold: " + gold);
                        MainActivity.localPrefs.edit().putInt("gold", gold).apply();
                        MainActivity.localPrefs.edit().putInt("numTanks", numTanks).apply();
                        if (InsufficientGold.getVisibility() == View.VISIBLE) {
                            InsufficientGold.setVisibility(View.INVISIBLE);
                        }
                    } else {
                        InsufficientGold.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        // end of add soldier buttons

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

                addSniper.setVisibility(View.INVISIBLE);
                addSoldier.setVisibility(View.INVISIBLE);
                addTank.setVisibility(View.INVISIBLE);
                unitsPreview.setVisibility(View.INVISIBLE);
                Soldiers.setVisibility(View.INVISIBLE);
                Tanks.setVisibility(View.INVISIBLE);
                Snipers.setVisibility(View.INVISIBLE);
                Gold.setVisibility(View.INVISIBLE);
                InsufficientGold.setVisibility(View.INVISIBLE);

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

    public static int getNumSoldiers() {
        return numSoldiers;
    }

    public static void setNumSoldiers(int numSoldiers) {
        MainScreen.numSoldiers -= numSoldiers;
    }

    public static int getNumSnipers() {
        return numSnipers;
    }

    public static void setNumSnipers(int numSnipers) {
        MainScreen.numSnipers -= numSnipers;
    }

    public static int getNumTanks() {
        return numTanks;
    }

    public static void setNumTanks(int numTanks) {
        MainScreen.numTanks -= numTanks;
    }

    public static int getGold() {
        return gold;
    }

    public static boolean checkGold(int gold, int cost) {

        if(cost <= gold){return true;}
        else {return false;}
    }

    public static void minusGold(int gold) {
        MainScreen.gold -= gold;

    }

    public static void addGold(int gold) {
        MainScreen.gold += gold;
    }
}
