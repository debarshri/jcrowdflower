package nl.wisdelft.cf.datamodel;

import com.google.common.collect.*;
import org.apache.http.message.*;

import java.util.*;

public class Order {

    private String theJobId;

    private Map<String, String> theAttributes;

    public Order()
    {
        theJobId = "";
        theAttributes = Maps.newHashMap();
    }

    public Order(String aJobId)
    {
        theJobId = aJobId;
        theAttributes = Maps.newHashMap();
    }

    public Order(String aJobId, Map<String, String> aAttributes)
    {
        theJobId = aJobId;
        theAttributes = aAttributes;
    }

    public void addChannel(String channel)
    {
        theAttributes.put("channels[0]", channel);
    }

    public void setDebitUnitCount(String count)
    {
        theAttributes.put("debit[units_count]", count);
    }

    public String getJobId()
    {
        return theJobId;
    }

    public Map<String, String> getAttributes()
    {
        return theAttributes;
    }
}
