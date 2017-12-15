SELECT genres.id, genres.name, COUNT(compositions.name) AS 'number'
FROM genres
  LEFT JOIN compositions ON genres.id = compositions.genre
GROUP BY genres.id;