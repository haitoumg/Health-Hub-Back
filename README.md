# HelathHub

Web application built in React and Spring Boot to manage the health services provided inside NTT DATA for its employees.

## Features

- Reservation for appointment
- Consulting schedule
- Notification by email

## Database Schema

The project utilizes a relational database to store data. Below is an example of the database schema:
+-------------+---------+----------------------------------+
| Field       | Type    | Description                      |
|-------------|---------|----------------------------------|
| centre_id   | INT     | Unique identifier for the centre |
| adresse     | VARCHAR | Address of the centre            |
| centre_name | VARCHAR | Name of the centre               |
| ville       | VARCHAR | Either Tetouan or Casablanca     |
 +------------+---------+----------------------------------+
## Usage

To test the application, please follow these instructions :

- After cloning the project to your IDE , make sure before testing to launch MySQL and run the java class HealthHubApplication
- By typing in the URL of your browser or API development tool (Postman for example), try this :

##Center

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

### Others API *******

## Email sending

http://localhost:9090/sendMail (GET)

{
    "to":"yourgmail@gmail.com",
    "subject":"Test",
    "body":"Test"
}

