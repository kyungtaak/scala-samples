package com.nogoon.samples.scala

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import scala.collection.mutable.Stack

class ExamplesSpec extends FlatSpec with Matchers {
  
  "A Stack" should "pop values in last-in-first-out order" in {
    val stack = new Stack[Int]
    stack.push(1)
    stack.push(2)
    stack.pop() should be (2)
    stack.pop() should be (1)
  }
  
  it should "throw NoSuchElementException if an empty stack is popped" in {
    val emptyStack = new Stack[Int]
    a [NoSuchElementException] should be thrownBy {
      emptyStack.pop()
    }
  }

}