package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class StartPage {

    private final SelenideElement heading = $(byText("Путешествие дня"));
    private final SelenideElement byButton = $(byText("Купить"));
    private final SelenideElement byCreditButton = $(byText("Купить в кредит"));

    public StartPage() {
        heading.shouldBe(visible);
    }
    public PaymentPage openBuyCard() {
        byButton.click();
        return new PaymentPage();
    }

    public CreditPage openBuyCredit() {
        byCreditButton.click();
        return new CreditPage();
    }
}
