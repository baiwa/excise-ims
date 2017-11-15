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
import { CreateTraderComponent } from './buckwaframework/project/tax-audit/trader-selection/create-trader/create-trader.component';
import { AnalystBasicDataTraderComponent } from './buckwaframework/project/tax-audit/trader-selection/analyst-basic-data-trader/analyst-basic-data-trader.component';
import { CreateWorkingPaperTraderComponent } from './buckwaframework/project/tax-audit/trader-selection/create-working-paper-trader/create-working-paper-trader.component';
import { WorkingPaper1TraderComponent } from './buckwaframework/project/tax-audit/trader-selection/working-paper-1-trader/working-paper-1-trader.component';
import { WorkingPaper1FullTraderComponent } from './buckwaframework/project/tax-audit/trader-selection/working-paper-1-full-trader/working-paper-1-full-trader.component';
import { SummaryAmountProductTraderComponent } from './buckwaframework/project/tax-audit/trader-selection/summary-amount-product-trader/summary-amount-product-trader.component';
import { CreateSaveMessageTraderComponent } from './buckwaframework/project/tax-audit/trader-selection/create-save-message-trader/create-save-message-trader.component';
import { CompareReportAssetComponent } from './buckwaframework/project/pages/compare-report-asset/compare-report-asset.component';
import { CheckStampYearlyComponent } from './buckwaframework/project/pages/check-stamp-yearly/check-stamp-yearly.component';
import { CheckStampBranchComponent } from './buckwaframework/project/pages/check-stamp-branch/check-stamp-branch.component';
import { SaveResultTradingComponent } from './buckwaframework/project/pages/save-result-trading/save-result-trading.component';
import { Int061Component } from './buckwaframework/project/internal-audit/check-withdrawal/int06-1/int06-1.component';
import { Int062Component } from './buckwaframework/project/internal-audit/check-withdrawal/int06-2/int06-2.component';
import { Int063Component } from './buckwaframework/project/internal-audit/check-withdrawal/int06-3/int06-3.component';
import { Int0631Component } from './buckwaframework/project/internal-audit/check-withdrawal/int06-3/int06-3-1/int06-3-1.component';
import { Int0632Component } from './buckwaframework/project/internal-audit/check-withdrawal/int06-3/int06-3-2/int06-3-2.component';
import { CheckOvertimeComWithdrawalComponent } from './buckwaframework/project/internal-audit/check-withdrawal/check-overtime-com-withdrawal/check-overtime-com-withdrawal.component';
import { Int065Component } from './buckwaframework/project/internal-audit/check-withdrawal/int06-5/int06-5.component';
import { CheckPersonalComWithdrawalComponent } from './buckwaframework/project/internal-audit/check-withdrawal/check-personal-com-withdrawal/check-personal-com-withdrawal.component';
import { CheckOvertimeComWithdrawalCalendarComponent } from './buckwaframework/project/internal-audit/check-withdrawal/check-overtime-com-withdrawal/check-overtime-com-withdrawal-calendar/check-overtime-com-withdrawal-calendar.component';
import { CheckOvertimeComWithdrawalDisplayComponent } from './buckwaframework/project/internal-audit/check-withdrawal/check-overtime-com-withdrawal/check-overtime-com-withdrawal-display/check-overtime-com-withdrawal-display.component';
import { Int0651Component } from './buckwaframework/project/internal-audit/check-withdrawal/int06-5/int06-5-1/int06-5-1.component';
import { Int0652Component } from './buckwaframework/project/internal-audit/check-withdrawal/int06-5/int06-5-2/int06-5-2.component';
import { CheckPersonalComWithdrawalCalendarComponent } from './buckwaframework/project/internal-audit/check-withdrawal/check-personal-com-withdrawal/check-personal-com-withdrawal-calendar/check-personal-com-withdrawal-calendar.component';
import { CheckPersonalComWithdrawalDisplayComponent } from './buckwaframework/project/internal-audit/check-withdrawal/check-personal-com-withdrawal/check-personal-com-withdrawal-display/check-personal-com-withdrawal-display.component';

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
        CheckReceiptLicenseComponent,
        CreateTraderComponent,
        AnalystBasicDataTraderComponent,
        CreateWorkingPaperTraderComponent,
        WorkingPaper1TraderComponent,
        WorkingPaper1FullTraderComponent,
        SummaryAmountProductTraderComponent,
        CreateSaveMessageTraderComponent,
        CompareReportAssetComponent,
        CheckStampYearlyComponent,
        CheckStampBranchComponent,
        SaveResultTradingComponent,
        Int061Component,
        Int062Component,
        Int063Component,
        Int0631Component,
        Int0632Component,
        CheckOvertimeComWithdrawalComponent,
        Int065Component,
        CheckPersonalComWithdrawalComponent,
        CheckOvertimeComWithdrawalCalendarComponent,
        CheckOvertimeComWithdrawalDisplayComponent,
        Int0651Component,
        Int0652Component,
        CheckPersonalComWithdrawalCalendarComponent,
        CheckPersonalComWithdrawalDisplayComponent
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
