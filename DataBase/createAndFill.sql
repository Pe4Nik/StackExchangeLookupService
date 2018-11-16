DROP DATABASE IF EXISTS db;
CREATE DATABASE db;
USE db;
CREATE TABLE `questions` (
  `id`         int(8)       NOT NULL,
  `title`      varchar(255) NOT NULL,
  `link`       varchar(255) NOT NULL,
  `date`       datetime     NOT NULL,
  `creator`    varchar(50)  NOT NULL,
  `answers`    tinyint(4)   NOT NULL,
  `isanswered` bit(1)       NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


INSERT INTO `questions` (`id`, `title`, `link`, `date`, `creator`, `answers`, `isanswered`)
VALUES (300316,
        'Java concurrency scenario -- do I need synchronization or not?',
        'https://stackoverflow.com/questions/300316/java-concurrency-scenario-do-i-need-synchronization-or-not',
        '2008-11-19 02:07:54',
        'storkman',
        10,
        1);
INSERT INTO `questions` (`id`, `title`, `link`, `date`, `creator`, `answers`, `isanswered`)
VALUES (309364,
        'SQL Server 2000, &quot;FOR XML AUTO&quot; Query via http, Need &quot;Content Length = 12345&quot; in return XML Header',
        'https://stackoverflow.com/questions/309364/sql-server-2000-for-xml-auto-query-via-http-need-content-length-12345-in',
        '2008-11-21 20:29:44',
        'Chuck',
        2,
        1);
INSERT INTO `questions` (`id`, `title`, `link`, `date`, `creator`, `answers`, `isanswered`)
VALUES (546921,
        'What&#39;s the result of the SQL statement &quot;SELECT DATEADD(s,1234567890, &#39;19700101&#39;)&quot;?',
        'https://stackoverflow.com/questions/546921/whats-the-result-of-the-sql-statement-select-dateadds-1234567890-19700101',
        '2009-02-13 21:41:56',
        'Drew Noakes',
        3,
        1);

