package core;

public class Main {
    public static void main(String[] args) {
        boolean running = true;
        boolean showLogo = true;
        Dialog dialog = new Dialog();
        dialog.logo(showLogo);
        dialog.menu(running);
    }
}
