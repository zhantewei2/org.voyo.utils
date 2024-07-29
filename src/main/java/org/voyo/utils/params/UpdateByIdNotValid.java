package org.voyo.utils.params;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Setter
@Getter
public class UpdateByIdNotValid<T,S> {
    @NotNull
    private T id;
    @NotNull
    private S body;
}
