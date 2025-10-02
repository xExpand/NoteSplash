package styles;
//This ANSI-Utility class was AI generated, to save time
@SuppressWarnings("all")//Suppress redundant never used warnings
public class ConsoleEffects {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static final String B = "\u001B[1m";
    public static final String UNDERLINE = "\u001B[4m";
    public static final String REVERSED = "\u001B[7m";

    public static final String CLEAR_SCREEN = "\u001B[2J";
    public static final String CLEAR_LINE = "\u001B[2K";

    public static String paint(String text, String color, boolean bold) {
        return (bold ? B : "") + color + text + RESET;
    }

    public static void colRed(String text, boolean bold) { System.out.println(paint(text, RED, bold)); }
    public static String colRedString(String text, boolean bold) {
        return paint(text, RED, bold);
    }
    public static String colGreen(String text, boolean bold) {
        return paint(text, GREEN, bold);
    }
    public static String colYellow(String text, boolean bold) {
        return paint(text, YELLOW, bold);
    }
    public static void colBlue(String text, boolean bold) { System.out.println(paint(text, BLUE, bold)); }
    public static void colMagenta(String text, boolean bold) { System.out.println(paint(text, MAGENTA, bold)); }
    public static void colCyan(String text, boolean bold) { System.out.println(paint(text, CYAN, bold)); }
    public static void colWhite(String text, boolean bold) { System.out.println(paint(text, WHITE, bold)); }

    public static String bg(String text, String bgColor) { return bgColor + text + RESET; }
    public static String rgb(int r, int g, int b, String text) { return "\u001B[38;2;" + r + ";" + g + ";" + b + "m" + text + RESET; }

    public static String moveCursor(int row, int col) { return "\u001B[" + row + ";" + col + "H"; }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J"); // Move cursor + clear
        System.out.flush();
    }
    public static void clearLine() { System.out.print(CLEAR_LINE + "\r"); System.out.flush(); }

    public static void rainbowText(String text) { System.out.println(rainbowTextString(text)); }

    public static String rainbowTextString(String text) {
        int length = text.length();
        int[] colorsR = {255, 255, 255, 173, 135, 196, 255};
        int[] colorsG = {179, 204, 255, 255, 206, 158, 182};
        int[] colorsB = {186, 153, 102, 198, 250, 235, 255};

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int colorIndex = i % colorsR.length;
            sb.append(rgb(colorsR[colorIndex], colorsG[colorIndex], colorsB[colorIndex], String.valueOf(text.charAt(i))));
        }
        return sb.toString();
    }

    public static void rainbowTextAnimated(String text) {
        int length = text.length();
        int[] colorsR = {255, 255, 255, 173, 135, 196, 255};
        int[] colorsG = {179, 204, 255, 255, 206, 158, 182};
        int[] colorsB = {186, 153, 102, 198, 250, 235, 255};

        try {
            for (int shift = 0; shift < length + colorsR.length; shift++) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < length; i++) {
                    int colorIndex = (i + shift) % colorsR.length;
                    sb.append(rgb(colorsR[colorIndex], colorsG[colorIndex], colorsB[colorIndex], String.valueOf(text.charAt(i))));
                }
                System.out.print("\r" + sb);
                System.out.flush();
                Thread.sleep(50);
            }
            System.out.println();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

