CREATE TABLE messages(
    idmessage serial PRIMARY KEY,
    contenu TEXT,
    dateEnvoi TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expediteur varchar(255),
    recepteur varchar(255)
)