-------------------- role --------------------
INSERT INTO role (id, name, description) 
VALUES (1, 'Administrator', '');

INSERT INTO role (id, name, description) 
VALUES (2, 'Audit', '');

INSERT INTO role (id, name, description) 
VALUES (3, 'Manager 1', '');

-------------------- object_ui --------------------
INSERT INTO object_ui (id, name, description, html_id)
VALUES (1, 'Add new item', '', 'ITM-Add');

INSERT INTO object_ui (id, name, description, html_id)
VALUES (2, 'Edit item', '', 'ITM-Edit');

INSERT INTO object_ui (id, name, description, html_id)
VALUES (3, 'View item', '', 'ITM-View');

-------------------- role_object_ui --------------------
INSERT INTO role_object_ui (role_id, object_ui_id)
VALUES (3, 1);