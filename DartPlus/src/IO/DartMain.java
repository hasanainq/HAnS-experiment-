package IO;

public class DartMain {

    static boolean programRunning = true;
    public static void main(String[] args) throws Exception {
        Controller main = new Controller();
        while (programRunning) {
            main.mainMenu();
        }
    }
}
