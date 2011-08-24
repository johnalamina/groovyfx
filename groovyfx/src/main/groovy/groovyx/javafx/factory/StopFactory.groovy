/*
* Copyright 2011 the original author or authors.
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

package groovyx.javafx.factory

import javafx.scene.paint.Stop
import javafx.scene.paint.Color

/**
 * This factory creates Stop objects for LinearGradients and RadialGradients.
 *
 * Author: Dean Iverson
 */
class StopFactory extends AbstractGroovyFXFactory {
    public final static STOPS_PROPERTY = "__stops"

    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
        if (FactoryBuilderSupport.checkValueIsType(value, name, Stop)) {
            return value
        } else {
            def offset = attributes.remove('offset') ?: 0.0
            def color = ColorFactory.get(attributes.remove('color') ?: Color.BLACK)
            return new Stop(offset, color)
        }
    }

    @Override
    boolean isLeaf() {
        return true
    }

    @Override
    void onNodeCompleted(FactoryBuilderSupport builder, Object parent, Object node) {
        def stopList = builder.parentContext[STOPS_PROPERTY]
        if (stopList) {
            stopList << node
        } else {
            builder.parentContext[STOPS_PROPERTY] = [node]
        }
    }


}