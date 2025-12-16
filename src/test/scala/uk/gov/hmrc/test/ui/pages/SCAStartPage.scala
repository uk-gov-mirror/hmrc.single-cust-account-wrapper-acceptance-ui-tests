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

import org.openqa.selenium.support.ui.{ExpectedConditions, FluentWait, WebDriverWait}
import org.openqa.selenium.{By, WebDriver, WebElement}
import org.scalatest.matchers.must.Matchers.*
import uk.gov.hmrc.test.ui.PagePaths.{ActionsPagePaths, FeedbackPagePaths, GGloginPagePaths, SCAStartPagePaths}
import uk.gov.hmrc.test.ui.pages.config.Configuration

import java.time.Duration

object SCAStartPage extends BasePage with GGloginPagePaths with ActionsPagePaths with SCAStartPagePaths with FeedbackPagePaths{
  def verifySCAStartPage() =
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.urlMatches(Configuration.settings.APPROOT))

  def searchResults(name: String) =
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.id(accountName), name))

  def SCAMenuResult(TaxesAndBenefits: String, Messages: String, YourDetails: String) = {
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.id(TaxandBenefits), TaxesAndBenefits))
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.id(Msgs), Messages))
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.id(PersonalDetails), YourDetails))
  }

  def searchResult(PAYE: String, SA: String, StatePension: String) = {
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.id(PayAsYouEarn), PAYE))
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.id(selfAssesment), SA))
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.id(Pension), StatePension))
  }

  def searchNISP(statePensionLink: String, niLink: String) = {
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(statePensionURL), statePensionLink))
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(niURL), niLink))
  }

  def clickOnTaxReturn(): Unit = click(By.linkText(taxReturnLink))

  def clickOnStatePensionSummary(): Unit = click(By.linkText(statePensionLink))

  def verifyStatePensionPageURL() =
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.urlMatches(Configuration.settings.STATEPENSION_PAGE))

  def clickOnNIRecord(): Unit = click(By.linkText(niLink))

  def verifyNIRecordPageURL() =
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.urlMatches(Configuration.settings.NI_PAGE))

  def checkMessage(Message: String) =
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(scaMessage), Message))

  def clickOnMessage(): Unit = click(By.partialLinkText(yourMessage))

  def clickOnBackButton(): Unit = click(By.className(backButton))

  def clickOnFeedback(): Unit = click(By.xpath(FeedbacklinkJenkins))

  def returnToPreviousPage(): Unit = driver.navigate.back()

  def clickOnSignOut(): Unit = click(By.xpath(SignOutLink))

  def verifyFeedbackPageURL() =
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.urlMatches(Configuration.settings.FEEDBACK_PAGE))

  def assertContent(id: By, expectedText: String)(implicit driver: WebDriver): Unit = {
    val wait = new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(15))
      .pollingEvery(Duration.ofMillis(500))
      .ignoring(classOf[org.openqa.selenium.NoSuchElementException])
      .ignoring(classOf[org.openqa.selenium.StaleElementReferenceException])

    val element: WebElement = wait.until(
      ExpectedConditions.visibilityOfElementLocated(id)
    )
    element.getText must be(expectedText)
  }

  def clickOn(by: By): Unit =
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .until(ExpectedConditions.elementToBeClickable(by))
      .click()

  def clickById(id: String): Unit =
    clickOn(By.id(id))

  def SCABTALink(BTA: String) =
    new FluentWait[WebDriver](driver)
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
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(AccountHomeLink), AccountHome))
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(MessagesLink), Messages))
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(CheckProgressLink), CheckProgress))
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(ProfileAndSettingsLink), ProfileAndSettings))
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(SignOutLink), SignOut))

  }

  def confirmActionsResult(message: String) =
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(actionsResult), message))


  def FeedBackLink(FeedbackLink: String) =
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.className("govuk-phase-banner__text"), FeedbackLink))

  def FeedBackLinkJenkins(FeedbackLink: String) =
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(FeedbacklinkJenkins), FeedbackLink))

  def FeedBackPage(Feedbackpage: String) =
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .ignoring(classOf[Nothing])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(pageFeedback), Feedbackpage))

  def clickOnPageNotWorkingProperly(): Unit = click(By.linkText(pageNotWorkingProperlyLink))

  def PageNotWorkingProperlyLink(PageNotWorkingProperly: String) =
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .pollingEvery(Duration.ofMillis(500))
      .ignoring(classOf[org.openqa.selenium.NoSuchElementException])
      .ignoring(classOf[org.openqa.selenium.StaleElementReferenceException])
      .until(
        ExpectedConditions
          .textToBePresentInElementLocated(By.linkText(pageNotWorkingProperlyLink), PageNotWorkingProperly)
      )

  def PageNotWorkingProperly(PageNotWorkingProperly: String) =
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .pollingEvery(Duration.ofMillis(500))
      .ignoring(classOf[org.openqa.selenium.NoSuchElementException])
      .ignoring(classOf[org.openqa.selenium.StaleElementReferenceException])
      .until(
        ExpectedConditions.textToBePresentInElementLocated(By.xpath(pageNotWorkingProperly), PageNotWorkingProperly)
      )

  def moveToTab(): WebDriver = {

    import java.util
    val newTb = new util.ArrayList[String](driver.getWindowHandles)
    driver.switchTo.window(newTb.get(1))
    driver.switchTo.window(newTb.get(1))
  }

  def VerifyServiceName(Serivename: String): Unit = {
    val url = driver.getCurrentUrl
    assert(url.contains(Serivename))

  }

  def AccessibilitystatementLink(Accessibilitystatement: String) : java.lang.Boolean =
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .pollingEvery(Duration.ofMillis(500))
      .ignoring(classOf[org.openqa.selenium.NoSuchElementException])
      .ignoring(classOf[org.openqa.selenium.StaleElementReferenceException])
      .until(
        ExpectedConditions
          .textToBePresentInElementLocated(By.linkText(AccessibilitystatementLink), Accessibilitystatement)
      )

  def clickOnAccessibilitystatementLink(): Unit = click(By.linkText(AccessibilitystatementLink))

  def WrapperSCAFooter(
                        Cookies: String,
                        AccessibilityStatement: String,
                        PrivacyPolicy: String,
                        TermsAndConditions: String,
                        HelpUsingGOVUK: String,
                        Contact: String,
                        WelshLanguage: String
                      ) = {
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .pollingEvery(Duration.ofMillis(500))
      .ignoring(classOf[org.openqa.selenium.NoSuchElementException])
      .ignoring(classOf[org.openqa.selenium.StaleElementReferenceException])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.linkText("Cookies"), Cookies))
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .pollingEvery(Duration.ofMillis(500))
      .ignoring(classOf[org.openqa.selenium.NoSuchElementException])
      .ignoring(classOf[org.openqa.selenium.StaleElementReferenceException])
      .until(
        ExpectedConditions
          .textToBePresentInElementLocated(By.linkText("Accessibility statement"), AccessibilityStatement)
      )
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .pollingEvery(Duration.ofMillis(500))
      .ignoring(classOf[org.openqa.selenium.NoSuchElementException])
      .ignoring(classOf[org.openqa.selenium.StaleElementReferenceException])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.linkText("Privacy policy"), PrivacyPolicy))
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .pollingEvery(Duration.ofMillis(500))
      .ignoring(classOf[org.openqa.selenium.NoSuchElementException])
      .ignoring(classOf[org.openqa.selenium.StaleElementReferenceException])
      .until(
        ExpectedConditions.textToBePresentInElementLocated(By.linkText("Terms and conditions"), TermsAndConditions)
      )
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .pollingEvery(Duration.ofMillis(500))
      .ignoring(classOf[org.openqa.selenium.NoSuchElementException])
      .ignoring(classOf[org.openqa.selenium.StaleElementReferenceException])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.linkText("Help using GOV.UK"), HelpUsingGOVUK))

    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .pollingEvery(Duration.ofMillis(500))
      .ignoring(classOf[org.openqa.selenium.NoSuchElementException])
      .ignoring(classOf[org.openqa.selenium.StaleElementReferenceException])
      .until(ExpectedConditions.textToBePresentInElementLocated(By.linkText("Contact"), Contact))
    new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(Configuration.settings.PAGE_TIMEOUT_SECS))
      .pollingEvery(Duration.ofMillis(500))
      .ignoring(classOf[org.openqa.selenium.NoSuchElementException])
      .ignoring(classOf[org.openqa.selenium.StaleElementReferenceException])
      .until(
        ExpectedConditions.textToBePresentInElementLocated(By.linkText("Rhestr o Wasanaethau Cymraeg"), WelshLanguage)
      )

  }


  def textContentVerify(txts: List[String]) =
    for (txt <- txts)
      assert(driver.getPageSource.contains(txt), s"\n'$txt' text was not found on the page")

  def clickOnCymraeg(Servicename: String): Unit = {
    val xpath = s"//a[@href='/$Servicename/hmrc-frontend/language/cy']"
    val wait = new WebDriverWait(driver, Duration.ofSeconds(10))
    val element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)))
    element.click()
  }

  def clickOnEnglish(Servicename: String): Unit =
    click(By.xpath("//a[@href='/" + Servicename + "/hmrc-frontend/language/en']"))

}
