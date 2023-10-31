CREATE TABLE model_user
(
 "id"       int NOT NULL,
 active   bool NOT NULL,
 password varchar(50) NOT NULL,
 username varchar(50) NOT NULL,
 CONSTRAINT PK_1 PRIMARY KEY ( "id" )
);

CREATE TABLE user_role
(
 "id"      int NOT NULL,
 name    varchar(50) NOT NULL,
 user_id int NOT NULL,
 CONSTRAINT PK_1 PRIMARY KEY ( "id" ),
 CONSTRAINT FK_4 FOREIGN KEY ( user_id ) REFERENCES model_user ( "id" )
);

CREATE TABLE passport
(
 "id"        int NOT NULL,
 last_name varchar(50) NOT NULL,
 name      varchar(50) NOT NULL,
 "number"    int NOT NULL,
 series    int NOT NULL,
 user_id   int NOT NULL,
 CONSTRAINT PK_1 PRIMARY KEY ( "id" ),
 CONSTRAINT FK_5_1 FOREIGN KEY ( user_id ) REFERENCES model_user ( "id" )
);

CREATE TABLE feedback
(
 "id"            int NOT NULL,
 feedback_text text NOT NULL,
 user_id       int NOT NULL,
 CONSTRAINT PK_1 PRIMARY KEY ( "id" ),
 CONSTRAINT FK_9 FOREIGN KEY ( user_id ) REFERENCES model_user ( "id" )
);

CREATE TABLE house
(
 "id"                  int NOT NULL,
 adress              varchar(50) NOT NULL,
 description         varchar(50) NOT NULL,
 price               int NOT NULL,
 sells               bool NOT NULL,
 user_id             int NOT NULL,
 city_id             int NOT NULL,
 insurance_agency_id int NOT NULL,
 status_house_id     int NOT NULL,
 CONSTRAINT PK_1 PRIMARY KEY ( "id" ),
 CONSTRAINT FK_6 FOREIGN KEY ( user_id ) REFERENCES model_user ( "id" ),
 CONSTRAINT FK_7 FOREIGN KEY ( city_id ) REFERENCES city ( "id" ),
 CONSTRAINT FK_8 FOREIGN KEY ( insurance_agency_id ) REFERENCES insurance_agency ( "id" ),
 CONSTRAINT FK_10 FOREIGN KEY ( status_house_id ) REFERENCES status_house ( "id" )
);

CREATE TABLE city
(
 "id"   int NOT NULL,
 name varhchar(50) NOT NULL,
 CONSTRAINT PK_1 PRIMARY KEY ( "id" )
);

CREATE TABLE insurance_agency
(
 "id"   int NOT NULL,
 name varchar(50) NOT NULL,
 CONSTRAINT PK_1 PRIMARY KEY ( "id" )
);

CREATE TABLE status_house
(
 "id"   int NOT NULL,
 name int NOT NULL,
 CONSTRAINT PK_1 PRIMARY KEY ( "id" )
);
