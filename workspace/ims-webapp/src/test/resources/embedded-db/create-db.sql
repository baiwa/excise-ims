CREATE TABLE Z_MOCK_EMPLOYEE (
  EMP_ID BIGINT PRIMARY KEY AUTO_INCREMENT,
  FIRST_NAME VARCHAR(20) NOT NULL,
  LAST_NAME VARCHAR(20) NOT NULL,
  SALARY DECIMAL,
  WORKING_DATE DATE,
  IS_DELETED VARCHAR(1) DEFAULT 'N',
  CREATED_BY VARCHAR(20) NOT NULL DEFAULT 'SYSTEM',
  CREATED_DATE DATE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  UPDATED_BY VARCHAR(20),
  UPDATED_DATE DATE
);

CREATE TABLE SYS_MESSAGE (
  MESSAGE_ID BIGINT PRIMARY KEY AUTO_INCREMENT,
  MESSAGE_CODE VARCHAR(50) NOT NULL,
  MESSAGE_EN VARCHAR(200) NOT NULL,
  MESSAGE_TH VARCHAR(200) DEFAULT NULL,
  MESSAGE_TYPE CHAR(1) NOT NULL,
  IS_DELETED CHAR(1) NOT NULL DEFAULT 'N',
  VERSION INT(11) NOT NULL DEFAULT '1',
  CREATED_BY VARCHAR(20) NOT NULL DEFAULT 'SYSTEM',
  CREATED_DATE DATE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  UPDATED_BY VARCHAR(20),
  UPDATED_DATE DATE,
  PRIMARY KEY (MESSAGE_ID)
);