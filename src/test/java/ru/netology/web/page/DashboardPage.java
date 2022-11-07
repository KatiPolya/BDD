package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import lombok.val;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
    }

    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
        val text = cards.last().text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public PaymentPageFirstCard chooseFirstCard() {
        ElementsCollection bottoms = $$("[data-test-id=\"action-deposit\"]");
        bottoms.first().click();
        return new PaymentPageFirstCard();
    }

    public PaymentPageSecondCard chooseSecondCard() {
        ElementsCollection bottoms = $$("[data-test-id=\"action-deposit\"]");
        bottoms.last().click();
        return new PaymentPageSecondCard();
    }
}