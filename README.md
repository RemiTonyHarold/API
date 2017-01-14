# API

**base_url** : http://remirobert.com:4242

#Authentification

##Signup
create a new user

  - **Method**     : POST
  - **route**      : {{base_url}}/signup
  - **parameters** :
```json
{
  email: "",
  password: ""
}
```
  - **response** :
  
```json
  {
  "user": {
    "id": "397e1d8b-7cd1-43ae-8bbf-4b8ae5defef0",
    "email": "user1@gmail.com",
    "password": "email"
  },
  "token": {
    "accessToken": "GDRYFXAIATTGEBLP9JX6W03T3IPFX041EA8K8H3KV2898RHO3I0K7Q994C6G64Z0IMPOB2IOE2CZBO1Y6GHD6DD6I0IIHD4GMDOA1XU49LSG99YV48Y5XL7Z92ERB5PSHM7F91OX8BG1YWW0C083SQI929GFVUXMP7HXATNG8AIS5TUPGWRM3J20CGKGM95GFTUY5SORCPYFFKDAKEW4NG43APIQBHZNSSU3ZQX2AMY5JWA2IBJ52AUER858D31X",
    "refreshToken": "TUCOC1X9T9YJSNQZMWPAFG6W79L4YSG5LTXJJV5Q41S011HSTFMBMTVCD80V2Z6S05PIOZ5FCM2OBC4HF0F2929ZMPONT50E399Y2O4WT5AGLEV6EY40JN124A81HBJF2IT167M6W7HJXCP32O7XXXRICRLVHHHNN4L126VLID8NEWCLB5MZYIZ6VYK1W0MQPL61BU7S0PGJ5Z7TZR7L79ENSK8JBV7N0T4ZPM2PXXJE6O2VZZDETBEVXNR109TH",
    "expireDate": "Wed Jan 11 18:30:43 EST 2017"
  }
}
```
 
##Login
authentificate user to get access, and refresh token

  - **Method**     : POST
  - **route**      : {{base_url}}/auth
  - **parameters** :
```json
{
  email: "",
  password: ""
}
```
  - **response**  :
```json
 {
  "accessToken": "GS6T805VODRVV964OHTIRQKE65GQF2DGFXV13QLDGR6QR3E1J9ZUZSFUTKC2RRFHF92ANGGATI447867NUCYEZYA253HPYSDC4XQZGNHCL6EG1KS7DGG71ANQCM3DAO5FNNE6E1TAQJ15L7SYHA6J17FRF4AQQ50ORU6RGZAL42ZSZ6KRKTSQLERPW0OQ4UC2180HU5RPBZLL8HFWF8KFILTFX454IQMSS22JRNBTNVCJLEH0WZRNOY2JTVWSND3",
  "refreshToken": "RZV1BNCX0O0MUH1YL42HRZIKOHXF0ALRJSJTO4YQR8DUGF6DXZNZCRDHSQDSSF8OF0AEMJSGXKFHJJD7JOWJ3UIGSOUBP4YBW4XDBUZW1EGBCP3VUADP13ITD8PPQ4JZ3KKNH9N7FF1NP60PSD6ME54DHI9B3OLPQCWOYLFHV9UUCOUNCAP9ZFT9E7Y79VDACE93AQM2QF75PRUUOCVB5FX8XEJH1NS57666H0W7D8Q23LQT1MX65UIZ8L4J9G8G",
  "expireDate": "Wed Jan 11 18:31:02 EST 2017"
}
```

#Token 🔒
call this route to refresh the code

  - **Method**    : POST
  - **route**     : {{base_url}}/accessToken
  - **parameters**:
  ```json
{
  refreshToken: "Y5YGIB25WEVQ9EIZ84AECVC532AF9PI55LXRN0Q69OPDOV0A7S8DPOKPKRJZI3RUYO7K3AYDG4UMIM447BYN1BCNR09F295O1INFDNM43EI6T9LTBSW25XGJBVRVJV8VP6KO0497MCRX666R6DG46N9KDFOUAP7B4FTYADSK3ARCFTTG1QSYZ3TJJ7SCJQ3GJD37J2824YN10EF9KNVURHEU5BE4AI4UF59J0M84SS42GJ511IYRQRMUE7PM2ZNE"
}
```
  - **response**  :
```json
{
  "accessToken": "POP5QZ67F9XXFD84IAND1SPS7ZDR1VZD4ZW27CCAUH1SP1Y2B9DSXQE7TNJ516RHXIHZD0FS79F4GZRSFP8H03LJPQXBUJJC3E74O4GX5K0WA137CFE06QQQ97USKVEGB0FZZQOUD3Z2OAK0297Z62YUUFBDRNPKZ3GR4UC1RCCZW6ACMUVWSABEV7OAZIOJGMCGQER3DX2VGOMOH7E951P84JFVC1M5YF0ULB3HH5Y2KRV7XKR983M4C2UJ5H2V",
  "expireDate": "Wed Jan 11 18:33:33 EST 2017"
}
```

#Categories
get avalaible categories

  - **Method**    : GET
  - **route**     : {{base_url}}/categories
  - **response**  :
```json
[
  {
    "id": "5876b07d90561225dd044cb2",
    "name": "World / U.S. News"
  },
  {
    "id": "5876b07d90561225dd044cb3",
    "name": "Local News"
  }
]
```

#Feed Source
get the feed sources for a given categorie

  - **Method**    : GET
  - **route**     : {{base_url}}/feedSource/{category_id}
  - **response**  :
```json
[
    {
        "categoryId": "5878030ab23d51a4a9fb3f38",
        "id": "5878030ab23d51a4a9fb3f3d",
        "name": "AP Top Science News",
        "url": "http://hosted.ap.org/lineups/SCIENCEHEADS-rss_2.0.xml?SITE=OHLIM&SECTION=HOME"
    },
    {
        "categoryId": "5878030ab23d51a4a9fb3f38",
        "id": "5878030ab23d51a4a9fb3f3e",
        "name": "ScienceDaily Headlines",
        "url": "http://feeds.sciencedaily.com/sciencedaily"
    }
]
```

#News
get the news for a given source

  - **Method**    : GET
  - **route**     : {{base_url}}/news/{source_id}
  - **response**  :
```json
[
    {
        "creator": "By MARCIA DUNN",
        "dateCreation": 1484385773127,
        "description": "CAPE CANAVERAL, Fla.        (AP) -- The International Space Station&amp;apos;s solar power grid got three more top-of-the-line batteries Friday during the second spacewalk in a week....",
        "guid": "http://hosted.ap.org/dynamic/stories/U/US_SCI_SPACE_STATION?SITE=OHLIM&SECTION=HOME&TEMPLATE=DEFAULT",
        "id": "5ee96fb5-e857-4245-b5f3-4b73d7a6d30f",
        "link": "http://hosted.ap.org/dynamic/stories/U/US_SCI_SPACE_STATION?SITE=OHLIM&SECTION=HOME&TEMPLATE=DEFAULT",
        "pubDate": "Fri, 13 Jan 2017 22:49:04 GMT",
        "sourceId": "5878030ab23d51a4a9fb3f3d",
        "thumbnail": null,
        "title": "Spacewalking astronauts upgrade orbiting lab's power grid"
    },
    {
        "creator": "By DAVE COLLINS",
        "dateCreation": 1484385773129,
        "description": "HARTFORD, Conn.        (AP) -- Keep an eye on the picnic basket. Bear sightings are surging across Connecticut....",
        "guid": "http://hosted.ap.org/dynamic/stories/U/US_BEAR_SIGHTINGS?SITE=OHLIM&SECTION=HOME&TEMPLATE=DEFAULT",
        "id": "722ece3d-eb58-493e-9847-b99c90d79788",
        "link": "http://hosted.ap.org/dynamic/stories/U/US_BEAR_SIGHTINGS?SITE=OHLIM&SECTION=HOME&TEMPLATE=DEFAULT",
        "pubDate": "Fri, 13 Jan 2017 19:04:33 GMT",
        "sourceId": "5878030ab23d51a4a9fb3f3d",
        "thumbnail": null,
        "title": "Watch the picnic basket: Bear sightings surge in Connecticut"
    }
]
```
