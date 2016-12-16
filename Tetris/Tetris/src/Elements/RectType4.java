package Elements;

import Processing.Manager;

import java.util.List;

/**
 * Created by dongdong on 2016-02-20.
 */
public class RectType4 extends TetrisBlock {

    private static final int RED = 255;
    private static final int GREEN = 215;
    private static final int BLUE = 0;

    private int rotateFlag = 1;

    @Override
    public void setRects(int x, int y)
    {
        type.get(0).setPos(x, y);
        type.get(1).setPos( type.get(0).getX()+ Rect.getBlockSize(),  type.get(0).getY() );
        type.get(2).setPos( type.get(1).getX() + Rect.getBlockSize(),  type.get(1).getY());
        type.get(3).setPos( type.get(1).getX(), type.get(2).getY() - Rect.getBlockSize());
    }

    @Override
    public void blockDraw(List<Rect> rect) {
        Manager.GAME_MANAGER.fill(RED,GREEN,BLUE,Rect.getColorAlpha());
        super.blockDraw(this.type);
    }

    @Override
    public void rotate()
    {
        if(rotateFlag == 1)
        {
            type.get(1).setPos(type.get(1).getX(),  type.get(1).getY());
            type.get(0).setPos(type.get(1).getX(), type.get(1).getY() - Rect.getBlockSize());
            type.get(2).setPos(type.get(1).getX(), type.get(1).getY() + Rect.getBlockSize());
            type.get(3).setPos(type.get(1).getX() + Rect.getBlockSize(), type.get(1).getY());
            rotateFlag = 2;
        }
        else if(rotateFlag == 2)
        {
            type.get(1).setPos(type.get(1).getX(),  type.get(1).getY());
            type.get(0).setPos(type.get(1).getX() - Rect.getBlockSize(), type.get(1).getY());
            type.get(2).setPos(type.get(1).getX() + Rect.getBlockSize(), type.get(1).getY());
            type.get(3).setPos(type.get(1).getX(), type.get(1).getY() + Rect.getBlockSize());
            rotateFlag = 3;
        }
        else if(rotateFlag == 3)
        {
            type.get(1).setPos(type.get(1).getX(),  type.get(1).getY());
            type.get(0).setPos(type.get(1).getX(), type.get(1).getY() - Rect.getBlockSize());
            type.get(2).setPos(type.get(1).getX(), type.get(1).getY() + Rect.getBlockSize());
            type.get(3).setPos(type.get(1).getX() - Rect.getBlockSize(), type.get(1).getY());
            rotateFlag = 4;
        }
        else if(rotateFlag == 4)
        {
            type.get(1).setPos(type.get(1).getX(),  type.get(1).getY());
            type.get(0).setPos(type.get(1).getX() - Rect.getBlockSize(), type.get(1).getY());
            type.get(2).setPos(type.get(1).getX() + Rect.getBlockSize(), type.get(1).getY());
            type.get(3).setPos(type.get(1).getX(), type.get(1).getY() - Rect.getBlockSize());
            rotateFlag = 1;
        }

    }

    @Override
    public void setTestBlockPos()
    {
        if(rotateFlag == 1)
        {
            test.get(1).setPos(type.get(1).getX(),  type.get(1).getY());
            test.get(0).setPos(test.get(1).getX(), test.get(1).getY() - Rect.getBlockSize());
            test.get(2).setPos(test.get(1).getX(), test.get(1).getY() + Rect.getBlockSize());
            test.get(3).setPos(test.get(1).getX() + Rect.getBlockSize(), test.get(1).getY());

        }
        else if(rotateFlag == 2)
        {
            test.get(1).setPos(type.get(1).getX(),  type.get(1).getY());
            test.get(0).setPos(test.get(1).getX() - Rect.getBlockSize(), test.get(1).getY());
            test.get(2).setPos(test.get(1).getX() + Rect.getBlockSize(), test.get(1).getY());
            test.get(3).setPos(test.get(1).getX(), test.get(1).getY() + Rect.getBlockSize());

        }
        else if(rotateFlag == 3)
        {
            test.get(1).setPos(type.get(1).getX(),  type.get(1).getY());
            test.get(0).setPos(test.get(1).getX(), test.get(1).getY() - Rect.getBlockSize());
            test.get(2).setPos(test.get(1).getX(), test.get(1).getY() + Rect.getBlockSize());
            test.get(3).setPos(test.get(1).getX() - Rect.getBlockSize(), test.get(1).getY());

        }
        else if(rotateFlag == 4)
        {
            test.get(1).setPos(type.get(1).getX(),  type.get(1).getY());
            test.get(0).setPos(test.get(1).getX() - Rect.getBlockSize(), test.get(1).getY());
            test.get(2).setPos(test.get(1).getX() + Rect.getBlockSize(), test.get(1).getY());
            test.get(3).setPos(test.get(1).getX(), test.get(1).getY() - Rect.getBlockSize());

        }
    }

    @Override
    public void setPos_preview(int x, int y) {
        preViews.get(0).setPos(x, y);
        preViews.get(1).setPos( preViews.get(0).getX()+ Rect.getPreviewBlockSize(),  preViews.get(0).getY() );
        preViews.get(2).setPos( preViews.get(1).getX() + Rect.getPreviewBlockSize(),  preViews.get(1).getY());
        preViews.get(3).setPos( preViews.get(1).getX(), preViews.get(2).getY() - Rect.getPreviewBlockSize());
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