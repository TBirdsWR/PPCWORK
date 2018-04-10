package reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Scanner;

public class Demo10 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("请输入类名:");
		String className = in.nextLine();
		try {
			Class cls = Class.forName(className);
			Object obj = cls.newInstance();
			
			Constructor[] cons = cls.getConstructors();
			for(Constructor con:cons){
				System.out.println(con);
			}
			System.out.println("constructor结束");
			Method[] methods = cls.getDeclaredMethods();
			for(Method method:methods){
				System.out.println(method);
				String mename = method.getName();
				
				int n = method.getParameterCount();
				
				Parameter[] parameters = method.getParameters();
				
				for(Parameter parameter:parameters){
					System.out.print(parameter);
					
				}
				System.out.println();
				
				System.out.println(n);
				if(mename.startsWith("test")){
					if(method.getAnnotation(Test.class)!=null){
						switch(n){
						case 1:method.invoke(obj, 1);break;
						case 2:method.invoke(obj, 2,3);break;
						default :method.invoke(obj);
						}
					}
					
				}
				Annotation[] ary = method.getAnnotations();
				System.out.println(Arrays.toString(ary));
			}
			System.out.println("method结束");
			Field[] fields = cls.getDeclaredFields();
			
			for(Field field:fields){
				field.setAccessible(true);
				System.out.print(field+" ");
				System.out.println(field.get(obj));
				
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("类未找到..");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		

	}

}
