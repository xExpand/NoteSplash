package core;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import styles.ConsoleEffects;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class JSONManager {
    Gson gson = new Gson();
    Scanner scanner = new Scanner(System.in);
    ArrayList<Note> allNotes = loadNotes();
    public void saveNotes(ArrayList<Note> notes){
        try (FileWriter writer = new FileWriter("Notes.json")) {
            gson.toJson(notes, writer);
        } catch (IOException e) {
            System.out.println("File writing failed :O");
        }
    }


    //return previous notes out of json
    public ArrayList<Note> loadNotes() {
        try {
            ArrayList<Note> notes = gson.fromJson(Files.readString(Paths.get("Notes.json")),
                    new TypeToken<ArrayList<Note>>(){}.getType());
            //prevent NullPointer
            return notes != null ? notes : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("loading notes failed");
            return new ArrayList<>();
        }
    }


    public void deleteNote(ArrayList<Note> notes, int delId) {
        // delete note with matching ind
        boolean removed = notes.removeIf(note -> note.getId() == delId);

        if (removed) {
            ConsoleEffects.clearScreen();
            System.out.println(ConsoleEffects.colYellow("Note deleted successfully!", true));
            saveNotes(notes);
        } else {
            System.out.println(ConsoleEffects.colYellow("Note found with ID: ",true) + ConsoleEffects.colGreen(String.valueOf(delId),true));
        }
    }




}
