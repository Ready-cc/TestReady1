package Test;

public class ProControl {
	int result = 0;
	public void testif(int i,int j){
		if(i>j){
			result = +1;
			}else if(i<j)
			result = -1;
		else result = 0;
	}
	public static void main(String[] args){
		ProControl pc = new ProControl();
		pc.testif(1,2);
	}

}
