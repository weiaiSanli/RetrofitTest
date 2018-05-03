package netework;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * 类描述：X509TrustManage管理类
 * 创建人： shi
 * 创建时间:2017/6/21 13:19
 */
public class MyTrustManager implements TrustManager {

    public static TrustManager[] getWrappedTrustManagers(TrustManager[] trustManagers) {

        final X509TrustManager originalTrustManager = (X509TrustManager) trustManagers[0];

        return new TrustManager[]{

            new X509TrustManager() {

                public X509Certificate[] getAcceptedIssuers() {

                    return originalTrustManager.getAcceptedIssuers();

                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {

                    try{

                        originalTrustManager.checkClientTrusted(certs, authType);

                    }catch(CertificateException e) {

                        e.printStackTrace();

                    }

                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {

                    try{

                        originalTrustManager.checkServerTrusted(certs, authType);

                    }catch(CertificateException e) {

                        e.printStackTrace();

                    }

                }

            }

        };

    }





}
