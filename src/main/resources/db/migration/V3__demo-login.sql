-- Create default permission if not exist
USE njangi;
INSERT INTO permission(id, name, description) VALUES(NULL, 'Create user', NULL) ON DUPLICATE KEY UPDATE name='Create user';
SET @PermissionId := LAST_INSERT_ID();

-- Create default role if not exist
INSERT INTO role(id, name, description) VALUES(NULL, 'ROLE_ADMINISTRATION', NULL) ON DUPLICATE KEY UPDATE name='ROLE_ADMINISTRATION';
SET @RoleId := LAST_INSERT_ID();

-- Create default user if not exist
INSERT INTO usersys(id, first_name, last_name, username,email, password,phone_number,is_active)
	VALUES(NULL, 'Administrator', 'Administrator', 'Admin', 'ruphinekengne@gmail.com' , '$2a$10$OSVh6T1Ah20r7NT9bAU5Xug8gEWYMegyHBu2XBARfE1xKPWRc0qNW', '(+237) 677996655', 1)
	ON DUPLICATE KEY UPDATE username='Admin', password='$2a$10$OSVh6T1Ah20r7NT9bAU5Xug8gEWYMegyHBu2XBARfE1xKPWRc0qNW';
SET @userId := LAST_INSERT_ID();

INSERT IGNORE INTO `njangi`.`usersys_role` (`usersys_id`, `role_id`) VALUES (@UsersysId, @RoleId);

INSERT IGNORE INTO `njangi`.`role_permission` (`role_id`, `permission_id`) VALUES (@RoleId, @PermissionId);

--Create default member if not exit
INSERT INTO member(id,first_name,last_name,username,email,password,is_active,identifier,phone_number,occupation,location,date_of_birth,account_balance,picture,account_number)
  VALUES (NULL,'ruphine','kengne','admin','ruphine@gmail.com','@@admin123',1,'123','657375417','engineer','buea','2009-10-01',10000.0,'hskjdkjdidhkdj','aaa')
  ON DUPLICATE KEY UPDATE username='admin',password='@@admin123';

-- Create a default Njangi account if not exit
INSERT INTO njangi_account(id,amount,account_number)
 VALUES(NULL, 50000.0, 'bbbaaa');

