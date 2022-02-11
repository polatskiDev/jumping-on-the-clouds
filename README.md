## **Jumping on The Clouds - Assesment**

This project is developed to solve the JOTC problem.

- JOTCSolutionConsole class is a standalone console application.
- JOTCController class is REST API Spring Boot Application.

For storing users, request for the problem and response H2 in-memory database is used.
Also for admin based services, Role management is applied.

When the spring boot application started, after server is up, example datas will be inserted
in user table and as well as role table.

Example data is :
For USER_INFO table;
    full_name : Daniel Deege
    email: d.deege@tech-lab.io
For USER_ROLE table;
    user : USERINFO of above record
    role_code: admin

which means the user who has admin role mentioned above tables can access admin based services.

The swagger link for the project is :
http://localhost:8080/techlab-assessment/swagger-ui/index.html
