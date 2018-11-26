package performance.tests;

import net.lightbody.bmp.BrowserMobProxy;
import net.thucydides.core.annotations.Title;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import performance.utils.proxy.ProxyUtils;

/**
 * Created by andriy.chornyak on 11/20/2018.
 */

public class FirstTest extends BaseTest {
    private BrowserMobProxy proxy;

    @Before
    public void startProxy() {
        test.initDriver();
        proxy = ProxyUtils.getProxy();
    }

    @After
    public void stopProxy() {
        proxy.stop();
    }

    @Test
    @Title("Performance. Front fage of DOU - Test")
    public void frontPageTest() {
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        String strFilePath = "target/" + methodName + ".har";

        proxy.newHar(methodName);
        test.openFrontPage();

        ProxyUtils.pushToHarStorage(strFilePath, proxy.getHar());
    }
}
