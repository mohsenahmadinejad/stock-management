package com.payconiq.stockmanagement.config;

import org.springframework.data.domain.AuditorAware;


import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        String  username ="root";
        return Optional.of( username);
    }
}

