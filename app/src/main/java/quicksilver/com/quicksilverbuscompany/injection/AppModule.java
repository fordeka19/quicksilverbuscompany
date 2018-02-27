package quicksilver.com.quicksilverbuscompany.injection;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import quicksilver.com.quicksilverbuscompany.App;

@Module
public class AppModule {

    @Provides
    Context provideContext(App application) {
        return application.getApplicationContext();
    }
}
