package inte.project;

// ser till att all hantering av pengar hanteras konsekvent i hela systemet.
// Hanterar endast SEK
public class Money implements Comparable<Money> {

    private static final int ORE_PER_CROWN = 100;
    private int amountInOre;

    public Money(int amountInOre){
        this.amountInOre = amountInOre;
    }

    public Money(int crown, int ore){
        this.amountInOre = crown*ORE_PER_CROWN + ore;
    }
    private int getAmountOfCrown(){
        return amountInOre /ORE_PER_CROWN;
    }
    private int getAmountOfOre(){
        return amountInOre %ORE_PER_CROWN;
    }

    public Money add(int ore){
        return (new Money(amountInOre + ore));
    }
    //Tar ett Money object och anropar första add metoden med dess värde.
    public Money add(Money m){
        return add(m.amountInOre);
    }
    public Money subtract(int ore){
        return new Money (amountInOre - ore);
    }
    //tar ett money object och anropar första subtract metoden med dess värde.
    public Money subtract(Money m){
        return subtract(m.amountInOre);
    }

    public int getAmountInOre(){
        return amountInOre;
    }

    public int compareTo(Money other){
        return amountInOre - other.amountInOre;
    }

    public String toString(){
        return String.format("%s:%02d kr", getAmountOfCrown(), getAmountOfOre());
    }
}
