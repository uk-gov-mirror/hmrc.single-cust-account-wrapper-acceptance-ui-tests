/*
 * Copyright 2026 HM Revenue & Customs
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

package scalaTest.pages

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.Select
import scalaTest.PagePaths.{FeedbackPagePaths, GGloginPagePaths, SCAStartPagePaths}
import scalaTest.pages.config.Configuration
import uk.gov.hmrc.selenium.webdriver.Driver

object GGNINOLoginPage extends BasePage with GGloginPagePaths with SCAStartPagePaths with FeedbackPagePaths {

  def navigateToStartPage(): Unit =
    Driver.instance.get(Configuration.settings.APP_ROOT)

  def navigateToAuthLoginStub(): Unit =
    Driver.instance.navigate().to(Configuration.settings.AUTHLOGINSTUB)

  def enterRedirectURL(): Unit =
    Driver.instance
      .findElement(By.name(redirectURLField))
      .sendKeys(Configuration.settings.NINO_PAGE)

  def selectConfidenceLevel(): Unit = {
    val confidenceLevel: Select = new Select(Driver.instance.findElement(By.name(confidenceLevelField)))
    confidenceLevel.selectByValue("200")
  }
  def enterNino(): Unit        =
    Driver.instance
      .findElement(By.name(nino))
      .sendKeys(NINumber)
  def selectSAEnrolment(): Unit = {
    val EnrolmentSelect: Select = new Select(Driver.instance.findElement(By.id(dropdown)))
    EnrolmentSelect.selectByVisibleText(SelfAssessment)
    Driver.instance.findElement(By.id(addPresent)).click()
    Driver.instance
      .findElement(By.id(identifierValueForUTRNumber))
      .sendKeys(UTRNumber)
  }

  def selectPTAEnrolment(): Unit = {
    Driver.instance
      .findElement(By.id("enrolment[0].name"))
      .sendKeys(EnrolmentKey)
    Driver.instance
      .findElement(By.id("input-0-0-name"))
      .sendKeys(IdentifierName)

    Driver.instance
      .findElement(By.id(identifierValueForPTA))
      .sendKeys(NINumber)
  }

  def clickSubmitButton(): Unit =
    Driver.instance.findElement(By.id(submitButton)).click()

  val NINumber                    = "AA000003B"
  val dropdown                    = "presets-dropdown"
  val SelfAssessment              = "SA"
  val addPresent                  = "add-preset"
  val identifierValueForUTRNumber = "input-4-0-value"
  val UTRNumber                   = "1632631936"
  val identifierValueForPTA       = "input-0-0-value"
  val PTANumber                   = "AA000003B"
  val EnrolmentKey                = "HMRC-PT"
  val IdentifierName              = "NINO"
}