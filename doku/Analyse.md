# Analyse

## Problembeschreibung
Viele Menschen starten - gerade in den dunklen Wintermonaten - sehr schlecht in den Tag, weil sie durch einen schrillen Weckton vor Sonnenaufgang geweckt werden. Gerade Menschen mit einem späten Chronotypen fühlen sich dadurch den ganzen Tag schläfrig und können oftmals weniger Leistung bringen. Oftmals führt dies zu einer ungesunden Überzufuhr von Thein. 

Der Markt hat auf diese Problematik mit sogenannten Wake-Up Lights reagiert. Ein Wake-Up Light simuliert einen künstlichen Sonnenaufgang zur gewünschten Weckzeit und verspricht so einen natürlicheren Aufwachvorgang. Die Wirksamkeit von Wake-Up Lights wurde auch schon in einer Studie von Giménez [Gim] bestätigt. 

Die existierenden Produkte am Markt sind meist stark eingebunden in ein bestehendes Produktökosystem, was ihre Bedienung vereinfacht aber wenig Erweiterungs- und Anbindungsmöglichkeiten bedeutet. Beispielsweise erlauben heutige Wake-Up Lights Weckmusik nur in Kombination mit lokalen Musikdateien auf dem Smartphone oder mit Musikdiensten. 

## Vision 
Mit einem Raspberry PI soll ein eigenes Wake-Up Light erstellt werden, dass über ein einfach zu bedienendes GUI konfiguriert werden kann. Der Benutzer kann das Wake-Up Light so konfigurieren, dass er zu einer bestimmten Zeit geweckt wird. Zusätzlich soll das Wake-Up Light auch reagieren, wenn der Benutzer in der Nacht aufsteht und ihn mit gedimmten Licht unterstützen aber nicht komplett wecken. Die Software auf dem Raspberry PI soll ausserdem in Zukunft noch weitere - allenfalls bereits bestehende - Geräte wie einen Receiver, oder Smart Lights ansprechen können um den Weckvorgang noch weiter auf den Benutzer zuzuschneidern. 

## Anforderungen
1. Das Wake-Up Light soll ein Leuchtmittel eine konfigurierbare Zeit vor dem geplanten Weckzeitpunkt anfangen zu erhellen (dimmen). 
2. Das Wake-Up Light soll auf Bewegungen reagieren, und ein Leuchtmittel in einen Nachtlichtmodus versetzen.
3.  Das Wake-Up Light soll das Leuchtmittel nur einschalten, wenn es nicht bereits hell in der Umgebung ist.
4. Das Wake-Up Light soll auch per Knopfdruck einschaltbar sein und so als eine normale Zimmerbeleuchtung dienen. 

### Use Cases
* Weckfunktion
	1. Weckzeitpunkt - konfigurierte Dimmzeit tritt ein
	1. Wake-Up Light startet Weckprozess (Leuchtmittel Dimm ein)
	1. Benutzer wacht auf und drückt Knopf
	1. Wake-Up Light schaltet aus
* Nachtlichtfunktion
	1. Benutzer steht auf
	1. Wake-Up Light prüft Umgebungslicht
		1. Wake-Up Light merkt dass es dunkel ist und startet Leuchtmittel
	1. Wake-Up Light schaltet sich automatisch aus
* Betrieb auf Knopfdruck
	1. Benutzer drückt Knopf
	1. Wake-Up Light schaltet ein
	1. Benutzer drückt Knopf erneut
	1. Wake-Up Light schaltet aus

### Kontextdiagramm
![Kontextdiagramm](/home/markus/ESHH/doku/Kontextdiagramm.png  "Kontextdiagramm Wake-Up Light")

## Zeitplan
### Rahmenbedingungen
* 15.10.2016 : Abgabe Projektidee
* 05.11.2016 : Abgabe Kontextdiagramm, Anforderungsliste, Terminplan
* 19.11.2016 : Abgabe Schaltungsentwurf / Softwareentwurf / Testkonzept
* 03.12.2016 : Präsenz
* 03.01.2017 : Abgabe Dokumentation
* 14.01.2017 : Präsentation

### Grobprojektplan
* 15.10.2016 - 05.11.2016 : Analyse
* 06.11.2016 - 19.11.2016 : Design
* 20.11.2016 - 03.01.2017 : Implementation
* 04.01.2017 - 10.01.2017 : Testing
* 11.01.2017 - 13.01.2017 : Präsentation erstellen

## Quellen
* [Gim] [Effects of artificial dawn on subjective ratings of sleep inertia and dim light melatonin onset.](https://www.ncbi.nlm.nih.gov/pubmed/20653451) 