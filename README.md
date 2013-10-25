JCrowdFlower
=======
JCrowdflower is a library for crowdflower web interface wrapped up in java.

This project is part of Web information Systems groups.


Credits to

- Prof. Alessandro Bozzon (project conceptualization)
- Prof. Jasper Oosterman (reviewer)
- Debarshi Basak (main developer)


### jCrowdFlower

This projects is a Java wrapper for web APIs exposed by CrowdFlower. This project is located in `master` branch.


### Tutorial

Simple job creation

#### Note
- Create a file called default.properties.
- Add a property apiKey="your-api-key"

Then the following is the implementation for creating a job

```
package nl.wisdelft.cf.acceptance.test.local.examples;

import nl.wisdelft.cf.*;
import nl.wisdelft.cf.datamodel.*;
import nl.wisdelft.cf.job.*;

public class CrowdFlowerTutorial {

    public static void main(String[] args)
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

        myJob.addProperty(JobAttribute.TITLE,"Test Job title");
        myJob.addProperty(JobAttribute.INSTRUCTIONS,"Test instructions");
        myJob.addProperty(JobAttribute.CML,"<cml>Test cml </cml>");

        Job myJobAfterCreation = myJobController.create(myJob);
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

