package com.nogoon.samples.scala.mr

import akka.actor.Actor
import scala.collection.mutable.ArrayBuffer

class MapActor extends Actor {
  
	val STOP_WORDS_LIST = List("a", "am", "an", "and", "are", "as", "at", "be", "do", "go", "if", "in", "is", "it", "of", "on", "the", "to")
	
	val defaultCount: Int = 1
  
	def receive : Receive = {
	  case message : String =>
	    sender ! evaluateExpression(message)
	}
	
	def evaluateExpression(line: String): MapData = MapData {
		println("evaluateExpression init -> " + line)
		line.split("""\s+""").foldLeft(ArrayBuffer.empty[WordCount]) {
				(index, word) =>
					if(!STOP_WORDS_LIST.contains(word.toLowerCase))
						index += WordCount(word.toLowerCase, 1)
					else
						index
		}
	}
}