package inte.project;
//author Dan Jensen

import java.util.*;

public class Register {


    private final Store store;

    private Report dailyReport = new Report();


    //saves orders based on ID
    private HashMap<String, Order> dailyOrders = new HashMap<>();

    //saves order collections based on Date
    private HashMap<String, HashMap<String, Order>> dailyReports = new HashMap<>();

    private HashMap<Integer, Cash> cashBalance = new HashMap<>();

    private HashMap<String, Product> inventory = new HashMap<>();

    private Money totalBalance = new Money(0);

    //slots for Cash
    private static final List<Cash> ACCEPTED_DENOMINATIONS = Arrays.asList(
            new Cash(new Money(1, 0), 0),
            new Cash(new Money(5, 0), 0),
            new Cash(new Money(10, 0), 0),
            new Cash(new Money(20, 0), 0),
            new Cash(new Money(50, 0), 0),
            new Cash(new Money(100, 0), 0),
            new Cash(new Money(200, 0), 0),
            new Cash(new Money(500, 0), 0),
            new Cash(new Money(1000, 0), 0));

    public Register(Store store, Product... products) {
        this.store = store;
        for (Cash cash : ACCEPTED_DENOMINATIONS)
            this.cashBalance.putIfAbsent(cash.getDenomination().getAmountOfCrown(), cash);
        for (Product p : products) {
            p.addStore(store);
            inventory.put(p.getId(), p);
        }
    }

    public Order scanProductsForOrder(Product... products) {
        Order order = new Order(products);
        for (Product product : products) {
            ;
            inventory.remove(product.getId());
        }
        return order;
    }

    public void cancelOrderAfterScan(Order order) {
        System.out.print("""


                Order cancelled, Receipt:
                """);
        for (Product product : order.getProductsToOrder()) {
            inventory.put(product.getId(), product);
        }
        Collection<Product> cancelledProducts = order.getProductsToOrder();
        order.getProductsToOrder().removeAll(cancelledProducts);
        order.resetCurrentTotal();
        order.setDateOfOrder();
        printReceipt(order);
    }

    public void payForOrderTF1TF4(Order order, Payment... payments) {
        Customer customer = order.getCustomer();
        String paymentConfirmation = waitForPaymentConfirmationYes();
        boolean paid = false;
        if (paymentConfirmation.equalsIgnoreCase("n")) checkIfCancelOfOrderRequestedNo(order);
        if (paymentConfirmation.equalsIgnoreCase("y")) paid = order.payTotalForProducts(payments);
        if (!paid && paymentConfirmation.equalsIgnoreCase("y")) {
            System.out.print("\nInsufficient payment, please try again");
            checkIfCancelOfOrderRequestedNo(order);
        }
        if (paid) {
            if (!customer.isMember()) checkIfCustomerWantsMembershipNo();
            if (customer.isMember()) {
                customer.getMembership().getMembershipPoints().addPoints((int)
                        getPointsForOrder(order.getCurrentPayment().getAmountInOre()));
                System.out.print("\nPoints for purchase: " + (int) getPointsForOrder(order.getCurrentPayment().getAmountInOre()));
            }
            totalBalance = totalBalance.add(order.getCurrentPayment());
            addToDailyReports(order);
            System.out.print("""


                    Payment completed, Receipt:
                    """);
            printReceipt(order);
        }
    }


    public void payForOrderTF2(Order order, Payment... payments) {
        Customer customer = order.getCustomer();
        String paymentConfirmation = waitForPaymentConfirmationYes();
        boolean paid = false;
        if (paymentConfirmation.equalsIgnoreCase("n")) checkIfCancelOfOrderRequestedNo(order);
        if (paymentConfirmation.equalsIgnoreCase("y")) paid = order.payTotalForProducts(payments);
        if (!paid && paymentConfirmation.equalsIgnoreCase("y")) {
            System.out.print("\nInsufficient payment, please try again");
            checkIfCancelOfOrderRequestedNo(order);
        }
        if (paid) {
            if (!customer.isMember()) checkIfCustomerWantsMembershipYes(order.getCustomer());
            if (customer.isMember()) {
                customer.getMembership().getMembershipPoints().addPoints((int)
                        getPointsForOrder(order.getCurrentPayment().getAmountInOre()));
                System.out.print("\nPoints for purchase: " + (int) getPointsForOrder(order.getCurrentPayment().getAmountInOre()));
            }
            totalBalance = totalBalance.add(order.getCurrentPayment());
            addToDailyReports(order);
            System.out.print("""


                    Payment completed, Receipt:
                    """);
            printReceipt(order);
        }
    }

    public void payForOrderTF3(Order order, Payment... payments) {
        Customer customer = order.getCustomer();
        String paymentConfirmation = waitForPaymentConfirmationYes();
        boolean paid = false;
        if (paymentConfirmation.equalsIgnoreCase("n")) checkIfCancelOfOrderRequestedNo(order);
        if (paymentConfirmation.equalsIgnoreCase("y")) paid = order.payTotalForProducts(payments);
        if (!paid && paymentConfirmation.equalsIgnoreCase("y")) {
            System.out.print("\nInsufficient payment, please try again");
            checkIfCancelOfOrderRequestedNo(order);
            payForOrderTF3(order, getPaymentInputDebitCard(order, order.getCurrentTotal().getAmountOfCrown(), order.getCustomer()));
        }
        if (paid) {
            if (!customer.isMember()) checkIfCustomerWantsMembershipNo();
            if (customer.isMember()) {
                customer.getMembership().getMembershipPoints().addPoints((int)
                        getPointsForOrder(order.getCurrentPayment().getAmountInOre()));
                System.out.print("\nPoints for purchase: " + (int) getPointsForOrder(order.getCurrentPayment().getAmountInOre()));
            }
            totalBalance = totalBalance.add(order.getCurrentPayment());
            addToDailyReports(order);
            System.out.print("""


                    Payment completed, Receipt:
                    """);
            printReceipt(order);
        }
    }

    public void payForOrderTF7TF8(Order order, Payment... payments) {
        Customer customer = order.getCustomer();
        String paymentConfirmation = waitForPaymentConfirmationNo();
        boolean paid = false;
        if (paymentConfirmation.equalsIgnoreCase("n")) checkIfCancelOfOrderRequestedYes(order);
        if (paymentConfirmation.equalsIgnoreCase("y")) paid = order.payTotalForProducts(payments);
        if (!paid && paymentConfirmation.equalsIgnoreCase("y")) {
            System.out.print("\nInsufficient payment, please try again");
            checkIfCancelOfOrderRequestedNo(order);
        }
        if (paid) {
            if (!customer.isMember()) checkIfCustomerWantsMembershipNo();
            if (customer.isMember()) {
                customer.getMembership().getMembershipPoints().addPoints((int)
                        getPointsForOrder(order.getCurrentPayment().getAmountInOre()));
                System.out.print("\nPoints for purchase: " + (int) getPointsForOrder(order.getCurrentPayment().getAmountInOre()));
            }
            totalBalance = totalBalance.add(order.getCurrentPayment());
            addToDailyReports(order);
            System.out.print("""


                    Payment completed, Receipt:
                    """);
            printReceipt(order);
        }
    }


    public void checkIfCancelOfOrderRequestedYes(Order order) {
        System.out.print("""
                    
                    Cancel order: (Y/N)""");
        System.out.print("\ny");
        cancelOrderAfterScan(order);
    }

    public void checkIfCancelOfOrderRequestedNo(Order order) {
        System.out.print("""
                    
                    Cancel order: (Y/N)""");
        System.out.print("\nn");
    }

    public String waitForPaymentConfirmationNo() {
        System.out.print("""
                    
                    Confirm payment: (Y/N)""");
        System.out.print("\nn");
        return "N";
    }

    public String waitForPaymentConfirmationYes() {
        System.out.print("""
                    
                    Confirm payment: (Y/N)""");
        System.out.print("\ny");

        return "Y";
    }

    public void checkIfCustomerWantsMembershipNo() {
        System.out.print("""
                    
                    Would you like to become a member?: (Y/N)""");
        System.out.print("\nn");
    }

    public void checkIfCustomerWantsMembershipYes(Customer customer) {
        System.out.print("""
                    
                    Would you like to become a member?: (Y/N)""");
        System.out.print("\ny");
        customer.addMembership();
    }
/*
    public Payment getPaymentInputCash(Order order, int amount, Customer customer) {
        order.setCustomer(customer);
        System.out.print("\nChoose payment method:");
        System.out.print("\ncash");
        return selectPaymentMethod(customer, amount, "cash");
    }
*/
    public Payment getPaymentInputDebitCard(Order order, int amount, Customer customer) {
        order.setCustomer(customer);
        System.out.print("\nChoose payment method:");
        System.out.print("\ndebitcard");
        return selectPaymentMethod(customer, amount, "debitcard");
    }

    public Payment getPaymentInputGiftCard(Order order, int amount, Customer customer) {
        order.setCustomer(customer);
        System.out.print("\nChoose payment method:");
        System.out.print("\ngiftcard");
        return selectPaymentMethod(customer, amount, "giftcard");
    }

    public Payment getPaymentInputPoints(Order order, int amount, Customer customer) {
        order.setCustomer(customer);
        System.out.print("\nChoose payment method:");
        System.out.print("\npoints");
        return selectPaymentMethod(customer, amount, "points");
    }


    public Payment selectPaymentMethod(Customer customer, int amount, String type) {
        Payment payment = new Payment(new Money(amount, 0));
        if (type.equalsIgnoreCase("giftcard")) {
            presentGiftCardBalance(customer.getGiftCard());
            payment = new Payment(new Money(amount, 0), customer.getGiftCard());
        }
        if (type.equalsIgnoreCase("debitcard")) {
            payment = new Payment(new Money(amount, 0), customer.getDebitCard());
        }
            if (type.equalsIgnoreCase("points")) {
            presentPointBalance(customer);
            payment = new Payment(new Money(amount, 0), customer);
        }
        return payment;
    }


    public void presentPointBalance(Customer customer) {
        if (customer.getMembership() != null) System.out.print("\n" + customer.getMembership().getMembershipPoints().toString());
    }

    public void presentGiftCardBalance(GiftCard giftCard) {
        System.out.print("\n" + giftCard);
    }

    public void presentTotal(Order order) {
        String totalInfo = "Products: " + order.getProductsAsString()+
                "\nTotal with VAT: " + order.getCurrentTotal();
        totalInfo = totalInfo.replaceAll(",", "");
        System.out.print(totalInfo.replaceAll("[\\[\\]]", ""));
        checkIfCancelOfOrderRequestedNo(order);
    }

    public void presentTotalCancel(Order order) {
        String totalInfo = "Products: " + order.getProductsAsString()+
                "\nTotal with VAT: " + order.getCurrentTotal();
        totalInfo = totalInfo.replaceAll(",", "");
        System.out.print(totalInfo.replaceAll("[\\[\\]]", ""));
        checkIfCancelOfOrderRequestedYes(order);
    }
    

    //literally just a system out print of order.getInfo
    public void printReceipt(Order order){
        Receipt receipt = new Receipt(order, store);
        System.out.print(receipt.print());
    }

    //logs orders
    public void addToDailyReports(Order order){
        dailyOrders.put(order.getOrderId(), order);
        dailyReports.put(order.getDateOfOrder(), dailyOrders);
    }

     //add points kollar membership, returnerar void
    public double getPointsForOrder(int costOfOrder){
        if(costOfOrder < 100){
            return costOfOrder * 0.1;
        }
        else if(costOfOrder < 1000){
            return costOfOrder * 0.2;
        }
        else if(costOfOrder < 10000){
            return costOfOrder * 0.3;
        }else{
            return costOfOrder * 0.4;
        }
    }

    public Collection<Product> getInventory() {
        return Collections.unmodifiableCollection(inventory.values());
    }

    public Collection<Cash> getCashBalance() {
        return Collections.unmodifiableCollection(cashBalance.values());
    }

}
