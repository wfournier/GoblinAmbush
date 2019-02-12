package entities;

public enum Difficulty {
    EASY(1),
    MEDIUM(2),
    HARD(3),
    DEADLY(4);

    private int value;

    Difficulty(int value) {
        this.value = value;
    }

    public static Difficulty get(int val) {
        for (Difficulty d : Difficulty.values()) {
            if (d.value == val) {
                return d;
            }
        }
        return null;
    }

    public int getValue() {
        return this.value;
    }

    public boolean equals(Difficulty diff) {
        return value == diff.value;
    }
}