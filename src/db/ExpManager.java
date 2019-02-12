package db;

import entities.ExpThreshold;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ExpManager extends DbManager {

    private static Map<Integer, ExpThreshold> thresholds;

    static {
        populate();
    }

    private static void populate() {
        String query = "SELECT * FROM level_exp_thresholds";

        ResultSet rs = DbManager.query(query);

        try {
            thresholds = new HashMap<>();
            while (rs.next()) {
                int level = rs.getInt("level");
                int easy = rs.getInt("easy");
                int medium = rs.getInt("medium");
                int hard = rs.getInt("hard");
                int deadly = rs.getInt("deadly");

                ExpThreshold t = new ExpThreshold(level, easy, medium, hard, deadly);
                thresholds.put(level, t);
            }
            DbManager.close();
        } catch (SQLException ex) {
            System.err.println("Error querying 'level_exp_thresholds' table.");
        }
    }

    public static ExpThreshold getThreshold(int level) {
        if (thresholds == null) {
            populate();
        }

        return thresholds.get(level);
    }
}
