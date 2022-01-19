import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: wangzg
 * @Time: 2022/1/19
 */
public class CglibMethodInterceptor implements MethodInterceptor {
    //拦截被代理类的方法
    //参数说明
    //Object o：被代理的对象
    //Method method：被拦截的方法
    //Object[] objects：方法参数
    //MethodProxy methodProxy：方法代理，用于调用原始方法
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib代理对象在实际对象操作之前的增强方法");
        methodProxy.invokeSuper(o, objects);
        System.out.println("cglib代理对象在实际对象操作之前的增强方法");
        return null;
    }
}
