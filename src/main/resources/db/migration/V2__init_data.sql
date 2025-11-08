-- ==============================================================
-- SCRIPT D’INITIALISATION RICHE (SANS PAIEMENT NI ÉVALUATION)
-- ==============================================================

-- 1️⃣ CATEGORIES
INSERT INTO Categorie (libelle) VALUES
('Ménage'),
('Plomberie'),
('Électricité'),
('Jardinage'),
('Informatique'),
('Menuiserie'),
('Peinture'),
('Mécanique');

-- 2️⃣ SERVICES
INSERT INTO Service (nom, description, idcategorie, tarif_horaire_min, tarif_horaire_max, duree_moyenne)
VALUES
('Nettoyage complet', 'Nettoyage complet d’appartement ou maison', 1, 5000, 15000, 120),
('Lessive et repassage', 'Prise en charge du linge et repassage', 1, 4000, 10000, 90),
('Réparation de fuite', 'Réparation rapide des fuites d’eau', 2, 8000, 25000, 90),
('Installation sanitaire', 'Pose de robinetterie et sanitaires', 2, 10000, 30000, 120),
('Installation électrique', 'Mise en place d’installations électriques domestiques', 3, 15000, 40000, 180),
('Réparation de prise', 'Remplacement de prises et interrupteurs', 3, 7000, 15000, 60),
('Tonte de pelouse', 'Entretien régulier des jardins et pelouses', 4, 6000, 15000, 60),
('Taille d’arbustes', 'Taille et entretien des haies et arbres', 4, 7000, 20000, 90),
('Assistance informatique', 'Dépannage et installation de logiciels', 5, 10000, 30000, 120),
('Création de site web', 'Développement de sites web vitrines et e-commerce', 5, 20000, 60000, 240),
('Fabrication de meubles', 'Conception et fabrication sur mesure', 6, 15000, 50000, 300),
('Réparation de porte', 'Réparation de portes ou armoires cassées', 6, 10000, 25000, 120),
('Peinture murale', 'Application de peinture intérieure et extérieure', 7, 12000, 40000, 180),
('Réparation automobile', 'Entretien et réparation mécanique de base', 8, 15000, 45000, 240),
('Vidange moteur', 'Vidange et entretien régulier du moteur', 8, 10000, 30000, 90);

-- 3️⃣ UTILISATEURS
INSERT INTO Utilisateur (nom, prenom, telephone, email, adresse, motDePasse, date_de_naissance, role)
VALUES
('Admin', 'Principal', '+221770000001', 'admin@services.com', 'Dakar Plateau', 'hash_admin', '1985-02-15', 'ADMIN'),
('Sow', 'Aminata', '+221770000002', 'aminata.sow@mail.com', 'Dakar Liberté 6', 'hash_pwd1', '1993-06-21', 'CLIENT'),
('Ndiaye', 'Cheikh', '+221770000003', 'cheikh.ndiaye@mail.com', 'Pikine Icotaf', 'hash_pwd2', '1990-03-11', 'PRESTATAIRE'),
('Ba', 'Fatou', '+221770000004', 'fatou.ba@mail.com', 'Guédiawaye', 'hash_pwd3', '1992-08-09', 'PRESTATAIRE'),
('Fall', 'Moussa', '+221770000005', 'moussa.fall@mail.com', 'Rufisque', 'hash_pwd4', '1995-12-05', 'CLIENT'),
('Diop', 'Abdou', '+221770000006', 'abdou.diop@mail.com', 'Thiaroye', 'hash_pwd5', '1988-01-18', 'PRESTATAIRE'),
('Sarr', 'Mame', '+221770000007', 'mame.sarr@mail.com', 'Dakar Yoff', 'hash_pwd6', '1991-10-23', 'PRESTATAIRE'),
('Diallo', 'Oumy', '+221770000008', 'oumy.diallo@mail.com', 'Parcelles Assainies', 'hash_pwd7', '1996-07-15', 'CLIENT'),
('Camara', 'Alioune', '+221770000009', 'alioune.camara@mail.com', 'Kaolack', 'hash_pwd8', '1990-09-09', 'CLIENT'),
('Mbaye', 'Khady', '+221770000010', 'khady.mbaye@mail.com', 'Mbour', 'hash_pwd9', '1998-03-02', 'CLIENT');

-- 4️⃣ ADMIN
INSERT INTO Administrateur (idutilisateur, codeDAccess)
VALUES (1, 'ADMIN-2025');

-- 5️⃣ PRESTATAIRES
INSERT INTO Prestataire (idutilisateur, disponibilite, description)
VALUES
(3, 'disponible', 'Plombier expérimenté avec 10 ans d’expérience'),
(4, 'occupé', 'Électricienne professionnelle, spécialisée dans les installations domestiques'),
(6, 'disponible', 'Menuisier hautement qualifié'),
(7, 'disponible', 'Peintre professionnel avec 8 ans d’expérience');

-- 6️⃣ CLIENTS
INSERT INTO Client (idutilisateur)
VALUES
(2), (5), (8), (9), (10);

-- 7️⃣ PROPOSITIONS DE SERVICES
INSERT INTO proposer (idprestataire, idservice, tarif_horaire, description_perso)
VALUES
(1, 3, 12000, 'Réparation de fuites avec garantie'),
(1, 4, 20000, 'Installation complète de sanitaires modernes'),
(2, 5, 18000, 'Pose d’installations électriques selon normes'),
(2, 6, 10000, 'Réparation rapide de prises et câbles'),
(3, 11, 30000, 'Fabrication de meubles sur mesure'),
(3, 12, 15000, 'Réparation rapide de portes'),
(4, 13, 20000, 'Peinture intérieure design et moderne'),
(4, 7, 9000, 'Entretien régulier de pelouses');

-- 8️⃣ CONTRATS
INSERT INTO Contrat (horaires, jours_travail, montant_salaire, duree_jours, type_contrat, statut)
VALUES
('08h-12h', 'Lun-Ven', 60000, 10, 'ponctuel', 'actif'),
('09h-17h', 'Lun-Sam', 120000, 20, 'temporaire', 'actif'),
('07h-15h', 'Lun-Ven', 80000, 15, 'ponctuel', 'actif'),
('10h-18h', 'Lun-Dim', 200000, 30, 'mensuel', 'actif');

-- 9️⃣ DEMANDES DE SERVICE (sans paiement ni évaluation)
INSERT INTO DemandeService (idclient, idprestataire, idservice, idcontrat, daterendezvous, statut, details_demande)
VALUES
(1, 1, 3, 1, NOW() + INTERVAL '2 day', 'accepte', 'Réparation de fuite dans la cuisine'),
(2, 2, 5, 2, NOW() + INTERVAL '3 day', 'en_cours', 'Installation électrique complète du salon'),
(3, null, 11, 3, NOW() + INTERVAL '4 day', 'en_attente', 'Fabrication d’un meuble TV sur mesure'),
(4, null, 13, 4, NOW() + INTERVAL '1 day', 'en_attente', 'Peinture du salon et de deux chambres'),
(5, 1, 4, 1, NOW() + INTERVAL '5 day', 'refuse', 'Installation sanitaire dans la salle de bain'),
(2, null, 12, 3, NOW() + INTERVAL '7 day', 'en_attente', 'Réparation de porte cassée'),
(3, 1, 7, 1, NOW() + INTERVAL '6 day', 'en_cours', 'Tonte de la pelouse du jardin'),
(4, 2, 6, 2, NOW() + INTERVAL '2 day', 'termine', 'Remplacement de prises défectueuses'),
(5, null, 13, 4, NOW() + INTERVAL '3 day', 'en_attente', 'Peinture extérieure de la maison'),
(1, null, 5, 2, NOW() + INTERVAL '1 day', 'en_attente', 'Installation électrique cuisine et salon');
