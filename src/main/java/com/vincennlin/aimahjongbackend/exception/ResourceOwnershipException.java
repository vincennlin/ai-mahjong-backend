package com.vincennlin.aimahjongbackend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ResourceOwnershipException extends RuntimeException {

    private Long userId;

    public ResourceOwnershipException(Long userId) {
        super(String.format("Current user with id: '%d' cannot access resource owned by another user", userId));
        this.userId = userId;
    }
}
