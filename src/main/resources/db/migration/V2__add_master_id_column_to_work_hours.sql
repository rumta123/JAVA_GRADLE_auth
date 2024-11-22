DO $$
BEGIN
   -- Проверка на существование столбца 'master_id'
   IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'work_hours' AND column_name = 'master_id') THEN
      -- Добавление столбца, если его нет
ALTER TABLE work_hours ADD COLUMN master_id BIGINT NOT NULL;
END IF;

   -- Добавление внешнего ключа, если столбец был успешно добавлен
   IF NOT EXISTS (SELECT 1 FROM information_schema.table_constraints WHERE constraint_type = 'FOREIGN KEY' AND table_name = 'work_hours' AND constraint_name = 'fk_master') THEN
ALTER TABLE work_hours
    ADD CONSTRAINT fk_master
        FOREIGN KEY (master_id)
            REFERENCES master(id);
END IF;
END $$;
