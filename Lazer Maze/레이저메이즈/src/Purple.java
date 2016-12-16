import java.util.Observable;
import java.util.Observer;


public class Purple extends Token  {
	int isTarget;
	public void set_isTarget(int isT){
		isTarget = isT;  // 타겟 토큰인지 아닌지 구분.
	}	
	public void move(){}
}
