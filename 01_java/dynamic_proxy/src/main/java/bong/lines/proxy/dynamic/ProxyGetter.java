package bong.lines.proxy.dynamic;

import bong.lines.proxy.domain.Sample;
import bong.lines.proxy.domain.SampleService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyGetter {

    // Proxy를 이용한 Dynamic Proxy를 처리한다.
    SampleService sample = (SampleService) Proxy.newProxyInstance(SampleService.class.getClassLoader(), new Class[]{ SampleService.class},
            new InvocationHandler() {

                SampleService sampleService = new Sample();

                @Override
                public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                    System.out.println("Test! Before");
                    Object invoke = method.invoke(sampleService, objects);
                    System.out.println("Test! After");

                    return invoke;
                }
            });


    public void test(){
        sample.sample();
    }

}
