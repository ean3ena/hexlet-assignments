package exercise;

// BEGIN
public class LabelTag implements TagInterface {

    private String label;
    private TagInterface tag;

    public LabelTag(String label, TagInterface tag) {
        this.label = label;
        this.tag = tag;
    }

    @Override
    public String render() {
        return String.format("<label>%s%s</label>", label, tag.render());
    }
}
// END
