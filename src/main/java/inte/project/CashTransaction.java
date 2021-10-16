//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package inte.project;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class CashTransaction {

    private HashMap<Integer, Cash> payment = new HashMap();

    public CashTransaction(Cash... bills) {
        for (Cash cash: bills)
            this.addToPayment(cash);
        }


    public void addToPayment(Cash money) {
        Cash earlierMoney = this.payment.putIfAbsent(money.getDenomination(), money);
        if (earlierMoney != null) {
            earlierMoney.add(money.getQuantity());
        }

    }

    public Cash getBillsOfCertainDenomination(int denomination) {
        return this.payment.get(denomination);
    }

    public Collection<Cash> getPayment() {
        return Collections.unmodifiableCollection(this.payment.values());
    }
}
