package Elements;

import Processing.Manager;

import java.util.List;

/**
 * Created by dongdong on 2016-02-19.
 */
public class RectType2  extends TetrisBlock {

    private static final int RED = 200;
    private static final int GREEN = 50;
    private static final int BLUE = 100;

    @Override
    public void setRects(int x, int y) {
        type.get(0).setPos(x, y);
        type.get(1).setPos( type.get(0).getX(),  type.get(0).getY()+Rect.getBlockSize());
        type.get(2).setPos( type.get(1).getX()+Rect.getBlockSize(),  type.get(1).getY());
        type.get(3).setPos( type.get(0).getX()+Rect.getBlockSize(),  type.get(0).getY());

    }

    @Override
    public void blockDraw(List<Rect> rect) {
        Manager.GAME_MANAGER.fill(RED,GREEN,BLUE,Rect.getColorAlpha());
        super.blockDraw(this.type);
    }

    @Override
    public void rotate() {


    }

    @Override
    public void setTestBlockPos() {

    }

    @Override
    public void setPos_preview(int x, int y) {
        preViews.get(0).setPos(x + 5, y);
        preViews.get(1).setPos( preViews.get(0).getX(),  preViews.get(0).getY()+Rect.getPreviewBlockSize());
        preViews.get(2).setPos( preViews.get(1).getX()+Rect.getPreviewBlockSize(),  preViews.get(1).getY());
        preViews.get(3).setPos( preViews.get(0).getX()+Rect.getPreviewBlockSize(),  preViews.get(0).getY());
    }

    @Override
    public void preViewDraw(List<Rect> rect) {
        Manager.GAME_MANAGER.fill(RED,GREEN,BLUE,Rect.getColorPreviewAlpha());
        super.preViewDraw(rect);
    }

    @Override
    public void downViewDraw(List<Rect> rect)
    {
        Manager.GAME_MANAGER.fill(RED,GREEN,BLUE,Rect.getColorDownviewAlpha());
        super.downViewDraw(rect);
    }

}
