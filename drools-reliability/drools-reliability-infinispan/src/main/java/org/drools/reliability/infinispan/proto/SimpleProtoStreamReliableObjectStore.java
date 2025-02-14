/*
 * Copyright 2023 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.reliability.infinispan.proto;

import org.drools.core.common.Storage;
import org.drools.reliability.core.SimpleSerializationReliableObjectStore;
import org.drools.reliability.core.StoredEvent;
import org.drools.reliability.core.StoredObject;

public class SimpleProtoStreamReliableObjectStore extends SimpleSerializationReliableObjectStore {

    public SimpleProtoStreamReliableObjectStore() {
        throw new UnsupportedOperationException("This constructor should never be called");
    }

    public SimpleProtoStreamReliableObjectStore(Storage<Long, StoredObject> storage) {
        super(storage);
    }

    @Override
    protected StoredObject createStoredObject(boolean propagated, Object object) {
        return new ProtoStreamStoredObject(object, propagated);
    }

    @Override
    protected StoredEvent createStoredEvent(boolean propagated, Object object, long timestamp, long duration) {
        return new ProtoStreamStoredEvent(object, propagated, timestamp, duration);
    }
}
