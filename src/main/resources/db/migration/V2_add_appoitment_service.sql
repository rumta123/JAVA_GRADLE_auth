DO $$
BEGIN
   -- Проверяем, существует ли таблица appointment_service
   IF NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'appointment_service') THEN
      -- Если таблицы нет, создаём её
CREATE TABLE appointment_service (
                                     appointment_id BIGINT NOT NULL,
                                     service_id BIGINT NOT NULL,
                                     PRIMARY KEY (appointment_id, service_id),
                                     FOREIGN KEY (appointment_id) REFERENCES appointment(id) ON DELETE CASCADE,
                                     FOREIGN KEY (service_id) REFERENCES service_salon(id) ON DELETE CASCADE
);
END IF;
END $$;
