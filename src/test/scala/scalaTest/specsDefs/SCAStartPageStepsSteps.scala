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

import org.openqa.selenium.By
import scalaTest.pages.*

object SCAStartPageStepsSteps {

  // User is on SCA start page$
  def userIsOnSCAStartPage(): Unit = {
    assert(SCAStartPage.verifySCAStartPage())
  }

  // User should see (.*) title page Header contain logo text as (.*) in (.*)$
  def titlePageHeaderContainLogoText(Servicename: String, value: String, locator: String): Unit = {
    (Servicename, SCAStartPage.assertContent(By.xpath("//*[@class='" + locator + "']"), value))
  }

  // ^User sees (.*)$
  def userSees(message: String): Unit = {
    assert(SCAStartPage.confirmActionsResult(message))
  }

  // User should see (.*) title page Header contain service name as (.*) in (.*)$
  def titlePageHeaderContainServiceName(Servicename: String, value: String, locator: String): Unit = {
    (Servicename, SCAStartPage.assertContent(By.xpath("//*[@class='" + locator + "']"), value))
  }

  // User should see SCA title page footer contain (.*) in (.*)$
  def titlePageFooterContain(value: String, locator: String): Unit = {
    SCAStartPage.assertContent(By.xpath("//*[@class='" + locator + "']"), value)
  }

  // User should see SCA username as (.*)$
  def userShouldSeeSCAUserName(name: String): Unit = {
    assert(SCAStartPage.searchResults(name))
  }

  // User click (.*) on SCA landing page menu by (.*) (.*)$
  def clickOnSCALandingPageMenu(text: String, identifier: String, locator: String): Unit = {
    identifier match {
            case "id" => SCAStartPage.clickOn(By.id(locator))
            case _ => throw new RuntimeException("Type of element identifier not found")
          }
  }

  // User should see following services on home page menu (.*), (.*) and (.*)$
  def seeFollowingServicesOnHomePageMenu(TaxesAndBenefits: String, Messages: String, YourDetails: String): Unit = {
    assert(SCAStartPage.SCAMenuResult(TaxesAndBenefits, Messages, YourDetails))
  }

  // User should see following tiles on the page (.*), (.*) and (.*)$
  def seeFollowingTiles(PAYE: String, SA: String, StatePension: String): Unit = {
    assert(SCAStartPage.searchResult(PAYE, SA, StatePension))
  }

  // User click 'Complete your tax return' link under Self Assessment tile$
  def clickCompleteYourTaxReturnLink(): Unit = {
    SCAStartPage.clickOnTaxReturn()
  }

  // The user can see their tax details under (.*) in (.*)$
  def seeTheirTaxDetailsUnder(value: String, locator: String): Unit = {
    SCAStartPage.assertContent(By.xpath("//*[@class='" + locator + "']"), value)
  }

  // User should see following links (.*) and (.*) under State Pension tile$
  def seeLinksUnderStatePensionTile(statePensionLink: String, niLink: String): Unit = {
    assert(SCAStartPage.searchNISP(statePensionLink, niLink))
  }

  // User selects 'Check your State Pension summary' link in State Pension tile$
  def selectCheckYourStatePensionSummary(): Unit = {
    SCAStartPage.clickOnStatePensionSummary()
  }

  // System directs the user to State Pension summary page$""")(() =>
  def directToStatePensionSummaryPage(): Unit = {
    assert(SCAStartPage.verifyStatePensionPageURL())
  }

  def userSee(value: String, locator: String): Unit = {
    SCAStartPage.assertContent(By.xpath("//*[@class='" + locator + "']"), value)
  }

  // The user should be able to return to 'Your taxes and benefits' page$
  def returnToYourTaxesAndBenefitPage(): Unit = {
    SCAStartPage.returnToPreviousPage()
  }

  // User selects 'Check your National Insurance record' link in State Pension tile$
  def selectsCheckYourNationalInsuranceRecord(): Unit = {
    SCAStartPage.clickOnNIRecord()
  }

  // System directs the user to National Insurance record page$""")(() =>
    def directsToNationInsuranceRecordPage(): Unit = {
      assert(SCAStartPage.verifyNIRecordPageURL())
    }

  // User should see CHOCS title page Header contain service name as (.*) in (.*)$
  def directsToNationalInsuranceRecordPage(chocsServiceName: String, locator: String): Unit = {
      SCAStartPage.assertContent(By.xpath("//*[@class='" + locator + "']"), chocsServiceName)
  }

  // The user can see their messages under messages home page (.*)$
  def seeTheirMessagesUnderMessagesHomePage(Message: String): Unit = {
    assert(SCAStartPage.checkMessage(Message))
  }

  // User click on a Message$
  def clickOnAMessage(): Unit = {
    SCAStartPage.clickOnMessage()
  }

  // More information related to that message can be seen under message focus page (.*)$
  def moreInformationRelatedMessage(Message: String): Unit = {
    SCAStartPage.assertContent(By.xpath("//*[(text()='" + Message + "')]"), Message)
  }

  // The user should be able to return to previous page$
  def returnToPreviousPage(): Unit = {
    SCAStartPage.clickOnBackButton()
  }

  // User should see if there is a link present on homepage to report Technical Problems (.*)$
  def homepageToReportTechnicalProblems(technicalProblemsLink: String): Unit = {
    SCAStartPage.assertContent(
            By.xpath("//*[contains(text(),'" + technicalProblemsLink + "')]"),
            technicalProblemsLink
          )
  }

  // User click on new service feedback link$
  def clickOnNewServiceFeedback(): Unit = {
    SCAStartPage.clickOnFeedback()
  }

  // User should see feedback page contain text as (.*)$
  def seeFeedbackPageContainText(feedbackPageText: String): Unit = {
    SCAStartPage.assertContent(By.xpath("//*[(text()='" + feedbackPageText + "')]"), feedbackPageText)
  }

  // User should return back to SCA home page$
  def returnBackToSCA(): Unit = {
    SCAStartPage.returnToPreviousPage()
  }

  // User should get re-directed to customer feedback page$
  def directedToFeedback(): Unit = {
   assert(SCAStartPage.verifyFeedbackPageURL())
  }

  // User should see customer feedback page contain text as (.*)$
  def seeCustomerFeedbackPage(feedbackPageText: String): Unit ={
    SCAStartPage.assertContent(By.xpath("//*[(text()='" + feedbackPageText + "')]"), feedbackPageText)
  }

  // User should see (.*) button$
  def seeButton(SignOutLink: String): Unit = {
    SCAStartPage.assertContent(By.xpath("//*[contains(text(),'Sign out')]"),
            SignOutLink)
  }

  // User clicks on Sign out button
  def clickSignOut(): Unit = {
    SCAStartPage.clickOnSignOut()
  }

}
