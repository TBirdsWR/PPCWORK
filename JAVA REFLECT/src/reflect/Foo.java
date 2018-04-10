package reflect;

public class Foo {
	public String name = "Foo";
	public int id = 1;
	private double d = 0.09;
	
	static{
		System.out.println("Foo loader");
	}
	public void f(){
		System.out.println("Foo f()");
	}
	private void f1(int n){
		System.out.println("Foo f(int n)");
	}
	
	public void test(int n){
		System.out.println("¥Ú”°Foo test(int n)");
	}
	@Test
	public String test(int n,int m){
		System.out.println("¥Ú”°Foo test(int n,int m)");
		return new String(""+n+m);
	}
	
	public void test(){
		System.out.println("¥Ú”°Foo test()");
	}
	
	public Foo() {
		
	}
	public Foo(String name, int id) {
		this.name = name;
		this.id = id;
	}

	
}


