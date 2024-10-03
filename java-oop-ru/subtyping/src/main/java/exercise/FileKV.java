package exercise;


// BEGIN
import java.util.Map;

public class FileKV implements KeyValueStorage {
    private String filePath;

    public FileKV(String filePath, Map<String, String> kv) {
        this.filePath = filePath;
        writeFileKV(kv);
    }

    @Override
    public void set(String key, String value) {
        var kv = readFileKV();
        kv.put(key, value);
        writeFileKV(kv);
    }

    @Override
    public void unset(String key) {
        var kv = readFileKV();
        kv.remove(key);
        writeFileKV(kv);
    }

    @Override
    public String get(String key, String defaultValue) {
        var kv = readFileKV();
        return kv.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return readFileKV();
    }

    private void writeFileKV(Map<String, String> kv) {
        var json = Utils.serialize(kv);
        Utils.writeFile(filePath, json);
    }

    private Map<String, String> readFileKV() {
        var content = Utils.readFile(filePath);
        return Utils.deserialize(content);
    }
}
// END
