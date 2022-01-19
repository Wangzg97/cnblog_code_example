import net.sf.cglib.proxy.Enhancer;

/**
 * @Description:
 * @Author: wangzg
 * @Time: 2022/1/19
 */
public class CglibProxyFactory {
    public static Object getProxy(Class<?> clazz) {
        //创建动态增强代理类
        Enhancer enhancer = new Enhancer();
        //设置类加载器
        enhancer.setClassLoader(clazz.getClassLoader());
        //设置被代理类
        enhancer.setSuperclass(clazz);
        //设置方法拦截器
        enhancer.setCallback(new CglibMethodInterceptor());

        return enhancer.create();
    }
}
