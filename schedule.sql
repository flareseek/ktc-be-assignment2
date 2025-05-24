CREATE DATABASE schedule_db
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;
USE schedule_db;
CREATE TABLE schedules (
       id INT AUTO_INCREMENT PRIMARY KEY,
       todo VARCHAR(255) NOT NULL,
       author VARCHAR(100) NOT NULL,
       password VARCHAR(100) NOT NULL,
       created_at DATETIME NOT NULL,
       updated_at DATETIME NOT NULL
);