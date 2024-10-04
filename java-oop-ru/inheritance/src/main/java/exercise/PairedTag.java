package exercise;

import java.util.Map;
import java.util.List;
//import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {

    private final String body;
    private final List<Tag> childs;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> childs) {
        super(name, attributes);
        this.body = body;
        this.childs = childs;
    }

    @Override
    public String toString() {

        StringBuilder text = new StringBuilder();

        text.append(super.toString());
        for (var child : childs) {
            text.append(child.toString());
        }
        text.append(body);
        text.append("</").append(name).append(">");

        return text.toString();
    }
}
// END
