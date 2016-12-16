import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Laser extends JPanel {
    /*
	위치 방향
	도달위치에 토근잇는지 있으면 뭔종류인지, 반사시킨다, 갈라진다. 
	전부다 부딪혓나 확인, 타겟에도달하면 끝
	*/
	Reader r = new Reader();
	Card c = new Card();
	
	private ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>();
	static int posX,posY; // 레이저의 x,y좌표 (시작 점)
	static int clicked = 2; // X축으로의 움직임을 제어함.
	static int clicked2 = 2; // Y축으로의 움직임을 제어함.
	static int deltaT = 2; // 이 속도만큼 이동.
	static int startDir; // 시작점의 방향
	static int endX,endY,endDir; // 끝나는 점의 좌표와 방향.
	static int flag; // 토큰들의 세팅을 위한 flag
    
    void reset(){ // 레이저발사한 뒤 초기화 필요할 때 사용한다.
    	posX = 0;
    	posY = 0;
    	clicked = 2;
    	clicked2 = 2;
    	startDir = 0;
    	endX = 0;
    	endY = 0;
    	endDir = 0;
    	flag = 0;
    	
    }
	void move() {
		this.setting(); // 출발과 끝 토큰의 정보들을 세팅 (한번만), 레이저가 출발.
		 if(clicked == 1){
			  // x방향으로만 감소 이동.
			 this.posX -= deltaT;
			 if(this.posX <= 0)
				 clicked = 0;}
		    	  // x좌표만 움직이도록 설정.	   
		 
		 if(clicked == 0){ // x방향으로 증가 이동.			  
			 this.posX += deltaT;
			 if(this.posX > c.end.x*50+60 && this.posY < c.end.y*50+ 210){
				 this.posX -= deltaT;	 // 끝나는 점 만나면 멈춤.
			   }
			 if(c.cardNum == 2){
			 if(this.posX > c.P.x*50+110){
				 clicked = 2;
				 clicked2 = 1; } }
			 } // x좌표만 움직이도록 설정.	
		 
		 if(clicked2 == 1){
			 this.posY -= deltaT;
			 if(c.cardNum == 2){ // y축으로만 감소 운동.
			 if(this.posY <= c.P.y*50 + 200 && this.posX < c.P.x*50 + 60 ){
				 clicked = 0; 
				 clicked2 = 2;
			 }
			 if(this.posY < c.end.y*50 + 180){this.posY += deltaT;}}
				  } // y좌표만 움직이도록 설정.
		 
		 if(clicked2 == 0){ // y축으로만 증가 운동.
			 this.posY += deltaT;
			 if(c.cardNum == 1){
			 if(this.posY > c.B.y*50 + 200){
				 if(c.B.dir == 2 || c.B.dir == 3){ // 파랑 토큰 만나면 dir에따라 이동.
				 clicked = 0;
				 clicked2 = 2;
				 }
			 } }
			 }
			 // y좌표만 움직이도록 설정.	 
		for (ChangeListener l : this.listeners) {
		l.stateChanged(new ChangeEvent(this));}} // 변하는 레이져의 상태를 보내줌.
	
	
	public int getX(){
		return posX;
	}
	public int getY(){
		return posY;
	}	 // x , y 좌표 가져오는 메소드
	public void setting(){
		if(flag == 0){
		posX = c.r.x*50 + 50; // 각 좌표에 따라 이미지에 맞게 좌표 설정
		// x,y둘 다 50만큼 곱해주고 x는 50 Y는 180만큼 더해주면 내가 그리는 이미지에 딱 맞다.
		posY = c.r.y*50 + 180;
		startDir = c.r.dir;
		endY = c.end.y;
		endX = c.end.x;
		endDir = c.end.dir;	
		if(startDir == 2)clicked = 1; // 스타트가 왼쪽으로 시작 할 때.
		if(startDir == 1)clicked = 0;
		if(startDir == 4)clicked2 = 1;
		if(startDir == 3)clicked2 = 0;
		flag = 1;
		}
	}
	public void addChangeListener(ChangeListener l) {
		this.listeners.add(l);
		}
	public void removeChangeListener(ChangeListener l) {
		this.listeners.remove(l);
		}
	public ChangeListener[] getChangeListeners() {
		return (ChangeListener[])(this.listeners.toArray());
		}
}
