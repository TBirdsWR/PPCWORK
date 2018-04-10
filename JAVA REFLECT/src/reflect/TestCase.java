package reflect;

public class TestCase {
	@Test
	public void hello(){
		System.out.println("hello world");
	}
	
	@Test
	@Ignore
	public void kitty(){
		System.out.println("hello kitty");
	}
	
}
