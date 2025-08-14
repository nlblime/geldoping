package erstesProjekt;

import javax.swing.*;
import java.awt.*;

public class KontoView extends JFrame {
    public JLabel nameLabel = new JLabel("Willkommen, ...");
    public JLabel ibanLabel = new JLabel("IBAN: ...");
    public JLabel telefonLabel = new JLabel("Telefon: ...");

    public JTextField lohnField = new JTextField(10);
    public JTextField ausgabeField = new JTextField(10);

    public JLabel kontostandLabel = new JLabel("Kontostand: €0.00");
    public JButton arbeitenButton = new JButton("Arbeiten");
    public JButton ausgebenButton = new JButton("Ausgeben");
    public JProgressBar progressBar = new JProgressBar(0, 10000);

    public JPanel headerPanel;
    public JPanel centerPanel;
    public JPanel footerPanel;
    public JPanel buttonPanel;

    public KonfettiPanel konfettiPanel;

    public KontoView() {
        setTitle("Geldoping");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        headerPanel.add(nameLabel);
        headerPanel.add(ibanLabel);
        headerPanel.add(telefonLabel);
        add(headerPanel, BorderLayout.NORTH);

        centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(new JLabel("Arbeitslohn (€):"), gbc);
        gbc.gridx = 1;
        centerPanel.add(lohnField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(new JLabel("Ausgabe (€):"), gbc);
        gbc.gridx = 1;
        centerPanel.add(ausgabeField, gbc);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.add(arbeitenButton);
        buttonPanel.add(ausgebenButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        centerPanel.add(buttonPanel, gbc);

        add(centerPanel, BorderLayout.CENTER);

        footerPanel = new JPanel();
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.Y_AXIS));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        kontostandLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        footerPanel.add(kontostandLabel);
        progressBar.setStringPainted(true);
        progressBar.setString("Ziel: 10000 €");
        progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        footerPanel.add(Box.createVerticalStrut(10));
        footerPanel.add(progressBar);
        add(footerPanel, BorderLayout.SOUTH);

        konfettiPanel = new KonfettiPanel();
        konfettiPanel.setBounds(0, 0, getWidth(), getHeight());
        konfettiPanel.setVisible(false);
        getLayeredPane().add(konfettiPanel, JLayeredPane.POPUP_LAYER);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                konfettiPanel.setSize(getSize());
            }
        });

        setVisible(true);
    }

    public void setKontoinfo(String name, String iban, String tel, double kontostand) {
        nameLabel.setText("👤 Willkommen, " + name);
        ibanLabel.setText("🏦 IBAN: " + iban);
        telefonLabel.setText("📞 Telefon: " + tel);
        kontostandLabel.setText(String.format("💰 Kontostand: €%.2f", kontostand));
    }

    public void updateKontostand(double betrag) {
        kontostandLabel.setText(String.format("💰 Kontostand: €%.2f", betrag));
        int wert = (int) Math.min(betrag, 10000);
        progressBar.setValue(wert);
        progressBar.setString(String.format("Ziel: 10000 € – %.0f%%", wert / 100.0));
        updateHintergrund(betrag);
    }

    public void updateHintergrund(double kontostand) {
        Color farbe;
        if (kontostand >= 0) {
            float prozent = (float) Math.min(kontostand, 10000) / 10000f;
            int gruen = (int) (255 * prozent);
            farbe = new Color(255 - gruen, 255, 255 - gruen);
        } else {
            float prozent = (float) Math.min(-kontostand, 2000) / 2000f;
            int rot = (int) (255 * prozent);
            farbe = new Color(255, 255 - rot, 255 - rot);
        }

        getContentPane().setBackground(farbe);
        headerPanel.setBackground(farbe);
        centerPanel.setBackground(farbe);
        footerPanel.setBackground(farbe);
        buttonPanel.setBackground(farbe);
    }

    public void zeigeKonfetti() {
        konfettiPanel.setVisible(true);
        konfettiPanel.repaint();
        new Timer(2000, _ -> konfettiPanel.setVisible(false)).start();
    }
}
