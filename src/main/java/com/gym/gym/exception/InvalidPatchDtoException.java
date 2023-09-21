package com.gym.gym.exception;

public class InvalidPatchDtoException extends RuntimeException {
    public InvalidPatchDtoException(String field) {
        super(field + " is a list. Patch doesn't support patching lists, please write a custom method or remove list from patchDto");
    }
}
