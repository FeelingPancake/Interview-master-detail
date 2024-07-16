
CREATE TABLE IF NOT EXISTS master (
      master_id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
      master_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
      master_sum BIGINT DEFAULT 0 CHECK (master_sum >= 0) NOT NULL,
      master_remark VARCHAR(600) CHECK (LENGTH(master_remark) <= 600)
);

CREATE TABLE IF NOT EXISTS detail (
    detail_id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    master_id BIGINT NOT NULL REFERENCES master (master_id) ON DELETE CASCADE,
    detail_name VARCHAR(100) NOT NULL CHECK LENGTH(detail_name) <= 100 OR (detail_name <> ''),
    detail_sum BIGINT NOT NULL CHECK (detail_sum >= 0)
);