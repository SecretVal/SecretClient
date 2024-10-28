package secretclient.event;

public class Event {
    private Boolean cancelled = false;

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }
}
