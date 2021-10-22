package inte.project;
//author Dan Jensen

//**STUB**//

public class Receipt {

    private final Purchase purchase;

    private final Store store;

    public Receipt(Purchase purchase, Store store){
        this.purchase = purchase;
        this.store = store;
    }

    public void print(){
        String receipt = store.toString() + "\n" + purchase.getInfo().replaceAll("[\\[\\]]", "");
        System.out.print(receipt);
    }
}
