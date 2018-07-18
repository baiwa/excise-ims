import { BrowserModule } from "@angular/platform-browser";
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HttpModule } from "@angular/http";

// pipes
import { TranslatePipe } from "./buckwaframework/common/pipes/translate.pipe";

// routing
import { AppRoutingModule } from "./buckwaframework/common/configs/app-routing.module";

// services
import {
  AuthGuard,
  AuthService,
  MessageBarService,
  MessageService,
  TranslateService,
  ParameterGroupService,
  ParameterInfoService,
  AjaxService,
  ExciseService,
  TravelService
} from "./buckwaframework/common/services";

// components
import { AppComponent } from "./app.component";
import { MessageBarComponent } from "./buckwaframework/common/components/message-bar.component";
import { DropdownComponent } from "./buckwaframework/common/components/dropdown.component";
import { HomePage } from "./buckwaframework/project/pages/home/home";
import { LoginPage } from "./buckwaframework/project/pages/login/login";

@NgModule({
  declarations: [
    AppComponent,
    MessageBarComponent,
    DropdownComponent,
    TranslatePipe,
    HomePage,
    LoginPage
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpModule
  ],
  providers: [
    AuthGuard,
    AuthService,
    MessageBarService,
    MessageService,
    TranslateService,
    ParameterGroupService,
    ParameterInfoService,
    AjaxService,
    ExciseService,
    TravelService
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  bootstrap: [AppComponent]
})
export class AppModule {}
