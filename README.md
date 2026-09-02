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

Representerar en tredimensionell vektor med värden för x, y och z.

Klassen innehåller funktioner för bland annat:

- addition
- subtraktion
- multiplikation
- dot product
- cross product
- beräkning av längd
- normalisering

### Color

Representerar en färg med färgkomponenter.

Klassen används av de geometriska objekten för att ange deras färg.

### Ray

Representerar en stråle i 3D-rummet.

En Ray innehåller:

- startpunkt
- riktning

Klassen kan även beräkna en punkt längs strålen.

### HitRecord

Lagrar information om en träff mellan en Ray och ett geometriskt objekt.

Informationen omfattar bland annat:

- avstånd till träffen
- träffpunkt
- normalvektor
- färg

### Hittable

`Hittable` är ett interface för objekt som kan träffas av en Ray.

Klasser som implementerar detta interface måste implementera metoden:

```java
hit(Ray ray)