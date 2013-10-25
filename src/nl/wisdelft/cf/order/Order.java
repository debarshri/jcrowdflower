package nl.wisdelft.cf.order;

public interface Order {

    // Check Parameters
    public void create();

    public String retrieve(String id);

    public void addChannel(String channel);

    public void setDebitUnitCount(String count);

}
