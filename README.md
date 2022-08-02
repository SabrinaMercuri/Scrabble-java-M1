# scrabble-en-groupe-chillncode
scrabble-en-groupe-chillncode created by GitHub Classroom

## Lancer le projet :

### Maven

Dans ```./``` : ```mvn clean install```  
  
Dans ```./serveur``` : ```mvn exec:java```  

Lancement du premier client :  
Dans ```./client``` : ```mvn exec:java -Dexec.args="--nom=J1"```  

Lancement du deuxième client :  
Dans ```./client``` : ```mvn exec:java -Dexec.args="--port=8082 --nom=J2"```  

Tests intégration :  
Dans ```./client``` : ```mvn exec:java -Dexec.args="--port=8081 --nom=J1 --mode=IT"```  
Dans ```./client``` : ```mvn exec:java -Dexec.args="--port=8082 --nom=J2 --mode=IT"```  
Dans ```./serveur``` : ```mvn failsafe:integration-test```

### Docker
Création de l'image du Serveur
Dans ```./``` : ```docker build serveur -t scrabble:serveur``` 

Création de l'image du Client
Dans ```./``` : ```docker build client -t scrabble:client``` 

Execution du docker compose
Dans ```./``` : ```docker-compose up --scale client=2``` 
