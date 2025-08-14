package erstesProjekt;

public class Main {
    public static void main(String[] args) {
        Database.init();
        StartView startView = new StartView();
        new StartController(startView);
    }
}
