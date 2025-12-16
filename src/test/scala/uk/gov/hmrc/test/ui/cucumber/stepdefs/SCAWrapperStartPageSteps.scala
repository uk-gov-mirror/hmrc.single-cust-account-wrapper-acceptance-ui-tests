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

package uk.gov.hmrc.test.ui.cucumber.stepdefs

import org.junit.Assert.assertTrue
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import org.openqa.selenium.{By, NoSuchElementException}
import play.api.libs.json.Json
import uk.gov.hmrc.test.ui.PagePaths.GGloginPagePaths
import uk.gov.hmrc.test.ui.pages.GGLoginPage.{AccountHomeIcon, banner}
import uk.gov.hmrc.test.ui.pages.{MessagesStub, SCAStartPage}
import uk.gov.hmrc.test.ui.utils.MongoConnection

import java.time.Duration
import scala.util.Random

class SCAWrapperStartPageSteps extends BaseStepDef with GGloginPagePaths {

  When("""SA enrolment is applied""") { () =>
    print("SA enrollment is applied")
  }

  Then("""User should see (.*) menu option$""") { (BTA: String) =>
    assert(SCAStartPage.SCABTALink(BTA))
  }

  Then("""User should see following option in menu (.*), (.*), (.*), (.*) and (.*)$""") {
    (AccountHome: String, Messages: String, CheckProgress: String, ProfileAndSettings: String, SignOut: String) =>
      assert(SCAStartPage.WrapperSCAMenu(AccountHome, Messages, CheckProgress, ProfileAndSettings, SignOut))
  }

  Then("""User should also see (.*) icon besides account home menu option$""") { (value: String) =>
    SCAStartPage.assertContent(By.xpath(AccountHomeIcon), value)
  }

  Then("""default content of phase status should be (.*)$""") { (Message: String) =>
    SCAStartPage.assertContent(By.xpath(banner), Message)
  }
  Then("""User should able to see (.*) link directly above the footer$""") { (FeedbackLink: String) =>
    assert(SCAStartPage.FeedBackLink(FeedbackLink))
  }
  Then("""User should able to see (.*) link directly above the footer in Jenkins$""") { (FeedbackLink: String) =>
    //TODO find out why the xpath is different when the service isn't running locally
    findBy(By.xpath("//span[@class='govuk-phase-banner__text']")).isDisplayed
    assert(SCAStartPage.FeedBackLinkJenkins(FeedbackLink))
  }
  When("""User click on feedback link""") { () =>
    SCAStartPage.clickOnFeedback()
  }
  Then("""it should redirect to (.*) page$""") { (Feedbackpage: String) =>
    assert(SCAStartPage.FeedBackPage(Feedbackpage))
  }
  Then("""User should see (.*) directly above the footer$""") { (PageNotWorkingProperly: String) =>
    assert(SCAStartPage.PageNotWorkingProperlyLink(PageNotWorkingProperly))
  }
  When("""User click on Is this page not working properly link""") { () =>
    SCAStartPage.clickOnPageNotWorkingProperly()
  }
  Then("""User should redirect to (.*) page$""") { (PageNotWorkingProperly: String) =>
    SCAStartPage.moveToTab()
    assert(SCAStartPage.PageNotWorkingProperly(PageNotWorkingProperly))
  }
  Then("""Verify redirected URL should contains service name as (.*)$""") { (Servicename: String) =>
    SCAStartPage.VerifyServiceName(Servicename)


  }
  Then("""User should see (.*) link in the footer$""") { (Accessibilitystatement: String) =>
    assert(SCAStartPage.AccessibilitystatementLink(Accessibilitystatement))

  }
  When("""User click on 'Accessibility statement' link""") { () =>
    SCAStartPage.clickOnAccessibilitystatementLink()
  }
  Then("""User should see (.*), (.*), (.*), (.*), (.*), (.*) and (.*) links in the footer$""") {
    (
      Cookies: String,
      AccessibilityStatement: String,
      PrivacyPolicy: String,
      TermsAndConditions: String,
      HelpUsingGOVUK: String,
      Contact: String,
      WelshLanguage: String
    ) =>
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

  And("""^the user sees relevant content in welsh language$""") { () =>
    val texts =
      List("Hafan y cyfrif", "Negeseuon", "Gwirio cynnydd", "Proffil a gosodiadau", "Allgofnodi", "Cyfrif treth busnes", "Cyfrif Cwsmer Sengl")
    SCAStartPage.textContentVerify(texts)
    val othertexts =
      List("Cwcis ar wasanaethau CThEF", "Mae‘r holl gynnwys ar gael o dan", "Drwydded Llywodraeth Agored v3.0", ", oni nodir yn wahanol", "A yw’r dudalen hon yn gweithio’n iawn? (yn agor tab newydd)", "Cwcis", "Polisi preifatrwydd", "Telerau ac Amodau", "Help wrth ddefnyddio GOV.UK", "Cysylltu")
    SCAStartPage.textContentVerify(othertexts)
  }

  When("""the user clicks on 'Cymraeg' welesh language link of (.*)$""") { (Servicename: String) =>
    SCAStartPage.clickOnCymraeg(Servicename)

  }
  When("""the user clicks on 'English' language link of (.*)$""") { (Servicename: String) =>
    SCAStartPage.clickOnEnglish(Servicename)

  }

  And("""the user sees relevant content in English language""") { () =>
    val texts = List("Single Customer Account", "Account home", "Messages", "Check progress", "Profile and settings", "Business tax account", "Sign out")

    SCAStartPage.textContentVerify(texts)
  }

  And("""^the user can not click language '(.*)' link$""") { (linkName: String) =>
    intercept[NoSuchElementException] {
      linkName match {
        case "Cymraeg" =>
          assert(
            !driver
              .findElement(By.cssSelector("a[href*='/single-customer-account/hmrc-frontend/language/cy']"))
              .isEnabled,
            s"language $linkName is present on the page"
          )

        case "English" =>
          assert(
            !driver
              .findElement(By.cssSelector("a[href*='/single-customer-account/hmrc-frontend/language/en']"))
              .isEnabled,
            s"language $linkName is present on the page"
          )
      }
    }
  }

  When("""^the user clicks on (.*) menu$""") { (linkName: String) =>
    linkName match {
      case "Account home" =>
        click(By.partialLinkText(linkName))
      case "Messages" =>
        click(By.partialLinkText(linkName))
      case "Check progress" =>
        click(By.partialLinkText(linkName))
      case "Profile and settings" =>
        click(By.partialLinkText(linkName))
      case "Sign out" =>
        click(By.partialLinkText(linkName))

    }
  }

  When("""^User clicks on (.*) footer link$""") { (linkName: String) =>
    linkName match {
      case "Cookies" =>
        click(By.partialLinkText(linkName))
      case "Accessibility statement" =>
        click(By.partialLinkText(linkName))
      case "Privacy policy" =>
        click(By.partialLinkText(linkName))
      case "Terms and conditions" =>
        click(By.partialLinkText(linkName))
      case "Help using GOV.UK" =>
        click(By.partialLinkText(linkName))
      case "Contact" =>
        click(By.partialLinkText(linkName))
      case "Rhestr o Wasanaethau Cymraeg" =>
        click(By.partialLinkText(linkName))

    }
  }

  Then("""user should redirects to track page$""") { () =>

    val trackURL = driver.getCurrentUrl
    trackURL.contains("/track")
  }


  Then("""user should redirect to (.*) page$""") { (locator: String) =>
    findBy(By.xpath("//*[contains(text(),'" + locator + "')]")).isDisplayed
  }


  Then("""user should go through tax letter journey and redirect to Account home page""") { () =>
    val wait = new WebDriverWait(driver, Duration.ofSeconds(50))

    if (driver.getCurrentUrl.contains("/personal-account")) {
      wait.until(
        ExpectedConditions.or(
          ExpectedConditions.urlContains("/paperless/survey/optin-declined?"),
          ExpectedConditions.urlContains("/personal-account")
        )
      )
      findBy(By.xpath("//*[contains(text(),'Account home')]")).isDisplayed
      driver.navigate().back()
    }

    if (driver.getCurrentUrl.contains("paperless/optin?")) {
      wait.until(
        ExpectedConditions.or(
          ExpectedConditions.urlContains("paperless/optin?"),
          ExpectedConditions.urlContains("/personal-account")
        )
      )
      click(By.id("sps-opt-in-2"))
      click(By.id("submitEmailButton"))
      wait.until(ExpectedConditions.urlContains("/paperless/optout-confirmation?"))
      click(By.id("submitEmailButton"))
      findBy(By.xpath("//*[contains(text(),'Account home')]")).isDisplayed
      driver.navigate().back()
      driver.navigate().back()
      driver.navigate().back()
    }

  }

  Then("""User should see cookies banner""") { () =>
    findBy(By.xpath("//*[contains(text(),'Accept additional cookies')]")).isDisplayed

  }
  Then("""User should able to close it""") { () =>
    click(By.xpath("//*[contains(text(),'Accept additional cookies')]"))
    click(By.xpath("//*[contains(text(),'Hide cookies message')]"))
  }

  Then("""User should not see Business tax account menu option""") { () =>
    assertTrue(driver.findElements(By.xpath("//*[contains(text(),'Business tax account')]")).isEmpty)
  }

  Then("""The message collection is dropped from mongo database""") { () =>
    MongoConnection.dropCollection("message", "secure-message")
  }

  And("""A message is posted to the messages API in the (.*) environment$""") { (env: String) =>

    val id = Random.alphanumeric.filter(_.isDigit).take(14).mkString
    val subject = Random.alphanumeric.filter(_.isLetter).take(4).mkString
    val stubRequestBody =
      s"""{
         |   "externalRef":{
         |      "id":"${id}",
         |      "source":"gmc"
         |   },
         |   "recipient":{
         |      "taxIdentifier":{
         |         "name":"nino",
         |         "value":"ER872414B"
         |      },
         |      "name":{
         |         "title":"Mr",
         |         "forename":"BOB",
         |         "secondForename":"Harry",
         |         "surname":"JONES",
         |         "honours":"OBE"
         |      },
         |      "email":"someEmail@test.com"
         |   },
         |   "messageType":"mailout-batch",
         |   "subject":"Reminder to file a Self Assessment return $subject",
         |   "content":"Some base64-encoded HTML",
         |   "validFrom":"2017-02-14",
         |   "alertQueue":"DEFAULT",
         |   "details":{
         |      "formId":"SA300",
         |      "issueDate":"2017-02-14",
         |      "statutory":true,
         |      "paperSent":false,
         |      "batchId":"1234567",
         |      "sourceData": "RnVjaw==",
         |      "replyTo": "5c0a57826b00006b0032d0db"
         |   }
         |}""".stripMargin
    MessagesStub.postMessagesStub(Json.parse(stubRequestBody), env)
  }


  And("""^the user should see (.*) as the number of messages$""") { (messages: String) =>
    driver.navigate().refresh()
    click(By.partialLinkText("Messages"))
    val actualMessagesText =
      findBy(By.className("hmrc-notification-badge")).getText

    actualMessagesText shouldBe messages
  }

  And("""the user should see the message on the page after clicking the message""") { () =>
    click(By.xpath("//*[contains(text(),'Messages')]"))
    click(By.xpath("//span[@class='govuk-!-font-weight-bold black-text govuk-body']"))
    findBy(By.xpath("//p[@class='message_time faded-text--small govuk-body']")).isDisplayed
    click(By.id("back-link"))

  }


  And("""^the user sees services relevant content in welsh language$""") { () =>
    val texts =
      List("Hafan y cyfrif", "Negeseuon", "Gwirio cynnydd", "Proffil a gosodiadau", "Allgofnodi", "Cyfrif treth busnes", "Cyfrif treth personol")
    SCAStartPage.textContentVerify(texts)
    val othertexts =
      List("Cwcis ar wasanaethau CThEM", "Mae‘r holl gynnwys ar gael o dan", "Drwydded Llywodraeth Agored v3.0", ", oni nodir yn wahanol", "A yw’r dudalen hon yn gweithio’n iawn? (yn agor tab newydd)", "Cwcis", "Polisi preifatrwydd", "Telerau ac Amodau", "Help wrth ddefnyddio GOV.UK", "Cysylltu")
    SCAStartPage.textContentVerify(othertexts)
  }


  And("""^the user sees services relevant content of chocs in welsh language$""") { () =>
    val texts =
      List("Hafan y cyfrif", "Negeseuon", "Gwirio cynnydd", "Proffil a gosodiadau", "Allgofnodi", "Cyfrif treth busnes", "Cyfrif treth personol")
    SCAStartPage.textContentVerify(texts)
    val othertexts =
      List("Cwcis ar wasanaethau CThEM", "Mae‘r holl gynnwys ar gael o dan", "Drwydded Llywodraeth Agored v3.0", ", oni nodir yn wahanol", "Cwcis", "Polisi preifatrwydd", "Telerau ac Amodau", "Help wrth ddefnyddio GOV.UK", "Cysylltu")
    SCAStartPage.textContentVerify(othertexts)
  }
  And("""the user sees services relevant content in English language""") { () =>
    val texts = List("Personal tax account", "Account home", "Messages", "Check progress", "Profile and settings", "Business tax account", "Sign out")

    SCAStartPage.textContentVerify(texts)
  }
}
