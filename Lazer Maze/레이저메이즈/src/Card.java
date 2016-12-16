import java.awt.Image;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.ImageIcon;


public class Card {

	static int cardNum;
	static Image img;
	int level; // ���̵�
	int targetNum; // Ÿ�� ����
	int used_tokenNum; // ��� �� ��ū�� �������� ����
	int use_tokenNum; // ��� �� ��ū�� �������� ����
	Reader rr = new Reader();
	
	// ī�忡 ���� �ʿ��� ������ (���� Ŭ�������� ��� ���� ���� �����ϵ��� static���� ����.)
	static Purple end;
	static Red R;  // 1 
	static ArrayList<Red> Re = new ArrayList<Red>();
	
	static Purple P;  // 2
	static ArrayList<Purple> Pu = new ArrayList<Purple>();
	
	static Blue B;  // 3
	static ArrayList<Blue> Bl = new ArrayList<Blue>();
	
	static Green G;  // 4
	static ArrayList<Green> Gr = new ArrayList<Green>();

	static Yellow Y;  // 5 
	static ArrayList<Yellow> Ye = new ArrayList<Yellow>();
	
	static Black K; // 6
	static ArrayList<Black> Ck = new ArrayList<Black>();
	///////////////////////// ��� �� ��ū�� ������ �����ϰ� ArrayList�� ��´�.
	
	static Red r;  // 1   
	static ArrayList<Red> re = new ArrayList<Red>();
	static Blue b;  // 3
	static ArrayList<Blue> bl = new ArrayList<Blue>();
	static Green g;  // 4
	static ArrayList<Green> gr = new ArrayList<Green>();
	
	static Purple p;
	static ArrayList<Purple> pu = new ArrayList<Purple>();
	
	static Black k; // 6
	static ArrayList<Black> ck = new ArrayList<Black>();
	static Yellow y;  // 5  
	static ArrayList<Yellow> ye = new ArrayList<Yellow>();
///////////////////////// ��� �� ��ū�� ������ �����ϰ� ArrayList�� ��´�.
	
	String use_token;
	String[] use_tokenType1; // split���� �ɰ� ������ ������ �迭.
	
	
	String used_token;
	String[] used_tokenType1;
	String[] used_tokenType2;  // split���� �ɰ� ������ ������ �迭.
	
	
	public void resetCard(){  // ��� ������  ����.
		r = null;
		R = null;
		b = null;
		B = null;
		g = null;
		G = null;
		y = null;
		Y = null;
		k = null;
		K = null;
		p = null;
		P = null;
		re.clear();
		Re.clear();
		Pu.clear();
		pu.clear();
		gr.clear();
		Gr.clear();
		bl.clear();
		Bl.clear();
		ye.clear();
		Ye.clear();
		Ck.clear();
		ck.clear();	
		rr.rotatePur = 0;
		rr.rotateRe = 0;
		rr.rotateBl = 0;
		rr.sol = 0;
		rr.play = 0;
		cardNum = 0;
		level = 0;
		targetNum = 0;
		used_tokenNum = 0;
		use_token = null;
		use_tokenType1 = null;
		used_token = null;
		used_tokenType1 = null;
		used_tokenType2 = null;		
		}
	
	public void set_cardimg(int n){
		cardNum = n;		
		if(this.cardNum == 1) img = new ImageIcon("src/img/Card-1.png").getImage();
		if(this.cardNum == 2) img = new ImageIcon("src/img/Card-2.png").getImage();			
			// ī�� ��ȣ�� ���� �̹��� �ε�	
	}	
	public void used_setting(){ // ��ġ �� ��ū ���� ����
		used_tokenType1 = used_token.split(" ");//�������� �ɰ���,
		for(int i = 0; i < used_tokenNum ; i++){ // ������ŭ ī�� ������ �����Ѵ�.
	
			used_tokenType2 = used_tokenType1[i].split("/"); // �ɰ��͵��� / �� �ɰ���.
	
			
			if(used_tokenType2.length == 4){ // 4���� ������ ��� �ִٸ�-> ������� �ƴϴ�.
				
				if(Integer.parseInt(used_tokenType2[0]) == 1){ // red ��ū ���� ����
					int tempX,tempY;
					String arr[];
					r = new Red();
					r.set_used(1); // ��� �Ǵ� ������ �ƴ��� ����.
					arr = used_tokenType2[1].split(","); 
					tempX = Integer.parseInt(arr[0]);
					tempY = Integer.parseInt(arr[1]);
					r.set_xy(tempX,tempY); //x,y��ǥ ����
					r.set_dir(Integer.parseInt(used_tokenType2[2])); // ���� ����
					r.set_fix(Integer.parseInt(used_tokenType2[3])); // ������ ��ū���� �ƴ��� ����
					re.add(r); // ArrayList�� ����
				}
			}
			if(used_tokenType2.length == 5){ // purple ��ū ���� ����
				if(Integer.parseInt(used_tokenType2[0]) == 2){
					int tempX,tempY;
					String arr[];
					p = new Purple();
					p.set_used(1);
					arr = used_tokenType2[1].split(",");
					tempX = Integer.parseInt(arr[0]);
					tempY = Integer.parseInt(arr[1]);
					p.set_xy(tempX,tempY);
					p.set_dir(Integer.parseInt(used_tokenType2[2]));
					p.set_fix(Integer.parseInt(used_tokenType2[3]));
					p.set_isTarget((Integer.parseInt(used_tokenType2[4])));
					if(p.isTarget == 1) end = p; // Ÿ���� �Ǵ� ��ū�� ���� ���� �� �ش�.
					pu.add(p);
				}
			}
			if(used_tokenType2.length == 4){ // blue ��ū ���� ����
				if(Integer.parseInt(used_tokenType2[0]) == 3){
					int tempX,tempY;
					String arr[];
					b = new Blue();
					b.set_used(1);
					arr = used_tokenType2[1].split(",");
					tempX = Integer.parseInt(arr[0]);
					tempY = Integer.parseInt(arr[1]);
					b.set_xy(tempX,tempY);
					b.set_dir(Integer.parseInt(used_tokenType2[2]));
					b.set_fix(Integer.parseInt(used_tokenType2[3]));
					bl.add(b);
				}
			}
			if(used_tokenType2.length == 4){ // green ��ū ���� ����
				if(Integer.parseInt(used_tokenType2[0]) == 4){
					int tempX,tempY;
					String arr[];
					g = new Green();
					g.set_used(1);
					arr = used_tokenType2[1].split(",");
					tempX = Integer.parseInt(arr[0]);
					tempY = Integer.parseInt(arr[1]);
					g.set_xy(tempX,tempY);
					g.set_dir(Integer.parseInt(used_tokenType2[2]));
					g.set_fix(Integer.parseInt(used_tokenType2[3]));
					gr.add(g);
				}
			}
			if(used_tokenType2.length == 4){ // yellow ��ū ���� ����
				if(Integer.parseInt(used_tokenType2[0]) == 5){
					int tempX,tempY;
					String arr[];
					y = new Yellow();
					y.set_used(1);
					arr = used_tokenType2[1].split(",");
					tempX = Integer.parseInt(arr[0]);
					tempY = Integer.parseInt(arr[1]);
					y.set_xy(tempX,tempY);
					y.set_dir(Integer.parseInt(used_tokenType2[2]));
					y.set_fix(Integer.parseInt(used_tokenType2[3]));
					ye.add(y);
				}
			}
			if(used_tokenType2.length == 4){ // black ��ū ���� ����
				if(Integer.parseInt(used_tokenType2[0]) == 6){
					int tempX,tempY;
					String arr[];
					k = new Black();
					k.set_used(1);
					arr = used_tokenType2[1].split(",");
					tempX = Integer.parseInt(arr[0]);
					tempY = Integer.parseInt(arr[1]);
					k.set_xy(tempX,tempY);
					k.set_dir(Integer.parseInt(used_tokenType2[2]));
					k.set_fix(Integer.parseInt(used_tokenType2[3]));
					ck.add(k);
				}
			}
		}
	}
	
	public void use_setting(){ // ��� �� ��ū  ���� ����
		use_tokenType1 = use_token.split("/");		
		for(int i = 0; i < use_tokenNum ; i++){ // ������ŭ ������ �����Ѵ�.		
				if(Integer.parseInt(use_tokenType1[i]) == 1){  // red ��ū ���� ����
					R = new Red();
					R.set_use(1);
					Re.add(R);
				}
				if(Integer.parseInt(use_tokenType1[i]) == 2){ // purple ��ū ���� ����
					P = new Purple();
					P.set_use(1);
					Pu.add(p);
				}
				if(Integer.parseInt(use_tokenType1[i]) == 3){ // blue ��ū ���� ����				
					B = new Blue();
					B.set_use(1);
					Bl.add(B);
				}
				if(Integer.parseInt(use_tokenType1[i]) ==  4){ // green ��ū ���� ����
					G = new Green();
					G.set_use(1);
					Gr.add(G);
				}
				if(Integer.parseInt(use_tokenType1[i]) ==  5){ // yellow ��ū ���� ����
					Y = new Yellow();
					Y.set_use(1);
					Ye.add(Y);
				}
				if(use_tokenType1[i] == "6"){ // black ��ū ���� ����
					K = new Black();
					K.set_use(1);
					Ck.add(K);
				}}}}