package StepDefenition;

import Action.TestAction;
import Base.Base;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class TestStepDefenition {

    TestAction testAction = new TestAction();




    @Given("Navigate to amazon")
    public void navigate_to_amazon() throws InterruptedException {

         testAction.Gotoamazon();

    }
    @When("search for headphones")
    public void search_for_headphoenes() throws InterruptedException {
        testAction.searchproduct("headphones");
    }
    @Then("Select the second product and get page title")
    public void select_the_second_product_and_get_page_title() throws InterruptedException {
        testAction.selectproductgettext();
    }

    @After
    public void tearDown() {
        Base.quitDriver();
    }
}






































