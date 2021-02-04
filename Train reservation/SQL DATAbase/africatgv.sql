-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : Dim 31 jan. 2021 à 03:05
-- Version du serveur :  10.4.14-MariaDB
-- Version de PHP : 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `africatgv`
--

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

CREATE TABLE `clients` (
  `ID` int(11) NOT NULL,
  `Nom` varchar(255) NOT NULL,
  `Prenom` varchar(255) NOT NULL,
  `CIN` varchar(255) NOT NULL,
  `Adresse` varchar(255) NOT NULL,
  `télé` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Départ` varchar(255) NOT NULL,
  `Arrivée` varchar(255) NOT NULL,
  `Date` varchar(255) NOT NULL,
  `Heure` varchar(255) NOT NULL,
  `Classe` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `clients`
--

INSERT INTO `clients` (`ID`, `Nom`, `Prenom`, `CIN`, `Adresse`, `télé`, `Email`, `Départ`, `Arrivée`, `Date`, `Heure`, `Classe`) VALUES
(1, 'KOUSTA', 'khalid', 'FG12200', 'Hay berkoukesse ksar zenaga Figuig', '0643756936', 'kousta90@gmail.com', 'OUJDA', 'EL JADIDA', 'Jan 26, 2021', '10:00', '1?re classe'),
(2, 'brahimi', 'youssef', 'AB5563', 'oujda hay zaitoun', '06542842', 'ibrajimi.yousef@gmail.com', 'OUJDA', 'TANGER', 'Jan 26, 2021', 'Matinée', '1?re classe'),
(3, 'Amer', 'Fouad', 'KT000', 'sidi bernoussi casablanca', '0615151515', 'kousta90@gmail.com', 'OUJDA', 'TAZA', 'Jan 26, 2021', 'Matinée', '1?re classe'),
(4, 'kousta', 'ahmed', 'FG555', 'ain sebaa', '06000111', 'kousta90@gmail.com', 'OUJDA', 'CASABLANCA', 'Jan 26, 2021', 'Matinée', '1?re classe'),
(6, 'kousta', 'khalid', 'fg2200', 'figuig', '0655555555', 'kousta90@gmail.com', 'OUJDA', 'CASABLANCA', 'Jan 26, 2021', 'Matinée', '2?me classe'),
(8, 'KOUSTA', 'khalid', 'fg12200', 'dfd', '552523652', 'kousta90@gmail.com', 'OUJDA', 'CASABLANCA', 'Jan 26, 2021', '09:45', '1?re classe'),
(9, 'fafa', 'hakkou', 'hj1542', 'figuig', '067585632', 'kousta90@gmail.com', 'OUJDA', 'CASABLANCA', 'Jan 26, 2021', '09:45', '1?re classe'),
(17, 'edderkaoui', 'yassine', 'fg5585', 'usa', '0645698574', 'kousta90@gmail.com', 'OUJDA', 'CASABLANCA', 'Jan 26, 2021', '09:45', '1?re classe'),
(18, 'KOUSTa', 'WALID', 'FF', 'FF', 'FF', 'kousta90@gmail.com', 'OUJDA', 'CASABLANCA', 'Jan 26, 2021', '09:45', '1?re classe'),
(20, 'EL arssi', 'zakaria', 'h5452', 'oujda', '0515256135', 'zakariaeelarsi18@gmail.com', 'KENITRA', 'OUJDA', 'Jan 31, 2021', '13:00', '1?re classe'),
(21, 'KOUSTA', 'khalid', 'FG12200', 'FIGUIG berkoukesse', '0643756936', 'kousta90@gmail.com', 'OUJDA', 'CASABLANCA', 'Jan 31, 2021', '14:00', '1?re classe'),
(22, 'q', 'q', 'a', 'a', 'a', 'a', 'OUJDA', 'CASABLANCA', 'Jan 30, 2021', '09:45', '2?me classe');

-- --------------------------------------------------------

--
-- Structure de la table `gares`
--

CREATE TABLE `gares` (
  `ID` int(11) NOT NULL,
  `Nom` varchar(255) NOT NULL,
  `Adress` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `gares`
--

INSERT INTO `gares` (`ID`, `Nom`, `Adress`) VALUES
(19, 'OUJDA', 'Boulevard Abdellah Chefchaouni'),
(20, 'CASABLANCA', 'AIN SEBAA'),
(22, 'FIGUIG', 'Hay BERKOUKESS Ksar ZENAGA'),
(23, 'NADOR', 'Gare Nador-Ville'),
(24, 'TAZA', 'bd Bir Anzarane TAZA'),
(25, 'FES', 'bd des Almohades .Quartier: fes'),
(26, 'TANGER', 'hay Mghogha'),
(30, 'BOUARFA', 'Rt bouarfa'),
(31, 'MEKNES', 'Nouvelle-Ville Hamria'),
(32, 'KENITRA', 'bd Mohamed Diouri, Maamora.Quartier'),
(33, 'AEROPORT', 'aéroport Mohammed V'),
(34, 'SETTAT', 'bd Changuit'),
(35, 'BENGUERIR', 'Bd. Mohamed V BEN GUERIR'),
(36, 'SAFI', 'Rue Sidi Oussel'),
(37, 'OUJDA', 'hay salam');

-- --------------------------------------------------------

--
-- Structure de la table `trains`
--

CREATE TABLE `trains` (
  `ID` int(11) NOT NULL,
  `Code` varchar(255) NOT NULL,
  `Gare_dep` varchar(255) NOT NULL,
  `Date` varchar(255) NOT NULL,
  `Heure_dep` varchar(255) NOT NULL,
  `Gare_dar` varchar(255) NOT NULL,
  `Heure_dar` varchar(255) NOT NULL,
  `Prix` varchar(255) NOT NULL,
  `statut` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `trains`
--

INSERT INTO `trains` (`ID`, `Code`, `Gare_dep`, `Date`, `Heure_dep`, `Gare_dar`, `Heure_dar`, `Prix`, `statut`) VALUES
(32, 'A001', 'OUJDA', 'Jan 26, 2021', '10:00', 'TAZA', '14:22', '120 DH', 'ACTIVER'),
(33, 'A002', 'OUJDA', 'Jan 27, 2021', '11:00', 'FES', '15:00', '150 DH', 'ACTIVER'),
(34, 'A003', 'OUJDA', 'Jan 27, 2021', '14:00', 'FIGUIG', '17:00', '150 DH', 'ACTIVER'),
(35, 'A004', 'OUJDA', 'Jan 28, 2021', '08:00', 'TANGER', '15:00', '220 DH', 'ACTIVER'),
(36, 'A005', 'OUJDA', 'Jan 27, 2021', '09:35', 'EL JADIDA', '15:22', '260 DH', 'ACTIVER'),
(37, 'A006', 'OUJDA', 'Jan 29, 2021', '14:19', 'MARRAKECH', '22:30', '300 DH', 'ACTIVER'),
(38, 'A007', 'OUJDA', 'Jan 27, 2021', '10:00', 'RABAT', '16:00', '210 DH', 'ACTIVER'),
(39, 'ZN12', 'RABAT', 'Jan 13, 2021', '15:00', 'CASABLANCA', '16:05', '50 DH', 'ACTIVER'),
(40, 'ZX63', 'CASABLANCA', 'Jan 27, 2021', '15:00', 'TANGER', '20:35', '160 DH', 'ACTIVER'),
(41, 'WT66', 'TANGER', 'Jan 27, 2021', '13:35', 'OUJDA', '18:56', '300 DH', 'ACTIVER'),
(42, 'KM05', 'FES', 'Jan 27, 2021', '01:15', 'EL JADIDA', '08:16', '260 DH', 'ACTIVER'),
(43, 'B001', 'OUJDA', 'Jan 21, 2021', '15:00', 'TAZA', '18:35', '160 DH', 'ACTIVER'),
(44, 'C358', 'FIGUIG', 'Jan 29, 2021', '16:35', 'CASABLANCA', '01:33', '320 DH', 'ACTIVER'),
(45, 'B638', 'EL JADIDA', 'Jan 28, 2021', '15:33', 'CASABLANCA', '20:16', '160 DH', 'ACTIVER'),
(46, 'ZT88', 'MARRAKECH', 'Jan 15, 2021', '09:33', 'RABAT', '15:36', '270 DH', 'ACTIVER'),
(47, 'T888', 'SAFI', 'Jan 15, 2021', '15:00', 'BENGUERIR', '20:23', '160 DH', 'ACTIVER'),
(48, 'B457', 'OUJDA', 'Jan 27, 2021', '09:45', 'CASABLANCA', '18:16', '330 DH', 'ACTIVER'),
(49, 'H355', 'KENITRA', 'Jan 31, 2021', '13:00', 'OUJDA', '13:30', '520 DH', 'ACTIVER'),
(50, 'BM33', 'OUJDA', 'Jan 29, 2021', '07:55', 'CASABLANCA', '13:35', '360 DH', 'ACTIVER'),
(51, 'KU59', 'OUJDA', 'Jan 31, 2021', '16:00', 'CASABLANCA', '23:11', '400 DH', 'ACTIVER'),
(52, 'FS85', 'OUJDA', 'Jan 31, 2021', '14:00', 'CASABLANCA', '19:20', '360 DH', 'ACTIVER');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `gares`
--
ALTER TABLE `gares`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `trains`
--
ALTER TABLE `trains`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `clients`
--
ALTER TABLE `clients`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT pour la table `gares`
--
ALTER TABLE `gares`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT pour la table `trains`
--
ALTER TABLE `trains`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
