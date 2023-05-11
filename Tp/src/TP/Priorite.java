package TP;

public enum Priorite {
    HIGH("HIGH", 3),
    MEDIUM("MEDIUM", 2),
    LOW("LOW", 1);

    private final String value;
    private final int priorityOrder;

    Priorite(String value, int priorityOrder) {
        this.value = value;
        this.priorityOrder = priorityOrder;
    }

    public String getValue() {
        return value;
    }

    public int getPriorityOrder() {
        return priorityOrder;
    }

    public static Priorite fromValue(String priorityValue) {
        for (Priorite priorite : values()) {
            if (priorite.value.equalsIgnoreCase(priorityValue)) {
                return priorite;
            }
        }
        throw new IllegalArgumentException("Invalid Priorite value: " + priorityValue);
    }

    public int comparePriority(Priorite otherPriority) {
        return Integer.compare(this.priorityOrder, otherPriority.priorityOrder);
    }
}
