package com.samtel.core.services;

import com.samtel.ports.primary.KeepAliveService;
import org.springframework.stereotype.Service;

@Service
public class KeepAliveServiceImpl implements KeepAliveService {
    @Override
    public String getAlive() {
        return "Hi!  it´s alive";
    }
}
