/**
 * @Description:
 * @Author: wangzg
 * @Time: 2022/1/19
 */
public class TestProxy {
    public static void main(String[] args) {
        HelloService helloService = (HelloService) CglibProxyFactory.getProxy(HelloService.class);
        helloService.hello();
    }
}
