package reflect;

import java.lang.reflect.Field;
import java.util.Scanner;

public class Demo09 {

	public static void main(String[] args)
	throws Exception {
		Scanner in = new Scanner(System.in);
		while(true){
			System.out.print("输入类名:");
			String className = in.nextLine();
			Class cls = Class.forName(className);
			Object obj = cls.newInstance();
			//输入属性名
			System.out.print("属性名:");
			String name = in.nextLine();
			//getfield 查找属性
			Field fld = cls.getDeclaredField(name);
			//打开属性的访问权限
			fld.setAccessible(true);
			Object value = fld.get(obj);
			System.out.println(value);
			 
		}
		

	}

}
