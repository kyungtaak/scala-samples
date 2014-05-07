package com.nogoon.samples.scala.mr

import akka.actor.Actor

class ReduceActor extends Actor {
  
	def receive: Receive = {
	  case MapData(dataList) => sender ! reduce(dataList)
	}
	
	def reduce(words: IndexedSeq[WordCount]): ReduceData = ReduceData {
	  
	  println("reduce init!! " + words)
	  
	  words.foldLeft(Map.empty[String, Int]) {
	    (index, words) =>
	      if(index contains words.word)
	        index + (words.word -> (index.get(words.word).get + 1))
	      else
	        index + (words.word -> 1)
	  }
	}
}