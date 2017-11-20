import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

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
import { Int031Component } from './buckwaframework/project/internal-audit/int03/int03-1/int03-1.component';
import { Int0311Component } from './buckwaframework/project/internal-audit/int03/int03-1/int3-1-1/int03-1-1.component';
import { Int0312Component } from './buckwaframework/project/internal-audit/int03/int03-1/int3-1-2/int03-1-2.component';
import { Int032Component } from './buckwaframework/project/internal-audit/int03/int03-2/int03-2.component';
import { CheckCompanyPaymentComponent } from './buckwaframework/project/pages/check-company-payment/check-company-payment.component';
import { CheckCompanyRenewComponent } from './buckwaframework/project/pages/check-company-renew/check-company-renew.component';
import { CheckReprintLicenseComponent } from './buckwaframework/project/pages/check-reprint-license/check-reprint-license.component';
import { CheckReceiptTaxComponent } from './buckwaframework/project/pages/check-receipt-tax/check-receipt-tax.component';
import { CheckReceiptLicenseComponent } from './buckwaframework/project/pages/check-receipt-license/check-receipt-license.component';
import { CreateTraderComponent } from './buckwaframework/project/tax-audit/trader-selection/create-trader/create-trader.component';
import { AnalystBasicDataTraderComponent } from './buckwaframework/project/tax-audit/trader-selection/analyst-basic-data-trader/analyst-basic-data-trader.component';
import { CreateWorkingPaperTraderComponent } from './buckwaframework/project/tax-audit/trader-selection/create-working-paper-trader/create-working-paper-trader.component';
import { WorkingPaper1TraderComponent } from './buckwaframework/project/tax-audit/trader-selection/working-paper-1-trader/working-paper-1-trader.component';
import { WorkingPaper1FullTraderComponent } from './buckwaframework/project/tax-audit/trader-selection/working-paper-1-full-trader/working-paper-1-full-trader.component';
import { SummaryAmountProductTraderComponent } from './buckwaframework/project/tax-audit/trader-selection/summary-amount-product-trader/summary-amount-product-trader.component';
import { CreateSaveMessageTraderComponent } from './buckwaframework/project/tax-audit/trader-selection/create-save-message-trader/create-save-message-trader.component';
import { Int051Component } from './buckwaframework/project/internal-audit/int05/int05-1/int05-1.component';
import { Int052Component } from './buckwaframework/project/internal-audit/int05/int05-2/int05-2.component';
import { Int053Component } from './buckwaframework/project/internal-audit/int05/int05-3/int05-3.component';
import { Int054Component } from './buckwaframework/project/internal-audit/int05/int05-4/int05-4.component';
import { Int061Component } from './buckwaframework/project/internal-audit/int06/int06-1/int06-1.component';
import { Int062Component } from './buckwaframework/project/internal-audit/int06/int06-2/int06-2.component';
import { Int063Component } from './buckwaframework/project/internal-audit/int06/int06-3/int06-3.component';
import { Int0631Component } from './buckwaframework/project/internal-audit/int06/int06-3/int06-3-1/int06-3-1.component';
import { Int0632Component } from './buckwaframework/project/internal-audit/int06/int06-3/int06-3-2/int06-3-2.component';
import { Int064Component } from './buckwaframework/project/internal-audit/int06/int06-4/int06-4.component';
import { Int0641Component } from './buckwaframework/project/internal-audit/int06/int06-4/int06-4-1/int06-4-1.component';
import { Int0642Component } from './buckwaframework/project/internal-audit/int06/int06-4/int06-4-2/int06-4-2.component';
import { Int065Component } from './buckwaframework/project/internal-audit/int06/int06-5/int06-5.component';
import { Int0651Component } from './buckwaframework/project/internal-audit/int06/int06-5/int06-5-1/int06-5-1.component';
import { Int0652Component } from './buckwaframework/project/internal-audit/int06/int06-5/int06-5-2/int06-5-2.component';
import { Int066Component } from './buckwaframework/project/internal-audit/int06/int06-6/int06-6.component';
import { Int0661Component } from './buckwaframework/project/internal-audit/int06/int06-6/int06-6-1/int06-6-1.component';
import { Int0662Component } from './buckwaframework/project/internal-audit/int06/int06-6/int06-6-2/int06-6-2.component';
import { Int071Component } from './buckwaframework/project/internal-audit/int07/int07-1/int07-1.component';
import { Int072Component } from './buckwaframework/project/internal-audit/int07/int07-2/int07-2.component';
import { Int073Component } from './buckwaframework/project/internal-audit/int07/int07-3/int07-3.component';
import { Int074Component } from './buckwaframework/project/internal-audit/int07/int07-4/int07-4.component';
import { Int075Component } from './buckwaframework/project/internal-audit/int07/int07-5/int07-5.component';
import { Int076Component } from './buckwaframework/project/internal-audit/int07/int07-6/int07-6.component';
import { Int077Component } from './buckwaframework/project/internal-audit/int07/int07-7/int07-7.component';
import { Int0771Component } from './buckwaframework/project/internal-audit/int07/int07-7/int07-7-1/int07-7-1.component';
import { Int0772Component } from './buckwaframework/project/internal-audit/int07/int07-7/int07-7-2/int07-7-2.component';
import { Int0773Component } from './buckwaframework/project/internal-audit/int07/int07-7/int07-7-3/int07-7-3.component';
import { Int0774Component } from './buckwaframework/project/internal-audit/int07/int07-7/int07-7-4/int07-7-4.component';
import { Int0775Component } from './buckwaframework/project/internal-audit/int07/int07-7/int07-7-5/int07-7-5.component';
import { Int081Component } from './buckwaframework/project/internal-audit/int08/int08-1/int08-1.component';
import { Int0811Component } from './buckwaframework/project/internal-audit/int08/int08-1/int08-1-1/int08-1-1.component';
import { Int0812Component } from './buckwaframework/project/internal-audit/int08/int08-1/int08-1-2/int08-1-2.component';
import { Int0813Component } from './buckwaframework/project/internal-audit/int08/int08-1/int08-1-3/int08-1-3.component';
import { Int082Component } from './buckwaframework/project/internal-audit/int08/int08-2/int08-2.component';
import { CreatePaperReceiveMaterialComponent } from './buckwaframework/project/pages/create-paper-receive-material/create-paper-receive-material.component';
import { CreatePaperPayMaterialComponent } from './buckwaframework/project/pages/create-paper-pay-material/create-paper-pay-material.component';
import { CreatePaperRelationMaterialComponent } from './buckwaframework/project/pages/create-paper-relation-material/create-paper-relation-material.component';

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
        Int031Component,
        Int0311Component,
        Int0312Component,
        Int032Component,
        CheckCompanyPaymentComponent,
        CheckCompanyRenewComponent,
        CheckReprintLicenseComponent,
        CheckReceiptTaxComponent,
        CheckReceiptLicenseComponent,
        CreateTraderComponent,
        AnalystBasicDataTraderComponent,
        CreateWorkingPaperTraderComponent,
        WorkingPaper1TraderComponent,
        WorkingPaper1FullTraderComponent,
        SummaryAmountProductTraderComponent,
        CreateSaveMessageTraderComponent,
        Int051Component,
        Int052Component,
        Int053Component,
        Int054Component,
        Int061Component,
        Int062Component,
        Int063Component,
        Int0631Component,
        Int0632Component,
        Int064Component,
        Int0641Component,
        Int0642Component,
        Int065Component,
        Int0651Component,
        Int0652Component,
        Int066Component,
        Int0661Component,
        Int0662Component,
        Int071Component,
        Int072Component,
        Int073Component,
        Int074Component,
        Int075Component,
        Int076Component,
        Int077Component,
        Int0771Component,
        Int0772Component,
        Int0773Component,
        Int0774Component,
        Int0775Component,
        Int081Component,
        Int0811Component,
        Int0812Component,
        Int0813Component,
        Int082Component,
        CreatePaperReceiveMaterialComponent,
        CreatePaperPayMaterialComponent,
        CreatePaperRelationMaterialComponent,
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
