SELECT compositions.name, artists.name AS "artist", compositions.album, compositions.length, compositions.year, genres.name AS "genre", compositions.addDate
FROM compositions LEFT JOIN artists ON compositions.artists = artists.id LEFT JOIN genres ON compositions.genre = genres.id;