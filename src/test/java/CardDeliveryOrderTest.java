
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

        import com.codeborne.selenide.Configuration;
        import com.codeborne.selenide.logevents.SelenideLogger;
        import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
        import ru.netology.web.DataGenerator;
        import ru.netology.web.User;

        import java.time.Duration;
        import java.time.format.DateTimeFormatter;

        import static com.codeborne.selenide.Condition.exactText;
        import static com.codeborne.selenide.Condition.visible;
        import static com.codeborne.selenide.Selectors.withText;
        import static com.codeborne.selenide.Selenide .*;
        import static com.codeborne.selenide.Selenide.$;

public class CardDeliveryOrderTest {

    @BeforeAll
    static void setUp() {
        Configuration.headless = false;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldRegisterCardOrder() {
        open("http://localhost:9999");
        User userOne = DataGenerator.Registration.registrationUser();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.uuuu");
        $("[data-test-id=city] .input__control").setValue(userOne.getCity());
        $("[data-test-id=date] .input__control[value]").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        $("[data-test-id=date] .input__control").setValue(userOne.getLocalDate());
        $("[data-test-id=name] .input__control").setValue(userOne.getName());
        $("[data-test-id=phone] .input__control").setValue(userOne.getPhone());
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button .button__text").click();
        //нажимаем продолжить с теми же данными
        $(".button .button__text").click();
        $$(".button .button__text").find(exactText("Перепланировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(6));
    }

    @Test
    void shouldRegisterCardOrderByInvalidName() {
        open("http://localhost:9999");
        User userOne = DataGenerator.Registration.registrationUser();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.uuuu");
        $("[data-test-id=city] .input__control").setValue(userOne.getCity());
        $("[data-test-id=date] .input__control[value]").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        $("[data-test-id=date] .input__control").setValue(userOne.getLocalDate());
        $("[data-test-id=name] .input__control").setValue("David Грец");
        $("[data-test-id=phone] .input__control").setValue(userOne.getPhone());
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button .button__text").click();
        //нажимаем продолжить с теми же данными
        $(".button .button__text").click();
        $$(".button .button__text").find(exactText("Перепланировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(6));
    }

}

