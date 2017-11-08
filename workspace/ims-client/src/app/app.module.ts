import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule }    from '@angular/http';

// pipes
import { TranslatePipe } from './buckwaframework/common/pipes/translate.pipe';

// routing
import { AppRoutingModule } from './buckwaframework/common/configs/app-routing.module';

// services
import { AuthGuard } from './buckwaframework/common/services/auth-guard.service';
import { AuthService } from './buckwaframework/common/services/auth.service';
import { MessageBarService } from './buckwaframework/common/services/message-bar.service';
import { MessageService } from './buckwaframework/common/services/message.service';
import { TranslateService } from './buckwaframework/common/services/translate.service';
import { ParameterGroupService } from './buckwaframework/common/services/parameterGroup.service';
import { ParameterInfoService } from './buckwaframework/common/services/parameterInfo.service';
import { AjaxService } from './buckwaframework/common/services/ajax.service';

// components
import { AppComponent } from './app.component';
import { MessageBarComponent } from './buckwaframework/common/components/message-bar.component';
import { DropdownComponent } from './buckwaframework/common/components/dropdown.component';

// pages
import { LoginPage } from './buckwaframework/project/pages/login/login';
import { HomePage } from './buckwaframework/project/pages/home/home';
import { MessagePage } from './buckwaframework/project/pages/message/message';
import { MessageDetailPage } from './buckwaframework/project/pages/message/message-detail';
import { ParameterInfoPage } from './buckwaframework/project/pages/parameterInfo/parameterInfo';
import { ParameterGroupPage } from './buckwaframework/project/pages/parameterGroup/parameterGroup';
import { ParameterInfoDetailPage } from './buckwaframework/project/pages/parameterInfo/parameterInfoDetail';
import { UserManagementPage } from './buckwaframework/project/pages/userManagement/userManagement';
import { UserManagementDetailPage } from './buckwaframework/project/pages/userManagement/userManagement-detail';
import { AnalysisPage } from './buckwaframework/project/pages/analysis/analysis.component';
import { ResultAnalysisPage } from './buckwaframework/project/pages/result-analysis/result-analysis.component';
import { SelectFormComponent } from './buckwaframework/project/pages/select-form/select-form.component';
import { CreateFormComponent } from './buckwaframework/project/pages/create-form/create-form.component';
import { CreateNewFormComponent } from './buckwaframework/project/pages/create-new-form/create-new-form.component';
import { SelectNewFormComponent } from './buckwaframework/project/pages/select-new-form/select-new-form.component';
import { ImportGfAccountingComponent } from './buckwaframework/project/pages/import-gf-accounting/import-gf-accounting.component';
import { SelectIncSummaryComponent } from './buckwaframework/project/pages/select-inc-summary/select-inc-summary.component';
import { ResultAnalysisGfIncComponent } from './buckwaframework/project/pages/result-analysis-gf-inc/result-analysis-gf-inc.component';
import { MappingGfIncCodeComponent } from './buckwaframework/project/pages/mapping-gf-inc-code/mapping-gf-inc-code.component';
import { CheckCompanyPaymentComponent } from './buckwaframework/project/pages/check-company-payment/check-company-payment.component';
import { CheckCompanyRenewComponent } from './buckwaframework/project/pages/check-company-renew/check-company-renew.component';
import { CheckReprintLicenseComponent } from './buckwaframework/project/pages/check-reprint-license/check-reprint-license.component';
import { CheckReceiptTaxComponent } from './buckwaframework/project/pages/check-receipt-tax/check-receipt-tax.component';
import { CheckReceiptLicenseComponent } from './buckwaframework/project/pages/check-receipt-license/check-receipt-license.component';

@NgModule({
    declarations: [
        AppComponent,
        MessageBarComponent,
        DropdownComponent,
        TranslatePipe,
        LoginPage,
        HomePage,
        MessagePage,
        MessageDetailPage,
        ParameterInfoPage,
        ParameterGroupPage,
        ParameterInfoDetailPage,
        UserManagementPage,
        UserManagementDetailPage,
        AnalysisPage,
        ResultAnalysisPage,
        SelectFormComponent,
        CreateFormComponent,
        CreateNewFormComponent,
        SelectNewFormComponent,
        ImportGfAccountingComponent,
        SelectIncSummaryComponent,
        ResultAnalysisGfIncComponent,
        MappingGfIncCodeComponent,
        CheckCompanyPaymentComponent,
        CheckCompanyRenewComponent,
        CheckReprintLicenseComponent,
        CheckReceiptTaxComponent,
        CheckReceiptLicenseComponent
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
        AjaxService
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
