//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package inte.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CashTransactionTest {
    public CashTransactionTest() {
    }

    @Test
    void constructorFillsPaymentForSingleQuantitiesDifferentDenominations() {
        Cash one = new Cash(1, 1);
        Cash twenty = new Cash(20, 1);
        Cash fifty = new Cash(50, 1);
        CashTransaction cashTransaction = new CashTransaction(one, twenty, fifty);
        Assertions.assertTrue(cashTransaction.getPayment().contains(one));
        Assertions.assertTrue(cashTransaction.getPayment().contains(twenty));
        Assertions.assertTrue(cashTransaction.getPayment().contains(fifty));
    }

    @Test
    void constructorFillsPaymentForMultipleQuantitySameDenomination() {
        Cash one = new Cash(1, 3);
        Cash otherOne = new Cash(1, 1);
        Cash otherOtherOne = new Cash(1, 1);
        CashTransaction cashTransaction = new CashTransaction(one, otherOne, otherOtherOne);
        Assertions.assertEquals(cashTransaction.getBillsOfCertainDenomination(1).getTotal(), 5);
    }
}
