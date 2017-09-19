CREATE TABLE role (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(30),
	description  VARCHAR(50)
);

CREATE TABLE object_ui (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(30),
	description VARCHAR(50),
	html_id VARCHAR(30)
);

CREATE TABLE role_object_ui (
	role_id VARCHAR(30),
	object_ui_id  VARCHAR(50),
	FOREIGN KEY (role_id) REFERENCES role(id),
	FOREIGN KEY (object_ui_id) REFERENCES object_ui(id),
	PRIMARY KEY (role_id, object_ui_id)
);

