package org.matterbot.configuration;

import lombok.Data;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@Configuration
@ConfigurationProperties(prefix = "http.client")
@Data
public class OkHttpClientConfig {
    private boolean verboseLogging;
    private boolean acceptAllCertificates;
    private boolean httpProxy;
    private String proxyHost;
    private int proxyPort;

    @Bean
    public OkHttpClient getHttpClient() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        if (verboseLogging) {
            clientBuilder.addInterceptor(getLogger());
        }

        if (acceptAllCertificates) {
            acceptAllCertificates(clientBuilder);
        }

        if (httpProxy) {
            clientBuilder.proxy(getHttpProxy());
        }

        return clientBuilder.build();
    }

    private Proxy getHttpProxy() {
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
    }

    private HttpLoggingInterceptor getLogger() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            private final Logger logger = LoggerFactory.getLogger(HttpLoggingInterceptor.class);

            @Override
            public void log(@NotNull final String message) {
                logger.info(message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return interceptor;
    }

    private void acceptAllCertificates(final OkHttpClient.Builder clientBuilder) {
        SSLContext trustAllSslContext;
        try {
            trustAllSslContext = SSLContext.getInstance("SSL");
            trustAllSslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException(e);
        }
        SSLSocketFactory trustAllSslSocketFactory = trustAllSslContext.getSocketFactory();

        clientBuilder.sslSocketFactory(trustAllSslSocketFactory, (X509TrustManager) trustAllCerts[0])
                .hostnameVerifier((hostname, session) -> true);
    }

    private static final TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(final java.security.cert.X509Certificate[] chain,
                                               final String authType) {
                }

                @Override
                public void checkServerTrusted(final java.security.cert.X509Certificate[] chain,
                                               final String authType) {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }
            }
    };
}
