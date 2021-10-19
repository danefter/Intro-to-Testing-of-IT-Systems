package inte.project;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Purchase {

    private int currentTotal;
    private int currentPayment;
    private String dateOfPurchase;

    private HashMap<String, Product> productsToPurchase = new HashMap<>();
  
    private HashMap<String, Payment> paymentMethods = new HashMap<>();

    public Purchase(Product... products) {
        for (Product p: products) {
            this.currentTotal += p.getPrice().getAmountInOre();
            this.productsToPurchase.put(p.getId(), p);
        }
    }

    public void payForProducts(int amount) {
        do {
            Payment payment = new Payment();
            currentPayment += payment.getPayment(amount);
            paymentMethods.put(payment.getPaymentType(), payment);
        }
        while (currentPayment < currentTotal);
        setDateOfPurchase();
    }


    public void setDateOfPurchase() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        dateOfPurchase = formatter.format(today);
    }

    public int getCurrentTotal() {
        return currentTotal;
    }

    public void setCurrentTotal(int currentTotal) {
        this.currentTotal = currentTotal;
    }

    public int getCurrentPayment() {
        return currentPayment;
    }

    public void setCurrentPayment(int currentPayment) {
        this.currentPayment = currentPayment;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

}
