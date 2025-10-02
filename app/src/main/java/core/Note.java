package core;

import java.util.Random;

public class Note {
    private final String title;
    private final String content;
    transient Random random = new Random();
    int id;
    transient int min = 1000;
    transient int max = 9999;

    @Override
    public String toString() {
        return "\033[31mtitle:\033[0m " + title + "\n\033[31mcontent\033[0m: " + content + "\n\033[31mID\033[0m: " + id;
    }

    public int getId() {
        return id;
    }

    //constructor
    public Note(String title, String content){
        this.title = title;
        this.content = content;
        id = random.nextInt(max - min + 1)+min;
    }
}
