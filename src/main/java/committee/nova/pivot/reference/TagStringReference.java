package committee.nova.pivot.reference;

public enum TagStringReference {
    SELECTED_SLOT("pivot_selected_slot");

    TagStringReference(String name) {
        this.name = name;
    }

    public final String name;

    @Override
    public String toString() {
        return name;
    }
}
