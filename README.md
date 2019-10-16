# PanoramaDOL 

This is a base project for: [[2]Kotlin][Kotlin] / [[3]Ktor][Ktor], integration with Google Spreadsheet and deploy on [[1]Heroku][Heroku].

For more details about integratio with Google Spreadsheets and deploy on Google Cloud, check this project: <https://github.com/mangar/google-cloud-kotlin-template>


## TODO
- Export API for AjusteFechamentos
- Security
    - HTTPS
    - CSRF
    - XSS 


## Google Spreadsheet

As a datasource the app uses a spreadsheet with the following structure:
Template: https://docs.google.com/spreadsheets/d/17jZR8Xb0ltkyzxd4N3qqbmxJlaO6Rp3uD6bXEkK3jgM/edit?usp=sharing

- Tab name
	__data_export__
- Columns (column name and format)
	- data (dd/mm/yyyy)
	- ajuste (4164,50)
	- fechamento (4164,50)
	- abertura (4164,50)
	- minima (4164,50)
	- maxima (4164,50)
	- estrangeirosOperacoes (126512)
	- estrangeirosPosicao (126512)
	- aberturaPanorama ([++|+|+/-|-|--])
	- estrangeiros([++|+|+/-|-|--])
	- indicesMundiais([++|+|+/-|-|--])
	- dx([++|+|+/-|-|--])
	- es([++|+|+/-|-|--])
	- cl([++|+|+/-|-|--])
	- noticias([++|+|+/-|-|--])
	- panorama([++|+|+/-|-|--])




## Database

__Install__

Get PostgreSQL here <https://postgresapp.com/>

__Client__

Get the client here <https://www.pgadmin.org/download/>


## Config 

1. Create a database named: ```panorama``` (```create database panorama;```)
2. Configure a Environment Variable on IntelliJ: ```JDBC_DATABASE_URL=jdbc:postgresql:panorama?user=postgres```



# Docker

__Build the image__

1. Build the App: ```mvn clean compile assembly:single ```
2. Create the machine: ```docker build -t panorama-dol:0.0.1 .```

__Configure network and DB__

3. Pull the Postgres machine: ```docker pull postgres```
4. Create a volume for Postgres: ```mkdir -p ~/docker/volumes/postgres-panorama-dol```
5. Create a docker network ```docker network create panorama-dol-net```
6. Starts the Postgress ```docker run --network panorama-dol-net --rm --name panorama-pg -e POSTGRES_PASSWORD=postgres -d -p 5432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql/data postgres```
7. Create the database ```docker exec -it panorama-pg psql -U postgres -c "create database panorama"```


__Fire the WebApp__

8. Run the docker image with the App: 
```
docker run --network panorama-dol-net -e JDBC_DATABASE_URL="jdbc:postgresql://panorama-pg:5432/panorama?user=postgres&password=postgres" -e PORT=8080 -m512M --cpus 2 -it -p 8080:8080 --name panorama-web --rm panorama-dol:0.0.1
```

__Enjoy__

9. Access: <http://localhost:8080>

---

__Copyright__

The MIT License (MIT)
Copyright (c) 2019 Marcio Mangar


 

 











[Heroku]: http://heroku.com "Heroku"
[Kotlin]: https://kotlinlang.org "Kotlin"
[Ktor]: https://ktor.io/ "Ktor"
