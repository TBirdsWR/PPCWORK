package reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Demo07 {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		System.out.println("��������:");
		String className = in.nextLine();
		Class cls = Class.forName(className);
		Method[] methods = cls.getDeclaredMethods();
		for(Method method:methods){
			System.out.println(method);
			//��ȡ�����ϱ�ǵ�ȫ��ע��
			Annotation[] ary = method.getAnnotations();
			System.out.println(Arrays.toString(ary));
			System.out.println(Arrays.toString(ary));
		}

	}

}
