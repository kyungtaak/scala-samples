package com.nogoon.samples.scala.mr

import akka.actor.Actor
import scala.collection.mutable.HashMap

class AggregateActor extends Actor {

  val finalReducedMap = new HashMap[String, Int];

  def receive: Receive = {
    case ReduceData(reduceDataMap) => aggregateInMemoryReduce(reduceDataMap)
    case Result => sender ! finalReducedMap.toString()
  }

  def aggregateInMemoryReduce(reducedList: Map[String, Int]): Unit = {
    
    println("aggregateInMemoryReduce init! " + reducedList)
    println("finalReducedMap ?? - " + finalReducedMap)
    
    for ((key, value) <- reducedList) {
      if (finalReducedMap contains key)
        finalReducedMap(key) = (value + finalReducedMap.get(key).get)
      else
        finalReducedMap += (key -> value)
    }
  }

}