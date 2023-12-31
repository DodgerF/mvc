package event;

import java.util.HashMap;
import java.util.Map;

public enum ServiceLocator {
    INSTANCE;
    private Map<Class<?>, Class<?>> services = new HashMap<>();
    private Map<Class<?>, Object> cashe = new HashMap<>();
    public <T> void registerService(Class<T> service, Class<? extends T> provider) {
        services.put(service, provider);
    }

    public <T> T getService (Class<T> type) {
        Class<?> provider = services.get(type);
        try {
            Object instance;
            if (cashe.containsKey(type)) {
                instance = cashe.get(type);
            }
            else {
                instance = provider.getConstructor().newInstance();
                cashe.put(type, instance);
            }
            return type.cast(instance);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw new IllegalArgumentException("Service is not available");
        }
    }
}
