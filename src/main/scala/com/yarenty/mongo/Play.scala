package com.yarenty.mongo


import com.mongodb.casbah.Imports._

/**
 * Created by yarenty on 14/09/2015.
 */
object Play {

  val mongoClient = MongoClient("localhost", 27017)

  val db = mongoClient("test")


  def main(args: Array[String]) {
    println(db.collectionNames mkString (";"))

    val coll = db("test")


//    val a = MongoDBObject("hello" -> "world")
//    val b = MongoDBObject("language" -> "scala")
//
//
//    coll.insert(a)
//    coll.insert(b)

    println(coll.count())



    val allDocs = coll.find()
    println(allDocs)
    for (doc <- allDocs) println(doc)


    val hello = MongoDBObject("hello" -> "world")
    val helloWorld = coll.findOne(hello)
    println(helloWorld)

    // Find a document that doesn't exist
    val goodbye = MongoDBObject("goodbye" -> "world")
    val goodbyeWorld = coll.findOne(goodbye)
    println(goodbyeWorld)


    val query = MongoDBObject("language" -> "scala")
    val update = MongoDBObject("platform" -> "JVM")
    val result = coll.update(query, update)

    println("Number updated: " + result.getN)
    for (c <- coll.find) println(c)

    val query2 = MongoDBObject("language" -> "clojure")
    val update2 = $set("platform" -> "JVM")
    val result2 = coll.update( query2, update2, upsert=true )

    println( "Number updated: " + result.getN )
    for (c <- coll.find) println(c)


    val query3 = MongoDBObject("language" -> "clojure")
    val result3 = coll.remove( query3 )

    println("Number removed: " + result3.getN)
    for (c <- coll.find) println(c)
  }
}
