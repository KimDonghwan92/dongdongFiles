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
	static int mainw = 0; // ���� ������ flag
	static int move; // ��ū�� �ַ�ǿ� ���� �������� ������ flag
    static int laser; // ������ �߻� flag
    
	public void paintComponent(Graphics g){
		
		Image cdimg;
		cdimg = c.img;
		if(laser == 0){
		g.setColor(Color.white);
		g.fillRect(0, 0, 800, 720);// ���� �׸� �����ش�.	
		}
		
		if(mainw == 0){ // main flag �ɸ���,
			cdimg = new ImageIcon("src/img/fld2.png").getImage();
			g.drawImage(cdimg,100,150,this);  // ���� �̹��� �׷��ְ�
			}	
		else if(laser == 0) g.drawImage(cdimg,3,-30,this); // �ƴϸ� �Ϲ� �̹���(ī�� �� �̹���)
		
		if(rd2.sol == 1 && c.cardNum == 1) { // �ַ�� �����̰�, 1�� ī���̸�,
			cdimg = new ImageIcon("src/img/solc1.png").getImage();
		    g.drawImage(cdimg,3,3,this); // 1��ī���� �ַ�� �̹����� �׷��ش�.
		}
		if(rd2.sol == 1 && c.cardNum == 2) { // same..
			cdimg = new ImageIcon("src/img/solc2.png").getImage();
			g.drawImage(cdimg,3,3,this);
		}
		
		if(!c.Bl.isEmpty() && rd2.play == 1 && rd2.sol == 0 ){// ��� �� ��ū�� �Ķ� ��ū �̶�� (��� �� ��ū���� �� ����.)
			AffineTransform at;
		if(move == 0){ // �ַ�ǿ����� ��ġ ���°� �ƴ϶��,
		 at = AffineTransform.getTranslateInstance(455,100);} // ��� �� ��ū���� ī�� ���ڸ��� ������
		else{
		 at = AffineTransform.getTranslateInstance(c.B.x*50+30, c.B.y*50 + 180);} // move��ư�� ������ ���� ��ġ �̵�.
		
		BufferedImage token = loadimg("src/img/blue.png"); // ȸ�� ��ų ��ū �̹����� �ҷ���.
		at.rotate(Math.toRadians(rd2.rotateBl), token.getWidth()/2,token.getHeight()/2); // 45���� ȸ��.
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(token, at, null); // ȸ�� �� ��ŭ �׷���.
		repaint();
		}
		if(!c.Pu.isEmpty() && rd2.play == 1  && rd2.sol == 0){ // ��� �� ��ū�� ����� ��ū �̶�� (��� �� ��ū���� �� ����.)
			AffineTransform at;
			if(move == 0){ // same..
			 at = AffineTransform.getTranslateInstance(455,150);} 
			else{
			 at = AffineTransform.getTranslateInstance(c.P.x*50 + 85 ,c.P.y*50 + 180);} // move��ư�� ������ ���� ��ġ
			
			BufferedImage token = loadimg("src/img/purple.png");
			at.rotate(Math.toRadians(rd2.rotatePur), token.getWidth()/2,token.getHeight()/2);
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(token, at, null); // ���� ��İ� ����..
			repaint();
			}
		if(!c.re.isEmpty() && c.r.fix == 1 && rd2.play == 1  && rd2.sol == 0){ // ��� �� ��ū�� �� �������� ���� �͵鸸 ����.
			AffineTransform at = AffineTransform.getTranslateInstance(c.r.x*50+10 ,c.r.y*50 + 200);
			BufferedImage token = loadimg("src/img/red.png");
			at.rotate(Math.toRadians(rd2.rotateRe), token.getWidth()/2,token.getHeight()/2);
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(token, at, null); // �������� ���� ��ġ�� �̹������� ������ �� �ִ�.
			repaint();
			}
		
		if(laser == 1){ // ������ flag�� �ɸ���,
			ls.move();
			g.setColor(Color.RED);
			g.fillOval(ls.getX(), ls.getY(), 10, 10); // ������ �̹���  draw
			
		}
	}
	

	BufferedImage loadimg (String f){ // ��ū �̹��� �ε�
		BufferedImage imgtoken = null;
		try{
			imgtoken = ImageIO.read(new File(f)); 
		}catch (IOException e){}
		
    return imgtoken;  	
	}
	
	
	public void stateChanged(ChangeEvent e) {
		this.repaint();  // �ٲ� ������ ������ ���� �׷��ش�.
		}
	
	public void setr(Reader b) { // reader�� ������ �ٲ�� ���� ���� �� �ش�.
		b.addChangeListener(this);
		if (this.rd != null) { this.rd.removeChangeListener(this); }
		this.rd = b;
		}
	
}
