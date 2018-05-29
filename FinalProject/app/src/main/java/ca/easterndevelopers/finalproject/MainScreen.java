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

public class MainScreen extends Activity{


    public static final int costSoldier = 100;
    public static final int costSniper = 100;
    public static final int costTank = 100;

    private static int missionSelect = 0; // will default start on mission one if you don't manually pick one
    private static Player player = new Player();

    private static int numSoldiers = 5;
    private static int numSnipers = 2;
    private static int numTanks = 2;
    private static int gold = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         final Intent levelIntent = new Intent(this, LevelSelect.class);
         final Intent shopIntent = new Intent(this, ShopActivity.class);
         final Intent gameIntent = new Intent(this, GameActivity.class);

        setContentView(R.layout.activity_main_screen);

        numSoldiers = MainActivity.localPrefs.getInt("numSoldiers", 5);
        numSnipers = MainActivity.localPrefs.getInt("numSnipers", 2);
        numTanks = MainActivity.localPrefs.getInt("numTanks", 2);
        gold = MainActivity.localPrefs.getInt("gold", 1000);

        missionSelect = MainActivity.localPrefs.getInt("mission", -1);

        if(missionSelect != -1) {
            startActivity(gameIntent);
            finish();
        }

        Button levelButton = (Button) findViewById(R.id.leveSelectButton);
        Button shopButton = (Button) findViewById(R.id.shopButton);

        levelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(levelIntent);
                finish();
            }
        });

        shopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(shopIntent);
                finish();
            }
        });
    }

    public static int getLevel(){
        return missionSelect;
    }

    public static void setLevel(int level){
        missionSelect = level;

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

    public static boolean buySolder() {
        if(gold >= costSoldier) {
            gold -= costSoldier;
            numSoldiers++;
            MainActivity.localPrefs.edit().putInt("gold", gold).apply();
            MainActivity.localPrefs.edit().putInt("numSoldiers", numSoldiers).apply();
        }
        return gold >= costSoldier;
    }

    public static boolean buySniper() {
        if(gold >= costSniper) {
            gold -= costSniper;
            numSnipers++;
            MainActivity.localPrefs.edit().putInt("gold", gold).apply();
            MainActivity.localPrefs.edit().putInt("numSnipers", numSnipers).apply();
        }
        return gold >= costSniper;
    }

    public static boolean buyTank() {
        if(gold >= costTank) {
            gold -= costTank;
            numTanks++;
            MainActivity.localPrefs.edit().putInt("gold", gold).apply();
            MainActivity.localPrefs.edit().putInt("numTanks", numTanks).apply();
        }
        return gold >= costTank;
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
