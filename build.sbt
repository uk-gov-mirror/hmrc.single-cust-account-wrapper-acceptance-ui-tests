lazy val testSuite = (project in file("."))
  .disablePlugins(JUnitXmlReportPlugin) //Required to prevent https://github.com/scalatest/scalatest/issues/1427
  .settings(
    name := "single-cust-account-wrapper-acceptance-ui-tests",
    version := "3.0.0",
    scalaVersion := "3.3.4",
    scalacOptions ++= Seq("-feature"),
    libraryDependencies ++= Dependencies.test
  )
