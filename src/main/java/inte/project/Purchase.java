package inte.project;
//author Dan Jensen
import java.text.SimpleDateFormat;
import java.util.*;

public class Purchase implements Discount{

    private Money currentTotalWithoutVat = new Money(0);
    private Money currentTotal = new Money(0);
    private Money currentPayment = new Money(0);
    private Customer customer;
    private String dateOfPurchase;
    private String purchaseId;

    //discounts implemented on total purchase
    private Money purchaseDiscountAmount = new Money(0);
    private double purchaseDiscountPercent = 0.0;

    private HashMap<String, String> productsToPurchaseAsStrings = new HashMap<>();
    private HashMap<String, Product> productsToPurchase = new HashMap<>();
    private HashMap<String, Product> productsPurchased = new HashMap<>();
    private HashMap<String, Payment> paymentMethods = new HashMap<>();



    public Purchase(Product... products) {
        for (Product p: products) {
            this.currentTotalWithoutVat = currentTotalWithoutVat.add(p.getPrice());
            this.currentTotal = currentTotal.add(p.getPricePlusVAT());
            this.productsToPurchase.put(p.getId(), p);
            this.productsToPurchaseAsStrings.put(p.getId(), p.toString());
        }
    }

    public void paySeparatelyForProducts(Payment... payments) {
        for (Payment p: payments) {
            this.currentPayment = currentPayment.add(p.getPayment());
            paymentMethods.put(p.getPaymentType(), p);
            if (!p.getPaymentType().equals("Cash")) customer = p.getCustomer();
        }
        if (currentPayment.getAmountInOre() < currentTotal.getAmountInOre()) throw new IllegalStateException("Insufficient amount.");
        productsPurchased.putAll(productsToPurchase);
        setDateOfPurchase();
    }

    public void payTotalForProducts(Payment payment) {
        this.currentPayment = currentPayment.add(payment.getPayment());
        paymentMethods.put(payment.getPaymentType(), payment);
        if (currentPayment.getAmountInOre() < currentTotal.getAmountInOre()) throw new IllegalStateException("Insufficient amount.");
        if (!payment.getPaymentType().equals("Cash")) customer = payment.getCustomer();
        productsPurchased.putAll(productsToPurchase);
        setDateOfPurchase();
    }

    public void applyDiscountPercentToTotalPurchase(double percent){
        if (percent > 1.00) throw new IllegalArgumentException("Discount causes price increase.");
        this.purchaseDiscountAmount = purchaseDiscountAmount.add((int) (currentTotal.getAmountInOre() * applyDiscountPercent(percent)));
        this.currentTotal = currentTotal.subtract((int) (currentTotal.getAmountInOre() * applyDiscountPercent(percent)));
        this.purchaseDiscountPercent = percent;
    }

    public void applyDiscountPercentToProductType(double percent, String productType){
        if (percent > 1.00) throw new IllegalArgumentException("Discount causes price increase.");
        for (Product p : productsToPurchase.values()) {
            if (p.getType().equals(productType)) {
                this.currentTotal = currentTotal.subtract((int) (p.getPrice().getAmountInOre() * applyDiscountPercent(percent)));
                productsToPurchaseAsStrings.put(p.getId(), p + " Discount: " + percent*100 + "%");
                this.purchaseDiscountAmount = purchaseDiscountAmount.add((int) (p.getPrice().getAmountInOre() * applyDiscountPercent(percent)));
            }
        }
    }

    public void applyDiscountAmountToTotalPurchase(Money amount){
        if (amount.getAmountOfCrown() > getCurrentTotal().getAmountOfCrown())
            throw new IllegalArgumentException("Discount causes price increase.");
        this.currentTotal = currentTotal.subtract(amount);
        this.purchaseDiscountAmount = purchaseDiscountAmount.add(amount);
    }

    public void applyDiscountAmountToProductType(Money amount, String productType){
        for (Product p : productsToPurchase.values()) {
            if (p.getType().equals(productType) && amount.getAmountOfCrown() > p.getPrice().getAmountOfCrown())
                throw new IllegalArgumentException("Discount causes price increase.");
            if (p.getType().equals(productType)) {
                this.currentTotal = currentTotal.subtract(applyDiscountAmount(amount));
                productsToPurchaseAsStrings.put(p.getId(), p + " Discount: " + amount);
                this.purchaseDiscountAmount = purchaseDiscountAmount.add(amount);
            }
        }
    }

    public void applyDiscountPercentToProduct(double percent, String productId){
        if (percent > 1.00) throw new IllegalArgumentException("Discount causes price increase.");
        Product p = productsToPurchase.get(productId);
        if (p == null) throw new NullPointerException("Product not included in purchase.");
        this.currentTotal = currentTotal.subtract((int) (p.getPrice().getAmountInOre() * applyDiscountPercent(percent)));
        productsToPurchaseAsStrings.put(p.getId(), p + " Discount: " + percent*100 + "%");
        this.purchaseDiscountAmount = purchaseDiscountAmount.add((int) (p.getPrice().getAmountInOre() * applyDiscountPercent(percent)));
    }
    
    public void applyDiscountAmountToProduct(Money amount, String productId){
        Product p = productsToPurchase.get(productId);
        if (p == null) throw new NullPointerException("Product not included in purchase.");
        if (amount.getAmountOfCrown() > p.getPrice().getAmountOfCrown())
            throw new IllegalArgumentException("Discount causes price increase.");
        this.currentTotal = currentTotal.subtract(applyDiscountAmount(amount));
        productsToPurchaseAsStrings.put(p.getId(), p + " Discount: " + amount);
        this.purchaseDiscountAmount = purchaseDiscountAmount.add(amount);
    }

    public Payment getCardPayment() {
        return paymentMethods.get("Card");
    }

    public Collection<Cash> getCashFromPayment() {
        return paymentMethods.get("Cash").getCashPaymentValues();
    }

    public void removeProduct(String ID) {
        Product productToRemove = productsToPurchase.get(ID);
        productsToPurchase.remove(ID);
        currentTotal = currentTotal.subtract(productToRemove.getPrice());
    }

    public void setDateOfPurchase() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        dateOfPurchase = formatter.format(today);
    }

    public HashMap<String, Product> getProductsPurchased() {
        return productsPurchased;
    }

    public Collection<String> getPaymentMethodsAsString() {
        ArrayList<String> paymentMethodsToString = new ArrayList<>();
        for (Payment p : paymentMethods.values()) {
            paymentMethodsToString.add("\n"+p.toString());
        }
        return Collections.unmodifiableCollection(paymentMethodsToString);
    }

    public Collection<String> getProductsAsString() {
        ArrayList<String> paymentMethodsToString = new ArrayList<>();
        for (String p : productsToPurchaseAsStrings.values()) {
            paymentMethodsToString.add("\n"+p);
        }
        return Collections.unmodifiableCollection(paymentMethodsToString);
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public Money getCurrentTotal() {
        return currentTotal;
    }

    public Money getCurrentPayment() {
        return currentPayment;
    }

    public Customer getCustomer(){
        return customer;
    }


    //basically a toString for the Receipt, all of this needs to be collected
    // in the Reciept class using a PrintWriter for a document, so this method is more of a placeholder for Receipt
    public String getInfo() {
        if (purchaseDiscountPercent == 0.0)
        return "Purchase date: " + getDateOfPurchase() + "\nPayment methods: "
                + getPaymentMethodsAsString() + "\nProducts: " + getProductsAsString()+
                "\nTotal amount paid: " + currentTotal
                +"\nTotal discount amount: " + purchaseDiscountAmount;
        return "Purchase date: " + getDateOfPurchase() + "\nPayment methods: "
                + getPaymentMethodsAsString() + "\nProducts: " + getProductsAsString()+
                "\nTotal amount paid: " + currentTotal
                +"\nTotal discount amount: " + purchaseDiscountAmount+
                " ("  + purchaseDiscountPercent*100 + "% off!)";
    }

    //purchaseId with date + first letter of customer nam + first 3 numbers of payment (without VAT)
    public String getPurchaseId() {
        purchaseId = getDateOfPurchase() + customer.getName().charAt(0)
                + Integer.toString(currentTotalWithoutVat.getAmountInOre()).substring(0,2);
        return purchaseId;
    }
}
