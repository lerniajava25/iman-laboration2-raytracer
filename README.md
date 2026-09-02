# RaytracerOOP

Detta projekt är skapat som en laboration i objektorienterad programmering i Java.

Projektet demonstrerar bland annat:

- Klasser
- Arv
- Method overriding
- Interface
- Polymorfism
- Collections
- Inkapsling

## Projektstruktur

### Vector3D

`Vector3D` representerar en tredimensionell vektor med värden för x, y och z.

Klassen innehåller funktioner för bland annat:

- addition
- subtraktion
- multiplikation med ett skalärt värde
- dot product
- cross product
- beräkning av vektorns längd
- normalisering

`Vector3D` används bland annat för positioner, riktningar och normalvektorer i raytracern.

### Color

`Color` representerar en färg.

Klassen används av de geometriska objekten för att lagra och hantera deras färg.

### Ray

`Ray` representerar en stråle i det tredimensionella rummet.

En Ray innehåller:

- en startpunkt
- en riktning

Klassen kan även beräkna en punkt längs strålen.

### HitRecord

`HitRecord` lagrar information om en träff mellan en `Ray` och ett geometriskt objekt.

Informationen omfattar bland annat:

- avståndet till träffen
- träffpunkten
- normalvektorn
- objektets färg

### Hittable

`Hittable` är ett interface för objekt som kan träffas av en `Ray`.

Klasser som implementerar detta interface måste implementera metoden:

```java
Optional<HitRecord> hit(Ray ray);
```

Metoden returnerar information om träffen om strålen träffar objektet.

Om ingen träff sker returneras en tom `Optional`.

### Shape

`Shape` är en abstrakt basklass för geometriska objekt.

Klassen implementerar `Hittable` och innehåller gemensamma egenskaper för geometriska former, till exempel färg.

`Shape` används som basklass för bland annat:

- `Sphere`
- `Triangle`

### Sphere

`Sphere` ärver från `Shape`.

Klassen representerar en sfär med:

- centrum
- radie
- färg

Klassen override:ar metoden:

```java
Optional<HitRecord> hit(Ray ray);
```

Metoden beräknar om en `Ray` träffar sfären.

Om en träff sker skapas ett `HitRecord` med information om bland annat träffpunkt, avstånd, normalvektor och färg.

### Triangle

`Triangle` ärver från `Shape`.

Klassen representerar en triangel med tre hörnpunkter.

Klassen innehåller bland annat:

- validering av hörnpunkter
- kontroll av ogiltiga och kollineära hörnpunkter
- beräkning av normalvektor
- ray-triangle intersection
- skapande av `HitRecord`

Klassen override:ar metoden:

```java
Optional<HitRecord> hit(Ray ray);
```

### Scene

`Scene` ansvarar för en samling objekt i raytracern.

Objekten lagras med Java Collections:

```java
List<Hittable>
```

Den interna listan implementeras med:

```java
ArrayList
```

Klassen innehåller funktioner för att:

- lägga till objekt
- ta bort objekt
- läsa objekten
- kontrollera antal objekt
- kontrollera om scenen är tom
- rensa scenen

Eftersom listan använder typen `Hittable` kan olika typer av objekt lagras i samma collection.

Till exempel kan både:

```java
Sphere
```

och:

```java
Triangle
```

lagras i:

```java
List<Hittable>
```

Detta demonstrerar polymorfism.

## Objektorienterade principer

### Klasser

Projektet består av flera klasser med olika ansvarsområden.

Exempel:

- `Vector3D`
- `Color`
- `Ray`
- `HitRecord`
- `Shape`
- `Sphere`
- `Triangle`
- `Scene`

Varje klass har ett tydligt ansvar i programmet.

### Arv

`Sphere` och `Triangle` ärver från den abstrakta klassen `Shape`.

Exempel:

```java
public class Sphere extends Shape
```

och:

```java
public class Triangle extends Shape
```

Det gör att gemensam funktionalitet kan placeras i basklassen `Shape`.

### Interface

`Hittable` används som ett gemensamt interface för objekt som kan träffas av en `Ray`.

Interface-metoden är:

```java
Optional<HitRecord> hit(Ray ray);
```

Det gör att olika objekt kan behandlas genom samma typ.

### Method override

Underklasserna implementerar sina egna versioner av metoden:

```java
Optional<HitRecord> hit(Ray ray);
```

Både `Sphere` och `Triangle` har därför sin egen beräkning för hur en stråle träffar objektet.

Flera klasser override:ar även:

```java
toString()
```

för att ge en tydlig textrepresentation av objektet.

### Polymorfism

Polymorfism används genom typen:

```java
Hittable
```

Eftersom både `Sphere` och `Triangle` kan behandlas som `Hittable` kan de exempelvis lagras tillsammans i:

```java
List<Hittable>
```

### Collections

`Scene` använder Java Collections för att hantera flera objekt.

Samlingen deklareras som:

```java
List<Hittable>
```

och implementeras med:

```java
ArrayList
```

Det gör det möjligt att dynamiskt lägga till och ta bort objekt från scenen.

### Inkapsling

Klassernas data lagras i privata fält.

Exempelvis har `Vector3D` privata koordinater och andra klasser har privata egenskaper som centrum, radie, färg och hörnpunkter.

Åtkomst sker genom publika metoder.

## Teknik

Projektet använder:

- Java 17
- Maven
- Git
- GitHub
- Pull Requests
- CodeRabbit

## Kompilera projektet

Projektet kan kompileras med Maven.

Kör:

```bash
mvn clean compile
```

Om projektet kompilerar korrekt visas:

```text
BUILD SUCCESS
```

## Git och utvecklingsflöde

Utvecklingen har gjorts med separata branches för olika delar av programmet.

Exempel på utvecklingsflöde:

1. Skapa en ny branch.
2. Implementera en funktion eller klass.
3. Kontrollera projektet med Maven.
4. Commita ändringarna.
5. Pusha branchen till GitHub.
6. Skapa en Pull Request.
7. Använd CodeRabbit för kodgranskning.
8. Rätta eventuell feedback.
9. Merga Pull Request till `main`.

Detta gör utvecklingen mer strukturerad och gör det lättare att granska förändringar innan de läggs till i huvudbranchen.

## CodeRabbit

CodeRabbit har använts för kodgranskning i projektets Pull Requests.

Feedback från CodeRabbit har bland annat använts för att förbättra:

- validering
- ray-object intersection
- hantering av geometriska beräkningar
- dokumentation

## Utöka projektet

Projektstrukturen gör det möjligt att lägga till fler geometriska objekt.

En ny form kan till exempel skapas genom att ärva från:

```java
Shape
```

och implementera:

```java
Optional<HitRecord> hit(Ray ray);
```

