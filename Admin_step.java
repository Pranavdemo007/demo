package orange_stepdefinition;

import basepackage.Base;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import orange_elements.Admin_pom;
import org.junit.Assert;

public class Admin_step extends Base {
    private Admin_pom adminPage;

    public Admin_step() {
        this.adminPage = new Admin_pom(driver);
    }

    @When("I navigate to the Admin module")
    public void i_navigate_to_admin_module() {
        adminPage.navigateToAdmin();
    }

    @When("I click on {string}")
    public void i_click_on(String button) {
        if ("Add User".equalsIgnoreCase(button)) {
            adminPage.clickAddUser();
        } else {
            throw new IllegalArgumentException("Invalid button: " + button);
        }
    }

    @When("I enter user details with username {string}, role {string}, employee name {string}, status {string}, and password {string}")
    public void i_enter_user_details(String username, String role, String employeeName, String status, String password) {
        adminPage.enterUserDetails(username, role, employeeName, status, password);
    }

    @When("I save the new user")
    public void i_save_the_new_user() {
        adminPage.saveUser();
    }

    @Then("I should see the user {string} in the user list")
    public void i_should_see_the_user_in_list(String username) {
        Assert.assertTrue("User not found in list!", adminPage.isUserDisplayed(username));
    }

    @When("I search for user {string}")
    public void i_search_for_user(String username) {
        adminPage.searchUser(username);
    }

    @Then("I should see {string} in the search results")
    public void i_should_see_in_results(String username) {
        Assert.assertTrue("User not found in search results!", adminPage.isUserDisplayed(username));
    }

    @When("I delete the user {string}")
    public void i_delete_the_user(String username) {
        adminPage.deleteUser(username);
    }

    @Then("the user {string} should be removed from the list")
    public void the_user_should_be_removed(String username) {
        Assert.assertFalse("User was not removed!", adminPage.isUserDisplayed(username));
    }
    @After
    public void tearDown() {
        closeBrowser();
}
}