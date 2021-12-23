package com.restaurant.order.infrasructure.driven.storage.user;

import com.restaurant.order.core.ports.storage.UserRepository;
import org.springframework.web.client.RestTemplate;
import java.net.URI;

public class HttpUserRepository implements UserRepository {
    private final String rootPath;
    private final RestTemplate client;

    public HttpUserRepository(String rootPath, RestTemplate client) {
        this.rootPath = rootPath;
        this.client = client;
    }

    @Override
    public boolean isLoggedIn(String token) {
        URI uri = URI.create(this.rootPath + "/auth/query/find-user");
        UserResult result = this.client.getForObject(uri, UserResult.class);

        return result != null;
    }
}