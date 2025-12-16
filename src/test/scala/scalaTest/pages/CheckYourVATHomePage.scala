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
import uk.gov.hmrc.selenium.webdriver.Driver
import uk.gov.hmrc.test.ui.conf.TestConfiguration

object CheckYourVATHomePage extends BasePage with CommonFunctions {
  val url: String     = TestConfiguration.url("example-frontend") + "/vat-return-period"
  val vatReturnPeriod = "Enter your VAT return details - Check your VAT flat rate - GOV.UK"

  val annuallyRadioButton  = "vatReturnPeriod"
  val quarterlyRadioButton = "vatReturnPeriod-2"

  def loadPage: this.type = {
    Driver.instance.navigate().to(url)
    onPage(vatReturnPeriod)
    this
  }

  def provideVATPeriod(period: String): Turnover.type = {
    period match {
      case "Annually" => click(By.id(annuallyRadioButton))
      case _          => click(By.id(quarterlyRadioButton))
    }
    submitPage()
    Turnover
  }
}