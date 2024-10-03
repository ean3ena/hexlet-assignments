package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage {
    Map<String, String> kv;

    public InMemoryKV(Map<String, String> kv) {
        this.kv = new HashMap<>(kv);
    }

    @Override
    public void set(String key, String value) {
        this.kv.put(key, value);
    }

    @Override
    public void unset(String key) {
        this.kv.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        return kv.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(kv);
    }
}
// END
