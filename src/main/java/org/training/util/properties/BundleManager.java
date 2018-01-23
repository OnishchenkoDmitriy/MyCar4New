package org.training.util.properties;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Bundle manager
 */
public enum BundleManager {
    INSTANCE;

    /**
     * @see ResourceBundle
     */
    private ResourceBundle resourceBundle;
    /**
     * name of resource bundle
     */
    private final String RESOURCE_BUNDLE_NAME = "exceptionMessages";

    BundleManager() {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, Locale.getDefault());
    }
    /**
     * change resource bundle locale
     * @param locale locale
     */
    public void changeLocale(Locale locale){
        resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, locale);
    }

    /**
     *
     * @param key key
     * @return string from resource by key
     */
    public String getString(String key){
        return resourceBundle.getString(key);
    }

}
