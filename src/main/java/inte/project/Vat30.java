package inte.project;

public interface Vat30 extends Vat{

    default double getVat() {
        return 0.30;
    }
}
