package sjq.bitcoin.context;

import sjq.bitcoin.logger.Logger;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Context {
    private static Map<Class, Object> instanceMap = new HashMap<Class, Object>();

    public synchronized static <T> T build(Class<T> clazz) {
        try {
            if (!instanceMap.containsKey(clazz)) {
                T instance = clazz.getConstructor().newInstance();
                instanceMap.put(clazz, instance);

                Field[] fields = instance.getClass().getDeclaredFields();
                for (Field field:fields) {
                    if (field.isAnnotationPresent(Autowire.class)) {
                        field.setAccessible(true);
                        field.set(instance, build(field.getType()));
                        field.setAccessible(false);
                    }
                }
            }
            return (T)instanceMap.get(clazz);
        } catch (Exception e) {
            Logger.fatal("can not initiate instance with class:%s, exception:%s", clazz.getName(), e);
        }
        return null;
    }
}
