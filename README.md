# oslo-bikes
En liten applikasjon for å få oversikt over tilgjengelige bysykler i Oslo.

Skrevet i React med Spring Boot.

Koden er delt opp i backend- og frontend-mapper.
## Requirements
- NPM
- Maven
- JDK 8

Egen `Identifier` kan settes ved å oppdatere filen `backend/src/main/resources/application.properties` og erstatte  `identifier=SETT_IDENTIFIER_HER`.

## Build
For å bygge en release-versjon må man først inn i frontend-mappen og bygge frontend-prosjektet.

Kjør følgende for å bygge koden:
`npm i && npm run build`

Gå deretter til backend-mappen og kjør følgende for å bygge backend-prosjektet.
`mvn clean install`

Man får da en `.jar`-fil som kan kjøres med:
`java -jar target/oslo-bikes-0.0.1-SNAPSHOT.jar`

Appen kan nås fra nettleseren på http://localhost:8080.

## Development
For å kjøre frontend og backend som to separate prosjekter kan man starte frontend-prosjektet med:
`npm run start`
Og backend prosjektet med:
`mvn spring-boot:run`

Da kan man nå frontend på http://localhost:3000/ og backend-endepunktet på http://localhost:8080/api/bikes

---
Inneholder data under Norsk lisens for offentlige data (NLOD) 2.0 tilgjengeliggjort av Oslo Bysykkel.
