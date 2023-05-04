package com.csankhala;

import io.micronaut.http.annotation.*;

@Controller("/orderService")
public class OrderServiceController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}