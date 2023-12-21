-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 13, 2023 at 03:49 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.1.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hrm`
--

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `code` varchar(10) NOT NULL,
  `depName` varchar(255) NOT NULL,
  `tel` varchar(10) NOT NULL,
  `manager` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`code`, `depName`, `tel`, `manager`) VALUES
('0001', 'Acounting', '0989878340', 'MBS Sisira'),
('0002', 'HRM', '0989789700', 'EDP Masith'),
('0003', 'Personal', '0978774500', 'DG Sekara'),
('0004', 'Training', '0989877', 'MA Kumara'),
('0005', 'Admin', '0112354567', 'K.S. Silva');

-- --------------------------------------------------------

--
-- Table structure for table `designation`
--

CREATE TABLE `designation` (
  `code` varchar(10) NOT NULL,
  `DesigName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `designation`
--

INSERT INTO `designation` (`code`, `DesigName`) VALUES
('0001', 'Director'),
('0002', 'Administrative Officer'),
('0003', 'Manager'),
('0004', 'Management Assistant'),
('0005', 'IT Assistant'),
('0006', 'IT Officer');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `code` varchar(10) NOT NULL,
  `fullName` varchar(255) NOT NULL,
  `NameWithInit` varchar(255) NOT NULL,
  `NIC` varchar(12) NOT NULL,
  `dofb` date NOT NULL,
  `Gender` varchar(6) NOT NULL,
  `Designation` varchar(10) NOT NULL,
  `Department` varchar(10) NOT NULL,
  `user` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`code`, `fullName`, `NameWithInit`, `NIC`, `dofb`, `Gender`, `Designation`, `Department`, `user`) VALUES
('00005', 'Amara Sampath Perera', 'A.S. Perera', '860987897V', '1986-11-22', 'male', '0003', '0002', 'user'),
('0001', 'Malaka Madushan Perera', 'M.M. Perera', '793434556V', '2023-11-25', 'male', '0001', '0003', 'admin'),
('0002', 'Waligamage Sanath Kumara', 'WS Kumara', '111111111V', '1989-11-04', 'male', '0002', '0003', 'admin'),
('0003', 'Kumudu Samanthi Silva', 'K.S. Siliva', '796787565V', '1978-03-23', 'female', '0004', '0005', 'admin'),
('0004', 'Kamal Mahesh Prerea', 'K.M.. Perera', '793434556V', '2023-11-25', 'male', '0003', '0003', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `userName` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `isAdmin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`userName`, `password`, `isAdmin`) VALUES
('admin', '19223a7bbd7325516f069df18b50', 1),
('su', '19223a7bbd7325516f069df18b50', 0),
('user', '6ad14ba9986e3615423dfca256d04e3f', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`code`);

--
-- Indexes for table `designation`
--
ALTER TABLE `designation`
  ADD PRIMARY KEY (`code`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`code`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`userName`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
