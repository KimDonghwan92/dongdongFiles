package Elements; /**
 * Created by dongdong on 2016-02-19.
 */

import Processing.Manager;

public class Rect implements Cloneable{

    private static final int DEFAULT_STROKE_COLOR = 0;
    private static final int COLOR_ALPHA = 255;
    private static final int COLOR_PREVIEW_ALPHA = 210;
    private static final int COLOR_DOWNVIEW_ALPHA = 80;
    private static final int BLOCK_SIZE = 30;
    private static final int PREVIEW_BLOCK_SIZE = 10;

    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public static int getBlockSize() {
        return BLOCK_SIZE;
    }

    public static int getPreviewBlockSize() {
        return PREVIEW_BLOCK_SIZE;
    }

    public static int getColorAlpha() {
        return COLOR_ALPHA;
    }

    public static int getColorPreviewAlpha() {
        return COLOR_PREVIEW_ALPHA;
    }

    public static int getColorDownviewAlpha() {
        return COLOR_DOWNVIEW_ALPHA;
    }

    public void setPos(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public void draw()
    {
        Manager.GAME_MANAGER.stroke(DEFAULT_STROKE_COLOR);
        Manager.GAME_MANAGER.rect(x,y,BLOCK_SIZE,BLOCK_SIZE);
    }
    public void preDraw()
    {
        Manager.GAME_MANAGER.stroke(DEFAULT_STROKE_COLOR, COLOR_PREVIEW_ALPHA);
        Manager.GAME_MANAGER.rect(x,y,PREVIEW_BLOCK_SIZE,PREVIEW_BLOCK_SIZE);
    }
    public void downDraw()
    {
        Manager.GAME_MANAGER.stroke(DEFAULT_STROKE_COLOR, COLOR_PREVIEW_ALPHA);
        Manager.GAME_MANAGER.rect(x,y,BLOCK_SIZE,BLOCK_SIZE);
    }

    public Rect clone() throws CloneNotSupportedException
    {
        return (Rect)super.clone();
    }

}
