package com.leadgen.tests.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CollectionUtils {

    public static <T> Stream<T> streamOf(Stream<T> stream) {
        return stream == null ? Stream.empty() : stream;
    }

    public static <T> Stream<T> streamOf(T... arr) {
        return arr == null || arr.length == 0 ? Stream.empty() : Stream.of(arr);
    }

    public static <T> Stream<T> concat(T[] arr1, T[] arr2) {
        return Stream.concat(streamOf(arr1), streamOf(arr2));
    }

    public static <T> Stream<T> concat(T[] arr1, T[] ...arr2) {
        return Stream.concat(streamOf(arr1), streamOf(arr2).flatMap(CollectionUtils::streamOf));
    }

    public static <T> Stream<T> streamOf(Collection<T> collection) {
        return collection == null || collection.isEmpty() ? Stream.empty() : collection.stream();
    }

    public static <T> Stream<T> concat(Collection<? extends T> collection1, Collection<? extends T> ...collection2) {
        return Stream.concat(streamOf(collection1), streamOf(collection2).flatMap(CollectionUtils::streamOf));
    }

    public static <T> Stream<T> concat(Stream<T> stream1, Stream<T> stream2) {
        return Stream.concat(stream1, stream2);
    }

    public static <T> Stream<T> concat(Stream<T> stream1, Stream<T> stream2, Stream<T> stream3) {
        return concat(Stream.concat(stream1, stream2), stream3);
    }

    public static <K, V> Stream<Map.Entry<K, V>> streamOf(Map<K, V> map) {
        return map == null || map.isEmpty() ? Stream.empty() : map.entrySet().stream();
    }

    public static <K, V> Map<K, V> unmodifiableMap(Map<K, V> map) {
        return map == null || map.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(map);
    }

    public static <T> Collection<T> unmodifiableCollection(Collection<T> collection) {
        return collection == null || collection.isEmpty()
                ? Collections.emptyList()
                : Collections.unmodifiableCollection(collection);
    }


    public static <T> Set<T> unmodifiableSet(Set<T> set) {
        return set == null || set.isEmpty()
                ? Collections.emptySet()
                : Collections.unmodifiableSet(set);
    }

    public static <T> Set<T> unmodifiableSetFromReusedSource(Set<T> set) {
        return Collections.unmodifiableSet(set);
    }

    public static <T> List<T> unmodifiableListFromReusedSource(List<T> list) {
        return Collections.unmodifiableList(list);
    }

    public static <T> List<T> unmodifiableList(List<T> list) {
        return list == null || list.isEmpty()
                ? Collections.emptyList()
                : Collections.unmodifiableList(list);
    }

    public static <T> Stream<T> streamOf(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    public static <T> Stream<T> streamOf(Iterator<T> iterator) {
        return streamOf(() -> iterator);
    }

    public static boolean contain(Collection<?> collection, Object value ) {
        if (Objects.isNull(collection) || collection.isEmpty()) {
            return false;
        }
        return collection.contains(value);
    }

    public static <T> Iterable<T> iterable(Stream<T> stream) {
        return stream::iterator;
    }

}
