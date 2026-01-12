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

import org.openqa.selenium.support.ui.{ExpectedConditions, FluentWait, Select}
import org.openqa.selenium.{By, WebDriver}
import scalaTest.PagePaths.GGloginPagePaths
import scalaTest.pages.GGLoginPage.SCAAccessErrorText
import uk.gov.hmrc.selenium.webdriver.Driver

import java.time.Duration

object GGLoginPageInvalidConfidenceLevel extends BasePage with GGloginPagePaths {

  def selectConfidenceLevel(): Unit = {
    val confidenceLevel: Select = new Select(Driver.instance.findElement(By.name(confidenceLevelField)))
    confidenceLevel.selectByValue("50")
  }

  def verifySCAStartPageAccessError(SCAAccessError: String) =
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(10))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.className(SCAAccessErrorText), SCAAccessError))

}