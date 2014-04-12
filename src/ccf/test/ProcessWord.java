package ccf.test;

public class ProcessWord {
	
	public String process(String msg){
		String r;
		r= msg.replaceAll("<","[").replace(">","]")
				.replaceAll("被就业", "就业");
		return r;
	}
	

}
