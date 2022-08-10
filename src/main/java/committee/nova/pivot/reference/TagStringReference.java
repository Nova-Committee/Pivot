package committee.nova.pivot.reference;

public enum TagStringReference {
    WAS_SELECTED("pivot_was_selected");

    TagStringReference(String name) {
        this.name = name;
    }

    public final String name;

    @Override
    public String toString() {
        return name;
    }
}
