package design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

	private Object target; //既然要做代理，我们必须知道我们是给谁做代理，这里的obj就是被代理者。   
    
    MyInvocationHandler() {    
        super();    
    }    
    
    MyInvocationHandler(Object target) {    
        super();    
        this.target = target;    
    } 
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		  
        System.out.println("++++++before " + method.getName() + "++++++");    
        Object result = method.invoke(target, args);    
        System.out.println("++++++after " + method.getName() + "++++++");    
        return result;
	}

}
