package org.example;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Request {
    private final String method;
    private final String uri;
    private final String ver;
    private final String path;
    private final List<NameValuePair> queryParams;

    public Request(String requestLine) throws IOException, URISyntaxException {
        final var parts = requestLine.split(" ");
        method = parts[0];
        uri = parts[1];
        ver = parts[2];
        URIBuilder uriBuilder = new URIBuilder(URI.create(uri));
        path = uriBuilder.getPath();
        queryParams = uriBuilder.getQueryParams();
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getVer() {
        return ver;
    }

    public String getPath() { return path; }

    public List<String> getQueryParam(String name) {
        List<String> queryParam = new ArrayList<>();
        for (NameValuePair qp :
                queryParams) {
            if (qp.getName().equals(name)) {
                queryParam.add(qp.getValue());
            }
        }
        return queryParam;
    }

    public List<NameValuePair> getQueryParams() {
        return queryParams;
    }
}
