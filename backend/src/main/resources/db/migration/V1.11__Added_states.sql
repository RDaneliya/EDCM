
ALTER TABLE state
    ADD eddn_id VARCHAR(255);

ALTER TABLE state
    ADD eddb_id BIGINT;

ALTER TABLE state
    ALTER COLUMN eddn_id SET NOT NULL;

ALTER TABLE state
    ADD CONSTRAINT uc_state_eddb UNIQUE (eddb_id);

ALTER TABLE state
    ADD CONSTRAINT uc_state_eddn UNIQUE (eddn_id);

INSERT INTO state
VALUES (DEFAULT,'None','None', 80),
(DEFAULT,'Boom','Boom', 16),
(DEFAULT,'Bust','Bust', 32),
(DEFAULT,'Civil Unrest','CivilUnrest', 48),
(DEFAULT,'Civil War','CivilWar', 64),
(DEFAULT,'Election','Election', 65),
(DEFAULT,'Expansion','Expansion', 67),
(DEFAULT,'Famine','Famine', 37),
(DEFAULT,'Investment','Investment', 101),
(DEFAULT,'Lockdown','Lockdown', 69),
(DEFAULT,'Outbreak','Outbreak', 72),
(DEFAULT,'Retreat','Retreat', 96),
(DEFAULT,'War','War', 73),
(DEFAULT,'Civil Liberty','CivilLiberty', 66),
(DEFAULT,'Pirate Attack','PirateAttack', 81),
(DEFAULT,'Blight','Blight', 102),
(DEFAULT,'Drought','Drought', 103),
(DEFAULT,'Infrastructure Failure','InfrastructureFailure', 104),
(DEFAULT,'Natural Disaster', 'NaturalDisaster', 105),
(DEFAULT,'Public Holiday', 'PublicHoliday', 106),
(DEFAULT,'Terrorist Attack', 'TerroristAttack', 107)
