package inte.project;

// ser till att all hantering av pengar hanteras konsekvent i hela systemet.
public class Money implements Comparable<Money> {

    private static final int ORE_PER_CROWN = 100;
    private long amount;

    public Money(long amount){
        this.amount = amount;
    }

    public int compareTo(Money other){
        return 0;
    }
}
