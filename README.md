# ESHH
Projekt Repository für die Zusammenarbeit zur Projektarbeit im Fach ES&amp;HH

## Projektbeschreibung
LED Lichterkette unter Bett gesteuert durch Raspberry PI. Jede LED in der Kette kann einzeln angesteuert werden, um die Farbe und Lichtstärke zu regeln. Die Steuerung soll auf Licht, Bewegung, Uhrzeit und manuelle Eingabe reagieren können. Die Uhrzeit soll in einer Datenbank geplant / gespeichert werden können. Die Datenbank soll für Anbindungen von anderen Systemen her auch erreichbar sein. 
Die Eingabe und Konfiguration soll über eine abstrakte Schnittstelle möglich sein, die verschiedene Endgeräte erlaubt. 

## Projektteilnehmer
* Andreas Züger
* Endre Marczi
* Markus Schenk

## GIT
Das GIT-Repository wird für die entfernte Zusammenarbeit im Projekt verwendet. Auszutauschende Dateien können ins Repository hochgeladen werden. Für grosse Dateien gibt es einen eigenen Branch um die Geschwindigkeit der anderen Branches nicht negativ zu beinflussen. Das Repository ist private.

### Repository
https://github.com/JazzMalar/ESHH.git

### Setup
```bash
git clone https://github.com/JazzMalar/ESHH.git
git branch admin origin/admin
git branch doku origin/doku
git branch source origin/source
git config --global push.default simple
```
### GIT-Richtlinien
Grundsätzlich gibt es 5 Branches. 
* Master - Read-Only Branch in dem der Repository Owner bestimmte Milestones von anderen Branches "pulled".
* Doku - Für die abzugebende Projektdokumentation
* Source - Für den zugehörigen Source Code
* Admin - Für Organisatorisches und Projektmanagement und alles was sonst nirgends hin passt
* BigFiles - Für allfällige grosse Dateien
Neue, eigene, Branches dürfen natürlich jederzeit erstellt werden. 
