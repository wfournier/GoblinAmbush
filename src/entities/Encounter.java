package entities;

import com.github.kiprobinson.bigfraction.BigFraction;
import main.Observer;

import java.util.ArrayList;

public class Encounter implements Observer {
    private ArrayList<Monster> monsters;
    private float totalCr;
    private int totalExp;
    private float adjustedExp;

    public Encounter() {
        monsters = new ArrayList<>();
        totalCr = 0;
        totalExp = 0;
        adjustedExp = 0;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public int getSize() {
        return monsters.size();
    }

    public int getTotalExp() {
        return totalExp;
    }

    public float getAdjustedExp() { return adjustedExp; }

    public float getTotalCr() { return totalCr; }

    public String getTotalCrString() {
        if(totalCr % 1 == 0) {
            return Integer.toString((int)totalCr);
        } else {
            String frac = BigFraction.valueOf(totalCr % 1).toString();

            if(totalCr > 1) {
                String coeff = Integer.toString((int)Math.floor(totalCr));

                return String.format("%s %s", coeff, frac);
            } else {
                return frac;
            }
        }
    }

    public void add(Monster m) {
        monsters.add(m);
        update();
    }

    public void remove(Monster m) {
        monsters.remove(m);
        update();
    }

    @Override
    public void update() {
        updateTotalExp();
        updateAdjustedExp();
        updateTotalCr();
    }

    private void updateTotalExp() {
        int sum = 0;
        for (Monster m : monsters) {
            sum += m.getExp();
        }
        totalExp = sum;
    }

    private void updateAdjustedExp() {
        int size = getSize();

        if(size == 1) {
            adjustedExp = totalExp;
        } else if(size == 2) {
            adjustedExp = totalExp * 1.5f;
        } else if(size >= 3 && size <= 6) {
            adjustedExp = totalExp * 2;
        } else if(size >= 7 && size <= 10) {
            adjustedExp = totalExp * 2.5f;
        } else if(size >= 11 && size <= 14) {
            adjustedExp = totalExp * 3;
        } else { // 15 or more
            adjustedExp = totalExp * 4;
        }
    }

    private void updateTotalCr() {
        float sum = 0;
        for(Monster m : monsters) {
            sum += m.getCr();
        }
        totalCr = sum;
    }

    public Difficulty getDifficulty(Party party) {
        if(party.getDeadly() <= adjustedExp) {
            return Difficulty.DEADLY;
        } else if(party.getHard() <= adjustedExp) {
            return Difficulty.HARD;
        } else if(party.getMedium() <= adjustedExp) {
            return Difficulty.MEDIUM;
        } else {
            return Difficulty.EASY;
        }
    }

    public void scale(Party party, Difficulty scaleDiff) {
        Difficulty currentDiff = getDifficulty(party);
        if(currentDiff.equals(scaleDiff)) {
            System.err.println("No changes needed.");
        } else {
            int targetExp = party.getDifficultyExp(scaleDiff);
            if(targetExp < adjustedExp) { // if encounter needs to be scaled DOWN
                Monster toRemove = null;
                for(Monster m : monsters) {
                    float gap = Math.abs(targetExp - adjustedExp);
                    if(toRemove == null) { // TODO: find the monster with exp closest to the exp gap

                    }
                }
            } else { // if encounter needs to be scaled UP

            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%54s\n" +
                        "%-35s%8s%11s\n" +
                        "%54s\n",
                "======================================================",
                "Name", "CR", "EXP",
                "------------------------------------------------------"));

        for (Monster m : monsters) {
            sb.append(String.format("%-35s%8s%11d\n",
                    m.getName(), m.getCrString(), m.getExp()));
        }

        sb.append(String.format("%54s\n" +
                        "%-35s%8s%11d\n",
                "======================================================",
                "TOTAL", getTotalCrString(), totalExp));

        return sb.toString();
    }
}
