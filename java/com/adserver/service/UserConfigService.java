package com.adserver.service;

import org.springframework.stereotype.Service;

@Service("configService")
public class UserConfigService implements ConfigService {

    public String sayHello() {
        return "Phew Helloooooooo!!!!!!";
    }
}
