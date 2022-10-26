package domain;

import java.time.LocalDateTime;

import static util.Constants.DEFAULT_ALERT_TIME;

public class Event extends Task {

    public enum AlertType{
        SCREEN, SOUND, EMAIL, NO_ALERT
    }

    private String location;
    private LocalDateTime dateTime;
    private boolean hasAlert;
    private AlertType alertType;
    private int alertTime;


    public Event(String name, String description, String location, LocalDateTime dateTime,int minutes, User owner) {
        super(name, description,minutes, owner);
        this.location = location;
        this.dateTime = dateTime;

        hasAlert = false;
    }

    public Event(String name, String description, String location, LocalDateTime dateTime, int minutes, User owner, boolean hasAlert) {
        this(name, description, location, dateTime,minutes, owner);
        if (hasAlert) {
            this.alertType = AlertType.values()[0];
            this.alertTime = DEFAULT_ALERT_TIME;
        }
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isHasAlert() {
        return hasAlert;
    }

    public void setHasAlert(boolean hasAlert) {
        this.hasAlert = hasAlert;
    }

    public AlertType getAlertType() {
        return alertType;
    }

    public void setAlertType(AlertType alertType) {
        this.alertType = alertType;
    }

    public int getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(int alertTime) {
        this.alertTime = alertTime;
    }


    @Override
    public int hashCode(){
        return String.valueOf(super.hashCode()).concat(dateTime.toString()).hashCode();
    }

    @Override
    public boolean equals(Object object){
        if (object == null){
            return false;
        }
        if (!(object instanceof Event)){
            return false;
        }

        Event event = (Event) object;
        return event.getName().equals(name) && (event.getDateTime().equals(dateTime));
    }

    @Override
    public String toString (){
        return super.toString() + " [" + dateTime.toString() + "]";
    }
    public boolean hasAlert() {
        return hasAlert;
    }

    public void removeAlert() {
        hasAlert = false;
        alertTime = 0;
        alertType = AlertType.NO_ALERT;
    }

    public boolean isAlertTime(){
        return LocalDateTime.now().equals(dateTime);
    }

}
