import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Board {

	Reader reader;
	BoardViewer v;
	Card ccc = new Card();
	Laser lsr = new Laser();
	JFrame frame; // 메인 창
	JFrame frame2; // 카드 선택 창
	JFrame frame3; // 게임 플레이 창
	JFrame frame4; // solution 창
	JPanel panel; // 메인 패널
	JPanel panel2; // 카드 선택 yes or no 패널
	JPanel panel3; // select, solution, init, rotate가 들어있는 패널

	int Fz1 = 800;
	int Fz2 = 720; // 프레임 사이즈

	public static void main(String[] args) {
		Board m = new Board();
		m.go();// GUI 창 연다.
	}

	public void go() {

		frame = new JFrame();
		panel = new JPanel();
		final JTextField fld = new JTextField(1);
		v = new BoardViewer();
		reader = new Reader();
		panel.setBackground(Color.DARK_GRAY);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		v.setr(reader);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton card1 = new JButton("CARD 1");
		JButton card2 = new JButton("CARD 2"); // 카드 종류 보여주는 버튼

		panel.add(card1);
		panel.add(card2); // main 창에서 카드 종류를 보여주는 패널.

		fld.addActionListener(new ActionListener() { // 숫자 입력창에 입력 했을 시.
			public void actionPerformed(ActionEvent event) {
				v.mainw = 1;  // boardviewer에서  main img를 보여줄 flag
				frame2 = new JFrame();
				panel2 = new JPanel();
				panel2.setBackground(Color.DARK_GRAY);
				JButton yes = new JButton("yes"); // 카드 확인 버튼
				JButton no = new JButton("no"); // 카드 취소 버튼
				panel2.add(yes);
				panel2.add(no);

				if (fld.getText().equals("1"))
					reader.setcard("src/card_1.txt"); // 입력한 숫자가 1 이라면 1번 카드를
														// 읽어온다.
				if (fld.getText().equals("2"))
					reader.setcard("src/card_2.txt"); // same..

				frame2.getContentPane().add(BorderLayout.SOUTH, panel2);
				frame2.getContentPane().add(BorderLayout.CENTER, v);
				frame2.setSize(Fz1, Fz2);
				frame2.setVisible(true);
				frame.setVisible(false); // 이전 창 꺼진다.

				yes.addActionListener(new ActionListener() { // 확인 누르면
					public void actionPerformed(ActionEvent event) {
						reader.play = 1; // 게임 플레이 상태가 된다.(flag 역할)
						frame3 = new JFrame();
						panel3 = new JPanel();
						panel3.setBackground(Color.DARK_GRAY);
						JButton select = new JButton("Select");
						JButton init = new JButton("Init");
						JButton rotatep = new JButton("Rotate Purple");
						JButton rotater = new JButton("Rotate Red");
						JButton rotateb = new JButton("Rotate Blue");
						JButton solution = new JButton("Solution");
						JButton move = new JButton("move");
						JButton laser = new JButton("Laser!!!");
						panel3.add(select);  // 카드 선택 창으로 돌아감.
						panel3.add(init);  // 토큰의 배치 초기화
						panel3.add(rotatep); // 토큰들의 회전 버튼  purple, red, blue
						panel3.add(rotater);  
						panel3.add(rotateb);
						panel3.add(solution);  // 솔루션 보여주는 창으로 연결.
						panel3.add(move);  // 솔루션에 맞는 배치 해준다.
						panel3.add(laser);  // 레이저 발사 버튼
						frame3.getContentPane().add(BorderLayout.SOUTH, panel3);
						frame3.getContentPane().add(BorderLayout.CENTER, v);
						frame3.setSize(Fz1, Fz2);
						frame3.setVisible(true);
						frame2.setVisible(false);

						move.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent event) {
								v.move = 1; // 움직인 모습을 보여주는 img draw
								if (ccc.cardNum == 1) { // 카드1번일 때
									
									ccc.B.x = 2;  // 솔루션에 따른 위치 설정.
									ccc.B.y = 4;
								}
								if (ccc.cardNum == 2) { // same..
									ccc.P.x = 5;
									ccc.P.y = 4;

								}
							}
						});
						select.addActionListener(new ActionListener() { // 카드 선택창으로 복귀
							public void actionPerformed(ActionEvent event) {
								v.laser = 0;
								v.move = 0;
								lsr.reset(); 
								ccc.cardNum = 0;  // 이전것들 다 reset..
								frame.setVisible(true);
								frame3.setVisible(false);
							}
						});
						init.addActionListener(new ActionListener() { // 토큰 재 배치
							public void actionPerformed(ActionEvent event) {
								v.move = 0;
								v.laser = 0;
								lsr.reset();  // 토큰 움직임과 레이저를 초기화
							}
						});
						rotatep.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent event) {
								reader.rotatePur = reader.rotatePur + 45;
								if (reader.rotatePur == 450)
									reader.rotatePur = 0;// 회전시 dir증가.
								if (reader.rotatePur / 90 == 1)
									ccc.P.dir = 2;
								if (reader.rotatePur / 180 == 1)
									ccc.P.dir = 4;
								if (reader.rotatePur / 270 == 1)
									ccc.P.dir = 1;
								if (reader.rotatePur / 360 == 1)
									ccc.P.dir = 3;
								if (reader.rotatePur == 0)
									ccc.P.dir = 3;
							} // 보라색 토큰의 회전에 따른 dir값 변화
						});
						rotater.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent event) {
								reader.rotateRe = reader.rotateRe + 45;
								if (reader.rotateRe == 450)
									reader.rotateRe = 0;// 회전시 dir증가.
								if (reader.rotateRe / 90 == 1)
									ccc.r.dir = 2;
								if (reader.rotateRe / 180 == 1)
									ccc.r.dir = 4;
								if (reader.rotateRe / 270 == 1)
									ccc.r.dir = 1;
								if (reader.rotateRe / 360 == 1)
									ccc.r.dir = 3;
								if (reader.rotateRe == 0)
									ccc.r.dir = 3;
							} // 빨강색 토큰의 회전에 따른 dir값 변화
						});
						rotateb.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent event) {
								reader.rotateBl = reader.rotateBl + 45;
								if (reader.rotateBl == 450)
									reader.rotateBl = 0;// 회전시 dir증가.
								if (reader.rotateBl / 90 == 1)
									ccc.B.dir = 2;
								if (reader.rotateBl / 180 == 1)
									ccc.B.dir = 4;
								if (reader.rotateBl / 270 == 1)
									ccc.B.dir = 1;
								if (reader.rotateBl / 360 == 1)
									ccc.B.dir = 3;
								if (reader.rotateBl == 0)
									ccc.B.dir = 3;
							} // 파랑색 토큰의 회전에 따른 dir값 변화
						});
						laser.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent event) {
								v.laser = 1; // 레이저 발사
							}
						});
						solution.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent event) {
								reader.sol = 1; // 솔루션 플래그 건다.-> solution img draw해준다.
								frame4 = new JFrame();
								frame4.getContentPane().add(
										BorderLayout.CENTER, v);
								frame4.setSize(Fz1, Fz2);
								frame4.setVisible(true);
								frame3.setVisible(false);
							}
						});

					}
				});
				no.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						v.mainw = 0; // 선택 안하면 메인으로 돌아간다.
						frame.setVisible(true);
						frame2.setVisible(false);
					}
				});

			}
		});
		fld.requestFocus(); // 커서위치를  맨 처음으로 위치 시킨다.
		frame.getContentPane().add(BorderLayout.EAST, panel);
		frame.getContentPane().add(BorderLayout.NORTH, fld);
		frame.getContentPane().add(BorderLayout.CENTER, v);
		frame.setSize(Fz1, Fz2);
		frame.setVisible(true);

	}

}