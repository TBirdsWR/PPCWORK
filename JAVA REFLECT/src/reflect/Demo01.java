package reflect;


 /**
 * @author PPC
 *��ʾ��̬���ص�������
 */
public class Demo01 {

	public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException {
		//className �����ȫ��
		String className = "reflect.C1";
		Class cls = null;
		Class<C1> c = null;
		try {
			c = (Class<C1>) Class.forName(className);
			System.out.println(c.newInstance().f1());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

	}

}

interface Cls1{
	String f1();
}

class C1 implements Cls1{

	@Override
	public String f1() {
		System.out.println("c1");
		return "123";
	}
	
}