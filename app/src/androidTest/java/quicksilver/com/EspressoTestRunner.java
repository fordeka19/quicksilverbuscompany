package quicksilver.com;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;


public class EspressoTestRunner extends AndroidJUnitRunner {
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws
            IllegalAccessException, ClassNotFoundException, InstantiationException {
        return super.newApplication(cl, TestApp.class.getName(), context);
    }
}
