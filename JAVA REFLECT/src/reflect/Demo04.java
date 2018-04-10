package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Demo04 {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		System.out.println(" ‰»Î¿‡√˚:");
		String className  = console.nextLine();
		Class cls;
		try {
			cls = Class.forName(className);
			Field[] f = cls.getDeclaredFields();
			System.out.println(Arrays.toString(f));
			Field[] f1 = cls.getFields();
			System.out.println(Arrays.toString(f1));
			Constructor[] con = cls.getConstructors();
			System.out.println(Arrays.toString(con));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		}
		
		console.close();
	

	
	}

}
