ALTER TABLE system
    ADD CONSTRAINT uc_system_name UNIQUE (name);

ALTER TABLE station
    DROP CONSTRAINT idx_stationentity_name_unq;
