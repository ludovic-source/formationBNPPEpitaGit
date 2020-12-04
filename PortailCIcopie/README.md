# PortailCI

Bienvenue sur l'API Portail CI
Il s'agit d'une API permettant de gérer un portail présentant une arborescence de liens regroupés sous différents thèmes.

L'API est développé en JAVA 1.8 avec Spring Boot, Maven, JPA et une base de données PostgreSQL.

Elle est composée de 4 couches:
- Exposition permettant d'exposer l'API
- Application permettant d'orchestrer l'application
- Domain pour l'aspect métier
- Infrastructure pour la partie data

L'API est  sécurisé grâce à l'implémentation de Spring Security
Pour le moment, il s'agit d'une authentification simple via username / password qui renvoie un cookie de session au client (JSESSIONID), le but étant à l'avenir d'évoluer vers une solution stateless.

La partie front de l'application est conçue avec Angular 9 et est accessible sur le repo GitHub: https://github.com/ludovic-source/projetFrontPortailci

# Pré-requis

- Créer une base de données PostgreSQL sur votre machine.
- Changer les paramètres permettant d'accéder à la base dans le fichier application.properties (Situé: Exposition\src\main\resources ).

      - spring.datasource.url = jdbc:postgresql://localhost:5432/nomDeVotreBase
      - spring.datasource.username=username
      - spring.datasource.password=password

- Un script de peuplement de la base est fourni (Fichier data.sql situé: Infrastructure\src\main\resources ).

    Deux solutions s'offrent à vous:
    - Décommenter le script et laisser l'application gérer (Attention: dans ce cas, la base de donnée sera remise à l'état d'origine à chaque lancement de l'application)
    - Exécuter le script directement sur votre base PostgreSQL après avoir lancé l'application (afin que les tables soient créées par l'application)

# Informations complémentaires

- Le demande initiale faisait part du fait de ne pas implémenter d'"Espace utilisateur" avec possibilité de changer le mot de passe, le choix a donc été fait de partir sur un mot de passe généré à la création de l'utilisateur et égal à l'UID de ce dernier

- De base, l'application embarque 3 utilisateurs ayant chacun un des trois profils de base:
    - UID: admin, Mot de passe: admin, Profil: Administrateur (accès à toutes les fonctionnalités de l'application)
    - UID: redac, Mot de passe: redac, Profil: Rédacteur (accès à la partie navigation sur l'arborescence de liens, avec en plus la possibilité de passer en "Mode Edition" afin d'effectuer des opérations CRUD sur les thèmes et les liens)
    - UID: user, Mot de passe: user, Profil: Utilisateur (accès uniquement à la visualisation de l'arborescence)

- En plus de la demande initiale faisant part du besoin de gérer des profils utilisateurs différents, il a été mis en place une gestion des profils en leur attribuant des droits (5 droits sont actuellement disponibles: Consultation, Création, Modification, Suppression et Administration). Il est donc possible pour un administrateur de créer un profil totalement personnalisable, ce qui apporte de la modularité pour l'adminsitration de l'application.

- L'application expose une API permettant de récupérer un collaborateur dans un bouchon simulant l'appel à un référentiel extérieur (/portailci//getRefogByUID/{utilisateurUid})
Cette fonctionnaté est utilisé côté front dans le formulaire de création d'un nouvel utilisateur. Cela permet, lorsqu'un collaborateur est trouvé, de pré-remplir le formulaire. Il ne reste plus qu'à l'administrateur à indiquer le profil à attribuer au nouvel utilisateur.

    Six collaborateurs sont requêtables, voici les UID à saisir:
    - 139696
    - a30607
    - 123456
    - 000000
    - 654321
    - 987654
