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

import play.api.libs.json.JsValue
import sttp.client3.{HttpClientSyncBackend, UriContext, basicRequest}
import sttp.model.{MediaType, StatusCode}

object MessagesStub {

  object HttpClientBackend {
    val backend = HttpClientSyncBackend()
  }

  def postMessagesStub(requestBody: JsValue, env: String): Boolean = {
    if (env == "local") {
      val response =
        basicRequest
          .post(uri"http://localhost:8910/messages")
          .contentType(MediaType.ApplicationJson)
          .body(requestBody.toString())
          .send(HttpClientBackend.backend)

      response.code match {
        case StatusCode.Ok | StatusCode.Created =>
          true
        case _ =>
          false
      }
    } else {
      false
    }
  }
}
