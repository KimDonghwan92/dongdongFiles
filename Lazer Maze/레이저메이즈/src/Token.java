
public class Token {// ��ū���� �ʿ� �������� ����ִ� Token Class
	int dir; // ����
	int x,y; // ��ǥ
	int fix; // ���� ����
	int use, used; // ��� �� ��ū����  // ��� �� ��ū����(ī�忡 ��ġ �Ǿ��ִ� ��ū����)	
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
