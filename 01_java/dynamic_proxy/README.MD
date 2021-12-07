# JPA의 핵심 - Proxy라는 클래스에 의해서 시작한다. 

# Proxy - 대리/대리인

Proxy는 실제 오브젝트를 감싸 대리인 처럼 실행될 수 있다. 실제 객체에는 본인의 역할만 유지한채 추가 기능을 실제 객체가 아닌 실제 실행할 객체를 둘러싼 범위에서 실행할 수 있게 하는 것.  

 - Real Object를 감싸는 Proxy 객체가 Real Object를 호출하면사 추가적인 작업을 할 수 있다.

 - 동적으로 런타임에 생성해내는 방법은 Dynamic Proxy - Dynamic Reflection 에 대해서 이해해야함.    

 - Java 내의 Proxy는 반드시 Interface로만 정의해야 사용이 가능하다.  

 - Private 생성자가 존재하거나 final을 정의한 클래스에서는 CGLib, ByteBuddy와 같은 Proxy를 사용할 수 없다.   

```java

class Sample {
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
    
    
}

```

# CGLib 

```java

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

```

# ByteBuddy

```java

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

```

# 다이나믹 프록시 사용처 

- 스프링 데이터 JPA
- 스프링 AOP
- Mockito
- 하이버네이트 lazy initialization
- ...
