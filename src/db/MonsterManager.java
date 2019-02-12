package db;

import entities.Monster;

import java.sql.*;
import java.util.ArrayList;

public class MonsterManager {

    public ArrayList<Monster> searchByName(String n) {
        String query = "SELECT m.name, m.cr, e.value as exp, m.size, m.type, m.tags, m.alignment, m.hp, m.init, m.environment\n" +
                "FROM monsters m\n" +
                "JOIN cr_exp e ON m.cr = e.cr\n" +
                "WHERE m.name LIKE '%" + n + "%'\n" +
                "ORDER BY name";

        ResultSet rs = DbManager.query(query);
        ArrayList<Monster> monsters = new ArrayList<>();

        try {
            while (rs.next()) {
                String name = rs.getString("name");
                float cr = rs.getFloat("cr");
                int exp = rs.getInt("exp");
                String size = rs.getString("size");
                String type = rs.getString("type");
                String tagsRaw = rs.getString("tags");
                String alignment = rs.getString("alignment");
                int hp = rs.getInt("hp");
                int init = rs.getInt("init");
                String envRaw = rs.getString("environment");

                String[] tags = null;
                if (tagsRaw != null) {
                    tags = tagsRaw.split(", ");
                }

                String[] environments = null;
                if (envRaw != null) {
                    environments = envRaw.split(", ");
                }

                Monster m = new Monster(name, cr, exp, size, type, tags, alignment, hp, init, environments);
                monsters.add(m);
            }
            DbManager.close();
        } catch (SQLException ex) {
            System.err.println("Error querying 'monsters' table.");
        }

        return monsters;
    }
}
