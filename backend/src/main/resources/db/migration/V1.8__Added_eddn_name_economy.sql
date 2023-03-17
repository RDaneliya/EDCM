ALTER TABLE economy
    ADD eddn_name VARCHAR(255) UNIQUE;

ALTER TABLE economy
    ALTER COLUMN eddn_name SET NOT NULL;

ALTER TABLE economy
    ADD CONSTRAINT uc_economy_name UNIQUE (name);

INSERT INTO economy
VALUES (DEFAULT,'$economy_Agri','Agriculture'),
(DEFAULT,'$economy_Colony','Colony'),
(DEFAULT,'$economy_Extraction','Extraction'),
(DEFAULT,'$economy_HighTech','High Tech'),
(DEFAULT,'$economy_Industrial','Industrial'),
(DEFAULT,'$economy_Military','Military'),
(DEFAULT,'$economy_None','None'),
(DEFAULT,'$economy_Refinery','Refinery'),
(DEFAULT,'$economy_Service','Service'),
(DEFAULT,'$economy_Terraforming','Terraforming'),
(DEFAULT,'$economy_Tourism','Tourism'),
(DEFAULT,'$economy_Prison','Prison'),
(DEFAULT,'$economy_Damaged','Damaged'),
(DEFAULT,'$economy_Rescue','Rescue'),
(DEFAULT,'$economy_Repair','Repair'),
(DEFAULT,'$economy_Carrier','Private Enterprise'),
(DEFAULT,'$economy_Engineer','Engineering')
