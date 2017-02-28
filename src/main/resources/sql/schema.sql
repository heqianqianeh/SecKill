/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50625
Source Host           : 127.0.0.1:3306
Source Database       : seckill

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2017-02-28 21:04:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for seckill
-- ----------------------------
DROP TABLE IF EXISTS `seckill`;
CREATE TABLE `seckill` (
  `seckill_id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `number` int(11) NOT NULL,
  `start_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`seckill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seckill
-- ----------------------------
INSERT INTO `seckill` VALUES ('1', '100元秒杀iphone', '100', '2017-02-02 23:08:16', '2017-04-06 14:48:19', '2017-02-28 21:04:33');
INSERT INTO `seckill` VALUES ('2', '200元秒杀iPad', '200', '2017-02-27 19:29:20', '2017-02-27 19:29:20', '2017-02-28 21:04:30');
INSERT INTO `seckill` VALUES ('3', '300元秒杀itouch', '300', '2017-02-01 19:17:03', '2017-02-01 19:17:08', '2017-02-25 19:17:10');

-- ----------------------------
-- Table structure for success_killed
-- ----------------------------
DROP TABLE IF EXISTS `success_killed`;
CREATE TABLE `success_killed` (
  `seckill_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_phone` int(11) NOT NULL,
  `state` int(11) NOT NULL DEFAULT '-1',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`seckill_id`,`user_phone`),
  CONSTRAINT `sid` FOREIGN KEY (`seckill_id`) REFERENCES `seckill` (`seckill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of success_killed
-- ----------------------------

-- ----------------------------
-- Procedure structure for execute_seckill
-- ----------------------------
DROP PROCEDURE IF EXISTS `execute_seckill`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `execute_seckill`(
	IN v_seckill_id BIGINT,
	IN v_phone BIGINT,
	IN v_kill_time TIMESTAMP,
	OUT r_result INT
)
BEGIN

DECLARE insert_count INT DEFAULT 0 ; START TRANSACTION ; INSERT IGNORE INTO success_killed (
	seckill_id,
	user_phone,
	create_time
)
VALUES
	(
		v_seckill_id,
		v_phone,
		v_kill_time
	) ; SELECT
		ROW_COUNT() INTO insert_count ;
	IF (insert_count = 0) THEN
		ROLLBACK ;
	SET r_result = - 1 ;
	ELSEIF (insert_count < 0) THEN
		ROLLBACK ;
	SET r_result =- 2 ;
	ELSE
		UPDATE seckill
	SET number = number - 1
	WHERE
		seckill_id = v_seckill_id
	AND end_time > v_kill_time
	AND start_time < v_kill_time
	AND number > 0 ; SELECT
		ROW_COUNT() INTO insert_count ;
	IF (insert_count = 0) THEN
		ROLLBACK ;
	SET r_result = 0 ;
	ELSEIF (insert_count < 0) THEN
		ROLLBACK ;
	SET r_result = - 2 ;
	ELSE
		COMMIT ;
	SET r_result = 1 ;
	END
	IF ;
	END
	IF ;
	END
;;
DELIMITER ;
