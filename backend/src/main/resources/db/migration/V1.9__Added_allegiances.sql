CREATE SEQUENCE IF NOT EXISTS allegiance_seq START WITH 1 INCREMENT BY 1;

ALTER TABLE allegiance
    ADD eddn_id VARCHAR(255);

ALTER TABLE allegiance
    ADD eddb_id BIGINT;

ALTER TABLE allegiance
    ALTER COLUMN eddn_id SET NOT NULL;

ALTER TABLE allegiance
    ADD CONSTRAINT uc_allegiance_eddb UNIQUE (eddb_id);

ALTER TABLE allegiance
    ADD CONSTRAINT uc_allegiance_eddn UNIQUE (eddn_id);

INSERT INTO allegiance
VALUES (DEFAULT, 'Alliance', '$faction_Alliance', 1),
    (DEFAULT, 'Empire', '$faction_Empire', 2),
    (DEFAULT, 'Federation', '$faction_Federation', 3),
    (DEFAULT, 'Independent', '$faction_Independent', 4),
    (DEFAULT, 'None', '$faction_none', 5),
    (DEFAULT, 'Pirate', '$faction_Pirate', 6),
    (DEFAULT, 'Pilots Federation', '$faction_PilotsFederation', 7),
    (DEFAULT, 'Guardian', '$faction_Guardian', 8),
    (DEFAULT, 'Thargoid', '$faction_Thargoid', 9)
