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

  private val dummyRover: Rover = Rover((0, 0), 0, 100)

  "moveForward" when {
    "supplied with a valid amount" should {
      "correctly change Rover position" in {
        dummyRover.moveForward(10) shouldBe dummyRover.copy(position = (0, 10))
      }
    }

    "movement causes Rover to leave the grid" should {
      "correct position to the other side of the grid" in {
        dummyRover.moveForward(101) shouldBe dummyRover.copy(position = (0, -100))
      }
    }

    "supplied with a value of zero" should {
      "return an error" in {
        dummyRover.moveForward(0) shouldBe ???
      }
    }

    "supplied with a negative value" should {
      "return an error" in {
        dummyRover.moveForward(0) shouldBe ???
      }
    }
  }

  "rotate" when {
    "supplied with a positive amount" should {
      "handle clockwise change in Rover rotation" in {
        dummyRover.rotate(10) shouldBe dummyRover.copy(rotation = 10)
      }
    }

    "supplied with a negative amount" should {
      "handle anti-clockwise change in Rover rotation" in {
        dummyRover.rotate(10) shouldBe dummyRover.copy(rotation = -10)
      }
    }
  }
}
