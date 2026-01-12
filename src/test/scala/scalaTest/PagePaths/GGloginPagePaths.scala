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

package scalaTest.PagePaths

trait GGloginPagePaths {
  val redirectURLField     = "redirectionUrl"
  val confidenceLevelField = "confidenceLevel"
  val nino                 = "nino"
  val submitButton         = "submit-top"
}

trait ActionsPagePaths {
  val actionsResult     = "div.govuk-grid-column-full"
}
trait SCAStartPagePaths {
  val accountName                = "name"
  val TaxandBenefits             = "nav-taxes-and-benefits"
  val Msgs                       = "nav-messages"
  val PersonalDetails            = "nav-details"
  val selfAssesment              = "sa"
  val PayAsYouEarn               = "paye"
  val Pension                    = "nisp"
  val statePensionURL            = "//a[normalize-space()='Check your State Pension summary']"
  val niURL                      = "//a[normalize-space()='Check your National Insurance record']"
  val taxReturnLink              = "Complete your tax return"
  val statePensionLink           = "Check your State Pension summary"
  val niLink                     = "Check your National Insurance record"
  val scaMessage                 = "//span[contains(text(),'Reminder to file a Self Assessment return')]"
  val yourMessage                = "Reminder to file a Self Assessment return"
  val backButton                 = "govuk-back-link"
  val Feedbacklink               = "./html/body/div/main/div/div/div[2]/div/p/span/a"
  val FeedbacklinkJenkins        = "//a[@class='govuk-link' and contains(text(),'feedback')]"
  val SCAAccessErrorText         = "govuk-heading-xl"
  val BusinessAccountLink        = "//*[contains(text(),'Business tax account')]"
  val AccountHomeLink            = "(//*[contains(text(),'Account home')])[1]"
  val AccountHomeIcon            = "//*[@class='hmrc-account-icon hmrc-account-icon--home']"
  val MessagesLink               = "//*[contains(text(),'Messages')]"
  val CheckProgressLink          = "//*[contains(text(),'Check progress')]"
  val ProfileAndSettingsLink     = "//*[contains(text(),'Profile and settings')]"
  val SignOutLink                = "//*[contains(text(),'Sign out')]"
  val banner                     = "//*[@class='govuk-tag govuk-phase-banner__content__tag']"
  val pageFeedback               = "//h1[contains(text(),'Send your feedback')]"
  val pageNotWorkingProperlyLink = "Is this page not working properly? (opens in new tab)"
  val pageNotWorkingProperly     = "//h1[contains(text(),'Get help with a technical problem')]"
  val AccessibilitystatementLink = "Accessibility statement"
}
trait FeedbackPagePaths {
  val TechnicalHelpPage      = "./html/body/div/main/div/div/a"
  val technicalHelpPageTitle = "head > title"
}
