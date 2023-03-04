INSERT INTO users (name, username, password, enabled)
  values ('Mr Smith',
    'manager',
    '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a',
    1);

INSERT INTO authorities (username, authority)
  values ('manager', 'manager');