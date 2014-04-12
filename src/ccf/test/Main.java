package ccf.test;


public class Main {
	public static void main(String[] args){
		String msg = "测试，<script>,被就业，敏感，等等。。艾斯黛拉覅";
		ProcessWord mp = new ProcessWord();
		String r = mp.process(msg);
		System.out.print(r);
	}
}
