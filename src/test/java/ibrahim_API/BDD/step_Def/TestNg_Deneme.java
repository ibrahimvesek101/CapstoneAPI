package ibrahim_API.BDD.step_Def;

import io.cucumber.java.en.Given;

public class TestNg_Deneme {

    @Given("TestNg working {int}")
    public void test_ng_working(Integer int1) {
        System.out.println("Test working");
    }
    @Given("TestNg working iki")
    public void test_ng_working_iki() {
        System.out.println("Test 2 working");
    }



}
