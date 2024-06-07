package com.ywk.sems.exceptions;

import java.io.IOException;

/**
 * @version 1.0
 * @date 2024/4/19 - 0:56
 * @className FileNotSupportException
 * @since 1.0
 */
public class FileNotSupportException extends IOException {
    public FileNotSupportException() {

    }

    public FileNotSupportException(String message) {
        super(message);
    }
}
