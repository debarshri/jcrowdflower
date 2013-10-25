package nl.wisdelft.cf.datamodel;

import com.google.common.collect.*;

import nl.wisdelft.cf.unit.*;

import org.json.*;
import org.slf4j.*;

import java.util.*;

public class Unit {

    private String theUnitId;
    private String theJobId;
    private Map<String,String> theAttributes;
    private static final Logger LOGGER = LoggerFactory.getLogger(Unit.class);

    public Unit()
    {
        theUnitId = "";
        theJobId = "";
        theAttributes = Maps.newHashMap();
    }

    public Unit(final JSONObject aMeta)
    {
        theUnitId = "";
        theJobId = "";
        theAttributes = Maps.newHashMap();
        parse(aMeta);
    }

    private void parse(final JSONObject aMeta)
    {
        Iterator iterate = aMeta.keys();

        while (iterate.hasNext())
        {
            addAttributes(aMeta, iterate);
        }
    }

    public void addProperty(
            String property,
            String value)
    {
        if (property.equals("id"))
        {
            theUnitId = value;
        }

        if (property.equals("job_id"))
        {
            theJobId = value;
        }

        theAttributes.put("unit[" + property + "]", value);
    }

    public void addProperty(
            UnitAttribute property,
            String value)
    {
        addProperty(property.toString(),
                    value);
    }

    public String getAttribute(String property)
    {
        return theAttributes.get("unit[" + property + "]");
    }

    public String getAttribute(UnitAttribute property)
    {
        return getAttribute(property.toString());
    }

    public String getUnitId()
    {
        return theUnitId;
    }

    public String getJobId()
    {
        return theJobId;
    }

    public Map<String,String> getAttributes()
    {
        return theAttributes;
    }

    private void addAttributes(final JSONObject aMeta, final Iterator aIterate)
    {
        String key = (String) aIterate.next();
        try
        {
            addProperty(key,
                        aMeta.get(key).toString());
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
