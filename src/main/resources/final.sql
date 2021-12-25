-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Хост: localhost
-- Время создания: Дек 25 2021 г., 05:30
-- Версия сервера: 10.4.14-MariaDB
-- Версия PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `final`
--

-- --------------------------------------------------------

--
-- Структура таблицы `Book`
--

CREATE TABLE `Book` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `genre` varchar(100) NOT NULL,
  `author` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `Book`
--

INSERT INTO `Book` (`id`, `name`, `genre`, `author`) VALUES
(1, 'Godfather', 'Detective', 'Mario Puso'),
(2, 'Omerta', 'Detective', 'Mario Puso'),
(3, 'Evgeni Onegin', 'Dramatic', 'Pushkin'),
(4, 'Abay zholy', 'Documentary', 'Mukhtar Auesov'),
(5, 'Death Note', 'Detective', 'Oooba and Takashi');

-- --------------------------------------------------------

--
-- Структура таблицы `Genre`
--

CREATE TABLE `Genre` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `Genre`
--

INSERT INTO `Genre` (`id`, `name`) VALUES
(1, 'Stars'),
(2, 'IT'),
(4, 'High-tech'),
(5, 'Geography'),
(6, 'Math'),
(7, 'Astronomy'),
(8, 'Medicine'),
(9, 'Religion');

-- --------------------------------------------------------

--
-- Структура таблицы `JobCenter`
--

CREATE TABLE `JobCenter` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `location` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `JobCenter`
--

INSERT INTO `JobCenter` (`id`, `name`, `location`) VALUES
(1, 'HH', 'http://hh.kz'),
(2, 'Linkedln', 'http://linkedin.com');

-- --------------------------------------------------------

--
-- Структура таблицы `JobCenterVacancyJoint`
--

CREATE TABLE `JobCenterVacancyJoint` (
  `id` int(11) NOT NULL,
  `jobcenter_id` int(11) DEFAULT NULL,
  `vacancy_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `JobCenterVacancyJoint`
--

INSERT INTO `JobCenterVacancyJoint` (`id`, `jobcenter_id`, `vacancy_id`) VALUES
(2, 1, 2);

-- --------------------------------------------------------

--
-- Структура таблицы `Library`
--

CREATE TABLE `Library` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `Library`
--

INSERT INTO `Library` (`id`, `name`, `address`) VALUES
(1, 'Karev\'s library', 'Dostyk st'),
(4, 'Vonushin\'s library', 'Dostyk st');

-- --------------------------------------------------------

--
-- Структура таблицы `LibraryBookJoint`
--

CREATE TABLE `LibraryBookJoint` (
  `id` int(11) NOT NULL,
  `library_id` int(11) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `LibraryBookJoint`
--

INSERT INTO `LibraryBookJoint` (`id`, `library_id`, `book_id`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 5),
(4, 1, 4);

-- --------------------------------------------------------

--
-- Структура таблицы `LibraryUserJoint`
--

CREATE TABLE `LibraryUserJoint` (
  `id` int(11) NOT NULL,
  `library_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `LibraryUserJoint`
--

INSERT INTO `LibraryUserJoint` (`id`, `library_id`, `user_id`) VALUES
(1, 1, 1),
(2, 1, 4);

-- --------------------------------------------------------

--
-- Структура таблицы `News`
--

CREATE TABLE `News` (
  `id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `message` text NOT NULL,
  `genre_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `News`
--

INSERT INTO `News` (`id`, `title`, `message`, `genre_id`) VALUES
(2, 'START', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 1),
(3, 'Islamic', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 9),
(4, 'Facebook renames as a Meta', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 2);

-- --------------------------------------------------------

--
-- Структура таблицы `Role`
--

CREATE TABLE `Role` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `Role`
--

INSERT INTO `Role` (`id`, `name`) VALUES
(2, 'ROLE_ADMIN'),
(1, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Структура таблицы `User`
--

CREATE TABLE `User` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `birthday` date NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `User`
--

INSERT INTO `User` (`id`, `name`, `email`, `birthday`, `password`) VALUES
(1, 'Daurbek', 'Daurbek@gmail.com', '2001-09-15', 'qweqwe'),
(4, 'Daur', 'Daur@gmail.com', '2005-09-15', 'qweqwe'),
(6, 'Azamat', 'Aza@gmail.com', '2002-09-15', 'qweqwe'),
(7, 'Maral', 'Maral@gmail.com', '1999-10-20', 'qweqwe'),
(8, 'Aliya', 'Aliya@gmail.com', '2010-05-01', 'qweqwe'),
(9, 'Dias', 'Dias@gmail.com', '2010-05-01', 'qweqwe');

-- --------------------------------------------------------

--
-- Структура таблицы `UserRoleJoint`
--

CREATE TABLE `UserRoleJoint` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `UserRoleJoint`
--

INSERT INTO `UserRoleJoint` (`id`, `user_id`, `role_id`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 4, 1),
(4, 6, 1),
(5, 7, 1),
(6, 8, 1),
(7, 9, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `Vacancy`
--

CREATE TABLE `Vacancy` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `salary` int(11) NOT NULL,
  `points` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `Vacancy`
--

INSERT INTO `Vacancy` (`id`, `name`, `salary`, `points`) VALUES
(2, 'Python Developer Senior', 1000000, 9.22),
(3, 'DEVops', 5000000, 9.39),
(4, 'IT SPECIALIST', 300000, 9.11);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `Book`
--
ALTER TABLE `Book`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `Genre`
--
ALTER TABLE `Genre`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `JobCenter`
--
ALTER TABLE `JobCenter`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `JobCenterVacancyJoint`
--
ALTER TABLE `JobCenterVacancyJoint`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `Library`
--
ALTER TABLE `Library`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `LibraryBookJoint`
--
ALTER TABLE `LibraryBookJoint`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `LibraryUserJoint`
--
ALTER TABLE `LibraryUserJoint`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `News`
--
ALTER TABLE `News`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `Role`
--
ALTER TABLE `Role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Индексы таблицы `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Индексы таблицы `UserRoleJoint`
--
ALTER TABLE `UserRoleJoint`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `Vacancy`
--
ALTER TABLE `Vacancy`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `Book`
--
ALTER TABLE `Book`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `Genre`
--
ALTER TABLE `Genre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT для таблицы `JobCenter`
--
ALTER TABLE `JobCenter`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `JobCenterVacancyJoint`
--
ALTER TABLE `JobCenterVacancyJoint`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `Library`
--
ALTER TABLE `Library`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `LibraryBookJoint`
--
ALTER TABLE `LibraryBookJoint`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `LibraryUserJoint`
--
ALTER TABLE `LibraryUserJoint`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `News`
--
ALTER TABLE `News`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `Role`
--
ALTER TABLE `Role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `User`
--
ALTER TABLE `User`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT для таблицы `UserRoleJoint`
--
ALTER TABLE `UserRoleJoint`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT для таблицы `Vacancy`
--
ALTER TABLE `Vacancy`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
