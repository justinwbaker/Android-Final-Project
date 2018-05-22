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

    public GameGUI() {
        viewMap = new Rect((int)MainActivity.getTileSize(), (int)MainActivity.getTileSize(), (int)MainActivity.getTileSize()*2, (int)MainActivity.getTileSize()*2);
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
        }
    }

    @Override
    public void render(Canvas canvas, Paint paint) {
        canvas.restore();
        paint.setColor(Color.BLUE);
        canvas.drawRect(viewMap, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        canvas.drawText("Map", (int)MainActivity.getTileSize() + (int)MainActivity.getTileSize()/8, (int)MainActivity.getTileSize()*1.7f, paint);
    }

    // esc button will deselect unit if unit is selected, else will open up main menu
}
