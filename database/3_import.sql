
INSERT INTO game.role (id, role_name) VALUES (default, 'admin');
INSERT INTO game.role (id, role_name) VALUES (default, 'user');

INSERT INTO game."user" (id, role_id, email, username, password, status) VALUES (default, 1, 'admin', 'admin', '123', 'A');
INSERT INTO game."user" (id, role_id, email, username, password, status) VALUES (default, 2, 'user1', 'user1', '123', 'A');

