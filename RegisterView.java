package erstesProjekt;

import javax.swing.*;
import java.awt.*;

public class RegisterView extends JFrame {
    public JTextField vornameField = new JTextField(20);
    public JTextField nachnameField = new JTextField(20);
    public JTextField emailField = new JTextField(20);
    public JTextField ibanField = new JTextField(20);
    public JTextField telefonField = new JTextField(20);
    public JPasswordField passwortField = new JPasswordField(20);
    public JButton registerButton = new JButton("Registrieren");

    public RegisterView() {
        setTitle("Geldoping");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Registrierung", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        formPanel.add(new JLabel("Vorname:"));
        formPanel.add(vornameField);
        formPanel.add(new JLabel("Nachname:"));
        formPanel.add(nachnameField);
        formPanel.add(new JLabel("E-Mail:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("IBAN:"));
        formPanel.add(ibanField);
        formPanel.add(new JLabel("Telefon:"));
        formPanel.add(telefonField);
        formPanel.add(new JLabel("Passwort:"));
        formPanel.add(passwortField);

        add(formPanel, BorderLayout.CENTER);

        registerButton.setBackground(new Color(33, 150, 243)); // blau
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        buttonPanel.add(registerButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
