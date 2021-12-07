package bong.lines.proxy.bytebuddy;

import bong.lines.proxy.domain.Sample;
import bong.lines.proxy.domain.SampleService;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class BuddyGetter {

    public void test1() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<? extends SampleService> proxyClass = new ByteBuddy().subclass(SampleService.class)
                .method(named("sample"))
                .intercept(InvocationHandlerAdapter.of(new InvocationHandler() {

                    SampleService sampleService = new Sample();

                    @Override
                    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                        System.out.println("Test! Before");
                        Object invoke = method.invoke(sampleService, objects);
                        System.out.println("Test! After");
                        return invoke;
                    }
                }))
                .make()
                .load(SampleService.class.getClassLoader())
                .getLoaded();

        SampleService sampleService = proxyClass.getConstructor(null).newInstance();

        sampleService.sample();
    }
}
