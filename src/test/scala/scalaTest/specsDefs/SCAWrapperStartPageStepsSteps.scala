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
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import org.openqa.selenium.{By, NoSuchElementException}
import org.scalatest.matchers.should.Matchers.shouldBe
import play.api.libs.json.Json
import scalaTest.pages.GGLoginPage.*
import scalaTest.pages.{MessagesStub, SCAStartPage}
import scalaTest.utils.MongoConnection
import uk.gov.hmrc.selenium.webdriver.Driver

import java.time.Duration
import scala.util.Random

object SCAWrapperStartPageStepsSteps {

  // SA enrolment is applied
  def SAEnrolmentIsApplied(): Unit = {
    print("SA enrollment is applied")
  }

  // User should see (.*) menu option$
  def menuOption(BTA: String): Unit = {
    assert(SCAStartPage.SCABTALink(BTA))
  }

  // User should see following option in menu (.*), (.*), (.*), (.*) and (.*)$
  def optionsInMenu(AccountHome: String, Messages: String, CheckProgress: String, ProfileAndSettings: String, SignOut: String): Unit = {
    assert(SCAStartPage.WrapperSCAMenu(AccountHome, Messages, CheckProgress, ProfileAndSettings, SignOut))
  }

  // User should also see (.*) icon besides account home menu option$
  def iconBesidesAccountHomeMenu(value: String): Unit = {
    SCAStartPage.assertContent(By.xpath(AccountHomeIcon), value)
  }

  // default content of phase status should be (.*)$
  def defaultContentOfPhaseStatus(Message: String): Unit = {
    SCAStartPage.assertContent(By.xpath(banner), Message)
  }

  // User should able to see (.*) link directly above the footer$
  def linkDirectlyAboveTheFooter(FeedbackLink: String): Unit = {
    assert(SCAStartPage.FeedBackLink(FeedbackLink))
  }

  // User should able to see (.*) link directly above the footer in Jenkins$
  def linkDirectlyAboveTheFooterInJenkins(FeedbackLink: String): Unit = {
    //TODO find out why the xpath is different when the service isn't running locally
        Driver.instance.findElement(By.xpath("//span[@class='govuk-phase-banner__text']")).isDisplayed
        assert(SCAStartPage.FeedBackLinkJenkins(FeedbackLink))
  }

  // User click on feedback link
  def clickOnFeedbackLink(): Unit = {
    SCAStartPage.clickOnFeedback()
  }

  // it should redirect to (.*) page$
  def redirectToPage(Feedbackpage: String): Unit = {
    assert(SCAStartPage.FeedBackPage(Feedbackpage))
  }

  // User should see (.*) directly above the footer$
  def textDirectlyAboveTheFooter(PageNotWorkingProperly: String): Unit = {
    assert(SCAStartPage.PageNotWorkingProperlyLink(PageNotWorkingProperly))
  }

  // User click on Is this page not working properly link
  def clickOnIsThisPageNotWorkingProperly(): Unit = {
    SCAStartPage.clickOnPageNotWorkingProperly()
  }

  // User should redirect to (.*) page$
  def redirectTo(PageNotWorkingProperly: String): Unit = {
    SCAStartPage.moveToTab()
        assert(SCAStartPage.PageNotWorkingProperly(PageNotWorkingProperly))
  }

  // Verify redirected URL should contains service name as (.*)$
  def verifyRedirectedURLContainsServiceName(Servicename: String): Unit = {
    SCAStartPage.VerifyServiceName(Servicename)
  }

  // User should see (.*) link in the footer$
  def seeLinkInTheFooter(Accessibilitystatement: String): Unit = {
    assert(SCAStartPage.AccessibilitystatementLink(Accessibilitystatement))
  }

  // User click on 'Accessibility statement' link
  def clickOnAccessibilityStatement(): Unit = {
    SCAStartPage.clickOnAccessibilitystatementLink()
  }

  // User should see (.*), (.*), (.*), (.*), (.*), (.*) and (.*) links in the footer$
  def userShouldSee(Cookies: String,
                    AccessibilityStatement: String,
                    PrivacyPolicy: String,
                    TermsAndConditions: String,
                    HelpUsingGOVUK: String,
                    Contact: String,
                    WelshLanguage: String): Unit = {
    SCAStartPage.WrapperSCAFooter(
            Cookies,
            AccessibilityStatement,
            PrivacyPolicy,
            TermsAndConditions,
            HelpUsingGOVUK,
            Contact,
            WelshLanguage
          )
  }

  // ^the user sees relevant content in welsh language$
  def seesRelevantContentInWelshLanguage(): Unit = {
    val texts =
          List("Hafan y cyfrif", "Negeseuon", "Gwirio cynnydd", "Proffil a gosodiadau", "Allgofnodi", "Cyfrif treth busnes", "Cyfrif Cwsmer Sengl")
        SCAStartPage.textContentVerify(texts)
        val othertexts =
          List("Cwcis ar wasanaethau CThEF", "Mae‘r holl gynnwys ar gael o dan", "Drwydded Llywodraeth Agored v3.0", ", oni nodir yn wahanol", "A yw’r dudalen hon yn gweithio’n iawn? (yn agor tab newydd)", "Cwcis", "Polisi preifatrwydd", "Telerau ac Amodau", "Help wrth ddefnyddio GOV.UK", "Cysylltu")
        SCAStartPage.textContentVerify(othertexts)
  }

  // the user clicks on 'Cymraeg' welesh language link of (.*)$
  def clickOnCymraeWeleshLanguage(Servicename: String): Unit = {
    SCAStartPage.clickOnCymraeg(Servicename)
  }

  // the user clicks on 'English' language link of (.*)$
  def clickOnEnglishLanguage(Servicename: String): Unit = {
    SCAStartPage.clickOnEnglish(Servicename)
  }

  // the user sees relevant content in English language
  def seesRelevantContentInEnglishLanguage(): Unit = {
    val texts = List("Single Customer Account", "Account home", "Messages", "Check progress", "Profile and settings", "Business tax account", "Sign out")

        SCAStartPage.textContentVerify(texts)
  }

  // ^the user can not click language '(.*)' link$
  def cannotClickLanguageLink(linkName: String): Unit = {
    intercept[NoSuchElementException] {
          linkName match {
            case "Cymraeg" =>
              assert(
                !Driver.instance
                  .findElement(By.cssSelector("a[href*='/single-customer-account/hmrc-frontend/language/cy']"))
                  .isEnabled,
                s"language $linkName is present on the page"
              )

            case "English" =>
              assert(
                !Driver.instance
                  .findElement(By.cssSelector("a[href*='/single-customer-account/hmrc-frontend/language/en']"))
                  .isEnabled,
                s"language $linkName is present on the page"
              )
          }
        }
  }

  // ^the user clicks on (.*) menu$
  def clickOnMenu(linkName: String): Unit = {
    linkName match {
          case "Account home" =>
            Driver.instance.findElement(By.partialLinkText(linkName)).click()
          case "Messages" =>
            Driver.instance.findElement(By.partialLinkText(linkName)).click()
          case "Check progress" =>
            Driver.instance.findElement(By.partialLinkText(linkName)).click()
          case "Profile and settings" =>
            Driver.instance.findElement(By.partialLinkText(linkName)).click()
          case "Sign out" =>
            Driver.instance.findElement(By.partialLinkText(linkName)).click()

        }
  }

  // ^User clicks on (.*) footer link$
  def clicksOnFooterLink(linkName: String): Unit = {
    linkName match {
          case "Cookies" =>
            Driver.instance.findElement(By.partialLinkText(linkName)).click()
          case "Accessibility statement" =>
            Driver.instance.findElement(By.partialLinkText(linkName)).click()
          case "Privacy policy" =>
            Driver.instance.findElement(By.partialLinkText(linkName)).click()
          case "Terms and conditions" =>
            Driver.instance.findElement(By.partialLinkText(linkName)).click()
          case "Help using GOV.UK" =>
            Driver.instance.findElement(By.partialLinkText(linkName)).click()
          case "Contact" =>
            Driver.instance.findElement(By.partialLinkText(linkName)).click()
          case "Rhestr o Wasanaethau Cymraeg" =>
            Driver.instance.findElement(By.partialLinkText(linkName)).click()

        }
  }

  // user should redirects to track page$
  def redirectsToTrackPage(): Unit = {
    val trackURL = Driver.instance.getCurrentUrl
        trackURL.contains("/track")
  }

  // user should redirect to (.*) page$
  def redirectsToPage(locator: String): Unit = {
    Driver.instance.findElement(By.xpath("//*[contains(text(),'" + locator + "')]")).isDisplayed
  }

  // user should go through tax letter journey and redirect to Account home page
  def taxLetterJourneyRedirectToAccountHomePage(): Unit = {
    val wait = new WebDriverWait(Driver.instance, Duration.ofSeconds(50))

        if (Driver.instance.getCurrentUrl.contains("/personal-account")) {
          wait.until(
            ExpectedConditions.or(
              ExpectedConditions.urlContains("/paperless/survey/optin-declined?"),
              ExpectedConditions.urlContains("/personal-account")
            )
          )
          Driver.instance.findElement(By.xpath("//*[contains(text(),'Account home')]")).isDisplayed
          Driver.instance.navigate().back()
        }

        if (Driver.instance.getCurrentUrl.contains("paperless/optin?")) {
          wait.until(
            ExpectedConditions.or(
              ExpectedConditions.urlContains("paperless/optin?"),
              ExpectedConditions.urlContains("/personal-account")
            )
          )
          Driver.instance.findElement(By.id("sps-opt-in-2")).click()
          Driver.instance.findElement(By.id("submitEmailButton")).click()
          wait.until(ExpectedConditions.urlContains("/paperless/optout-confirmation?"))
          Driver.instance.findElement(By.id("submitEmailButton")).click()
          Driver.instance.findElement(By.xpath("//*[contains(text(),'Account home')]")).isDisplayed
          Driver.instance.navigate().back()
          Driver.instance.navigate().back()
          Driver.instance.navigate().back()
        }
  }

  // User should see cookies banner
  def seeCookiesBanner(): Unit = {
    Driver.instance.findElement(By.xpath("//*[contains(text(),'Accept additional cookies')]")).isDisplayed
  }

  // User should able to close it
  def closeCookies(): Unit = {
    Driver.instance.findElement(By.xpath("//*[contains(text(),'Accept additional cookies')]")).click()
        Driver.instance.findElement(By.xpath("//*[contains(text(),'Hide cookies message')]")).click()
  }

  // User should not see Business tax account menu option
  def notSeeBusinessTaxAccount(): Unit = {
    assertTrue(Driver.instance.findElements(By.xpath("//*[contains(text(),'Business tax account')]")).isEmpty)
  }

  // The message collection is dropped from mongo database
  def messageCollectionDroppedFromMongoDatabase(): Unit = {
    MongoConnection.dropCollection("message", "secure-message")
  }

  // A message is posted to the messages API in the (.*) environment$
  def messagePostedToTheMessagesAPI(env: String): Unit = {
    val id = Random.alphanumeric.filter(_.isDigit).take(14).mkString
        val subject = Random.alphanumeric.filter(_.isLetter).take(4).mkString
        val stubRequestBody =
          s"""{
   "externalRef":{
      "id":"${id}",
      "source":"gmc"
   },
   "recipient":{
      "taxIdentifier":{
         "name":"nino",
         "value":"ER872414B"
      },
      "name":{
         "title":"Mr",
         "forename":"BOB",
         "secondForename":"Harry",
         "surname":"JONES",
         "honours":"OBE"
      },
      "email":"someEmail@test.com"
   },
   "messageType":"mailout-batch",
   "subject":"Reminder to file a Self Assessment return $subject",
   "content":"Some base64-encoded HTML",
   "validFrom":"2017-02-14",
   "alertQueue":"DEFAULT",
   "details":{
      "formId":"SA300",
      "issueDate":"2017-02-14",
      "statutory":true,
      "paperSent":false,
      "batchId":"1234567",
      "sourceData": "RnVjaw==",
      "replyTo": "5c0a57826b00006b0032d0db"
   }
}""".stripMargin
        MessagesStub.postMessagesStub(Json.parse(stubRequestBody), env)
  }

  // ^the user should see (.*) as the number of messages$
  def numberOfMessages(messages: String): Unit = {
    Driver.instance.navigate().refresh()
        Driver.instance.findElement(By.partialLinkText("Messages")).click()
        val actualMessagesText =
          Driver.instance.findElement(By.className("hmrc-notification-badge")).getText

        actualMessagesText shouldBe messages
  }

  // the user should see the message on the page after clicking the message
  def seeTheMessageOnThePage(): Unit = {
    Driver.instance.findElement(By.xpath("//*[contains(text(),'Messages')]")).click()
        Driver.instance.findElement(By.xpath("//span[@class='govuk-!-font-weight-bold black-text govuk-body']")).click()
        Driver.instance.findElement(By.xpath("//p[@class='message_time faded-text--small govuk-body']")).isDisplayed
        Driver.instance.findElement(By.id("back-link")).click()
  }

  // ^the user sees services relevant content in welsh language$
  def relevantContentInWelshLanguage(): Unit = {
    val texts =
          List("Hafan y cyfrif", "Negeseuon", "Gwirio cynnydd", "Proffil a gosodiadau", "Allgofnodi", "Cyfrif treth busnes", "Cyfrif treth personol")
        SCAStartPage.textContentVerify(texts)
        val othertexts =
          List("Cwcis ar wasanaethau CThEM", "Mae‘r holl gynnwys ar gael o dan", "Drwydded Llywodraeth Agored v3.0", ", oni nodir yn wahanol", "A yw’r dudalen hon yn gweithio’n iawn? (yn agor tab newydd)", "Cwcis", "Polisi preifatrwydd", "Telerau ac Amodau", "Help wrth ddefnyddio GOV.UK", "Cysylltu")
        SCAStartPage.textContentVerify(othertexts)
  }

  // ^the user sees services relevant content of chocs in welsh language$
  def relevantContentOfChocsInWelshLanguage(): Unit = {
    val texts =
          List("Hafan y cyfrif", "Negeseuon", "Gwirio cynnydd", "Proffil a gosodiadau", "Allgofnodi", "Cyfrif treth busnes", "Cyfrif treth personol")
        SCAStartPage.textContentVerify(texts)
        val othertexts =
          List("Cwcis ar wasanaethau CThEM", "Mae‘r holl gynnwys ar gael o dan", "Drwydded Llywodraeth Agored v3.0", ", oni nodir yn wahanol", "Cwcis", "Polisi preifatrwydd", "Telerau ac Amodau", "Help wrth ddefnyddio GOV.UK", "Cysylltu")
        SCAStartPage.textContentVerify(othertexts)
  }

  // the user sees services relevant content in English language
  def relevantContentInEnglishLanguage(): Unit = {
    val texts = List("Personal tax account", "Account home", "Messages", "Check progress", "Profile and settings", "Business tax account", "Sign out")

        SCAStartPage.textContentVerify(texts)
  }

}
