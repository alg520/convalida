package convalida.sample;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static convalida.sample.TestUtils.testFieldWithAValidValue;
import static convalida.sample.TestUtils.testFieldWithAnInvalidValue;
import static convalida.sample.TestUtils.testFieldWithEmptyValue;

/**
 * @author Wellington Costa on 14/11/2017.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class DatabindingSampleActivityTest {


    @Rule
    public ActivityTestRule<DatabindingSampleActivity> activityTestRule =
            new ActivityTestRule<>(DatabindingSampleActivity.class);

    @Test
    public void executeValidationsWithEmptyFields() {
        onView(withId(R.id.validate_button))
                .perform(closeSoftKeyboard())
                .perform(scrollTo(), click());

        onView(withText(R.string.field_required))
                .perform(scrollTo())
                .check(matches(isDisplayed()));

        onView(withText(R.string.min_3_characters))
                .perform(scrollTo())
                .check(matches(isDisplayed()));

        onView(withText(R.string.only_numbers))
                .perform(scrollTo())
                .check(matches(isDisplayed()));

        onView(withText(R.string.invalid_phone))
                .perform(scrollTo())
                .check(matches(isDisplayed()));

        onView(withText(R.string.invalid_email))
                .perform(scrollTo())
                .check(matches(isDisplayed()));

        onView(withText(R.string.invalid_password))
                .perform(scrollTo())
                .check(matches(isDisplayed()));
    }

    @Test
    public void clearValidations() {
        onView(withId(R.id.validate_button))
                .perform(closeSoftKeyboard())
                .perform(scrollTo(), click());

        onView(withId(R.id.clear_button))
                .perform(scrollTo(), click());

        onView(withText(R.string.field_required))
                .check(doesNotExist());

        onView(withText(R.string.min_3_characters))
                .check(doesNotExist());

        onView(withText(R.string.only_numbers))
                .check(doesNotExist());

        onView(withText(R.string.invalid_phone))
                .check(doesNotExist());

        onView(withText(R.string.invalid_email))
                .check(doesNotExist());

        onView(withText(R.string.emails_not_match))
                .check(doesNotExist());

        onView(withText(R.string.invalid_password))
                .check(doesNotExist());

        onView(withText(R.string.passwords_not_match))
                .check(doesNotExist());
    }

    @Test
    public void testNameField() {
        testFieldWithEmptyValue(R.id.validate_button, R.string.field_required);
        testFieldWithAValidValue(R.id.name_field, R.string.field_required, "Wellington");
    }

    @Test
    public void testNicknameField() {
        testFieldWithEmptyValue(R.id.validate_button, R.string.min_3_characters);
        testFieldWithAnInvalidValue(R.id.nickname_field, R.string.min_3_characters, "We");
        testFieldWithAValidValue(R.id.nickname_field, R.string.min_3_characters, "Well");
    }

    @Test
    public void testAgeField() {
        testFieldWithEmptyValue(R.id.validate_button, R.string.only_numbers);
        testFieldWithAnInvalidValue(R.id.age_field, R.string.only_numbers, "abc");
        testFieldWithAValidValue(R.id.age_field, R.string.only_numbers, "21");
    }

    @Test
    public void testPhoneField() {
        testFieldWithEmptyValue(R.id.validate_button, R.string.invalid_phone);
        testFieldWithAnInvalidValue(R.id.phone_field, R.string.invalid_phone, "558586846409");
        testFieldWithAValidValue(R.id.phone_field, R.string.invalid_phone, "+55(85)8684-6409");
    }

    @Test
    public void testEmailField() {
        testFieldWithEmptyValue(R.id.validate_button, R.string.invalid_email);
        testFieldWithAnInvalidValue(R.id.email_field, R.string.invalid_email, "well@email");
        testFieldWithAValidValue(R.id.email_field, R.string.invalid_email, "well@email.com");
    }

    @Test
    public void testConfirmEmailField() {
        testFieldWithAValidValue(R.id.email_field, R.string.invalid_email, "wellington@email.com");
        testFieldWithAnInvalidValue(R.id.confirm_email_field, R.string.emails_not_match, "wellington@email.co");
        testFieldWithAValidValue(R.id.confirm_email_field, R.string.emails_not_match, "wellington@email.com");
    }

    @Test
    public void testPasswordField() {
        testFieldWithEmptyValue(R.id.validate_button, R.string.invalid_password);
        testFieldWithAnInvalidValue(R.id.password_field, R.string.invalid_password, "asdASD");
        testFieldWithAValidValue(R.id.password_field, R.string.invalid_password, "asdASD123");
    }

    @Test
    public void testConfirmPasswordField() {
        testFieldWithAValidValue(R.id.password_field, R.string.invalid_password, "asdASD123");
        testFieldWithAnInvalidValue(R.id.confirm_password_field, R.string.passwords_not_match, "asdASD");
        testFieldWithAValidValue(R.id.confirm_password_field, R.string.passwords_not_match, "asdASD123");
    }

}