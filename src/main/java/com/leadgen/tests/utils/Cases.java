package com.leadgen.tests.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * В этот класс нужно добавлять произвольное представление того, какой тест-кейс будет использоваться.
 *  Строковый 'param' - является точной копией строки-ключа, который нужно добавить в конфигурационный файлы
 *  -> conf/prod/qa
 */
@RequiredArgsConstructor
public enum Cases {

    //TODO:: add new cases for test here. See conf/environment/*.conf

    LEAD_DEFAULT("lead_default"),
    LEAD_NOT_DEFAULT("lead_non_default"),
    LEAD_REJECTED("lead_rejected"),
    LEAD_SOLD("lead_sold"),
    LEAD_ASYNC("lead_async"),
    LEAD_WHITELISTED("lead_whitelisted"),
    LEAD_NON_WHITELISTED("lead_non_whitelisted"),
    LEAD_BLACKLISTED("lead_blacklisted"),
    ;

    @Getter
    private final String param;
}
