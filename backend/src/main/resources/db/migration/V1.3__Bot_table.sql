CREATE TABLE bot_task
(
    id        bigint primary key,
    user_id   bigint                      not null,
    commodity bigint references commodity not null,
    price     bigint                      not null
);

CREATE INDEX user_id_index ON bot_task (user_id);