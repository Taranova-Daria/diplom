package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.StartPage;
import static com.codeborne.selenide.Selenide.open;


public class PaymentTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @DisplayName("Card - Purchase approvedy")
    @Test
    public void shouldVerifyApprovedCardPayment() {
        var startPage = new StartPage();
        var payCard = startPage.openBuyCard();
        var approvedCardInformation = DataHelper.getValidCard();
        payCard.enterCardData(approvedCardInformation);
        payCard.verifySuccessNotificationCard();

        var statusPayment = SQLHelper.getStatusPayment();
        Assertions.assertEquals("APPROVED", statusPayment.getStatus());
    }

    @DisplayName("Card - Purchase with current month and year approved")
    @Test
    public void shouldVerifyCurrentDateCardPayment() {
        var startPage = new StartPage();
        var payCard = startPage.openBuyCard();
        var validCardInformation = DataHelper.getCurrentMonthAndYear();
        payCard.enterCardData(validCardInformation);
        payCard.verifySuccessNotificationCard();

        var statusPayment = SQLHelper.getStatusPayment();
        Assertions.assertEquals("APPROVED", statusPayment.getStatus());
    }

    @DisplayName("Card - Card payment is prohibited")
    @Test
    public void shouldNotApproveDeclinedCardPayment() {
        var startPage = new StartPage();
        var payCard = startPage.openBuyCard();
        var declinedCard = DataHelper.getDeclinedCard();
        payCard.enterCardData(declinedCard);
        payCard.verifyErrorNotificationCard();

        var statusPayment = SQLHelper.getStatusPayment();
        Assertions.assertEquals("DECLINED", statusPayment.getStatus());
    }

    @DisplayName("Card - in the fields area no data")
    @Test
    public void shouldNotAcceptEmptyCardDetails() {
        var startPage = new StartPage();
        var payCard = startPage.openBuyCard();
        var emptyCardInformation = DataHelper.getAllFieldsEmpty();
        payCard.enterCardData(emptyCardInformation);
        payCard.verifyInvalidFormatCard();
    }

    @DisplayName("Card - empty field for the card number")
    @Test
    public void shouldNotAcceptEmptyCardNumberField() {
        var startPage = new StartPage();
        var payCard = startPage.openBuyCard();
        var fieldCardEmpty = DataHelper.getCardNumberEmpty();
        payCard.enterCardData(fieldCardEmpty);
        payCard.verifyInvalidFormatCard();
    }

    @DisplayName("Card - Empty month field")
    @Test
    public void shouldNotAcceptEmptyMonthField() {
        var startPage = new StartPage();
        var payCard = startPage.openBuyCard();
        var fieldMonthEmpty = DataHelper.getMonthEmpty();
        payCard.enterCardData(fieldMonthEmpty);
        payCard.verifyInvalidFormatCard();
    }

    @DisplayName("Card - Empty year field")
    @Test
    public void shouldNotAcceptEmptyYearField() {
        var startPage = new StartPage();
        var payCard = startPage.openBuyCard();
        var fieldYearEmpty = DataHelper.getYearEmpty();
        payCard.enterCardData(fieldYearEmpty);
        payCard.verifyInvalidFormatCard();
    }

    @DisplayName("Card - Empty cardholder name field")
    @Test
    public void shouldNotAcceptEmptyHolderField() {
        var startPage = new StartPage();
        var payCard = startPage.openBuyCard();
        var fieldHolderEmpty = DataHelper.getHolderEmpty();
        payCard.enterCardData(fieldHolderEmpty);
        payCard.verifyRequiredFieldCard();
    }

    @DisplayName("Card - Empty CVV field")
    @Test
    public void shouldNotAcceptEmptyCvvField() {
        var startPage = new StartPage();
        var payCard = startPage.openBuyCard();
        var fieldCvvEmpty = DataHelper.getCVVEmpty();
        payCard.enterCardData(fieldCvvEmpty);
        payCard.verifyInvalidFormatCard();
    }

    @DisplayName("Card - Incorrect card number format")
    @Test
    public void shouldNotAcceptInvalidCardNumber() {
        var startPage = new StartPage();
        var payCard = startPage.openBuyCard();
        var invalidCard = DataHelper.getInvalidNumber();
        payCard.enterCardData(invalidCard);
        payCard.verifyInvalidFormatCard();
    }

    @DisplayName("Card - Incorrect month entered")
    @Test
    public void shouldNotAcceptInvalidMonthField() {
        var startPage = new StartPage();
        var payCard = startPage.openBuyCard();
        var invalidCard = DataHelper.getInvalidMonth();
        payCard.enterCardData(invalidCard);
        payCard.verifyInvalidDateCard();
    }

    @DisplayName("Card - Incorrect year entered")
    @Test
    public void shouldNotAcceptInvalidYearField() {
        var startPage = new StartPage();
        var payCard = startPage.openBuyCard();
        var invalidCard = DataHelper.getWrongYear();
        payCard.enterCardData(invalidCard);
        payCard.verifyInvalidDateCard();
    }

    @DisplayName("Card - Numeric cardholder name")
    @Test
    public void shouldNotAcceptNumericCardholderName() {
        var startPage = new StartPage();
        var payCard = startPage.openBuyCard();
        var invalidCard = DataHelper.getNumericName();
        payCard.enterCardData(invalidCard);
        payCard.verifyInvalidFormatCard();
    }

    @DisplayName("Card - Incorrect CVV format")
    @Test
    public void shouldNotAcceptInvalidCvvField() {
        var startPage = new StartPage();
        var payCard = startPage.openBuyCard();
        var invalidCard = DataHelper.getInvalidCVV();
        payCard.enterCardData(invalidCard);
        payCard.verifyInvalidFormatCard();
    }

    @DisplayName("Card - Expired month entered")
    @Test
    public void shouldNotAcceptExpiredMonthField() {
        var startPage = new StartPage();
        var payCard = startPage.openBuyCard();
        var invalidCard = DataHelper.getExpiredMonth();
        payCard.enterCardData(invalidCard);
        payCard.verifyInvalidDateCard();
    }

    @DisplayName("Card - Expired year entered")
    @Test
    public void shouldNotAcceptExpiredYearField() {
        var startPage = new StartPage();
        var payCard = startPage.openBuyCard();
        var invalidCard = DataHelper.getExpiredYear();
        payCard.enterCardData(invalidCard);
        payCard.expiredCardYear();
    }

    @DisplayName("Card - Zero card number provided")
    @Test
    public void shouldNotAcceptZeroCardNumber() {
        var startPage = new StartPage();
        var payCard = startPage.openBuyCard();
        var invalidCard = DataHelper.getZeroCard();
        payCard.enterCardData(invalidCard);
        payCard.verifyInvalidFormatCard();
    }

    @DisplayName("Card - Zero month provided")
    @Test
    public void shouldNotAcceptZeroMonthField() {
        var startPage = new StartPage();
        var payCard = startPage.openBuyCard();
        var invalidCard = DataHelper.getZeroMonth();
        payCard.enterCardData(invalidCard);
        payCard.verifyInvalidDateCard();
    }

    @DisplayName("Card - Zero CVV provided")
    @Test
    public void shouldNotAcceptZeroCvvField() {
        var startPage = new StartPage();
        var payCard = startPage.openBuyCard();
        var invalidCard = DataHelper.getZeroCVV();
        payCard.enterCardData(invalidCard);
        payCard.verifyInvalidFormatCard();
    }
}
