package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.*;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        int firstCardBalance = dashboardPage.getFirstCardBalance();
        int secondCardBalance = dashboardPage.getSecondCardBalance();

        dashboardPage.chooseFirstCard();
        var secondCardInfo = DataHelper.getSecondCardInfo();
        var paymentPage = new PaymentPage();
        int amount = 200;
        paymentPage.paymentFirstCard(secondCardInfo, amount);
        int newFirstCardBalance = firstCardBalance + amount;
        int newSecondCardBalance = secondCardBalance - amount;
        assertEquals(newFirstCardBalance, dashboardPage.getFirstCardBalance());
        assertEquals(newSecondCardBalance, dashboardPage.getSecondCardBalance());

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
        int firstCardBalance = dashboardPage.getFirstCardBalance();
        int secondCardBalance = dashboardPage.getSecondCardBalance();

        dashboardPage.chooseSecondCard();
        var firstCardInfo = DataHelper.getFirstCardInfo();
        var paymentPage = new PaymentPage();
        int amount = 400;
        paymentPage.paymentSecondCard(firstCardInfo, amount);

        int newFirstCardBalance = firstCardBalance - amount;
        int newSecondCardBalance = secondCardBalance + amount;

        assertEquals(newFirstCardBalance, dashboardPage.getFirstCardBalance());
        assertEquals(newSecondCardBalance, dashboardPage.getSecondCardBalance());

    }
}