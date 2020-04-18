import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class DemoTest {
    @Test
    public void demoTest() {
        boolean isDemo = true;
        log.info("Demo test is running.");
        assertThat(isDemo, is(true));
    }
}
