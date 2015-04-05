CREATE TABLE AD_DETAILS (
    ad_id int NOT NULL,
	target_url varchar(240) NOT NULL, 
	image_location varchar(240) NOT NULL,
	PRIMARY KEY (ad_id)
);

INSERT INTO AD_DETAILS VALUES (1, 'http://myad.adserver.com/test/ad1.png', '/var/tmp/images/1.jpg');
INSERT INTO AD_DETAILS VALUES (2, 'http://myad.adserver.com/test/ad2.png', '/var/tmp/images/2.jpg');
INSERT INTO AD_DETAILS VALUES (3, 'http://myad.adserver.com/test/ad3.png', '/var/tmp/images/3.jpg');
INSERT INTO AD_DETAILS VALUES (4, 'http://myad.adserver.com/test/ad4.png', '/var/tmp/images/4.jpg');
INSERT INTO AD_DETAILS VALUES (5, 'http://myad.adserver.com/test/ad1.png', '/var/tmp/images/1.jpg');

CREATE TABLE AD_SPACE_DETAILS (
    ad_space_id int NOT NULL,
	ad_height INT NOT NULL,
	ad_width INT NOT NULL,
	PRIMARY KEY (ad_space_id)
);

INSERT INTO AD_SPACE_DETAILS VALUES (1, 600, 600);
INSERT INTO AD_SPACE_DETAILS VALUES (2, 800, 600);
INSERT INTO AD_SPACE_DETAILS VALUES (3, 600, 800);

CREATE TABLE AD_CAMPAIGN (
    campaign_id INT NOT NULL,
	camp_active TINYINT DEFAULT 0,
	camp_start_date DATE DEFAULT NULL,
	camp_end_date DATE DEFAULT NULL,
	camp_max_hit_limit BIGINT DEFAULT -1,
	PRIMARY KEY (campaign_id)
);

INSERT INTO AD_CAMPAIGN(campaign_id, camp_active) VALUES (1, 1);
INSERT INTO AD_CAMPAIGN(campaign_id, camp_active, camp_start_date, camp_end_date) VALUES (2, 1, '2015-03-11', '2015-09-10');
INSERT INTO AD_CAMPAIGN(campaign_id, camp_active, camp_max_hit_limit) VALUES (3, 1, 100);
INSERT INTO AD_CAMPAIGN(campaign_id, camp_active, camp_start_date, camp_end_date) VALUES (4, 1, '2014-03-11', '2014-09-10');
INSERT INTO AD_CAMPAIGN(campaign_id, camp_active, camp_start_date, camp_end_date) VALUES (5, 0, '2015-03-11', '2015-09-10');

CREATE TABLE AD_CUSTOMER (
    cust_id INT NOT NULL,
	cust_dns VARCHAR(50) NOT NULL,
	cust_name VARCHAR(100) NOT NULL,
	cust_active TINYINT(1) DEFAULT 0,
	cust_start_date DATE DEFAULT NULL,
	cust_end_date DATE DEFAULT NULL,
	PRIMARY KEY (cust_id)
);

INSERT INTO AD_CUSTOMER(cust_id, cust_dns, cust_name, cust_active) VALUES (1, 'adserver.com', 'customer1', 1);
INSERT INTO AD_CUSTOMER(cust_id, cust_dns, cust_name, cust_active) VALUES (2, 'adserver2.com', 'customer2', 0);

CREATE TABLE AD_CATEGORY (
    category_id INT NOT NULL,
	category_name VARCHAR(50) NOT NULL,
	category_parent_id INT DEFAULT NULL,
	PRIMARY KEY (category_id)
);

INSERT INTO AD_CATEGORY VALUES (1, 'Entertainment', null);
INSERT INTO AD_CATEGORY VALUES (2, 'Movies', 1);

CREATE TABLE AD_CAMPAIGN_HIT (
     campaign_id INT NOT NULL,
	 date_hit DATE NOT NULL,
	 campaign_hit_count INT NOT NULL,
	 FOREIGN KEY(campaign_id) REFERENCES AD_CAMPAIGN(campaign_id)
);

CREATE TABLE AD_MAPPING (
    ad_id int NOT NULL,
	cust_id int NOT NULL,
	campaign_id int NOT NULL,
	ad_space_id int NOT NULL,
	category_id INT NOT NULL,
	FOREIGN KEY(ad_id) REFERENCES AD_DETAILS(ad_id),
	FOREIGN KEY(cust_id) REFERENCES AD_CUSTOMER(cust_id),
	FOREIGN KEY(campaign_id) REFERENCES AD_CAMPAIGN(campaign_id),
	FOREIGN KEY(category_id) REFERENCES AD_CATEGORY(category_id),
	FOREIGN KEY(ad_space_id) REFERENCES AD_SPACE_DETAILS(ad_space_id)
);

INSERT INTO AD_MAPPING VALUES (1, 1, 1, 1, 1);
INSERT INTO AD_MAPPING VALUES (2, 1, 2, 1, 1);
INSERT INTO AD_MAPPING VALUES (3, 1, 3, 1, 2);
INSERT INTO AD_MAPPING VALUES (4, 2, 4, 2, 1);
INSERT INTO AD_MAPPING VALUES (5, 2, 5, 3, 1);