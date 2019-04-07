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

package org.apache.shardingsphere.core.parse.antlr.filler.common.dql;

import org.apache.shardingsphere.core.metadata.table.ShardingTableMetaData;
import org.apache.shardingsphere.core.parse.antlr.filler.SQLSegmentFiller;
import org.apache.shardingsphere.core.parse.antlr.sql.segment.dml.order.GroupBySegment;
import org.apache.shardingsphere.core.parse.antlr.sql.segment.dml.order.item.OrderByItemSegment;
import org.apache.shardingsphere.core.parse.antlr.sql.statement.SQLStatement;
import org.apache.shardingsphere.core.parse.antlr.sql.statement.dml.SelectStatement;
import org.apache.shardingsphere.core.rule.BaseRule;

/**
 * Group by filler.
 *
 * @author duhongjun
 * @author panjuan
 */
public final class GroupByFiller implements SQLSegmentFiller<GroupBySegment, BaseRule> {
    
    @Override
    public void fill(final GroupBySegment sqlSegment, final SQLStatement sqlStatement, final BaseRule rule, final ShardingTableMetaData shardingTableMetaData) {
        SelectStatement selectStatement = (SelectStatement) sqlStatement;
        selectStatement.setGroupByLastIndex(sqlSegment.getGroupByStopIndex());
        for (OrderByItemSegment each : sqlSegment.getGroupByItems()) {
            selectStatement.getGroupByItems().add(new OrderItemBuilder(selectStatement, each).createOrderItem());
        }
    }
}
