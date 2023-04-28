/*
 * Copyright 2023 Luke A Jones
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import scala.annotation.tailrec

object Main {

  def main(args: Array[String]): Unit = {
    val rover = Rover(Position(0, 0), UP, 100)

    @tailrec
    def run(rover: Rover): Unit = {
      println(s"\nThe rover's current position is: ${rover.position.toCoordString}, " +
        s"and its rotation is described by the vector: ${rover.rotation.directionVector.toCoordString}.")

      println("Would you like to rotate (R), move (M), or end (E)?")
      val input = scala.io.StdIn.readLine()

      @tailrec
      def rotateState(): Rover = {
        println("Would you like to rotate clockwise (C) or anti-clockwise (A)?")
        val input = scala.io.StdIn.readLine().toUpperCase()

        input match {
          case "C" => rover.rotate(clockwise = true)
          case "A" => rover.rotate(clockwise = false)
          case _ =>
            println(s"unrecognised input '$input'. Please supply 'C', or 'A'.")
            rotateState()
        }
      }

      @tailrec
      def moveState(): Rover = {
        println("By what integer amount would you like to move?")
        val input = scala.io.StdIn.readLine()

        input.toIntOption match {
          case Some(int) => rover.moveForward(int)
          case None =>
            println(s"unrecognised input '$input'. Please supply an integer.")
            moveState()
        }
      }

      input.toUpperCase match {
        case "E" => println("Goodbye.")
        case "R" => run(rotateState())
        case "M" => run(moveState())
        case _ =>
          println(s"unrecognised input '$input'. Please supply 'R', 'M', or 'E'.")
          run(rover)
      }
    }

    run(rover)
  }
}