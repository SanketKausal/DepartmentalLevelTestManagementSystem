-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 03, 2021 at 05:39 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test_management_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `mobile` bigint(20) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `name`, `mobile`, `password`) VALUES
(1, 'Sangram', 9757111111, '12345678');

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

CREATE TABLE `class` (
  `id` int(11) NOT NULL,
  `department` varchar(100) NOT NULL,
  `class` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`id`, `department`, `class`) VALUES
(1, 'Computer Engineering', 'First Year'),
(2, 'Information Technology', 'First Year'),
(3, 'Computer Engineering', 'Second Year'),
(4, 'Information Technology', 'Second Year'),
(5, 'Computer Engineering', 'Third Year'),
(6, 'Information Technology', 'Third Year'),
(7, 'Computer Engineering', 'Fourth Year'),
(8, 'Information Technology', 'Fourth Year');

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `id` int(11) NOT NULL,
  `department` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`id`, `department`) VALUES
(1, 'Computer Engineering'),
(2, 'Information Technology');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `mobile` bigint(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  `department` varchar(100) NOT NULL,
  `class` varchar(100) NOT NULL,
  `subject` varchar(100) NOT NULL,
  `test` varchar(100) NOT NULL,
  `score` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `name`, `mobile`, `password`, `department`, `class`, `subject`, `test`, `score`) VALUES
(1, 'Aaradhya', 9757000000, '12345678', 'Computer Engineering', 'First Year', 'Basic Civil and Environmental Engineering (BCEE) ', 'Online In-Sem - I', 0),
(2, 'Adah', 9757000001, '12345678', 'Computer Engineering', 'First Year', 'Basic Electrical Engineering (BEE) ', 'Online In-Sem - I', 0),
(3, 'Kashvi', 9757000002, '12345678', 'Computer Engineering', 'First Year', 'Engineering Chemistry ', 'Online In-Sem - II', 0),
(4, 'Mehar', 9757000003, '12345678', 'Computer Engineering', 'Second Year', 'Computer Organization ', 'Unit Test - I', 0),
(6, 'Nehrika', 9757000004, '12345678', 'Computer Engineering', 'Second Year', 'Data Structures Algorithm ', 'Unit Test - I', 0),
(8, 'Sahana', 9757000005, '12345678', 'Computer Engineering', 'Second Year', 'Engineering Maths 3 ', 'Unit Test - II', 0),
(9, 'Sneha', 9757000006, '12345678', 'Computer Engineering', 'Second Year', 'Microprocessor and Interfacing Techniques', 'Unit Test - II', 0),
(10, 'Tanvi', 9757000007, '12345678', 'Computer Engineering', 'Third Year', 'Theory of Computation ', 'Unit Test - I', 0),
(11, 'Sai', 9757000008, '12345678', 'Computer Engineering', 'Third Year', ' Database Management Systems Applications ', 'Unit Test - I', 0),
(12, 'Saloni', 9757000009, '12345678', 'Computer Engineering', 'Third Year', 'Computer Forensic and Cyber Applications ', 'Unit Test - II', 0),
(13, 'Neysa', 9757000010, '12345678', 'Computer Engineering', 'Third Year', 'Software Engineering ', 'Unit Test - II', 0),
(14, 'Pavati', 9757000011, '12345678', 'Computer Engineering', 'Third Year', 'Embedded Operating Systems ', 'Unit Test - II', 0),
(15, 'Mayra', 9757000012, '12345678', 'Computer Engineering', 'Fourth Year', 'Artificial Intelligence ', 'Unit Test - I', 0),
(16, 'Krisha', 9757000013, '12345678', 'Computer Engineering', 'Fourth Year', 'Design & Analysis of Algorithms ', 'Unit Test - I', 0),
(17, 'Ira', 9757000014, '12345678', 'Computer Engineering', 'Fourth Year', 'Pervasive Computing ', 'Unit Test - I', 0),
(18, 'Drishti', 9757000015, '12345678', 'Computer Engineering', 'Fourth Year', 'Advanced Computer Architecture ', 'Unit Test - II', 0),
(19, 'Anushka', 9757000016, '12345678', 'Computer Engineering', 'Fourth Year', 'Business Analytic and Intelligence', 'Unit Test - II', 0),
(20, 'Adhira', 9757000017, '12345678', 'Information Technology', 'First Year', 'Basic Civil and Environmental Engineering (BCEE) ', 'Unit Test - I', 0),
(21, 'Avni', 9757000018, '12345678', 'Information Technology', 'First Year', 'Basic Electrical Engineering (BEE) ', 'Unit Test - I', 0),
(22, 'Keya', 9757000019, '12345678', 'Information Technology', 'First Year', 'Engineering Chemistry ', 'Unit Test - II', 0),
(23, 'Ishana', 97570000020, '12345678', 'Information Technology', 'Second Year', 'Computer Organization & Architecture ', 'Unit Test - I', 0),
(24, 'Naitee', 9757000021, '12345678', 'Information Technology', 'Second Year', 'Discrete Structures ', 'Unit Test - I', 0),
(25, 'Ryka', 9757000022, '12345678', 'Information Technology', 'Second Year', 'Problem Solving and Object Oriented Programming ', 'Unit Test - II', 0),
(26, 'Saanvi', 9757000023, '12345678', 'Information Technology', 'Second Year', 'Processor Architectures & Interfacing ', 'Unit Test - II', 0),
(27, 'Saloni', 97570000024, '12345678', 'Information Technology', 'Third Year', 'Database Management Systems ', 'Unit Test - I', 0),
(28, 'Taara', 9757000025, '12345678', 'Information Technology', 'Third Year', 'Design and Analysis of Algorithms ', 'Unit Test - II', 0),
(29, 'Viti', 9757000026, '12345678', 'Information Technology', 'Fourth Year', 'Advance Database Management ', 'Unit Test - I', 0),
(30, 'Prisha', 9757000027, '12345678', 'Information Technology', 'Fourth Year', 'Business Analytics & Intelligence', 'Unit Test - II', 0),
(31, 'Naitee', 9757000028, '12345678', 'Information Technology', 'Fourth Year', 'Advanced Graphics & Animation', 'Unit Test - II', 0);

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `id` int(11) NOT NULL,
  `department` varchar(100) NOT NULL,
  `class` varchar(100) DEFAULT NULL,
  `subject` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`id`, `department`, `class`, `subject`) VALUES
(1, 'Computer Engineering', 'First Year', 'Basic Civil and Environmental Engineering (BCEE) '),
(2, 'Information Technology', 'First Year', 'Basic Civil and Environmental Engineering (BCEE) '),
(3, 'Computer Engineering', 'First Year', 'Basic Electrical Engineering (BEE) '),
(4, 'Information Technology', 'First Year', 'Basic Electrical Engineering (BEE) '),
(5, 'Computer Engineering', 'First Year', 'Engineering Chemistry '),
(6, 'Information Technology', 'First Year', 'Engineering Chemistry '),
(7, 'Computer Engineering', 'Second Year', 'Computer Organization '),
(8, 'Computer Engineering', 'Second Year', 'Data Structures Algorithm '),
(9, 'Computer Engineering', 'Second Year', 'Engineering Maths 3 '),
(10, 'Computer Engineering', 'Second Year', 'Microprocessor and Interfacing Techniques'),
(11, 'Information Technology', 'Second Year', 'Computer Organization & Architecture '),
(12, 'Information Technology', 'Second Year', 'Discrete Structures '),
(13, 'Information Technology', 'Second Year', 'Problem Solving and Object Oriented Programming '),
(14, 'Information Technology', 'Second Year', 'Processor Architectures & Interfacing '),
(15, 'Computer Engineering', 'Third Year', 'Theory of Computation '),
(16, 'Computer Engineering', 'Third Year', ' Database Management Systems Applications '),
(17, 'Computer Engineering', 'Third Year', 'Computer Forensic and Cyber Applications '),
(18, 'Computer Engineering', 'Third Year', 'Software Engineering '),
(19, 'Computer Engineering', 'Third Year', 'Embedded Operating Systems '),
(20, 'Information Technology', 'Third Year', 'Database Management Systems '),
(21, 'Information Technology', 'Third Year', 'Software Engineering '),
(22, 'Information Technology', 'Third Year', 'Design and Analysis of Algorithms '),
(23, 'Information Technology', 'Third Year', 'Systems Programming '),
(24, 'Computer Engineering', 'Fourth Year', 'Artificial Intelligence '),
(25, 'Computer Engineering', 'Fourth Year', 'Design & Analysis of Algorithms '),
(26, 'Computer Engineering', 'Fourth Year', 'Pervasive Computing '),
(27, 'Computer Engineering', 'Fourth Year', 'Advanced Computer Architecture '),
(28, 'Computer Engineering', 'Fourth Year', 'Business Analytic and Intelligence'),
(29, 'Information Technology', 'Fourth Year', 'Advance Database Management '),
(30, 'Information Technology', 'Fourth Year', 'Artificial Intelligence '),
(31, 'Information Technology', 'Fourth Year', 'Business Analytics & Intelligence'),
(32, 'Information Technology', 'Fourth Year', 'Advance Computer Networks '),
(33, 'Information Technology', 'Fourth Year', 'Advanced Graphics & Animation');

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE `test` (
  `id` int(11) NOT NULL,
  `department` varchar(100) NOT NULL,
  `class` varchar(100) NOT NULL,
  `subject` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `date` date NOT NULL,
  `total_score` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `test`
--

INSERT INTO `test` (`id`, `department`, `class`, `subject`, `name`, `date`, `total_score`) VALUES
(1, 'Computer Engineering', 'First Year', 'Basic Civil and Environmental Engineering (BCEE) ', 'Online In-Sem - I', '2020-10-08', 50),
(2, 'Computer Engineering', 'First Year', 'Basic Electrical Engineering (BEE) ', 'Online In-Sem - I', '2020-10-10', 50),
(3, 'Computer Engineering', 'First Year', 'Engineering Chemistry ', 'Online In-Sem - II', '2020-10-12', 50),
(4, 'Computer Engineering', 'Second Year', 'Computer Organization ', 'Unit Test - I', '2020-10-20', 50),
(5, 'Computer Engineering', 'Second Year', 'Data Structures Algorithm ', 'Unit Test - I', '2020-10-23', 50),
(6, 'Computer Engineering', 'Second Year', 'Engineering Maths 3 ', 'Unit Test - II', '2020-10-25', 50),
(7, 'Computer Engineering', 'Second Year', 'Microprocessor and Interfacing Techniques', 'Unit Test - II', '2020-10-27', 50),
(8, 'Computer Engineering', 'Third Year', 'Theory of Computation ', 'Unit Test - I', '2020-10-29', 50),
(9, 'Computer Engineering', 'Third Year', ' Database Management Systems Applications ', 'Unit Test - I', '2020-10-31', 50),
(10, 'Computer Engineering', 'Third Year', 'Computer Forensic and Cyber Applications ', 'Unit Test - II', '2020-11-01', 50),
(11, 'Computer Engineering', 'Third Year', 'Software Engineering ', 'Unit Test - II', '2020-11-03', 50),
(12, 'Computer Engineering', 'Third Year', 'Embedded Operating Systems ', 'Unit Test - II', '2020-11-05', 50),
(13, 'Computer Engineering', 'Fourth Year', 'Artificial Intelligence ', 'Unit Test - I', '2020-11-07', 50),
(14, 'Computer Engineering', 'Fourth Year', 'Design & Analysis of Algorithms ', 'Unit Test - I', '2020-11-09', 50),
(15, 'Computer Engineering', 'Fourth Year', 'Pervasive Computing ', 'Unit Test - I', '2020-11-11', 50),
(16, 'Computer Engineering', 'Fourth Year', 'Advanced Computer Architecture ', 'Unit Test - II', '2020-11-13', 50),
(17, 'Computer Engineering', 'Fourth Year', 'Business Analytic and Intelligence', 'Unit Test - II', '2020-11-15', 50),
(18, 'Information Technology', 'First Year', 'Basic Civil and Environmental Engineering (BCEE) ', 'Unit Test - I', '2020-11-17', 50),
(19, 'Information Technology', 'First Year', 'Basic Electrical Engineering (BEE) ', 'Unit Test - I', '2020-11-19', 50),
(20, 'Information Technology', 'First Year', 'Engineering Chemistry ', 'Unit Test - II', '2020-11-20', 50),
(21, 'Information Technology', 'Second Year', 'Computer Organization & Architecture ', 'Unit Test - I', '2020-11-22', 50),
(22, 'Information Technology', 'Second Year', 'Discrete Structures ', 'Unit Test - I', '2020-11-24', 50),
(23, 'Information Technology', 'Second Year', 'Problem Solving and Object Oriented Programming ', 'Unit Test - II', '2020-11-26', 50),
(24, 'Information Technology', 'Second Year', 'Processor Architectures & Interfacing ', 'Unit Test - II', '2020-11-28', 50),
(25, 'Information Technology', 'Third Year', 'Database Management Systems ', 'Unit Test - I', '2020-12-01', 50),
(26, 'Information Technology', 'Third Year', 'Software Engineering ', 'Unit Test - I', '2020-12-03', 50),
(27, 'Information Technology', 'Third Year', 'Design and Analysis of Algorithms ', 'Unit Test - II', '2020-12-05', 50),
(28, 'Information Technology', 'Third Year', 'Systems Programming ', 'Unit Test - II', '2020-12-08', 50),
(30, 'Information Technology', 'Fourth Year', 'Advance Database Management ', 'Unit Test - I', '2020-12-10', 50),
(31, 'Information Technology', 'Fourth Year', 'Artificial Intelligence ', 'Unit Test - I', '2020-12-12', 50),
(32, 'Information Technology', 'Fourth Year', 'Business Analytics & Intelligence', 'Unit Test - II', '2020-12-20', 50),
(33, 'Information Technology', 'Fourth Year', 'Advance Computer Networks ', 'Unit Test - II', '2020-12-22', 50),
(34, 'Information Technology', 'Fourth Year', 'Advanced Graphics & Animation', 'Unit Test - II', '2020-12-24', 50);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `mobile` (`mobile`);

--
-- Indexes for table `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `department` (`department`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `mobile` (`mobile`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `test`
--
ALTER TABLE `test`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `date` (`date`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `class`
--
ALTER TABLE `class`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `subject`
--
ALTER TABLE `subject`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `test`
--
ALTER TABLE `test`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
