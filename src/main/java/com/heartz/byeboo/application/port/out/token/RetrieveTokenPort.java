package com.heartz.byeboo.application.port.out.token;

import com.heartz.byeboo.domain.model.Token;

public interface RetrieveTokenPort {
    Token retrieveTokenById(Long userId);
}
