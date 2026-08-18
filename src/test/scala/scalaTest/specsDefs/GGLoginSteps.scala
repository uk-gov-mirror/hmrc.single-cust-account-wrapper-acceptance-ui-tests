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

import scalaTest.pages.*

object GGLoginSteps {

  def loginWithGG(): Unit = {
    GGLoginPage.navigateToAuthLoginStub()
        GGLoginPage.enterRedirectURL()
        GGLoginPage.selectConfidenceLevel()
        GGLoginPage.enterNino()
        GGLoginPage.selectSAEnrolment()
        GGLoginPage.clickSubmitButton()
  }

  def loginWithGGWithoutSAEnrollment(): Unit = {
    GGLoginPage.navigateToAuthLoginStub()
        GGLoginPage.enterRedirectURL()
        GGLoginPage.selectConfidenceLevel()
        GGLoginPage.enterNino()
        GGLoginPage.clickSubmitButton()
  }

  def loginWithGGWithPTAEnrolment(): Unit = {
    GGLoginPage.navigateToAuthLoginStub()
        GGLoginPage.enterRedirectURL()
        GGLoginPage.selectConfidenceLevel()
        GGLoginPage.enterNino()
        GGLoginPage.selectPTAEnrolment()
        GGLoginPage.clickSubmitButton()
  }

  def loginWithChocsGG(): Unit = {
    GGChocsLoginPage.navigateToAuthLoginStub()
        GGChocsLoginPage.enterRedirectURL()
        GGChocsLoginPage.selectConfidenceLevel()
        GGChocsLoginPage.enterNino()
        GGChocsLoginPage.selectPTAEnrolment()
        GGChocsLoginPage.selectSAEnrolment()
        GGChocsLoginPage.clickSubmitButton()
  }

  def loginWithChocsGGWithoutSAEnrollment(): Unit = {
    GGChocsLoginPage.navigateToAuthLoginStub()
        GGChocsLoginPage.enterRedirectURL()
        GGChocsLoginPage.selectConfidenceLevel()
        GGChocsLoginPage.enterNino()
        GGChocsLoginPage.selectPTAEnrolment()
        GGChocsLoginPage.clickSubmitButton()
  }

  def loginWithChocsWithPTAEnrolment(): Unit = {
    GGChocsLoginPage.navigateToAuthLoginStub()
        GGChocsLoginPage.enterRedirectURL()
        GGChocsLoginPage.selectConfidenceLevel()
        GGChocsLoginPage.enterNino()
        GGChocsLoginPage.selectPTAEnrolment()
        GGChocsLoginPage.clickSubmitButton()
  }

  def loginToActionsGGWithNino(ninoNumber: String): Unit = {
    GGChocsLoginPage.navigateToAuthLoginStub()
        GGChocsLoginPage.enterRedirectActionURL()
        GGChocsLoginPage.selectConfidenceLevel()
        GGChocsLoginPage.enterNino(ninoNumber)
        GGChocsLoginPage.selectPTAEnrolment()
        GGChocsLoginPage.clickSubmitButton()
  }

  def loginToNinoGG(): Unit = {
    GGNINOLoginPage.navigateToAuthLoginStub()
        GGNINOLoginPage.enterRedirectURL()
        GGNINOLoginPage.selectConfidenceLevel()
        GGNINOLoginPage.enterNino()
        GGNINOLoginPage.selectSAEnrolment()
        GGNINOLoginPage.clickSubmitButton()
  }

  def loginToNinoGGWithoutSAEnrollment(): Unit = {
    GGNINOLoginPage.navigateToAuthLoginStub()
        GGNINOLoginPage.enterRedirectURL()
        GGNINOLoginPage.selectConfidenceLevel()
        GGNINOLoginPage.enterNino()
        GGNINOLoginPage.clickSubmitButton()
  }

  def loginToNinoGGWithPTAEnrolment(): Unit = {
    GGNINOLoginPage.navigateToAuthLoginStub()
        GGNINOLoginPage.enterRedirectURL()
        GGNINOLoginPage.selectConfidenceLevel()
        GGNINOLoginPage.enterNino()
        GGNINOLoginPage.selectPTAEnrolment()
        GGNINOLoginPage.clickSubmitButton()
  }

  def accessPageWithNino(url: String, nino: String): Unit = {
    url match {
          case "/actions" =>
            GGChildBenefitLogin.navigateToServiceUrl("actions", url)
            GGChildBenefitLogin.setConfidenceLevel()
            GGChildBenefitLogin.enterNINO(nino)
            GGChildBenefitLogin.clickSubmitButton()
          case "child-benefit" =>
            GGChildBenefitLogin.navigateToBaseUrl(url)
            GGChildBenefitLogin.setConfidenceLevel()
            GGChildBenefitLogin.enterNINO(nino)
            GGChildBenefitLogin.clickSubmitButton()
          case "/service-down" =>
            GGChildBenefitLogin.navigateToServiceUrl("child-benefit", url)
            GGChildBenefitLogin.setConfidenceLevel()
          case _ =>
            GGChildBenefitLogin.navigateToServiceUrl("child-benefit", url)
            GGChildBenefitLogin.setConfidenceLevel()
            GGChildBenefitLogin.enterNINO(nino)
            GGChildBenefitLogin.clickSubmitButton()
        }
  }

  def loginToActivityPage(): Unit = {
    GGLoginPage.navigateToAuthLoginStub()
        GGLoginPage.enterRedirectActivityURL()
        GGLoginPage.selectConfidenceLevel()
        GGLoginPage.enterNino()
        GGLoginPage.selectSAEnrolment()
        GGLoginPage.clickSubmitButton()
  }

}
