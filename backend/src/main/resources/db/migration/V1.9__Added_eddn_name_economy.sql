ALTER TABLE economy
    ADD eddn_name VARCHAR(255) UNIQUE;

UPDATE economy
SET eddn_name = '$economy_' || economy.name;

UPDATE economy
SET name = 'Private enterprise'
WHERE (eddn_name = '$economy_Carrier');

ALTER TABLE economy
    ALTER COLUMN eddn_name SET NOT NULL;

ALTER TABLE economy
    ADD CONSTRAINT uc_economy_name UNIQUE (name);
