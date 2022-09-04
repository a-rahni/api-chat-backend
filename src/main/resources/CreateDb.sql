DROP DATABASE IF EXISTS `chat_api`;
DROP USER IF EXISTS 'chat'@'localhost';
CREATE DATABASE `chat_api` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE USER 'chat'@'localhost' IDENTIFIED BY 'chat';
GRANT ALL PRIVILEGES ON `chat_api` . * TO 'chat'@'localhost' IDENTIFIED BY 'chat'