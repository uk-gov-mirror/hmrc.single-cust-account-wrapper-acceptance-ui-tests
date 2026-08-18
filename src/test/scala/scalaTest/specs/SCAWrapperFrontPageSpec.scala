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

package scalaTest.specs

import org.scalatest.matchers.should.Matchers
import org.scalatest.featurespec.AnyFeatureSpec
import scalaTest.specs.tags.WipTest
import scalaTest.specsDefs.GGLoginSteps.*
import scalaTest.specsDefs.SCAStartPageSteps.*
import scalaTest.specsDefs.SCAWrapperStartPageSteps.*


class SCAWrapperFrontPageSpec extends AnyFeatureSpec with Matchers with BaseSpec {

  Feature("Wrapper integrating with sca-frontend") {

    Scenario("User logins to SCA web application and should see default GOV.UK header along with service name so user is reassured they have signed into Government service.") {
      Given("User login to the GG Login Page")
      loginWithGG()

      And("User is on SCA start page")
        userIsOnSCAStartPage()

      Then("User should see SCA title page Header contain logo text as GOV.UK in govuk-header__logotype")
        titlePageHeaderContainLogoText("SCA", "GOV.UK", "govuk-header__logotype")

      Then("User should see SCA title page Header contain service name as Single Customer Account in govuk-header__content")
        titlePageHeaderContainServiceName("SCA", "Single Customer Account", "govuk-header__content")

    }

    Scenario("User logins to SCA web application and BTA menu only shown based on the SA enrolment rule.") {
      Given("User login to the GG Login Page")
        loginWithGG()

      And("User is on SCA start page")
        userIsOnSCAStartPage()

      When("SA enrolment is applied")
        SAEnrolmentIsApplied()

      Then("User should see Business tax account menu option")
        menuOption("Business tax account")

    }

    Scenario("User logins to SCA web application and should see following menu items.", WipTest) {
      Given("User login to the GG Login Page Without SA enrollment")
      loginWithGGWithoutSAEnrollment()

      Then("User should see following option in menu Account home, Messages, Check progress, Profile and settings and Sign out")
        optionsInMenu("Account home", "Messages", "Check progress", "Profile and settings", "Sign out")

      Then("User should also see Account home icon besides account home menu option")
        iconBesidesAccountHomeMenu("Account home")

      Then("User should not see Business tax account menu option")
        notSeeBusinessTaxAccount()

    }

    Scenario("The Phase banner if it is shown should only be positioned at the bottom of the page directly above the footer and feedback link should be redirect to 'Send your feedback' Page") {
      Given("User login to the GG Login Page")
        loginWithGG()

      Then("default content of phase status should be Alpha")
        defaultContentOfPhaseStatus("Alpha")

      Then("User should able to see feedback link directly above the footer")
        linkDirectlyAboveTheFooter("feedback")

      When("User click on feedback link")
        clickOnFeedbackLink()

      Then("it should redirect to Send your feedback page")
        redirectToPage("Send your feedback")

      Then("Verify redirected URL should contains service name as single-customer-account")
        verifyRedirectedURLContainsServiceName("single-customer-account")

    }

    Scenario("Services should be able to configure the URL of Accessibility statement link") {
      Given("User login to the GG Login Page")
        loginWithGG()

      Then("User should see Accessibility statement link in the footer")
        seeLinkInTheFooter("Accessibility statement")

      When("User click on Accessibility statement link")
        clickOnAccessibilityStatement()

      Then("Verify redirected URL should contains service name as single-customer-account")
        verifyRedirectedURLContainsServiceName("single-customer-account")

    }

    Scenario("User logins to SCA web application and should see links in the footer") {
      Given("User login to the GG Login Page")
        loginWithGG()

      Then("User should see Cookies, Accessibility statement, Privacy policy, Terms and conditions, Help using GOV.UK, Contact and Rhestr o Wasanaethau Cymraeg links in the footer")
      userShouldSee("Cookies", "Accessibility statement", "Privacy policy", "Terms and conditions", "Help using GOV.UK", "Contact", "Rhestr o Wasanaethau Cymraeg")

    }

    Scenario("User logins to SCA web application and should see Is this page not working properly? link and this link should lead to a page that says 'Get help with a technical problem'.") {
      Given("User login to the GG Login Page")
        loginWithGG()

      Then("User should see Is this page not working properly? (opens in new tab) directly above the footer")
        textDirectlyAboveTheFooter("Is this page not working properly? (opens in new tab)")

      When("User click on Is this page not working properly link")
        clickOnIsThisPageNotWorkingProperly()

      Then("User should redirect to Get help with a technical problem page")
        redirectTo("Get help with a technical problem")

      Then("Verify redirected URL should contains service name as single-customer-account")
        verifyRedirectedURLContainsServiceName("single-customer-account")

    }

    Scenario("User should be able to see the content in Welsh language") {
      Given("User login to the GG Login Page")
        loginWithGG()

      When("the user clicks on Cymraeg welesh language link of single-customer-account")
        clickOnCymraeWeleshLanguage("single-customer-account")

      Then("the user sees relevant content in welsh language")
        seesRelevantContentInWelshLanguage()

      And("the user can not click language Cymraeg link")
        cannotClickLanguageLink("Cymraeg")

      When("the user clicks on English language link of single-customer-account")
        clickOnEnglishLanguage("single-customer-account")

      Then("the user can not click language English link")
        cannotClickLanguageLink("English")

      And("the user sees relevant content in English language")
        seesRelevantContentInEnglishLanguage()

    }

    Scenario("User logins to SCA web application should see the cookies banner and able to close it") {
      And("User is on SCA start page")
        userIsOnSCAStartPage()

      Then("User should see cookies banner")
        seeCookiesBanner()

      Then("User should able to close it")
        closeCookies()

    }

    Scenario("Check the messages icon displays correctly") {
      Given("The message collection is dropped from mongo database")
        messageCollectionDroppedFromMongoDatabase()

      And("User login to the GG Login Page")
        loginWithGG()

      And("User is on SCA start page")
        userIsOnSCAStartPage()

      And("A message is posted to the messages API in the local environment")
        messagePostedToTheMessagesAPI("local")

      Then("the user should see 1 as the number of messages")
        numberOfMessages("1")

    }

    Scenario("User logins to SCA web application should be able to log out") {
      Given("User login to the GG Login Page")
        loginWithGG()

      And("User is on SCA start page")
        userIsOnSCAStartPage()

      Then("User should see Sign out button")
        seeButton("Sign out")

      Then("User clicks on Sign out button")
        clickSignOut()

    }
  }
}
