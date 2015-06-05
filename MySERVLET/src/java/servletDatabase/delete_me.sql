-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Giu 02, 2015 alle 18:38
-- Versione del server: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `delete_me`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `libri`
--

CREATE TABLE IF NOT EXISTS `libri` (
  `id_libro` int(11) NOT NULL AUTO_INCREMENT,
  `nome_libro` varchar(45) NOT NULL,
  `autore` varchar(25) NOT NULL,
  `quantita` int(11) NOT NULL,
  `data` date DEFAULT NULL,
  PRIMARY KEY (`id_libro`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dump dei dati per la tabella `libri`
--

INSERT INTO `libri` (`id_libro`, `nome_libro`, `autore`, `quantita`, `data`) VALUES
(1, 'Harry Potter e la pietra filosofale', 'J. K. Rowling', 2, NULL),
(2, 'Harry Potter e la camera dei segreti', 'J. K. Rowling', 3, '2015-03-10'),
(3, 'Game of Thrones', 'George R. Martin', 5, '2015-06-18'),
(5, 'Il signore degli anelli', 'J.R. Tolkien', 4, '2015-06-02');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
