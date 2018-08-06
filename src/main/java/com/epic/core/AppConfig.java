package com.epic.core;

import com.epic.auth.AuthenticationHandlingFilter;
import com.epic.endpoints.CommonEndpoint;
import com.epic.endpoints.UserEndpoint;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by thilina_h on 8/3/2018.
 */
@ApplicationPath("api")
public class AppConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> classes=new HashSet<>();
        classes.add(UserEndpoint.class);
        classes.add(CommonEndpoint.class);
        classes.add(AuthenticationHandlingFilter.class);
        return classes;
    }


}
