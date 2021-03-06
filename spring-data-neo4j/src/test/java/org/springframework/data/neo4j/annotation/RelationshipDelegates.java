/**
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.neo4j.annotation;

import org.neo4j.graphdb.Relationship;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import java.util.HashSet;
import java.util.Set;

import static org.neo4j.helpers.collection.IteratorUtil.count;

public class RelationshipDelegates {
    public static Set<String> getRelationshipNames(Neo4jTemplate template, IdentifiableEntity entity) {
        HashSet<String> relationshipNames = new HashSet<String>();

        for (Relationship relationship : getRelationships(template, entity)) {
            relationshipNames.add(relationship.getType().name());
        }

        return relationshipNames;
    }

    public static Integer getNumberOfRelationships(Neo4jTemplate template, IdentifiableEntity entity) {
        return count(getRelationships(template, entity));
    }

    private static Iterable<Relationship> getRelationships(Neo4jTemplate template, IdentifiableEntity entity) {
        return template.getNode(entity.getId()).getRelationships();
    }
}
