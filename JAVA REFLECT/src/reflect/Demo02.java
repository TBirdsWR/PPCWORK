package reflect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Demo02 {

	public static void main(String[] args) {

		Scanner console = new Scanner(System.in);
		System.out.println(" ‰»Î¿‡√˚:");
		String className  = console.nextLine();
		Class cls;
		try {
			cls = Class.forName(className);
			Object obj = cls.newInstance();
			System.out.println(obj);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
