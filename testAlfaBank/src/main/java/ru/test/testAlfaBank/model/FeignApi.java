package ru.test.testAlfaBank.model;

import feign.RequestLine;

/**
 * интерфейс необходимый для работы Feign
 */
public interface FeignApi {

    /**
     * отправляет GET запрос на url определенный при создании FeignBuider
     */
    @RequestLine("GET")
    Object find();

}
