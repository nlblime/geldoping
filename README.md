# 💰 Geldoping – Java GUI Konto-Manager

**Geldoping** ist eine Java-Desktop-Anwendung mit grafischer Benutzeroberfläche (GUI),  
die es ermöglicht, ein Konto mit IBAN und Besitzerinformationen zu verwalten.  
Man kann Einnahmen (z. B. Gehalt) hinzufügen, Ausgaben abziehen und den Kontostand  
in Echtzeit verfolgen. Zusätzlich gibt es visuelle Effekte und Sounds, die das  
Benutzererlebnis spielerischer gestalten.

---

## 🚀 Features
- Kontoanmeldung mit Name, IBAN, Telefonnummer, E-Mail und Passwort  
- Automatische Speicherung der Kontodaten in einer **SQLite-Datenbank**  
- Einnahmen- und Ausgabenverwaltung  
- „Arbeiten“-Button erhöht automatisch den Kontostand um den hinterlegten Lohn  
- Sounds & visuelle Effekte:
  - Positiver Sound beim Arbeiten  
  - Negativer Sound & roter Bildschirmblitz bei Ausgaben  
  - Hintergrundfarbe verändert sich mit steigendem Kontostand  
  - Konfetti & Popup bei 10.000 € Kontostand  
- MVC-Struktur (Model-View-Controller) für sauberen Code  
- Automatische Reduzierung des Kontostands nach Erreichen bestimmter Schwellenwerte  

---

## 🛠 Installation

> **Empfohlen:** Java 17 oder neuer  
> Das Projekt verwendet **Gradle** für den Build- und Ausführungsprozess.

### 1️⃣ Repository klonen
```bash
git clone https://github.com/nlblime/geldoping.git
cd geldoping
