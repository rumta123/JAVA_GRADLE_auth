DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'master_qualification') THEN
CREATE TABLE master_qualification (
                                      master_id BIGINT NOT NULL,
                                      qualification_id BIGINT NOT NULL,
                                      PRIMARY KEY (master_id, qualification_id),
                                      FOREIGN KEY (master_id) REFERENCES master(id) ON DELETE CASCADE,
                                      FOREIGN KEY (qualification_id) REFERENCES qualification(id) ON DELETE CASCADE
);
END IF;
END $$;
