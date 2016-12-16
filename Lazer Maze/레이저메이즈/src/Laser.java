import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Laser extends JPanel {
    /*
	��ġ ����
	������ġ�� ����մ��� ������ ����������, �ݻ��Ų��, ��������. 
	���δ� �ε����� Ȯ��, Ÿ�ٿ������ϸ� ��
	*/
	Reader r = new Reader();
	Card c = new Card();
	
	private ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>();
	static int posX,posY; // �������� x,y��ǥ (���� ��)
	static int clicked = 2; // X�������� �������� ������.
	static int clicked2 = 2; // Y�������� �������� ������.
	static int deltaT = 2; // �� �ӵ���ŭ �̵�.
	static int startDir; // �������� ����
	static int endX,endY,endDir; // ������ ���� ��ǥ�� ����.
	static int flag; // ��ū���� ������ ���� flag
    
    void reset(){ // �������߻��� �� �ʱ�ȭ �ʿ��� �� ����Ѵ�.
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
		this.setting(); // ��߰� �� ��ū�� �������� ���� (�ѹ���), �������� ���.
		 if(clicked == 1){
			  // x�������θ� ���� �̵�.
			 this.posX -= deltaT;
			 if(this.posX <= 0)
				 clicked = 0;}
		    	  // x��ǥ�� �����̵��� ����.	   
		 
		 if(clicked == 0){ // x�������� ���� �̵�.			  
			 this.posX += deltaT;
			 if(this.posX > c.end.x*50+60 && this.posY < c.end.y*50+ 210){
				 this.posX -= deltaT;	 // ������ �� ������ ����.
			   }
			 if(c.cardNum == 2){
			 if(this.posX > c.P.x*50+110){
				 clicked = 2;
				 clicked2 = 1; } }
			 } // x��ǥ�� �����̵��� ����.	
		 
		 if(clicked2 == 1){
			 this.posY -= deltaT;
			 if(c.cardNum == 2){ // y�����θ� ���� �.
			 if(this.posY <= c.P.y*50 + 200 && this.posX < c.P.x*50 + 60 ){
				 clicked = 0; 
				 clicked2 = 2;
			 }
			 if(this.posY < c.end.y*50 + 180){this.posY += deltaT;}}
				  } // y��ǥ�� �����̵��� ����.
		 
		 if(clicked2 == 0){ // y�����θ� ���� �.
			 this.posY += deltaT;
			 if(c.cardNum == 1){
			 if(this.posY > c.B.y*50 + 200){
				 if(c.B.dir == 2 || c.B.dir == 3){ // �Ķ� ��ū ������ dir������ �̵�.
				 clicked = 0;
				 clicked2 = 2;
				 }
			 } }
			 }
			 // y��ǥ�� �����̵��� ����.	 
		for (ChangeListener l : this.listeners) {
		l.stateChanged(new ChangeEvent(this));}} // ���ϴ� �������� ���¸� ������.
	
	
	public int getX(){
		return posX;
	}
	public int getY(){
		return posY;
	}	 // x , y ��ǥ �������� �޼ҵ�
	public void setting(){
		if(flag == 0){
		posX = c.r.x*50 + 50; // �� ��ǥ�� ���� �̹����� �°� ��ǥ ����
		// x,y�� �� 50��ŭ �����ְ� x�� 50 Y�� 180��ŭ �����ָ� ���� �׸��� �̹����� �� �´�.
		posY = c.r.y*50 + 180;
		startDir = c.r.dir;
		endY = c.end.y;
		endX = c.end.x;
		endDir = c.end.dir;	
		if(startDir == 2)clicked = 1; // ��ŸƮ�� �������� ���� �� ��.
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
