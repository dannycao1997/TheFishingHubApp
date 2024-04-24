package com.fishinghub.fishinghub.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomErrorController implements ErrorController {
    private static final Logger log = LoggerFactory.getLogger(CustomErrorController.class);
    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    @ResponseBody
    public String error(HttpServletRequest request) {
        Object status = request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        log.error("Error with status code " + status + " and exception " + exception);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "Error 404: Page not found";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "Error 500: Internal server error";
            }
        }
        return "SOME TYPE OF ERROR LOL";

    }
}

