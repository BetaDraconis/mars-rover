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

sealed trait GridRotation {
  val nextClockwise: GridRotation
  val nextAntiClockwise: GridRotation
  val directionVector: RotationVector
}

case object UP extends GridRotation {
  override val nextClockwise: GridRotation = UP_RIGHT
  override val nextAntiClockwise: GridRotation = UP_LEFT
  override val directionVector: RotationVector = RotationVector(xVal = 0, yVal = 1)
}

case object UP_RIGHT extends GridRotation {
  override val nextClockwise: GridRotation = RIGHT
  override val nextAntiClockwise: GridRotation = UP
  override val directionVector: RotationVector = RotationVector(xVal = 1, yVal = 1)
}

case object RIGHT extends GridRotation{
  override val nextClockwise: GridRotation = DOWN_RIGHT
  override val nextAntiClockwise: GridRotation = UP_RIGHT
  override val directionVector: RotationVector = RotationVector(xVal = 1, yVal = 0)
}

case object DOWN_RIGHT extends GridRotation{
  override val nextClockwise: GridRotation = DOWN
  override val nextAntiClockwise: GridRotation = RIGHT
  override val directionVector: RotationVector = RotationVector(xVal = 1, yVal = -1)
}

case object DOWN extends GridRotation{
  override val nextClockwise: GridRotation = DOWN_LEFT
  override val nextAntiClockwise: GridRotation = DOWN_RIGHT
  override val directionVector: RotationVector = RotationVector(xVal = 0, yVal = -1)
}

case object DOWN_LEFT extends GridRotation{
  override val nextClockwise: GridRotation = UP
  override val nextAntiClockwise: GridRotation = DOWN
  override val directionVector: RotationVector = RotationVector(xVal = -1, yVal = -1)
}

case object LEFT extends GridRotation{
  override val nextClockwise: GridRotation = UP_LEFT
  override val nextAntiClockwise: GridRotation = DOWN_LEFT
  override val directionVector: RotationVector = RotationVector(xVal = -1, yVal = 0)
}

case object UP_LEFT extends GridRotation{
  override val nextClockwise: GridRotation = UP
  override val nextAntiClockwise: GridRotation = LEFT
  override val directionVector: RotationVector = RotationVector(xVal = -1, yVal = 1)
}
