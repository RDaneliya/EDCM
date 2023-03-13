
CREATE TABLE station_economies (
    economies_id BIGINT NOT NULL,
    station_id   BIGINT NOT NULL,
    CONSTRAINT pk_station_economies PRIMARY KEY (economies_id, station_id)
);

ALTER TABLE station
    ADD faction_state_id BIGINT;

ALTER TABLE allegiance
    ADD CONSTRAINT uc_allegiance_name UNIQUE (name);

ALTER TABLE government
    ADD CONSTRAINT uc_government_name UNIQUE (name);

ALTER TABLE station
    ADD CONSTRAINT FK_STATION_ON_FACTION_STATE FOREIGN KEY (faction_state_id)
        REFERENCES faction_state(id);

ALTER TABLE station_economies
    ADD CONSTRAINT fk_staeco_on_economy FOREIGN KEY (economies_id)
        REFERENCES economy(id);

ALTER TABLE station_economies
    ADD CONSTRAINT fk_staeco_on_station FOREIGN KEY (station_id)
        REFERENCES station(id);

ALTER TABLE stations_economies
    DROP CONSTRAINT fkgl8wro82cg89pg8m3m2hpq4x6;

ALTER TABLE stations_economies
    DROP CONSTRAINT fkmnsf16hrykyh9w1gmfbpul799;

DROP TABLE bot_task CASCADE;

DROP TABLE stations_economies CASCADE;

ALTER TABLE allegiance
    ALTER COLUMN name SET NOT NULL;

ALTER TABLE government
    ALTER COLUMN name SET NOT NULL;
