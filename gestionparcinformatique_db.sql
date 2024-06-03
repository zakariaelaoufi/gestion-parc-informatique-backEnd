-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 03, 2024 at 11:48 AM
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
(1, '2024-06-02', NULL, 12, 2),
(2, '2024-06-02', NULL, 13, 1),
(3, '2024-06-02', NULL, 17, 3),
(4, '2024-06-02', '2024-06-02', 1, 2),
(5, '2024-06-02', '2024-06-02', 1, 5),
(6, '2024-06-02', NULL, 1, 3),
(7, '2024-06-02', NULL, 3, 1);

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
(1, '2024-06-02', NULL, 9, 18);

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
(3, b'0', 'Audit Interne', 'DEPARTEMENT', NULL),
(4, b'0', 'Juridique', 'DEPARTEMENT', NULL),
(5, b'0', 'Comptable et Financiers', 'DEPARTEMENT', NULL),
(6, b'0', 'Architecture et sécurité des systèmes', 'DIVISION', 1),
(7, b'0', 'Exploitation des SI', 'DIVISION', 1),
(8, b'0', 'Transformation digitale et solution SI', 'DIVISION', 1),
(9, b'0', 'Parc Informatique, Assistance technique et maintenance', 'SERVICE', 7),
(10, b'0', 'Intégration des solutions SI', 'SERVICE', 8),
(11, b'0', 'Systèmes et sécurité informatique', 'SERVICE', 6);

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
  `id_produit` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inventaire`
--

INSERT INTO `inventaire` (`id_inventaire`, `etat`, `hostname`, `numero_serie`, `id_produit`) VALUES
(1, 'ACTIF', '00001', 'IMP456315', 1),
(2, 'ENREPARATION', NULL, 'IMP456316', 1),
(3, 'ACTIF', '00003', 'IMP456317', 1),
(4, 'ENSTOCK', '00004', 'RTTR45545', 2),
(5, 'ENSTOCK', '00005', 'RTTR45546', 2),
(6, 'ENSTOCK', '00006', 'RTTR45547', 2),
(7, 'ENSTOCK', '00007', 'RTTR45548', 2),
(8, 'ENSTOCK', '00008', 'RTTR45549', 2),
(9, 'ENSTOCK', '00009', 'IMM564545', 3),
(10, 'ENSTOCK', '00010', 'IMM564546', 3),
(11, 'ENSTOCK', '00011', 'IMM564547', 3),
(12, 'ACTIF', '00012', 'CPCPC3455', 4),
(13, 'ACTIF', '00013', 'CPCPC3456', 4),
(14, 'ENSTOCK', '00014', 'CPCPC3457', 4),
(15, 'ENSTOCK', '00015', 'CPCPC3458', 4),
(16, 'ENSTOCK', '00016', 'CPCPC3459', 4),
(17, 'ACTIF', '00017', 'SCSC64646', 5),
(18, 'ACTIF', '00018', 'SCSC64647', 5),
(19, 'ENSTOCK', '00019', 'SCSC64648', 5),
(20, 'ENSTOCK', '00020', 'SCSC64649', 5),
(21, 'ENSTOCK', '00021', 'SCSC64650', 5),
(22, 'ENSTOCK', '00022', 'SCSC64651', 5),
(23, 'ENSTOCK', '00023', 'SCSC64652', 5),
(24, 'ENSTOCK', '00024', 'UCUC36453', 6),
(25, 'ENSTOCK', '00025', 'UCUC36454', 6),
(26, 'ENSTOCK', '00026', 'UCUC36455', 6),
(27, 'ENSTOCK', '00027', 'UCUC36456', 6),
(28, 'ENSTOCK', '00028', 'UCUC36457', 6);

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
  `date_livraison` date NOT NULL,
  `delai` int(11) NOT NULL,
  `description` text NOT NULL,
  `imageurl` varchar(255) DEFAULT NULL,
  `nom_produit` text NOT NULL,
  `prix` double NOT NULL,
  `id_categorie` bigint(20) DEFAULT NULL,
  `id_fournisseur` bigint(20) DEFAULT NULL,
  `id_marque` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `produit`
--

INSERT INTO `produit` (`id_produit`, `date_livraison`, `delai`, `description`, `imageurl`, `nom_produit`, `prix`, `id_categorie`, `id_fournisseur`, `id_marque`) VALUES
(1, '2024-06-02', 1, 'Passez à la vitesse supérieure avec l’ordinateur professionnel HP ProDesk 600 G3 Tour, goûtez à la puissance des processeurs de 6e génération et aux joies de la mémoire RAM DDR4 !\n\n- Modèle : HP ProDesk 600 G3\n- Format : Mini Tour\n- Processeur : Intel Core i5 6ème génération 3.20 GHz - 4 coeurs - 4 Threads - Turbo : 3,60 Ghz - DMI : 5 GT/s - Cache : 6 Mo - Socket FCLGA1151\n- Mémoire Vive : 8 Go - DDR4\n- Disque dur : 256Go - SSD \n- Lecteur optique :Oui\n- Carte graphique : Intel HD Graphics 530\n- Carte son : Intégrée\n- Réseau : Intel® I219LM Gigabit Ethernet\n- Système d\'exploitation installé : Microsoft Windows 10 64 bits \n- Alimentation : 280 Watts - 80+ Platinum\n- Dimensions : Hauteur : 10 cm / Largeur : 34 cm / Profondeur : 37,9 cm', 'imprimante_20240602140030.jpg', 'Hp Unité centrale i5 Ram 8Go -256Go SSD 6éme Gén - Remis à Neuf', 1990, 1, 2, 1),
(2, '2024-06-02', 3, 'PORTS DE COMMUTATEUR : 8 ports 10/100/1000 + ports SFP 2 x 1G\nFLEXIBILITɠ: une gamme étendue de solutions pour une flexibilité optimale de 8 à 48 ports et des combinaisons PoE+.\nSIMPLICITɠ: l\'interface utilisateur intégrée, l\'application mobile et le tableau de bord Cisco Business Dashboard intuitifs simplifient les opérations réseau et automatisent la gestion tout au long du cycle de vie de la solution.\nSÉCURITɠ: IEEE 802.1X intégré pour le contrôle des accès au réseau, prévention des attaques DoS pour améliorer la disponibilité lors d\'une attaque et listes de contrôle d\'accès (ACL) pour protéger le réseau contre les utilisateurs non autorisés\nCONCEPTION INNOVANTE : un design élégant et compact, idéal pour une installation en extérieur de l\'armoire de répartition dans les commerces, les bureaux ouverts et les salles de classe par exemple\nEFFICACITÉ ÉNERGÉTIQUE : optimise la consommation énergétique pour réduire les coûts d\'exploitation. Conforme au standard IEEE802.3az Energy Efficient Ethernet. Une conception sans ventilateur (pour certains modèles uniquement)\nSÉRÉNITɠ: une garantie matérielle limitée de 3 ans avec retour atelier pour remplacement et assistance technique d\'un an', '61eToe-wc1S._AC_UF1000,1000_QL80__20240602141017.jpg', 'Cisco Commutateur Intelligent Business CBS220-8T-E-2G | 8 Ports GE | Ports Small Form-Factor Pluggable (SFP) 2 x 1G | Garantie matérielle limitée de 3 ans (CBS220-8T-E-2G-EU)', 450, 4, 2, 3),
(3, '2024-06-02', 1, 'Impression, numérisation et copie à domicile sans effort\nEntrez dans un pays des merveilles sans fil avec le Canon PIXMA 3340, un tout-en-un polyvalent pour l\'impression, la numérisation et la copie de documents rapidement et simplement. Conçu pour répondre à tous vos besoins quotidiens en matière d\'impression, qu\'il s\'agisse de documents comportant beaucoup de texte ou de photographies en couleur, cette imprimante multifonction compatible Wi-Fi est conçue dans un format compact et convivial.\nImprimez à partir de votre smartphone, tablette ou appareil photo\nLa prise en charge de Google Cloud Print et de l\'application Canon PRINT pour iOS et Android permettent d\'imprimer à partir d\'appareils mobiles. Vous pouvez également imprimer sans fil à partir de votre caméra compatible PictBridge WLAN, tandis que le mode Point d\'accès signifie que les utilisateurs mobiles peuvent se connecter à l\'imprimante même sans réseau Wi-Fi.\nImpression mobile et cloud\nGrâce à PIXMA Cloud Link, accessible via l\'application Canon PRINT, vous pouvez désormais imprimer à partir de réseaux sociaux et de services cloud populaires', 'imprimante5_20240602142035.jpg', 'Canon Jet d\'encre Pixma TS3340 MFP Wifi', 429, 3, 2, 6),
(4, '2024-06-02', 1, 'Explorez notre dernier ordinateur portable ASUS, conçu pour répondre à vos besoins informatiques quotidiens avec style et performance. Doté d\'un écran 15,6\" Full HD 1920 x 1080 au format 16:9, cet afficheur antireflet ultra-fin de 200 nits offre une expérience visuelle immersive, vous permettant de profiter de vos contenus avec une clarté exceptionnelle, même dans des environnements lumineux.Alimenté par un puissant processeur Intel® Core™ i3 de 10e génération, cet ordinateur portable ASUS assure une réactivité exceptionnelle pour toutes vos tâches, du travail productif au streaming de contenu multimédia. Avec 4 Go de mémoire DDR4 RAM, bénéficiez d\'une performance fluide et efficace, même lors de l\'utilisation de plusieurs applications simultanément.Le disque dur SATA de 1 To à 5400 tr/min offre un espace de stockage généreux pour vos fichiers, photos, vidéos et bien plus encore, vous permettant de conserver tous vos souvenirs et documents importants à portée de main. Le clavier chiclet à touches d\'île assure une expérience de frappe confortable et précise, tandis que le système audio intégré, avec haut-parleurs et microphone, offre une qualité sonore exceptionnelle grâce à la technologie ICEpower® Sonic Master.Préinstallé avec Windows 10 Home, cet ordinateur portable ASUS vous offre une interface conviviale et familière ainsi qu\'un accès facile à une gamme étendue d\'applications et de fonctionnalités. Que ce soit pour le travail, les études ou le divertissement, cet ordinateur portable ASUS est prêt à vous accompagner dans toutes vos aventures numériques, offrant performance, fiabilité et style.', 'pcportable_20240602142351.jpg', 'Asus PC Portable X509J i3-1005G1 4GB/ 1T AZERTY', 3490, 2, 1, 2),
(5, '2024-06-02', 1, 'En associant un scanner à plat à la commodité d’un chargeur automatique de documents de 50 pages, il devient possible de numériser une large gamme de documents difficiles, tels que livres, documents reliés, livrets de famille, passeports et autres supports délicats tout en numérisant rapidement et simplement des lots de documents de bureau. Son design compact facilite son installation dans les espaces de vente et les environnements recevant des clients, sans oublier les groupes de travail des services administratifs.', 'scanner_20240602142649.jpg', 'Epson Scanner A4 à plat WORKFORCE DS-1630', 3890, 5, 2, 7),
(6, '2024-06-02', 2, 'Ce pc de bureau est conçu pour optimiser votre productivité au quotidien. Les fonctionnalités du Dell OptiPlex 3020 simplifient la gestion et l\'administration de vos tâches professionnelles et personnelles.\n\nProcesseur\n\nN° du processeur :  Intel Celeron G 1840\nNb. de cœurs : 4\nFréquence de base : 2,8 GHz\nCache : 3MB SmartCache\n \n\nMémoire vive\n\ncapacité : 4 Go\nType : DDR3\n \n\nStockage\n\nCapacité : 500 HDD\nType : SATA\nVitesse de rotation : 7200 tr/min\n \n\nGraphique\n\nIntel HD GRraphics', 'unitecentrale_20240602160753.jpg', 'DELL PC BUREAU OptiPlex 3020 SFF PEINTUM-RAM 4Go-HDD 500Go-avec Ecran 19\" (Remis a Neuf)', 1099, 1, 1, 4);

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
(4, '2024-06-02', NULL, 9, 4),
(5, '2024-06-02', NULL, 11, 5);

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
(1, 'super admin', '$2a$10$UuP1kiyUQxpf9lYYDDTZluMEsuDA7clQTLF7tntbA1FGMtzbskRGa', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a657870000000017704000000017e720043636f6d2e67657374696f6e50617263496e666f726d6174697175652e67657374696f6e50617263496e666f726d6174697175652e557365722e6d6f64656c2e526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d0000000000000000120000787074000b53555045525f41444d494e78, '00000');

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
(4, '01156', b'0', 'azami.bouchra@radeef.ma', 'AZAMI HASSANI', 'Bouchra'),
(5, '02238', b'0', 'taqui.ayoub@radeef.ma', 'TAQUI', 'Ayoub');

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
  ADD KEY `FKqsrethqkd21x52as8pmruxce0` (`id_produit`);

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
  ADD KEY `FK6g04laafmn1wh32padmqxtjs7` (`id_fournisseur`),
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
  MODIFY `id_affectation` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `attacher`
--
ALTER TABLE `attacher`
  MODIFY `id_attachment` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id_categorie` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `entite_travail`
--
ALTER TABLE `entite_travail`
  MODIFY `id_entite_travail` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `fournisseur`
--
ALTER TABLE `fournisseur`
  MODIFY `id_fournisseur` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `inventaire`
--
ALTER TABLE `inventaire`
  MODIFY `id_inventaire` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `marque`
--
ALTER TABLE `marque`
  MODIFY `id_marque` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `produit`
--
ALTER TABLE `produit`
  MODIFY `id_produit` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `travailler`
--
ALTER TABLE `travailler`
  MODIFY `id_travail` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id_utilisateur` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

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
  ADD CONSTRAINT `FKqsrethqkd21x52as8pmruxce0` FOREIGN KEY (`id_produit`) REFERENCES `produit` (`id_produit`);

--
-- Constraints for table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `FK6g04laafmn1wh32padmqxtjs7` FOREIGN KEY (`id_fournisseur`) REFERENCES `fournisseur` (`id_fournisseur`),
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
