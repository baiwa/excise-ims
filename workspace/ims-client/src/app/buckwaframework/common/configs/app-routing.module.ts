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
import { Int0111Component } from './../../project/internal-audit/int01/int01-1/int01-1-1/int01-1-1.component';
import { Int0112Component } from './../../project/internal-audit/int01/int01-1/int01-1-2/int01-1-2.component';
import { Int0113Component } from './../../project/internal-audit/int01/int01-1/int01-1-3/int01-1-3.component';
import { Int031Component } from './../../project/internal-audit/int03/int03-1/int03-1.component';
import { Int0311Component } from './../../project/internal-audit/int03/int03-1/int3-1-1/int03-1-1.component';
import { Int0312Component } from './../../project/internal-audit/int03/int03-1/int3-1-2/int03-1-2.component';
import { Int032Component } from './../../project/internal-audit/int03/int03-2/int03-2.component';
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
import { Int051Component } from './../../project/internal-audit/int05/int05-1/int05-1.component';
import { Int052Component } from './../../project/internal-audit/int05/int05-2/int05-2.component';
import { Int053Component } from './../../project/internal-audit/int05/int05-3/int05-3.component';
import { Int054Component } from './../../project/internal-audit/int05/int05-4/int05-4.component';
import { Int061Component } from './../../project/internal-audit/int06/int06-1/int06-1.component';
import { Int062Component } from './../../project/internal-audit/int06/int06-2/int06-2.component';
import { Int063Component } from './../../project/internal-audit/int06/int06-3/int06-3.component';
import { Int0631Component } from './../../project/internal-audit/int06/int06-3/int06-3-1/int06-3-1.component';
import { Int0632Component } from './../../project/internal-audit/int06/int06-3/int06-3-2/int06-3-2.component';
import { Int064Component } from './../../project/internal-audit/int06/int06-4/int06-4.component';
import { Int0641Component } from './../../project/internal-audit/int06/int06-4/int06-4-1/int06-4-1.component';
import { Int0642Component } from './../../project/internal-audit/int06/int06-4/int06-4-2/int06-4-2.component';
import { Int065Component } from './../../project/internal-audit/int06/int06-5/int06-5.component';
import { Int0651Component } from './../../project/internal-audit/int06/int06-5/int06-5-1/int06-5-1.component';
import { Int0652Component } from './../../project/internal-audit/int06/int06-5/int06-5-2/int06-5-2.component';
import { Int066Component } from './../../project/internal-audit/int06/int06-6/int06-6.component';
import { Int0661Component } from './../../project/internal-audit/int06/int06-6/int06-6-1/int06-6-1.component';
import { Int0662Component } from './../../project/internal-audit/int06/int06-6/int06-6-2/int06-6-2.component';
import { Int071Component } from './../../project/internal-audit/int07/int07-1/int07-1.component';
import { Int072Component } from './../../project/internal-audit/int07/int07-2/int07-2.component';
import { Int073Component } from './../../project/internal-audit/int07/int07-3/int07-3.component';
import { Int074Component } from './../../project/internal-audit/int07/int07-4/int07-4.component';
import { Int075Component } from './../../project/internal-audit/int07/int07-5/int07-5.component';
import { Int076Component } from './../../project/internal-audit/int07/int07-6/int07-6.component';
import { Int077Component } from './../../project/internal-audit/int07/int07-7/int07-7.component';
import { Int0771Component } from './../../project/internal-audit/int07/int07-7/int07-7-1/int07-7-1.component';
import { Int0772Component } from './../../project/internal-audit/int07/int07-7/int07-7-2/int07-7-2.component';
import { Int0773Component } from './../../project/internal-audit/int07/int07-7/int07-7-3/int07-7-3.component';
import { Int0774Component } from './../../project/internal-audit/int07/int07-7/int07-7-4/int07-7-4.component';
import { Int0775Component } from './../../project/internal-audit/int07/int07-7/int07-7-5/int07-7-5.component';
import { Int081Component } from './../../project/internal-audit/int08/int08-1/int08-1.component';
import { Int0811Component } from './../../project/internal-audit/int08/int08-1/int08-1-1/int08-1-1.component';
import { Int0812Component } from './../../project/internal-audit/int08/int08-1/int08-1-2/int08-1-2.component';
import { Int0813Component } from './../../project/internal-audit/int08/int08-1/int08-1-3/int08-1-3.component';
import { Int082Component } from './../../project/internal-audit/int08/int08-2/int08-2.component';
import { Int083Component } from './../../project/internal-audit/int08/int08-3/int08-3.component';
import { Int0831Component } from './../../project/internal-audit/int08/int08-3/int08-3-1/int08-3-1.component';
import { Int0832Component } from './../../project/internal-audit/int08/int08-3/int08-3-2/int08-3-2.component';
import { Int0833Component } from './../../project/internal-audit/int08/int08-3/int08-3-3/int08-3-3.component';
import { Int0834Component } from './../../project/internal-audit/int08/int08-3/int08-3-4/int08-3-4.component';
import { Int0835Component } from './../../project/internal-audit/int08/int08-3/int08-3-5/int08-3-5.component';
import { Int0836Component } from './../../project/internal-audit/int08/int08-3/int08-3-6/int08-3-6.component';
import { CreatePaperPayMaterialComponent } from './../../project/pages/create-paper-pay-material/create-paper-pay-material.component';
import { CreatePaperReceiveMaterialComponent } from './../../project/pages/create-paper-receive-material/create-paper-receive-material.component';
import { CreatePaperRelationMaterialComponent } from './../../project/pages/create-paper-relation-material/create-paper-relation-material.component';
import { CreatePaperReceiveInstantComponent } from './../../project/pages/create-paper-receive-instant/create-paper-receive-instant.component';
import { CreatePaperPayInstantComponent } from './../../project/pages/create-paper-pay-instant/create-paper-pay-instant.component';
import { CreatePaperCheckValueComponent } from './../../project/pages/create-paper-check-value/create-paper-check-value.component';

const routes: Routes = [

    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'login', component: LoginPage },
    { path: 'home', component: HomePage, canActivate: [AuthGuard] },
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

    { path: 'int01-1-1', component: Int0111Component, canActivate: [AuthGuard] },
    { path: 'int01-1-2', component: Int0112Component, canActivate: [AuthGuard] },
    { path: 'int01-1-3', component: Int0113Component, canActivate: [AuthGuard] },

    { path: 'int03-1', component: Int031Component, canActivate: [AuthGuard] },
    { path: 'int03-1-1', component: Int0311Component, canActivate: [AuthGuard] },
    { path: 'int03-1-2', component: Int0312Component, canActivate: [AuthGuard] },
    { path: 'int03-2', component: Int032Component, canActivate: [AuthGuard] },

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
    
    { path: 'int05-1', component: Int051Component, canActivate: [AuthGuard] },
    { path: 'int05-2', component: Int052Component, canActivate: [AuthGuard] },
    { path: 'int05-3', component: Int053Component, canActivate: [AuthGuard] },
    { path: 'int05-4', component: Int054Component, canActivate: [AuthGuard] },

    { path: 'int06-1', component: Int061Component, canActivate: [AuthGuard] },
    { path: 'int06-2', component: Int062Component, canActivate: [AuthGuard] },
    { path: 'int06-3', component: Int063Component, canActivate: [AuthGuard] },
    { path: 'int06-3-1', component: Int0631Component, canActivate: [AuthGuard] },
    { path: 'int06-3-2', component: Int0632Component, canActivate: [AuthGuard] },
    { path: 'int06-4', component: Int064Component, canActivate: [AuthGuard] },
    { path: 'int06-4-1', component: Int0641Component, canActivate: [AuthGuard] },
    { path: 'int06-4-2', component: Int0642Component, canActivate: [AuthGuard] },
    { path: 'int06-5', component: Int065Component, canActivate: [AuthGuard] },
    { path: 'int06-5-1', component: Int0651Component, canActivate: [AuthGuard] },
    { path: 'int06-5-2', component: Int0652Component, canActivate: [AuthGuard] },
    { path: 'int06-6', component: Int066Component, canActivate: [AuthGuard] },
    { path: 'int06-6-1', component: Int0661Component, canActivate: [AuthGuard] },
    { path: 'int06-6-2', component: Int0662Component, canActivate: [AuthGuard] },
    
    { path: 'int07-1', component: Int071Component, canActivate: [AuthGuard] },
    { path: 'int07-2', component: Int072Component, canActivate: [AuthGuard] },
    { path: 'int07-3', component: Int073Component, canActivate: [AuthGuard] },
    { path: 'int07-4', component: Int074Component, canActivate: [AuthGuard] },
    { path: 'int07-5', component: Int075Component, canActivate: [AuthGuard] },
    { path: 'int07-6', component: Int076Component, canActivate: [AuthGuard] },
    { path: 'int07-7', component: Int077Component, canActivate: [AuthGuard] },
    { path: 'int07-7-1', component: Int0771Component, canActivate: [AuthGuard] },
    { path: 'int07-7-2', component: Int0772Component, canActivate: [AuthGuard] },
    { path: 'int07-7-3', component: Int0773Component, canActivate: [AuthGuard] },
    { path: 'int07-7-4', component: Int0774Component, canActivate: [AuthGuard] },
    { path: 'int07-7-5', component: Int0775Component, canActivate: [AuthGuard] },

    { path: 'int08-1', component: Int081Component, canActivate: [AuthGuard] },
    { path: 'int08-1-1', component: Int0811Component, canActivate: [AuthGuard] },
    { path: 'int08-1-2', component: Int0812Component, canActivate: [AuthGuard] },
    { path: 'int08-1-3', component: Int0813Component, canActivate: [AuthGuard] },
    { path: 'int08-2', component: Int082Component, canActivate: [AuthGuard] },
    { path: 'int08-3', component: Int083Component, canActivate: [AuthGuard] },
    { path: 'int08-3-1', component: Int0831Component, canActivate: [AuthGuard] },
    { path: 'int08-3-2', component: Int0832Component, canActivate: [AuthGuard] },
    { path: 'int08-3-3', component: Int0833Component, canActivate: [AuthGuard] },
    { path: 'int08-3-4', component: Int0834Component, canActivate: [AuthGuard] },
    { path: 'int08-3-5', component: Int0835Component, canActivate: [AuthGuard] },
    { path: 'int08-3-6', component: Int0836Component, canActivate: [AuthGuard] },    

    { path: 'create-paper-receive-material', component: CreatePaperReceiveMaterialComponent, canActivate: [AuthGuard] },
    { path: 'create-paper-pay-material', component: CreatePaperPayMaterialComponent, canActivate: [AuthGuard] },
    { path: 'create-paper-relation-material', component: CreatePaperRelationMaterialComponent, canActivate: [AuthGuard] },
    { path: 'create-paper-receive-instant', component: CreatePaperReceiveInstantComponent, canActivate: [AuthGuard] },
    { path: 'create-paper-pay-instant', component: CreatePaperPayInstantComponent, canActivate: [AuthGuard] },
    { path: 'create-paper-check-value', component: CreatePaperCheckValueComponent, canActivate: [AuthGuard] },
    
];

@NgModule({
    imports: [RouterModule.forRoot(routes, { useHash: true })],
    exports: [RouterModule]
})
export class AppRoutingModule { }