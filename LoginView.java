package erstesProjekt;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    public JTextField emailField = new JTextField(20);
    public JPasswordField passwortField = new JPasswordField(20);
    public JButton loginButton = new JButton("Anmelden");

    public LoginView() {
        setTitle("Geldoping");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Anmelden", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        formPanel.add(new JLabel("E-Mail:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Passwort:"));
        formPanel.add(passwortField);
        add(formPanel, BorderLayout.CENTER);

        loginButton.setBackground(new Color(76, 175, 80));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        buttonPanel.add(loginButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
