package exercise;

//import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public abstract class Tag {

    protected String name;
    protected Map<String, String> attributes;

    public Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    @Override
    public String toString() {

        StringBuilder text = new StringBuilder();

        text.append("<").append(name);
        for (var entry : attributes.entrySet()) {
            text.append(String.format(" %s=\"%s\"", entry.getKey(), entry.getValue()));
        }
        text.append(">");

        return text.toString();
    }
}
// END
