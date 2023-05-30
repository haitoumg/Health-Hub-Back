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
| centre_id   | INT     | Unique identifier for the center |
| adresse     | VARCHAR | Address of the center            |
| centre_name | VARCHAR | Name of the center               |
| ville       | VARCHAR | Either Tetouan or Casablanca     |

## Personne

| Field         | Type    | Description                        |
|---------------|---------|----------------------------------  |
| personneId    | INT     | Unique identifier for the personne |
| nom           | VARCHAR | Last name of the personne          |
| prenom        | VARCHAR | first name of the personne         |
| email         | VARCHAR | Email of the personne              |
| mot_de_passe  | VARCHAR | Password                           |
| telephone     | VARCHAR | Phone number of the personne       |
| date_naissance| VARCHAR | Birthday of the personne           |
| role          | VARCHAR | Either a Docter or Employe         |
| centre_id     | INT     | Referance to the center            |

## Docteur

| Field       | Type    | Description                      |
|-------------|---------|----------------------------------|
| num_docteur | INT     | Unique identifier for the docter|
| specialité  | VARCHAR | Speciality of the docter        |
| personne_id | INT     | Referance to the table personne  |

## Employee

| Field       | Type    | Description                       |
|-------------|---------|-----------------------------------|
| num_employe | INT     | Unique identifier for the employee|
| personne_id | INT     | Referance to the table personne   |

## calendrier

| Field        | Type    | Description                       |
|--------------|---------|-----------------------------------|
| calendrier_id| INT     | Unique identifier for the calender|
| jour_travail | INT     | Referance to the table personne   |

## Rendez-vous

| Field           | Type    | Description                              |
|-----------------|---------|------------------------------------------|
| rendez_vous_id  | INT     | Unique identifier for the appointment    |
| annule          | BIT     | Indicates if the appointment is cancelled|
| accept          | BIT     | Indicates if the appointment is cancelled|
| date_rendez_vous| DATETIME| Timing of the appointment                |
| calendrier_id   | INT     | referance to the table calender          |
| personne_id     | INT     | referance to the table personne          |

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

http://localhost:9090/centres (GET)

### Deleting a center 

http://localhost:9090/centre/{id} (DELETE)


## Docteur

### Inserting a docteur

http://localhost:9090/docteur (POST)

{
    "nom":"nom",
    "prenom":"prenom",
    "dateNaissance": "1990-01-04",
    "telephone":"+212 6 00000",
    "email":"email@gamil.com",
    "motDePasse":"*******",
    "role":"Docteur",
    "centre":1,
    "specialité":"generaliste",
    "numDocteur":1
}

### Updating a docteur

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

### Showing a docteur 

http://localhost:9090/docteur/{id} (GET)

### Showing all docteur

http://localhost:9090/docteurs (GET)

### Deleting a docteur

http://localhost:9090/docteur/{id} (DELETE)

## Employee

### Inserting a employee

http://localhost:9090/employee (POST)

{
    "nom":"nom",
    "prenom":"prenom",
    "dateNaissance": "1990-01-04",
    "telephone":"+212 6 00000",
    "email":"email@gamil.com",
    "motDePasse":"*******",
    "role":"Docteur",
    "centre":1,
    "specialité":"generaliste",
    "numDocteur":1
}

### Updating a employee

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

### Showing a employee 

http://localhost:9090/employee/{id} (GET)

### Showing all employee

http://localhost:9090/employes (GET)

### Deleting a employee

http://localhost:9090/employe/{id} (DELETE)


### Others API *******

## Email sending

http://localhost:9090/sendMail (GET)

{
    "to":"yourgmail@gmail.com",
    "subject":"Test",
    "body":"Test"
}

## Forgetting password

