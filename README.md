# demo-jdbc

This project is for learning purpose.

## Installation

Prerequisites : 
- Java 21
- Maven
- Docker Compose

1. Clone this project
```sh
git clone git@github.com:SullyvanBoulanger/demo-jdbc.git
```

## Launch

1. (Optional) Run Docker for database
```sh
docker compose up -d
```

2. Edit `src/main/resources/database.properties` to match your need
```conf
# Default values are suited to match Docker compose container 
database.url=jdbc:mariadb://localhost:3306/compta
database.user=cats4ever
database.password=cats4life
```

3. Run java classes you want