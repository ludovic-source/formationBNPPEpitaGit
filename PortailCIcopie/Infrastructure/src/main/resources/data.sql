/*
-----------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------
---- Script de peuplement de la base de données à exécuter directement sur PgAdmin
---- Possibilité de l'exécuter au lancement du programme en décommentant le script
-----------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------

DELETE FROM PROFIL_DROITS;
DELETE FROM DROIT;
DELETE FROM UTILISATEUR;
DELETE FROM PROFIL;
DELETE FROM LIENS;
DELETE FROM THEMATIQUE;

-----------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------
---- Insertion des entités pour peupler la base
-----------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------

INSERT INTO DROIT (ID, DESCRIPTION, NOM) VALUES (1, 'Droit de consultation', 'Consultation');
INSERT INTO DROIT (ID, DESCRIPTION, NOM) VALUES (2, 'Droit de création', 'Création');
INSERT INTO DROIT (ID, DESCRIPTION, NOM) VALUES (3, 'Droit de modification', 'Modification');
INSERT INTO DROIT (ID, DESCRIPTION, NOM) VALUES (4, 'Droit de suppression', 'Suppression');
INSERT INTO DROIT (ID, DESCRIPTION, NOM) VALUES (5, 'Droit d''administration', 'Administration');

INSERT INTO PROFIL (ID, DESCRIPTION, NOM) VALUES (1, 'Utilisateur avec tous les droits', 'Administrateur');
INSERT INTO PROFIL (ID, DESCRIPTION, NOM) VALUES (2, 'Utilisateur avec les droits de tous les droits hors administration', 'Rédacteur');
INSERT INTO PROFIL (ID, DESCRIPTION, NOM) VALUES (3, 'Utilisateur avec tous les droits', 'Utilisateur');

INSERT INTO PROFIL_DROITS (PROFIL_ENTITY_ID, DROITS_ID) VALUES (1, 1);
INSERT INTO PROFIL_DROITS (PROFIL_ENTITY_ID, DROITS_ID) VALUES (1, 2);
INSERT INTO PROFIL_DROITS (PROFIL_ENTITY_ID, DROITS_ID) VALUES (1, 3);
INSERT INTO PROFIL_DROITS (PROFIL_ENTITY_ID, DROITS_ID) VALUES (1, 4);
INSERT INTO PROFIL_DROITS (PROFIL_ENTITY_ID, DROITS_ID) VALUES (1, 5);
INSERT INTO PROFIL_DROITS (PROFIL_ENTITY_ID, DROITS_ID) VALUES (2, 1);
INSERT INTO PROFIL_DROITS (PROFIL_ENTITY_ID, DROITS_ID) VALUES (2, 2);
INSERT INTO PROFIL_DROITS (PROFIL_ENTITY_ID, DROITS_ID) VALUES (2, 3);
INSERT INTO PROFIL_DROITS (PROFIL_ENTITY_ID, DROITS_ID) VALUES (2, 4);
INSERT INTO PROFIL_DROITS (PROFIL_ENTITY_ID, DROITS_ID) VALUES (3, 1);

INSERT INTO UTILISATEUR(ID, UID, NOM, PRENOM, MOT_DE_PASSE, UO_AFFECTATION, SITE_EXERCICE, FONCTION, PROFIL_ID ) VALUES (1, 'admin', 'WAYNE', 'Bruce', '$2a$10$fd9F9QuxewNVJkXP.GS0gOGRXc39y6Ztz9POe0K5chembMImek25i', '784562A23', 'Gotham city', 'CIL', 1);
INSERT INTO UTILISATEUR(ID, UID, NOM, PRENOM, MOT_DE_PASSE, UO_AFFECTATION, SITE_EXERCICE, FONCTION, PROFIL_ID ) VALUES (2, 'redac', 'KENT', 'Clark', '$2a$10$nQuTqmP7Tc136EWLo1LO3.PWBFUAbAOw2/9clNPe6NVwhWhZVlwvW', '784562A23', 'Metropolis', 'CIL', 2);
INSERT INTO UTILISATEUR(ID, UID, NOM, PRENOM, MOT_DE_PASSE, UO_AFFECTATION, SITE_EXERCICE, FONCTION, PROFIL_ID ) VALUES (3, 'user', 'STARK', 'Tony', '$2a$10$YywSbGetceYBXS.EuJX1ceVip9OqxoWIQHumO29dQavpEnVJkCVvO', '784562A23', 'Long Island', 'CIL', 3);

INSERT INTO thematique (id, description, id_parent, image_path, niveau, nom) VALUES (1, 'modèles de voitures sportives', 0, '', 1, 'sportives');
INSERT INTO thematique (id, description, id_parent, image_path, niveau, nom) VALUES (2, 'modèles de voitures compactes', 0, '', 1, 'compactes');
INSERT INTO thematique (id, description, id_parent, image_path, niveau, nom) VALUES (3, 'modèles de voitures berlines', 0, '', 1, 'berlines');
INSERT INTO thematique (id, description, id_parent, image_path, niveau, nom) VALUES (4, 'modèles de voitures familiales', 0, '', 1, 'familiales');
INSERT INTO thematique (id, description, id_parent, image_path, niveau, nom) VALUES (5, 'modèles de SUV', 0, '', 1, 'SUV');
INSERT INTO thematique (id, description, id_parent, image_path, niveau, nom) VALUES (6, 'modèles de camionnettes', 0, '', 1, 'camionnettes');
INSERT INTO thematique (id, description, id_parent, image_path, niveau, nom) VALUES (7, 'sportives italiennes', 1, '', 2, 'Italie');
INSERT INTO thematique (id, description, id_parent, image_path, niveau, nom) VALUES (8, 'ferrari', 7, '', 3, 'Ferrari');
INSERT INTO thematique (id, description, id_parent, image_path, niveau, nom) VALUES (9, 'lamborghini', 7, '', 3, 'lamborghini');
INSERT INTO thematique (id, description, id_parent, image_path, niveau, nom) VALUES (10, 'maserati', 7, '', 3, 'maserati');
INSERT INTO thematique (id, description, id_parent, image_path, niveau, nom) VALUES (11, 'bugatti', 7, '', 3, 'bugatti');
INSERT INTO thematique (id, description, id_parent, image_path, niveau, nom) VALUES (12, 'sportives allemandes', 1, '', 2, 'Allemagne');
INSERT INTO thematique (id, description, id_parent, image_path, niveau, nom) VALUES (13, 'porsche', 12, '', 3, 'porsche');
INSERT INTO thematique (id, description, id_parent, image_path, niveau, nom) VALUES (14, 'sportives françaises', 1, '', 2, 'France');
INSERT INTO thematique (id, description, id_parent, image_path, niveau, nom) VALUES (15, 'sportives anglaises', 1, '', 2, 'Royaume-uni');
INSERT INTO thematique (id, description, id_parent, image_path, niveau, nom) VALUES (16, 'McLaren', 15, '', 3, 'McLaren');
INSERT INTO thematique (id, description, id_parent, image_path, niveau, nom) VALUES (17, 'jaguar', 15, '', 3, 'jaguar');
INSERT INTO thematique (id, description, id_parent, image_path, niveau, nom) VALUES (18, 'sportives américaines', 1, '', 2, 'USA');

INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id) VALUES (1, null, '2020-07-15 10:06:32.119', '2020-07-14 10:06:32.119', 'ferrari 812 Superfast', true, '812 Superfast', 'publié', 'https://www.ferrari.com/fr-FR/auto/812-superfast', 8);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id) VALUES (2, null, null, '2020-07-14 10:06:32.119', 'ferrari 812 GTS', false, '812 GTS', 'publié restreint', 'https://www.ferrari.com/fr-FR/auto/812-gts', 8);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (3, null, '2020-07-15 10:06:32.119', '2020-07-14 10:06:32.119', 'ferrari SF90 Stradale', true, 'SF90 Stradale', 'publié', 'https://www.ferrari.com/fr-FR/auto/sf90-stradale', 8);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (4, null, '2020-07-15 10:06:32.119', '2020-07-14 10:06:32.119', 'ferrari F8 Tributo', true, 'F8 Tributo', 'publié', 'https://www.ferrari.com/fr-FR/auto/f8-tributo', 8);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (5, '2020-07-25 10:06:32.119', '2020-07-15 10:06:32.119', '2020-07-14 10:06:32.119', 'ferrari F8 Spider', true, 'F8 Spider', 'dépublié', 'https://www.ferrari.com/fr-FR/auto/f8-spider', 8);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (6, null, '2020-07-15 10:06:32.119', '2020-07-14 10:06:32.119', 'ferrari 488 Pista', false, '488 Pista', 'publié', 'https://www.ferrari.com/fr-FR/auto/ferrari-488-pista', 8);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (7, null, null, '2020-07-14 10:06:32.119', 'ferrari GTC4Lusso', true, 'GTC4Lusso', 'publié restreint', 'https://www.ferrari.com/fr-FR/auto/gtc4lusso', 8);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (8, null, '2020-07-15 10:06:32.119', '2020-07-14 10:06:32.119', 'ferrari Roma', false, 'Ferrari Roma', 'publié', 'https://www.ferrari.com/fr-FR/auto/ferrari-roma', 8);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (9, null, '2020-07-15 10:06:32.119', '2020-07-14 10:06:32.119', 'ferrari Portofino', true, 'Ferrari Portofino', 'publié', 'https://portofino.ferrari.com/fr', 8);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (10, null, '2020-07-15 10:06:32.119', '2020-07-14 10:06:32.119', 'ferrari Monza SP1', true, 'Ferrari Monza SP1', 'publié', 'https://www.ferrari.com/fr-FR/auto/monza-sp1', 8);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (11, null, '2020-07-15 10:06:32.119', '2020-07-14 10:06:32.119', 'lamborghini Aventador', true, 'Aventador', 'publié', '', 9);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (12, null, '2020-07-15 10:06:32.119', '2020-07-14 10:06:32.119', 'lamborghini Huracan', true, 'Huracan', 'publié', '', 9);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (13, null, '2020-07-15 10:06:32.119', '2020-07-14 10:06:32.119', 'Maserati GranTurismo', true, 'GranTurismo', 'publié', '', 10);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (14, null, '2020-07-15 10:06:32.119', '2020-07-13 10:06:32.119', 'bugatti Chiron', true, 'Chiron', 'publié', '', 11);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (15, '2020-07-19 10:06:32.119', '2020-07-11 10:06:32.119', '2020-07-10 10:06:32.119', 'bugatti ONE OFF', true, 'ONE OFF', 'dépublié', '', 11);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (16, null, '2020-07-15 10:06:32.119', '2020-07-13 10:06:32.119', 'bugatti Chiron restreint', true, 'Chiron restreint', 'publié restreint', '', 11);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (17, null, null, '2020-07-13 10:06:32.119', 'bugatti Veyron restreint', true, 'Veyron restreint', 'publié restreint', '', 11);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (18, null, '2020-07-15 10:06:32.119', '2020-07-13 10:06:32.119', 'bugatti Veyron', true, 'Veyron', 'publié', '', 11);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (19, null, '2020-07-15 10:06:32.119', '2020-07-13 10:06:32.119', 'mercedes F1', true, 'mercedes F1', 'publié', '', 12);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (20, null, '2020-07-15 10:06:32.119', '2020-07-13 10:06:32.119', 'Porsche 718', true, '718', 'publié', '', 13);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (21, null, '2020-07-15 10:06:32.119', '2020-07-13 10:06:32.119', 'Porsche 911', true, '911', 'publié', '', 13);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (22, null, '2020-07-15 10:06:32.119', '2020-07-13 10:06:32.119', 'Audi TT', true, 'Audi TT', 'publié', '', 12);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (23, null, '2020-07-15 10:06:32.119', '2020-07-13 10:06:32.119', 'Alpine', true, 'Alpine A110', 'publié', '', 14);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (24, null, '2020-07-15 10:06:32.119', '2020-07-13 10:06:32.119', 'McLaren 600LT', true, '600LT', 'publié', '', 16);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (25, null, '2020-07-15 10:06:32.119', '2020-07-13 10:06:32.119', 'McLaren 570S', true, '570GT', 'publié', '', 16);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (26, null, '2020-07-15 10:06:32.119', '2020-07-13 10:06:32.119', 'McLaren 600LT', true, '540C', 'publié', '', 16);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (27, null, '2020-07-15 10:06:32.119', '2020-07-13 10:06:32.119', 'jaguar F-Type', true, 'F-Type', 'publié', '', 17);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (28, null, '2020-07-15 10:06:32.119', '2020-07-13 10:06:32.119', 'Ford GT 40', true, 'Ford GT 40', 'publié', '', 18);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (29, null, '2020-07-15 10:06:32.119', '2020-07-13 10:06:32.119', 'Volkswagen Golf', true, 'Volkswagen Golf', 'publié', '', 2);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (30, null, '2020-07-15 10:06:32.119', '2020-07-13 10:06:32.119', 'Renault Clio', true, 'Renault Clio', 'publié', '', 2);
INSERT INTO liens (id, date_depublication, date_publication, date_publication_restreinte, description, mode_affichage, nom, statut, url, thematique_id)	VALUES (31, null, '2020-07-15 10:06:32.119', '2020-07-13 10:06:32.119', 'Renault Talisman', true, 'Renault Talisman', 'publié', '', 3);

-----------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------
---- Initialisation des séquences des tables pour éviter les conflits d'intégrité sur l'id lors de la création de
---- nouvelles entités
-----------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------

ALTER SEQUENCE IF EXISTS droit_id_seq RESTART WITH 6;
ALTER SEQUENCE IF EXISTS profil_id_seq RESTART WITH 4;
ALTER SEQUENCE IF EXISTS utilisateur_id_seq RESTART WITH 4;
ALTER SEQUENCE IF EXISTS liens_id_seq RESTART WITH 32;
ALTER SEQUENCE IF EXISTS thematique_id_seq RESTART WITH 19;
*/
