import java.lang.reflect.Proxy;

/**
 * @Description:
 * @Author: wangzg
 * @Time: 2022/1/19
 */
public class JdkProxyFactory {
    public static Object getProxy(Object object) {
        return Proxy.newProxyInstance(
                object.getClass().getClassLoader(), //被代理类的类加载器，用于加载代理对象
                object.getClass().getInterfaces(), //被代理类实现的一些接口
                new JdkProxy(object) // 实现了 InvocationHandler 接口的对象
        );
    }
}
