var db = db.getMongo().getDB("rss");

db.category.drop();
db.feedSource.drop();

db.category.insertMany([
   {name: "World / U.S. News"},
   {name: "Education News"},
   {name: "Science"},
   {name: "Technology"},
   {name: "Sports"},
   {name: "Entertainment"},
   {name: "Photos"}
])

var science = db.category.findOne({name: "Science"});
var technology = db.category.findOne({name: "Technology"});
var sport = db.category.findOne({name: "Sports"});
var entertainment = db.category.findOne({name: "Entertainment"});
var news = db.category.findOne({name: "World / U.S. News"});
var education = db.category.findOne({name: "Education News"});
var photos = db.category.findOne({name: "Photos"});

var listScience = [
    {categoryId: science._id.str, url: "http://hosted.ap.org/lineups/SCIENCEHEADS-rss_2.0.xml?SITE=OHLIM&SECTION=HOME"},
    {categoryId: science._id.str, url: "http://feeds.sciencedaily.com/sciencedaily"}
]

var listTechnology = [
    {categoryId: technology._id.str, url: "http://www.computerweekly.com/rss/All-Computer-Weekly-content.xml"},
    {categoryId: technology._id.str, url: "http://www.computerweekly.com/rss/Latest-IT-news.xml"},
    {categoryId: technology._id.str, url: "http://www.computerweekly.com/rss/IT-hardware.xml"},
    {categoryId: technology._id.str, url: "http://www.computerweekly.com/rss/Enterprise-software.xml"},
    {categoryId: technology._id.str, url: "http://www.computerweekly.com/rss/IT-security.xml"},
    {categoryId: technology._id.str, url: "http://www.computerweekly.com/rss/Mobile-technology.xml"},
    {categoryId: technology._id.str, url: "http://www.techradar.com/rss/news/gaming"}
]

var listSport = [
    {categoryId: sport._id.str, url: "http://hosted.ap.org/lineups/SPORTSHEADS-rss_2.0.xml?SITE=VABRM&SECTION=HOME"},
    {categoryId: sport._id.str, url: "http://www.si.com/rss/si_topstories.rss"},
    {categoryId: sport._id.str, url: "http://feeds1.nytimes.com/nyt/rss/Sports"},
    {categoryId: sport._id.str, url: "http://www.nba.com/jazz/rss.xml"}
]

var listEntertainment = [
    {categoryId: entertainment._id.str, url: "http://www.npr.org/rss/rss.php?id=1008"},
    {categoryId: entertainment._id.str, url: "http://www.newyorker.com/feed/humor"},
    {categoryId: entertainment._id.str, url: "http://www.npr.org/rss/rss.php?id=13"},
    {categoryId: entertainment._id.str, url: "http://www.npr.org/rss/rss.php?id=1045"}
]

var listNews = [
    {categoryId: news._id.str, url: "http://www.npr.org/rss/rss.php?id=1008"},
    {categoryId: news._id.str, url: "http://feeds.nytimes.com/nyt/rss/HomePage"}
]

var listEducation = [
    {categoryId: education._id.str, url: "http://www.uen.org/feeds/rss/news.xml.php"},
    {categoryId: education._id.str, url: "https://www.ed.gov/feed"},
    {categoryId: education._id.str, url: "http://www.techlearning.com/RSS"}
]

var listPhotos = [
    {categoryId: photos._id.str, url: "http://feeds.feedburner.com/TheDailyPuppy"},
    {categoryId: photos._id.str, url: "http://feeds.feedburner.com/animals"}
]

db.feedSource.insertMany(listScience);
db.feedSource.insertMany(listPhotos);
db.feedSource.insertMany(listEducation);
db.feedSource.insertMany(listTechnology);
db.feedSource.insertMany(listSport);
db.feedSource.insertMany(listEntertainment);
db.feedSource.insertMany(listNews);
