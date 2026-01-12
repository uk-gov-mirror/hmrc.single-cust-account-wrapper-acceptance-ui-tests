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
import scalaTest.pages.CheckYourVATHomePage.*
import scalaTest.pages.CheckYourVATResult.{result, useSetVATFlatRate, useUniqueVATFlatRate}

object ExampleStepDefSteps {

  // I am on the Check your VAT flat rate service
  def checkYourVATFlatRateService(): Unit = {
    CheckYourVATHomePage.loadPage
  }

  // I submit my VAT for goods under £1000 for the year
  def submitVATForGoodsUnder1000Year(): Unit = {
    provideVATPeriod("Annually")
          .provideTurnoverAmount("1000")
          .provideCostOfGoodsAmount("999")
          .submitVATInformation
  }

  // I submit my VAT information for goods over £1000 for the year
  def submitVATInformationForGoodsOver1000Year(): Unit = {
    provideVATPeriod("Annually")
          .provideTurnoverAmount("1000")
          .provideCostOfGoodsAmount("1000")
          .submitVATInformation
  }

  // I submit my VAT information for goods under £250 for the quarter
  def submitVATInformationForGoodsUnder250Quarter(): Unit = {
    provideVATPeriod("Quarterly")
          .provideTurnoverAmount("1000")
          .provideCostOfGoodsAmount("249")
          .submitVATInformation
  }

  // I submit my VAT information for goods for £250 for the quarter
  def submitMyInformationForGoodsFor250Quarter(): Unit = {
    provideVATPeriod("Quarterly")
          .provideTurnoverAmount("1000")
          .provideCostOfGoodsAmount("250")
          .submitVATInformation
  }

  // I will be asked to use the 16.5% VAT flat rate
  def use16PercentFlatRate(): Unit = {
    result.equals(useSetVATFlatRate)
  }

  // I will be asked to use the VAT flat rate
  def useTheVATFlatRate(): Unit = {
    result.equals(useUniqueVATFlatRate)
  }

}
