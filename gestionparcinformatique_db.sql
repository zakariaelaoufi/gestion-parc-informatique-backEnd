-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 13, 2024 at 12:46 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gestionparcinformatique`
--

-- --------------------------------------------------------

--
-- Table structure for table `affecter`
--

CREATE TABLE `affecter` (
  `id_affectation` bigint(20) NOT NULL,
  `date_affectation` date NOT NULL,
  `date_retoure` date DEFAULT NULL,
  `id_inventaire` bigint(20) DEFAULT NULL,
  `id_travail` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `affecter`
--

INSERT INTO `affecter` (`id_affectation`, `date_affectation`, `date_retoure`, `id_inventaire`, `id_travail`) VALUES
(1, '2024-06-06', NULL, 11, 1),
(2, '2024-06-06', '2024-06-06', 2, 1),
(3, '2024-06-06', NULL, 2, 2),
(4, '2024-06-06', NULL, 3, 3),
(5, '2024-06-06', NULL, 10, 2),
(6, '2024-06-06', NULL, 18, 3),
(7, '2024-06-07', NULL, 12, 6),
(8, '2024-06-07', NULL, 19, 6),
(9, '2024-06-07', NULL, 1, 6);

-- --------------------------------------------------------

--
-- Table structure for table `attacher`
--

CREATE TABLE `attacher` (
  `id_attachment` bigint(20) NOT NULL,
  `date_attachment` date NOT NULL,
  `date_retoure` date DEFAULT NULL,
  `id_entite_travail` bigint(20) DEFAULT NULL,
  `id_inventaire` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `attacher`
--

INSERT INTO `attacher` (`id_attachment`, `date_attachment`, `date_retoure`, `id_entite_travail`, `id_inventaire`) VALUES
(1, '2024-06-06', '2024-06-06', 1, 3),
(2, '2024-06-06', '2024-06-07', 2, 1),
(3, '2024-06-06', '2024-06-07', 2, 4),
(4, '2024-06-06', NULL, 4, 6),
(5, '2024-06-06', NULL, 5, 8),
(6, '2024-06-06', NULL, 7, 9),
(7, '2024-06-06', NULL, 1, 17),
(8, '2024-06-07', NULL, 4, 4);

-- --------------------------------------------------------

--
-- Table structure for table `categorie`
--

CREATE TABLE `categorie` (
  `id_categorie` bigint(20) NOT NULL,
  `abv` varchar(255) NOT NULL,
  `date_creation` date DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `libelle` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `categorie`
--

INSERT INTO `categorie` (`id_categorie`, `abv`, `date_creation`, `deleted`, `libelle`) VALUES
(1, 'UC', '2024-06-02', b'0', 'Unité centrale'),
(2, 'PCP', '2024-06-02', b'0', 'PC Portable'),
(3, 'IM', '2024-06-02', b'0', 'Imprimante'),
(4, 'RT', '2024-06-02', b'0', 'Routeur'),
(5, 'SC', '2024-06-02', b'0', 'Scanner');

-- --------------------------------------------------------

--
-- Table structure for table `entite_travail`
--

CREATE TABLE `entite_travail` (
  `id_entite_travail` bigint(20) NOT NULL,
  `deleted` bit(1) NOT NULL,
  `nom_entite_travail` varchar(255) DEFAULT NULL,
  `type_entite_travail` enum('DEPARTEMENT','DIVISION','SERVICE') DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `entite_travail`
--

INSERT INTO `entite_travail` (`id_entite_travail`, `deleted`, `nom_entite_travail`, `type_entite_travail`, `parent_id`) VALUES
(1, b'0', 'Systeme d\'information', 'DEPARTEMENT', NULL),
(2, b'0', 'Commercial', 'DEPARTEMENT', NULL),
(3, b'1', 'Audit Interne', 'DEPARTEMENT', NULL),
(4, b'0', 'Juridique', 'DEPARTEMENT', NULL),
(5, b'0', 'Comptable et Financiers', 'DEPARTEMENT', NULL),
(6, b'0', 'Architecture et sécurité des systèmes', 'DIVISION', 1),
(7, b'0', 'Exploitation des SI', 'DIVISION', 1),
(8, b'0', 'Transformation digitale et solution SI', 'DIVISION', 1),
(9, b'0', 'Parc Informatique, Assistance technique et maintenance', 'SERVICE', 7),
(10, b'0', 'Intégration des solutions SI', 'SERVICE', 8),
(11, b'0', 'Systèmes et sécurité informatique', 'SERVICE', 6),
(12, b'0', 'Ordonnancement et Adminstration des bases de données', 'SERVICE', 7);

-- --------------------------------------------------------

--
-- Table structure for table `fournisseur`
--

CREATE TABLE `fournisseur` (
  `id_fournisseur` bigint(20) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `fax` varchar(255) NOT NULL,
  `ice` varchar(255) NOT NULL,
  `tel` varchar(255) NOT NULL,
  `deleted` bit(1) NOT NULL,
  `nom_fournisseur` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `fournisseur`
--

INSERT INTO `fournisseur` (`id_fournisseur`, `adresse`, `email`, `fax`, `ice`, `tel`, `deleted`, `nom_fournisseur`) VALUES
(1, 'Avenue Mehdi Ben Barka Secteur 11, Rabat 10100', 'service.clientele@aswakassalam.com', '05134589', 'ASW2526', '0800097070', b'0', 'Aswak Assalam'),
(2, 'Ave St Louis, Fes 30050', 'service.clientele@electroplanet.com', '0802010102', 'EL553C', '+212 80-2010102', b'0', 'Electroplanet');

-- --------------------------------------------------------

--
-- Table structure for table `inventaire`
--

CREATE TABLE `inventaire` (
  `id_inventaire` bigint(20) NOT NULL,
  `etat` enum('ENSTOCK','ENREPARATION','ACTIF','REFORME') NOT NULL,
  `hostname` varchar(255) DEFAULT NULL,
  `numero_serie` varchar(255) DEFAULT NULL,
  `id_livraison` bigint(20) DEFAULT NULL,
  `id_produit` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inventaire`
--

INSERT INTO `inventaire` (`id_inventaire`, `etat`, `hostname`, `numero_serie`, `id_livraison`, `id_produit`) VALUES
(1, 'ACTIF', NULL, 'CNCJH67461', NULL, 1),
(2, 'ACTIF', '00002', 'CNCJH67462', 1, 1),
(3, 'ACTIF', NULL, 'CNCJH67463', NULL, 1),
(4, 'ACTIF', '00004', 'CNCJH67464', 1, 1),
(5, 'ENREPARATION', NULL, 'CNCJH67465', NULL, 1),
(6, 'ACTIF', '00006', 'RNCJH67461', 2, 2),
(7, 'REFORME', '00007', 'RNCJH67462', 2, 2),
(8, 'ACTIF', '00008', 'RNCJH67463', 2, 2),
(9, 'ACTIF', '00009', 'PNCJH67461', 3, 3),
(10, 'ACTIF', '00010', 'PNCJH67462', 3, 3),
(11, 'ACTIF', '00011', 'PNCJH67463', 3, 3),
(12, 'ACTIF', '00012', 'PNCJH67464', 3, 3),
(13, 'ENSTOCK', '00013', 'PNCJH67465', 3, 3),
(14, 'ENSTOCK', '00014', 'SNCJH67461', 4, 4),
(15, 'ENSTOCK', '00015', 'SNCJH67462', 4, 4),
(16, 'ENSTOCK', '00016', 'SNCJH67463', 4, 4),
(17, 'ACTIF', '00017', 'SNCJH67464', 4, 4),
(18, 'ACTIF', '00018', 'SNCJH67465', 4, 4),
(19, 'ACTIF', '00019', 'SNCJH67466', 4, 4),
(20, 'ENSTOCK', '00020', 'SNCJH67467', 4, 4),
(21, 'ENSTOCK', '00021', 'SNCJH67468', 4, 4),
(22, 'ENSTOCK', '00022', 'UNCJH67461', 5, 5),
(23, 'ENSTOCK', '00023', 'UNCJH67462', 5, 5),
(24, 'ENSTOCK', '00024', 'UNCJH67463', 5, 5),
(25, 'ENSTOCK', '00025', 'UNCJH67464', 5, 5),
(26, 'ENSTOCK', '00026', 'UNCJH67465', 5, 5),
(27, 'ENSTOCK', '00027', 'UNCJH67466', 5, 5),
(28, 'ENSTOCK', '00028', 'UNCJH67467', 5, 5),
(29, 'ENSTOCK', '00029', 'UNCJH67468', 5, 5),
(30, 'ENSTOCK', '00030', 'CNCJH67471', 6, 1),
(31, 'ENSTOCK', '00031', 'CNCJH67472', 6, 1),
(32, 'ENSTOCK', '00032', 'CNCJH67473', 6, 1);

-- --------------------------------------------------------

--
-- Table structure for table `livraison`
--

CREATE TABLE `livraison` (
  `id_livraison` bigint(20) NOT NULL,
  `date_livraison` date NOT NULL,
  `delai` int(11) NOT NULL,
  `prix` double NOT NULL,
  `id_fournisseur` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `livraison`
--

INSERT INTO `livraison` (`id_livraison`, `date_livraison`, `delai`, `prix`, `id_fournisseur`) VALUES
(1, '2024-06-06', 1, 712, 2),
(2, '2024-06-06', 2, 450, 2),
(3, '2024-06-06', 1, 3490, 1),
(4, '2024-06-06', 1, 3890, 2),
(5, '2024-06-06', 1, 1099, 2),
(6, '2024-06-05', 1, 710, 1);

-- --------------------------------------------------------

--
-- Table structure for table `marque`
--

CREATE TABLE `marque` (
  `id_marque` bigint(20) NOT NULL,
  `date_creation` date DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  `nom_marque` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `marque`
--

INSERT INTO `marque` (`id_marque`, `date_creation`, `deleted`, `nom_marque`) VALUES
(1, '2024-06-02', b'0', 'HP'),
(2, '2024-06-02', b'0', 'Asus'),
(3, '2024-06-02', b'0', 'Cisco'),
(4, '2024-06-02', b'0', 'DELL'),
(5, '2024-06-02', b'0', 'Asus'),
(6, '2024-06-02', b'0', 'Canon'),
(7, '2024-06-02', b'0', 'Epson');

-- --------------------------------------------------------

--
-- Table structure for table `produit`
--

CREATE TABLE `produit` (
  `id_produit` bigint(20) NOT NULL,
  `description` text NOT NULL,
  `imageurl` varchar(255) DEFAULT NULL,
  `nom_produit` text NOT NULL,
  `id_categorie` bigint(20) DEFAULT NULL,
  `id_marque` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `produit`
--

INSERT INTO `produit` (`id_produit`, `description`, `imageurl`, `nom_produit`, `id_categorie`, `id_marque`) VALUES
(1, 'Wi-Fi, Impression, Copie, Scan, Cloud\nVitesse d\'impression noir: 7.7 images/min en norme ISO\nVitesse d\'impression couleur: 4.0 images/min en norme ISO\nQualité d\'impression noire: Jusqu\'à 4800 x 1200 DPI (ppp)\nQualité d\'impression couleur: Jusqu\'à 4800 x 1200 DPI (ppp)\nImpression recto/verso: Manuelle\nFonctionne avec: 2 Cartouches (1 noir, 1 couleur : cyan, magenta, jaune)\nConnectivité: Hi-Speed USB (B Port); Wi-Fi\nEncres fournies avec l\'imprimante: PG-445 (Noir) CL-446 (Couleur)', 'imprimante5_20240606102016.jpg', 'Imprimante Multifonction Jet d\'encre Canon PIXMA TS3340', 3, 6),
(2, 'PORTS DE COMMUTATEUR : 8 ports 10/100/1000 + ports SFP 2 x 1G\nFLEXIBILITɠ: une gamme étendue de solutions pour une flexibilité optimale de 8 à 48 ports et des combinaisons PoE+.\nSIMPLICITɠ: l\'interface utilisateur intégrée, l\'application mobile et le tableau de bord Cisco Business Dashboard intuitifs simplifient les opérations réseau et automatisent la gestion tout au long du cycle de vie de la solution.\nSÉCURITɠ: IEEE 802.1X intégré pour le contrôle des accès au réseau, prévention des attaques DoS pour améliorer la disponibilité lors d\'une attaque et listes de contrôle d\'accès (ACL) pour protéger le réseau contre les utilisateurs non autorisés', '61eToe-wc1S._AC_UF1000,1000_QL80__20240606123349.jpg', 'Cisco Commutateur Intelligent Business CBS220-8T-E-2G | 8 Ports GE | Ports Small Form-Factor Pluggable (SFP) 2 x 1G', 4, 3),
(3, 'Explorez notre dernier ordinateur portable ASUS, conçu pour répondre à vos besoins informatiques quotidiens avec style et performance. Doté d\'un écran 15,6\" Full HD 1920 x 1080 au format 16:9, cet afficheur antireflet ultra-fin de 200 nits offre une expérience visuelle immersive, vous permettant de profiter de vos contenus avec une clarté exceptionnelle, même dans des environnements lumineux.Alimenté par un puissant processeur Intel® Core™ i3 de 10e génération, cet ordinateur portable ASUS assure une réactivité exceptionnelle pour toutes vos tâches, du travail productif au streaming de contenu multimédia. Avec 4 Go de mémoire DDR4 RAM, bénéficiez d\'une performance fluide et efficace, même lors de l\'utilisation de plusieurs applications simultanément.Le disque dur SATA de 1 To à 5400 tr/min offre un espace de stockage généreux pour vos fichiers, photos, vidéos et bien plus encore, vous permettant de conserver tous vos souvenirs et documents importants à portée de main. Le clavier chiclet à touches d\'île assure une expérience de frappe confortable et précise, tandis que le système audio intégré, avec haut-parleurs et microphone, offre une qualité sonore exceptionnelle grâce à la technologie ICEpower® Sonic Master.Préinstallé avec Windows 10 Home, cet ordinateur portable ASUS vous offre une interface conviviale et familière ainsi qu\'un accès facile à une gamme étendue d\'applications et de fonctionnalités. Que ce soit pour le travail, les études ou le divertissement, cet ordinateur portable ASUS est prêt à vous accompagner dans toutes vos aventures numériques, offrant performance, fiabilité et style.', 'pcportable_20240606102928.jpg', 'Asus PC Portable X509J i3-1005G1 4GB/ 1T AZERTY', 2, 2),
(4, 'Scanner à plat avec chargeur automatique de documents\nRecto-verso automatique\nRésolution optique maximale: Jusqu\'à 1.200 DPI (ppp) x 1.200 DPI (ppp) (horizontal x vertical)\nProfondeur: Entrée: 30 Bits Couleur / 10 Bits Monochrome , Sortie: 24 Bits Couleur / 8 Bits Monochrome\nVitesse de numérisation chargeur auto: Monochrome : 25 pages/min - Couleur : 25 pages/min , résolution : 200 / 300 dpi, Monochrome : 10 image/min - Couleur : 10 image/min , résolution : 200 / 300 dpi\nTaille maximale de numérisation : 216 x 297 mm (scanner à plat) - 210 mm x 3.048 mm (horizontal x vertical) (chargeur auto)\nFormats acceptés: A4, A5, A6, B5, Letter, Letter Legal\nTaux d\'utilisation: Jusqu\'à 1500 pages/jour\nConnectivité: USB 3.0\nCapacité du bac d\'alimentation automatique: 50 Feuilles', 'scanner_20240606103149.jpg', 'Epson Scanner A4 à plat WORKFORCE DS-1630', 5, 7),
(5, 'DELL Optiplex 3020 SFF : Le Dell OptiPlex 3020 SFF est un ordinateur de bureau professionnel reconnu dans le milieu pour son excellente fiabilité. Peu encombrant et simple, c’est un PC de bureau qui se loge facilement dans tous les environnements sans difficultés. Le châssis très bien étudié permet une bonne circulation de l’air ainsi qu’une chauffe réduite des composants. La configuration est homogène et idéal pour un usage de tous les jours. Nous y retrouvons un intel Core i5-4570 , 4 Go de RAM et un disque dur de 500 Go. L’ensemble se montre à l’aise sur toutes les tâches les plus simples (bureautique – internet) et offre une véritable solution à tous les utilisateurs qui cherche un ordinateur fiable sans se ruiner. Specifications : ¤ DELL Optiplex 3020 SFF ¤ Intel Core i5-4570 3.20GHz ¤ Fréquance Turbo maxi 3.50GHz ¤ Mémoire cache 6Mo ¤ 4 GB de Mémoire DDR3-1600 ¤ Disque dur 500 GB HDD ¤ Intel HD Graphics 4600 ¤ Hi-Speed USB (2.0) – (2 à l’avant, 4 à l’arrière) x 6 ¤ Super-Speed USB (3.0) – (à l’arrière) x 2 ¤ VGA – HD D-Sub (HD-15) 15 broches x 1 ¤ DisplayPort x1 ¤ Gigabit Ethernet (RJ45) 10/100/1000Mbps x 1 ¤ Lignes d’entrée (stéréo / microphone) x 2 ¤ 2 x Lignes de sortie (casque / haut-parleur) ¤ Slots – Mémoire vive x2 ¤ 1 x PCI-Express x16 (Low profile) ¤ PCI-Express x 1 (Low profile) x 1 ¤ Baie 5,25 » (externe – ultraplate) x 1 ¤ 1 x Baie 3,5 » (interne) ¤ Windows 10 Professional ¤ Remis à neuf ¤', 'imprimante_20240606123315.jpg', 'DELL PC BUREAU OptiPlex 3020 SFF PEINTUM-RAM 4Go-HDD 500Go-avec Ecran 19 (Remis a Neuf)', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `travailler`
--

CREATE TABLE `travailler` (
  `id_travail` bigint(20) NOT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  `id_entite_travail` bigint(20) DEFAULT NULL,
  `id_utilisateur` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `travailler`
--

INSERT INTO `travailler` (`id_travail`, `date_debut`, `date_fin`, `id_entite_travail`, `id_utilisateur`) VALUES
(1, '2024-06-02', NULL, 11, 1),
(2, '2024-06-02', NULL, 10, 2),
(3, '2024-06-02', NULL, 9, 3),
(4, '2024-06-02', '2024-06-06', 9, 4),
(5, '2024-06-02', NULL, 11, 5),
(6, '2007-06-07', NULL, 12, 6);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varbinary(255) DEFAULT NULL,
  `user_number` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `full_name`, `password`, `role`, `user_number`) VALUES
(1, 'super admin', '$2a$10$jb67xBzplrdXX1Bf4Bjr5O0qN5tqKKECrV2eYjbVjlxeINAVBe1YS', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a657870000000017704000000017e720043636f6d2e67657374696f6e50617263496e666f726d6174697175652e67657374696f6e50617263496e666f726d6174697175652e557365722e6d6f64656c2e526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d0000000000000000120000787074000b53555045525f41444d494e78, '00000');

-- --------------------------------------------------------

--
-- Table structure for table `user_seq`
--

CREATE TABLE `user_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_seq`
--

INSERT INTO `user_seq` (`next_val`) VALUES
(51);

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id_utilisateur` bigint(20) NOT NULL,
  `immatricule` varchar(255) NOT NULL,
  `deleted` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nom_utilisateur` varchar(255) NOT NULL,
  `prenom_utilisateur` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `utilisateur`
--

INSERT INTO `utilisateur` (`id_utilisateur`, `immatricule`, `deleted`, `email`, `nom_utilisateur`, `prenom_utilisateur`) VALUES
(1, '02236', b'0', 'elaoufi.iliass@radeef.ma', 'EL-AOUFI', 'Iliass'),
(2, '02237', b'0', 'mouhim.ghita@radeef.ma', 'MOUHIM', 'Ghita'),
(3, '01155', b'0', 'bensaid.khalid@radeef.ma', 'BENSAID', 'Khalid'),
(4, '01156', b'1', 'azami.bouchra@radeef.ma', 'AZAMI HASSANI', 'Bouchra'),
(5, '02238', b'0', 'taqui.ayoub@radeef.ma', 'TAQUI', 'Ayoub'),
(6, '01199', b'0', 'aberchane.mahmoud@radeef.ma', 'ABERCHANE', 'Mahmoud');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `affecter`
--
ALTER TABLE `affecter`
  ADD PRIMARY KEY (`id_affectation`),
  ADD KEY `FKvq7pcqoa0uom9sxrrdnxi5qg` (`id_inventaire`),
  ADD KEY `FKp0bup21q5qjorj9bvb70oc846` (`id_travail`);

--
-- Indexes for table `attacher`
--
ALTER TABLE `attacher`
  ADD PRIMARY KEY (`id_attachment`),
  ADD KEY `FKna1kfj6bx9vu6rddwrp4v8ive` (`id_entite_travail`),
  ADD KEY `FKk1jtmiuo3lbdipxidbu3gaqoo` (`id_inventaire`);

--
-- Indexes for table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id_categorie`),
  ADD UNIQUE KEY `UK_qgned67yowigtha7bec77iqc8` (`abv`),
  ADD UNIQUE KEY `UK_201klrwuww0os41kte46ac6lq` (`libelle`);

--
-- Indexes for table `entite_travail`
--
ALTER TABLE `entite_travail`
  ADD PRIMARY KEY (`id_entite_travail`),
  ADD KEY `FKjgm060dm42ewtcd3wdn9pi0ok` (`parent_id`);

--
-- Indexes for table `fournisseur`
--
ALTER TABLE `fournisseur`
  ADD PRIMARY KEY (`id_fournisseur`),
  ADD UNIQUE KEY `UKcdkuwpbam1p8aoavns0cgajd0` (`ice`);

--
-- Indexes for table `inventaire`
--
ALTER TABLE `inventaire`
  ADD PRIMARY KEY (`id_inventaire`),
  ADD UNIQUE KEY `UK_saifa9kvlgn04e5chlmgn3w2e` (`numero_serie`),
  ADD KEY `FK2xt9g7tn04xkp28lf964iatb7` (`id_livraison`),
  ADD KEY `FKqsrethqkd21x52as8pmruxce0` (`id_produit`);

--
-- Indexes for table `livraison`
--
ALTER TABLE `livraison`
  ADD PRIMARY KEY (`id_livraison`),
  ADD KEY `FKh1ptyp3vqr2x4esg8oak02mb5` (`id_fournisseur`);

--
-- Indexes for table `marque`
--
ALTER TABLE `marque`
  ADD PRIMARY KEY (`id_marque`);

--
-- Indexes for table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id_produit`),
  ADD KEY `FKlgcxfrlljt10cdwx730b4tujf` (`id_categorie`),
  ADD KEY `FKc6ec9lijy69myc06c554whesm` (`id_marque`);

--
-- Indexes for table `travailler`
--
ALTER TABLE `travailler`
  ADD PRIMARY KEY (`id_travail`),
  ADD KEY `FK2blcev04uf5jyokagntfx57t` (`id_entite_travail`),
  ADD KEY `FKng5x350ycg0oxi9r8qsgxug3u` (`id_utilisateur`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_pao7fvyhbiu7kg0ubf1copfps` (`user_number`);

--
-- Indexes for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id_utilisateur`),
  ADD UNIQUE KEY `UKqxjw83o2mt628th2ckd5sc9e8` (`immatricule`),
  ADD UNIQUE KEY `UKrma38wvnqfaf66vvmi57c71lo` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `affecter`
--
ALTER TABLE `affecter`
  MODIFY `id_affectation` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `attacher`
--
ALTER TABLE `attacher`
  MODIFY `id_attachment` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id_categorie` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `entite_travail`
--
ALTER TABLE `entite_travail`
  MODIFY `id_entite_travail` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `fournisseur`
--
ALTER TABLE `fournisseur`
  MODIFY `id_fournisseur` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `inventaire`
--
ALTER TABLE `inventaire`
  MODIFY `id_inventaire` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `livraison`
--
ALTER TABLE `livraison`
  MODIFY `id_livraison` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `marque`
--
ALTER TABLE `marque`
  MODIFY `id_marque` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `produit`
--
ALTER TABLE `produit`
  MODIFY `id_produit` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `travailler`
--
ALTER TABLE `travailler`
  MODIFY `id_travail` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id_utilisateur` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `affecter`
--
ALTER TABLE `affecter`
  ADD CONSTRAINT `FKp0bup21q5qjorj9bvb70oc846` FOREIGN KEY (`id_travail`) REFERENCES `travailler` (`id_travail`),
  ADD CONSTRAINT `FKvq7pcqoa0uom9sxrrdnxi5qg` FOREIGN KEY (`id_inventaire`) REFERENCES `inventaire` (`id_inventaire`);

--
-- Constraints for table `attacher`
--
ALTER TABLE `attacher`
  ADD CONSTRAINT `FKk1jtmiuo3lbdipxidbu3gaqoo` FOREIGN KEY (`id_inventaire`) REFERENCES `inventaire` (`id_inventaire`),
  ADD CONSTRAINT `FKna1kfj6bx9vu6rddwrp4v8ive` FOREIGN KEY (`id_entite_travail`) REFERENCES `entite_travail` (`id_entite_travail`);

--
-- Constraints for table `entite_travail`
--
ALTER TABLE `entite_travail`
  ADD CONSTRAINT `FKjgm060dm42ewtcd3wdn9pi0ok` FOREIGN KEY (`parent_id`) REFERENCES `entite_travail` (`id_entite_travail`);

--
-- Constraints for table `inventaire`
--
ALTER TABLE `inventaire`
  ADD CONSTRAINT `FK2xt9g7tn04xkp28lf964iatb7` FOREIGN KEY (`id_livraison`) REFERENCES `livraison` (`id_livraison`),
  ADD CONSTRAINT `FKqsrethqkd21x52as8pmruxce0` FOREIGN KEY (`id_produit`) REFERENCES `produit` (`id_produit`);

--
-- Constraints for table `livraison`
--
ALTER TABLE `livraison`
  ADD CONSTRAINT `FKh1ptyp3vqr2x4esg8oak02mb5` FOREIGN KEY (`id_fournisseur`) REFERENCES `fournisseur` (`id_fournisseur`);

--
-- Constraints for table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `FKc6ec9lijy69myc06c554whesm` FOREIGN KEY (`id_marque`) REFERENCES `marque` (`id_marque`),
  ADD CONSTRAINT `FKlgcxfrlljt10cdwx730b4tujf` FOREIGN KEY (`id_categorie`) REFERENCES `categorie` (`id_categorie`);

--
-- Constraints for table `travailler`
--
ALTER TABLE `travailler`
  ADD CONSTRAINT `FK2blcev04uf5jyokagntfx57t` FOREIGN KEY (`id_entite_travail`) REFERENCES `entite_travail` (`id_entite_travail`),
  ADD CONSTRAINT `FKng5x350ycg0oxi9r8qsgxug3u` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
