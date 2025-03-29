package com.tre3p;

import com.tre3p.error.RuntimeError;
import com.tre3p.scanner.model.Token;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    private final Map<String, Object> values = new HashMap<>();

    public void define(String name, Object value) {
        values.put(name, value);
    }

    public Object get(Token name) {
        if (values.containsKey(name)) {
            return values.get(name.lexeme());
        }

        throw new RuntimeError(name, "Undefined variable '" + name.lexeme() + "'.");
    }
}
