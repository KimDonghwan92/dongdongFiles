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
	JFrame frame; // ���� â
	JFrame frame2; // ī�� ���� â
	JFrame frame3; // ���� �÷��� â
	JFrame frame4; // solution â
	JPanel panel; // ���� �г�
	JPanel panel2; // ī�� ���� yes or no �г�
	JPanel panel3; // select, solution, init, rotate�� ����ִ� �г�

	int Fz1 = 800;
	int Fz2 = 720; // ������ ������

	public static void main(String[] args) {
		Board m = new Board();
		m.go();// GUI â ����.
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
		JButton card2 = new JButton("CARD 2"); // ī�� ���� �����ִ� ��ư

		panel.add(card1);
		panel.add(card2); // main â���� ī�� ������ �����ִ� �г�.

		fld.addActionListener(new ActionListener() { // ���� �Է�â�� �Է� ���� ��.
			public void actionPerformed(ActionEvent event) {
				v.mainw = 1;  // boardviewer����  main img�� ������ flag
				frame2 = new JFrame();
				panel2 = new JPanel();
				panel2.setBackground(Color.DARK_GRAY);
				JButton yes = new JButton("yes"); // ī�� Ȯ�� ��ư
				JButton no = new JButton("no"); // ī�� ��� ��ư
				panel2.add(yes);
				panel2.add(no);

				if (fld.getText().equals("1"))
					reader.setcard("src/card_1.txt"); // �Է��� ���ڰ� 1 �̶�� 1�� ī�带
														// �о�´�.
				if (fld.getText().equals("2"))
					reader.setcard("src/card_2.txt"); // same..

				frame2.getContentPane().add(BorderLayout.SOUTH, panel2);
				frame2.getContentPane().add(BorderLayout.CENTER, v);
				frame2.setSize(Fz1, Fz2);
				frame2.setVisible(true);
				frame.setVisible(false); // ���� â ������.

				yes.addActionListener(new ActionListener() { // Ȯ�� ������
					public void actionPerformed(ActionEvent event) {
						reader.play = 1; // ���� �÷��� ���°� �ȴ�.(flag ����)
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
						panel3.add(select);  // ī�� ���� â���� ���ư�.
						panel3.add(init);  // ��ū�� ��ġ �ʱ�ȭ
						panel3.add(rotatep); // ��ū���� ȸ�� ��ư  purple, red, blue
						panel3.add(rotater);  
						panel3.add(rotateb);
						panel3.add(solution);  // �ַ�� �����ִ� â���� ����.
						panel3.add(move);  // �ַ�ǿ� �´� ��ġ ���ش�.
						panel3.add(laser);  // ������ �߻� ��ư
						frame3.getContentPane().add(BorderLayout.SOUTH, panel3);
						frame3.getContentPane().add(BorderLayout.CENTER, v);
						frame3.setSize(Fz1, Fz2);
						frame3.setVisible(true);
						frame2.setVisible(false);

						move.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent event) {
								v.move = 1; // ������ ����� �����ִ� img draw
								if (ccc.cardNum == 1) { // ī��1���� ��
									
									ccc.B.x = 2;  // �ַ�ǿ� ���� ��ġ ����.
									ccc.B.y = 4;
								}
								if (ccc.cardNum == 2) { // same..
									ccc.P.x = 5;
									ccc.P.y = 4;

								}
							}
						});
						select.addActionListener(new ActionListener() { // ī�� ����â���� ����
							public void actionPerformed(ActionEvent event) {
								v.laser = 0;
								v.move = 0;
								lsr.reset(); 
								ccc.cardNum = 0;  // �����͵� �� reset..
								frame.setVisible(true);
								frame3.setVisible(false);
							}
						});
						init.addActionListener(new ActionListener() { // ��ū �� ��ġ
							public void actionPerformed(ActionEvent event) {
								v.move = 0;
								v.laser = 0;
								lsr.reset();  // ��ū �����Ӱ� �������� �ʱ�ȭ
							}
						});
						rotatep.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent event) {
								reader.rotatePur = reader.rotatePur + 45;
								if (reader.rotatePur == 450)
									reader.rotatePur = 0;// ȸ���� dir����.
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
							} // ����� ��ū�� ȸ���� ���� dir�� ��ȭ
						});
						rotater.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent event) {
								reader.rotateRe = reader.rotateRe + 45;
								if (reader.rotateRe == 450)
									reader.rotateRe = 0;// ȸ���� dir����.
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
							} // ������ ��ū�� ȸ���� ���� dir�� ��ȭ
						});
						rotateb.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent event) {
								reader.rotateBl = reader.rotateBl + 45;
								if (reader.rotateBl == 450)
									reader.rotateBl = 0;// ȸ���� dir����.
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
							} // �Ķ��� ��ū�� ȸ���� ���� dir�� ��ȭ
						});
						laser.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent event) {
								v.laser = 1; // ������ �߻�
							}
						});
						solution.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent event) {
								reader.sol = 1; // �ַ�� �÷��� �Ǵ�.-> solution img draw���ش�.
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
						v.mainw = 0; // ���� ���ϸ� �������� ���ư���.
						frame.setVisible(true);
						frame2.setVisible(false);
					}
				});

			}
		});
		fld.requestFocus(); // Ŀ����ġ��  �� ó������ ��ġ ��Ų��.
		frame.getContentPane().add(BorderLayout.EAST, panel);
		frame.getContentPane().add(BorderLayout.NORTH, fld);
		frame.getContentPane().add(BorderLayout.CENTER, v);
		frame.setSize(Fz1, Fz2);
		frame.setVisible(true);

	}

}