package com.vincennlin.aimahjongbackend.payload.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "UserDto",
        description = "用戶的 Data Transfer Object"
)
public class UserDto {

    @Schema(
            name = "id",
            description = "用戶 id",
            example = "1"
    )
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Schema(
            name = "name",
            description = "用戶暱稱",
            example = "user"
    )
    private String name;

    @Schema(
            name = "username",
            description = "用戶帳號名稱",
            example = "user"
    )
    private String username;
}
