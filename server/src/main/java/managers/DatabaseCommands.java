package managers;

public class DatabaseCommands {
    public static final String allTablesCreation = """
            CREATE TYPE COLOR AS ENUM (
                'RED',
                'WHITE',
                'BROWN'
            );
            CREATE TYPE DRAGON_TYPE AS ENUM(
                'WATER',
                'AIR',
                'FIRE'
            );
            CREATE TYPE DRAGON_CHARACTER AS ENUM(
                'EVIL',
                'CHAOTIC',
                'CHAOTIC_EVIL'
            );
            CREATE TABLE IF NOT EXISTS dragon (
                id SERIAL PRIMARY KEY,
                dragon_name TEXT NOT NULL ,
                cord_x NUMERIC NOT NULL,
                cord_y NUMERIC NOT NULL ,
                creation_date TIMESTAMP NOT NULL ,
                dragon_age BIGINT NOT NULL ,
                dragon_color COLOR NOT NULL,
                dragon_type DRAGON_TYPE,
                dragon_character DRAGON_CHARACTER NOT NULL,
                dragon_head_eyes_count INTEGER NOT NULL,
                owner_login TEXT NOT NULL
            );
            CREATE TABLE IF NOT EXISTS users (
                id SERIAL PRIMARY KEY,
                login TEXT,
                password TEXT,
                salt TEXT
            );
            """;
    public static final String addUser = """
            INSERT INTO users(login, password, salt) VALUES (?, ?, ?);""";

    public static final String getUser = """
            SELECT * FROM users WHERE (login = ?);""";

    public static final String addObject = """
            INSERT INTO dragon(dragon_name, cord_x, cord_y, creation_date, dragon_age, dragon_color, dragon_type, dragon_character, dragon_head_eyes_count, owner_login)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            RETURNING id;
            """;

    public static final String getAllObjects = """
            SELECT * FROM dragon;
            """;

    public static final String deleteUserOwnedObjects = """
            DELETE FROM dragon WHERE (owner_login = ?) AND (id = ?) RETURNING id;
            """;

    public static final String deleteUserObject = """
            DELETE FROM dragon WHERE (owner_login = ?) AND (id = ?) RETURNING id;
            """;

    public static final String updateUserObject = """
            UPDATE dragon
            SET (dragon_name, cord_x, cord_y, creation_date, dragon_age, dragon_color, dragon_type, dragon_character, dragon_head_eyes_count)
             = (?, ?, ?, ?, ?, ?, ?, ?, ?)
            WHERE (id = ?) AND (owner_login = ?)
            RETURNING id;
            """;
}
