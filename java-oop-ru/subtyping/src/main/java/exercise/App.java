package exercise;

import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Set;

// BEGIN
public class App {

    public static void swapKeyValue(KeyValueStorage storage) {
        for (Map.Entry<String, String> entry : storage.toMap().entrySet()) {
            var key = entry.getKey();
            storage.set(entry.getValue(), key);
            storage.unset(key);
        }
    }
}
// END
