/**
 * @Description:
 * @Author: wangzg
 * @Time: 2022/1/19
 */
public class HelloServiceImpl implements HelloService{
    @Override
    public String hello() {
        System.out.println("hello, 这是静态代理模式的实际对象的操作");
        return null;
    }
}
