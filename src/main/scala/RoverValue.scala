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

sealed trait RoverValue {
  val xVal: Int
  val yVal: Int
}

case class RotationVector(xVal: Int, yVal: Int) extends RoverValue {
  def toChangeInPosition(moveFactor: Int): Position = Position(this.xVal * moveFactor, this.yVal * moveFactor)
}

case class Position(xVal: Int, yVal: Int) extends RoverValue {
  def +(pos: Position): Position = this.copy(xVal = this.xVal + pos.xVal, yVal = this.yVal + pos.yVal)
}

object Position {
  def correctPositionToGrid(pos: Position, gridSize: Int): Position = {
    def correctDimensionToGrid(pos: Int): Int = {
      if (pos.abs <= gridSize) pos else (pos.sign * -(gridSize + 1)) + (pos % gridSize)
    }

    val correctedXVal = correctDimensionToGrid(pos.xVal)
    val correctedYVal = correctDimensionToGrid(pos.yVal)

    Position(correctedXVal, correctedYVal)
  }
}
