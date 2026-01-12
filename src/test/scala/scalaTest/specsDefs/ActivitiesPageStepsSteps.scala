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

package scalaTest.specsDefs

import org.junit.Assert.assertTrue
import org.openqa.selenium.By
import scalaTest.pages.SCAStartPage
import uk.gov.hmrc.selenium.webdriver.Driver

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object ActivitiesPageStepsSteps {

  // ^User is on activity start page$
  def userIsOnActivityStartPage(): Unit = {
  assert(SCAStartPage.verifySCAStartPage())
  }
  
  def userShouldAbleToSee(value: String, locator: String): Unit = {
    SCAStartPage.assertContent(By.xpath("//*[@class='" + locator + "']"), value)
  }

  // ^User should see test text on activity page$
  def userShouldSeeTestTextOnActivityPage(): Unit = {
    val date = LocalDate.now.minusMonths(2).minusDays(1)
        val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy")
        val formattedString = date.format(formatter)
        System.out.print(formattedString)
        SCAStartPage.assertContent(By.xpath("(//div[@class='govuk-summary-list__row'][dd/a[contains(text(), 'Your tax calculation for the 2022-2023 is now available')]]/dt/strong[contains(text(), '" + formattedString + "')])[1]"), formattedString)
  }

  // ^the user sees PAYE income date on the page$
  def userSeesPAYEIncomeDateOnPage(): Unit = {
    val date = LocalDate.now.minusMonths(2).minusDays(1)
        val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy")
        val formattedString = date.format(formatter)
        System.out.print(formattedString)
        SCAStartPage.assertContent(By.xpath("//div[@class='govuk-summary-list__row'][contains(., \"Central Perk Coffee Ltd paid you PAYE income\")]/descendant::dt/strong[contains(text(), '" + formattedString + "')]"), formattedString)
  }

  // the user sees text latest tax code change date on the page
  def userSeesTextLatestTaxCodeChangeDateOnThePage(): Unit = {
    val date = LocalDate.now.minusMonths(2).minusDays(1)
        val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy")
        val formattedString = date.format(formatter)
        SCAStartPage.assertContent(By.xpath("//div[@class='govuk-summary-list__row'][descendant::dt/strong[normalize-space(text())='" + formattedString + "']][1]/dt/strong"), formattedString)
  }
  
  // ^the user sees text (.*) on the page$
  def userSeesTextOnThePage(value: String): Unit = {
    SCAStartPage.assertContent(By.xpath("//*[contains(text(),'" + value + "')]"), value)
  }

  // ^the user should sees text (.*) on the page$
  def userShouldSeesTextOnThePage(value: String): Unit = {
    SCAStartPage.assertContent(By.xpath("//*[contains(text(),'" + value + "')]"), value)
  }

  // ^the user should see latest tax code text (.*) on the page$
  def userShouldSeesLatestTaxCodeTextOnThePage(value: String): Unit = {
    SCAStartPage.assertContent(By.xpath("//*[contains(a/text(), '" + value + "')]"), value)
  }

  // User should not see (.*) text on activity page$
  def userShouldNotSeeTextOnActivityPage(value: String): Unit = {
    assertTrue(Driver.instance.findElements(By.xpath("//*[contains(text(),'" + value + "')]")).isEmpty)
  }

  // ^the user sees text as a (.*) date on the page$
  def userSeesTextDateOnThePage(value: String): Unit = {
    value match {

          case "HMRC paid you child benefit" =>
            val childbenefitdate = LocalDate.now.minusMonths(1).minusDays(1)
            val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy")
            val formattedString = childbenefitdate.format(formatter)
            SCAStartPage.assertContent(By.xpath("//div[@class='govuk-summary-list__row'][descendant::dt/strong[normalize-space(text())='" + formattedString + "']][1]/dt/strong"), formattedString)

            val childbenefitdate1 = LocalDate.now.minusMonths(2)
            val formattedString1 = childbenefitdate1.format(formatter)
            SCAStartPage.assertContent(By.xpath("//div[@class='govuk-summary-list__row'][descendant::dt/strong[normalize-space(text())='" + formattedString1 + "']][1]/dt/strong"), formattedString1)

            val childbenefitdate2 = LocalDate.now.withMonth(4).withDayOfMonth(6)
            val formattedString2 = childbenefitdate2.format(formatter)
            SCAStartPage.assertContent(By.xpath("//div[@class='govuk-summary-list__row'][descendant::dt/strong[normalize-space(text())='" + formattedString2 + "']][1]/dt/strong"), formattedString2)


            val childbenefitdate3 = LocalDate.now.withMonth(4).withDayOfMonth(5)
            val formattedString3 = childbenefitdate3.format(formatter)
            SCAStartPage.assertContent(By.xpath("//div[@class='govuk-summary-list__row'][descendant::dt/strong[normalize-space(text())='" + formattedString3 + "']][1]/dt/strong"), formattedString3)

            val childbenefitdate4 = LocalDate.now.minusMonths(3).plusDays(1)
            val formattedString4 = childbenefitdate4.format(formatter)
            SCAStartPage.assertContent(By.xpath("//div[@class='govuk-summary-list__row'][descendant::dt/strong[normalize-space(text())='" + formattedString4 + "']][1]/dt/strong"), formattedString4)

        }
  }

}
