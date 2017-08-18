# maxipago


To upload an application just run a MaxipagoApplication.java class
-o Server will be initialized using Spring Boot
-the path is http: // localhost: 8080 / cities / {json or xml} / {km or mi}
-This Project uses Maven
-config in pom.xml that generates a war in TARGET folder when execute the command "mvn clean install"

Example
-http: // localhost: 8080 / cities / json / km
Returns in JSON using KM unit
-http: // localhost: 8080 / cities / xml / mi
Returns in XML using Miles unit