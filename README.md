# Libreria

**Libreria** è una web application sviluppata in Java con Spring Boot che consente la gestione di libri e autori. Il progetto espone API RESTful per operazioni CRUD su entrambe le entità, pensato per un sistema di catalogazione semplice e intuitivo.

## 🚀 Tecnologie utilizzate

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 (o altro database compatibile)
- Gradle


## 📌 Funzionalità

- CRUD Libri
- CRUD Autori
- Collegamento tra libri e autori
- Gestione semplificata via API REST

## ▶️ Come eseguire il progetto

1. **Clona il repository**
   ```bash
   git clone https://github.com/Luca16-95/libreria.git
   cd libreria
   ```
Avvia l'applicazione

```bash
./gradlew bootRun
Accedi all'interfaccia H2 (se attivata)
Vai su http://localhost:8080/h2-console
(verifica credenziali in application.properties)
```

🧪 Test
```bash
./gradlew test
```
## 📬 API Endpoints principali

| Metodo | Endpoint      | Descrizione                |
|--------|---------------|----------------------------|
| GET    | /libri        | Lista di tutti i libri     |
| POST   | /libri        | Aggiungi un nuovo libro    |
| PUT    | /libri/{id}   | Modifica libro             |
| DELETE | /libri/{id}   | Elimina libro              |
| GET    | /autori       | Lista di tutti gli autori  |
| POST   | /autori       | Aggiungi un autore         |


👤 Autore
Luca Manzo
