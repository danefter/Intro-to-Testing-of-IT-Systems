package inte.project;
//author Dan Jensen
//**STUB**//
import java.util.Scanner;

public class ScannerInput {

    private final Scanner scanner = new Scanner(System.in);

    public ScannerInput(){}

    public Scanner getScanner() {
        return scanner;
    }

    public Product[] scanProducts(Product... products){
        return products.clone();
    }

    public Payment[] inputPayments(Payment... payments){
        return payments.clone();
    }
}
