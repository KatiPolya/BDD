package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.*;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        int balance = dashboardPage.getFirstCardBalance();
        dashboardPage.chooseFirstCard();
        var secondCardInfo = DataHelper.getSecondCardInfo();
        var paymentPageFirstCard = new PaymentPageFirstCard();
        int amount = 200;
        paymentPageFirstCard.PaymentFirstCard(secondCardInfo, amount);
        int newBalance = balance + amount;
        $(byText(String.valueOf(newBalance))).shouldBe(Condition.visible);


    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV2() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        int balance = dashboardPage.getSecondCardBalance();
        dashboardPage.chooseSecondCard();
        var firstCardInfo = DataHelper.getFirstCardInfo();
        var paymentPageSecondCard = new PaymentPageSecondCard();
        int amount = 400;
        paymentPageSecondCard.PaymentSecondCard(firstCardInfo, amount);
        int newBalance = balance + amount;
        $(byText(String.valueOf(newBalance))).shouldBe(Condition.visible);

    }
}