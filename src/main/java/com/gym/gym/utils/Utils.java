package com.gym.gym.utils;

import com.gym.gym.base.model.DropdownDto;
import com.gym.gym.enums.Roles;
import lombok.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Utils {

    public static String parameterizeMessage(String msg) {
        Pattern p = Pattern.compile("\\<([\\w.$]+|\"[^\"]+\"|'[^']+')>",
                Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
        return p.matcher(msg).replaceAll("%s");
    }

    public static @NonNull List<DropdownDto> rolesList() {
        return Arrays.stream(Roles.values())
                .map(x -> DropdownDto.builder().value(x.name()).label(x.getId().toString()).build())
                .collect(Collectors.toList());
    }
}
