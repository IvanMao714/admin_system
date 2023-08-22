package com.gsiv.springboot.utils;

import cn.hutool.core.collection.CollUtil;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @projectName: admin_system_back
 * @package: com.gsiv.springboot.utils
 * @className: CollectionUtils
 * @author: 65151
 * @description: Collection 工具类
 * @date: 2023-08-15 22:52
 * @version: 1.0
 */
public class CollectionUtils {

    public static <T, U> Set<U> convertSet(Collection<T> from, Function<T, U> func) {
        if (CollUtil.isEmpty(from)) {
            return new HashSet<>();
        }
        return from.stream().map(func).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    public static <T, U> Set<U> convertSet(Collection<T> from, Function<T, U> func, Predicate<T> filter) {
        if (CollUtil.isEmpty(from)) {
            return new HashSet<>();
        }
        return from.stream().filter(filter).map(func).filter(Objects::nonNull).collect(Collectors.toSet());
    }

}
