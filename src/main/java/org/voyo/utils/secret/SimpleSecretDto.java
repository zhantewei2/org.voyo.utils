package org.voyo.utils.secret;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Setter
@Getter
@ToString
public class SimpleSecretDto {
    @NotBlank
    private String nonStr;
    @NotNull
    private Long timestamp;
    @NotBlank
    private String appid;
}
