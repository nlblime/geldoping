package erstesProjekt;

public class StartController {
    public StartController(StartView view) {
        view.loginButton.addActionListener(_ -> {
            view.dispose();
            new LoginController(new LoginView());
        });

        view.registerButton.addActionListener(_ -> {
            view.dispose();
            new RegisterController(new RegisterView());
        });
    }
}
