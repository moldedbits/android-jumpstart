package bdesir.raneh.salon;

import bdesir.raneh.salon.api.MockApiModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MockApiModule.class})
interface MockApiComponent extends ApiComponent {
}
