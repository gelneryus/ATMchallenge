
package com.atm.console.config;

import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.UUID;

public class TraceIdInterceptor implements ClientHttpRequestInterceptor {

    public static final String TRACE_ID_HEADER = "X-Trace-Id";

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        String traceId = UUID.randomUUID().toString();
        MDC.put(TRACE_ID_HEADER, traceId);
        request.getHeaders().add(TRACE_ID_HEADER, traceId);
        return execution.execute(request, body);
    }
}
