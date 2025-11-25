
CREATE TABLE IF NOT EXISTS Games (
                                     game_id integer primary key autoincrement,
                                     title text not null,
                                     year_published integer,
                                     min_players integer,
                                     max_players integer,
                                     playing_time_min integer,
                                     complexity real,
                                     designer_name text
);

INSERT INTO Games
    (title, year_published, min_players, max_players, playing_time_min, complexity, designer_name) VALUES
                                                                                                       ('Snakes and Ladders', 2016, 1, 5, 115, 3.4, 'Zack Radison'),
                                                                                                       ('Clue', 2015, 1, 6, 90, 2.9, 'James Floyd'),
                                                                                                       ('Operation', 2015, 2, 8, 15, 1.3, 'Percy Jackson'),
                                                                                                       ('Trouble', 2015, 2, 4, 240, 4.3, 'Pravish'),
                                                                                                       ('Dungeon and Dragons', 1999, 2, 2, 30, 1.8, 'Karim Fahd'),
                                                                                                       ('Candy Land', 2015, 2, 4, 90, 2.7, 'Michael Tellides'),
                                                                                                       ('Uno', 1997, 2, 4, 90, 3.6, 'Syed');select * from Games;

SELECT * FROM Games;

SELECT g.title, g.year_published
FROM Games g;

SELECT g.title, g.playing_time_min
FROM Games g
WHERE g.playing_time_min <= 60;

SELECT g.title AS GameTitle, g.complexity
FROM Games g
ORDER BY g.title;

SELECT avg(g.complexity) AS AverageComplexity
FROM Games g;