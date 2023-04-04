import java.util.UUID;

public abstract class Request {
    private UUID id;
    private boolean isSubmitted;

    public Request() {
        this.id = UUID.randomUUID();
        this.isSubmitted = false;
    }

    public UUID getId() {
        return id;
    }

    public boolean isSubmitted() {
        return isSubmitted;
    }

    public void setSubmitted(boolean submitted) {
        isSubmitted = submitted;
    }


    @Override
    public String toString() {
        return "Request: " + "\n" +
                " code= " + id + "\n" +
                ", isSubmitted=" + isSubmitted +  "\n";
    }
}
