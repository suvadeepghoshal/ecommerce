package com.ecommerceDemo.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;

import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition(name = "Code Challenge", description = "Code Challenge on 21-05-21")
public @interface CodeChallengeConfig {
    @AttributeDefinition(name = "Demo Text", description = "Demo String Description", type = AttributeType.STRING)
    String getText() default "demo";

    @AttributeDefinition(name = "Demo Boolean", description = "Demo Boolean Description", type = AttributeType.BOOLEAN)
    boolean getBoolean() default false;

    @AttributeDefinition(name = "Demo Selection", description = "Demo Selection Description", options = {
            @Option(label = "Item 1", value = "item1"), @Option(label = "Item 2", value = "item2"),
            @Option(label = "Item 3", value = "item3") }, type = AttributeType.STRING)
    String getSelection() default "item1";

    @AttributeDefinition(name = "Demo Multifield", description = "Demo Description of multifield", type = AttributeType.STRING)
    String[] getMultifled() default { "V1", "V2" };
}
