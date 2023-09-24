-- Table: public.users


 
   INSERT INTO oauth_client_details (
	client_details_$$$_id,
	client_details_$$$_client_id, 
	client_details_$$$_client_secret, 
	client_details_$$$_scope, 
	client_details_$$$_authorized_grant_types,
	client_details_$$$_web_server_redirect_uri, 
	client_details_$$$_authorities, 
	client_details_$$$_access_token_validity,
	client_details_$$$_refresh_token_validity,
	client_details_$$$_resource_ids, 
	client_details_$$$_additional_information, 
	client_details_$$$_autoapprove
	 )VALUES(
	'1','public', 
	'{bcrypt}$2y$12$s/vsquzVWfiEf/CmWPrHn.8U0febzYnF6pmvhPljJU4MVWwXgdiF.', 
	'foo,read,write',
	'password,authorization_code,refresh_token,client_credentials', 
	'http://localhost:8082/login,http://localhost:8083/login,http://localhost:8084/login', 
	'ROLE_CLIENT', 
	3600, 
	3600,
	'morasalat-service-resource,mq-service-resource,fm-service-resource,mail-service-resource,test-resource',null,'false'
	); 

   insert into role(role_$$$_id,role_$$$_name,role_$$$_client_id) values (1,'user','1');
   insert into role(role_$$$_id,role_$$$_name,role_$$$_client_id) values (2,'admin','1');
   insert into role(role_$$$_id,role_$$$_name,role_$$$_client_id) values (3,'Manager','1');
   

INSERT INTO USERS (user_$$$_id,user_$$$_email,user_$$$_user_name,user_$$$_is_credentials_non_expired,user_$$$_is_account_non_expired,user_$$$_is_account_non_blocked,user_$$$_is_enabled,user_$$$_pass ,user_$$$_role_id) VALUES (
   1, 'user@core.com','user',true,true,true,true,'{bcrypt}$2y$12$WBcP53NiC20Gyksn2nN5SOttJWN.kdHQz71Vt7pkSQXVbcqx/trVK',1);
      
INSERT INTO USERS (user_$$$_id,user_$$$_email,user_$$$_user_name,user_$$$_is_credentials_non_expired,user_$$$_is_account_non_expired,user_$$$_is_account_non_blocked,user_$$$_is_enabled,user_$$$_pass ,user_$$$_role_id) VALUES (
   2, 'admin@core.com','admin',true,true,true,true,'{bcrypt}$2y$12$WBcP53NiC20Gyksn2nN5SOttJWN.kdHQz71Vt7pkSQXVbcqx/trVK',2);

INSERT INTO USERS (user_$$$_id,user_$$$_email,user_$$$_user_name,user_$$$_is_credentials_non_expired,user_$$$_is_account_non_expired,user_$$$_is_account_non_blocked,user_$$$_is_enabled,user_$$$_pass ,user_$$$_role_id) VALUES (
   3, 'manager@core.com','manager',true,true,true,true,'{bcrypt}$2y$12$WBcP53NiC20Gyksn2nN5SOttJWN.kdHQz71Vt7pkSQXVbcqx/trVK',3);
   




DROP TABLE IF EXISTS public.users;
 DROP TABLE IF EXISTS public.role;
DROP TABLE IF EXISTS public.oauth_access_token;
DROP TABLE IF EXISTS public.oauth_refresh_token;
DROP TABLE IF EXISTS public.oauth_client_details;


CREATE TABLE IF NOT EXISTS public.oauth_refresh_token
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    authentication text COLLATE pg_catalog."default",
    token text COLLATE pg_catalog."default",
    token_id text COLLATE pg_catalog."default",
    CONSTRAINT oauth_refresh_token_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.oauth_refresh_token
    OWNER to postgres;

	-- Table: public.oauth_access_token


CREATE TABLE IF NOT EXISTS public.oauth_access_token
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    authentication text COLLATE pg_catalog."default",
    authentication_id text COLLATE pg_catalog."default",
    client_id character varying(255) COLLATE pg_catalog."default",
    refresh_token text COLLATE pg_catalog."default",
    token text COLLATE pg_catalog."default",
    token_id text COLLATE pg_catalog."default",
    username character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT oauth_access_token_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.oauth_access_token
    OWNER to postgres;

	-- Table: public.oauth_client_details


CREATE TABLE IF NOT EXISTS public.oauth_client_details
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    access_token_validity integer,
    additional_information character varying(255) COLLATE pg_catalog."default",
    authorities character varying(255) COLLATE pg_catalog."default",
    authorized_grant_types character varying(255) COLLATE pg_catalog."default",
    autoapprove boolean,
    client_id character varying(255) COLLATE pg_catalog."default",
    client_secret text COLLATE pg_catalog."default",
    refresh_token_validity integer,
    resource_ids text COLLATE pg_catalog."default",
    scope character varying(255) COLLATE pg_catalog."default",
    web_server_redirect_uri text COLLATE pg_catalog."default",
    CONSTRAINT oauth_client_details_pkey PRIMARY KEY (id),
    CONSTRAINT uk_3my6lp6ttga6hhwtsutscqset UNIQUE (client_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.oauth_client_details
    OWNER to postgres;
   

CREATE TABLE IF NOT EXISTS public.role
(
    role_id integer NOT NULL,
    role_name character varying(255) COLLATE pg_catalog."default",
    client_id character varying(255) COLLATE pg_catalog."default",
    oauth_client_details character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT role_pkey PRIMARY KEY (role_id),
    CONSTRAINT fkowdidant15eaiuoo1wdlx855o FOREIGN KEY (client_id)
        REFERENCES public.oauth_client_details (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.role
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.users
(
    user_id integer NOT NULL,
    email character varying(255) COLLATE pg_catalog."default",
    is_account_non_expired boolean,
    is_account_non_blocked boolean,
    is_credentials_non_expired boolean,
    is_enabled boolean,
    pass text COLLATE pg_catalog."default",
    user_name character varying(255) COLLATE pg_catalog."default",
    user_type character varying(255) COLLATE pg_catalog."default",
    role_role_id integer,
    role_id integer,
    CONSTRAINT users_pkey PRIMARY KEY (user_id),
    CONSTRAINT fk4qu1gr772nnf6ve5af002rwya FOREIGN KEY (role_id)
        REFERENCES public.role (role_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk7dd36gc14q3fjywsnk9konknt FOREIGN KEY (role_role_id)
        REFERENCES public.role (role_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;



   
   INSERT INTO oauth_client_details (
	client_details_$$$_id,
	client_details_$$$_client_id, 
	client_details_$$$_client_secret, 
	client_details_$$$_scope, 
	client_details_$$$_authorized_grant_types,
	client_details_$$$_web_server_redirect_uri, 
	client_details_$$$_authorities, 
	client_details_$$$_access_token_validity,
	client_details_$$$_refresh_token_validity,
	client_details_$$$_resource_ids, 
	client_details_$$$_additional_information, 
	client_details_$$$_autoapprove
	 )VALUES(
	'1','public', 
	'{bcrypt}$2y$12$s/vsquzVWfiEf/CmWPrHn.8U0febzYnF6pmvhPljJU4MVWwXgdiF.', 
	'foo,read,write',
	'password,authorization_code,refresh_token,client_credentials', 
	'http://localhost:8082/login,http://localhost:8083/login,http://localhost:8084/login', 
	'ROLE_CLIENT', 
	3600, 
	3600,
	'morasalat-service-resource,mq-service-resource,fm-service-resource,mail-service-resource,test-resource',null,'false'
	); 

   insert into role(role_$$$_id,role_$$$_name,role_$$$_client_id) values (1,'user','1');
   insert into role(role_$$$_id,role_$$$_name,role_$$$_client_id) values (2,'admin','1');
   insert into role(role_$$$_id,role_$$$_name,role_$$$_client_id) values (3,'Manager','1');
   

INSERT INTO USERS (user_$$$_id,user_$$$_user_name,user_$$$_is_credentials_non_expired,user_$$$_is_account_non_expired,user_$$$_is_account_non_blocked,user_$$$_is_enabled,pass ,user_$$$_role_id) VALUES (
   1, 'user',true,true,true,true,'{bcrypt}$2y$12$WBcP53NiC20Gyksn2nN5SOttJWN.kdHQz71Vt7pkSQXVbcqx/trVK',1);
      
INSERT INTO USERS (user_$$$_id,user_$$$_user_name,user_$$$_is_credentials_non_expired,user_$$$_is_account_non_expired,user_$$$_is_account_non_blocked,user_$$$_is_enabled,pass ,user_$$$_role_id) VALUES (
   2, 'admin',true,true,true,true,'{bcrypt}$2y$12$WBcP53NiC20Gyksn2nN5SOttJWN.kdHQz71Vt7pkSQXVbcqx/trVK',2);

INSERT INTO USERS (user_$$$_id,user_$$$_user_name,user_$$$_is_credentials_non_expired,user_$$$_is_account_non_expired,user_$$$_is_account_non_blocked,user_$$$_is_enabled,pass ,user_$$$_role_id) VALUES (
   3, 'manager',true,true,true,true,'{bcrypt}$2y$12$WBcP53NiC20Gyksn2nN5SOttJWN.kdHQz71Vt7pkSQXVbcqx/trVK',3);
   


--------------------------------------------------------------------


   insert into permission(permission_id,permission_name) values(1,'read_user');
   insert into permission(permission_id,permission_name) values(2,'create_user');
   insert into permission(permission_id,permission_name) values(3,'update_user');
   insert into permission(permission_id,permission_name) values(4,'delete_user');
   insert into permission(permission_id,permission_name) values(5,'read_users');
   


   
   insert into role_permission (role_id,permission_id) values (3,1);
   insert into role_permission (role_id,permission_id) values (3,2);
   insert into role_permission (role_id,permission_id) values (3,3);
   insert into role_permission (role_id,permission_id) values (3,4);
   insert into role_permission (role_id,permission_id) values (3,5);
   
   
   
INSERT INTO oauth_client_details (
	id,
	client_id, 
	client_secret, 
	scope, 
	authorized_grant_types,
	web_server_redirect_uri, 
	authorities, 
	access_token_validity,
	refresh_token_validity,resource_ids, additional_information, autoapprove
	 )VALUES(
	1,'morasalat_service', 
	'{bcrypt}$2y$12$s/vsquzVWfiEf/CmWPrHn.8U0febzYnF6pmvhPljJU4MVWwXgdiF.', 
	'foo,read,write',
	'password,authorization_code,refresh_token,client_credentials', 
	'http://localhost:8082/login,http://localhost:8083/login,http://localhost:8084/login', 
	'ROLE_CLIENT', 
	36000, 
	36000,
	'morasalat-service-resource,test-resource',null,'false'
	);   
   
INSERT INTO oauth_client_details (
	id,
	client_id, 
	client_secret, 
	scope, 
	authorized_grant_types,
	web_server_redirect_uri, 
	authorities, 
	access_token_validity,
	refresh_token_validity,resource_ids, additional_information, autoapprove
	 )VALUES(
	2,'mq-_ervice', 
	'{bcrypt}$2y$12$s/vsquzVWfiEf/CmWPrHn.8U0febzYnF6pmvhPljJU4MVWwXgdiF.', 
	'foo,read,write',
	'password,authorization_code,refresh_token,client_credentials', 
	'http://localhost:8082/login,http://localhost:8083/login,http://localhost:8084/login', 
	'ROLE_CLIENT', 
	36000, 
	36000,
	'mq-service-resource,test-resource',null,'false'
	);   

	INSERT INTO oauth_client_details (
	id,
	client_id, 
	client_secret, 
	scope, 
	authorized_grant_types,
	web_server_redirect_uri, 
	authorities, 
	access_token_validity,
	refresh_token_validity,resource_ids, additional_information, autoapprove
	 )VALUES(
	3,'fm_service', 
	'{bcrypt}$2y$12$s/vsquzVWfiEf/CmWPrHn.8U0febzYnF6pmvhPljJU4MVWwXgdiF.', 
	'foo,read,write',
	'password,authorization_code,refresh_token,client_credentials', 
	'http://localhost:8082/login,http://localhost:8083/login,http://localhost:8084/login', 
	'ROLE_CLIENT', 
	36000, 
	36000,
	'fm-service-resource,test-resource',null,'false'
	);  


	INSERT INTO oauth_client_details (
	id,
	client_id, 
	client_secret, 
	scope, 
	authorized_grant_types,
	web_server_redirect_uri, 
	authorities, 
	access_token_validity,
	refresh_token_validity,resource_ids, additional_information, autoapprove
	 )VALUES(
	4,'mail_service', 
	'{bcrypt}$2y$12$s/vsquzVWfiEf/CmWPrHn.8U0febzYnF6pmvhPljJU4MVWwXgdiF.', 
	'foo,read,write',
	'password,authorization_code,refresh_token,client_credentials', 
	'http://localhost:8082/login,http://localhost:8083/login,http://localhost:8084/login', 
	'ROLE_CLIENT', 
	36000, 
	36000,
	'mail-service-resource,test-resource',null,'false'
	);  
create table oauth_access_token (
	id VARCHAR(256),
  token_id VARCHAR(256),
  token TEXT,
  authentication_id VARCHAR(256) PRIMARY KEY,
  username VARCHAR(256),
  client_id VARCHAR(256),
  authentication TEXT,
  refresh_token VARCHAR(256)
);

create table oauth_refresh_token (
		id VARCHAR(256),
  token_id VARCHAR(256),
  token TEXT,
  authentication Text
);