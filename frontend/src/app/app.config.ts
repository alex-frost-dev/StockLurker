import {ApplicationConfig, provideBrowserGlobalErrorListeners, LOCALE_ID} from "@angular/core";
import { registerLocaleData } from '@angular/common';
import { provideRouter, withHashLocation } from "@angular/router";
import localeEs from '@angular/common/locales/es';

import { routes } from "./app.routes";
import { provideHttpClient } from "@angular/common/http";

registerLocaleData(localeEs);

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes),
    provideRouter(routes, withHashLocation()),
    provideHttpClient(),
    { provide: LOCALE_ID, useValue: 'es' }
  ],  
};
