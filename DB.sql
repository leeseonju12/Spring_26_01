DROP DATABASE IF EXISTS `Spring_Demo_26_01`;
CREATE DATABASE `Spring_Demo_26_01`;
USE `Spring_Demo_26_01`;

# 게시글 테이블
CREATE TABLE article (
                         id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
                         regDate DATETIME NOT NULL,
                         title CHAR(100) NOT NULL,
                         `body` TEXT NOT NULL
);

# 게시글 TD
INSERT INTO article
SET regDate = NOW(),

title = '제목1',
`body` = '내용1';

INSERT INTO article
SET regDate = NOW(),

title = '제목2',
`body` = '내용2';

INSERT INTO article
SET regDate = NOW(),

title = '제목3',
`body` = '내용3';

DESC article;

SELECT *
FROM article ORDER BY id DESC;

##===============================###################### 테스트

INSERT INTO article SET 
    regDate = NOW(), 
    memberId = 2, 
    title = '제목11', 
    `body` = '내용11';

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