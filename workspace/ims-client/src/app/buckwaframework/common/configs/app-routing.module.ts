import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// service
import { AuthGuard } from '../services/auth-guard.service';

// pages
import { LoginPage } from '../../project/pages/login/login';
import { HomePage } from '../../project/pages/home/home';
import { MessagePage } from '../../project/pages/message/message';
import { MessageDetailPage } from '../../project/pages/message/message-detail';
import { ParameterInfoPage } from '../../project/pages/parameterInfo/parameterInfo';
import { ParameterGroupPage } from '../../project/pages/parameterGroup/parameterGroup';
import { ParameterInfoDetailPage } from '../../project/pages/parameterInfo/parameterInfoDetail';
import { UserManagementPage } from '../../project/pages/userManagement/userManagement';
import { UserManagementDetailPage } from '../../project/pages/userManagement/userManagement-detail';
import { AnalysisPage } from '../../project/pages/analysis/analysis.component';
import { ResultAnalysisPage } from '../../project/pages/result-analysis/result-analysis.component';
import { SelectFormComponent } from './../../project/pages/select-form/select-form.component';
import { CreateFormComponent } from './../../project/pages/create-form/create-form.component';
import { SelectNewFormComponent } from './../../project/pages/select-new-form/select-new-form.component';
import { CreateNewFormComponent } from './../../project/pages/create-new-form/create-new-form.component';
import { ImportGfAccountingComponent } from './../../project/pages/import-gf-accounting/import-gf-accounting.component';
import { SelectIncSummaryComponent } from './../../project/pages/select-inc-summary/select-inc-summary.component';
import { ResultAnalysisGfIncComponent } from './../../project/pages/result-analysis-gf-inc/result-analysis-gf-inc.component';
import { MappingGfIncCodeComponent } from './../../project/pages/mapping-gf-inc-code/mapping-gf-inc-code.component';
import { CheckCompanyPaymentComponent } from './../../project/pages/check-company-payment/check-company-payment.component';
import { CheckCompanyRenewComponent } from './../../project/pages/check-company-renew/check-company-renew.component';
import { CheckReprintLicenseComponent } from './../../project/pages/check-reprint-license/check-reprint-license.component';
import { CheckReceiptTaxComponent } from './../../project/pages/check-receipt-tax/check-receipt-tax.component';
import { CheckReceiptLicenseComponent } from './../../project/pages/check-receipt-license/check-receipt-license.component';
import { CreateTraderComponent } from './../../project/tax-audit/trader-selection/create-trader/create-trader.component';
import { AnalystBasicDataTraderComponent } from './../../project/tax-audit/trader-selection/analyst-basic-data-trader/analyst-basic-data-trader.component';
import { CreateWorkingPaperTraderComponent } from './../../project/tax-audit/trader-selection/create-working-paper-trader/create-working-paper-trader.component';
import { WorkingPaper1TraderComponent } from './../../project/tax-audit/trader-selection/working-paper-1-trader/working-paper-1-trader.component';
import { WorkingPaper1FullTraderComponent } from './../../project/tax-audit/trader-selection/working-paper-1-full-trader/working-paper-1-full-trader.component';
import { SummaryAmountProductTraderComponent } from './../../project/tax-audit/trader-selection/summary-amount-product-trader/summary-amount-product-trader.component';
import { CreateSaveMessageTraderComponent } from './../../project/tax-audit/trader-selection/create-save-message-trader/create-save-message-trader.component';
import { CompareReportAssetComponent } from './../../project/pages/compare-report-asset/compare-report-asset.component';
import { CheckStampYearlyComponent } from './../../project/pages/check-stamp-yearly/check-stamp-yearly.component';
import { CheckStampBranchComponent } from './../../project/pages/check-stamp-branch/check-stamp-branch.component';
import { SaveResultTradingComponent } from './../../project/pages/save-result-trading/save-result-trading.component';

const routes: Routes = [

    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'login',  component: LoginPage },
    { path: 'home', component: HomePage , canActivate: [AuthGuard]},
    { path: 'message', component: MessagePage, canActivate: [AuthGuard] },
    { path: 'add-message', component: MessageDetailPage, canActivate: [AuthGuard] },
    { path: 'edit-message/:id', component: MessageDetailPage, canActivate: [AuthGuard] },

    { path: 'parameterInfo', component: ParameterInfoPage },
    { path: 'parameterGroup', component: ParameterGroupPage },
    { path: 'ParameterInfoDetail', component: ParameterInfoDetailPage },

    { path: 'userManagement', component: UserManagementPage, canActivate: [AuthGuard] },
    { path: 'add-userManagement', component: UserManagementDetailPage, canActivate: [AuthGuard] },
    { path: 'edit-userManagement/:id', component: UserManagementDetailPage, canActivate: [AuthGuard] },

    { path: 'analysis', component: AnalysisPage, canActivate: [AuthGuard] },
    { path: 'result-analysis', component: ResultAnalysisPage, canActivate: [AuthGuard] },
    { path: 'select-form', component: SelectFormComponent, canActivate: [AuthGuard] },
    { path: 'create-form', component: CreateFormComponent, canActivate: [AuthGuard] },
    { path: 'select-new-form', component: SelectNewFormComponent, canActivate: [AuthGuard] },
    { path: 'create-new-form', component: CreateNewFormComponent, canActivate: [AuthGuard] },

    { path: 'import-gf-accounting', component: ImportGfAccountingComponent, canActivate: [AuthGuard] },
    { path: 'select-inc-summary', component: SelectIncSummaryComponent, canActivate: [AuthGuard] },
    { path: 'result-analysis-gf-inc', component: ResultAnalysisGfIncComponent, canActivate: [AuthGuard] },
    { path: 'mapping-gf-inc-code', component: MappingGfIncCodeComponent, canActivate: [AuthGuard] },

    { path: 'check-company-payment', component: CheckCompanyPaymentComponent, canActivate: [AuthGuard] },
    { path: 'check-company-renew', component: CheckCompanyRenewComponent, canActivate: [AuthGuard] },
    { path: 'check-reprint-license', component: CheckReprintLicenseComponent, canActivate: [AuthGuard] },

    { path: 'check-receipt-tax', component: CheckReceiptTaxComponent, canActivate: [AuthGuard] },
    { path: 'check-receipt-license', component: CheckReceiptLicenseComponent, canActivate: [AuthGuard] },
    { path: 'trader-selection', component: CreateTraderComponent, canActivate: [AuthGuard] },
    { path: 'analyst-basic-data-trader', component: AnalystBasicDataTraderComponent, canActivate: [AuthGuard] },
    { path: 'create-working-paper-trader', component: CreateWorkingPaperTraderComponent, canActivate: [AuthGuard] },
    { path: 'working-paper-1-trader', component: WorkingPaper1TraderComponent, canActivate: [AuthGuard] },
    { path: 'working-paper-1-full-trader', component: WorkingPaper1FullTraderComponent, canActivate: [AuthGuard] },
    { path: 'summary-amount-product-trader', component: SummaryAmountProductTraderComponent, canActivate: [AuthGuard] },
    { path: 'create-save-message-trader', component: CreateSaveMessageTraderComponent, canActivate: [AuthGuard] },
    
    { path: 'compare-report-asset', component: CompareReportAssetComponent, canActivate: [AuthGuard] },
    { path: 'check-stamp-yearly', component: CheckStampYearlyComponent, canActivate: [AuthGuard] },
    { path: 'check-stamp-branch', component: CheckStampBranchComponent, canActivate: [AuthGuard] },
    { path: 'save-result-trading', component: SaveResultTradingComponent, canActivate: [AuthGuard] },
    
];

@NgModule({
    imports: [ RouterModule.forRoot(routes, { useHash: true }) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule {}