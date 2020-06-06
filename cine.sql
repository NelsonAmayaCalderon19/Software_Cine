-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-08-2019 a las 17:07:11
-- Versión del servidor: 10.1.38-MariaDB
-- Versión de PHP: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `cine`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `espectador`
--

CREATE TABLE `espectador` (
  `id` int(5) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `edad` int(3) NOT NULL,
  `dinero` int(11) NOT NULL,
  `pelicula` varchar(50) NOT NULL,
  `silla` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `espectador`
--

INSERT INTO `espectador` (`id`, `nombre`, `edad`, `dinero`, `pelicula`, `silla`) VALUES
(1, 'Nelson', 19, 12000, 'Shazam', 'A8'),
(2, 'Jaider', 25, 15000, 'Dumbo', 'F4'),
(3, 'Estela', 19, 15000, 'Dumbo', 'G1'),
(4, 'Ramón', 38, 7500, 'Parque Magico', 'G1'),
(5, 'Geovanny', 30, 8000, 'Capitana Marvel', 'F3'),
(6, 'Luis', 30, 9500, 'La Maldición de la Llorona', 'B4'),
(7, 'Ana', 17, 9500, 'La Maldición de la Llorona', 'D4'),
(8, 'Pepe', 19, 9500, 'La Maldición de la Llorona', 'F7'),
(9, 'Estorgio', 41, 11000, 'Cementerio Maldito', 'H7'),
(10, 'Agustin', 25, 5000, 'HellBoy', 'A1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pelicula`
--

CREATE TABLE `pelicula` (
  `id` int(5) NOT NULL,
  `titulo` varchar(50) NOT NULL,
  `duracion` varchar(50) NOT NULL,
  `descripcion` longtext NOT NULL,
  `edadminima` int(3) NOT NULL,
  `director` varchar(50) NOT NULL,
  `horaFuncion` time NOT NULL,
  `precioEntrada` double NOT NULL,
  `imagen` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `pelicula`
--


--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `espectador`
--
ALTER TABLE `espectador`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `espectador`
--
ALTER TABLE `espectador`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
