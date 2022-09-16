package com.demoqa;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import java.io.File;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestForm {
    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }
    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        $("[id=firstName]").setValue("Misha");
        $("[id=lastName]").setValue("Slotin");
        $("[id=userEmail]").setValue("hello@mars.com");
        $("[id=genterWrapper]").$(byText("Male")).click();
        $("[id=userNumber]").setValue("7999123455");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("1988");
        $(".react-datepicker__day--017:not(.react-datepicker__day--selected").click();
        $("[id=subjectsInput]").setValue("Biology").pressEnter();;
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/12345.png"));
        $("#currentAddress").setValue("SPB");
        $(byText("Select State")).click();
        $(byText("NCR")).click();
        $(byText("Select City")).click();
        $(byText("Gurgaon")).click();
        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Misha Slotin"),
                text("hello@mars.com"),
                text("Male"),
                text("7999123455"),
                text("17 July,1988"),
                text("Biology"),
                text("Music"),
                text("12345.png"),
                text("SPB"),
                text("NCR Gurgaon"));
        $("#closeLargeModal").click();
    }
}
