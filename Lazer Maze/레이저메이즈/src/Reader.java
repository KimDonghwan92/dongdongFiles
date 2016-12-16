import java.io.*;
import java.util.*;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Reader {
	
	private String filename;
	public String[] tempStr = new String[7]; // txt���Ϸ� �о���鼭 Ȯ�� �ϱ� ���� ��������ϴ�.
	private ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>();;
	static int rotatePur= 0;// ����� ��ū�� ȸ�� ��ȭ��
	static int rotateRe= 0;// ����� ��ū�� ȸ�� ��ȭ��
	static int rotateBl= 0;// ����� ��ū�� ȸ�� ��ȭ��
	static int sol = 0; // �ַ���� ���� �ٰ����� �ƴ���.
	static int play = 0; // ���� ȭ������ �ƴ���.
	
	public void setcard(String filename) {
		BufferedReader br = null;
		BufferedWriter bw = null;
		String line = null;
		Card card = new Card();
		card.resetCard(); // �� �� �о�� �� ���� ���� ������ �� reset.
		
		
		for (ChangeListener l : this.listeners) {  // ī�尡 ���� ���õ� ������  ������ �ٲ�.
			l.stateChanged(new ChangeEvent(this));} 
		
		try {
			br = new BufferedReader(new FileReader(filename)); // txt������  �о� ������ ����.
			while ((line = br.readLine()) != null) {
				String[] arr = line.split(":");
				if (arr[0].equals("ī���ȣ")){
					card.set_cardimg(Integer.parseInt(arr[1])); // ī�� ��ȣ ����
					tempStr[0] = arr[0] + arr[1];
				}
				if (arr[0].equals("����")) {
					card.level = Integer.parseInt(arr[1]); // ���� ����
					tempStr[1] = arr[0] + arr[1];
				}
				if (arr[0].equals("Ÿ�� ����")) {
					card.targetNum = Integer.parseInt(arr[1]); // Ÿ�� ���� ����
					tempStr[2] = arr[0] + arr[1];
				}
				if (arr[0].equals("����� ��ū�� ����")) {
					card.use_tokenNum = Integer.parseInt(arr[1]); // ��� �� ��ū ���� ����
					tempStr[3] = arr[0] + arr[1];
				}
				if (arr[0].equals("����� ��ū�� Ÿ��")) {
					card.use_token = arr[1];
					tempStr[4] = arr[0] + arr[1]; // ��� �� ��ū Ÿ�� ����
				    card.use_setting();
				}
				if (arr[0].equals("��ġ�� ��ū�� ����")){
					card.used_tokenNum = Integer.parseInt(arr[1]); // ��ġ�� ��ū ���� ����
					tempStr[5] = arr[0] + arr[1];
				}
				if (arr[0].equals("��ġ�� ��ū�� ��ġ�� ����� ��������")) {
					card.used_token = arr[1];
					tempStr[6] = arr[0] + arr[1]; // ��ū�� ������ ����
					card.used_setting();
				}
			}
		for(int i = 0 ; i < 7 ; i ++)
		System.out.println(tempStr[i]);
		} catch (IOException e) {
			System.out.println("Error : ������ ã�� �� �����ϴ�.");
		}
	}

	public void addChangeListener(ChangeListener l) {  // �ǽð����� ī�� ���� ������Ʈ
		this.listeners.add(l);
		}
	public void removeChangeListener(ChangeListener l) {
		this.listeners.remove(l);
		}
	public ChangeListener[] getChangeListeners() {
		return (ChangeListener[])(this.listeners.toArray());
		}
}