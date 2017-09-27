import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';

// services
import { AuthGuard } from '../services/auth-guard.service';
import { AuthService } from '../services/auth.service';
import { MessageService } from '../services/message.service';

// components
import { AppComponent } from './app.component';

// pages
import { LoginPage } from '../pages/login/login';
import { HomePage } from '../pages/home/home';
import { MessagePage } from '../pages/message/message';
import { ParameterPage } from '../pages/parameter/parameter';

@NgModule({
    declarations: [
        AppComponent,
        LoginPage,
        HomePage,
        MessagePage,
        ParameterPage
    ],
    imports: [
        BrowserModule,
        AppRoutingModule
    ],
    providers: [
        AuthGuard,
        AuthService,
        MessageService
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
