/**
 * @Description:
 * @Author: wangzg
 * @Time: 2022/1/19
 */
public class Test {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        HelloProxy helloProxy = new HelloProxy(helloService);
        helloProxy.hello();
    }
}
