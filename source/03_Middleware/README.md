# 03_Middleware

In diesem Ordner befinden sich die Source-Dateien zum Middlelayer und der REST-API. 
Der Ordner 

## src/ch/ffhs/eshh/wakeuplight/data
Dieser Ordner enthält die Klassen der Datenbankverbindung und der ORM-Klasse. 

## src/ch/ffhs/eshh/wakeuplight/interfaces
Dieser Ordner enthält das IDBProxy Interface, dass von der Datenbankklasse implementiert werden muss, 
wenn es als Proxy zwischen API und Datenbank dienen will. 

## src/ch/ffhs/eshh/wakeuplight/model
Enthält POJOs zur einheitlichen Datendarstellung. Diese Klassen werden von der ORM-Klasse benutzt um die relationalen Datenbankwerte 
auf Java-Klassen zu mappen. 

## src/ch/ffhs/eshh/wakeuplight/notused
Dieser Ordner enthält Klassen, die nicht in Benutzung sind, aber noch zur Referenz vorhanden sind. 

## src/ch/ffhs/eshh/wakeuplight/resources
Enthält die Endpunkte für die REST-API. Die Klassen sind mit JAX-RS Annotationen versehen, die dafür sorgen, dass tomcat die 
HTTP-Requests an die richtigen Resourcen leitet. 
