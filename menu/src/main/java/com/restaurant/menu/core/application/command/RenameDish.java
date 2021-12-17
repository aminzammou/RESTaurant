package com.restaurant.menu.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class RenameDish {
    private final UUID id;
    private final String name;
}
