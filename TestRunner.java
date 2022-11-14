import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestRunner<T> {

    public void runTests(Class<T> clazz) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Inspector<T> inst = new Inspector<>(clazz);
        for (Method method : clazz.getDeclaredMethods()) {
            if(method.isAnnotationPresent(BeforeClass.class)) {
                method.invoke(inst.createInstance());
            }
        }
        for (Method method : clazz.getDeclaredMethods()) {
            if(method.isAnnotationPresent(Before.class)) {
                method.invoke(inst.createInstance());
            }
            if(method.isAnnotationPresent(Test.class) && method.getAnnotation(Test.class).enabled()) {
                System.out.println(method.getAnnotation(Test.class).name());
                method.invoke(inst.createInstance());
            }
            if(method.isAnnotationPresent(After.class)) {
                method.invoke(inst.createInstance());
            }
        }
        for (Method method : clazz.getDeclaredMethods()) {
            if(method.isAnnotationPresent(AfterClass.class)) {
                method.invoke(inst.createInstance());
            }
        }
    }
}
