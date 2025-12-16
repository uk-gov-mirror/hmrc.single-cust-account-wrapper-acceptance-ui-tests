import sbt.*

object Dependencies {

  val test: Seq[ModuleID] = Seq(
    "org.scalaj"        %% "scalaj-http"                  % "2.4.2" cross CrossVersion.for3Use2_13,
    "uk.gov.hmrc"       %% "ddcn-acceptance-tests-common" % "1.0.7",
    "org.mongodb.scala" %% "mongo-scala-driver"           % "5.2.0" cross CrossVersion.for3Use2_13
  )
}
