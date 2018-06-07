import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

// pipes
import { TranslatePipe } from './buckwaframework/common/pipes/translate.pipe';

// routing
import { AppRoutingModule } from './buckwaframework/common/configs/app-routing.module';

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
    ExciseService
} from './buckwaframework/common/services/index';

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
import { CheckReceiptTaxComponent } from './buckwaframework/project/pages/check-receipt-tax/check-receipt-tax.component';
import { CheckReceiptLicenseComponent } from './buckwaframework/project/pages/check-receipt-license/check-receipt-license.component';
import { CreateTraderComponent } from './buckwaframework/project/tax-audit/trader-selection/create-trader/create-trader.component';
import { AnalystBasicDataTraderComponent } from './buckwaframework/project/tax-audit/trader-selection/analyst-basic-data-trader/analyst-basic-data-trader.component';
import { CreateWorkingPaperTraderComponent } from './buckwaframework/project/tax-audit/trader-selection/create-working-paper-trader/create-working-paper-trader.component';
import { WorkingPaper1TraderComponent } from './buckwaframework/project/tax-audit/trader-selection/working-paper-1-trader/working-paper-1-trader.component';
import { WorkingPaper1FullTraderComponent } from './buckwaframework/project/tax-audit/trader-selection/working-paper-1-full-trader/working-paper-1-full-trader.component';
import { SummaryAmountProductTraderComponent } from './buckwaframework/project/tax-audit/trader-selection/summary-amount-product-trader/summary-amount-product-trader.component';
import { CreateSaveMessageTraderComponent } from './buckwaframework/project/tax-audit/trader-selection/create-save-message-trader/create-save-message-trader.component';
import { Int0111Component } from './buckwaframework/project/internal-audit/int01/int01-1/int01-1-1/int01-1-1.component';
import { Int0112Component } from './buckwaframework/project/internal-audit/int01/int01-1/int01-1-2/int01-1-2.component';
import { Int0113Component } from './buckwaframework/project/internal-audit/int01/int01-1/int01-1-3/int01-1-3.component';


import { Int051Component } from './buckwaframework/project/internal-audit/int05/int05-1/int05-1.component';
import { Int052Component } from './buckwaframework/project/internal-audit/int05/int05-2/int05-2.component';
import { Int053Component } from './buckwaframework/project/internal-audit/int05/int05-3/int05-3.component';
import { Int0531Component } from './buckwaframework/project/internal-audit/int05/int05-3/int05-3-1/int05-3-1.component';
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

import { Int081Component } from './buckwaframework/project/internal-audit/int08/int08-1/int08-1.component';
import { Int0811Component } from './buckwaframework/project/internal-audit/int08/int08-1/int08-1-1/int08-1-1.component';
import { Int0812Component } from './buckwaframework/project/internal-audit/int08/int08-1/int08-1-2/int08-1-2.component';
import { Int0813Component } from './buckwaframework/project/internal-audit/int08/int08-1/int08-1-3/int08-1-3.component';
import { Int082Component } from './buckwaframework/project/internal-audit/int08/int08-2/int08-2.component';
import { Int083Component } from './buckwaframework/project/internal-audit/int08/int08-3/int08-3.component';
import { Int0831Component } from './buckwaframework/project/internal-audit/int08/int08-3/int08-3-1/int08-3-1.component';
import { Int0832Component } from './buckwaframework/project/internal-audit/int08/int08-3/int08-3-2/int08-3-2.component';
import { Int0833Component } from './buckwaframework/project/internal-audit/int08/int08-3/int08-3-3/int08-3-3.component';
import { Int0834Component } from './buckwaframework/project/internal-audit/int08/int08-3/int08-3-4/int08-3-4.component';
import { Int0835Component } from './buckwaframework/project/internal-audit/int08/int08-3/int08-3-5/int08-3-5.component';
import { Int0836Component } from './buckwaframework/project/internal-audit/int08/int08-3/int08-3-6/int08-3-6.component';
import { Int0121Component } from './buckwaframework/project/internal-audit/int01/int01-2/int01-2-1/int01-2-1.component';
import { Int0122Component } from './buckwaframework/project/internal-audit/int01/int01-2/int01-2-2/int01-2-2.component';
import { Int0123Component } from './buckwaframework/project/internal-audit/int01/int01-2/int01-2-3/int01-2-3.component';
import { Int08331Component } from './buckwaframework/project/internal-audit/int08/int08-3/int08-3-3/int08-3-3-1/int08-3-3-1.component';
import { Int08332Component } from './buckwaframework/project/internal-audit/int08/int08-3/int08-3-3/int08-3-3-2/int08-3-3-2.component';
import { Int08333Component } from './buckwaframework/project/internal-audit/int08/int08-3/int08-3-3/int08-3-3-3/int08-3-3-3.component';
import { Int0621Component } from './buckwaframework/project/internal-audit/int06/int06-2/int06-2-1/int06-2-1.component';
import { Ope041Component } from './buckwaframework/project/tax-audit/operate-tax-audit/ope04/ope04-1/ope04-1.component';
import { Ope042Component } from './buckwaframework/project/tax-audit/operate-tax-audit/ope04/ope04-2/ope04-2.component';
import { Ope043Component } from './buckwaframework/project/tax-audit/operate-tax-audit/ope04/ope04-3/ope04-3.component';
import { Ope044Component } from './buckwaframework/project/tax-audit/operate-tax-audit/ope04/ope04-4/ope04-4.component';
import { Ope0441Component } from './buckwaframework/project/tax-audit/operate-tax-audit/ope04/ope04-4/ope04-4-1/ope04-4-1.component';
import { Ope045Component } from './buckwaframework/project/tax-audit/operate-tax-audit/ope04/ope04-5/ope04-5.component';
import { Ope046Component } from './buckwaframework/project/tax-audit/operate-tax-audit/ope04/ope04-6/ope04-6.component';
import { Ope047Component } from './buckwaframework/project/tax-audit/operate-tax-audit/ope04/ope04-7/ope04-7.component';
import { Ope048Component } from './buckwaframework/project/tax-audit/operate-tax-audit/ope04/ope04-8/ope04-8.component';
import { Ope049Component } from './buckwaframework/project/tax-audit/operate-tax-audit/ope04/ope04-9/ope04-9.component';
import { Ope0410Component } from './buckwaframework/project/tax-audit/operate-tax-audit/ope04/ope04-10/ope04-10.component';
import { Ope051Component } from './buckwaframework/project/tax-audit/operate-tax-audit/ope05/ope05-1/ope05-1.component';
import { Ope0511Component } from './buckwaframework/project/tax-audit/operate-tax-audit/ope05/ope05-1/ope05-1-1/ope05-1-1.component';
import { Ope052Component } from './buckwaframework/project/tax-audit/operate-tax-audit/ope05/ope05-2/ope05-2.component';
import { Ope053Component } from './buckwaframework/project/tax-audit/operate-tax-audit/ope05/ope05-3/ope05-3.component';
import { Ope054Component } from './buckwaframework/project/tax-audit/operate-tax-audit/ope05/ope05-4/ope05-4.component';
import { Ope055Component } from './buckwaframework/project/tax-audit/operate-tax-audit/ope05/ope05-5/ope05-5.component';
import { Ope056Component } from './buckwaframework/project/tax-audit/operate-tax-audit/ope05/ope05-6/ope05-6.component';
import { Ope057Component } from './buckwaframework/project/tax-audit/operate-tax-audit/ope05/ope05-7/ope05-7.component';
import { Int0751Component } from './buckwaframework/project/internal-audit/int07/int07-5/int07-5-1/int07-5-1/int07-5-1.component';
import { Int0752Component } from './buckwaframework/project/internal-audit/int07/int07-5/int07-5-2/int07-5-2.component';
import { Int0753Component } from './buckwaframework/project/internal-audit/int07/int07-5/int07-5-3/int07-5-3.component';
import { Int0754Component } from './buckwaframework/project/internal-audit/int07/int07-5/int07-5-4/int07-5-4.component';
import { Int0511Component } from './buckwaframework/project/internal-audit/int05/int05-1/int05-1-1/int05-1-1.component';

import { Int0521Component } from './buckwaframework/project/internal-audit/int05/int05-2/int05-2-1/int05-2-1.component';
import { Int05111Component } from './buckwaframework/project/internal-audit/int05/int05-1/int05-1-1/int05-1-1-1/int05-1-1-1.component';
import { Int05112Component } from './buckwaframework/project/internal-audit/int05/int05-1/int05-1-1/int05-1-1-2/int05-1-1-2.component';
import { Int05113Component } from './buckwaframework/project/internal-audit/int05/int05-1/int05-1-1/int05-1-1-3/int05-1-1-3.component';
import { Int0522Component } from './buckwaframework/project/internal-audit/int05/int05-2/int05-2-2/int05-2-2.component';
import { Int0523Component } from './buckwaframework/project/internal-audit/int05/int05-2/int05-2-3/int05-2-3.component';
import { Int0524Component } from './buckwaframework/project/internal-audit/int05/int05-2/int05-2-4/int05-2-4.component';

import { Cop011Component } from './buckwaframework/project/check-operation/cop01/cop01-1/cop01-1.component';
import { Cop012Component } from './buckwaframework/project/check-operation/cop01/cop01-2/cop01-2.component';
import { Cop021Component } from './buckwaframework/project/check-operation/cop02/cop02-1/cop02-1.component';
import { Cop013Component } from './buckwaframework/project/check-operation/cop01/cop01-3/cop01-3.component';
import { Cop0211Component } from './buckwaframework/project/check-operation/cop02/cop02-1/cop02-1-1/cop02-1-1.component';
import { Cop0212Component } from './buckwaframework/project/check-operation/cop02/cop02-1/cop02-1-2/cop02-1-2.component';
import { Cop031Component } from './buckwaframework/project/check-operation/cop03/cop03-1/cop03-1.component';
import { Cop0311Component } from './buckwaframework/project/check-operation/cop03/cop03-1-1/cop03-1-1.component';
import { Cop03111Component } from './buckwaframework/project/check-operation/cop03/cop03-1-1/cop03-1-1-1/cop03-1-1-1.component';
import { Cop041Component } from './buckwaframework/project/check-operation/cop04/cop04-1/cop04-1.component';
import { Cop042Component } from './buckwaframework/project/check-operation/cop04/cop04-2/cop04-2.component';
import { Cop0411Component } from './buckwaframework/project/check-operation/cop04/cop04-1/cop04-1-1/cop04-1-1.component';
import { Cop0421Component } from './buckwaframework/project/check-operation/cop04/cop04-2/cop04-2-1/cop04-2-1.component';
import { Cop051Component } from './buckwaframework/project/check-operation/cop05/cop05-1/cop05-1.component';
import { Cop0511Component } from './buckwaframework/project/check-operation/cop05/cop05-1/cop05-1-1/cop05-1-1.component';
import { Epa011Component } from './buckwaframework/project/export-audit/epa01/epa01-1/epa01-1.component';
import { Epa0111Component } from './buckwaframework/project/export-audit/epa01/epa01-1/epa01-1-1/epa01-1-1.component';
import { Epa0112Component } from './buckwaframework/project/export-audit/epa01/epa01-1/epa01-1-2/epa01-1-2.component';
import { Epa021Component } from './buckwaframework/project/export-audit/epa02/epa02-1/epa02-1.component';
import { Epa0211Component } from './buckwaframework/project/export-audit/epa02/epa02-1/epa02-1-1/epa02-1-1.component';
import { Epa0212Component } from './buckwaframework/project/export-audit/epa02/epa02-1/epa02-1-2/epa02-1-2.component';
import { Epa0213Component } from './buckwaframework/project/export-audit/epa02/epa02-1/epa02-1-3/epa02-1-3.component';
import { Int0611Component } from './buckwaframework/project/internal-audit/int06/int06-1/int06-1-1/int06-1-1.component';
import { Int0633Component } from './buckwaframework/project/internal-audit/int06/int06-3/int06-3-3/int06-3-3.component';
import { Int0634Component } from './buckwaframework/project/internal-audit/int06/int06-3/int06-3-4/int06-3-4.component';
import { Int0643Component } from './buckwaframework/project/internal-audit/int06/int06-4/int06-4-3/int06-4-3.component';
import { Int0644Component } from './buckwaframework/project/internal-audit/int06/int06-4/int06-4-4/int06-4-4.component';
import { Int0653Component } from './buckwaframework/project/internal-audit/int06/int06-5/int06-5-3/int06-5-3.component';
import { Int0654Component } from './buckwaframework/project/internal-audit/int06/int06-5/int06-5-4/int06-5-4.component';
import { Int0663Component } from './buckwaframework/project/internal-audit/int06/int06-6/int06-6-3/int06-6-3.component';
import { Int0664Component } from './buckwaframework/project/internal-audit/int06/int06-6/int06-6-4/int06-6-4.component';
import { Int0131Component } from './buckwaframework/project/internal-audit/int01/int01-3/int01-3-1/int01-3-1.component';
import { Int0132Component } from './buckwaframework/project/internal-audit/int01/int01-3/int01-3-2/int01-3-2.component';
import { Int0133Component } from './buckwaframework/project/internal-audit/int01/int01-3/int01-3-3/int01-3-3.component';
import { Int0141Component } from './buckwaframework/project/internal-audit/int01/int01-4/int01-4-1/int01-4-1.component';

import { Int0142Component } from './buckwaframework/project/internal-audit/int01/int01-4/int01-4-2/int01-4-2.component';
import { Int0143Component } from './buckwaframework/project/internal-audit/int01/int01-4/int01-4-3/int01-4-3.component';
import { Int0144Component } from './buckwaframework/project/internal-audit/int01/int01-4/int01-4-4/int01-4-4.component';
import { Int0151Component } from './buckwaframework/project/internal-audit/int01/int01-5/int01-5-1/int01-5-1.component';
import { Int0161Component } from './buckwaframework/project/internal-audit/int01/int01-6/int01-6-1/int01-6-1.component';
import { Int0171Component } from './buckwaframework/project/internal-audit/int01/int01-7/int01-7-1/int01-7-1.component';
import { Int0181Component } from './buckwaframework/project/internal-audit/int01/int01-8/int01-8-1/int01-8-1.component';
import { TaxAuditReportingComponent } from './buckwaframework/project/pages/reports/tax-audit-reporting/tax-audit-reporting.component';
import { Ts0101Component } from './buckwaframework/project/pages/reports/tax-audit-reporting/ts01-01/ts01-01.component';
import { Ts0102Component } from './buckwaframework/project/pages/reports/tax-audit-reporting/ts01-02/ts01-02.component';
import { Ts0104Component } from './buckwaframework/project/pages/reports/tax-audit-reporting/ts01-04/ts01-04.component';
import { Ts0107Component } from './buckwaframework/project/pages/reports/tax-audit-reporting/ts01-07/ts01-07.component';
import { Ts0108Component } from './buckwaframework/project/pages/reports/tax-audit-reporting/ts01-08/ts01-08.component';
import { Ts0103Component } from './buckwaframework/project/pages/reports/tax-audit-reporting/ts01-03/ts01-03.component';
import { Ts0105Component } from './buckwaframework/project/pages/reports/tax-audit-reporting/ts01-05/ts01-05.component';
import { Ts0110Component } from './buckwaframework/project/pages/reports/tax-audit-reporting/ts01-10/ts01-10.component';
import { Ts0113Component } from './buckwaframework/project/pages/reports/tax-audit-reporting/ts01-13/ts01-13.component';
import { Ts01101Component } from './buckwaframework/project/pages/reports/tax-audit-reporting/ts01-10-1/ts01-10-1.component';
import { Ts0111Component } from './buckwaframework/project/pages/reports/tax-audit-reporting/ts01-11/ts01-11.component';
import { Ts01141Component } from './buckwaframework/project/pages/reports/tax-audit-reporting/ts01-14-1/ts01-14-1.component';
import { Ts0114Component } from './buckwaframework/project/pages/reports/tax-audit-reporting/ts01-14/ts01-14.component';
import { Ts0115Component } from './buckwaframework/project/pages/reports/tax-audit-reporting/ts01-15/ts01-15.component';
import { Ts0116Component } from './buckwaframework/project/pages/reports/tax-audit-reporting/ts01-16/ts01-16.component';
import { Ts0119Component } from './buckwaframework/project/pages/reports/tax-audit-reporting/ts01-19/ts01-19.component';
import { Ts01142Component } from './buckwaframework/project/pages/reports/tax-audit-reporting/ts01-14-2/ts01-14-2.component';
import { Ts0117Component } from './buckwaframework/project/pages/reports/tax-audit-reporting/ts01-17/ts01-17.component';
import { AddDataComponent } from './buckwaframework/project/tax-audit/trader-selection/analyst-basic-data-trader/add-data/add-data.component';


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
        CheckReceiptTaxComponent,
        CheckReceiptLicenseComponent,
        CreateTraderComponent,
        AnalystBasicDataTraderComponent,
        CreateWorkingPaperTraderComponent,
        WorkingPaper1TraderComponent,
        WorkingPaper1FullTraderComponent,
        SummaryAmountProductTraderComponent,
        CreateSaveMessageTraderComponent,
        Int0111Component,
        Int0112Component,
        Int0113Component, 
        Int051Component,
        Int052Component,
        Int053Component,
        Int0531Component,
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
        Int081Component,
        Int0811Component,
        Int0812Component,
        Int0813Component,
        Int082Component,
        Int083Component,
        Int0831Component,
        Int0832Component,
        Int0833Component,
        Int0834Component,
        Int0835Component,
        Int0836Component,
        Int0121Component,
        Int0122Component,
        Int0123Component,
        Int08331Component,
        Int08332Component,
        Int08333Component,
        Int0621Component,
        Ope041Component,
        Ope042Component,
        Ope043Component,
        Ope044Component,
        Ope0441Component,
        Ope045Component,
        Ope046Component,
        Ope047Component,
        Ope048Component,
        Ope049Component,
        Ope0410Component,
        Ope051Component,
        Ope0511Component,
        Ope052Component,
        Ope053Component,
        Ope054Component,
        Ope055Component,
        Ope056Component,
        Ope057Component,
        Int0751Component,
        Int0752Component,
        Int0753Component,
        Int0754Component,
        Int0511Component,
        Int0521Component,
        Int05111Component,
        Int05112Component,
        Int05113Component,
        Int0522Component,
        Int0523Component,
        Int0524Component,
        Cop011Component,
        Cop012Component,
        Cop021Component,
        Cop013Component,
        Cop0211Component,
        Cop0212Component,
        Cop031Component,
        Cop0311Component,
        Cop03111Component,
        Cop041Component,
        Cop042Component,
        Cop0411Component,
        Cop0421Component,
        Cop051Component,
        Cop0511Component,
        Epa011Component,
        Epa0111Component,
        Epa0112Component,
        Epa021Component,
        Epa0211Component,
        Epa0212Component,
        Epa0213Component,
        Int0611Component,
        Int0633Component,
        Int0634Component,
        Int0643Component,
        Int0644Component,
        Int0653Component,
        Int0654Component,
        Int0663Component,
        Int0664Component,
        Int0131Component,
        Int0132Component,
        Int0133Component,
        Int0141Component,
        Int0142Component,
        Int0143Component,
        Int0144Component,
        Int0151Component,
        Int0161Component,
        Int0171Component,
        Int0181Component,
        TaxAuditReportingComponent,
        Ts0101Component,
        Ts0102Component,
        Ts0104Component,
        Ts0107Component,
        Ts0108Component,
        Ts0103Component,
        Ts0105Component,
        Ts0110Component,
        Ts0113Component,
        Ts01101Component,
        Ts0111Component,
        Ts01141Component,
        Ts0114Component,
        Ts0115Component,
        Ts0116Component,
        Ts0119Component,
        Ts01142Component,
        Ts0117Component,
        AddDataComponent,
       
       
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
        ExciseService
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
