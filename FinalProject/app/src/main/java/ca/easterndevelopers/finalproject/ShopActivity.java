package ca.easterndevelopers.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ca.easterndevelopers.finalproject.level.gameobject.Collider;

public class ShopActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        final Intent mainIntent = new Intent(this, MainScreen.class);

        Button backButton = (Button) findViewById(R.id.backButton);
        final Button soldier = (Button) findViewById(R.id.buySoldier);
        final Button sniper = (Button) findViewById(R.id.buySniper);
        final Button tank = (Button) findViewById(R.id.buyTank);


        final TextView soldiers = (TextView) findViewById(R.id.soldiers);
        final TextView snipers = (TextView) findViewById(R.id.snipers);
        final TextView tanks = (TextView) findViewById(R.id.tanks);
        final TextView gold = (TextView) findViewById(R.id.gold);

        soldiers.setText("soldiers: " + MainScreen.getNumSoldiers());
        snipers.setText("snipers: " + MainScreen.getNumSnipers());
        tanks.setText("tanks: " + MainScreen.getNumTanks());
        gold.setText("Gold: " + MainScreen.getGold());


        checkGold(soldier, sniper, tank);

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(mainIntent);
                finish();
            }
        });

        soldier.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!MainScreen.buySolder()){
                    soldier.setBackgroundColor(Color.rgb(255, 20, 20));
                }
                soldiers.setText("soldiers: " + MainScreen.getNumSoldiers());
                gold.setText("Gold: " + MainScreen.getGold());
                checkGold(soldier, sniper, tank);
            }
        });

        sniper.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!MainScreen.buySniper()){
                    sniper.setBackgroundColor(Color.rgb(255, 20, 20));
                }
                snipers.setText("snipers: " + MainScreen.getNumSnipers());
                gold.setText("Gold: " + MainScreen.getGold());
                checkGold(soldier, sniper, tank);
            }
        });

        tank.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!MainScreen.buyTank()){
                    tank.setBackgroundColor(Color.rgb(255, 20, 20));
                }
                tanks.setText("tanks: " + MainScreen.getNumTanks());
                gold.setText("Gold: " + MainScreen.getGold());
                checkGold(soldier, sniper, tank);
            }
        });

    }

    public void checkGold(Button soldier, Button sniper, Button tank) {
        if(MainScreen.getGold() < MainScreen.costSoldier) {
            soldier.setBackgroundColor(Color.rgb(255, 20, 20));
        }

        if(MainScreen.getGold() < MainScreen.costSniper) {
            sniper.setBackgroundColor(Color.rgb(255, 20, 20));
        }

        if(MainScreen.getGold() < MainScreen.costTank) {
            tank.setBackgroundColor(Color.rgb(255, 20, 20));
        }
    }
}
