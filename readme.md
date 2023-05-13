# About This Project # 

[![NPM](https://img.shields.io/npm/l/react)](https://github.com/devsuperior/sds1-wmazoni/blob/master/LICENSE) 


This project was created according to the needs of a farm.

The project will manage the milk production, and make monthly reports about the production.

When sending the post method

{
  "date": "2023-05-13",
  "periodTime": "MANHA",
  "quantity": 0
}

The system automatically creates monthlyMilk linked to milk, Every new registration the monthly is updated

At the end of the month, the user should make a post telling the value of each liter of milk

http://localhost:8080/v1/monthly/price?month=05&year=2023&price=4
HTTP POST METHOD

The system will automatically calculate what the monthly income of the property will be.

After the system has updated everything, the client can download a pdf file with the records.

http://localhost:8080/v1/monthly/pdf

To run any endpoint you will need basic auth, The data is in the security config below








## Entity UML
![UML](https://github.com/BrunoTotoli/Assets/blob/35ba7b290c1bb27c5fff646f2ad063aae692a7dc/MonthlyMilk1.png)


## DTO UML
![DTOUML](https://github.com/BrunoTotoli/Assets/blob/35ba7b290c1bb27c5fff646f2ad063aae692a7dc/MilkPostRequestBody1.png)


## Running

To run this projects you need:

- Docker

- JDK 17
 
**git clone https://github.com/BrunoTotoli/java-restapi-milkproject.git**
 
**Write in terminal 'docker compose up' to run database**

**And 'mvn spring-boot:run' to run the project**

Once executed, you can access the documentation

http://localhost:8080/swagger-ui/


## Security Configs

### Admin:

Username:admin

Password:senha
****
### User:

Username:user

Password:senhauser

## Dependencies

[MapStruct 1.5.2 ](https://mapstruct.org/)

[SpringFoxSwaggerUI 3.0](https://springfox.github.io/springfox/docs/current/)

[LibrePDF 1.3.30](https://github.com/LibrePDF/OpenPDF)

[Postgresql 42.5.0](https://www.postgresql.org/)

[Spring Boot 2.7.5](https://spring.io/projects/spring-boot)

- Starter Web 
- Security
- Data-JPA
- Hibernate
- Lombok
- DevTools
- Test
- Validation

[H2 Database](https://www.h2database.com/html/main.html)


## Author

Bruno Totoli

https://www.linkedin.com/in/bruno-totoli-36331a178/










