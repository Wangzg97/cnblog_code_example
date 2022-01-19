/**
 * @Description:
 * @Author: wangzg
 * @Time: 2022/1/19
 */
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
