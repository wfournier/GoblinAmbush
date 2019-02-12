package entities;

import main.Observer;

import java.util.ArrayList;

public class Party implements Observer {
    private ArrayList<Character> characters;
    private int totalLevel;
    private int dailyBudget;

    public Party() {
        characters = new ArrayList<>();
        totalLevel = 0;
        dailyBudget = 0;
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public int getTotalLevel() {
        return totalLevel;
    }

    public int getSize() {
        return characters.size();
    }

    public int getEasy() {
        int sum = 0;
        for (Character c : characters) {
            sum += c.getEasy();
        }
        return sum;
    }

    public int getMedium() {
        int sum = 0;
        for (Character c : characters) {
            sum += c.getMedium();
        }
        return sum;
    }

    public int getHard() {
        int sum = 0;
        for (Character c : characters) {
            sum += c.getHard();
        }
        return sum;
    }

    public int getDeadly() {
        int sum = 0;
        for (Character c : characters) {
            sum += c.getDeadly();
        }
        return sum;
    }

    public int getDifficultyExp(Difficulty diff) {
        switch (diff) {
            case DEADLY:
                return getDeadly();
            case HARD:
                return getHard();
            case MEDIUM:
                return getMedium();
            default:
                return getEasy();
        }
    }

    public void add(Character c) {
        characters.add(c);
        update();
    }

    public void remove(Character c) {
        characters.remove(c);
        update();
    }

    public Character get(String name) {
        for (Character c : characters) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void update() {
        updateTotalLevel();
        updateDailyBudget();
    }

    private void updateTotalLevel() {
        int sum = 0;
        for (Character c : characters) {
            sum += c.getLevel();
        }
        totalLevel = sum;
    }

    private void updateDailyBudget() {
        dailyBudget = 300 * totalLevel;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%43s\n" +
                        "%-35s%8s\n" +
                        "%43s\n",
                "===========================================",
                "NAME", "LEVEL",
                "-------------------------------------------"));

        for (Character c : characters) {
            sb.append(String.format("%-35s%8s\n",
                    c.getName(), c.getLevel()));
        }

        sb.append(String.format("%43s\n" +
//                        "%-28s%15d\n" +
                        "%-28s%15d\n" +
                        "%-28s%15d\n" +
                        "%-28s%15d\n" +
                        "%-28s%15d\n\n" +
                        "%-28s%15d\n",
                "===========================================",
//                "TOTAL", totalLevel,
                "EASY", getEasy(),
                "MEDIUM", getMedium(),
                "HARD", getHard(),
                "DEADLY", getDeadly(),
                "DAILY BUDGET", dailyBudget));

        return sb.toString();
    }
}
