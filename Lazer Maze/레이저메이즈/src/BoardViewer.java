import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class BoardViewer extends JPanel implements ChangeListener{

	Card c = new Card();
	Laser ls = new Laser();
	Reader rd = null;
	Reader rd2 = new Reader();
	static int mainw = 0; // 메인 보여줄 flag
	static int move; // 토큰을 솔루션에 따라 움직임을 보여줄 flag
    static int laser; // 레이저 발사 flag
    
	public void paintComponent(Graphics g){
		
		Image cdimg;
		cdimg = c.img;
		if(laser == 0){
		g.setColor(Color.white);
		g.fillRect(0, 0, 800, 720);// 이전 그림 지워준다.	
		}
		
		if(mainw == 0){ // main flag 걸리면,
			cdimg = new ImageIcon("src/img/fld2.png").getImage();
			g.drawImage(cdimg,100,150,this);  // 메인 이미지 그려주고
			}	
		else if(laser == 0) g.drawImage(cdimg,3,-30,this); // 아니면 일반 이미지(카드 판 이미지)
		
		if(rd2.sol == 1 && c.cardNum == 1) { // 솔루션 상태이고, 1번 카드이면,
			cdimg = new ImageIcon("src/img/solc1.png").getImage();
		    g.drawImage(cdimg,3,3,this); // 1번카드의 솔루션 이미지를 그려준다.
		}
		if(rd2.sol == 1 && c.cardNum == 2) { // same..
			cdimg = new ImageIcon("src/img/solc2.png").getImage();
			g.drawImage(cdimg,3,3,this);
		}
		
		if(!c.Bl.isEmpty() && rd2.play == 1 && rd2.sol == 0 ){// 사용 할 토큰이 파랑 토큰 이라면 (사용 할 토큰들은 다 등장.)
			AffineTransform at;
		if(move == 0){ // 솔루션에의한 배치 상태가 아니라면,
		 at = AffineTransform.getTranslateInstance(455,100);} // 사용 할 토큰들은 카드 옆자리에 보여짐
		else{
		 at = AffineTransform.getTranslateInstance(c.B.x*50+30, c.B.y*50 + 180);} // move버튼을 눌렀을 때의 위치 이동.
		
		BufferedImage token = loadimg("src/img/blue.png"); // 회전 시킬 토큰 이미지를 불러옴.
		at.rotate(Math.toRadians(rd2.rotateBl), token.getWidth()/2,token.getHeight()/2); // 45도씩 회전.
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(token, at, null); // 회전 한 만큼 그려줌.
		repaint();
		}
		if(!c.Pu.isEmpty() && rd2.play == 1  && rd2.sol == 0){ // 사용 할 토큰이 보라색 토큰 이라면 (사용 할 토큰들은 다 등장.)
			AffineTransform at;
			if(move == 0){ // same..
			 at = AffineTransform.getTranslateInstance(455,150);} 
			else{
			 at = AffineTransform.getTranslateInstance(c.P.x*50 + 85 ,c.P.y*50 + 180);} // move버튼을 눌렀을 때의 위치
			
			BufferedImage token = loadimg("src/img/purple.png");
			at.rotate(Math.toRadians(rd2.rotatePur), token.getWidth()/2,token.getHeight()/2);
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(token, at, null); // 이전 방식과 동일..
			repaint();
			}
		if(!c.re.isEmpty() && c.r.fix == 1 && rd2.play == 1  && rd2.sol == 0){ // 사용 된 토큰들 중 고정되지 않은 것들만 등장.
			AffineTransform at = AffineTransform.getTranslateInstance(c.r.x*50+10 ,c.r.y*50 + 200);
			BufferedImage token = loadimg("src/img/red.png");
			at.rotate(Math.toRadians(rd2.rotateRe), token.getWidth()/2,token.getHeight()/2);
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(token, at, null); // 고정되지 않은 배치된 이미지들을 돌려줄 수 있다.
			repaint();
			}
		
		if(laser == 1){ // 레이저 flag가 걸리면,
			ls.move();
			g.setColor(Color.RED);
			g.fillOval(ls.getX(), ls.getY(), 10, 10); // 레이저 이미지  draw
			
		}
	}
	

	BufferedImage loadimg (String f){ // 토큰 이미지 로딩
		BufferedImage imgtoken = null;
		try{
			imgtoken = ImageIO.read(new File(f)); 
		}catch (IOException e){}
		
    return imgtoken;  	
	}
	
	
	public void stateChanged(ChangeEvent e) {
		this.repaint();  // 바뀐 정보가 있으면 새로 그려준다.
		}
	
	public void setr(Reader b) { // reader의 정보가 바뀌면 새로 세팅 해 준다.
		b.addChangeListener(this);
		if (this.rd != null) { this.rd.removeChangeListener(this); }
		this.rd = b;
		}
	
}
