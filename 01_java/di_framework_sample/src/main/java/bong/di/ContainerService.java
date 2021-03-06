package bong.di;

import java.util.Arrays;

public class ContainerService {

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
