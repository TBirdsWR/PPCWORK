package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Demo06 {

	public static void main(String[] args) 
	throws Exception{
		Scanner console = new Scanner(System.in);		
		Class cls = Class.forName(console.nextLine());
		Method[] m = cls.getDeclaredMethods();
		Object obj = cls.newInstance();
		for(Method m1:m){
			String name = m1.getName();
			if(name.startsWith("test")){
				System.out.println(m1);
				int n = m1.getParameterTypes().length;
				//System.out.println(n);
				if(n==0){
					System.out.println(m1.invoke(obj));
				}if(n==1){
					System.out.println(m1.invoke(obj,1));
				}if(n==2){
					System.out.println(m1.invoke(obj,1,2));
				}							
			}
		}
	}

}
