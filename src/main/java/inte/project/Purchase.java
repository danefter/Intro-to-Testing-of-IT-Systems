package inte.project;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public class Purchase {

    private Money currentTotal = new Money(0);
    private Money currentPayment = new Money(0);
    private String dateOfPurchase;

    private HashMap<String, Product> productsToPurchase = new HashMap<>();
    private HashMap<String, Product> productsPurchased = new HashMap<>();
    private HashMap<String, Payment> paymentMethods = new HashMap<>();

    public Purchase(Product... products) {
        for (Product p: products) {
            this.currentTotal.add(p.getPrice());
            this.productsToPurchase.put(p.getId(), p);
        }
    }

    public void paySeparatelyForProducts(Payment... payments) {
        for (Payment p: payments) {
            currentPayment.add(p.getPayment());
            paymentMethods.put(p.getPaymentType(), p);}
        if (currentPayment.getAmountInOre() < currentTotal.getAmountInOre()) paySeparatelyForProducts();

        productsPurchased.putAll(productsToPurchase);
        setDateOfPurchase();
    }

    public void payTotalForProducts(Payment payment) {
        currentPayment.add(payment.getPayment());
        paymentMethods.put(payment.getPaymentType(), payment);
        if (currentPayment.getAmountInOre() < currentTotal.getAmountInOre()) paySeparatelyForProducts();
        productsPurchased.putAll(productsToPurchase);
        setDateOfPurchase();
    }

    public Collection<Cash> getCardsFromPayment() {
        return paymentMethods.get("Card").getCashPaymentValues();
    }

    public Collection<Cash> getCashFromPayment() {
        return paymentMethods.get("Cash").getCashPaymentValues();
    }

    public void removeProduct(String ID) {
        Product productToRemove = productsToPurchase.get(ID);
        productsToPurchase.remove(ID);
        currentTotal.subtract(productToRemove.getPrice());
    }

    public void setDateOfPurchase() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        dateOfPurchase = formatter.format(today);
    }

    public HashMap<String, Product> getProductsPurchased() {
        return productsPurchased;
    }

    public HashMap<String, Payment> getPaymentMethods() {
        return paymentMethods;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public Money getCurrentTotal() {
        return currentTotal;
    }
}
