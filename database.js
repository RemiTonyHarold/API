var db = db.getMongo().getDB("rss");

db.category.drop();
db.feedSource.drop();

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

var science = db.category.findOne({name: "Science"});

var listScience = [
    {
        categoryId: science._id.str,
        name: "AP Top Science News",
        url: "http://hosted.ap.org/lineups/SCIENCEHEADS-rss_2.0.xml?SITE=OHLIM&SECTION=HOME"
    },
    {
        categoryId: science._id.str,
        name: "ScienceDaily Headlines",
        url: "http://feeds.sciencedaily.com/sciencedaily"
    }
]

db.feedSource.insertMany(listScience);
