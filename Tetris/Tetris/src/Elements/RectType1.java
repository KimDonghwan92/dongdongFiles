package Elements;

import Processing.Manager;

import java.util.List;

/**
 * Created by dongdong on 2016-02-19.
 */

public class RectType1 extends TetrisBlock {

    private static final int RED = 50;
    private static final int GREEN = 100;
    private static final int BLUE = 200;

    private boolean rotateFlag = false;

    @Override
    public void setRects(int x, int y)
    {
        type.get(0).setPos(x, y);
        type.get(1).setPos(type.get(0).getX(), type.get(0).getY()+Rect.getBlockSize());
        type.get(2).setPos(type.get(1).getX(), type.get(1).getY()+Rect.getBlockSize());
        type.get(3).setPos(type.get(2).getX(), type.get(2).getY()+Rect.getBlockSize());
    }

    public void setPos_preview(int x, int y)
    {
        preViews.get(0).setPos(x + 10, y - 15);
        preViews.get(1).setPos(preViews.get(0).getX(), preViews.get(0).getY()+Rect.getPreviewBlockSize());
        preViews.get(2).setPos(preViews.get(1).getX(), preViews.get(1).getY()+Rect.getPreviewBlockSize());
        preViews.get(3).setPos(preViews.get(2).getX(), preViews.get(2).getY()+Rect.getPreviewBlockSize());
    }

    @Override
    public void blockDraw(List<Rect> rect)
    {
        Manager.GAME_MANAGER.fill(RED,GREEN,BLUE,Rect.getColorAlpha());
        super.blockDraw(this.type);
    }

    @Override
    public void rotate()
    {
        if(!rotateFlag)
        {

            type.get(1).setPos(type.get(1).getX(), type.get(1).getY()); // 기준
            type.get(0).setPos(type.get(1).getX() - Rect.getBlockSize(), type.get(1).getY());
            type.get(2).setPos(type.get(1).getX() + Rect.getBlockSize(), type.get(1).getY());
            type.get(3).setPos(type.get(2).getX() + Rect.getBlockSize(), type.get(1).getY());
            rotateFlag = true;
        }
        else
        {
            type.get(1).setPos(type.get(1).getX(), type.get(1).getY()); // 기준
            type.get(0).setPos(type.get(1).getX(), type.get(1).getY() - Rect.getBlockSize());
            type.get(2).setPos(type.get(1).getX(), type.get(1).getY() + Rect.getBlockSize());
            type.get(3).setPos(type.get(2).getX(), type.get(2).getY() + Rect.getBlockSize());
            rotateFlag = false;
        }

    }

    @Override
    public void setTestBlockPos()
    {
        if(!rotateFlag)
        {
            test.get(1).setPos(type.get(1).getX(), type.get(1).getY()); // 기준
            test.get(0).setPos(test.get(1).getX() - Rect.getBlockSize(), test.get(1).getY());
            test.get(2).setPos(test.get(1).getX() + Rect.getBlockSize(), test.get(1).getY());
            test.get(3).setPos(test.get(2).getX() + Rect.getBlockSize(), test.get(1).getY());
        }
        else
        {
            test.get(1).setPos(type.get(1).getX(), type.get(1).getY()); // 기준
            test.get(0).setPos(test.get(1).getX() + Rect.getBlockSize(), test.get(1).getY() - Rect.getBlockSize());
            test.get(2).setPos(test.get(1).getX(), test.get(1).getY() + Rect.getBlockSize());
            test.get(3).setPos(test.get(2).getX(), test.get(2).getY() + Rect.getBlockSize());
        }
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
