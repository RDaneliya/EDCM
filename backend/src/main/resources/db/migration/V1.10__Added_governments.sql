CREATE SEQUENCE IF NOT EXISTS government_seq START WITH 1 INCREMENT BY 1;

ALTER TABLE government
    ADD eddb_id BIGINT;

ALTER TABLE government
    ADD eddn_name VARCHAR(255);

ALTER TABLE government
    ALTER COLUMN eddn_name SET NOT NULL;

ALTER TABLE government
    ADD CONSTRAINT uc_government_eddb UNIQUE (eddb_id);

ALTER TABLE government
    ADD CONSTRAINT uc_government_eddn_name UNIQUE (eddn_name);

INSERT INTO government
VALUES (DEFAULT, 'Anarchy', 16, '$government_Anarchy'),
    (DEFAULT, 'Communism', 32, '$government_Communism'),
    (DEFAULT, 'Confederacy', 48, '$government_Confederacy'),
    (DEFAULT, 'Cooperative', 64, '$government_Cooperative'),
    (DEFAULT, 'Corporate', 80, '$government_Corporate'),
    (DEFAULT, 'Democracy', 96, '$government_Democracy'),
    (DEFAULT, 'Dictatorship', 112, '$government_Dictatorship'),
    (DEFAULT, 'Feudal', 128, '$government_Feudal'),
    (DEFAULT, 'Imperial', 133, '$government_Imperial'),
    (DEFAULT, 'None', 176, '$government_None'),
    (DEFAULT, 'Patronage', 144, '$government_Patronage'),
    (DEFAULT, 'Prison Colony', 150, '$government_PrisonColony'),
    (DEFAULT, 'Theocracy', 160, '$government_Theocracy'),
    (DEFAULT, 'Engineer', 192, '$government_Engineer'),
    (DEFAULT, 'Private Ownership', 209, '$government_Carrier')
