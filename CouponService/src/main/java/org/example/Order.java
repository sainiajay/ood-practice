package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
public class Order {

    @Getter
    @NonNull
    private Integer orderValue;

    @Getter
    @NonNull
    private String userId;

}
