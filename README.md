JCrowdFlower
=======
JCrowdflower is a library for crowdflower web interface wrapped up in java.

This project is part of Web information Systems groups.


Credits to

- Prof. Alessandro Bozzon (project conceptualization)
- Prof. Jasper Oosterman (reviewer)
- Debarshi Basak (main developer)

![tudelft](http://www.se.ewi.tudelft.nl/dmcd2011/images/TU-Delft_logo.gif)

### jCrowdFlower

This projects is a Java wrapper for web APIs exposed by CrowdFlower. This project is located in `master` branch.

You need to build the project as follows

```  mvn clean install ```


### Tutorial

Simple job creation

#### Note
- Create a file called default.properties.
- Add a property apiKey="your-api-key"
- Include this project in your pom.xml as follows

```
<dependency>
    <groupId>crowdflower-java</groupId>
    <artifactId>crowdflower-java</artifactId>
    <version>1.0.1-SNAPSHOT</version>
<dependency>
```

Then the following is the implementation for creating a job

```
package nl.wisdelft.cf.acceptance.test.local.examples;

import nl.wisdelft.cf.*;
import nl.wisdelft.cf.datamodel.*;
import nl.wisdelft.cf.exception.*;
import nl.wisdelft.cf.job.*;
import nl.wisdelft.cf.order.*;

public class CrowdFlowerTutorial {

    public static void main(String[] args) throws NullAPIKeyException, InterruptedException
    {
        /**
         * Alternatively you can declare it as
         * CrowdFlowerFactory.setProperties("/path/to/properties/file");
         */

        JobController myJobController = CrowdFlowerFactory.getJobController();
        Job myJob = new Job();

        /**
         * You must have following three properties to create a job successfully
         */

        myJob.addProperty(JobAttribute.TITLE, "Test Job title");
        myJob.addProperty(JobAttribute.INSTRUCTIONS, "Test instructions");
        myJob.addProperty(JobAttribute.CML, "<cml>Test cml </cml>");
        myJob.addProperty(JobAttribute.PAGES_PER_ASSIGNMENT,
                          "0.1");
        myJob.addProperty(JobAttribute.UNITS_PER_ASSIGNMENT,
                          "1");

        Job myJobAfterCreation = myJobController.create(myJob);

        myJobController.upload(myJobAfterCreation, "log/crowd.csv", "text/csv");

        OrderController order = myJobController.order(myJobAfterCreation.getId());

        order.setDebitUnitCount("3");
        order.addChannel("amt");

        /*
        * Costs money
        */

        order.create();
        /**
         * Takes some time to create
         */
        order.cancel(myJobAfterCreation.getId());
    }
}

```



### Crowdflower behaviour

The Crowdflower API is running under version number 2. Error handling is however not yet consistent or clear. Some problems on the Crowdflower side return HTTP error. Other errors return a HTTP 200 with a error attribute in the (JSON) body of the response.

When updating an entity we have seen the following behaviour:

* Unknown attributes are ignored
* Read-only atributes are ignored
* When an error attribute is returned no fields have been updated
* When a HTTP 200 reponse is returned with no error attribute all attributes are updated.

