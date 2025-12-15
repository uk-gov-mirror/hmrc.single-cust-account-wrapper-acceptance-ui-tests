import sbt.*

object Dependencies {

  val test: Seq[ModuleID] = Seq(
//    "io.cucumber"           % "cucumber-junit"            % "7.31.0"    % Test,
//    "io.cucumber"           %% "cucumber-scala"           % "8.35.0"    % Test,
//    "uk.gov.hmrc"           %% "ui-test-runner"           % "0.50.0"    % Test,
//    "org.playframework"     %% "play-test"                % "3.0.9"     % Test,
//    "org.mongodb.scala"     %% "mongo-scala-driver"       % "5.1.1" cross CrossVersion.for3Use2_13,
      "org.scalaj"            %% "scalaj-http"              % "2.4.2"  cross CrossVersion.for3Use2_13,
      "uk.gov.hmrc"           %% "ddcn-acceptance-tests-common" % "1.0.7",
      "org.mongodb.scala" %% "mongo-scala-driver"           % "5.2.0" cross CrossVersion.for3Use2_13

  )
}
