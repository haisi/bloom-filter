Bloom-Filter
===

Projekt von Hasan Kara und Michael Hauser

## Idee des Bloom-Filters
Ein Bloom-Filter ist ein statistische Datenstruktur, 
mit der man effizient überprüfen kann, ob ein Element in einer Menge 
vorhanden ist oder nicht. Dabei kann mann sicher sein, dass ein Element 
sicher nicht in der Menge ist. Dass ein Element ganz sicher in der Menge ist,
weiss man unter umständen nicht. 

Die Funkionsweise ist wie folgendermassen:
- Es gibt ein Array der Grösse m. Alle Felder sind am Anfang auf 0.
- Es gibt k Hashfunkionen welche für ein Wort eine Position des Arrays berechnen
- Einfügen eines Wertes in den Filter: An jeder der berechneten Positionen wird eine 1 geschrieben
- Überprüfen, ob ein Wert vorhanden ist: mit den Hashfunktionen die Arraypositionen berechnen.
  Falls an mind. einer dieser Positionen eine 0 steht, ist der Wert sicher nicht vorhanden.
  Sonst ist der Wert evtl. vorhanden.

### Vorteile
- Sehr effizientes überprüfen, ob ein Wert element einer Menge ist (auch bei sehr grossen Mengen)
- Der Bloom Filter speichert nicht die Werte selbst und ist somit ressourcensparender als z.B. eine Hashtabelle

### Nachteile
- Eignet sich nur zum überprüfen, ob ein Wert sicher nicht Element einer Menge ist.
  Man kann (zumindest bei einer Standard-Implementierung) nicht sicher sein ob ein
  Element sicher vorhanden sind.
- Normalerweise muss man die Grösse des Bloom Filters bestimmen bevor man ihn erstellt.
  Dies kann man zu diesem Zeitpunkt unter Umständen nur grob abschätzen.
  (Ein ["Scaleable Bloom Filter"](http://gsd.di.uminho.pt/members/cbm/ps/dbloom.pdf) wäre da eine Alternative).
- Das Hinzufügen von Werten zum Bloom Filter ist in der Regel aufwändiger als z.B.
  bei einer Hashtabelle, da man k Hashfunktionen berechnen dementsprechend viele Werte im 
  Array ändern muss.
- Man kann (zumindest bei einer Standard-Implementierung) keine Elemente entfernen
  und auch nicht feststellen welche Elemente schon vorhanden sind.

## Beispiel aus der Praxis
Google Chrome (oder zumindest gewisse Versionen davon) verwendet einen Bloomfilter um eine Webseite schnell als schädlich oder 
unschädlich einzustufen. Der Bloomfilter enthält dabei die bekannten schädlichen Webseiten.
Falls eine Webseite nicht im Filter gefunden wird, ist sie sicher nicht schädlich 
(zumendest gemäss den Listen der Bekannten schädlichen Seiten). Falls die geprüfte Seite
im Filter vorhanden ist, ist sie mit hoher Wahrscheinlichkeit schädlich.
Um dies mit Sicherheit zu Überprüfen wird eine Anfrage an eine Datanbank von Google gesendet
welche dann normal durchsucht wird.

Quelle: http://blog.alexyakunin.com/2010/03/nice-bloom-filter-application.html

## Beschreibung Fehlerwahrscheinlichkeit

### Test-Resultate
