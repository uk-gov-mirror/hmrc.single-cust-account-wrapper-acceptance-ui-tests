/*
 * Copyright 2024 HM Revenue & Customs
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

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.Select
import uk.gov.hmrc.test.ui.PagePaths.{FeedbackPagePaths, GGloginPagePaths, SCAStartPagePaths}
import uk.gov.hmrc.test.ui.pages.config.Configuration

object GGNINOLoginPage extends BasePage with GGloginPagePaths with SCAStartPagePaths with FeedbackPagePaths {

  def navigateToStartPage(): Unit =
    driver.get(Configuration.settings.APP_ROOT)

  def navigateToAuthLoginStub(): Unit =
    driver.navigate().to(Configuration.settings.AUTHLOGINSTUB)

  def enterRedirectURL(): Unit = {
    sendKeys(By.name(redirectURLField), Configuration.settings.NINO_PAGE)
  }

  def selectConfidenceLevel(): Unit = {
    val confidenceLevel: Select = new Select(findBy(By.name(confidenceLevelField)))
    confidenceLevel.selectByValue("200")
  }

  def enterNino(): Unit        = {
    sendKeys(By.name(nino), NINumber)
  }

  def selectSAEnrolment(): Unit = {
    val EnrolmentSelect: Select = new Select(findBy(By.id(dropdown)))
    EnrolmentSelect.selectByVisibleText(SelfAssessment)
    click(By.id(addPresent))
    sendKeys(By.id(identifierValueForUTRNumber), UTRNumber)
  }

  def selectPTAEnrolment(): Unit = {
    sendKeys(By.id("enrolment[0].name"), EnrolmentKey)
    sendKeys(By.id("input-0-0-name"), IdentifierName)
    sendKeys(By.id(identifierValueForPTA), NINumber)
  }

  def clickSubmitButton(): Unit = {
    click(By.id(submitButton))
  }

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
