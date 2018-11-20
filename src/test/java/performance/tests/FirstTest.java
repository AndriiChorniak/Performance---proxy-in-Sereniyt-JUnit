package performance.tests;

import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;

/**
 * Created by andriy.chornyak on 11/20/2018.
 */

public class FirstTest extends BaseTest {

    @Test
    @Title("Performance. Front fage of DOU - Test")
    @WithTags({
            @WithTag("Tasks")
    })
    public void frontPageTest() {
        test.openFrontPage();
    }
}
