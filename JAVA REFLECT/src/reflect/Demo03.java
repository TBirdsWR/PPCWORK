package reflect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Demo03 {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		System.out.println(" ‰»Î¿‡√˚:");
		String className  = console.nextLine();
		Class cls;
		try {
			cls = Class.forName(className);
			Method[] m = cls.getDeclaredMethods();
			System.out.println(Arrays.toString(m));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		}
		
		console.close();
	

	}

}
