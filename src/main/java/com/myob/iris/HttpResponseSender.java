package com.myob.iris;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public abstract class HttpResponseSender {
    public void sendResponse(HttpResponse response, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(response.getStatusCode(), response.getResponse().length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getResponse().getBytes());
        os.close();
    }
}
