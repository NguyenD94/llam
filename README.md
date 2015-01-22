# LLAM
Learn like a Motherfucker

## Dependencies
- [NodeJS installieren](http://nodejs.org/download/)
- npm vom NodeJS im Terminal/CMD in PATH binden und folgende Befehle ausführen:
```
npm install -g bower grunt yo generator-jhipster
```
- [Maven installieren](http://maven.apache.org/download.cgi)
- [JDK8 installieren](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) und in der IDE angeben (JAVA_HOME sollte gesetzt sein)
- [PostgreSQL installieren](http://www.postgresql.org/download/)
- [Git installieren](http://git-scm.com/downloads)

## Repository hinzufügen und pullen
- Neuen Ordner erstellen, wo das Projekt rein soll
- Mit Terminal/CMD per `cd` in diesen Ordner wechseln (Git sollte im PATH liegen!)
- Folgende Befehle ausführen:
```
git init
```
Falls SSH Key in Github hinterlegt:
```
git remote add origin git@github.com:xeTaiz/llam.git
```
Wenn nicht, diesen hinzufügen (in Einstellungen, Tutorial liegt bei) ODER Folgendes tun (bei jedem Push/Pull wird dann euer GitHub Passwort benötigt....), falls ihr euren SSH Key hinterlegt habt NICHT ausführen!:
`git remote add origin https://github.com/xeTaiz/llam.git`
Nun habt ihr dieses Repository als Remote hinzugefügt, nun könnt ihr die Projektdaten wiefolgt pullen:
```
git pull origin master
```
Desweiteren bitte AutoRebase im Git anmachen:
```
git config --global branch.autosetuprebase always
```

## Weitere Abhängigkeiten installieren
Nach erstem Pull im `application` Ordner einen
```
npm install
```
ausführen um die node_modules zu laden


## Projekt starten
Die `application/src/main/java/de.uulm.llam/Application` starten um Dev zu starten, Seite ist dann auf
[http://localhost:8081/](http://localhost:8081/)
Alternativ kann man diesen Command im `application` Ordner ausführen:
```
mvn spring-boot:run
```
