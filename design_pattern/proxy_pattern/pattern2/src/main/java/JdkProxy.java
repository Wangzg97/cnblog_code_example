import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: wangzg
 * @Time: 2022/1/19
 */
public class JdkProxy implements InvocationHandler {

    //需要代理的真实对象
    private final Object object;

    public JdkProxy(Object object) {
        this.object = object;
    }

    //参数说明
    //Object proxy：动态生成的代理类，代理原生对象
    //Method method：代理类的方法，对应于被代理的原生对象的方法method
    //Object[] args：method方法的参数
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk代理对象在实际对象操作之前的增强方法");
        method.invoke(object, args); //调用原生对象的方法
        System.out.println("jdk代理对象在实际对象操作后前的增强方法");
        return null;
    }
}
