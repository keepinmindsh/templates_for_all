package lines.jacoco.sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JacocoCallTest {

    private JacocoCall jacocoCall = new JacocoCall();

    @Test
    public void setJacocoCallTest(){
        String value = jacocoCall.call("Hello");

        assertEquals(value, "Hello");
    }
}
