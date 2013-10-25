package nl.wisdelft.cf.acceptance.test.local.account;

import nl.wisdelft.cf.*;
import org.junit.*;

import java.io.*;

import static org.fest.assertions.api.Assertions.assertThat;

public class AccountDetailsTest {

    @Ignore
    @Test
    public void test() throws IOException
    {
        CrowdFlower myCrowdFlower = new CrowdFlowerImpl();
        Account acc = myCrowdFlower.getAccount();
        assertThat(acc.get("company")).isNotNull();
    }

}
