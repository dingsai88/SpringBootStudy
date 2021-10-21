package com.ding.study.net.httpclient5;

/**
 * @author daniel 2021-10-20 0020.
 */

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.hc.client5.http.async.methods.SimpleHttpRequest;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.apache.hc.client5.http.async.methods.SimpleRequestBuilder;
import org.apache.hc.client5.http.async.methods.SimpleRequestProducer;
import org.apache.hc.client5.http.async.methods.SimpleResponseConsumer;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.client5.http.impl.async.MinimalHttpAsyncClient;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.config.Http1Config;
import org.apache.hc.core5.http.message.StatusLine;
import org.apache.hc.core5.http.nio.AsyncClientEndpoint;
import org.apache.hc.core5.http2.HttpVersionPolicy;
import org.apache.hc.core5.io.CloseMode;
import org.apache.hc.core5.reactor.IOReactorConfig;
import org.apache.hc.core5.util.Timeout;

/**
 * This example demonstrates pipelined execution of multiple HTTP/1.1 message exchanges.
 */
public class AsyncClientHttp1Pipelining {

    public static void main(final String[] args) throws Exception {

        final IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                .setSoTimeout(Timeout.ofSeconds(5))
                .build();

        final MinimalHttpAsyncClient client = HttpAsyncClients.createMinimal(
                HttpVersionPolicy.FORCE_HTTP_1, null, Http1Config.DEFAULT, ioReactorConfig);

        client.start();

        final HttpHost target = new HttpHost("httpbin.org");
        final Future<AsyncClientEndpoint> leaseFuture = client.lease(target, null);
        final AsyncClientEndpoint endpoint = leaseFuture.get(30, TimeUnit.SECONDS);
        try {
            final String[] requestUris = new String[] {"/", "/ip", "/user-agent", "/headers"};

            final CountDownLatch latch = new CountDownLatch(requestUris.length);
            for (final String requestUri: requestUris) {
                final SimpleHttpRequest request = SimpleRequestBuilder.get()
                        .setHttpHost(target)
                        .setPath(requestUri)
                        .build();

                System.out.println("Executing request " + request);
                endpoint.execute(
                        SimpleRequestProducer.create(request),
                        SimpleResponseConsumer.create(),
                        new FutureCallback<SimpleHttpResponse>() {

                            @Override
                            public void completed(final SimpleHttpResponse response) {
                                latch.countDown();
                                System.out.println(request + "->" + new StatusLine(response));
                                System.out.println(response.getBody());
                            }

                            @Override
                            public void failed(final Exception ex) {
                                latch.countDown();
                                System.out.println(request + "->" + ex);
                            }

                            @Override
                            public void cancelled() {
                                latch.countDown();
                                System.out.println(request + " cancelled");
                            }

                        });
            }
            latch.await();
        } finally {
            endpoint.releaseAndReuse();
        }

        System.out.println("Shutting down");
        client.close(CloseMode.GRACEFUL);
    }

}