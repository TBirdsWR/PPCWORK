package design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class XMQ {

	public static void main(String[] args) {
		SN sn = new PJL(); 
		sn.pmy();
		sn.ppp();
        //创建一个InvocationHandler，描述我们希望代理者执行哪些操作  
        InvocationHandler invocationHandler = new MyInvocationHandler(sn);   
        //通过刚才创建的InvocationHandler，创建真正的代理者。第一个参数是类加载器，第二个参数是这个代理者实现哪些接口(与被代理者实现的是相同的接口)  
        SN snProxy = 
        		(SN)Proxy.newProxyInstance(sn.getClass().getClassLoader(),    
        				sn.getClass().getInterfaces(), invocationHandler);    
        snProxy.pmy();  
        snProxy.ppp();
	}
}
