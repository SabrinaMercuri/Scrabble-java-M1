# Bilan livraison 2

## Les fonctionnalités proposées: 
- Il y a maintenant une pioche avec laquelle on distribue les premières lettres au joueur.  
- Le joueur est capable de joueur un mot depuis une API (en anglais).   
- Le mot est placé sur le plateau en fonction du sens et de la première lettre du mot sans contraintes pour le moment.
- L'application permet après que le joueur ait joué un mot, de distribuer les lettres manquantes au joueur (pour avoir un nombre de 7 lettres au total).  
- La partie se termine quand les joueurs ont joué 2 mots chacun. 
- Le score des joueurs est également affiché.
- Execution du code dans un environement Dockerisé

## L’organisation du code:
Le code est organisé en 3 modules comme pour le premier rendu (rien n'a changé pour cette partie)
- le module client 
- le module commun (contient toutes les classes utilisées par le client et le serveur)
- le module serveur

## L'organisation des chemins (des web services):

### Côté client:
- ```/jouer``` : Le serveur demande au client de jouer. Et le client retourne un mot au serveur.
- ```/finir``` : Le serveur avertit le client que la partie est terminée. 
- ```/mainDepart``` : Le serveur distribut la première main au joueur.
- ```/distributionLettres``` : Le serveur envoit les lettres manquantes au joueur.
- ```/majScore``` : Le serveur envoit le score du mot au joueur qui met son score à jour.
- ```/echange``` : Pas utilisé dans cette partie, mais début d'implémentation pour permettre au joueur d'échanger ses lettres lorsqu'il ne joue pas.

### Côté Serveur:
- ```/Connexion``` : Le client se connecte au serveur en envoyant son Identification (nom et url). et le serveur retourne l'état de la connexion au client.
- ```/echange``` : Le client demande au serveur de pouvoir échanger ses lettres (début d'implémentation mais pas encore utilisé pour cette partie). 

## Les tests effectués:
Pour cette partie, nous avons continué les tests unitaires petit à petit (notamment dans gestionnairePartie).  
Et nous avons effectué les tests d'intégration pour le déroulement de la partie.
