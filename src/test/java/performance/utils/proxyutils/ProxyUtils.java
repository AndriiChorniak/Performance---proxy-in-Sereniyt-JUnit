package performance.utils.proxyutils;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.openqa.selenium.Proxy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * Created by andriy.chornyak on 11/20/2018.
 */
public class ProxyUtils {
    private static BrowserMobProxy proxy;
    private static boolean isExecuted = false;

    public static void initProxyServer() {
        if (!isExecuted) {
            proxy = new BrowserMobProxyServer();
            proxy.setTrustAllServers(true);
            proxy.setHarCaptureTypes(CaptureType.getAllContentCaptureTypes());
            proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
            proxy.start(0);
            isExecuted = true;
        } else {
            System.out.println("BrowserMob is already started! You can not start one more time!");
        }
    }

    public static BrowserMobProxy getProxy() {
        return proxy;
    }

    public static Proxy getSeleniumProxy(BrowserMobProxy proxyServer) {
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxyServer);
        String hostIp = null;
        try {
            hostIp = Inet4Address.getLocalHost().getHostAddress();
            seleniumProxy.setHttpProxy(hostIp + ":" + proxyServer.getPort());
            seleniumProxy.setSslProxy(hostIp + ":" + proxyServer.getPort());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            Assert.fail("Invalid host addresss");
        }
        return seleniumProxy;
    }

    public static void pushToHarStorage(String filePath, Har har) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath);
            har.writeTo(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String mongoHost = "http://52.17.179.195:5000";
            //Post to mongo server which stores Har file
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(mongoHost + "/results/upload");

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("file", new File(filePath));
            HttpEntity multipart = builder.build();
            post.setEntity(multipart);
            //entity.addPart("file", new FileBody(new File(filePath), ContentType.DEFAULT_BINARY));
            //post.setEntity(entity);

            //send post request to mongo server
            CloseableHttpResponse response = client.execute(post);
            client.close();
            Assert.assertEquals(302, response.getStatusLine().getStatusCode());

        } catch (IOException ex) {
            System.out.println("Error: ");
            System.out.println(ex.getMessage());
        }
    }
}
