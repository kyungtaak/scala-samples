package com.nogoon.samples.scala.mr

import scala.collection.immutable.Map
import scala.collection.mutable.ArrayBuffer
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import scala.concurrent.Await
import scala.concurrent.Future
import scala.concurrent.duration._	

import akka.pattern.ask
import akka.util.Timeout	


sealed trait MapReduceMessage
case class WordCount(word: String, count: Int) extends MapReduceMessage
case class MapData(dataList: ArrayBuffer[WordCount]) extends MapReduceMessage
case class ReduceData(reduceDataMap: Map[String, Int]) extends MapReduceMessage
case class Result extends MapReduceMessage

object MapReduceApplication extends App {
  val _system = ActorSystem("MapReduceApp")
  val master = _system.actorOf(Props[MasterActor], name = "master")
  
  implicit val timeout = Timeout(10000)
  
  master ! "The quick brown fox tried to jump over the lazy dog and fell on the dog"
  master ! "Dog is man's best friend"
  master ! "Dog and Fox belong to the same family"
  
  Thread.sleep(1000)
  
  // When using non-blocking it is better to use the mapTo method 
  // to safely try to cast a Future to an expected type
  val future = (master ? Result).mapTo[String]
  val result = Await.result(future, timeout.duration)
   
  
  println(result)
      
  //_system.shutdown
  
  //_system.scheduler.scheduleOnce(20, SECONDS)(system.shutdown)
    
}