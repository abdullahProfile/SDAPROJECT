import controller.LoginController;

public class Main {
    public static void main(String[] args) {
        // Launch the login GUI on the event dispatch thread
        javax.swing.SwingUtilities.invokeLater(LoginController::new);
    }
}