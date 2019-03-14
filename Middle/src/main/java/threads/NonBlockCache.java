package threads;

import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Класс для неблокирующего кэша.
 * @author Николай Разилов
 * @since 14.03.2019
 */
@ThreadSafe
public class NonBlockCache {
    private static Map<Integer, Base> map = new ConcurrentHashMap<>();

    public Base add(Base base) {
        return map.putIfAbsent(base.getId(), base);
    }

    public Base delete(Base base) {
        return map.remove(base.getId());
    }

    public Base update(Base base) {
        return map.computeIfPresent(base.getId(), (integer, baseParam) -> {
            if (base.getVersion() != baseParam.getVersion()) {
                throw new OptimisticException("Разные версии");
            }
            baseParam.setVersion(baseParam.getVersion() + 1);
            return baseParam;
        });
    }

    public Iterator<Map.Entry<Integer, Base>> iterator() {
        return map.entrySet().iterator();
    }

    public int size() {
        return map.size();
    }
}