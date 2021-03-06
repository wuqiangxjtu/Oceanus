/**
 * Copyright 2011-2013 FoundationDB, LLC
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/* The original from which this derives bore the following: */

/*

   Derby - Class org.apache.derby.impl.sql.compile.SetSchemaNode

   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to you under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */

package com.bj58.sql.parser;

import com.bj58.sql.StandardException;

/**
 * A SetSchemaNode is the root of a QueryTree that 
 * represents a SET SCHEMA statement.    It isn't
 * replicated, but it generates a ConstantAction
 * because it is basically easier than generating
 * the code from scratch.
 *
 */

public class SetSchemaNode extends MiscellaneousStatementNode
{
    private String name;
    private int type;

    /**
     * Initializer for a SetSchemaNode
     *
     * @param schemaName The name of the new schema
     * @param type Type of schema name could be USER or dynamic parameter
     *
     */
    public void init(Object schemaName, Object type) {
        this.name = (String)schemaName;
        if (type != null)
            this.type = ((Integer)type).intValue();
    }

    /**
     * Fill this node with a deep copy of the given node.
     */
    public void copyFrom(QueryTreeNode node) throws StandardException {
        super.copyFrom(node);

        SetSchemaNode other = (SetSchemaNode)node;
        this.name = other.name;
        this.type = other.type;
    }

    /**
     * Convert this object to a String.  See comments in QueryTreeNode.java
     * for how this should be done for tree printing.
     *
     * @return This object as a String
     */

    public String toString() {
        return super.toString() + 
            (type == StatementType.SET_SCHEMA_USER ? "schemaName: \nUSER\n" :
             (type == StatementType.SET_SCHEMA_DYNAMIC ? "schemaName: \n?\n" : 
              "schemaName: " + "\n" + name + "\n"));
    }

    public String statementToString() {
        return "SET SCHEMA";
    }
    
    public String getSchemaName() {
        return name;
    }
    
    public int statementType() {
        return type;
    }
}
