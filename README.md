# HelathHub

Web application built in React and Spring Boot to manage the health services provided inside NTT DATA for its employees.

## Features

- Reservation for appointment
- Consulting schedule
- Notification by email

## Database Schema

The project utilizes a relational database to store data. Below is an example of the database schema:

## Centre

| Field       | Type    | Description                      |
|-------------|---------|----------------------------------|
| centre_id   | INT     | Unique identifier for the centre |
| adresse     | VARCHAR | Address of the centre            |
| centre_name | VARCHAR | Name of the centre               |
| ville       | VARCHAR | Either Tetouan or Casablanca     |

## Personne

| Field         | Type    | Description                        |
|---------------|---------|----------------------------------  |
| personneId    | INT     | Unique identifier for the personne |
| nom           | VARCHAR | Name of the personne               |
| prenom        | VARCHAR | Prenom of the personne             |
| email         | VARCHAR | Email of the centre                |
| mot_de_passe  | VARCHAR | Password                           |
| telephone     | VARCHAR | Number of the phone                |
| date_naissance| VARCHAR | Birthday of the personne           |
| role          | VARCHAR | Either a Docteur or Employe        |

## Usage

To test the application, please follow these instructions :

- After cloning the project to your IDE , make sure before testing to launch MySQL and run the java class HealthHubApplication
- By typing in the URL of your browser or API development tool (Postman for example), try this :

## Center

### Inserting a center

http://localhost:9090/centre (POST)

{
  "centreName": "Example Centre",
  "ville": "Tetouan",
  "adresse": "Example Address"
}

### Updating a center

http://localhost:9090/centre/{id} (PUT)

  {
   		"centreName": "Updated Example",
        "ville": "Tetouan",
        "adresse": "Updated Address"
    }
 
### Showing a center 

http://localhost:9090/centre/{id} (GET)

### Showing all centers

http://localhost:9090/centre (GET)

### Deleting a center 

http://localhost:9090/centre/{id} (DELETE)


## Personne

### Inserting a personne

http://localhost:9090/personne (POST)

{
  "nom": "Nom",
  "prenom": "Prenom",
  "dateNaissance": "01-01-1999",
  "telephone": "+212 000000000",
  "email": "email@gmail.com",
  "motDePasse": "****",
  "role" : "Docteur"  
}

### Updating a personne

http://localhost:9090/centre/{id} (PUT)

{
  "nom": "Updated Nom",
  "prenom": "Updated Prenom",
  "dateNaissance": "01-01-1999",
  "telephone": "+212 000000000",
  "email": "email@gmail.com",
  "motDePasse": "****",
  "role" : "Docteur"  
}

### Showing a personne 

http://localhost:9090/personne/{id} (GET)

### Showing all personnes

http://localhost:9090/centre (GET)

### Deleting a person

http://localhost:9090/personne/{id} (DELETE)


### Others API *******

## Email sending

http://localhost:9090/sendMail (GET)

{
    "to":"yourgmail@gmail.com",
    "subject":"Test",
    "body":"Test"
}

