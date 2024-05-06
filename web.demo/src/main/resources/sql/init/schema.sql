DROP TABLE IF EXISTS `web_acess`;
CREATE TABLE `web_acess` (
   `user_id` int NOT NULL AUTO_INCREMENT,
   `user_name` varchar(45) NOT NULL,
   `last_name` varchar(45) DEFAULT NULL,
   `password` varchar(45) DEFAULT NULL,
   `role` varchar(45) DEFAULT NULL,
   `locked` tinyint DEFAULT NULL,
   `email` varchar(45) DEFAULT NULL,
   `uuid` varchar(45) DEFAULT NULL,
   PRIMARY KEY (`user_id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci