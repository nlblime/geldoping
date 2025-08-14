package erstesProjekt;

import javax.swing.*;
import java.sql.*;

public class LoginController {
    private LoginView view;

    public LoginController(LoginView view) {
        this.view = view;
        view.loginButton.addActionListener(_ -> anmelden());
    }

    private void anmelden() {
        String email = view.emailField.getText().trim();
        String passwort = new String(view.passwortField.getPassword());

        if (email.isEmpty() || passwort.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Bitte E-Mail und Passwort eingeben.");
            return;
        }

        try (Connection conn = Database.connect()) {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM konto WHERE email = ? AND passwort = ?");
            stmt.setString(1, email);
            stmt.setString(2, passwort);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                view.dispose();
                Konto konto = new Konto(email);
                KontoView kontoView = new KontoView();
                kontoView.setKontoinfo(konto.getInhaber(), konto.getIban(), konto.getTelefon(), konto.getKontostand());
                new KontoController(kontoView, konto);
            } else {
                JOptionPane.showMessageDialog(view, "E-Mail oder Passwort ist falsch.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Fehler beim Anmelden: " + e.getMessage());
        }
    }
}
