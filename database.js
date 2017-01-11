var db = db.getMongo().getDB("rss");

db.category.insertMany([
   {name: "World / U.S. News"},
   {name: "Local News"},
   {name: "Education News"},
   {name: "Science"},
   {name: "Technology"},
   {name: "Sports"},
   {name: "Entertainment"},
   {name: "Photos"}
])
