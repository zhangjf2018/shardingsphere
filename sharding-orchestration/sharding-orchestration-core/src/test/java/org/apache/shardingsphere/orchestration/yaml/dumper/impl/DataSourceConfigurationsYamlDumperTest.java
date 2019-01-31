/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.orchestration.yaml.dumper.impl;

import org.apache.shardingsphere.core.config.DataSourceConfiguration;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public final class DataSourceConfigurationsYamlDumperTest {
    
    @Test
    public void assertDump() {
        String actual = new DataSourceConfigurationsYamlDumper().dump(createDataSourceConfigurations());
        assertTrue(actual.contains("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false;MODE=MySQL"));
    }
    
    private Map<String, DataSourceConfiguration> createDataSourceConfigurations() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("driverClassName", "org.h2.Driver");
        properties.put("jdbcUrl", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false;MODE=MySQL");
        properties.put("username", "root");
        properties.put("password", "root");
        DataSourceConfiguration result = new DataSourceConfiguration("com.alibaba.druid.pool.DruidDataSource");
        result.getProperties().putAll(properties);
        return Collections.singletonMap("test", result);
    }
}
