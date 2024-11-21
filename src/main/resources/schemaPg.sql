-- pg
CREATE TABLE IF NOT EXISTS congregations (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE IF NOT EXISTS territories (
    id BIGSERIAL PRIMARY KEY,
    congregation_id BIGINT NOT NULL,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(250),
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    UNIQUE (congregation_id, name),
    FOREIGN KEY (congregation_id) REFERENCES congregations(id)
);
CREATE TABLE IF NOT EXISTS blocks (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(250),
    territory_id BIGINT NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    UNIQUE (territory_id, name),
    FOREIGN KEY (territory_id) REFERENCES territories(id)
);
CREATE TABLE IF NOT EXISTS schedules (
    id BIGSERIAL PRIMARY KEY,
    congregation_id BIGINT NOT NULL,
    name VARCHAR(50) NOT NULL,
    time VARCHAR(50) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    UNIQUE (congregation_id, name),
    UNIQUE (congregation_id, time),
    FOREIGN KEY (congregation_id) REFERENCES congregations(id)
);
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    names VARCHAR(200) NOT NULL,
    last_names VARCHAR(200) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(200) NOT NULL,
    congregation_id BIGINT NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (congregation_id) REFERENCES congregations(id)
);

CREATE TABLE IF NOT EXISTS reports (
    id BIGSERIAL PRIMARY KEY,
    date VARCHAR(10) NOT NULL,
    schedule_id BIGINT NOT NULL,
    preaching_driver_id BIGINT NOT NULL,
    observations VARCHAR(250),
    FOREIGN KEY (schedule_id) REFERENCES schedules(id),
    FOREIGN KEY (preaching_driver_id) REFERENCES users(id)
);
CREATE TABLE IF NOT EXISTS report_territory_items (
    id BIGSERIAL PRIMARY KEY,
    report_id BIGINT NOT NULL,
    territory_id BIGINT NOT NULL,
    observations VARCHAR(250),
    completed BOOLEAN,
    FOREIGN KEY (report_id) REFERENCES reports(id),
    FOREIGN KEY (territory_id) REFERENCES territories(id)
);
CREATE TABLE IF NOT EXISTS report_territory_block_items(
    id BIGSERIAL PRIMARY KEY,
    block_id BIGINT NOT NULL,
    report_territory_item_id BIGINT NOT NULL,
    observations VARCHAR(250),
    completed BOOLEAN,
    FOREIGN KEY (block_id) REFERENCES blocks(id),
    FOREIGN KEY (report_territory_item_id) REFERENCES report_territory_items(id)
);