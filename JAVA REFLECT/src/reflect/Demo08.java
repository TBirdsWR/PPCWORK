package reflect;

import java.lang.reflect.Method;
import java.util.Scanner;

public class Demo08 {

	public static void main(String[] args) 
	throws Exception {
		Scanner in = new Scanner(System.in);
		System.out.println(" ‰»Î¿‡√˚:");
		String className = in.nextLine();
		Class cls = Class.forName(className);
		Method[] methods = cls.getDeclaredMethods();
		Object obj = cls.newInstance();
		for(Method method:methods){
			System.out.println(method);
			if(method.getAnnotation(Test.class)!=null){
				if(method.getAnnotation(Ignore.class)==null){
					method.invoke(obj);
				}
			}
		}

	}

}
