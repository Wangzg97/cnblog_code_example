/**
 * @Description:
 * @Author: wangzg
 * @Time: 2022/1/19
 */
public class TestProxy {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        HelloService service = (HelloService) JdkProxyFactory.getProxy(helloService);
        service.hello();
    }
}
