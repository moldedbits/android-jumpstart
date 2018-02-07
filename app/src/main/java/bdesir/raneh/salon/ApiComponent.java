package bdesir.raneh.salon;

import bdesir.raneh.salon.api.ApiModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class})
public interface ApiComponent {

    void inject(BaseActivity baseActivity);
}
