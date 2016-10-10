# ES&HH

## Idee
LED Lichterkette unter Bett gesteuert durch Raspberry PI. Jede LED in der Kette kann einzeln angesteuert werden. Die Steuerung soll auf Licht, Bewegung, Uhrzeit und manuelle Eingabe reagieren k�nnen. Die Uhrzeit soll in einer Datenbank geplant / gespeichert werden k�nnen. Die Datenbank soll f�r Anbindungen von anderen Systemen her auch erreichbar sein. 
Die Eingabe und Konfiguration soll �ber eine abstrakte Schnittstelle m�glich sein, die verschiedene Endger�te erlaubt. 

## Projektname
?? (Wake-Up-Light?)

## Schichten
1. Treiber (Ansteuerung jeder einzelnen LED In Python)
2. Logik (Priorisierung von Inputs (Uhrzeit / Licht / Bewegung / manuell)
3. Datenbank f�r Speicherung (MySQL / mongo / postgreSQL)
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
* 03.12.2016 : Pr�senz
* 03.01.2017 : Abgabe Dokumentation
* 14.01.2017 : Pr�sentation