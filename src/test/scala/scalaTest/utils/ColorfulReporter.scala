/*
 * Copyright 2025 HM Revenue & Customs
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

package scalaTest.utils

import org.scalatest.Reporter
import org.scalatest.events.*

class ColorfulReporter() extends Reporter {

  private val RESET  = "\u001B[0m"
  private val GREEN  = "\u001B[32m"
  private val RED    = "\u001B[31m"
  private val YELLOW = "\u001B[33m"
  private val CYAN   = "\u001B[36m"

  override def apply(event: Event): Unit =
    event match {
      case e: ScopeOpened   =>
        println(s"$CYAN=== Feature: ${e.message} ===$RESET")
      case e: TestStarting  =>
        println(s"$YELLOW--> Scenario: ${e.testName}$RESET")
      case e: TestSucceeded =>
        println(s"$GREEN✓ PASSED: ${e.testName}$RESET")
      case e: TestFailed    =>
        println(s"$RED❌ FAILED: ${e.testName}$RESET")
      case _                => // ignore
    }
}
