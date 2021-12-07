# JPA의 핵심 - Proxy라는 클래스에 의해서 시작한다. 

# Proxy - 대리/대리인

Proxy는 실제 오브젝트를 감싸 대리인 처럼 실행될 수 있다. 실제 객체에는 본인의 역할만 유지한채 추가 기능을 실제 객체가 아닌 실제 실행할 객체를 둘러싼 범위에서 실행할 수 있게 하는 것.  

 - Real Object를 감싸는 Proxy 객체가 Real Object를 호출하면사 추가적인 작업을 할 수 있다.

동적으로 런타임에 생성해내는 방법은 Dynamic Proxy - Dynamic Reflection 에 대해서 이해해야함. 

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