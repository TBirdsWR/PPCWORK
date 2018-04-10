package reflect;

import java.lang.reflect.Field;
import java.util.Scanner;

public class Demo09 {

	public static void main(String[] args)
	throws Exception {
		Scanner in = new Scanner(System.in);
		while(true){
			System.out.print("��������:");
			String className = in.nextLine();
			Class cls = Class.forName(className);
			Object obj = cls.newInstance();
			//����������
			System.out.print("������:");
			String name = in.nextLine();
			//getfield ��������
			Field fld = cls.getDeclaredField(name);
			//�����Եķ���Ȩ��
			fld.setAccessible(true);
			Object value = fld.get(obj);
			System.out.println(value);
			 
		}
		

	}

}
