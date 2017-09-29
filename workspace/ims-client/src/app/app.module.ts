import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule }   from '@angular/forms';
import { HttpModule }    from '@angular/http';

// routing
import { AppRoutingModule } from './buckwaframework/common/configs/app-routing.module';

// services
import { AuthGuard } from './buckwaframework/common/services/auth-guard.service';
import { AuthService } from './buckwaframework/common/services/auth.service';
import { MessageBarService } from './buckwaframework/common/services/message-bar.service';
import { MessageService } from './buckwaframework/project/services/message.service';

// components
import { AppComponent } from './app.component';
import { MessageBarComponent } from './buckwaframework/common/components/message-bar.component';

// pages
import { LoginPage } from './buckwaframework/project/pages/login/login';
import { HomePage } from './buckwaframework/project/pages/home/home';
import { MessagePage } from './buckwaframework/project/pages/message/message';
import { ParameterPage } from './buckwaframework/project/pages/parameter/parameter';
import { MessageDetailPage } from './buckwaframework/project/pages/message/message-detail';

@NgModule({
    declarations: [
        AppComponent,
        MessageBarComponent,
        LoginPage,
        HomePage,
        MessagePage,
        MessageDetailPage,
        ParameterPage
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpModule
    ],
    providers: [
        AuthGuard,
        AuthService,
        MessageBarService,
        MessageService
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
