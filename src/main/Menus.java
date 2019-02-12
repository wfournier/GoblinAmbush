package main;

import db.MonsterManager;
import entities.*;
import entities.Character;

import java.util.ArrayList;
import java.util.Scanner;

class Menus {

    private Scanner scan;
    private Inputs in;
    private Party party;
    private Encounter encounter;

    public Menus() {
        scan = new Scanner(System.in);
        in = new Inputs(scan);
    }

    public void displayPrompt() {
        int option;
        do {
            mainMenu();
            option = in.getIntInput(1, 2);
            switch (option) {
                case 1:
                    createEncounter();
                    break;
                case 2:
                    manageParty();
                    break;
            }
        } while (option != 0);
    }

    private void welcome() {
        System.out.println("Welcome to Goblin Ambush!");
    }

    private void mainMenu() {
        welcome();
        System.out.println("1- Encounter\n" +
                "2- Party\n" +
                "0- Exit\n"
        );
    }

    private void createEncounter() {
        if (party == null || party.getSize() == 0) {
            System.err.println("Please create a party first.");
            return;
        }

        int option;
        do {
            if (encounter != null && encounter.getSize() != 0) {
                System.out.println(encounter.toString());
            } else {
                encounter = new Encounter(party);
                System.out.println("New encounter created!");
            }

            encounterOptions();
            option = in.getIntInput(1, 3);
            switch (option) {
                case 1:
                    addMonster();
                    break;
                case 2:
                    removeMonster();
                    break;
                case 3:
                    scaleEncounter();
                    break;
            }
        } while (option != 0);
    }

    private void encounterOptions() {
        System.out.println("1- Add monster\n" +
                "2- Remove monster\n" +
                "3- Scale encounter\n" +
                "0- Return\n");
    }

    private void addMonster() {
        MonsterManager mm = new MonsterManager();

        monsterFilters();
        int choice = in.getIntInput(1, 4);

        ArrayList<Monster> monsters = new ArrayList<Monster>();
        switch (choice) {
            case 1:
                String name = in.getStringInput("Name");
                monsters = mm.searchByName(name);
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
        }

        Monster m = selectMonster(monsters);
        if (monsters.size() > 0) {
            int count = in.getIntInput("How many?");

            for (int i = 0; i < count; i++) {
                encounter.add(i == 0 ? m : m.clone());
            }
        }
    }

    private void monsterFilters() {
        System.out.println("Which field do you want to filter?");
        System.out.println("1- Name\n" +
                "2- Challenge rating (CR)\n" +
                "3- Type\n" +
                "4- HP\n" +
                "0- Return");
    }

    private Monster selectMonster(ArrayList<Monster> monsters) {
        Monster monster = null;

        if (monsters.size() > 0) {
            displayList(monsters);
            monster = monsters.get(in.getIntInput(1, monsters.size()) - 1);
        } else {
            System.out.println("No monsters found.");
        }

        return monster;
    }

    private void removeMonster() {
        ArrayList<Monster> monsters = encounter.getMonsters();
        Monster m = selectMonster(monsters);

        encounter.remove(m);
    }

    private void scaleEncounter() {
        scaleOptions();
        int choice = in.getIntInput(1, 4);
        encounter.scale(Difficulty.get(choice));
    }

    private void scaleOptions() {
        System.out.println("1- Easy\n" +
                "2- Medium\n" +
                "3- Hard\n" +
                "4- Deadly");
    }

    private void manageParty() {
        int option;
        do {
            if (party != null && party.getSize() != 0) {
                System.out.println(party.toString());
            } else {
                party = new Party();
                System.out.println("New party created!");
            }

            partyOptions();
            option = in.getIntInput(1, 3);
            switch (option) {
                case 1:
                    addCharacter();
                    break;
                case 2:
                    removeCharacter();
                    break;
                case 3:
                    modifyCharacter();
                    break;
            }
        } while (option != 0);
    }

    private void addCharacter() {
        System.out.println("Name:");
        String name = scan.nextLine();

        int level = in.getIntInput("Level", 1, 20);

        Character c = new Character(name, level, party);
        party.add(c);
    }

    private void removeCharacter() {
        Character c = selectCharacter();
        party.remove(c);
    }

    private void modifyCharacter() {
        Character c = selectCharacter();

        int option;
        modifyFields();
        option = in.getIntInput(1, 2);
        switch (option) {
            case 1:
                c.setName(in.getStringInput("New Name"));
                break;
            case 2:
                c.setLevel(in.getIntInput("New Level", 1, 20));
                break;
        }
    }

    private Character selectCharacter() {
        ArrayList<Character> characters = party.getCharacters();
        displayList(characters);

        return characters.get(in.getIntInput(1, characters.size()) - 1);
    }

    private void partyOptions() {
        System.out.println("1- Add character\n" +
                "2- Remove character\n" +
                "3- Update character\n" +
                "0- Return\n");
    }

    private void modifyFields() {
        System.out.println("Which field to you want to update?");
        System.out.println("1- Name\n" +
                "2- Level\n" +
                "0- Return\n");
    }

    private void displayList(ArrayList<?> list) {
        for (Object o : list) {
            System.out.printf("%d- %s\n", (list.indexOf(o) + 1), o.toString());
        }
    }

}