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

class RoverValueSpec extends UnitSpec {

  "correctedToGrid" when {
    "supplied an on-grid position" should {
      "return an unchanged position" in {
        val dummyPosition: Position = Position(xVal = 10, yVal = 10)

        Position.correctPositionToGrid(dummyPosition, 100) shouldBe dummyPosition
      }
    }

    "supplied an edge of grid position" should {
      "return an unchanged position" in {
        val dummyPosition: Position = Position(xVal = 100, yVal = 100)

        Position.correctPositionToGrid(dummyPosition, 100) shouldBe dummyPosition
      }
    }

    "supplied an off-grid position" should {
      def testGridPosition(uncorrected: Position, corrected: Position): Unit = {
        s"return the grid position: $corrected, for supplied the grid position: $uncorrected" in {
          Position.correctPositionToGrid(uncorrected, 100) shouldBe corrected
        }
      }

      val testScenarios = Seq(
        // Right
        (Position(xVal = 101, yVal = 10), Position(xVal = -100, yVal = 10)),
        // Up-right
        (Position(xVal = 101, yVal = 101), Position(xVal = -100, yVal = -100)),
        // Up
        (Position(xVal = 77, yVal = 101), Position(xVal = 77, yVal = -100)),
        // Up-left
        (Position(xVal = -101, yVal = 101), Position(xVal = 100, yVal = -100)),
        // Left
        (Position(xVal = -101, yVal = 30), Position(xVal = 100, yVal = 30)),
        // Down-left
        (Position(xVal = -101, yVal = -101), Position(xVal = 100, yVal = 100)),
        // Down
        (Position(xVal = 30, yVal = -101), Position(xVal = 30, yVal = 100)),
        // Down-right
        (Position(xVal = 101, yVal = -101), Position(xVal = -100, yVal = 100)),
      )

      testScenarios.foreach(test => testGridPosition(uncorrected = test._1, corrected = test._2))
    }
  }
}
