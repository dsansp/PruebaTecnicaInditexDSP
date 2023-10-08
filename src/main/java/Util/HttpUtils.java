package Util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Properties;

public class HttpUtils {
    private static final Logger LOGGER = LogManager.getLogger(HttpUtils.class);

    public static int getResponse(String url) throws IOException {
        try{
            doTrustToCertificates();
            URL urlObj = new URL(url);
            HttpURLConnection httpCon = (HttpURLConnection) urlObj.openConnection();
            httpCon.connect();
            return httpCon.getResponseCode();
        } catch (IOException e) {
            LOGGER.error("Error creating HTTP connection");
            e.printStackTrace();
            throw e;} catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public static void doTrustToCertificates() throws Exception {

        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                        return;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                        return;
                    }
                }
        };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HostnameVerifier hv = new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session) {
                if (!urlHostName.equalsIgnoreCase(session.getPeerHost())) {
                    LOGGER.error("Warning: URL host '" + urlHostName + "' is different to SSLSession host '" + session.getPeerHost() + "'.");
                }
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }

    public static String getClipboardContent() throws IOException, UnsupportedFlavorException {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        return (String) clipboard.getData(DataFlavor.stringFlavor);
    }


}
