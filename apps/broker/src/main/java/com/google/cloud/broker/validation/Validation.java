// Copyright 2019 Google LLC
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// http://www.apache.org/licenses/LICENSE-2.0
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.cloud.broker.validation;

import com.google.cloud.broker.endpoints.GetSessionToken;
import com.google.cloud.broker.settings.AppSettings;
import com.google.common.collect.ImmutableSet;
import io.grpc.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Validation {
    private static final Logger logger = LoggerFactory.getLogger(GetSessionToken.class);

    public static void validateNotEmpty(String parameter, String value) {
        if (value.length() == 0) {
            throw Status.INVALID_ARGUMENT
                .withDescription(String.format("Request must provide the `%s` parameter", parameter))
                .asRuntimeException();
        }
    }

    public static void validateImpersonator(String impersonator, String impersonated) {
        AppSettings settings = AppSettings.getInstance();
        String proxyString = Optional.ofNullable(settings.getProperty("PROXY_USER_WHITELIST")).orElse("");
        Set<String> proxyUsers = ImmutableSet.copyOf(proxyString.split(","));
        if (!impersonator.equals(impersonated) && !proxyUsers.contains(impersonator)) {
            logger.debug("impersonator=" + impersonator + " proxyUserWhitelist=" + proxyString);
            throw Status.PERMISSION_DENIED
                .withDescription(String.format("%s is not a whitelisted impersonator", impersonator))
                .asRuntimeException();
        }
    }


    public static void validateScope(String scope) {
        AppSettings settings = AppSettings.getInstance();
        Set<String> scopeSet = new HashSet<String>(Arrays.asList(scope.split(",")));
        Set<String> whitelist = new HashSet<String>(Arrays.asList(settings.getProperty("SCOPE_WHITELIST")));
        if (!whitelist.containsAll(scopeSet)) {
            throw Status.PERMISSION_DENIED
                .withDescription(String.format("%s is not a whitelisted scope", scope))
                .asRuntimeException();
        }
    }


}
