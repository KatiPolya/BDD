package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PaymentPageSecondCard {
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement cardNumberField = $("[data-test-id=from] input");
    private SelenideElement paymentButton = $(byText("Пополнить"));

    public DashboardPage PaymentSecondCard(DataHelper.FirstCardInfo info, int amount) {
        amountField.setValue(String.valueOf(amount));
        cardNumberField.setValue(info.getCardNumber());
        paymentButton.click();
        return new DashboardPage();
    }
}
