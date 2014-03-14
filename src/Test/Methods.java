package Test;

public class Methods {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(digui(digui2(3)));

	}
	public static int digui(int n){
		if(n==1){
			return 1;
		}
		else{
			return n*digui(n-1);
		}
	}
	public static int  digui2(int n){
		if(n==1){
			return 1;
		}
		else{
			return n*digui(n-1);
	
		}
}
}
