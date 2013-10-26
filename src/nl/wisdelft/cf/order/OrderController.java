package nl.wisdelft.cf.order;

import nl.wisdelft.cf.datamodel.*;

public interface OrderController {

    public void create(Order aOrder);

    public String retrieve(String aJobId);

    public void pause(String aJobId);

    public void resume(String aJobId);

    public void cancel(String aJobId);

}
