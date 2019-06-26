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

package com.google.cloud.broker.logging;

import java.lang.invoke.MethodHandles;

import io.grpc.StatusRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.google.cloud.broker.grpc.ClientAddressServerInterceptor;


public class LoggingUtils {
    public static String getClientAddr() {
        return ClientAddressServerInterceptor.CLIENT_ADDRESS_CONTEXT_KEY.get();
    }

    public static String logError(StatusRuntimeException e, String endpointName) {
        StringBuilder sb = new StringBuilder();
        sb.append("StatusRuntimeException { endpoint=");
        sb.append(endpointName);
        sb.append(" status_code=");
        sb.append(e.getStatus().getCode().toString());
        sb.append(" status_description=");
        sb.append(e.getStatus().getDescription());
        sb.append(" client_address=");
        sb.append(getClientAddr());
        sb.append(" }");
        return sb.toString();
    }
}
