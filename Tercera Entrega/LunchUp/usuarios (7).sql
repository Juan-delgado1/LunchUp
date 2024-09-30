-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-09-2024 a las 10:02:09
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `usuarios`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `firstName` varchar(30) NOT NULL,
  `lastName` varchar(30) NOT NULL,
  `dob` date NOT NULL,
  `university` varchar(30) NOT NULL,
  `restaurant` varchar(30) NOT NULL,
  `lunes` tinyint(1) NOT NULL,
  `martes` tinyint(1) NOT NULL,
  `miercoles` tinyint(1) NOT NULL,
  `jueves` tinyint(1) NOT NULL,
  `viernes` tinyint(1) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `matching` tinyint(4) DEFAULT NULL,
  `carrera` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `firstName`, `lastName`, `dob`, `university`, `restaurant`, `lunes`, `martes`, `miercoles`, `jueves`, `viernes`, `username`, `password`, `matching`, `carrera`) VALUES
(1, 'Juan', 'Delgado', '2004-01-14', 'nacional', 'ComedorYutakeuchi', 1, 0, 1, 0, 1, 'judelgadoe', '123', 1, 'Ingeniería Mecatrónica'),
(2, 'Lucia ', 'Santos', '1999-02-14', 'nacional', 'ComedorYutakeuchi', 1, 1, 1, 0, 0, 'alsantos', '123', 0, 'Ingeniería Mecatrónica'),
(3, 'David', 'Cedeno', '2003-03-06', 'nacional', 'ComedorBiologia', 0, 0, 0, 1, 0, 'dacedeno', '123', 1, 'Biologia'),
(4, 'Juan David', 'Ardila', '2005-04-25', 'nacional', 'ComedorBiologia', 1, 0, 1, 1, 0, 'judardila', '123', 0, 'Biologia'),
(5, 'Carlos', 'Quintero', '2002-09-16', 'nacional', 'ComedorCentral', 0, 1, 0, 1, 1, 'cquintero', '123', 1, 'Medicina'),
(6, 'Andres', 'Preciado', '2002-11-18', 'nacional', 'ComedorCentral', 0, 0, 0, 1, 1, 'anpreciado', '123', 0, 'Medicina'),
(7, 'Miguel', 'Malagon', '2002-10-04', 'nacional', 'ComedorCentral', 1, 0, 1, 0, 0, 'mimalagon', '123', 1, 'Artes Plasticas'),
(8, 'Dylan', 'Cruz', '2000-06-13', 'nacional', 'ComedorBiologia', 0, 1, 0, 0, 1, 'dycruz', '123', 0, 'Biologia'),
(9, 'Nicolas', 'Garzon', '2003-04-12', 'nacional', 'ComedorYutakeuchi', 0, 0, 0, 1, 0, 'nigarzon', '123', 1, 'Ingeniería de Sistemas'),
(10, 'Ariadna', 'Contreras', '2004-12-12', 'nacional', 'ComedorBiologia', 0, 1, 0, 1, 0, 'adcontreras', '123', 0, 'Biologia'),
(11, 'Alfonso', 'Delgado', '2024-09-01', 'nacional', 'ComedorYutakeuchi', 1, 0, 1, 1, 1, 'luisalfonso', '123', 1, 'Ingeniería Mecatrónica'),
(12, 'Elías', 'Estrada', '1948-12-28', 'nacional', 'ComedorYutakeuchi', 1, 0, 1, 1, 0, 'elestrada', '123', 0, 'Ingeniería Mecatrónica'),
(13, 'Amparo', 'Ordonez', '1946-08-28', 'nacional', 'ComedorBiologia', 1, 0, 1, 0, 1, 'amordo', '123', 0, 'Biologia'),
(14, 'Carlos', 'Dorado', '2004-06-07', 'nacional', 'ComedorYutakeuchi', 0, 0, 1, 1, 0, 'cdorado', '123', 0, 'Ingeniería de Sistemas'),
(15, 'Jonnier', 'Toledo', '2003-02-09', 'nacional', 'ComedorYutakeuchi', 1, 0, 0, 0, 0, 'jtoledo', '123', 1, 'Ingeniería Mecatrónica'),
(16, 'Johan', 'Rodríguez', '2003-09-16', 'nacional', 'ComedorBiologia', 1, 0, 0, 0, 0, 'jrodriguez', '123', 1, 'Biologia'),
(17, 'Mario', 'Fernandez', '2003-02-10', 'nacional', 'ComedorCentral', 1, 0, 0, 0, 0, 'mfernandez', '123', 1, 'Artes Plasticas'),
(18, 'Nicolas', 'Toro', '2004-01-15', 'nacional', 'ComedorCentral', 1, 0, 0, 0, 0, 'ntoro', '123', 0, 'Artes Plasticas');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=112;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
