INSERT INTO Z_MOCK_EMPLOYEE (FIRST_NAME, LAST_NAME, SALARY, WORKING_DATE) VALUES ('Emp1', 'LastName1', 12000, CURRENT_TIMESTAMP);
INSERT INTO Z_MOCK_EMPLOYEE (FIRST_NAME, LAST_NAME, SALARY, WORKING_DATE) VALUES ('Emp2', 'LastName2', 24000, CURRENT_TIMESTAMP);
INSERT INTO Z_MOCK_EMPLOYEE (FIRST_NAME, LAST_NAME, SALARY, WORKING_DATE) VALUES ('Emp3', 'LastName3', 36000, CURRENT_TIMESTAMP);

INSERT INTO SYS_MESSAGE(MESSAGE_ID, MESSAGE_CODE, MESSAGE_EN, MESSAGE_TH, MESSAGE_TYPE, CREATED_BY) 
VALUES (1, 'INF-00001', 'Are you sure to save this changes ?', 'คุณต้องการบันทึกข้อมูลหรือไม่ ?', 'INFO', 'Y', '1', 'INITIAL');