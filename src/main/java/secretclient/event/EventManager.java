package secretclient.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import secretclient.SecretClient;

@SuppressWarnings("unchecked")
public class EventManager {
    // EventClass (e.g. TickEvent), EventListener
    private HashMap<Class<? extends Event>, ArrayList<EventListener>> registeredListeners = new HashMap<>();

    public void register(Object obj, Object... args) {
        for (Method m : obj.getClass().getDeclaredMethods()) {
            if (m.isAnnotationPresent(EventHandler.class) && m.getParameterCount() != 0) {
                Class<? extends Event> type = (Class<? extends Event>) m.getParameters()[0].getType();
                if (registeredListeners.get(type) == null)
                    registeredListeners.put(type, new ArrayList<EventListener>());
                registeredListeners.get(type).add(new EventListener(obj, args));
            }
        }
    }

    public void post(Object obj) {
        ArrayList<EventListener> listeners = registeredListeners.get(obj.getClass());
        if (listeners == null)
            return;
        for (EventListener o : listeners) {
            for (Method m : o.source.getClass().getMethods()) {
                if (m.isAnnotationPresent(EventHandler.class)) {
                    try {
                        m.invoke(o.source, obj);
                    } catch (Exception err) {
                        SecretClient.LOGGER.error("Error on event post: " + err + "\nCaused By: " + err.getCause());
                    }
                }
            }
        }

    }
}
