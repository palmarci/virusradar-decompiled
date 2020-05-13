package p008hu.gov.virusradar.Utilities;

import android.util.Base64;
import java.io.IOException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.crypto.Cipher;

/* renamed from: hu.gov.virusradar.Utilities.EncriptionUtil */
public class EncriptionUtil {
    private static final String ASSETS_CERTIFICATE_PATH = "nscomcert.crt";

    public static String encryptMessage(String str) {
        try {
            PublicKey publicKey = getPublicKey();
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
            instance.init(1, publicKey);
            return Base64.encodeToString(instance.doFinal(str.getBytes()), 0);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static PublicKey getPublicKey() throws IOException, CertificateException {
        return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(VirusTraceApp.getInstance().getAssets().open(ASSETS_CERTIFICATE_PATH))).getPublicKey();
    }
}
