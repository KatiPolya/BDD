package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PaymentPage {
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement cardNumberField = $("[data-test-id=from] input");
    private SelenideElement paymentButton = $(byText("Пополнить"));

    public DashboardPage paymentFirstCard(DataHelper.SecondCardInfo secondCardInfo, int amount) {
        amountField.setValue(String.valueOf(amount));
        cardNumberField.setValue(secondCardInfo.getCardNumber());
        paymentButton.click();
        return new DashboardPage();
    }

    public DashboardPage paymentSecondCard(DataHelper.FirstCardInfo firstCardInfo, int amount) {
        amountField.setValue(String.valueOf(amount));
        cardNumberField.setValue(firstCardInfo.getCardNumber());
        paymentButton.click();
        return new DashboardPage();
    }
}
