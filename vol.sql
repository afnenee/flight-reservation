-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 24 avr. 2024 à 16:21
-- Version du serveur : 10.4.28-MariaDB
-- Version de PHP : 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `vol`
--

-- --------------------------------------------------------

--
-- Structure de la table `depart_arriver`
--

CREATE TABLE `depart_arriver` (
  `depart` varchar(35) NOT NULL,
  `arrivee` varchar(35) NOT NULL,
  `codee` int(11) NOT NULL,
  `date` date NOT NULL,
  `price` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `depart_arriver`
--

INSERT INTO `depart_arriver` (`depart`, `arrivee`, `codee`, `date`, `price`) VALUES
('djerba', 'tunis', 3, '2024-04-24', 120),
('djerba', 'france', 4, '2024-04-30', 1000),
('djerba', 'france', 5, '2024-05-09', 500),
('djerba', 'tunis', 6, '2024-05-01', 100),
('djerba', 'france', 7, '2024-06-05', 1380),
('djerba', 'france', 8, '2024-06-12', 720),
('djerba', 'france', 9, '2024-05-31', 870),
('djerba', 'france', 10, '2024-07-17', 1240),
('italy', 'canada', 11, '2024-08-08', 14300);

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `code` int(10) NOT NULL,
  `from` varchar(20) NOT NULL,
  `to` varchar(10) NOT NULL,
  `nb_person` int(10) NOT NULL,
  `total` float NOT NULL,
  `price` float NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`code`, `from`, `to`, `nb_person`, `total`, `price`, `date`) VALUES
(4, 'djerba', 'france', 2, 16000, 1000, '2024-04-30'),
(6, 'djerba', 'tunis', 4, 400, 100, '2024-05-01'),
(7, 'djerba', 'france', 1, 1380, 1380, '2024-06-05'),
(8, 'djerba', 'france', 3, 2160, 720, '2024-06-12'),
(11, 'italy', 'canada', 2, 28600, 14300, '2024-08-08');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `code` int(10) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`code`, `username`, `password`) VALUES
(1, 'cc', '123'),
(2, 'afnene', '1001'),
(3, 'hachem', '123'),
(4, 'souha', '456');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `depart_arriver`
--
ALTER TABLE `depart_arriver`
  ADD PRIMARY KEY (`codee`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`code`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`code`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `depart_arriver`
--
ALTER TABLE `depart_arriver`
  MODIFY `codee` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `code` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
