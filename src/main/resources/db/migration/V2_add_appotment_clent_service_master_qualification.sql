DO $$
    BEGIN
        -- Проверяем существование таблицы appointment
        IF NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'appointment') THEN
            CREATE TABLE appointment (
                                         id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                         client_id BIGINT NOT NULL,
                                         master_id BIGINT NOT NULL,
                                         qualification_id BIGINT NOT NULL,
                                         date_time TIMESTAMP NOT NULL, -- Поле для хранения даты и времени
                                         FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE,
                                         FOREIGN KEY (master_id) REFERENCES master(id) ON DELETE CASCADE,
                                         FOREIGN KEY (qualification_id) REFERENCES qualification(id) ON DELETE CASCADE
            );
        END IF;

        -- Проверяем существование таблицы appointment_service
        IF NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'appointment_service') THEN
            CREATE TABLE appointment_service (
                                                 appointment_id BIGINT NOT NULL,
                                                 service_id BIGINT NOT NULL,
                                                 PRIMARY KEY (appointment_id, service_id),
                                                 FOREIGN KEY (appointment_id) REFERENCES appointment(id) ON DELETE CASCADE,
                                                 FOREIGN KEY (service_id) REFERENCES service_salon(id) ON DELETE CASCADE
            );
        END IF;
    END $$;
