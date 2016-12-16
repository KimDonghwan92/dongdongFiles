
public class Token {// 토큰들의 필요 정보들을 담고있는 Token Class
	int dir; // 방향
	int x,y; // 좌표
	int fix; // 고정 유무
	int use, used; // 사용 할 토큰인지  // 사용 된 토큰인지(카드에 배치 되어있는 토큰인지)	
	public void set_use(int tmp){
		use = tmp;
		}
	public void set_used(int tmp){
		used = tmp;
		}
	public void set_fix(int tmp){
		fix= tmp;
		}
	
	public void set_dir(int tmp){
		dir = tmp;
		}
	public void set_xy(int tmp1, int tmp2){
		x = tmp1;
		y = tmp2;}}
