package inte.project;
//author Dan Jensen
import java.text.SimpleDateFormat;
import java.util.*;

public class Order implements Discount{

    private Money currentTotalWithoutVat = new Money(0);
    private Money currentTotal = new Money(0);
    private Money currentPayment = new Money(0);
    private Customer customer;
    private String dateOfOrder;
    private String orderId;

    //discounts implemented on total order
    private Money orderDiscountAmount = new Money(0);
    private double orderDiscountPercent = 0.0;

    private HashMap<String, String> productsToOrderAsStrings = new HashMap<>();
    private HashMap<String, Product> productsToOrder = new HashMap<>();
    private HashMap<String, Product> productsOrderd = new HashMap<>();
    private HashMap<String, Payment> paymentMethods = new HashMap<>();


    public Order(Product... products) {
        for (Product p: products) {
            this.currentTotalWithoutVat = currentTotalWithoutVat.add(p.getPrice());
            this.currentTotal = currentTotal.add(p.getPricePlusVAT());
            this.productsToOrder.put(p.getId(), p);
            this.productsToOrderAsStrings.put(p.getId(), p.toString());
        }
    }


    public void paySeparatelyForProducts(Payment... payments) {
        for (Payment p: payments) {
            this.currentPayment = currentPayment.add(p.getPayment());
            paymentMethods.put(p.getPaymentType(), p);
            if (!p.getPaymentType().equals("Cash")) customer = p.getCustomer();
        }
        if (currentPayment.getAmountInOre() < currentTotal.getAmountInOre()) throw new IllegalStateException("Insufficient amount.");
        productsOrderd.putAll(productsToOrder);
        setDateOfOrder();
    }

    public void payTotalForProducts(Payment payment) {
        this.currentPayment = currentPayment.add(payment.getPayment());
        paymentMethods.put(payment.getPaymentType(), payment);
        if (currentPayment.getAmountInOre() < currentTotal.getAmountInOre()) throw new IllegalStateException("Insufficient amount.");
        if (!payment.getPaymentType().equals("Cash")) customer = payment.getCustomer();
        productsOrderd.putAll(productsToOrder);
        setDateOfOrder();
    }

    public void applyDiscountPercentToTotalOrder(double percent){
        if (percent > 1.00) throw new IllegalArgumentException("Discount causes price increase.");
        this.orderDiscountAmount = orderDiscountAmount.add((int) (currentTotal.getAmountInOre() * applyDiscountPercent(percent)));
        this.currentTotal = currentTotal.subtract((int) (currentTotal.getAmountInOre() * applyDiscountPercent(percent)));
        this.orderDiscountPercent = percent;
    }

    public void applyDiscountPercentToProductType(double percent, String productType){
        if (percent > 1.00) throw new IllegalArgumentException("Discount causes price increase.");
        for (Product p : productsToOrder.values()) {
            if (p.getType().equals(productType)) {
                this.currentTotal = currentTotal.subtract((int) (p.getPrice().getAmountInOre() * applyDiscountPercent(percent)));
                productsToOrderAsStrings.put(p.getId(), p + " Discount: " + percent*100 + "%");
                this.orderDiscountAmount = orderDiscountAmount.add((int) (p.getPrice().getAmountInOre() * applyDiscountPercent(percent)));
            }
        }
    }

    public void applyDiscountAmountToTotalOrder(Money amount){
        if (amount.getAmountOfCrown() > getCurrentTotal().getAmountOfCrown())
            throw new IllegalArgumentException("Discount causes price increase.");
        this.currentTotal = currentTotal.subtract(amount);
        this.orderDiscountAmount = orderDiscountAmount.add(amount);
    }

    public void applyDiscountAmountToProductType(Money amount, String productType){
        for (Product p : productsToOrder.values()) {
            if (p.getType().equals(productType) && amount.getAmountOfCrown() > p.getPrice().getAmountOfCrown())
                throw new IllegalArgumentException("Discount causes price increase.");
            if (p.getType().equals(productType)) {
                this.currentTotal = currentTotal.subtract(applyDiscountAmount(amount));
                productsToOrderAsStrings.put(p.getId(), p + " Discount: " + amount);
                this.orderDiscountAmount = orderDiscountAmount.add(amount);
            }
        }
    }

    public void applyDiscountPercentToProduct(double percent, String productId){
        if (percent > 1.00) throw new IllegalArgumentException("Discount causes price increase.");
        Product p = productsToOrder.get(productId);
        if (p == null) throw new NullPointerException("Product not included in order.");
        this.currentTotal = currentTotal.subtract((int) (p.getPrice().getAmountInOre() * applyDiscountPercent(percent)));
        productsToOrderAsStrings.put(p.getId(), p + " Discount: " + percent*100 + "%");
        this.orderDiscountAmount = orderDiscountAmount.add((int) (p.getPrice().getAmountInOre() * applyDiscountPercent(percent)));
    }
    
    public void applyDiscountAmountToProduct(Money amount, String productId){
        Product p = productsToOrder.get(productId);
        if (p == null) throw new NullPointerException("Product not included in order.");
        if (amount.getAmountOfCrown() > p.getPrice().getAmountOfCrown())
            throw new IllegalArgumentException("Discount causes price increase.");
        this.currentTotal = currentTotal.subtract(applyDiscountAmount(amount));
        productsToOrderAsStrings.put(p.getId(), p + " Discount: " + amount);
        this.orderDiscountAmount = orderDiscountAmount.add(amount);
    }

    public Payment getCardPayment() {
        return paymentMethods.get("Card");
    }

    public void addProduct(Product product) {
        productsToOrder.put(product.getId(), product);
        productsToOrderAsStrings.put(product.getId(), product.toString());
        currentTotal = currentTotal.add(product.getPricePlusVAT());
    }

    public Collection<Cash> getCashFromPayment() {
        return paymentMethods.get("Cash").getCashPaymentValues();
    }

    public void removeProduct(String ID) {
        Product productToRemove = productsToOrder.get(ID);
        productsToOrder.remove(ID);
        currentTotal = currentTotal.subtract(productToRemove.getPricePlusVAT());
    }

    public void setDateOfOrder() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        dateOfOrder = formatter.format(today);
    }

    public HashMap<String, Product> getProductsOrderd() {
        return productsOrderd;
    }

    public Collection<Product> getProductsToOrder() {
        return productsToOrder.values();
    }

    public Collection<String> getPaymentMethodsAsString() {
        ArrayList<String> paymentMethodsToString = new ArrayList<>();
        for (Payment p : paymentMethods.values()) {
            paymentMethodsToString.add("\n"+p.toString());
        }
        return Collections.unmodifiableCollection(paymentMethodsToString);
    }

    public Collection<String> getProductsAsString() {
        ArrayList<String> productsToString = new ArrayList<>();
        for (String p : productsToOrderAsStrings.values()) {
            productsToString.add("\n"+p);
        }
        return Collections.unmodifiableCollection(productsToString);
    }

    public String getDateOfOrder() {
        return dateOfOrder;
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
        if (orderDiscountPercent == 0.0)
        return "Order date: " + getDateOfOrder() + "\nPayment methods: "
                + getPaymentMethodsAsString() + "\nProducts: " + getProductsAsString()+
                "\nTotal amount paid: " + currentTotal
                +"\nTotal discount amount: " + orderDiscountAmount;
        return "Order date: " + getDateOfOrder() + "\nPayment methods: "
                + getPaymentMethodsAsString() + "\nProducts: " + getProductsAsString()+
                "\nTotal amount paid: " + currentTotal
                +"\nTotal discount amount: " + orderDiscountAmount+
                " ("  + orderDiscountPercent*100 + "% off!)";
    }

    //orderId with date + first letter of customer nam + first 3 numbers of payment (without VAT)
    public String getOrderId() {
        if (paymentMethods.containsKey("Card") || paymentMethods.containsKey("Points")) {
            orderId = getDateOfOrder() + customer.getName().charAt(0)
                    + ""+ currentTotalWithoutVat.toString().substring(0, 2);
        }
        else {
            orderId = getDateOfOrder() + "C"
                    + currentTotalWithoutVat.toString().substring(0,2);
        }
        return orderId;
    }
}
