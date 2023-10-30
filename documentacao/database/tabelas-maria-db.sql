-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.6.8-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para javapet
CREATE DATABASE IF NOT EXISTS `javapet` /*!40100 DEFAULT CHARACTER SET utf8mb3 */;
USE `javapet`;

-- Copiando estrutura para tabela javapet.sq_animal
CREATE TABLE IF NOT EXISTS `sq_animal` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) unsigned NOT NULL,
  `cycle_option` tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Copiando dados para a tabela javapet.sq_animal: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `sq_animal` DISABLE KEYS */;
INSERT IGNORE INTO `sq_animal` (`next_not_cached_value`, `minimum_value`, `maximum_value`, `start_value`, `increment`, `cache_size`, `cycle_option`, `cycle_count`) VALUES
	(2, 1, 9223372036854775806, 1, 1, 1000, 0, 0);
/*!40000 ALTER TABLE `sq_animal` ENABLE KEYS */;

-- Copiando estrutura para tabela javapet.sq_pessoa
CREATE TABLE IF NOT EXISTS `sq_pessoa` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) unsigned NOT NULL,
  `cycle_option` tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Copiando dados para a tabela javapet.sq_pessoa: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `sq_pessoa` DISABLE KEYS */;
INSERT IGNORE INTO `sq_pessoa` (`next_not_cached_value`, `minimum_value`, `maximum_value`, `start_value`, `increment`, `cache_size`, `cycle_option`, `cycle_count`) VALUES
	(5, 1, 9223372036854775806, 1, 1, 1000, 0, 0);
/*!40000 ALTER TABLE `sq_pessoa` ENABLE KEYS */;

-- Copiando estrutura para tabela javapet.sq_servico
CREATE TABLE IF NOT EXISTS `sq_servico` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) unsigned NOT NULL,
  `cycle_option` tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Copiando dados para a tabela javapet.sq_servico: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `sq_servico` DISABLE KEYS */;
INSERT IGNORE INTO `sq_servico` (`next_not_cached_value`, `minimum_value`, `maximum_value`, `start_value`, `increment`, `cache_size`, `cycle_option`, `cycle_count`) VALUES
	(2, 1, 9223372036854775806, 1, 1, 1000, 0, 0);
/*!40000 ALTER TABLE `sq_servico` ENABLE KEYS */;

-- Copiando estrutura para tabela javapet.tb_animal
CREATE TABLE IF NOT EXISTS `tb_animal` (
  `ID_ANIMAL` bigint(20) NOT NULL AUTO_INCREMENT,
  `TP_ANIMAL` varchar(31) NOT NULL,
  `NM_ANIMAL` varchar(255) DEFAULT NULL,
  `RACA` varchar(255) DEFAULT NULL,
  `DS_ANIMAL` varchar(255) DEFAULT NULL,
  `DONO` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_ANIMAL`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- Copiando dados para a tabela javapet.tb_animal: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tb_animal` DISABLE KEYS */;
INSERT IGNORE INTO `tb_animal` (`ID_ANIMAL`, `TP_ANIMAL`, `NM_ANIMAL`, `RACA`, `DS_ANIMAL`, `DONO`) VALUES
	(1, 'CACHORRO', 'PIT O BULL DOG', 'PITBULL', 'CAO DA CASA', 1);
/*!40000 ALTER TABLE `tb_animal` ENABLE KEYS */;

-- Copiando estrutura para tabela javapet.tb_pf
CREATE TABLE IF NOT EXISTS `tb_pf` (
  `ID_PESSOA` bigint(20) NOT NULL AUTO_INCREMENT,
  `TP_PESSOA` varchar(31) NOT NULL,
  `NM_PESSOA` varchar(255) DEFAULT NULL,
  `DT_NASCIMENTO` date DEFAULT NULL,
  `NR_CPF` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_PESSOA`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- Copiando dados para a tabela javapet.tb_pf: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `tb_pf` DISABLE KEYS */;
INSERT IGNORE INTO `tb_pf` (`ID_PESSOA`, `TP_PESSOA`, `NM_PESSOA`, `DT_NASCIMENTO`, `NR_CPF`) VALUES
	(1, 'PF', 'Bolsonaro', '1960-05-15', '132456464654'),
	(2, 'PF', 'Bolsonaro', '1960-05-15', '132456464654'),
	(3, 'PF', 'Bolsonaro', '1960-05-15', '132456464654');
/*!40000 ALTER TABLE `tb_pf` ENABLE KEYS */;

-- Copiando estrutura para tabela javapet.tb_pj
CREATE TABLE IF NOT EXISTS `tb_pj` (
  `ID_PESSOA` bigint(20) NOT NULL AUTO_INCREMENT,
  `TP_PESSOA` varchar(31) NOT NULL,
  `NM_PESSOA` varchar(255) DEFAULT NULL,
  `DT_NASCIMENTO` date DEFAULT NULL,
  `NR_CNPJ` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_PESSOA`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

-- Copiando dados para a tabela javapet.tb_pj: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tb_pj` DISABLE KEYS */;
INSERT IGNORE INTO `tb_pj` (`ID_PESSOA`, `TP_PESSOA`, `NM_PESSOA`, `DT_NASCIMENTO`, `NR_CNPJ`) VALUES
	(4, 'PJ', 'Mulher de Aluguel', '2010-05-15', '564546546');
/*!40000 ALTER TABLE `tb_pj` ENABLE KEYS */;

-- Copiando estrutura para tabela javapet.tb_servico
CREATE TABLE IF NOT EXISTS `tb_servico` (
  `ID_SERVICO` bigint(20) NOT NULL AUTO_INCREMENT,
  `TP_SERVICO` varchar(31) NOT NULL,
  `DT_REALIZACAO` date DEFAULT NULL,
  `DS_SERVICO` varchar(255) DEFAULT NULL,
  `ANIMAL` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_SERVICO`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- Copiando dados para a tabela javapet.tb_servico: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tb_servico` DISABLE KEYS */;
INSERT IGNORE INTO `tb_servico` (`ID_SERVICO`, `TP_SERVICO`, `DT_REALIZACAO`, `DS_SERVICO`, `ANIMAL`) VALUES
	(1, 'TOSA', '2023-10-15', 'TOSA NA REGUA', 1);
/*!40000 ALTER TABLE `tb_servico` ENABLE KEYS */;

-- Copiando estrutura para trigger javapet.TG_SQ_ANIMAL
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `TG_SQ_ANIMAL` BEFORE INSERT ON `tb_animal` FOR EACH ROW BEGIN 
DECLARE id INT (30);
	IF NEW.ID_ANIMAL IS NULL OR NEW.ID_ANIMAL < 1 THEN
		SELECT next_not_cached_value INTO id
		FROM SQ_ANIMAL; SET NEW.ID_ANIMAL = id; 
		UPDATE SQ_ANIMAL SET next_not_cached_value = next_not_cached_value +1;
	END IF; 
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Copiando estrutura para trigger javapet.TG_SQ_PF
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `TG_SQ_PF` BEFORE INSERT ON `tb_pf` FOR EACH ROW BEGIN 
DECLARE id INT (30);
	IF NEW.ID_PESSOA IS NULL OR NEW.ID_PESSOA < 1 THEN
		SELECT next_not_cached_value INTO id
		FROM sq_pessoa; SET NEW.ID_PESSOA = id; 
		UPDATE sq_pessoa SET next_not_cached_value = next_not_cached_value +1;
	END IF; 
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Copiando estrutura para trigger javapet.TG_SQ_PJ
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `TG_SQ_PJ` BEFORE INSERT ON `tb_pj` FOR EACH ROW BEGIN 
DECLARE id INT (30);
	IF NEW.ID_PESSOA IS NULL OR NEW.ID_PESSOA < 1 THEN
		SELECT next_not_cached_value INTO id
		FROM sq_pessoa; SET NEW.ID_PESSOA = id; 
		UPDATE sq_pessoa SET next_not_cached_value = next_not_cached_value +1;
	END IF; 
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Copiando estrutura para trigger javapet.TG_SQ_SERVICO
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `TG_SQ_SERVICO` BEFORE INSERT ON `tb_servico` FOR EACH ROW BEGIN 
DECLARE id INT (30);
	IF NEW.ID_SERVICO IS NULL OR NEW.ID_SERVICO < 1 THEN
		SELECT next_not_cached_value INTO id
		FROM SQ_SERVICO; SET NEW.ID_SERVICO = id; 
		UPDATE SQ_SERVICO SET next_not_cached_value = next_not_cached_value +1;
	END IF; 
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
