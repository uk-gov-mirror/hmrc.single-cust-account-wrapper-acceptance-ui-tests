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

package scalaTest.pages

import commonstepdefs.CommonFunctions
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.Select
import uk.gov.hmrc.selenium.webdriver.Driver
import uk.gov.hmrc.test.ui.PagePaths.{FeedbackPagePaths, GGloginPagePaths, SCAStartPagePaths}
import uk.gov.hmrc.test.ui.conf.TestConfiguration

object GGChildBenefitLogin extends BasePage with GGloginPagePaths with SCAStartPagePaths with FeedbackPagePaths with CommonFunctions {


  def navigateToBaseUrl(url: String): Unit = {
    deleteCookies()
    Driver.instance.navigate().to(TestConfiguration.url(url))
  }

  def navigateToServiceUrl(url: String, serviceName: String): Unit = {
    deleteCookies()
    Driver.instance.navigate().to(TestConfiguration.url(url) + serviceName)
    Driver.instance.manage().window().maximize()
  }

  def deleteCookies(): Unit =
    Driver.instance.manage().deleteAllCookies()

  def setConfidenceLevel(): Unit = {
    sendKeys(By.id("confidenceLevel"), "200")
  }

  def setOrganisationAffinityGroup(): Unit = {
    sendKeys(By.id("affinityGroupSelect"), "Organisation")
  }


  def enterNINO(nino: String): Unit =
    nino match {
      case "invalidService" =>
      case _                => sendKeys(By.id("nino"), nino)
    }

  def clickSubmitButton(): Unit = {
    click(By.id("submit"))
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

  val NINumber = "AB654321A"
  val dropdown = "presets-dropdown"
  val SelfAssessment = "SA"
  val addPresent = "add-preset"
  val identifierValueForUTRNumber = "input-4-0-value"
  val UTRNumber = "1632631936"
  val identifierValueForPTA = "input-0-0-value"
  val PTANumber = "AB654321A"
  val EnrolmentKey = "HMRC-PT"
  val IdentifierName = "NINO"
}