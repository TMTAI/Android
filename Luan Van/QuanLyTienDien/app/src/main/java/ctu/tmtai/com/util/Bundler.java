package ctu.tmtai.com.util;

import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;

import static ctu.tmtai.com.util.Constant.BUNDLE_USER;
import static ctu.tmtai.com.util.Constant.ROLE;
import static ctu.tmtai.com.util.Constant.USER;

/**
 * Created by tranm on 04-Aug-17.
 */

public class Bundler {
    private Bundler instance = new Bundler();

    public void putBundleUser(Object ob, Intent intent){
        Bundle bundle = new Bundle();
        bundle.putSerializable(USER, (Serializable) ob);
        intent.putExtra(BUNDLE_USER, bundle);
    }

    public void putBundle(Object ob, String role, Intent intent){
        Bundle bundle = new Bundle();
        bundle.putSerializable(USER, (Serializable) ob);
        bundle.putString(ROLE, role);
        intent.putExtra(BUNDLE_USER, bundle);
    }

}
