package entities;

public class ExpThreshold {
    private int level;
    private int easy;
    private int medium;
    private int hard;
    private int deadly;

    public ExpThreshold(int level, int easy, int medium, int hard, int deadly) {
        this.level = level;
        this.easy = easy;
        this.medium = medium;
        this.hard = hard;
        this.deadly = deadly;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getEasy() {
        return easy;
    }

    public void setEasy(int easy) {
        this.easy = easy;
    }

    public int getMedium() {
        return medium;
    }

    public void setMedium(int medium) {
        this.medium = medium;
    }

    public int getHard() {
        return hard;
    }

    public void setHard(int hard) {
        this.hard = hard;
    }

    public int getDeadly() {
        return deadly;
    }

    public void setDeadly(int deadly) {
        this.deadly = deadly;
    }
}
