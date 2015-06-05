-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Giu 05, 2015 alle 04:32
-- Versione del server: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `esempi_servlet`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `acquisti`
--

CREATE TABLE IF NOT EXISTS `acquisti` (
  `numero_ordine` int(11) NOT NULL,
  `id_prodotto` int(11) NOT NULL,
  `quantita` int(11) NOT NULL,
  UNIQUE KEY `numero_ordine_3` (`numero_ordine`,`id_prodotto`),
  KEY `numero_ordine` (`numero_ordine`),
  KEY `numero_ordine_2` (`numero_ordine`),
  KEY `id_prodotto` (`id_prodotto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `acquisti`
--

INSERT INTO `acquisti` (`numero_ordine`, `id_prodotto`, `quantita`) VALUES
(5, 2, 2),
(5, 3, 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `catalogo`
--

CREATE TABLE IF NOT EXISTS `catalogo` (
  `id_prodotto` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(40) NOT NULL,
  `descrizione` text,
  `quantita` int(11) NOT NULL,
  `prezzo` int(11) NOT NULL,
  PRIMARY KEY (`id_prodotto`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dump dei dati per la tabella `catalogo`
--

INSERT INTO `catalogo` (`id_prodotto`, `nome`, `descrizione`, `quantita`, `prezzo`) VALUES
(1, 'Dell XPS 13', 'Nuovo ultrabook versione 2015 Dell.', 10, 989),
(2, 'Moto 360', 'Smartwatch motorola.', 20, 190),
(3, 'Raspberry Pi', 'Il piccolo Lampone', 30, 25);

-- --------------------------------------------------------

--
-- Struttura della tabella `ordini`
--

CREATE TABLE IF NOT EXISTS `ordini` (
  `numero_ordine` int(11) NOT NULL AUTO_INCREMENT,
  `data_ordine` date NOT NULL,
  `id_utente` int(11) NOT NULL,
  `totale` int(11) NOT NULL,
  PRIMARY KEY (`numero_ordine`),
  KEY `id_utente` (`id_utente`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dump dei dati per la tabella `ordini`
--

INSERT INTO `ordini` (`numero_ordine`, `data_ordine`, `id_utente`, `totale`) VALUES
(5, '2015-06-05', 1, 430);

-- --------------------------------------------------------

--
-- Struttura della tabella `recensioni`
--

CREATE TABLE IF NOT EXISTS `recensioni` (
  `numero_ordine` int(11) NOT NULL,
  `id_prodotto` int(11) NOT NULL,
  `id_recensione` int(11) NOT NULL AUTO_INCREMENT,
  `testo_recensione` text NOT NULL,
  `id_utente` int(11) NOT NULL,
  `stars` int(11) NOT NULL,
  `data` date NOT NULL,
  PRIMARY KEY (`id_recensione`),
  UNIQUE KEY `numero_ordine` (`numero_ordine`,`id_prodotto`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dump dei dati per la tabella `recensioni`
--

INSERT INTO `recensioni` (`numero_ordine`, `id_prodotto`, `id_recensione`, `testo_recensione`, `id_utente`, `stars`, `data`) VALUES
(5, 3, 2, 'Sicuramente un ottimo prodotto. Da acquistare se siete in cerca di una board linux per la domotica.', 1, 4, '2015-06-05');

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
--

CREATE TABLE IF NOT EXISTS `utenti` (
  `id_utente` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(40) NOT NULL,
  `nome` varchar(25) NOT NULL,
  `cognome` varchar(25) NOT NULL,
  PRIMARY KEY (`id_utente`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dump dei dati per la tabella `utenti`
--

INSERT INTO `utenti` (`id_utente`, `username`, `password`, `email`, `nome`, `cognome`) VALUES
(1, 'rocco', '123456', 'rocco@hackerstribe.com', 'Rocco', 'Musolino');

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `acquisti`
--
ALTER TABLE `acquisti`
  ADD CONSTRAINT `acquisti_ibfk_2` FOREIGN KEY (`id_prodotto`) REFERENCES `catalogo` (`id_prodotto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `acquisti_ibfk_1` FOREIGN KEY (`numero_ordine`) REFERENCES `ordini` (`numero_ordine`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `ordini`
--
ALTER TABLE `ordini`
  ADD CONSTRAINT `ordini_ibfk_1` FOREIGN KEY (`id_utente`) REFERENCES `utenti` (`id_utente`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
