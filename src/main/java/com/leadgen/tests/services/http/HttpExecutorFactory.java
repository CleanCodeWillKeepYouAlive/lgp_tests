package com.leadgen.tests.services.http;

import com.leadgen.tests.dto.Method;
import com.leadgen.tests.dto.RequestTemplate;
import com.leadgen.tests.dto.ResponseTemplate;
import com.leadgen.tests.utils.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
public class HttpExecutorFactory {

    private static final int DEFAULT_LGP_TIMEOUT = 180;

    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(8);

    private static final IResponse EMPTY_RESPONSE = ResponseTemplate.of(null, StringUtils.EMPTY, 0);


    public static List<IResponse> getMultiAsync(@Nonnull List<RequestTemplate> dataList) {
        final Map<RequestTemplate, CompletableFuture<IResponse>> futures = CollectionUtils.streamOf(dataList)
                .collect(Collectors.toMap(
                        Function.identity(),
                        request -> CompletableFuture.supplyAsync(responseSupplier(request, Method.GET), EXECUTOR),
                        (k1, k2) -> k1
                ));
        try {
            final CompletableFuture[] futuresArray = futures.values().toArray(new CompletableFuture[futures.size()]);
            CompletableFuture.allOf(futuresArray).get(DEFAULT_LGP_TIMEOUT, TimeUnit.SECONDS);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return CollectionUtils.streamOf(futures.entrySet())
                .map(e -> e.getValue().getNow(EMPTY_RESPONSE))
                .collect(Collectors.toList());
    }

    public static List<IResponse> postMultiAsync(@Nonnull List<RequestTemplate> dataList) {
        final Map<RequestTemplate, CompletableFuture<IResponse>> futures = CollectionUtils.streamOf(dataList)
                .collect(Collectors.toMap(
                        Function.identity(),
                        request -> CompletableFuture.supplyAsync(responseSupplier(request, Method.POST), EXECUTOR),
                        (k1, k2) -> k1
                ));
        try {
            final CompletableFuture[] futuresArray = futures.values().toArray(new CompletableFuture[futures.size()]);
            CompletableFuture.allOf(futuresArray).get(DEFAULT_LGP_TIMEOUT, TimeUnit.SECONDS);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return CollectionUtils.streamOf(futures.entrySet())
                .map(e -> e.getValue().getNow(EMPTY_RESPONSE))
                .collect(Collectors.toList());
    }

    private static Supplier<IResponse> responseSupplier(@Nonnull RequestTemplate request, Method method) {
        return () -> {
            try {
                return method.isGet() ? HttpFactory.doGet(request) : HttpFactory.doPost(request);
            } catch (Exception e) {
                log.error("Unable to get http response to {}. {}", request.getUri().toString(), e);
            }
            return EMPTY_RESPONSE;
        };
    }
}
