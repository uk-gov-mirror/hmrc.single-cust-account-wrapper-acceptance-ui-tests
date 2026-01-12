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

import org.openqa.selenium.support.ui.{ExpectedConditions, FluentWait}
import org.openqa.selenium.{By, WebDriver}
import org.scalatest.matchers.must.Matchers.*
import scalaTest.PagePaths.{FeedbackPagePaths, GGloginPagePaths, SCAStartPagePaths}
import scalaTest.pages.config.Configuration
import uk.gov.hmrc.selenium.webdriver.Driver

import java.time.Duration

object ActivityPage
    extends BasePage
    with GGloginPagePaths
    with SCAStartPagePaths
    with FeedbackPagePaths {
  
  def verifySCAStartPage() =
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.urlMatches(Configuration.settings.ACTIVITY))

  def searchResults(name: String) =
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.id(accountName), name))

  def SCAMenuResult(TaxesAndBenefits: String, Messages: String, YourDetails: String) = {
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.id(TaxandBenefits), TaxesAndBenefits))
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.id(Msgs), Messages))
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.id(PersonalDetails), YourDetails))
  }

  def searchResult(PAYE: String, SA: String, StatePension: String) = {
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.id(PayAsYouEarn), PAYE))
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.id(selfAssesment), SA))
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.id(Pension), StatePension))
  }

  def searchNISP(statePensionLink: String, niLink: String) = {
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(statePensionURL), statePensionLink))
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(niURL), niLink))
  }

  def clickOnTaxReturn(): Unit = Driver.instance.findElement(By.linkText(taxReturnLink)).click()

  def clickOnStatePensionSummary(): Unit = Driver.instance.findElement(By.linkText(statePensionLink)).click()

  def verifyStatePensionPageURL() =
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.urlMatches(Configuration.settings.STATEPENSION_PAGE))

  def clickOnNIRecord(): Unit = Driver.instance.findElement(By.linkText(niLink)).click()

  def verifyNIRecordPageURL() =
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.urlMatches(Configuration.settings.NI_PAGE))

  def checkMessage(Message: String) =
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(scaMessage), Message))

  def clickOnMessage(): Unit = Driver.instance.findElement(By.partialLinkText(yourMessage)).click()

  def clickOnBackButton(): Unit = Driver.instance.findElement(By.className(backButton)).click()

  def clickOnFeedback(): Unit = Driver.instance.findElement(By.xpath(FeedbacklinkJenkins)).click()

  def returnToPreviousPage(): Unit = Driver.instance.navigate.back()

  def verifyFeedbackPageURL() =
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.urlMatches(Configuration.settings.FEEDBACK_PAGE))

  def assertContent(id: By, expectedText: String) = Driver.instance.findElement(id).getText must be(expectedText)

  def clickOn(by: By): Unit =
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .until(ExpectedConditions.elementToBeClickable(by))
      .click()

  def clickById(id: String): Unit =
    clickOn(By.id(id))

  def SCABTALink(BTA: String) =
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(BusinessAccountLink), BTA))

  def WrapperSCAMenu(
    AccountHome: String,
    Messages: String,
    CheckProgress: String,
    ProfileAndSettings: String,
    SignOut: String
  ) = {
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(AccountHomeLink), AccountHome))
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(MessagesLink), Messages))
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(CheckProgressLink), CheckProgress))
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(ProfileAndSettingsLink), ProfileAndSettings))
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(SignOutLink), SignOut))

  }

  def FeedBackLink(FeedbackLink: String) =
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.className("govuk-phase-banner__text"), FeedbackLink))

  def FeedBackLinkJenkins(FeedbackLink: String) =
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(FeedbacklinkJenkins), FeedbackLink))

  def FeedBackPage(Feedbackpage: String) =
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(pageFeedback), Feedbackpage))

  def clickOnPageNotWorkingProperly(): Unit = Driver.instance.findElement(By.linkText(pageNotWorkingProperlyLink)).click()

  def PageNotWorkingProperlyLink(PageNotWorkingProperly: String) =
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(
        ExpectedConditions
          .textToBePresentInElementLocated(By.linkText(pageNotWorkingProperlyLink), PageNotWorkingProperly)
      )

  def PageNotWorkingProperly(PageNotWorkingProperly: String) =
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(
        ExpectedConditions.textToBePresentInElementLocated(By.xpath(pageNotWorkingProperly), PageNotWorkingProperly)
      )

  def moveToTab(): WebDriver = {

    import java.util
    val newTb = new util.ArrayList[String](Driver.instance.getWindowHandles)
    Driver.instance.switchTo.window(newTb.get(1))
    Driver.instance.switchTo.window(newTb.get(1))
  }

  def VerifyServiceName(Serivename: String): Unit = {
    val url = Driver.instance.getCurrentUrl
    assert(url.contains(Serivename))

  }

  def AccessibilitystatementLink(Accessibilitystatement: String): java.lang.Boolean =
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(
        ExpectedConditions
          .textToBePresentInElementLocated(By.linkText(AccessibilitystatementLink), Accessibilitystatement)
      )

  def clickOnAccessibilitystatementLink(): Unit = Driver.instance.findElement(By.linkText(AccessibilitystatementLink)).click()

  def WrapperSCAFooter(
    Cookies: String,
    AccessibilityStatement: String,
    PrivacyPolicy: String,
    TermsAndConditions: String,
    HelpUsingGOVUK: String,
    Contact: String,
    WelshLanguage: String
  ) = {
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.linkText("Cookies"), Cookies))
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(
        ExpectedConditions
          .textToBePresentInElementLocated(By.linkText("Accessibility statement"), AccessibilityStatement)
      )
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.linkText("Privacy policy"), PrivacyPolicy))
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(
        ExpectedConditions.textToBePresentInElementLocated(By.linkText("Terms and conditions"), TermsAndConditions)
      )
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.linkText("Help using GOV.UK"), HelpUsingGOVUK))

    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.linkText("Contact"), Contact))
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(
        ExpectedConditions.textToBePresentInElementLocated(By.linkText("Rhestr o Wasanaethau Cymraeg"), WelshLanguage)
      )

  }

  def textContentVerify(txts: List[String]) =
    for (txt <- txts)
      assert(Driver.instance.getPageSource.contains(txt), s"\n'$txt' text was not found on the page")

  def clickOnCymraeg(Servicename: String): Unit = Driver.instance.findElement(By.xpath("//a[@href='/"+Servicename+"/hmrc-frontend/language/cy']")).click()

  def clickOnEnglish(Servicename: String): Unit =
    Driver.instance.findElement(By.xpath("//a[@href='/"+Servicename+"/hmrc-frontend/language/en']")).click()

}