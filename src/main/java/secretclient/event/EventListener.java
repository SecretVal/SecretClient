package secretclient.event;

public class EventListener {
    public Object source;
    public Object[] args;

    public EventListener(Object source, Object[] args) {
        this.source = source;
        this.args = args;
    }

}
