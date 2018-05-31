package ca.easterndevelopers.finalproject.GUI;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import ca.easterndevelopers.finalproject.GameActivity;
import ca.easterndevelopers.finalproject.MainActivity;
import ca.easterndevelopers.finalproject.MainScreen;
import ca.easterndevelopers.finalproject.R;
import ca.easterndevelopers.finalproject.game.Game;
import ca.easterndevelopers.finalproject.level.LevelManager;
import ca.easterndevelopers.finalproject.renderer.GameRenderer;
import ca.easterndevelopers.finalproject.utils.Utils;

public class GameGUI extends GUI {

    private Rect viewMap;
    private Rect nextUnit;
    private Rect endTurn;

    private Bitmap map;
    private Bitmap next;
    private Bitmap end;

    private double fps;

    public GameGUI() {
        map = Utils.loadBitmap(GameActivity.getContext(), R.drawable.map_button);
        map = Utils.getResizedBitmap(map, (int)MainActivity.getTileSize()*2, (int)MainActivity.getTileSize(), true);
        next = Utils.loadBitmap(GameActivity.getContext(), R.drawable.next_button);
        next = Utils.getResizedBitmap(next, (int)MainActivity.getTileSize()*2, (int)MainActivity.getTileSize(), true);
        end = Utils.loadBitmap(GameActivity.getContext(), R.drawable.end_button);
        end = Utils.getResizedBitmap(end, (int)MainActivity.getTileSize()*2, (int)MainActivity.getTileSize(), true);

        viewMap = new Rect((int)MainActivity.getTileSize(), (int)MainActivity.getTileSize(), (int)(MainActivity.getTileSize()*3), (int)(MainActivity.getTileSize()*2));
        nextUnit = new Rect((int)MainActivity.getResolution().x - (int)MainActivity.getTileSize()*3, MainActivity.getResolution().y - (int)(MainActivity.getTileSize()*2), (int)MainActivity.getResolution().x - (int)MainActivity.getTileSize(), MainActivity.getResolution().y - (int)MainActivity.getTileSize());
        endTurn = new Rect((int)MainActivity.getTileSize(), MainActivity.getResolution().y - (int)(MainActivity.getTileSize()*2), (int)MainActivity.getTileSize()*3, MainActivity.getResolution().y - (int)MainActivity.getTileSize());
    }

    @Override
    public void update(double fps) {
        isOnGUI = false;
        this.fps = fps;
        if(GameRenderer.getTouchedPoint() != null) {
            int x = GameRenderer.getTouchedPoint().x;
            int y = GameRenderer.getTouchedPoint().y;

            if(MainScreen.getPlayer().isPlayersTurn()){

                if(Rect.intersects(viewMap, new Rect(x, y, x+1, y+1))){
                    isOnGUI = true;
                    Game.setIsLookingAtMap(!Game.isLookingAtMap());
                }

                if(Rect.intersects(nextUnit, new Rect(x, y, x+1, y+1))){
                    isOnGUI = true;
                    MainScreen.getPlayer().setNextActiveUnit();
                    // select next unit
                }

                if(Rect.intersects(endTurn, new Rect(x, y, x+1, y+1))) {
                    isOnGUI = true;
                    MainScreen.getPlayer().getCurrentLevel().endPlayersTurn();

                }
                // go to enemy's turn
            }
        }
    }

    @Override
    public void render(Canvas canvas, Paint paint) {
        canvas.restore();
        paint.setColor(Color.argb(255, 255, 255, 255));
        canvas.drawBitmap(map, viewMap.left, viewMap.top, paint);
        if(Game.debug) canvas.drawRect(viewMap, paint);

        if(MainScreen.getPlayer().isPlayersTurn()) {
            canvas.drawBitmap(next, nextUnit.left, nextUnit.top, paint);
            if(Game.debug) canvas.drawRect(nextUnit, paint);
            canvas.drawRect(endTurn, paint);
            canvas.drawBitmap(end, endTurn.left, endTurn.top, paint);
            if(Game.debug) canvas.drawRect(endTurn, paint);
        }

        if(Game.debug)
            canvas.drawText("" + fps, MainActivity.getResolution().x/2 , MainActivity.getTileSize(), paint);
    }

    // esc button will deselect unit if unit is selected, else will open up main menu
}
