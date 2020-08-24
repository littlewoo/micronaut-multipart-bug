package com.github.littlewoo.multipartbug;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.server.multipart.MultipartBody;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.security.Principal;

@Controller
@Secured(SecurityRule.IS_AUTHENTICATED)
public class BobController {

    @Post(value = "/bob", consumes = MediaType.MULTIPART_FORM_DATA)
    public Single<HttpResponse<?>> bob(Principal auth, @Body MultipartBody body) {
        System.out.println("receiving multipart request");
        System.out.println(auth);
        return Flowable.fromPublisher(body)
                   .toList()
                   .map(HttpResponse::ok);
    }
}
