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

object SCAStartPageSteps {

  def userIsOnSCAStartPage(): Unit = {
    assert(SCAStartPage.verifySCAStartPage())
  }

  def titlePageHeaderContainLogoText(Servicename: String, value: String, locator: String): Unit = {
    (Servicename, SCAStartPage.assertContent(By.xpath("//*[@class='" + locator + "']"), value))
  }

  def userSees(message: String): Unit = {
    assert(SCAStartPage.confirmActionsResult(message))
  }

  def titlePageHeaderContainServiceName(Servicename: String, value: String, locator: String): Unit = {
    (Servicename, SCAStartPage.assertContent(By.xpath("//*[@class='" + locator + "']"), value))
  }

  def titlePageFooterContain(value: String, locator: String): Unit = {
    SCAStartPage.assertContent(By.xpath("//*[@class='" + locator + "']"), value)
  }

  def userShouldSeeSCAUserName(name: String): Unit = {
    assert(SCAStartPage.searchResults(name))
  }

  def clickOnSCALandingPageMenu(text: String, identifier: String, locator: String): Unit = {
    identifier match {
            case "id" => SCAStartPage.clickOn(By.id(locator))
            case _ => throw new RuntimeException("Type of element identifier not found")
          }
  }

  def seeFollowingServicesOnHomePageMenu(TaxesAndBenefits: String, Messages: String, YourDetails: String): Unit = {
    assert(SCAStartPage.SCAMenuResult(TaxesAndBenefits, Messages, YourDetails))
  }

  def seeFollowingTiles(PAYE: String, SA: String, StatePension: String): Unit = {
    assert(SCAStartPage.searchResult(PAYE, SA, StatePension))
  }

  def clickCompleteYourTaxReturnLink(): Unit = {
    SCAStartPage.clickOnTaxReturn()
  }

  def seeTheirTaxDetailsUnder(value: String, locator: String): Unit = {
    SCAStartPage.assertContent(By.xpath("//*[@class='" + locator + "']"), value)
  }

  def seeLinksUnderStatePensionTile(statePensionLink: String, niLink: String): Unit = {
    assert(SCAStartPage.searchNISP(statePensionLink, niLink))
  }

  def selectCheckYourStatePensionSummary(): Unit = {
    SCAStartPage.clickOnStatePensionSummary()
  }

  def directToStatePensionSummaryPage(): Unit = {
    assert(SCAStartPage.verifyStatePensionPageURL())
  }

  def userSee(value: String, locator: String): Unit = {
    SCAStartPage.assertContent(By.xpath("//*[@class='" + locator + "']"), value)
  }

  def returnToYourTaxesAndBenefitPage(): Unit = {
    SCAStartPage.returnToPreviousPage()
  }

  def selectsCheckYourNationalInsuranceRecord(): Unit = {
    SCAStartPage.clickOnNIRecord()
  }

    def directsToNationInsuranceRecordPage(): Unit = {
      assert(SCAStartPage.verifyNIRecordPageURL())
    }

  def directsToNationalInsuranceRecordPage(chocsServiceName: String, locator: String): Unit = {
      SCAStartPage.assertContent(By.xpath("//*[@class='" + locator + "']"), chocsServiceName)
  }

  def seeTheirMessagesUnderMessagesHomePage(Message: String): Unit = {
    assert(SCAStartPage.checkMessage(Message))
  }

  def clickOnAMessage(): Unit = {
    SCAStartPage.clickOnMessage()
  }

  def moreInformationRelatedMessage(Message: String): Unit = {
    SCAStartPage.assertContent(By.xpath("//*[(text()='" + Message + "')]"), Message)
  }

  def returnToPreviousPage(): Unit = {
    SCAStartPage.clickOnBackButton()
  }

  def homepageToReportTechnicalProblems(technicalProblemsLink: String): Unit = {
    SCAStartPage.assertContent(
            By.xpath("//*[contains(text(),'" + technicalProblemsLink + "')]"),
            technicalProblemsLink
          )
  }

  def clickOnNewServiceFeedback(): Unit = {
    SCAStartPage.clickOnFeedback()
  }

  def seeFeedbackPageContainText(feedbackPageText: String): Unit = {
    SCAStartPage.assertContent(By.xpath("//*[(text()='" + feedbackPageText + "')]"), feedbackPageText)
  }

  def returnBackToSCA(): Unit = {
    SCAStartPage.returnToPreviousPage()
  }

  def directedToFeedback(): Unit = {
   assert(SCAStartPage.verifyFeedbackPageURL())
  }

  def seeCustomerFeedbackPage(feedbackPageText: String): Unit ={
    SCAStartPage.assertContent(By.xpath("//*[(text()='" + feedbackPageText + "')]"), feedbackPageText)
  }

  def seeButton(SignOutLink: String): Unit = {
    SCAStartPage.assertContent(By.xpath("//*[contains(text(),'Sign out')]"),
            SignOutLink)
  }

  def clickSignOut(): Unit = {
    SCAStartPage.clickOnSignOut()
  }

}
