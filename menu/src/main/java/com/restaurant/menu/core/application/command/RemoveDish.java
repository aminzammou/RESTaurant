package com.restaurant.menu.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class RemoveDish {
    public UUID id;
}
