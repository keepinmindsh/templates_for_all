package bong.lines.proxy.inhancer;

import bong.lines.proxy.domain.Sample;
import bong.lines.proxy.domain.SampleService;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibInhancer {
    public void test1(){
        MethodInterceptor handler = new MethodInterceptor() {
            SampleService sampleService = new Sample();

            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                if(method.getName().equals("something")){
                    System.out.println("Test! Before");
                    Object invoke = method.invoke(sampleService, objects);
                    System.out.println("Test! After");
                    return invoke;
                }

                return method.invoke(sampleService, objects);
            }
        };

        SampleService sampleService = (SampleService) Enhancer.create(SampleService.class, handler);

        sampleService.sample();
    }
}
