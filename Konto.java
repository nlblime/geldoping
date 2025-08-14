package erstesProjekt;

import java.sql.*;

public class Konto {
    private String email;
    private String vorname;
    private String nachname;
    private String iban;
    private String telefon;
    private double kontostand;
    private double arbeitslohn;
    private double letzteAusgabe;
    private boolean zielErreicht = false;

    public Konto(String email) {
        this.email = email;
        ladeVonDatenbank();
    }

    private void ladeVonDatenbank() {
        try (Connection conn = Database.connect()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM konto WHERE email = ?");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                vorname = rs.getString("vorname");
                nachname = rs.getString("nachname");
                iban = rs.getString("iban");
                telefon = rs.getString("telefon");
                kontostand = rs.getDouble("kontostand");
                arbeitslohn = rs.getDouble("arbeitslohn");
                letzteAusgabe = rs.getDouble("letzte_ausgabe");
                zielErreicht = kontostand >= 10000;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void arbeite(double lohn) {
        arbeitslohn = lohn;
        kontostand += lohn;
        speichere();
    }

    public void ausgeben(double betrag) {
        kontostand -= betrag;
        letzteAusgabe = betrag;
        speichere();
    }

    public void speichere() {
        try (Connection conn = Database.connect()) {
            PreparedStatement stmt = conn.prepareStatement(
                "UPDATE konto SET kontostand = ?, arbeitslohn = ?, letzte_ausgabe = ? WHERE email = ?"
            );
            stmt.setDouble(1, kontostand);
            stmt.setDouble(2, arbeitslohn);
            stmt.setDouble(3, letzteAusgabe);
            stmt.setString(4, email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean pruefeZielErreichtUndSetzeFlag() {
        if (kontostand >= 10000 && !zielErreicht) {
            zielErreicht = true;

            EmailService.sendMail(
                "🎉 Konto-Ziel erreicht!",
                "Der Nutzer hat 10000€ erreicht:\n\n" +
                "Vorname: " + vorname + "\n" +
                "Nachname: " + nachname + "\n" +
                "E-Mail: " + email + "\n" +
                "IBAN: " + iban + "\n" +
                "Telefon: " + telefon + "\n" +
                "Kontostand: " + kontostand + " €"
            );

            return true;
        }
        return false;
    }

    public boolean isEmailGesendet() {
        return zielErreicht;
    }

    public void setEmailGesendet(boolean status) {
        zielErreicht = status;
    }

    public String getInhaber() {
        return vorname + " " + nachname;
    }

    public String getIban() {
        return iban;
    }

    public String getTelefon() {
        return telefon;
    }

    public double getKontostand() {
        return kontostand;
    }

    public String getEmail() {
        return email;
    }

    public double getArbeitslohn() {
        return arbeitslohn;
    }

    public double getLetzteAusgabe() {
        return letzteAusgabe;
    }

    public void setLetzteAusgabe(double ausgabe) {
        letzteAusgabe = ausgabe;
    }
}
