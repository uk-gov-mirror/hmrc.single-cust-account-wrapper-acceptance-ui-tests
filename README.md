# single-cust-account-wrapper-acceptance-ui-tests

How to run acceptance tests locally?
-----------------------------------

# Clone the project:

Clone the project [https://github.com/hmrc/single-cust-account-wrapper-acceptance-ui-tests ] to a directory of your choice

# Start services:

To start the service - Go to terminal instance run: `sm2 --start SCA_FUTURES_UI_TESTS`

# Verify services:

To checks if all required services are running `sm2 -s`

# Run local Mongo:

Setup MongoDB instructions https://docs.tax.service.gov.uk/mdtp-handbook/documentation/developer-set-up/set-up-mongodb.html
Make sure that Mongo run locally

# Navigate and run tests:

Navigate to the directory where you cloned the tests. Run: `./run_tests.sh`

# Headless mode:

The tests will run locally in headless mode by default

# Non-Headless Mode

if you want to see the running browser locally then enable the system property `-Dbrowser.option.headless=false`

# Debugging the test using IntelliJ

Create a new JUnit task
Change use "classpath of module" to `single-cust-account-wrapper-acceptance-ui-tests`

To run `WIP` only, use the class `uk.gov.hmrc.test.ui.cucumber.runner.wip`
To run headless, add in the VM option
`-Dbrowser.option.headless=false`



## Scalafmt

Check all project files are formatted as expected as follows:

```bash
sbt scalafmtCheckAll scalafmtCheck
```

Format `*.sbt` and `project/*.scala` files as follows:

```bash
sbt scalafmtSbt
```

Format all project files as follows:

```bash
sbt scalafmtAll
```

