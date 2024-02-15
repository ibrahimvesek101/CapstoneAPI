package ibrahim_API.BDD.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions
        (
                plugin = {
                        "json:target/cucumber.json",
                        "html:target/cucumber-report.html",
                        "rerun:target/rerun.txt",
                        "me.jvt.cucumber.report.PrettyReports:target/cucumber"
                },
                features = "src/test/resources",
                glue = "ibrahim_API/BDD/step_Def"
              //  tags = "@dnm"
        )
public class Runner extends AbstractTestNGCucumberTests {



}
