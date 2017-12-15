SELECT artists.id, artists.name, COUNT(compositions.name) AS 'number'
FROM artists
  LEFT JOIN compositions ON artists.id = compositions.artists
GROUP BY artists.id;