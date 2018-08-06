package com.epic.dao;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thilina_h on 8/5/2018.
 */
@ApplicationScoped
public class UserData {

    private Map<String, String> userData = new HashMap<>();

    {
        userData.put("kasun", "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad");
        userData.put("dasun", "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3");
    }

    public boolean isValidCredentials(String uname, String pword) {
        final String password = userData.get(uname);
        if (password != null && password.equals(pword)) {
            return true;
        }
        return false;
    }
}
