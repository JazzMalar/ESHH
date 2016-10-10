# ES&HH

## Idee
LED Lichterkette unter Bett gesteuert durch Raspberry PI. Jede LED in der Kette kann einzeln angesteuert werden. Die Steuerung soll auf Licht, Bewegung, Uhrzeit und manuelle Eingabe reagieren können. Die Uhrzeit soll in einer Datenbank geplant / gespeichert werden können. Die Datenbank soll für Anbindungen von anderen Systemen her auch erreichbar sein. 
Die Eingabe und Konfiguration soll über eine abstrakte Schnittstelle möglich sein, die verschiedene Endgeräte erlaubt. 

## Projektname
?? (Wake-Up-Light?)

## Schichten
1. Treiber (Ansteuerung jeder einzelnen LED In Python)
2. Logik (Priorisierung von Inputs (Uhrzeit / Licht / Bewegung / manuell)
3. Datenbank für Speicherung (MySQL / mongo / postgreSQL)
4. Middleware (Apache, Python Bridge, python-rpc-json)
5. GUI (Webseite / App)

## Erweiterungsideen
* LIFX
* Receiver


## Links
* https://www.digitalocean.com/community/tutorials/how-to-set-up-an-apache-mysql-and-python-lamp-server-without-frameworks-on-ubuntu-14-04


## Termine
* 15.10.2016 : Abgabe Projektidee 15.10.2016
* 05.11.2016 : Abgabe Kontextdiagramm, Anforderungsliste, Terminplan
* 19.11.2016 : Abgabe Schaltungsentwurf / Softwareentwurf / Testkonzept
* 03.12.2016 : Präsenz
* 03.01.2017 : Abgabe Dokumentation
* 14.01.2017 : Präsentation