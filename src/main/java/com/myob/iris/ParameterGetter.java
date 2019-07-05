package com.myob.iris;

import java.util.Map;
import java.util.Optional;

public interface ParameterGetter {
    Optional<Map<String, String>> getParameters(String queryString);
}
