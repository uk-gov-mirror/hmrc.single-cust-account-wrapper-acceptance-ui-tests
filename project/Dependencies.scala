import sbt.*

object Dependencies {

  val test: Seq[ModuleID] = Seq(
    "uk.gov.hmrc"       %% "ddcn-acceptance-tests-common" % "2.0.1",
    "org.mongodb.scala" %% "mongo-scala-driver"           % "5.2.0" cross CrossVersion.for3Use2_13
  )
}
