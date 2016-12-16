package Elements;
import Processing.Manager;
import processing.core.PConstants;

/**
 * Created by dongdong on 2016-02-25.
 */
public class TetrisMap {

    public static final int WINDOW_WIDTH = 530;
    public static final int WINDOW_HEIGHT = 600;
    public static final int WINDOW_OFFSET = 30;

    public static final int GAME_WINDOW_X1 = 140;
    public static final int GAME_WINDOW_X2 = 500;
    public static final int GAME_WINDOW_Y1 = 30;
    public static final int GAME_WINDOW_Y2 = 570;

    public static final int BLOCK_LIST_WINDOW_X = 30;
    public static final int BLOCK_LIST_WINDOW_Y = 30;
    public static final int BLOCK_LIST_WIDTH = 50;
    public static final int BLOCK_LIST_HEIGHT = 210;

    public static final int RECORD_X = 30;
    public static final int RECORD_Y = 300;

    public void drawMap()
    {
        if(Manager.GAME_MANAGER.isPlaying)
        {
            Manager.GAME_MANAGER.fill(0);
            Manager.GAME_MANAGER.rect(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
            //배경

            Manager.GAME_MANAGER.fill(255);
            Manager.GAME_MANAGER.rect(BLOCK_LIST_WINDOW_X, BLOCK_LIST_WINDOW_Y, BLOCK_LIST_WIDTH, BLOCK_LIST_HEIGHT);
            //블록 리스트

            Manager.GAME_MANAGER.fill(255);
            Manager.GAME_MANAGER.rect(GAME_WINDOW_X1,GAME_WINDOW_Y1, GAME_WINDOW_X2-GAME_WINDOW_X1, GAME_WINDOW_Y2 - GAME_WINDOW_Y1);
            //게임 창

            Manager.GAME_MANAGER.textSize(15);
            Manager.GAME_MANAGER.fill(255);
            Manager.GAME_MANAGER.text("Time :  " + Manager.GAME_MANAGER.getCountTime(), RECORD_X, RECORD_Y);

            Manager.GAME_MANAGER.textSize(15);
            Manager.GAME_MANAGER.fill(0,102,153);
            Manager.GAME_MANAGER.text("Line :  " + Manager.GAME_MANAGER.getCountLine(), RECORD_X, RECORD_Y + 20);

            Manager.GAME_MANAGER.textSize(15);
            Manager.GAME_MANAGER.fill(0,102,153);
            Manager.GAME_MANAGER.text("Score :  " + Manager.GAME_MANAGER.getScore(), RECORD_X, RECORD_Y + 40);

        }
        else
        {
            Manager.GAME_MANAGER.fill(0);
            Manager.GAME_MANAGER.rect(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);

            Manager.GAME_MANAGER.textSize(20);
            Manager.GAME_MANAGER.fill(255);
            Manager.GAME_MANAGER.text("Press 'R' to reStart", TetrisMap.WINDOW_WIDTH/2 - 80, TetrisMap.WINDOW_HEIGHT/2);
            Manager.GAME_MANAGER.fill(0,102,153);
            Manager.GAME_MANAGER.text("Score : " + Manager.GAME_MANAGER.getScore() , TetrisMap.WINDOW_WIDTH/2 - 30, TetrisMap.WINDOW_HEIGHT/2 - 30);
            Manager.GAME_MANAGER.text("Cleared lines : " + Manager.GAME_MANAGER.getCountLine() , TetrisMap.WINDOW_WIDTH/2- 70, TetrisMap.WINDOW_HEIGHT/2 - 60);
        }
    }



}
