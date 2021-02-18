package org.example.buyticket.app.config;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

/**
 * Register DI Binder
 *
 * @author Gulyamov Rustam
 */
public class ComponentFeature implements Feature {
    @Override
    public boolean configure(final FeatureContext context) {
        context.register(new ComponentBinder());
        return true;
    }
}
