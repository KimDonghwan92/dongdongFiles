package Elements;

import Processing.Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by dongdong on 2016-02-19.
 */
public abstract class TetrisBlock implements Cloneable{

    protected static final int DOWN_DIST = Rect.getBlockSize();
    private static final int BLOCK_NUMBER = 4;
    protected static final int PREVIEW_POS_X = 40;
    protected static final int PREVIEW_POS_Y0 = 20 + TetrisMap.WINDOW_OFFSET;
    protected static final int PREVIEW_POS_Y1 = 70 + TetrisMap.WINDOW_OFFSET;
    protected static final int PREVIEW_POS_Y2 = 120 + TetrisMap.WINDOW_OFFSET;
    protected static final int PREVIEW_POS_Y3 = 170 + TetrisMap.WINDOW_OFFSET;

    private Rect rect;
    private Rect testRect;
    private Rect pre;
    public ArrayList<Rect> test;
    public ArrayList<Rect> type;
    public ArrayList<Rect> preViews;

    public TetrisBlock()
    {
        type = new ArrayList<>();
        test = new ArrayList<>();
        preViews = new ArrayList<>();

        for(int i = 0 ; i < BLOCK_NUMBER ; i++)
        {
            rect = new Rect();
            testRect = new Rect();
            pre = new Rect();

            type.add(rect);
            test.add(testRect);
            preViews.add(pre);

        }
    }

    public abstract void setTestBlockPos();
    public abstract void setPos_preview(int x, int y);
    public abstract void setRects(int x, int y);
    public abstract void rotate();

    public static int getDownDist()
    {
        return DOWN_DIST;
    }

    public void blockDraw(List<Rect> rect)
    {
        for(int i = 0 ; i < rect.size() ; i++) {rect.get(i).draw();}
    }
   public void preViewDraw(List<Rect> rect)
    {
        for(int i = 0 ; i < rect.size() ; i++) {rect.get(i).preDraw();}
    }
    public void downViewDraw(List<Rect> rect)
    {
        for(int i = 0 ; i < rect.size() ; i++) {rect.get(i).downDraw();}
    }


    public void findPreViewPos(int index) {

        switch (index)
        {
            case 0:
                setPos_preview(PREVIEW_POS_X,PREVIEW_POS_Y0);
                break;

            case 1:
                setPos_preview(PREVIEW_POS_X,PREVIEW_POS_Y1);
                break;

            case 2:
                setPos_preview(PREVIEW_POS_X,PREVIEW_POS_Y2);
                break;

            case 3:
                setPos_preview(PREVIEW_POS_X,PREVIEW_POS_Y3);
                break;

        }

    }

    public void moveDown(List<Rect> rect)
    {
        for(int i = 0 ; i < rect.size() ; i++)
        {
            rect.get(i).setY(rect.get(i).getY() + DOWN_DIST);
        }
    }


    public void moveSide(int dist)
    {
        for(int i = 0 ; i < type.size() ; i++)
        {
            type.get(i).setX(type.get(i).getX() + dist);
        }
    }

    @Override
    public TetrisBlock clone()
    {
        TetrisBlock clonedTetrisBlock = null;

        try {
            clonedTetrisBlock = (TetrisBlock)super.clone();
            clonedTetrisBlock.type = new ArrayList<>(this.type);
            clonedTetrisBlock.test = new ArrayList<>(this.test);

            clonedTetrisBlock.type.clear();
            clonedTetrisBlock.test.clear();

            for(int i = 0 ; i < BLOCK_NUMBER ; i++)
            {
                clonedTetrisBlock.type.add(this.type.get(i).clone());
                clonedTetrisBlock.test.add(this.test.get(i).clone());
            }

            return clonedTetrisBlock;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;
    }

}
