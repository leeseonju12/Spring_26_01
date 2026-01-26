DROP DATABASE IF EXISTS `Spring_Demo_26_01`;
CREATE DATABASE `Spring_Demo_26_01`;
USE `Spring_Demo_26_01`;

# 게시글 테이블
CREATE TABLE article (
                         id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
                         regDate DATETIME NOT NULL,
                         updateDate DATETIME NOT NULL,
                         title CHAR(100) NOT NULL,
                         `body` TEXT NOT NULL
);

# 회원 테이블
CREATE TABLE `member` (
		 id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
		 regDate DATETIME NOT NULL,
		 updateDate DATETIME NOT NULL,
		 loginId CHAR(30) NOT NULL,
		 loginPw CHAR(100) NOT NULL,
		 `authLevel` SMALLINT(2) UNSIGNED DEFAULT 3 COMMENT '권한 레벨 (3=일반,7=관리자)',
		 `name` CHAR(20) NOT NULL,
		 nickname CHAR(20) NOT NULL,
		 cellphoneNum CHAR(20) NOT NULL,
		 email CHAR(20) NOT NULL,
		 delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '탈퇴 여부 (0=탈퇴 전, 1=탈퇴 후)',
		 delDate DATETIME COMMENT '탈퇴 날짜'
);




# 게시글 TD
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목1',
`body` = '내용1';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목2',
`body` = '내용2';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목3',
`body` = '내용3';

# 회원 TD
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin',
loginPw = 'admin',
`authLevel` = 7,
`name` = '관리자',
nickname = '관리자_별명',
cellphoneNum = '01012341234',
email = 'abc@gmail.com';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test1',
loginPw = 'test1',
`name` = '회원1',
nickname = '회원1_별명',
cellphoneNum = '01043214321',
email = 'abcd@gmail.com';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test2',
loginPw = 'test2',
`name` = '회원2',
nickname = '회원2_별명',
cellphoneNum = '01056785678',
email = 'abced@gmail.com';

ALTER TABLE article ADD COLUMN memberId INT(10) UNSIGNED NOT NULL AFTER regDate;

UPDATE article 
SET memberId = 2
WHERE id IN (1,2);

UPDATE article 
SET memberId = 3
WHERE id = 3;

DESC article;

SELECT *
FROM article ORDER BY id DESC;


SELECT *
FROM `member`;


##===============================###################### 테스트

SELECT LAST_INSERT_ID();

INSERT INTO article SET regDate = NOW(), title = '제목1', `body` = '내용1'; , DATA=[, ]

SELECT *
FROM `member`
WHERE loginId = 'test3';

SELECT *
FROM article
ORDER BY id DESC;

SELECT *
FROM `member`;

SELECT A.*, M.name AS extra__writer
FROM article AS A
         INNER JOIN `member` AS M
                    ON A.memberId = M.id;

SELECT CEILING(RAND() * 2);

# article 대량생성
INSERT INTO article
SET regDate = NOW(),
memberId = CEILING(RAND() * 2),
title = CONCAT('제목', RAND()),
`body` = CONCAT('내용', RAND());

# member 대량생성
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = CONCAT('loginId', SUBSTRING(RAND() * 1000 FROM 1 FOR 2)),
loginPw = CONCAT('loginPw', SUBSTRING(RAND() * 1000 FROM 1 FOR 2)),
`name` = CONCAT('name', SUBSTRING(RAND() * 1000 FROM 1 FOR 2));

SELECT * FROM `member` WHERE loginId = 'test1';

SELECT 1 + 1;
SELECT 1 >= 1;

SELECT COUNT(*) > 0
FROM `member`
WHERE loginId = 'test1';

SELECT COUNT(*) > 0
FROM `member`
WHERE loginId = 'test3';

SELECT NOW();

SELECT '제목1';

SELECT CONCAT('제목','2');

SELECT SUBSTRING(RAND() * 1000 FROM 1 FOR 2);

UPDATE article
SET updateDate = NOW(),
    title = '',
    `body` = 'test1'
WHERE id = 1;

SELECT COUNT(*)
FROM article
WHERE id = 5;


UPDATE article
SET updateDate = NOW(),
    `body` = 'test3'
WHERE id = 3;


SELECT *
FROM article;