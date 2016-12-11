# ES&HH

## Idee
Es soll eine LED-Pixelkette mit einem Raspberry PI als Microcomputer gesteuert werden. Jeder LED-Pixel besteht aus drei einzelnen LEDs, die durch einen integrierten Schaltkreis kontrolliert werden. So kann jeder Pixel in der Kette einzeln angesteuert werden. Die LED-Pixelkette wird durch eine Applikation auf dem Raspberry PI gesteuert. Die Applikation auf dem Raspberry PI soll Licht, Bewegung, Uhrzeit und manuelle Eingabe als Input annehmen können. Auf dem Raspberry PI wird für persistente Datenhaltung eine Datenbank geführt. Die Datenbank soll für wiederkehrende und geplante Ereignisse benutzt werden. Die Applikation reagiert auf Inputs, geplante oder wiederkehrende Ereignisse mit der Ansteuerung der LED-Pixelkette, die verschiedene Modi zur Darstellung anbietet.

Die Datenbank soll für Anbindungen von anderen Systemen her auch erreichbar sein.

Die Eingabe und Konfiguration soll über eine abstrakte Schnittstelle möglich sein, die verschiedene Endgeräte erlaubt.

## Projektteilnehmer
* Andreas Züger
* Endre Marczi
* Markus Schenk

## Projektname
WakeUp-Light mit Raspberry PI

## Schichten
1. Treiber (Ansteuerung jedes Pixels In Python)
2. Logik (Priorisierung von Inputs (Uhrzeit / Licht / Bewegung / manuell)
3. Datenbank für Speicherung (MySQL / mongo / postgreSQL)
4. Middleware (Apache, Python Bridge, python-rpc-json)
5. *~~GUI (Webseite / App)~~ (out of scope)*

## Skizze
![Projektskizze](/home/markus/ESHH/doku/Projektskizze.png  "Projektskizze")

## Anforderungen

### Software
* Übers Netzwerk ansprechbare Web Service Schnittstelle
* Web Server mit Web Service Support auf Raspberry PI
* Treiber für Zugriff auf WS2801 Pixelkette über GPIO
* Datenbank mit vernünftigem Footprint auf Raspberry PI
* Logikapplikation zur Komponentenverbindung

### Hardware
* WS2801 Pixelkette
* Bewegungssensor
* Schalter für manuelle Eingabe

## Erweiterungsideen
* LIFX (Lichtsteuerung über Applikation)
* Receiver (Musiksteuerung über Applikation)

## Termine
* 15.10.2016 : Abgabe Projektidee 15.10.2016
* 05.11.2016 : Abgabe Kontextdiagramm, Anforderungsliste, Terminplan
* 19.11.2016 : Abgabe Schaltungsentwurf / Softwareentwurf / Testkonzept
* 03.12.2016 : Präsenz
* 03.01.2017 : Abgabe Dokumentation
* 14.01.2017 : Präsentation