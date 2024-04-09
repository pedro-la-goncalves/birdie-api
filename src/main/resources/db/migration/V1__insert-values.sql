DROP TABLE IF EXISTS public.accommodation CASCADE;
DROP TABLE IF EXISTS public.guest CASCADE;
DROP TABLE IF EXISTS public.contact CASCADE;
DROP TABLE IF EXISTS public.expense CASCADE;
DROP TABLE IF EXISTS public.booking CASCADE;
DROP TABLE IF EXISTS public.flyway_schema_history CASCADE;

INSERT INTO public.accommodation (title, description, price)
VALUES
    ('Room 1012', 'A nice room.', 100.0),
    ('Room 1013', 'A nice room.', 100.0),
    ('Room 1014', 'A nice room.', 100.0),
    ('Room 1021', 'A nice room.', 100.0),
    ('Room 1022', 'A nice room.', 100.0),
    ('Room 1023', 'A nice room.', 100.0),
    ('Room 1024', 'A nice room.', 100.0),
    ('Room 1031', 'A nice room.', 100.0),
    ('Room 1032', 'A nice room.', 100.0),
    ('Room 1033', 'A nice room.', 100.0),
    ('Room 1034', 'A nice room.', 100.0),
    ('Room 1041', 'A nice room.', 100.0),
    ('Room 1042', 'A nice room.', 100.0),
    ('Room 1043', 'A nice room.', 100.0),
    ('Room 1044', 'A nice room.', 100.0);

INSERT INTO public.guest (name, surname, social_name, birthdate)
VALUES
    ('James', 'Smith', null, '2000-01-01'),
    ('Mary', 'Johnson', null, '2000-01-01'),
    ('Robert', 'Williams', null, '2000-01-01'),
    ('Patricia', 'Brown', null, '2000-01-01'),
    ('John', 'Jones', null, '2000-01-01'),
    ('Jennifer', 'Garcia', null, '2000-01-01'),
    ('Michael', 'Miller', null, '2000-01-01'),
    ('Linda', 'Davis', null, '2000-01-01'),
    ('David', 'Rodriguez', null, '2000-01-01'),
    ('Elizabeth', 'Martinez', null, '2000-01-01');

INSERT INTO public.contact (type, value, guest_id)
    VALUES
        ('EMAIL', 'james.smith@example.com', 1),
        ('EMAIL', 'mary.johnson@example.com', 2),
        ('EMAIL', 'robert.williams@example.com', 3),
        ('EMAIL', 'patricia.brown@example.com', 4),
        ('EMAIL', 'john.jones@example.com', 5),
        ('EMAIL', 'jennifer.garcia@example.com', 6),
        ('EMAIL', 'michael.miller@example.com', 7),
        ('EMAIL', 'linda.davis@example.com', 8),
        ('EMAIL', 'david.rodriguez@example.com', 9),
        ('EMAIL', 'elizabeth.martinez@example.com', 10);

INSERT INTO public.booking (entry, departure, guest_id, accommodation_id)
    VALUES
        ('2024-04-10', '2024-04-20', 1, 1),
        ('2024-04-10', '2024-04-20', 2, 2),
        ('2024-04-10', '2024-04-20', 3, 3),
        ('2024-04-10', '2024-04-20', 4, 4),
        ('2024-04-10', '2024-04-20', 5, 5),
        ('2024-04-10', '2024-04-20', 6, 6),
        ('2024-04-10', '2024-04-20', 7, 7),
        ('2024-04-10', '2024-04-20', 8, 8),
        ('2024-04-10', '2024-04-20', 9, 9),
        ('2024-04-10', '2024-04-20', 10, 10);