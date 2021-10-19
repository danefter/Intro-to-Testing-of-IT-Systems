package inte.project;
//author Marah Zeibak
public interface Vat30 extends Vat{

    default double getVat() {
        return 0.30;
    }
}
