package erstesProjekt;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterController {
    private final RegisterView view;

    public RegisterController(RegisterView view) {
        this.view = view;
        view.registerButton.addActionListener(_ -> registrieren());
    }

    private void registrieren() {
        String vorname = view.vornameField.getText().trim();
        String nachname = view.nachnameField.getText().trim();
        String email = view.emailField.getText().trim();
        String iban = view.ibanField.getText().trim().replaceAll(" ", "");
        String telefon = view.telefonField.getText().trim();
        String passwort = new String(view.passwortField.getPassword());

        if (vorname.isEmpty() || nachname.isEmpty() || email.isEmpty() ||
            iban.isEmpty() || telefon.isEmpty() || passwort.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Bitte alle Felder ausfüllen.");
            return;
        }

        if (iban.length() != 22) {
            JOptionPane.showMessageDialog(view, "Die IBAN muss genau 22 Zeichen lang sein.");
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(view, "Bitte gib eine gültige E-Mail-Adresse ein.");
            return;
        }

        if (!telefon.matches("[+]?[0-9]{7,}")) {
            JOptionPane.showMessageDialog(view, "Bitte gib eine gültige Telefonnummer ein (mind. 7 Ziffern).");
            return;
        }

        try (Connection conn = Database.connect()) {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO konto (email, passwort, vorname, nachname, iban, telefon, kontostand, arbeitslohn) " +
                "VALUES (?, ?, ?, ?, ?, ?, 0.0, 0.0)");
            stmt.setString(1, email);
            stmt.setString(2, passwort);
            stmt.setString(3, vorname);
            stmt.setString(4, nachname);
            stmt.setString(5, iban);
            stmt.setString(6, telefon);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(view, "Registrierung erfolgreich!");
            view.dispose();
            new LoginController(new LoginView());

        } catch (Exception ex) {
            if (ex.getMessage().contains("UNIQUE constraint failed: konto.email")) {
                JOptionPane.showMessageDialog(view, "Diese E-Mail-Adresse ist bereits registriert.");
            } else {
                JOptionPane.showMessageDialog(view, "Fehler bei der Registrierung: " + ex.getMessage());
            }
        }
    }
}
