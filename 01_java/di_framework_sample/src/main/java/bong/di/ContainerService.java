package bong.di;

import java.util.Arrays;

public class ContainerService {

    // TODO - 아래의 구성에 대해서 DI 에 대한 개념을 이해하기!
    public static <T> T getObject(Class<T> classType){
        T instance = getNewInstance(classType);

        Arrays.stream(classType.getDeclaredFields()).forEach(f -> {
            if(f.getAnnotation(Inject.class) != null){
                Object fieldInstance = getNewInstance(f.getType());

                f.setAccessible(true);

                try {
                    f.set(instance, fieldInstance);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return instance;
    }

    private static <T> T getNewInstance(Class<T> classType) {
        try{
            return classType.getConstructor(null).newInstance();
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }
}
