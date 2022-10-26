package domain;

public class ExpressEvent extends Task{

    private int minutesDelay;

    public ExpressEvent(String name, String description, User owner, int minutes, int minutesDelay) {
        super(name, description, minutes, owner);
        this.minutesDelay = minutesDelay;
    }

    public int getMinutesDelay (){
        return minutesDelay;
    }

    public void setMinutesDelay(int minutesDelay){
        this.minutesDelay = minutesDelay;
    }

    @Override
    public String toString (){
        return super.toString() + " [delay=" + minutesDelay + "]";
    }
}
