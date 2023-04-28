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

class RoverSpec extends UnitSpec {

  private val dummyRover: Rover = Rover(Position(xVal = 0, yVal = 0), UP, 100)

  "moveForward" when {
    "supplied with a valid amount" should {
      "correctly change Rover position" in {
        dummyRover.moveForward(10) shouldBe dummyRover.copy(Position(xVal = 0, yVal = 10))
      }
    }


    "supplied with a valid amount, and non-upwards rotation" should {
      "correctly change Rover position" in {
        val downRightDummyRover = Rover(Position(0, 0), DOWN_RIGHT, 100)
        downRightDummyRover.moveForward(10) shouldBe downRightDummyRover.copy(Position(xVal = 10, yVal = -10))
      }
    }

    "movement causes Rover to leave the grid" should {
      "correct position to the other side of the grid" in {
        dummyRover.moveForward(101) shouldBe dummyRover.copy(Position(xVal = 0, yVal = -100))
      }
    }

    "supplied with a value of zero" should {
      "return the Rover with the same position" in {
        dummyRover.moveForward(0) shouldBe dummyRover
      }
    }

    "supplied with a negative value" should {
      "return the Rover moved backwards" in {
        dummyRover.moveForward(-10) shouldBe dummyRover.copy(position = Position(xVal = 0, yVal = -10))
      }
    }
  }

  "rotate" when {
    "supplied with `true`" should {
      "handle clockwise change in Rover rotation" in {
        dummyRover.rotate(true) shouldBe dummyRover.copy(rotation = UP_RIGHT)
      }
    }

    "supplied with `false`" should {
      "handle anti-clockwise change in Rover rotation" in {
        dummyRover.rotate(false) shouldBe dummyRover.copy(rotation = UP_LEFT)
      }
    }
  }
}
