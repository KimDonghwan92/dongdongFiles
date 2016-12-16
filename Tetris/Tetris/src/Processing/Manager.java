package Processing;

import Elements.*;
import processing.core.PApplet;
import java.util.*;

/**
 * Created by dongdong on 2016-02-19.
 */
public class Manager extends PApplet {

    public static final Manager GAME_MANAGER = new Manager();
    private static final int MOVE_DIST = Rect.getBlockSize();
    private static final int CLEAR_LINE_BONUS_SCORE = 100;
    private static final int BASIC_BONUS_SCORE = 10;
    private static final int PREVIEW_BLOCKS_NUMBER = 4;

    private static final TetrisMap map = new TetrisMap();

    private boolean grid[] = new boolean[ (TetrisMap.GAME_WINDOW_Y2 - TetrisMap.GAME_WINDOW_Y1) / Rect.getBlockSize()];

    private static List<TetrisBlock> blocks;
    private static List<TetrisBlock> downblocks;
    private Collection syncCollection;
    private Collection syncCollectionDownBlock;

    private int score;
    private int countTime;
    private int countLine;

    public boolean isPlaying;

    private TetrisGame tetris;
    private Timer schedule;

    private static TetrisBlock presentBlcok;
    private static TetrisBlock downBlock;
    private static List<TetrisBlock> previewBlocks;

    public int getScore() {
        return score;
    }

    public int getCountLine() {
        return countLine;
    }

    public int getCountTime() {
        return countTime;
    }

    private static int randomRange(int num1, int num2)
    {
        return (int) (Math.random() * (num2 - num1 + 1)) + num1;
    }
    private static TetrisBlock makeBlock()
    {
        switch (randomRange(1,4))
        {
            case 1 :
                RectType1 rectType1 = new RectType1();
                rectType1.setRects((TetrisMap.GAME_WINDOW_X1 + TetrisMap.GAME_WINDOW_X2)/2, -Rect.getBlockSize()*2);
                return rectType1;

            case 2 :
                RectType2 rectType2 = new RectType2();
                rectType2.setRects((TetrisMap.GAME_WINDOW_X1 + TetrisMap.GAME_WINDOW_X2)/2, 0);
                return rectType2;

            case 3 :
                RectType3 rectType3 = new RectType3();
                rectType3.setRects((TetrisMap.GAME_WINDOW_X1 + TetrisMap.GAME_WINDOW_X2)/2, 0);
                return rectType3;

            case 4 :
                RectType4 rectType4 = new RectType4();
                rectType4.setRects((TetrisMap.GAME_WINDOW_X1 + TetrisMap.GAME_WINDOW_X2)/2, 0);
                return rectType4;
        }

        return null;
    }

    public static void main(String...args)
    {
        GAME_MANAGER.runSketch();
    }
    public void settings()
    {
        size(TetrisMap.WINDOW_WIDTH, TetrisMap.WINDOW_HEIGHT);
    }
    public void setup()
    {
        resetGrid();
        tetris = new TetrisGame();
        schedule = new Timer(true);
        blocks = new ArrayList<>();
        downblocks = new ArrayList<>();
        previewBlocks = new ArrayList<>();
        syncCollection = Collections.synchronizedCollection(blocks);
        syncCollectionDownBlock = Collections.synchronizedCollection(downblocks);
        score = 0;
        countTime = 0;
        countLine = 0;
        isPlaying = true;
        makePreViewBlocks();
        schedule.scheduleAtFixedRate(tetris,1000,1000);
    }

    public void makeDownBlock()
    {
        downblocks.clear();
        downBlock = null;
        downBlock = presentBlcok.clone();
        downblocks.add(downBlock);
        setBlockDown(downblocks.get(0));

    }

    public void setBlockDown(TetrisBlock downBlock)
    {
        while(!isCollision_under(downBlock))
        {
            downblocks.get(0).moveDown(downblocks.get(0).type);
        }
    }
    public void makeScore()
    {
        score = countLine*CLEAR_LINE_BONUS_SCORE + countTime*BASIC_BONUS_SCORE;
    }

    public void clearEmptyBlock()
    {
        for(int i = 0 ; i < blocks.size() ; i++)
        {
            /*
            for(int j = 0 ; j < blocks.get(i).type.size() ; j++)
            {
                if(blocks.get(i).type.get(j).getY() == REMOVE_SPOT || blocks.get(i).type.get(j).getX() == REMOVE_SPOT)
                {
                    blocks.get(i).type.remove(j);
                    j--;
                }
            }
            */
            if(blocks.get(i).type.size() == 0)
            {
                blocks.remove(i);
                i--;
            }
        }
    }

    public void checkOver()
    {
        for(int k = blocks.size()-1 ; k >= 0 ; k-- )
        {
            for(int i = 0 ; i < blocks.get(k).type.size() ; i++)
            {
                if(k != blocks.size()-1)
                {
                    if(blocks.get(k).type.get(i).getY() <= 0)
                    {
                        makeScore();
                        tetris.cancel();
                        isPlaying = false;
                    }
                }

            }
        }
    }
    public void draw()
    {
        if(isPlaying)
        {
            map.drawMap();
            gamePlayView();
        }
        else
        {
            map.drawMap();
        }

    }

    public void makePreViewBlocks()
    {
        if(previewBlocks.size() == 0)
            {
                for(int i = 0 ; i < PREVIEW_BLOCKS_NUMBER ; i++)
                {
                    previewBlocks.add(makeBlock());
                }
            }
        else
            {
                previewBlocks.add(makeBlock());
            }

        for(int i = 0 ; i < previewBlocks.size() ; i++)
        {
            previewBlocks.get(i).findPreViewPos(i);
        }

        presentBlcok = previewBlocks.get(0);
        blocks.add(presentBlcok);

        makeDownBlock();

    }

    public void gamePlayView()
    {

        synchronized (syncCollection)
        {
            for(TetrisBlock each : blocks)
            {
                each.blockDraw(each.type);
            }
            for(TetrisBlock each : previewBlocks)
            {
                each.preViewDraw(each.preViews);
            }
        }
        synchronized (syncCollectionDownBlock)
        {
            for(TetrisBlock each : downblocks)
            {
                each.downViewDraw(each.type);
            }
        }
        makeScore();
        fill(0);
        rect(TetrisMap.GAME_WINDOW_X1,0,(TetrisMap.GAME_WINDOW_X2 - TetrisMap.GAME_WINDOW_X1),TetrisMap.WINDOW_OFFSET);
    }

    public void keyPressed()
    {
        if (key == CODED && isPlaying)
        {
            if (keyCode == RIGHT)
            {
                if(!isCollision_side(presentBlcok, MOVE_DIST))
                {
                    presentBlcok.moveSide(MOVE_DIST);
                    makeDownBlock();
                }

            }
            else if (keyCode == LEFT)
            {
                if(!isCollision_side(presentBlcok, ((-1)*(MOVE_DIST))))
                {
                    presentBlcok.moveSide(((-1)*(MOVE_DIST)));
                    makeDownBlock();
                }
            }
            else if( keyCode == DOWN)
            {
                tetrisBlockMove();
            }
            else if( keyCode == UP)
            {
                presentBlcok.setTestBlockPos();
                if(!isCollision_rotate(presentBlcok))
                {
                    presentBlcok.rotate();
                    makeDownBlock();
                }
            }
        }
        else if(key == ' ' && isPlaying)
        {
            straightDown();
        }
        else if(key == 'R' || key == 'r'&& !isPlaying)
        {
            setup();
        }
    }

    public void straightDown()
    {
        while(!isCollision_under(presentBlcok))
        {
            presentBlcok.moveDown(presentBlcok.type);
        }
        if(isCollision_under(presentBlcok))
        {
            previewBlocks.remove(0);
            makePreViewBlocks();
            checkOver();
            matchAndClear();
            makeDownBlock();
        }

    }

    public boolean isCollision_rotate(TetrisBlock presentBlcok)
    {
        if(blocks.size() == 1)
        {
            for (int i = 0; i < presentBlcok.type.size(); i++)
            {
                if(presentBlcok.test.get(i).getX() < TetrisMap.GAME_WINDOW_X1 || TetrisMap.GAME_WINDOW_X2 < presentBlcok.test.get(i).getX() + Rect.getBlockSize())
                {
                    return true;
                }
            }
            return false;
        }
        else
        {
            for(int j = 0 ; j < blocks.size()-1 ; j++)
            {
                for (int i = 0; i < presentBlcok.type.size(); i++) {
                    if(presentBlcok.test.get(i).getX() < TetrisMap.GAME_WINDOW_X1 || TetrisMap.GAME_WINDOW_X2 < presentBlcok.test.get(i).getX() + Rect.getBlockSize())
                    {
                        return true;
                    }
                    for (int k = 0; k < blocks.get(j).type.size(); k++) {
                        if ( (presentBlcok.test.get(i).getX() < blocks.get(j).type.get(k).getX() + Rect.getBlockSize()
                                && blocks.get(j).type.get(k).getX() < presentBlcok.test.get(i).getX() + Rect.getBlockSize()
                                && presentBlcok.test.get(i).getY() < blocks.get(j).type.get(k).getY() + Rect.getBlockSize()
                                && blocks.get(j).type.get(k).getY() < presentBlcok.test.get(i).getY() + Rect.getBlockSize() )
                                )
                        {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean isCollision_side(TetrisBlock presentBlcok, int move_dist)
    {
        if(blocks.size() > 1)
        {
            for(int j = 0 ; j < blocks.size()-1 ; j++) {
                for (int i = 0; i <  presentBlcok.type.size(); i++) {
                    for(int k = 0 ; k < blocks.get(j).type.size(); k++)
                    {
                        if ( (presentBlcok.type.get(i).getX() + move_dist < blocks.get(j).type.get(k).getX() + Rect.getBlockSize()
                                && blocks.get(j).type.get(k).getX() < presentBlcok.type.get(i).getX() + Rect.getBlockSize() + move_dist
                                && presentBlcok.type.get(i).getY() < blocks.get(j).type.get(k).getY() + Rect.getBlockSize()
                                && blocks.get(j).type.get(k).getY() < presentBlcok.type.get(i).getY() + Rect.getBlockSize() )
                                || (presentBlcok.type.get(i).getX() + move_dist < TetrisMap.GAME_WINDOW_X1
                                || TetrisMap.GAME_WINDOW_X2 < presentBlcok.type.get(i).getX() + Rect.getBlockSize() + move_dist) )
                        {
                            return true;
                        }
                    }
                }
            }
        }
        else
        {
            for(int i = 0 ; i < presentBlcok.type.size() ; i++)
            {
                if (presentBlcok.type.get(i).getX() + move_dist < TetrisMap.GAME_WINDOW_X1
                        || TetrisMap.GAME_WINDOW_X2 < presentBlcok.type.get(i).getX() + Rect.getBlockSize() + move_dist)
                {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isCollision_under(TetrisBlock presentBlcok)
    {
        if(blocks.size() > 1)
        {
            for(int j = 0 ; j < blocks.size()-1 ; j++) {
                for (int i = 0; i <  presentBlcok.type.size(); i++)
                {
                    for(int k = 0 ; k < blocks.get(j).type.size(); k++)
                    {
                        if ( (presentBlcok.type.get(i).getX() < blocks.get(j).type.get(k).getX() + Rect.getBlockSize()
                                && blocks.get(j).type.get(k).getX() < presentBlcok.type.get(i).getX() + Rect.getBlockSize()
                                && presentBlcok.type.get(i).getY() + TetrisBlock.getDownDist() < blocks.get(j).type.get(k).getY() + Rect.getBlockSize()
                                && blocks.get(j).type.get(k).getY() < presentBlcok.type.get(i).getY() + Rect.getBlockSize() + TetrisBlock.getDownDist())
                                || (presentBlcok.type.get(i).getY() + Rect.getBlockSize() + MOVE_DIST> TetrisMap.GAME_WINDOW_Y2) )
                        {
                            return true;
                        }

                    }
                }
            }
        }
        else
        {
            for(int i = 0 ; i < presentBlcok.type.size() ; i++)
            {
                if (presentBlcok.type.get(i).getY() + Rect.getBlockSize() + MOVE_DIST >  TetrisMap.GAME_WINDOW_Y2)
                {
                    return true;
                }
            }
        }


        return false;
    }

    public void resetGrid()
    {
        for(int i = 0 ; i < grid.length ; i++)
        {
            grid[i] = false;
        }
    }

    public void tetrisBlockMove()
    {
        if(isCollision_under(presentBlcok))
        {
            previewBlocks.remove(0);
            checkOver();
            matchAndClear();
            makePreViewBlocks();
        }
        else
        {
            presentBlcok.moveDown(presentBlcok.type);
        }
    }

    public void matchAndClear()
    {
        matchLine(TetrisMap.GAME_WINDOW_Y2);
        clearLine();
        clearEmptyBlock();
        moveAfterClear();
        resetGrid();
    }

    public void moveAfterClear() {
        for (int j = 0; j < grid.length; j++)
        {
            if (grid[j])
            {
                countLine++;
                for(int i = 0 ; i < blocks.size() ; i++)
                {
                    for(int k = 0 ; k < blocks.get(i).type.size() ; k++)
                    {
                        if(blocks.get(i).type.get(k).getY() < j*Rect.getBlockSize() + TetrisMap.WINDOW_OFFSET)
                        {
                            blocks.get(i).type.get(k).setY(blocks.get(i).type.get(k).getY()+Rect.getBlockSize());
                        }
                    }
                }
            }
        }
    }

    public void clearLine()
    {
        for(int j  = 0 ; j < grid.length ; j++)
        {
            if(grid[j])
            {
                for(int k = blocks.size()-1 ; k >= 0 ; k-- )
                {
                    for(int i = 0 ; i < blocks.get(k).type.size() ; i++)
                    {
                        if(k != blocks.size()-1)
                        {
                          if(blocks.get(k).type.get(i).getY() == j*Rect.getBlockSize() + TetrisMap.WINDOW_OFFSET)
                          {
                              blocks.get(k).type.remove(i);
                              i--;
                          }
                        }
                    }


                }
            }
        }
    }

    public void matchLine(int line)
    {
        int matchNum = 0;

        for(int k = blocks.size()-1 ; k >= 0 ; k-- )
        {
            for(int i = 0 ; i < blocks.get(k).type.size() ; i++)
            {
                if(k != blocks.size()-1)
                {
                    if( blocks.get(k).type.get(i).getY() == (line - Rect.getBlockSize()) )
                    {
                        matchNum++;
                        if(matchNum == (TetrisMap.GAME_WINDOW_X2 - TetrisMap.GAME_WINDOW_X1) /Rect.getBlockSize())
                        {
                            grid[(line - Rect.getBlockSize()-TetrisMap.WINDOW_OFFSET)/Rect.getBlockSize()] = true;
                        }
                    }
                }
            }
        }
        if(line - Rect.getBlockSize() >= TetrisMap.WINDOW_OFFSET)
        {
            matchLine(line - Rect.getBlockSize());
        }
    }

    class TetrisGame extends TimerTask
    {
        @Override
        public void run()
        {
            countTime++;
            tetrisBlockMove();
        }
    }

}

