/*
 * Copyright (c) 2020. Red Hat, Inc. and/or its affiliates.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.core.base;

import org.drools.base.rule.accessor.ReadAccessor;
import org.drools.base.rule.accessor.WriteAccessor;
import org.kie.api.internal.utils.KieService;

import static org.drools.base.base.CoreComponentsBuilder.throwExceptionForMissingMvel;

public interface FieldAccessorFactory extends KieService {
    class Holder {
        private static final FieldAccessorFactory fieldFactory = getFactory();

        private static FieldAccessorFactory getFactory() {
            FieldAccessorFactory instance = KieService.load( FieldAccessorFactory.class );
            return instance != null ? instance : throwExceptionForMissingMvel();
        }
    }

    static FieldAccessorFactory get() {
        return Holder.fieldFactory;
    }

    ReadAccessor getClassFieldReader(Class< ? > clazz, String fieldName, ClassFieldAccessorCache.CacheEntry cache);
    WriteAccessor getClassFieldWriter(Class< ? > clazz, String fieldName, ClassFieldAccessorCache.CacheEntry cache);
}
