-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : dim. 28 juil. 2024 à 14:55
-- Version du serveur : 8.0.31
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `employee`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id`, `login`, `password`) VALUES
(1, 'admin', 'admin123');

-- --------------------------------------------------------

--
-- Structure de la table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `firstName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `gender` varchar(100) NOT NULL,
  `phoneNum` varchar(100) NOT NULL,
  `position` varchar(100) NOT NULL,
  `image` varchar(100) NOT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `employee`
--

INSERT INTO `employee` (`id`, `employee_id`, `firstName`, `lastName`, `gender`, `phoneNum`, `position`, `image`, `date`) VALUES
(16, 2, 'sarr', 'ben', 'Female', '92231211', 'Web Developer (Back End)', 'C:\\\\Users\\\\Pc\\\\Pictures\\\\Screenshots\\\\Capture d’écran (1).png', '2024-07-28'),
(17, 3, 'nour', 'ben', 'Female', '564898', 'Web Developer (Front End)', 'C:\\\\Users\\\\Pc\\\\Pictures\\\\Screenshots\\\\Capture d’écran (1).png', '2024-07-28'),
(14, 1, 'n', 'b', 'Female', '4554121', 'Marketer Coordinator', 'C:\\\\Users\\\\Pc\\\\Pictures\\\\Screenshots\\\\Capture d’écran (1).png', '2024-07-28'),
(18, 4, 'ahmed', 'ar', 'Male', '56231244', 'Marketer Coordinator', 'C:\\\\Users\\\\Pc\\\\Pictures\\\\Screenshots\\\\Capture d’écran (1).png', '2024-07-28'),
(19, 5, 'ana', 'aa', 'Male', '5362312', 'Marketer Coordinator', 'C:\\\\Users\\\\Pc\\\\Pictures\\\\Screenshots\\\\Capture d’écran (1).png', '2024-07-28');

-- --------------------------------------------------------

--
-- Structure de la table `employee_info`
--

DROP TABLE IF EXISTS `employee_info`;
CREATE TABLE IF NOT EXISTS `employee_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NOT NULL,
  `firstName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `position` varchar(100) NOT NULL,
  `salary` double NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `employee_info`
--

INSERT INTO `employee_info` (`id`, `employee_id`, `firstName`, `lastName`, `position`, `salary`, `date`) VALUES
(1, 1, 'n', 'b', 'Marketer Coordinator', 5.5, '2024-07-27'),
(3, 2, 'sarr', 'ben', 'Web Developer (Back End)', 10, '2024-07-28'),
(4, 3, 'nour', 'ben', 'Web Developer (Front End)', 0, '2024-07-28'),
(5, 4, 'ahmed', 'ar', 'Marketer Coordinator', 0, '2024-07-28'),
(6, 5, 'ana', 'aa', 'Marketer Coordinator', 5, '2024-07-28');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
