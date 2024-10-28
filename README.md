# Budget App

En Android-app där man säkert kan följa sitt spenderande och sätta upp sparandemål.

## Innehållsförteckning
1. [Skärmdumpar](#skärmdumpar)
2. [Teknologier](#teknologier)
3. [Installation](#installation)
4. [Funktioner](#funktioner)
5. [Arkitektur / Struktur](#arkitektur--struktur)
6. [Framtida Utveckling](#framtida-utveckling)

## Skärmdumpar


## Teknologier
- **Android**: Kotlin
- **Backend**: Java Spring Boot
- **Databas**: MySQL

## Installation
För att använda appen med API och databas, följ dessa steg:

1. Gå till valfri mapp.
2. I adressfältet, skriv `cmd` och tryck på **Enter**.
3. Klona projektet:
   
```bash
git clone https://github.com/AlexanderJson/Mobilt_Java23_Alexander_Jansson_API_intergration_v4
```

```bash
git clone https://github.com/AlexanderJson/Mobilt_Java23_Alexander_Jansson_API_intergrationv4-SPRING_API
```

## Funktioner
- **Registrering/Logga in**: Sker med hashing och JWT-token.
- **Lägg till transaktioner**: Spara transaktioner i databasen.
- **Visa till transaktioner**: Hämta transaktioner från databasen.


## Arkitektur / Struktur
Appens struktur är baserad på följande mönster:

- **API-nätverk** ➔ `api`-klass (HTTP requests) ➔ `repository` som returnerar http resultat ➔ `service`-klass (extra logik) ➔ `viewmodels` för livscykelhantering.
- Använder **Coroutines** och **LiveData** i alla sammanhang.
- Säker data (som autentiseringstoken) sparas krypterat i `SharedPreferences`.
- **Data-klasser** används för att hantera HTTP-responser.

## Framtida Utveckling
- **Ta bort transaktion** (på väg).
- **Sparmål**: Användaren ska kunna sätta upp och anpassa budgetmål.
- **Statistik och Notiser**: Statistik över utgifter visas i appen, med varningsnotiser.


