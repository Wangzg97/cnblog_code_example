## 代理模式

使用代理对象来代替对真实对象的访问，实现扩展目标对象的功能，即可以在对象操作的行为前后添加自定义操作

### 静态代理

#### 特点
1. 需要针对每一个目标类（接口的是实现类）创建一个代理类，并实现其接口对应的方法
2. 接口中增加方法，目标类和代理类中都要实现相应的方法，比较麻烦

#### 示例
1. 定义接口及其实现类
```java
public interface HelloService {
    String hello();
}
```
```java
public class HelloServiceImpl implements HelloService{
    @Override
    public String hello() {
        System.out.println("hello, 这是静态代理模式的实际对象的操作");
        return null;
    }
}
```
2. 创建一个代理类实现这个接口,将目标对象注入这个代理类，然后在代理类实现的接口方法里调用目标对象的方法，可以在目标对象执行的方法前后添加自定义逻辑。
```java
public class HelloProxy implements HelloService{

    private final HelloService helloService;

    public HelloProxy(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public String hello() {
        System.out.println("代理对象在实际对象操作之前的增强方法");
        helloService.hello();
        System.out.println("代理对象在实际对象操作之后的增强方法");
        return null;
    }
}
```
3. 测试
```java
public class Test {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        HelloProxy helloProxy = new HelloProxy(helloService);
        helloProxy.hello();
    }
}
```

### 动态代理

#### JDK动态代理

##### 特点
1. 只能代理实现了接口的类或者直接代理接口

##### 示例
1. 定义一个接口及其实现类
```java
public interface HelloService {
    String hello();
}
```
```java
public class HelloServiceImpl implements HelloService{
    @Override
    public String hello() {
        System.out.println("hello, 这是JDK动态代理模式的实际对象的操作");
        return null;
    }
}
```
2. 定义一个JDK动态代理类，实现InvocationHandler接口，重写其中的invoke方法，在invoke方法调用目标对象的方法，可以在目标对象执行的方法前后添加自定义逻辑。
```java
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
```

4. 创建代理对象，使用Proxy.newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)创建代理对象
```java
public class JdkProxyFactory {
    public static Object getProxy(Object object) {
        return Proxy.newProxyInstance(
                object.getClass().getClassLoader(), //被代理类的类加载器，用于加载代理对象
                object.getClass().getInterfaces(), //被代理类实现的一些接口
                new JdkProxy(object) // 实现了 InvocationHandler 接口的对象
        );
    }
}
```

5. 测试
```java
public class TestProxy {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        HelloService service = (HelloService) JdkProxyFactory.getProxy(helloService);
        service.hello();
    }
}
```
#### CGLIB动态代理

##### 特点
1. 可以代理未实现任何接口的类
2. 通过生成一个被代理类的子类来拦截被代理类的方法调用

##### 示例
1. 定义一个类
```java
public class HelloService{

    public String hello() {
        System.out.println("hello, 这是cglib代理模式的实际对象的操作");
        return null;
    }
}
```
2. 自定义类实现 MethodInterceptor 接口并重写 intercept 方法，用于拦截方法做增强处理
```java
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
```

3. 通过 Enhancer 类的 create()创建代理类
```java
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
```

4. 测试
```java
public class TestProxy {
    public static void main(String[] args) {
        HelloService helloService = (HelloService) CglibProxyFactory.getProxy(HelloService.class);
        helloService.hello();
    }
}
```

### 参考链接
1. https://javaguide.cn/java/basis/%E4%BB%A3%E7%90%86%E6%A8%A1%E5%BC%8F%E8%AF%A6%E8%A7%A3/