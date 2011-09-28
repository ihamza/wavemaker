/*
 *  Copyright (C) 2009-2011 VMWare, Inc. All rights reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.wavemaker.json.type.reflect;

import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import com.wavemaker.common.MessageResource;
import com.wavemaker.common.WMRuntimeException;
import com.wavemaker.json.type.FieldDefinition;
import com.wavemaker.json.type.MapTypeDefinition;

/**
 * @author small
 * @version $Rev$ - $Date$
 *
 */
public class MapReflectTypeDefinition extends ReflectTypeDefinition
        implements MapTypeDefinition {
    
    private FieldDefinition keyFieldDefinition;
    private FieldDefinition valueFieldDefinition;
    
    /* (non-Javadoc)
     * @see com.wavemaker.json.type.reflect.ReflectTypeDefinition#newInstance(java.lang.Object[])
     */
    @Override
    public Object newInstance(Object... args) {
        
        if (null==getKlass()) {
            return new HashMap<Object, Object>();
        } else if (getKlass().isInterface()) {
            if (getKlass().isAssignableFrom(HashMap.class)) {
                return new HashMap<Object, Object>();
            } else if (getKlass().isAssignableFrom(SortedMap.class)) {
                return new TreeMap<Object, Object>();
            } else {
                throw new WMRuntimeException(MessageResource.JSON_FAILEDINSTANCE_MAP,
                        getKlass());
            }
        } else {
            return super.newInstance();
        }
    }

    
    
    public FieldDefinition getValueFieldDefinition() {
        return valueFieldDefinition;
    }

    public void setValueFieldDefinition(FieldDefinition valueFieldDefinition) {
        this.valueFieldDefinition = valueFieldDefinition;
    }

    public FieldDefinition getKeyFieldDefinition() {
        return keyFieldDefinition;
    }

    public void setKeyFieldDefinition(FieldDefinition keyFieldDefinition) {
        this.keyFieldDefinition = keyFieldDefinition;
    }
}