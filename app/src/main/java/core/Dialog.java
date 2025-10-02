package core;

import styles.ConsoleEffects;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Dialog {
    Scanner scnr = new Scanner(System.in);
    JSONManager jsonManager = new JSONManager();

    public void menu(boolean running) {
        while (running) {
            ArrayList<Note> allNotes = jsonManager.loadNotes();

            System.out.println("\u001B[0;95m" + """
                    \u001B[34mCreate a new Note: 1\u001B[0m\s
                    \u001B[36mLoad all Notes: 2\u001B[0m\s
                    \u001B[31mDelete a Note: 3\u001B[0m\s
                    \u001B[35mExit: 4\u001B[0;m""");


            try {
                int choice = scnr.nextInt();
                scnr.nextLine(); //empty the scanner
                ConsoleEffects.clearScreen();

                switch (choice) {
                    case 1 -> createNote(allNotes);
                    case 2 -> showNotes(allNotes);
                    case 3 -> delNote();
                    case 4 -> {ConsoleEffects.rainbowTextAnimated(sayGoodBye()); running = false;}
                    default -> System.out.println("Please enter a Number from 1-4");
                }
            } catch (Exception e) {
                ConsoleEffects.clearScreen();
                System.out.println(ConsoleEffects.colYellow("Enter a number!!!", true));
                scnr.nextLine();
            }

        }
    }

    public String sayGoodBye(){
        Random random = new Random();
        String[] farewells = {"bye bye!", "bella ciao!", "adios amigos!", "see you later!", "auf wiedersehen!", "au revoir!", "sayonara!", "ciao!", "hasta luego!", "peace out!", "catch you later!", "bye for now!", "farewell!", "tschüss!", "take care!"};
        return (farewells[random.nextInt(farewells.length)]);
    }

    public void showNotes(ArrayList<Note> allNotes){
        if (!allNotes.isEmpty()) {
            try {
                for (Note note : allNotes) {
                    System.out.println(note);
                }
            } catch (RuntimeException e) {
                System.out.println("Notes are empty, create new notes...");
            }
        }else {
            System.out.println(ConsoleEffects.colYellow("Notes are empty...", true));
        }
    }

    public void createNote(ArrayList<Note> allNotes) {
        ArrayList<Note> sessionNotes = new ArrayList<>(); //temporary session notes
        boolean again = true;
        while (again) {
            System.out.println(ConsoleEffects.colYellow("Enter your Title", true));
            String titleInp = scnr.nextLine();
            System.out.println(ConsoleEffects.colYellow("Enter your content", true));
            String contentInp = scnr.nextLine();
            Note note = new Note(titleInp, contentInp);
            allNotes.add(note);
            sessionNotes.add(note);
            System.out.println(ConsoleEffects.colYellow("Added Notes: \n", true) + sessionNotes);
            try {
                Thread.sleep(3000);
                ConsoleEffects.clearScreen();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(ConsoleEffects.colYellow("Create another Note? y/n", true));
            String againChoice = scnr.nextLine();
            if (againChoice.equalsIgnoreCase("n") || againChoice.equalsIgnoreCase("no")) {
                ConsoleEffects.clearScreen();
                again = false;
            }
        }
        jsonManager.saveNotes(allNotes);
    }

    public void delNote() {
        boolean again = true;
        ArrayList<Note> allNotes = jsonManager.loadNotes();

        if (allNotes.isEmpty()) {
            System.out.println(ConsoleEffects.colYellow("No deletable Notes, the Notes are empty", true));
            return;
        }

        while (again) {
            ConsoleEffects.clearScreen();
            System.out.println(ConsoleEffects.colYellow("Deletable Notes:", true));
            showNotes(allNotes);
            System.out.println(ConsoleEffects.colYellow("Enter Note ID to delete a Note, or \"All\" to delete all Notes:", true));

            String input = scnr.nextLine().trim();

            if (input.equalsIgnoreCase("all")) {
                allNotes.clear();
                jsonManager.saveNotes(allNotes);
                ConsoleEffects.clearScreen();
                System.out.println(ConsoleEffects.colYellow("All notes deleted!", true));
                again = false;
            } else {
                try {
                    int delId = Integer.parseInt(input);
                    jsonManager.deleteNote(allNotes, delId);
                    System.out.println(ConsoleEffects.colYellow("Delete another Note? y/n", true));
                    String againChoice = scnr.nextLine().trim();
                    if (againChoice.equalsIgnoreCase("n") || againChoice.equalsIgnoreCase("no")) {
                        again = false;
                    }
                } catch (NumberFormatException e) {
                    ConsoleEffects.clearScreen();
                    System.out.println(ConsoleEffects.colYellow("Please enter a number or 'All'!", true));
                }
            }
        }
    }


    public void logo(boolean show) {
        if (show) {
            System.out.println("""
                    \u001B[92m\
                         _   __      __      _____       __           __
                        / | / /___  / /____ / ___/____  / /___ ______/ /_
                       /  |/ / __ \\/ __/ _ \\\\__ \\/ __ \\/ / __ `/ ___/ __ \\
                      / /|  / /_/ / /_/  __/__/ / /_/ / / /_/ (__  ) / / /
                     /_/ |_/\\____/\\__/\\___/____/ .___/_/\\__,_/____/_/ /_/
                                              /_/                       \s
                    \u001B[0m""");
        }
    }
}
