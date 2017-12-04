Bloom-Filter
===

Projekt von Hasan Kara und Michael Hauser

## Idee des Bloom-Filters
Ein Bloom-Filter ist eine statistische Datenstruktur,
mit welcher effizient überprüft werden kann, ob ein Element in einer Menge
vorhanden ist oder nicht. Dabei kann man sicher sein, ob ein Element sicher nicht
in der Menge ist. Dass ein Element sicher in der Menge vorhanden ist, weiss man
normalerweise nicht.

Die Funktionsweise ist wie folgendermassen:
- Es gibt ein Array der Grösse m. Alle Felder haben am Anfang den Wert 0.
- Es gibt k Hashfunkionen welche für ein Wort eine Position des Arrays berechnen.
- **Einfügen eines Wertes in den Filter:** An jeder der berechneten Positionen wird eine 1 geschrieben.
- **Überprüfen, ob ein Wert vorhanden ist:** mit den Hashfunktionen die Arraypositionen berechnen.
  Falls an mind. einer dieser Positionen eine 0 steht, ist der Wert sicher nicht vorhanden.
  Sonst ist der Wert evtl. vorhanden.

### Vorteile
- Sehr effizientes Überprüfen, ob ein Wert Element einer Menge ist (auch bei sehr grossen Datenmengen)
- Der Bloom Filter speichert nicht die Werte selbst und braucht somit viel weniger Speicherplatz als z.B. eine Hashtabelle

### Nachteile
- Eignet sich nur zum überprüfen, ob ein Wert sicher nicht Element einer Menge ist.
  Man kann (zumindest bei einer Standard-Implementierung) nicht sicher sein ob ein
  Element sicher vorhanden sind.
- Normalerweise muss man die Grösse des Bloom Filters bestimmen bevor man ihn erstellt.
  Dies kann man zu diesem Zeitpunkt unter Umständen nur grob abschätzen.
  (Ein ["Scaleable Bloom Filter"](http://gsd.di.uminho.pt/members/cbm/ps/dbloom.pdf) wäre da eine Alternative).
- Das Hinzufügen von Werten zum Bloom Filter ist in der Regel aufwändiger als z.B.
  bei einer Hashtabelle, da man k Hashfunktionen berechnen und dementsprechend viele Werte im
  Array ändern muss.
- Man kann (zumindest bei einer Standard-Implementierung) keine Elemente entfernen
  und auch nicht feststellen welche Elemente schon vorhanden sind.

## Beispiel aus der Praxis
Google Chrome (oder zumindest gewisse Versionen davon) verwendet einen Bloom-Filter um eine Webseite schnell als schädlich oder
unschädlich einzustufen. Der Bloom-Filter enthält dabei die bekannten schädlichen Webseiten.
Falls eine Webseite nicht im Filter gefunden wird, ist sie sicher nicht schädlich
(zumindest gemäss der Datenbank der bekannten schädlichen Seiten). Falls die geprüfte Seite
im Filter vorhanden ist, ist sie mit hoher Wahrscheinlichkeit schädlich.
Um dies mit Sicherheit zu Überprüfen wird dann eine zusätzliche Anfrage an eine Datenbank von Google gesendet.

Quelle: http://blog.alexyakunin.com/2010/03/nice-bloom-filter-application.html

## Beschreibung Fehlerwahrscheinlichkeit
Die Fehlerwahrscheinlichkeit wurde mit folgenden Schritten getestet:  
1. Alle Wörter in den Bloom-Filter einfügen.
2. x-Anzahl überprüfungen von Wörter, die garantiert nicht im words.txt sind, durchführen (Zahlen von 0 bis x).
3. Für alle false positives inkrementieren
4. Error rate = 100 / words.size * false positives

### Test-Resultate
Können selber durchgeführt werden, indem `double errorProbability` und `int numberOfNonExistingEntries` im `Main.java` 
angepasst wird und das Programm ausgeführt wird.  

Beispiel Ausgabe unseres Programms:  
```
number of non existing entries=100000
n=58110
p=0.001
false positives=94
errorRate in percent=0.16176217518499397
BloomFilter{m=835481, k=9}
```
