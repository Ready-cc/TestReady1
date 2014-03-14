package Test;

public class TestToString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dog dog = new Dog();
		System.out.print(dog);
	}

}
class Dog{
	public String toString(){
		return "dog";
	}
	
}