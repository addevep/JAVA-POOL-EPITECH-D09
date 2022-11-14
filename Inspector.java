import java.lang.reflect.*;
public class Inspector<T> {
    public Class<T> inspectedClass;

    Inspector(Class<T> inspector) {
        inspectedClass = inspector;
    }
    public void displayInformations() {
        System.out.println("Information of the " + '"' +inspectedClass.getName() + '"' + " class:");
        System.out.println("Superclass: " + inspectedClass.getSuperclass().getName());
        System.out.println(inspectedClass.getDeclaredMethods().length + " methods:");
        for (Method key: inspectedClass.getDeclaredMethods()) {
            System.out.println("- " + key.getName());
        }
        System.out.println(inspectedClass.getDeclaredFields().length + " fields:");
        for (Field key: inspectedClass.getDeclaredFields()) {
            System.out.println("- " + key.getName());
        }
    }
    public T createInstance() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return inspectedClass.getDeclaredConstructor().newInstance();
    }
}
