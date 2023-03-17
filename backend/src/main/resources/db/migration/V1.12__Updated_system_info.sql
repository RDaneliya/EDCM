
CREATE SEQUENCE IF NOT EXISTS commodities_at_station_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS state_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS station_type_seq START WITH 1 INCREMENT BY 50;

ALTER TABLE system
    ADD allegiance_id BIGINT;

ALTER TABLE system
    ADD economy_id BIGINT;

ALTER TABLE station
    ADD market_id BIGINT;

ALTER TABLE station
    ALTER COLUMN market_id SET NOT NULL;

ALTER TABLE station
    ADD CONSTRAINT uc_station_market UNIQUE (market_id);

ALTER TABLE station_type
    ADD CONSTRAINT uc_station_type_type UNIQUE (type);

ALTER TABLE system
    ADD CONSTRAINT FK_SYSTEM_ON_ALLEGIANCE FOREIGN KEY (allegiance_id)
        REFERENCES allegiance(id);

ALTER TABLE system
    ADD CONSTRAINT FK_SYSTEM_ON_ECONOMY FOREIGN KEY (economy_id)
        REFERENCES economy(id);

ALTER TABLE station_type
    ALTER COLUMN type SET NOT NULL;