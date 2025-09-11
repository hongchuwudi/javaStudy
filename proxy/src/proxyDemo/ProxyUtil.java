package proxyDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyUtil {
    //  创建一个明星对象的代理对象返回
    //  newproxy三个参数
    //  1.class加载器  去加载要生成的代理类
    //  2.指定代理类需要实现的接口
    //  3.用来指定代理取药如何代理(代理要做的事情)
    public static StarAction createProxy(Star s) {
        StarAction proxy = (StarAction) Proxy.newProxyInstance(
                ProxyUtil.class.getClassLoader(),
                s.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        /*
                        用来声明代理对象要干的事情。
                        参数一：proxy接收到代理对象本身（暂时用处不大）
                        参数二：method代表正在被代理的方法
                        参数三：args代表正在被代理的方法的参数
                         */
                        String methodName = method.getName();
                        if ("sing".equals(methodName)) System.out.println("准备花筒 交钱20w");
                        else if ("dance".equals(methodName)) System.out.println("准备场地");

                        //  真正干活的(把真正的明星对象叫过来干活)
                        Object result = method.invoke(s, args);
                        return result;
                    }
                }

        );
        return proxy;
    }
}
