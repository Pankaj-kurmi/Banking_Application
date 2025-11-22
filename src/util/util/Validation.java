package util.util;

import exceptions.ValidationException;

@FunctionalInterface
public interface Validation<String> {
    void valid(String value) throws ValidationException;
}
