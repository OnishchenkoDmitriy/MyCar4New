package org.training.util.properties;


import java.util.ResourceBundle;

/**
 * Resource manager
 */
public enum PropertyManager {
    CONFIG("config");

    /**
     * @see ResourceBundle
     */
    private ResourceBundle resource;


    PropertyManager(String propertyName) {
        this.resource = ResourceBundle.getBundle(propertyName);
    }

    /**
     *
     * @param key key
     * @return message from resource by key
     */
    public String getString(String key){
        return resource.getString(key);
    }
}
