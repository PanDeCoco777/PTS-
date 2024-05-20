-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 20, 2024 at 03:16 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `userandadmin`
--

-- --------------------------------------------------------

--
-- Table structure for table `parking`
--

CREATE TABLE `parking` (
  `ID` int(50) NOT NULL,
  `TicketNo` int(50) NOT NULL,
  `c_name` varchar(255) NOT NULL,
  `b_brand` varchar(255) NOT NULL,
  `p_plate` varchar(255) NOT NULL,
  `a_at` varchar(255) NOT NULL,
  `c_c` varchar(255) NOT NULL,
  `v_type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales` (
  `id` int(11) NOT NULL,
  `subtotal` int(11) NOT NULL,
  `pay` int(11) NOT NULL,
  `balance` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sales`
--

INSERT INTO `sales` (`id`, `subtotal`, `pay`, `balance`) VALUES
(1, 650, 1000, 350),
(2, 950, 1000, 50),
(3, 1600, 500, -1100),
(4, 900, 1000, 100);

-- --------------------------------------------------------

--
-- Table structure for table `sales_product`
--

CREATE TABLE `sales_product` (
  `id` int(11) NOT NULL,
  `sales_id` int(11) NOT NULL,
  `tickettype` varchar(255) NOT NULL,
  `qty` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sales_product`
--

INSERT INTO `sales_product` (`id`, `sales_id`, `tickettype`, `qty`, `price`, `total`) VALUES
(1, 2, 'ODC', 2, 300, 600),
(2, 2, 'Balcony', 1, 350, 350),
(3, 3, 'ODC', 3, 300, 900),
(4, 3, 'Balcony', 2, 350, 700),
(5, 4, 'Box', 1, 400, 400),
(6, 4, 'Super Balcony', 1, 500, 500);

-- --------------------------------------------------------

--
-- Table structure for table `ttb`
--

CREATE TABLE `ttb` (
  `u_id` int(11) NOT NULL COMMENT '1000',
  `u_Email` varchar(50) NOT NULL,
  `u_Fname` varchar(50) NOT NULL,
  `u_Lname` varchar(50) NOT NULL,
  `u_Username` varchar(50) NOT NULL,
  `u_Password` varchar(255) NOT NULL,
  `u_type` varchar(50) NOT NULL,
  `u_status` varchar(50) NOT NULL,
  `u_image` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ttb`
--

INSERT INTO `ttb` (`u_id`, `u_Email`, `u_Fname`, `u_Lname`, `u_Username`, `u_Password`, `u_type`, `u_status`, `u_image`) VALUES
(1001, 'Steven@gmail.com', 'Steven', 'Pable', 'PandeCoco', 'FeKw08M4keuw8e9gnsQZQgwg4yDOlMZfvIwzEkSOsiU=', 'Admin', 'Active', ''),
(1002, 'Pable@gmail.com', 'Pable', 'Steven', 'Pan', 'ky88G1YlfOhTmsJp16q0JVDaz4gY0HXwvfGZBWKq4+8=', 'User', 'Active', ''),
(1003, 'Guko@gmail.com', 'Guko', 'Buko', 'kakarot', 'FeKw08M4keuw8e9gnsQZQgwg4yDOlMZfvIwzEkSOsiU=', 'User', 'Pending', ''),
(1004, 'Dummy@gmail.com', 'Mommy', 'single', 'Kape', 'hgdy0sDAIfISo/gdaL3V1v1ud24K86PhbKPysQMmc8k=', 'User', 'Pending', ''),
(1005, 'LOGO', 'Logos', 'logo', 'logoo', 'ky88G1YlfOhTmsJp16q0JVDaz4gY0HXwvfGZBWKq4+8=', 'User', 'Active', 'src/userimages/loho1.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `parking`
--
ALTER TABLE `parking`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sales_product`
--
ALTER TABLE `sales_product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ttb`
--
ALTER TABLE `ttb`
  ADD PRIMARY KEY (`u_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `parking`
--
ALTER TABLE `parking`
  MODIFY `ID` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `sales`
--
ALTER TABLE `sales`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `sales_product`
--
ALTER TABLE `sales_product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `ttb`
--
ALTER TABLE `ttb`
  MODIFY `u_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '1000', AUTO_INCREMENT=1006;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
