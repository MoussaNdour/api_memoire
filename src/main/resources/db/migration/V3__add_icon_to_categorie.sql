-- ==============================================================
-- V4__add_icon_to_categorie.sql
-- Ajout d'une colonne d'icône pour chaque catégorie
-- et initialisation avec des icônes Material Design
-- ==============================================================

-- 1️⃣ Ajout de la colonne 'icone'
ALTER TABLE Categorie
ADD COLUMN icone VARCHAR(255);

-- 2️⃣ Mise à jour des icônes pour chaque catégorie existante
UPDATE Categorie SET icone = 'cleaning_services' WHERE libelle = 'Ménage';
UPDATE Categorie SET icone = 'plumbing' WHERE libelle = 'Plomberie';
UPDATE Categorie SET icone = 'electrical_services' WHERE libelle = 'Électricité';
UPDATE Categorie SET icone = 'grass' WHERE libelle = 'Jardinage';
UPDATE Categorie SET icone = 'computer' WHERE libelle = 'Informatique';
UPDATE Categorie SET icone = 'construction' WHERE libelle = 'Menuiserie';
UPDATE Categorie SET icone = 'format_paint' WHERE libelle = 'Peinture';
UPDATE Categorie SET icone = 'directions_car' WHERE libelle = 'Mécanique';

-- 3️⃣ Vérification rapide (facultative)
-- SELECT idcategorie, libelle, icone FROM Categorie;

-- ==============================================================
-- FIN DE MIGRATION V4
-- ==============================================================
