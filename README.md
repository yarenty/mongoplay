# mongoplay
Fun with mongo db


## Start mongo deamon
./mongodb -dbpath /opt/mongodb/bin/data/db



##import test db
./mongoimport --db test --collection restaurants --drop --file /opt/mongodb/example_dataset.json



## play with data
./mongo

use test

db.restaurants.find()

db.restaurants.find({"borough":"Manhattan"})

db.restaurants.find({"grades.score":{$lt: 10 }})

db.restaurants.find({ "cuisine":"Italian", "address.zipcode":"10075"})

db.restaurants.update(     { "name" : "Juni" },     {       $set: { "cuisine": "American (New)" },       $currentDate: { "lastModified": true }     } )

db.restaurants.update(   { "address.zipcode": "10016", cuisine: "Other" },   {     $set: { cuisine: "Category To Be Determined" },     $currentDate: { "lastModified": true }   },   { multi: true} )

db.restaurants.update(   { "restaurant_id" : "41156888" },   { $set: { "address.street": "East 31st Street" } } )

db.restaurants.find({ "restaurant_id":"41704620"})


db.restaurants.find({ "cuisine":"Italian"}).sort({ "address.zipcode":1})


//replace
db.restaurants.update(    { "restaurant_id" : "41704620" },    {      "name" : "Vella 2",      "address" : {               "coord" : [ -73.9557413, 40.7720266 ],               "building" : "1480",               "street" : "2 Avenue",               "zipcode" : "10075"      }    } )

db.restaurants.aggregate(    [      { $group: { "_id": "$borough", "count": { $sum: 1 } } }    ] );

db.restaurants.remove({ "borough":null})

db.restaurants.aggregate(    [      { $match: { "borough": "Queens", "cuisine": "Brazilian" } },      { $group: { "_id": "$address.zipcode" , "count": { $sum: 1 } } }    ] );


db.restaurants.createIndex( { "cuisine": 1 } )

// zip code descending
db.restaurants.createIndex( { "cuisine": 1, "address.zipcode": -1 } )



