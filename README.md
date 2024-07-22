[![Java CI with Maven](https://github.com/conorheffron/marjaderoniet/actions/workflows/maven.yml/badge.svg)](https://github.com/conorheffron/marjaderoniet/actions/workflows/maven.yml)

![Proof HTML](https://github.com/conorheffron/marjaderoniet/actions/workflows/proof-html.yml/badge.svg)

![Auto Assign](https://github.com/conorheffron/marjaderoniet/actions/workflows/auto-assign.yml/badge.svg)

Personal website for women's wear designer http://marjaderoniet.com/

Tech Stack: Java 21, Spring Boot 3, angularJS 1.6.1, HTML5, & JQuery

## Build project, run unit & integration tests, & generate jar file.
```
mvn clean package
```

## Run application
```
mvn spring-boot:run
```

## Build & run via Docker
```
docker image build -t marjaderoniet .
docker compose up -d
```

## View Single Page Application (Angular SPA)
```
http://localhost:8080/
```

![image](https://github.com/user-attachments/assets/ba8389b3-0bb3-42dc-bc21-d6d2267a92c0)


## View contact requests in embedded DB
```
http://localhost:8080/contact
```
![image](https://github.com/user-attachments/assets/5c39f20e-dabb-4a83-815d-0e27c22b44c6)

```
http://localhost:8080/h2-console
```

![image](https://github.com/user-attachments/assets/d8cf95f9-58b8-4918-93aa-6f99721676b2)

![image](https://github.com/user-attachments/assets/c90a3fc1-a751-432d-9aa9-28d065352eb9)


