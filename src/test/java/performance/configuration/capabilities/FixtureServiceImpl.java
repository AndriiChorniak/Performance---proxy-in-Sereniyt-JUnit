package performance.configuration.capabilities;

import net.thucydides.core.fixtureservices.FixtureException;
import net.thucydides.core.fixtureservices.FixtureService;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import performance.utils.proxyutils.ProxyUtils;

/**
 * Created by andriy.chornyak on 11/20/2018.
 */
public class FixtureServiceImpl implements FixtureService {
    @Override
    public void setup() throws FixtureException {

    }

    @Override
    public void shutdown() throws FixtureException {

    }

    @Override
    public void addCapabilitiesTo(DesiredCapabilities desiredCapabilities) {
        System.out.println("---------------------------------------------Capabilities-------------------------------");

        String performanceTest = System.getProperty("proxy.turnedOn");

        if (!Boolean.valueOf(performanceTest)) {
            System.out.println("---------------------------------without proxy----------------");
        }
        if (Boolean.valueOf(performanceTest)) {
            System.out.println("----------------------------------------------PROXY---------------------------------");
            ProxyUtils.initProxyServer();
            Proxy seleniumProxy = ProxyUtils.getSeleniumProxy(ProxyUtils.getProxy());
            desiredCapabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
            desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        }
        /*try {
            desiredCapabilities.setCapability(CapabilityType.PROXY, ProxyUtils.initProxyServer().seleniumProxy());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }*/

        /*LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);*/
    }
}
