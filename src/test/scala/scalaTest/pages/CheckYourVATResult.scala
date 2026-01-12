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
import uk.gov.hmrc.selenium.webdriver.Driver

object CheckYourVATResult extends BasePage {

  val checkYourVatResult   = "Your VAT calculation - Check your VAT flat rate - GOV.UK"
  val resultOutcome        = "resultOutcome"
  val useSetVATFlatRate    = "You can use the 16.5% VAT flat rate"
  val useUniqueVATFlatRate = "You can use the VAT flat rate for your business type"

  def result: String = {
    onPage(checkYourVatResult)
    Driver.instance.findElement(By.id(resultOutcome)).getText
  }

}