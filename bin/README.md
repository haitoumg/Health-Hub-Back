# HelathHub

Web application built in React and Spring Boot to manage the health services provided inside NTT DATA for its employees.

## Features

- Reservation for appointment
- Consulting schedule
- Notification by email
- Generates a diagnostic file during consumtation

## Database Schema

The project utilizes a relational database to store data. Below is an example of the database schema:

## Hub

| Field       | Type    | Description                    |
|-------------|---------|--------------------------------|
| hub_id      | INT     | Unique identifier for the hub  |
| address     | VARCHAR | Address of the hub             |
| hub_name    | VARCHAR | Name of the hub                |
| address     | VARCHAR | The city of the hub            |

## Personne

| Field         | Type    | Description                        |
|---------------|---------|----------------------------------  |
| personneId    | INT     | Unique identifier for the personne |
| last_name     | VARCHAR | Last name of the personne          |
| first_name    | VARCHAR | first name of the personne         |
| email         | VARCHAR | Email of the personne              |
| password      | VARCHAR | Password to login in               |
| telephone     | VARCHAR | Phone number of the personne       |
| birth_date    | VARCHAR | Birthday of the personne           |
| role          | VARCHAR | Either a Docter or Employe or Admin|
| hub_id        | INT     | Referance to the hub               |

## Docteur

| Field       | Type    | Description                      |
|-------------|---------|----------------------------------|
| num_docteur | INT     | Unique identifier for the docter |
| specialty   | VARCHAR | Speciality of the docter         |
| personne_id | INT     | Referance to the table personne  |

## Employee

| Field       | Type    | Description                       |
|-------------|---------|-----------------------------------|
| num_employe | INT     | Unique identifier for the employee|
| personne_id | INT     | Referance to the table personne   |

## calendar

| Field        | Type    | Description                       |
|--------------|---------|-----------------------------------|
| calendar_id  | INT     | Unique identifier for the calender|
| working_day  | DATETIME| The date of working for the doctor|
| start_time   | TIME    | The start timing for working      |
| end_time     | TIME    | The end timing for working        |
| personne_id  | INT     | Referance to the table personne   |

## Appointment

| Field           | Type    | Description                              |
|-----------------|---------|------------------------------------------|
| appointment_id  | INT     | Unique identifier for the appointment    |
| annule          | BIT     | Indicates if the appointment is cancelled|
| date_appointment| DATETIME| Timing of the appointment                |
| calendar_id     | INT     | referance to the table calender          |
| personne_id     | INT     | referance to the table personne          |

## Usage

To test the application, please follow these instructions :

- After cloning the project to your IDE , make sure before testing to launch MySQL and run the java class HealthHubApplication
- By typing in the URL of your browser or API development tool (Postman for example), try this :

## Hub 

### Inserting a hub 

http://localhost:9090/hub (POST)

{
  "hubName": "Example hub",
  "city": "Tetouan",
  "address": "Example Address"
}

### Updating a hub 

http://localhost:9090/hub/{id} (PUT)

  {
   		"hubName": "Updated Example",
        "city": "Casablanca",
        "address": "Updated Address"
    }
 
### Showing a hub  

http://localhost:9090/hub/{id} (GET)

### Showing all hub s

http://localhost:9090/hubs (GET)

### Deleting a hub  

http://localhost:9090/hub/{id} (DELETE)


## Docteur

### Inserting a docteur

http://localhost:9090/docteur (POST)

{
    "last_name":"last_name",
    "first_name":"first_name",
    "dateNaissance": "1990-01-04",
    "telephone":"+212 6 00000",
    "email":"email@gamil.com",
    "motDePasse":"*******",
    "role":"Docteur",
    "hub":1,
    "specialité":"generaliste",
    "numDocteur":1
}

### Updating a docteur

http://localhost:9090/hub/{id} (PUT)

{
  "last_name": "Updated last_name",
  "first_name": "Updated first_name",
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
    "last_name":"last_name",
    "first_name":"first_name",
    "dateNaissance": "1990-01-04",
    "telephone":"+212 6 00000",
    "email":"email@gamil.com",
    "motDePasse":"*******",
    "role":"Docteur",
    "hub":1,
    "specialité":"generaliste",
    "numDocteur":1
}

### Updating a employee

http://localhost:9090/hub/{id} (PUT)

{
  "last_name": "Updated last_name",
  "first_name": "Updated first_name",
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

