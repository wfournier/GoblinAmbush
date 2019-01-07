package entities;

import db.ExpManager;

public class Character {
    private String name;
    private int level;
    private ExpThreshold expThreshold;
    private Party party;

    public Character(String name, int level, Party party) throws IllegalArgumentException {
        if (level > 0) {
            this.name = name;
            this.level = level;
            this.expThreshold = ExpManager.getThreshold(level);
            this.party = party;
        } else {
            throw new IllegalArgumentException("Character level is illegal.");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        party.update();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        expThreshold = ExpManager.getThreshold(level);
        party.update();
    }

    public int getEasy() {
        return expThreshold.getEasy();
    }

    public int getMedium() {
        return expThreshold.getMedium();
    }

    public int getHard() {
        return expThreshold.getHard();
    }

    public int getDeadly() {
        return expThreshold.getDeadly();
    }

    public String toString() {
        return String.format("%s (%d)\n", name, level);
    }
}
