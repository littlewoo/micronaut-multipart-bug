package com.github.littlewoo.multipartbug;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.multipart.MultipartBody;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import io.micronaut.test.annotation.MicronautTest;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;

@MicronautTest
public class MicronautMultipartBugTest {

    @Inject
    @Client("/")
    RxHttpClient client;

    @Test
    void testBob() {
        AccessRefreshToken token = client.toBlocking().exchange(HttpRequest.POST("/login", new UsernamePasswordCredentials("bob", "password")),
                                                   AccessRefreshToken.class)
                                         .getBody()
                                         .orElseThrow();

        MultipartBody body = MultipartBody.builder().addPart("Name", "bob").build();
        MutableHttpRequest request = HttpRequest.POST("/bob", body)
                                         .contentType(MediaType.MULTIPART_FORM_DATA)
                                         .bearerAuth(token.getAccessToken());

        client.toBlocking().exchange(request);

    }

}
