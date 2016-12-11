# UseCase - Weckfunktion per LED-Pixelkette

1. Der Konfigurierte Weckzeitpunkt tritt ein
2. Der Treiber-Layer liest seine Aufgaben aus der Datenbank
3. Der Treiber-Layer erkennt, dass der Weckzeitpunkt erreicht ist
4. Der Treiber-Layer erkennt, dass die LED-Pixelkette angesprochen werden soll
5. Der Treiber-Layer startet den Dimm- und Farbverlaufprozess, anhand der Angaben in der DB-Aufgabe
6. Benutzer erwacht und steht auf
7. Benutzer betätigt Knopf zum ausschalten des Lichts
8. Wake-Up Light schaltet aus