# Bilan livraison 3

## Nouvelles fonctionnalités proposées:
Nous avons ammélioré le placement des mots
Dans le joueur la méthode envoyer mots permet maintenant de :
- Au premier tour, il y a le placement du meilleur mot possible avec la main du joueur au milieu du plateau
- Aux autres tours, le meilleur mot perpendiculaire possible est rajouté sur le plateau 

L'ajout des statistiques à également été implémenté.  
Le joker a été implémenté de façon "random" : si un joueur possède un joker, il est aléatoire à chaque tour.   
Les joueurs ont également la possiblité d'échanger leurs lettres s'ils n'ont pas la possibilité de placer un mot (le nombre de lettre échangé est aléatoire).  
La partie se termine maintenant lorsqu'aucun des joueurs ne peut plus jouer ou s'ils passent leur tour 3 fois chacun de manière consécutive (soit 6 tours d'affilé au total).


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
Pour cette partie, nous avons continué les tests unitaires.
Nous avons aussi effectué les tests d'intégration pour le déroulement de la partie.
