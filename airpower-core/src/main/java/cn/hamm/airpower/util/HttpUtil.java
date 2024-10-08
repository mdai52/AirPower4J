package cn.hamm.airpower.util;

import cn.hamm.airpower.config.Constant;
import cn.hamm.airpower.enums.ContentType;
import cn.hamm.airpower.enums.HttpMethod;
import cn.hamm.airpower.exception.ServiceException;
import lombok.Data;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.Contract;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.*;

/**
 * <h1>{@code HTTP} 请求工具类</h1>
 *
 * @author Hamm.cn
 */
@Data
@Accessors(chain = true, makeFinal = true)
public class HttpUtil {
    /**
     * <h2>请求头</h2>
     */
    private Map<String, Object> headers = new HashMap<>();

    /**
     * <h2>{@code Cookie}</h2>
     */
    private Map<String, Object> cookies = new HashMap<>();

    /**
     * <h2>请求地址</h2>
     */
    private String url;

    /**
     * <h2>请求体</h2>
     */
    private String body = Constant.EMPTY_STRING;

    /**
     * <h2>请求方法</h2>
     */
    private HttpMethod method = HttpMethod.GET;

    /**
     * <h2>请求体类型</h2>
     */
    private ContentType contentType = ContentType.JSON;

    /**
     * <h2>连接超时时间</h2>
     */
    private int connectTimeout = 5;

    /**
     * <h2>添加 {@code Cookie}</h2>
     *
     * @param key   {@code Cookie} 键
     * @param value {@code Cookie} 值
     * @return {@code HttpUtil}
     */
    @Contract("_, _ -> this")
    public final HttpUtil addCookie(String key, String value) {
        cookies.put(key, value);
        return this;
    }

    /**
     * <h2>发送 {@code POST} 请求</h2>
     *
     * @return {@code HttpResponse}
     */
    public final HttpResponse<String> post() {
        method = HttpMethod.POST;
        return send();
    }

    /**
     * <h2>发送 {@code POST} 请求</h2>
     *
     * @param body 请求体
     * @return {@code HttpResponse}
     */
    public final HttpResponse<String> post(String body) {
        method = HttpMethod.POST;
        this.body = body;
        return send();
    }

    /**
     * <h2>发送GET请求</h2>
     *
     * @return {@code HttpResponse}
     */
    public final HttpResponse<String> get() {
        method = HttpMethod.GET;
        return send();
    }

    /**
     * <h2>发送请求</h2>
     *
     * @return {@code HttpResponse}
     */
    public final HttpResponse<String> send() {
        try {
            return getHttpClient().send(getHttpRequest(), HttpResponse.BodyHandlers.ofString());
        } catch (Exception exception) {
            throw new ServiceException(exception);
        }
    }

    /**
     * <h2>获取 {@code HttpRequest} 对象</h2>
     *
     * @return {@code HttpRequest}
     */
    private HttpRequest getHttpRequest() {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url));
        headers.forEach((key, value) -> requestBuilder.header(key, value.toString()));
        HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(body);
        switch (method) {
            case GET -> requestBuilder.GET();
            case POST -> requestBuilder.POST(bodyPublisher);
            case PUT -> requestBuilder.PUT(bodyPublisher);
            case DELETE -> requestBuilder.DELETE();
            default -> {
            }
        }
        if (Objects.nonNull(cookies)) {
            List<String> cookieList = new ArrayList<>();
            cookies.forEach((key, value) -> cookieList.add(key + Constant.EQUAL + value));
            requestBuilder.setHeader(
                    Constant.COOKIE, String.join(Constant.SEMICOLON + Constant.SPACE, cookieList)
            );
        }
        if (Objects.nonNull(contentType)) {
            requestBuilder.header(Constant.CONTENT_TYPE, contentType.getValue());
        }
        return requestBuilder.build();
    }

    /**
     * <h2>获取 {@code HttpClient}</h2>
     *
     * @return {@code HttpClient}
     */
    private HttpClient getHttpClient() {
        HttpClient.Builder httpClientBuilder = HttpClient.newBuilder();
        if (connectTimeout > 0) {
            httpClientBuilder.connectTimeout(Duration.ofSeconds(connectTimeout));
        }
        return httpClientBuilder.build();
    }

    /**
     * <h2>添加 {@code Header}</h2>
     *
     * @param key   {@code Header} 键
     * @param value {@code Header} 值
     * @return {@code HttpUtil}
     */
    @Contract("_, _ -> this")
    public final HttpUtil addHeader(String key, Object value) {
        headers.put(key, value);
        return this;
    }
}
