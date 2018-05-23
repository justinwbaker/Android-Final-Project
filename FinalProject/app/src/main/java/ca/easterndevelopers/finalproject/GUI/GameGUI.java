package ca.easterndevelopers.finalproject.GUI;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import ca.easterndevelopers.finalproject.MainActivity;
import ca.easterndevelopers.finalproject.MainScreen;
import ca.easterndevelopers.finalproject.game.Game;
import ca.easterndevelopers.finalproject.renderer.GameRenderer;

public class GameGUI extends GUI {

    private Rect viewMap;
    private Rect nextUnit;
    private Rect endTurn;

    public GameGUI() {
        viewMap = new Rect((int)MainActivity.getTileSize(), (int)MainActivity.getTileSize(), (int)(MainActivity.getTileSize()*2.5f), (int)(MainActivity.getTileSize()*2.5f));
        nextUnit = new Rect((int)MainActivity.getResolution().x - (int)MainActivity.getTileSize()*4, MainActivity.getResolution().y - (int)(MainActivity.getTileSize()*2.5f), (int)MainActivity.getResolution().x - (int)MainActivity.getTileSize()*2, MainActivity.getResolution().y - (int)MainActivity.getTileSize());
        endTurn = new Rect((int)MainActivity.getTileSize(), MainActivity.getResolution().y - (int)(MainActivity.getTileSize()*2.5f), (int)MainActivity.getTileSize()*3, MainActivity.getResolution().y - (int)MainActivity.getTileSize());
    }

    @Override
    public void update() {
        isOnGUI = false;
        if(GameRenderer.getTouchedPoint() != null) {
            int x = GameRenderer.getTouchedPoint().x;
            int y = GameRenderer.getTouchedPoint().y;

            if(Rect.intersects(viewMap, new Rect(x, y, x+1, y+1))){
                isOnGUI = true;
                Game.setIsLookingAtMap(!Game.isLookingAtMap());
            }

            if(Rect.intersects(nextUnit, new Rect(x, y, x+1, y+1))){
                isOnGUI = true;
                MainScreen.getPlayer().setNextActiveUnit();
                // select next unit
            }

            if(Rect.intersects(endTurn, new Rect(x, y, x+1, y+1))){
                isOnGUI = true;
                // go to enemy's turn
            }
        }
    }

    @Override
    public void render(Canvas canvas, Paint paint) {
        canvas.restore();
        paint.setColor(Color.argb(135, 0, 0, 255));
        canvas.drawRect(viewMap, paint);
        canvas.drawRect(nextUnit, paint);
        canvas.drawRect(endTurn, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        canvas.drawText("  MAP ", (int)MainActivity.getTileSize() + (int)MainActivity.getTileSize()/8, (int)MainActivity.getTileSize()*1.7f, paint);
        canvas.drawText(" NEXT ", nextUnit.left + MainActivity.getTileSize()/2, nextUnit.bottom - MainActivity.getTileSize()/2, paint);
        canvas.drawText("  END ", endTurn.left + MainActivity.getTileSize()/2, endTurn.bottom - MainActivity.getTileSize()/2, paint);
    }

    // esc button will deselect unit if unit is selected, else will open up main menu
}
