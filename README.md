# TheClinicSystem
Projekat iz predmeta Internet softverske arhitekture i Projektovanje softvera za školsku 2019/2020 godinu.

Za pokretanje projekta neophodno je imati instalirano:
1) [Eclipse](https://www.eclipse.org/)
2) [Spring Tools 4 aka Spring Tool Suite 4](https://marketplace.eclipse.org/content/spring-tools-4-aka-spring-tool-suite-4)
3) [Lombok](https://projectlombok.org/)
4) [Maven](https://maven.apache.org/)

## Pokretanje programa

Projekat je neophodno klonorati ili preuzeti sa [GitHub](https://github.com/markoercegovac/TheClinicSystem)-a

Potrebna konfiguracija fajla **aplication.properties** za bazu:
```bash
spring.datasource.url = jdbc:h2:file:~/TheClinicSystem;DB_CLOSE_ON_EXIT=FALSE;IFEXISTS=FALSE;DB_CLOSE_DELAY=-1;
spring.jpa.hibernate.ddl-auto =update
spring.datasource.username=sa
spring.datasource.password=
```
Prilikom pokretanja projekta neophodno je na [localhost h2](http://localhost:8081/h2-console)  prijaviti se sa prethodno unetim kredencijalima:
```bash
JDBC URL:jdbc:h2:file:~/TheClinicSystem
```
i uneti u tabelu USER sledeci upit 

```bash
insert into user values(1,null,null,null,null,null,null,'admin@yahoo.com',true,null,null,null,'admin','admin','415152',null,'admin',false,'Clinic Centar Administrator','admin',null,'admin',null)
```
Posle pokretanja programa na **Run As > Spring Boot App**

Aplikaciji se pristupa preko: 
[login page](http://localhost:8081/logovanje)

Prijava je moguca sa kredencijalima: **admin:admin**


Tokom pokretanja programa savetuje se da se isključi **Antivirus** zbog uspešnog slanja mejlova i uspešnog rada samog programa.
## Autori
Projekat su radili:
1) *Marko Ercegovac* **(RA 18/2016)**
2) *Nataša Mitrović* **(RA 64/2016)**
3) *Maja Kešin* **(RA 25/2016)**
