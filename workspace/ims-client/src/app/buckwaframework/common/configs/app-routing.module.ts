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
import { TaxAuditReportingComponent } from '../../project/pages/reports/tax-audit-reporting/tax-audit-reporting.component';
import { ResultAnalysisPage } from '../../project/pages/result-analysis/result-analysis.component';
import { SelectFormComponent } from './../../project/pages/select-form/select-form.component';
import { CreateFormComponent } from './../../project/pages/create-form/create-form.component';
import { SelectNewFormComponent } from './../../project/pages/select-new-form/select-new-form.component';
import { CreateNewFormComponent } from './../../project/pages/create-new-form/create-new-form.component';
import { CheckReceiptTaxComponent } from './../../project/pages/check-receipt-tax/check-receipt-tax.component';
import { CheckReceiptLicenseComponent } from './../../project/pages/check-receipt-license/check-receipt-license.component';
import { CreateTraderComponent } from './../../project/tax-audit/trader-selection/create-trader/create-trader.component';
import { AnalystBasicDataTraderComponent } from './../../project/tax-audit/trader-selection/analyst-basic-data-trader/analyst-basic-data-trader.component';
import { AddDataComponent } from '../../project/tax-audit/trader-selection/analyst-basic-data-trader/add-data/add-data.component';
import { CreateWorkingPaperTraderComponent } from './../../project/tax-audit/trader-selection/create-working-paper-trader/create-working-paper-trader.component';
import { WorkingPaper1TraderComponent } from './../../project/tax-audit/trader-selection/working-paper-1-trader/working-paper-1-trader.component';
import { WorkingPaper1FullTraderComponent } from './../../project/tax-audit/trader-selection/working-paper-1-full-trader/working-paper-1-full-trader.component';
import { SummaryAmountProductTraderComponent } from './../../project/tax-audit/trader-selection/summary-amount-product-trader/summary-amount-product-trader.component';
import { CreateSaveMessageTraderComponent } from './../../project/tax-audit/trader-selection/create-save-message-trader/create-save-message-trader.component';

import { Epa0112Component } from './../../project/export-audit/epa01/epa01-1/epa01-1-2/epa01-1-2.component';
import { Epa011Component } from './../../project/export-audit/epa01/epa01-1/epa01-1.component';
import { Epa0111Component } from './../../project/export-audit/epa01/epa01-1/epa01-1-1/epa01-1-1.component';
import { Epa021Component } from '../../project/export-audit/epa02/epa02-1/epa02-1.component';
import { Epa0211Component } from '../../project/export-audit/epa02/epa02-1/epa02-1-1/epa02-1-1.component';
import { Epa0212Component } from '../../project/export-audit/epa02/epa02-1/epa02-1-2/epa02-1-2.component';
import { Epa0213Component } from '../../project/export-audit/epa02/epa02-1/epa02-1-3/epa02-1-3.component';
import { Cop011Component } from './../../project/check-operation/cop01/cop01-1/cop01-1.component';
import { Cop012Component } from './../../project/check-operation/cop01/cop01-2/cop01-2.component';
import { Cop013Component } from './../../project/check-operation/cop01/cop01-3/cop01-3.component';
import { Cop021Component } from './../../project/check-operation/cop02/cop02-1/cop02-1.component';
import { Cop0212Component } from './../../project/check-operation/cop02/cop02-1/cop02-1-2/cop02-1-2.component';
import { Cop0211Component } from './../../project/check-operation/cop02/cop02-1/cop02-1-1/cop02-1-1.component';
import { Cop031Component } from './../../project/check-operation/cop03/cop03-1/cop03-1.component';
import { Cop0311Component } from './../../project/check-operation/cop03/cop03-1-1/cop03-1-1.component';
import { Cop03111Component } from './../../project/check-operation/cop03/cop03-1-1/cop03-1-1-1/cop03-1-1-1.component';
import { Cop041Component } from './../../project/check-operation/cop04/cop04-1/cop04-1.component';
import { Cop0411Component } from './../../project/check-operation/cop04/cop04-1/cop04-1-1/cop04-1-1.component';
import { Cop042Component } from './../../project/check-operation/cop04/cop04-2/cop04-2.component';
import { Cop0421Component } from './../../project/check-operation/cop04/cop04-2/cop04-2-1/cop04-2-1.component';
import { Cop051Component } from './../../project/check-operation/cop05/cop05-1/cop05-1.component';
import { Cop0511Component } from './../../project/check-operation/cop05/cop05-1/cop05-1-1/cop05-1-1.component';







import { Int0111Component } from './../../project/internal-audit/int01/int01-1/int01-1-1/int01-1-1.component';
import { Int0112Component } from './../../project/internal-audit/int01/int01-1/int01-1-2/int01-1-2.component';
import { Int0113Component } from './../../project/internal-audit/int01/int01-1/int01-1-3/int01-1-3.component';
import { Int0121Component } from './../../project/internal-audit/int01/int01-2/int01-2-1/int01-2-1.component';
import { Int0122Component } from './../../project/internal-audit/int01/int01-2/int01-2-2/int01-2-2.component';
import { Int0123Component } from './../../project/internal-audit/int01/int01-2/int01-2-3/int01-2-3.component';
import { Int0131Component } from '../../project/internal-audit/int01/int01-3/int01-3-1/int01-3-1.component';
import { Int0132Component } from '../../project/internal-audit/int01/int01-3/int01-3-2/int01-3-2.component';
import { Int0133Component } from '../../project/internal-audit/int01/int01-3/int01-3-3/int01-3-3.component';
import { Int0141Component } from '../../project/internal-audit/int01/int01-4/int01-4-1/int01-4-1.component';
import { Int0142Component } from '../../project/internal-audit/int01/int01-4/int01-4-2/int01-4-2.component';
import { Int0143Component } from '../../project/internal-audit/int01/int01-4/int01-4-3/int01-4-3.component';
import { Int0144Component } from '../../project/internal-audit/int01/int01-4/int01-4-4/int01-4-4.component';
import { Int0151Component } from '../../project/internal-audit/int01/int01-5/int01-5-1/int01-5-1.component';
import { Int0161Component } from '../../project/internal-audit/int01/int01-6/int01-6-1/int01-6-1.component';
import { Int0171Component } from '../../project/internal-audit/int01/int01-7/int01-7-1/int01-7-1.component';
import { Int0181Component } from '../../project/internal-audit/int01/int01-8/int01-8-1/int01-8-1.component';



import { Int051Component } from './../../project/internal-audit/int05/int05-1/int05-1.component';
import { Int0511Component } from './../../project/internal-audit/int05/int05-1/int05-1-1/int05-1-1.component';
import { Int05111Component } from './../../project/internal-audit/int05/int05-1/int05-1-1/int05-1-1-1/int05-1-1-1.component';
import { Int05112Component } from './../../project/internal-audit/int05/int05-1/int05-1-1/int05-1-1-2/int05-1-1-2.component';
import { Int05113Component } from './../../project/internal-audit/int05/int05-1/int05-1-1/int05-1-1-3/int05-1-1-3.component';

import { Int052Component } from './../../project/internal-audit/int05/int05-2/int05-2.component';
import { Int0521Component } from './../../project/internal-audit/int05/int05-2/int05-2-1/int05-2-1.component';
import { Int0522Component } from './../../project/internal-audit/int05/int05-2/int05-2-2/int05-2-2.component';
import { Int0523Component } from './../../project/internal-audit/int05/int05-2/int05-2-3/int05-2-3.component';
import { Int0524Component } from './../../project/internal-audit/int05/int05-2/int05-2-4/int05-2-4.component';
import { Int053Component } from './../../project/internal-audit/int05/int05-3/int05-3.component';
import { Int0531Component } from './../../project/internal-audit/int05/int05-3/int05-3-1/int05-3-1.component';
import { Int054Component } from './../../project/internal-audit/int05/int05-4/int05-4.component';
import { Int061Component } from './../../project/internal-audit/int06/int06-1/int06-1.component';
import { Int0611Component } from '../../project/internal-audit/int06/int06-1/int06-1-1/int06-1-1.component';
import { Int062Component } from './../../project/internal-audit/int06/int06-2/int06-2.component';
import { Int0621Component } from './../../project/internal-audit/int06/int06-2/int06-2-1/int06-2-1.component';
import { Int063Component } from './../../project/internal-audit/int06/int06-3/int06-3.component';
import { Int0631Component } from './../../project/internal-audit/int06/int06-3/int06-3-1/int06-3-1.component';
import { Int0632Component } from './../../project/internal-audit/int06/int06-3/int06-3-2/int06-3-2.component';
import { Int0633Component } from '../../project/internal-audit/int06/int06-3/int06-3-3/int06-3-3.component';
import { Int0634Component } from '../../project/internal-audit/int06/int06-3/int06-3-4/int06-3-4.component';
import { Int064Component } from './../../project/internal-audit/int06/int06-4/int06-4.component';
import { Int0641Component } from './../../project/internal-audit/int06/int06-4/int06-4-1/int06-4-1.component';
import { Int0642Component } from './../../project/internal-audit/int06/int06-4/int06-4-2/int06-4-2.component';
import { Int0644Component } from '../../project/internal-audit/int06/int06-4/int06-4-4/int06-4-4.component';
import { Int0643Component } from '../../project/internal-audit/int06/int06-4/int06-4-3/int06-4-3.component';
import { Int065Component } from './../../project/internal-audit/int06/int06-5/int06-5.component';
import { Int0651Component } from './../../project/internal-audit/int06/int06-5/int06-5-1/int06-5-1.component';
import { Int0652Component } from './../../project/internal-audit/int06/int06-5/int06-5-2/int06-5-2.component';
import { Int0654Component } from '../../project/internal-audit/int06/int06-5/int06-5-4/int06-5-4.component';
import { Int0653Component } from '../../project/internal-audit/int06/int06-5/int06-5-3/int06-5-3.component';
import { Int066Component } from './../../project/internal-audit/int06/int06-6/int06-6.component';
import { Int0661Component } from './../../project/internal-audit/int06/int06-6/int06-6-1/int06-6-1.component';
import { Int0662Component } from './../../project/internal-audit/int06/int06-6/int06-6-2/int06-6-2.component';
import { Int0664Component } from '../../project/internal-audit/int06/int06-6/int06-6-4/int06-6-4.component';
import { Int0663Component } from '../../project/internal-audit/int06/int06-6/int06-6-3/int06-6-3.component';
import { Int071Component } from './../../project/internal-audit/int07/int07-1/int07-1.component';
import { Int072Component } from './../../project/internal-audit/int07/int07-2/int07-2.component';
import { Int073Component } from './../../project/internal-audit/int07/int07-3/int07-3.component';
import { Int074Component } from './../../project/internal-audit/int07/int07-4/int07-4.component';
import { Int075Component } from './../../project/internal-audit/int07/int07-5/int07-5.component';
import { Int076Component } from './../../project/internal-audit/int07/int07-6/int07-6.component';
import { Int077Component } from './../../project/internal-audit/int07/int07-7/int07-7.component';

import { Int0751Component } from './../../project/internal-audit/int07/int07-5/int07-5-1/int07-5-1/int07-5-1.component';
import { Int0752Component } from './../../project/internal-audit/int07/int07-5/int07-5-2/int07-5-2.component';
import { Int0753Component } from './../../project/internal-audit/int07/int07-5/int07-5-3/int07-5-3.component';
import { Int0754Component } from './../../project/internal-audit/int07/int07-5/int07-5-4/int07-5-4.component';
import { Int081Component } from './../../project/internal-audit/int08/int08-1/int08-1.component';
import { Int0811Component } from './../../project/internal-audit/int08/int08-1/int08-1-1/int08-1-1.component';
import { Int0812Component } from './../../project/internal-audit/int08/int08-1/int08-1-2/int08-1-2.component';
import { Int0813Component } from './../../project/internal-audit/int08/int08-1/int08-1-3/int08-1-3.component';
import { Int082Component } from './../../project/internal-audit/int08/int08-2/int08-2.component';
import { Int083Component } from './../../project/internal-audit/int08/int08-3/int08-3.component';
import { Int0831Component } from './../../project/internal-audit/int08/int08-3/int08-3-1/int08-3-1.component';
import { Int0832Component } from './../../project/internal-audit/int08/int08-3/int08-3-2/int08-3-2.component';
import { Int0833Component } from './../../project/internal-audit/int08/int08-3/int08-3-3/int08-3-3.component';
import { Int08331Component } from './../../project/internal-audit/int08/int08-3/int08-3-3/int08-3-3-1/int08-3-3-1.component';
import { Int08332Component } from './../../project/internal-audit/int08/int08-3/int08-3-3/int08-3-3-2/int08-3-3-2.component';
import { Int08333Component } from './../../project/internal-audit/int08/int08-3/int08-3-3/int08-3-3-3/int08-3-3-3.component';
import { Int0834Component } from './../../project/internal-audit/int08/int08-3/int08-3-4/int08-3-4.component';
import { Int0835Component } from './../../project/internal-audit/int08/int08-3/int08-3-5/int08-3-5.component';
import { Int0836Component } from './../../project/internal-audit/int08/int08-3/int08-3-6/int08-3-6.component';
import { Ope041Component } from './../../project/tax-audit/operate-tax-audit/ope04/ope04-1/ope04-1.component';
import { Ope042Component } from './../../project/tax-audit/operate-tax-audit/ope04/ope04-2/ope04-2.component';
import { Ope043Component } from './../../project/tax-audit/operate-tax-audit/ope04/ope04-3/ope04-3.component';
import { Ope044Component } from './../../project/tax-audit/operate-tax-audit/ope04/ope04-4/ope04-4.component';
import { Ope0441Component } from './../../project/tax-audit/operate-tax-audit/ope04/ope04-4/ope04-4-1/ope04-4-1.component';
import { Ope045Component } from './../../project/tax-audit/operate-tax-audit/ope04/ope04-5/ope04-5.component';
import { Ope046Component } from './../../project/tax-audit/operate-tax-audit/ope04/ope04-6/ope04-6.component';
import { Ope047Component } from './../../project/tax-audit/operate-tax-audit/ope04/ope04-7/ope04-7.component';
import { Ope048Component } from './../../project/tax-audit/operate-tax-audit/ope04/ope04-8/ope04-8.component';
import { Ope049Component } from './../../project/tax-audit/operate-tax-audit/ope04/ope04-9/ope04-9.component';
import { Ope0410Component } from './../../project/tax-audit/operate-tax-audit/ope04/ope04-10/ope04-10.component';
import { Ope051Component } from './../../project/tax-audit/operate-tax-audit/ope05/ope05-1/ope05-1.component';
import { Ope0511Component } from './../../project/tax-audit/operate-tax-audit/ope05/ope05-1/ope05-1-1/ope05-1-1.component';
import { Ope052Component } from './../../project/tax-audit/operate-tax-audit/ope05/ope05-2/ope05-2.component';
import { Ope053Component } from './../../project/tax-audit/operate-tax-audit/ope05/ope05-3/ope05-3.component';
import { Ope054Component } from './../../project/tax-audit/operate-tax-audit/ope05/ope05-4/ope05-4.component';
import { Ope055Component } from './../../project/tax-audit/operate-tax-audit/ope05/ope05-5/ope05-5.component';
import { Ope056Component } from './../../project/tax-audit/operate-tax-audit/ope05/ope05-6/ope05-6.component';
import { Ope057Component } from './../../project/tax-audit/operate-tax-audit/ope05/ope05-7/ope05-7.component';

























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
    { path: 'tax-audit-manage', component: TaxAuditReportingComponent, canActivate: [AuthGuard] },
    { path: 'result-analysis/:category/:coordinate', component: ResultAnalysisPage, canActivate: [AuthGuard] },
    { path: 'select-form/:category/:coordinate', component: SelectFormComponent, canActivate: [AuthGuard] },
    { path: 'create-form', component: CreateFormComponent, canActivate: [AuthGuard] },
    { path: 'select-new-form', component: SelectNewFormComponent, canActivate: [AuthGuard] },
    { path: 'create-new-form', component: CreateNewFormComponent, canActivate: [AuthGuard] },

    { path: 'check-receipt-tax', component: CheckReceiptTaxComponent, canActivate: [AuthGuard] },
    { path: 'check-receipt-license', component: CheckReceiptLicenseComponent, canActivate: [AuthGuard] },
    { path: 'trader-selection', component: CreateTraderComponent, canActivate: [AuthGuard] },
    { path: 'analyst-basic-data-trader', component: AnalystBasicDataTraderComponent, canActivate: [AuthGuard] },
    { path: 'add-data', component: AddDataComponent, canActivate: [AuthGuard] },
    { path: 'create-working-paper-trader', component: CreateWorkingPaperTraderComponent, canActivate: [AuthGuard] },
    { path: 'working-paper-1-trader', component: WorkingPaper1TraderComponent, canActivate: [AuthGuard] },
    { path: 'working-paper-1-full-trader', component: WorkingPaper1FullTraderComponent, canActivate: [AuthGuard] },
    { path: 'summary-amount-product-trader', component: SummaryAmountProductTraderComponent, canActivate: [AuthGuard] },
    { path: 'create-save-message-trader', component: CreateSaveMessageTraderComponent, canActivate: [AuthGuard] },

    { path: 'epa01-1', component: Epa011Component, canActivate: [AuthGuard] },    
    { path: 'epa01-1-1', component: Epa0111Component, canActivate: [AuthGuard] },
    { path: 'epa01-1-2', component: Epa0112Component, canActivate: [AuthGuard] },
    { path: 'epa02-1', component: Epa021Component, canActivate: [AuthGuard] },   
    { path: 'epa02-1-1', component: Epa0211Component, canActivate: [AuthGuard] },
    { path: 'epa02-1-2', component: Epa0212Component, canActivate: [AuthGuard] },
    { path: 'epa02-1-3', component: Epa0213Component, canActivate: [AuthGuard] }, 
   

    { path: 'cop01-1', component: Cop011Component, canActivate: [AuthGuard] },
    { path: 'cop01-2', component: Cop012Component, canActivate: [AuthGuard] },
    { path: 'cop01-3', component: Cop013Component, canActivate: [AuthGuard] },
    { path: 'cop02-1', component: Cop021Component, canActivate: [AuthGuard] },
    { path: 'cop02-1-1', component: Cop0211Component, canActivate: [AuthGuard] },
    { path: 'cop02-1-2', component: Cop0212Component, canActivate: [AuthGuard] },    
    { path: 'cop03-1', component: Cop031Component, canActivate: [AuthGuard] },
    { path: 'cop03-1-1', component: Cop0311Component, canActivate: [AuthGuard] },
    { path: 'cop03-1-1-1', component: Cop03111Component, canActivate: [AuthGuard] },
    { path: 'cop04-1', component: Cop041Component, canActivate: [AuthGuard] },
    { path: 'cop04-1-1', component: Cop0411Component, canActivate: [AuthGuard] },
    { path: 'cop04-2', component: Cop042Component, canActivate: [AuthGuard] },
    { path: 'cop04-2-1', component: Cop0421Component, canActivate: [AuthGuard] },
    { path: 'cop05-1', component: Cop051Component, canActivate: [AuthGuard] },    
    { path: 'cop05-1-1', component: Cop0511Component, canActivate: [AuthGuard] },


    { path: 'int01-1-1', component: Int0111Component, canActivate: [AuthGuard] },
    { path: 'int01-1-2', component: Int0112Component, canActivate: [AuthGuard] },
    { path: 'int01-1-3', component: Int0113Component, canActivate: [AuthGuard] },
    { path: 'int01-2-1', component: Int0121Component, canActivate: [AuthGuard] },
    { path: 'int01-2-2', component: Int0122Component, canActivate: [AuthGuard] },
    { path: 'int01-2-3', component: Int0123Component, canActivate: [AuthGuard] },
    { path: 'int01-3-1', component: Int0131Component, canActivate: [AuthGuard] },
    { path: 'int01-3-2', component: Int0132Component, canActivate: [AuthGuard] },
    { path: 'int01-3-3', component: Int0133Component, canActivate: [AuthGuard] },
    { path: 'int01-4-1', component: Int0141Component, canActivate: [AuthGuard] },
    { path: 'int01-4-2', component: Int0142Component, canActivate: [AuthGuard] },
    { path: 'int01-4-3', component: Int0143Component, canActivate: [AuthGuard] },
    { path: 'int01-4-4', component: Int0144Component, canActivate: [AuthGuard] },
    { path: 'int01-5-1', component: Int0151Component, canActivate: [AuthGuard] },
    { path: 'int01-6-1', component: Int0161Component, canActivate: [AuthGuard] },
    { path: 'int01-7-1', component: Int0171Component, canActivate: [AuthGuard] },
    { path: 'int01-8-1', component: Int0181Component, canActivate: [AuthGuard] },  
    { path: 'int05-1', component: Int051Component, canActivate: [AuthGuard] },    
    { path: 'int05-1-1', component: Int0511Component, canActivate: [AuthGuard] },
    { path: 'int05-1-1-1', component: Int05111Component, canActivate: [AuthGuard] },
    { path: 'int05-1-1-2', component: Int05112Component, canActivate: [AuthGuard] },
    { path: 'int05-1-1-3', component: Int05113Component, canActivate: [AuthGuard] },
    { path: 'int05-2', component: Int052Component, canActivate: [AuthGuard] },
    { path: 'int05-2-1', component: Int0521Component, canActivate: [AuthGuard] },
    { path: 'int05-2-2', component: Int0522Component, canActivate: [AuthGuard] },
    { path: 'int05-2-3', component: Int0523Component, canActivate: [AuthGuard] },
    { path: 'int05-2-4', component: Int0524Component, canActivate: [AuthGuard] },
    { path: 'int05-3', component: Int053Component, canActivate: [AuthGuard] },
    { path: 'int05-3-1', component: Int0531Component, canActivate: [AuthGuard] },
    { path: 'int05-4', component: Int054Component, canActivate: [AuthGuard] },

    { path: 'int06-1', component: Int061Component, canActivate: [AuthGuard] },
    { path: 'int06-1-1', component: Int0611Component, canActivate: [AuthGuard] },
    { path: 'int06-2', component: Int062Component, canActivate: [AuthGuard] },
    { path: 'int06-2-1', component: Int0621Component, canActivate: [AuthGuard] },
    { path: 'int06-3', component: Int063Component, canActivate: [AuthGuard] },
    { path: 'int06-3-1', component: Int0631Component, canActivate: [AuthGuard] },
    { path: 'int06-3-2', component: Int0632Component, canActivate: [AuthGuard] },
    { path: 'int06-3-3', component: Int0633Component, canActivate: [AuthGuard] },
    { path: 'int06-3-4', component: Int0634Component, canActivate: [AuthGuard] },
    { path: 'int06-4', component: Int064Component, canActivate: [AuthGuard] },
    { path: 'int06-4-1', component: Int0641Component, canActivate: [AuthGuard] },
    { path: 'int06-4-2', component: Int0642Component, canActivate: [AuthGuard] },
    { path: 'int06-4-3', component: Int0643Component, canActivate: [AuthGuard] },
    { path: 'int06-4-4', component: Int0644Component, canActivate: [AuthGuard] },
    { path: 'int06-5', component: Int065Component, canActivate: [AuthGuard] },
    { path: 'int06-5-1', component: Int0651Component, canActivate: [AuthGuard] },
    { path: 'int06-5-2', component: Int0652Component, canActivate: [AuthGuard] },
    { path: 'int06-5-3', component: Int0653Component, canActivate: [AuthGuard] },
    { path: 'int06-5-4', component: Int0654Component, canActivate: [AuthGuard] },
    { path: 'int06-6', component: Int066Component, canActivate: [AuthGuard] },
    { path: 'int06-6-1', component: Int0661Component, canActivate: [AuthGuard] },
    { path: 'int06-6-2', component: Int0662Component, canActivate: [AuthGuard] },
    { path: 'int06-6-3', component: Int0663Component, canActivate: [AuthGuard] },
    { path: 'int06-6-4', component: Int0664Component, canActivate: [AuthGuard] },
    { path: 'int07-1', component: Int071Component, canActivate: [AuthGuard] },
    { path: 'int07-2', component: Int072Component, canActivate: [AuthGuard] },
    { path: 'int07-3', component: Int073Component, canActivate: [AuthGuard] },
    { path: 'int07-4', component: Int074Component, canActivate: [AuthGuard] },
    { path: 'int07-5', component: Int075Component, canActivate: [AuthGuard] },
    { path: 'int07-5-1', component: Int0751Component, canActivate: [AuthGuard] },
    { path: 'int07-5-2', component: Int0752Component, canActivate: [AuthGuard] },
    { path: 'int07-5-3', component: Int0753Component, canActivate: [AuthGuard] },
    { path: 'int07-5-4', component: Int0754Component, canActivate: [AuthGuard] },
    { path: 'int07-6', component: Int076Component, canActivate: [AuthGuard] },
    { path: 'int07-7', component: Int077Component, canActivate: [AuthGuard] },

    { path: 'int08-1', component: Int081Component, canActivate: [AuthGuard] },
    { path: 'int08-1-1', component: Int0811Component, canActivate: [AuthGuard] },
    { path: 'int08-1-2', component: Int0812Component, canActivate: [AuthGuard] },
    { path: 'int08-1-3', component: Int0813Component, canActivate: [AuthGuard] },
    { path: 'int08-2', component: Int082Component, canActivate: [AuthGuard] },
    { path: 'int08-3', component: Int083Component, canActivate: [AuthGuard] },
    { path: 'int08-3-1', component: Int0831Component, canActivate: [AuthGuard] },
    { path: 'int08-3-2', component: Int0832Component, canActivate: [AuthGuard] },
    { path: 'int08-3-3', component: Int0833Component, canActivate: [AuthGuard] },
    { path: 'int08-3-3-1', component: Int08331Component, canActivate: [AuthGuard] },
    { path: 'int08-3-3-2', component: Int08332Component, canActivate: [AuthGuard] },
    { path: 'int08-3-3-3', component: Int08333Component, canActivate: [AuthGuard] },
    { path: 'int08-3-4', component: Int0834Component, canActivate: [AuthGuard] },
    { path: 'int08-3-5', component: Int0835Component, canActivate: [AuthGuard] },
    { path: 'int08-3-6', component: Int0836Component, canActivate: [AuthGuard] },    

    { path: 'ope04-1', component: Ope041Component, canActivate: [AuthGuard] },
    { path: 'ope04-2', component: Ope042Component, canActivate: [AuthGuard] },
    { path: 'ope04-3', component: Ope043Component, canActivate: [AuthGuard] },
    { path: 'ope04-4', component: Ope044Component, canActivate: [AuthGuard] },
    { path: 'ope04-4-1', component: Ope0441Component, canActivate: [AuthGuard] },
    { path: 'ope04-5', component: Ope045Component, canActivate: [AuthGuard] },
    { path: 'ope04-6', component: Ope046Component, canActivate: [AuthGuard] },
    { path: 'ope04-7', component: Ope047Component, canActivate: [AuthGuard] },
    { path: 'ope04-8', component: Ope048Component, canActivate: [AuthGuard] },
    { path: 'ope04-9', component: Ope049Component, canActivate: [AuthGuard] },
    { path: 'ope04-10', component: Ope0410Component, canActivate: [AuthGuard] },

    { path: 'ope05-1', component: Ope051Component, canActivate: [AuthGuard] },
    { path: 'ope05-1-1', component: Ope0511Component, canActivate: [AuthGuard] },
    { path: 'ope05-2', component: Ope052Component, canActivate: [AuthGuard] },
    { path: 'ope05-3', component: Ope053Component, canActivate: [AuthGuard] },
    { path: 'ope05-4', component: Ope054Component, canActivate: [AuthGuard] },
    { path: 'ope05-5', component: Ope055Component, canActivate: [AuthGuard] },
    { path: 'ope05-6', component: Ope056Component, canActivate: [AuthGuard] },
    { path: 'ope05-7', component: Ope057Component, canActivate: [AuthGuard] },
    
];

@NgModule({
    imports: [RouterModule.forRoot(routes, { useHash: true })],
    exports: [RouterModule]
})
export class AppRoutingModule { }