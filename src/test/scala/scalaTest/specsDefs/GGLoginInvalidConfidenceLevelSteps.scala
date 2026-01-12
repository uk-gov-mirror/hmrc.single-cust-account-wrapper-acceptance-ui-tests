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

object GGLoginInvalidConfidenceLevelSteps {

  // User login to the GGLogin Page$
  def userLoginToTheGGLoginPage(): Unit = {
    GGLoginPage.navigateToAuthLoginStub()
        GGLoginPage.enterRedirectURL()
  }

  // ^Confidence level less is less than 200$
  def confidenceLevelLessThan200(): Unit = {
    GGLoginPageInvalidConfidenceLevel.selectConfidenceLevel()
        GGLoginPage.enterNino()
        GGLoginPage.clickSubmitButton()
  }

  // ^User should see SCA home page with an error "([^"]*)"$
  def SCAHomePageError()(accessError: String): Unit = {
    assert(GGLoginPageInvalidConfidenceLevel.verifySCAStartPageAccessError(accessError))
  }

}
