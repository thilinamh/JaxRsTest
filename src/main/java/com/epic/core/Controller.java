package com.epic.core;

import javax.enterprise.inject.spi.CDI;

/**
 * Created by thilina_h on 8/6/2018.
 */
public abstract interface Controller {

    public interface ControllerCommands<T extends Controller> {
        default Controller getController() {
            Class<?> enclosingClass = this.getClass().getEnclosingClass();
            return (T) CDI.current().select(enclosingClass).get();
        }
    }

}
