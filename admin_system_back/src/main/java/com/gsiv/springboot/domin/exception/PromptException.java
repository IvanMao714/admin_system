package com.gsiv.springboot.domin.exception;

/**
 * @projectName: admin_system_back
 * @package: com.gsiv.springboot.domin.exception
 * @className: PromptException
 * @author: 65151
 * @description: TODO
 * @date: 2023-09-03 18:32
 * @version: 1.0
 */
public class PromptException extends RuntimeException {
    public PromptException(String message) {
        super(message);
    }
}
