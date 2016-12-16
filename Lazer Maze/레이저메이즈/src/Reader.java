import java.io.*;
import java.util.*;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Reader {
	
	private String filename;
	public String[] tempStr = new String[7]; // txt파일로 읽어오면서 확인 하기 위해 만들었습니다.
	private ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>();;
	static int rotatePur= 0;// 보라색 토큰의 회전 변화율
	static int rotateRe= 0;// 보라색 토큰의 회전 변화율
	static int rotateBl= 0;// 보라색 토큰의 회전 변화율
	static int sol = 0; // 솔루션을 보여 줄것인지 아닌지.
	static int play = 0; // 게임 화면인지 아닌지.
	
	public void setcard(String filename) {
		BufferedReader br = null;
		BufferedWriter bw = null;
		String line = null;
		Card card = new Card();
		card.resetCard(); // 매 번 읽어올 때 마다 이전 정보들 다 reset.
		
		
		for (ChangeListener l : this.listeners) {  // 카드가 새로 선택될 때마다  내용이 바뀜.
			l.stateChanged(new ChangeEvent(this));} 
		
		try {
			br = new BufferedReader(new FileReader(filename)); // txt파일을  읽어 정보를 저장.
			while ((line = br.readLine()) != null) {
				String[] arr = line.split(":");
				if (arr[0].equals("카드번호")){
					card.set_cardimg(Integer.parseInt(arr[1])); // 카드 번호 세팅
					tempStr[0] = arr[0] + arr[1];
				}
				if (arr[0].equals("레벨")) {
					card.level = Integer.parseInt(arr[1]); // 레벨 세팅
					tempStr[1] = arr[0] + arr[1];
				}
				if (arr[0].equals("타겟 갯수")) {
					card.targetNum = Integer.parseInt(arr[1]); // 타겟 갯수 세팅
					tempStr[2] = arr[0] + arr[1];
				}
				if (arr[0].equals("사용할 토큰의 갯수")) {
					card.use_tokenNum = Integer.parseInt(arr[1]); // 사용 할 토큰 갯수 세팅
					tempStr[3] = arr[0] + arr[1];
				}
				if (arr[0].equals("사용할 토큰의 타입")) {
					card.use_token = arr[1];
					tempStr[4] = arr[0] + arr[1]; // 사용 할 토큰 타입 세팅
				    card.use_setting();
				}
				if (arr[0].equals("배치된 토큰의 갯수")){
					card.used_tokenNum = Integer.parseInt(arr[1]); // 배치된 토큰 갯수 세팅
					tempStr[5] = arr[0] + arr[1];
				}
				if (arr[0].equals("배치된 토큰과 위치와 방향과 고정유무")) {
					card.used_token = arr[1];
					tempStr[6] = arr[0] + arr[1]; // 토큰의 정보들 세팅
					card.used_setting();
				}
			}
		for(int i = 0 ; i < 7 ; i ++)
		System.out.println(tempStr[i]);
		} catch (IOException e) {
			System.out.println("Error : 파일을 찾을 수 없습니다.");
		}
	}

	public void addChangeListener(ChangeListener l) {  // 실시간으로 카드 정보 업데이트
		this.listeners.add(l);
		}
	public void removeChangeListener(ChangeListener l) {
		this.listeners.remove(l);
		}
	public ChangeListener[] getChangeListeners() {
		return (ChangeListener[])(this.listeners.toArray());
		}
}