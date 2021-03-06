/*
 * Copyright (C) 2017 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redhat.red.build.finder;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JSONUtils {
    private JSONUtils() {

    }

    public static String dumpString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new BuildFinderObjectMapper();

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    }

    public static void dumpObjectToFile(Object obj, File file) throws IOException {
        ObjectMapper mapper = new BuildFinderObjectMapper();

        mapper.writerWithDefaultPrettyPrinter().writeValue(file, obj);

        FileUtils.writeLines(file, Collections.singletonList(null), true);
    }

    public static Map<String, Collection<String>> loadChecksumsFile(File file) throws IOException {
        ObjectMapper mapper = new BuildFinderObjectMapper();
        TypeReference<Map<String, Collection<String>>> typeRef = new TypeReference<Map<String, Collection<String>>>() { };

        return mapper.readValue(file, typeRef);
    }

    public static Map<BuildSystemInteger, KojiBuild> loadBuildsFile(File file) throws IOException {
        ObjectMapper mapper = new BuildFinderObjectMapper();
        TypeReference<Map<BuildSystemInteger, KojiBuild>> ref = new TypeReference<Map<BuildSystemInteger, KojiBuild>>() { };

        return mapper.readValue(file, ref);
    }
}
