package inte.project;

public interface Vat25 extends Vat{

    default double getVat() {
        return 0.25;
    }


}
