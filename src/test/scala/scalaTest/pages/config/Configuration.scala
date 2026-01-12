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

package scalaTest.pages.config

case class Configuration(
  APP_ROOT: String,
  APPROOT: String,
  AUTHLOGINSTUB: String,
  FEEDBACK_PAGE: String,
  STATEPENSION_PAGE: String,
  NI_PAGE: String,
  PAGE_TIMEOUT_SECS: Int = 60,
  CHOCS_PAGE: String,
  ACTIONS_PAGE: String,
  NINO_PAGE: String,
  ACTIVITY: String
)

object Configuration {

  lazy val environment: Option[String] = {
    System.getProperty("environment", "local").toLowerCase match {
      case "local"             => None
      case "dev"               => Some("https://www.development.tax.service.gov.uk")
      case "qa"                => Some("https://www.qa.tax.service.gov.uk")
      case "staging"           => Some("https://www.staging.tax.service.gov.uk")
      case environmentProperty => throw new IllegalArgumentException(s"Environment '$environmentProperty' not known")
    }
  }

  lazy val settings: Configuration = environment.fold(
    new Configuration(
      APP_ROOT = "http://localhost:9949/auth-login-stub/gg-sign-in?",
      APPROOT = "http://localhost:8420/single-customer-account",
      AUTHLOGINSTUB = "http://localhost:9949/auth-login-stub/gg-sign-in",
      FEEDBACK_PAGE = "http://localhost:9514/feedback/single-customer-account-frontend",
      STATEPENSION_PAGE = "http://localhost:9234/check-your-state-pension/account",
      NI_PAGE = "http://localhost:9234/check-your-state-pension/account/nirecord",
      CHOCS_PAGE = "http://localhost:10600/hmrc-account/update-your-details",
      ACTIONS_PAGE = "http://localhost:8420/single-customer-account/actions",
      NINO_PAGE = "http://localhost:14006/save-your-national-insurance-number",
      ACTIVITY = "http://localhost:8420/single-customer-account/activities"

    )
  ) { environmentUrl =>
    new Configuration(
      APP_ROOT = s"$environmentUrl/auth-login-stub/gg-sign-in?continue=%2Fsingle-customer-account",
      APPROOT = s"$environmentUrl/single-customer-account",
      AUTHLOGINSTUB = s"$environmentUrl/auth-login-stub/gg-sign-in",
      FEEDBACK_PAGE = s"$environmentUrl/feedback/single-customer-account-frontend",
      STATEPENSION_PAGE = s"$environmentUrl/check-your-state-pension/account",
      NI_PAGE = s"$environmentUrl/check-your-state-pension/account/nirecord",
      CHOCS_PAGE = s"$environmentUrl/hmrc-account/update-your-details",
      ACTIONS_PAGE = s"$environmentUrl/single-customer-account/actions",
      NINO_PAGE= s"$environmentUrl/save-your-national-insurance-number",
      ACTIVITY= s"$environmentUrl/single-customer-account/activities"

    )
  }
}
