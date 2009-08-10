/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package org.yaml.snakeyaml.issues.issue9;

import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.Tags;

public class BeanConstructor extends Constructor {

    public BeanConstructor() {
        super(BeanHolder.class);
        yamlConstructors.put(Tags.getGlobalTagForClass(Bean1.class), new Bean1ScalarConstructor());
        yamlConstructors.put(Tags.getGlobalTagForClass(BeanHolder.class),
                new BeanHolderScalarConstructor());
    }

    private class Bean1ScalarConstructor extends ConstructScalar {
        @Override
        public Object construct(Node node) {
            ScalarNode snode = (ScalarNode) node;
            if (snode.getValue().length() == 0) {
                return new Bean1();
            } else {
                return new Bean1(Integer.parseInt(snode.getValue()));
            }
        }
    }

    private class BeanHolderScalarConstructor extends ConstructScalar {
        @Override
        public Object construct(Node node) {
            return new BeanHolder();
        }
    }
}