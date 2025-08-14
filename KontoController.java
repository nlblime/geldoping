package erstesProjekt;

import javax.swing.*;

public class KontoController {
    private KontoView view;
    private Konto konto;

    public KontoController(KontoView view, Konto konto) {
        this.view = view;
        this.konto = konto;

        view.setKontoinfo(konto.getInhaber(), konto.getIban(), konto.getTelefon(), konto.getKontostand());
        view.updateKontostand(konto.getKontostand());

        view.lohnField.setText(String.valueOf(konto.getArbeitslohn()));
        view.ausgabeField.setText(String.valueOf(konto.getLetzteAusgabe()));

        view.arbeitenButton.addActionListener(_ -> arbeite());
        view.ausgebenButton.addActionListener(_ -> ausgeben());
    }

    private void arbeite() {
        try {
            double lohn = Double.parseDouble(view.lohnField.getText().trim());
            konto.arbeite(lohn);
            view.updateKontostand(konto.getKontostand());
            view.lohnField.setText(String.valueOf(lohn));
            SoundPlayer.playSound("D:\\Schule\\Informatik\\LK Klasse 11\\Projekte\\Geldoping\\sounds\\good.wav");

            if (konto.pruefeZielErreichtUndSetzeFlag()) {
                SwingUtilities.invokeLater(() -> {
                    SoundPlayer.playSound("D:\\Schule\\Informatik\\LK Klasse 11\\Projekte\\Geldoping\\sounds\\win.wav");
                    JOptionPane.showMessageDialog(view, "🎉 Ziel erreicht! Du hast 10.000€ angespart!", "Gratulation", JOptionPane.INFORMATION_MESSAGE);
                    view.zeigeKonfetti();
                    new Timer(5000, _ -> beginneGeldzerfall()).start();
                });
            }

            if (konto.getKontostand() >= 10000 && !konto.isEmailGesendet()) {
                konto.setEmailGesendet(true);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Ungültiger Arbeitslohn.");
        }
    }

    private void ausgeben() {
        try {
            double betrag = Double.parseDouble(view.ausgabeField.getText().trim());
            konto.setLetzteAusgabe(betrag);
            konto.ausgeben(betrag);
            view.updateKontostand(konto.getKontostand());
            view.ausgabeField.setText(String.valueOf(betrag));
            SoundPlayer.playSound("D:\\Schule\\Informatik\\LK Klasse 11\\Projekte\\Geldoping\\sounds\\bad.wav");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Ungültiger Ausgabebetrag.");
        }
    }

    private void beginneGeldzerfall() {
        Timer timer = new Timer(100, null);
        timer.addActionListener(_ -> {
            double aktuell = konto.getKontostand();
            if (aktuell > 0) {
                double neu = Math.max(0, aktuell - 250);
                konto.ausgeben(aktuell - neu);
                view.updateKontostand(neu);
                SoundPlayer.playSound("D:\\Schule\\Informatik\\LK Klasse 11\\Projekte\\Geldoping\\sounds\\bad.wav");
            } else {
                timer.stop();
                JOptionPane.showMessageDialog(view, "😢 Ups... Alles weg...", "Gescammt", JOptionPane.WARNING_MESSAGE);
            }
        });
        timer.start();
    }
}
