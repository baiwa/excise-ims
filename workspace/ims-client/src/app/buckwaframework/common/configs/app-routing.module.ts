import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// service
import { AuthGuard } from '../services/auth-guard.service';

// pages
import { AddExternalDataComponent } from '../../project/tax-audit/trader-selection/add-external-data/add-external-data.component';
import { SendLineUserComponent } from '../../project/tax-audit/trader-selection/add-external-data/send-line-user/send-line-user.component';

const routes: Routes = [

    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'login', loadChildren: '../../project/pages/login/login.module#LoginModule' },
    { path: 'home', loadChildren: '../../project/pages/home/home.module#HomeModule' },
    { path: 'message', loadChildren: '../../project/pages/message/message.module#MessageModule' },
    { path: 'add-message', loadChildren: '../../project/pages/message/message-detail.module#MessageDetailModule' },
    { path: 'edit-message', loadChildren: '../../project/pages/message/message-detail.module#MessageDetailModule' },

    { path: 'parameterInfo', loadChildren: '../../project/pages/parameterInfo/parameterInfo.module#ParameterInfoPageModule' },
    { path: 'parameterGroup', loadChildren: '../../project/pages/parameterGroup/parameterGroup.module#ParameterGroupPageModule' },
    { path: 'parameterInfoDetail', loadChildren: '../../project/pages/parameterInfo/parameterInfoDetail.module#ParameterInfoDetailModule' },

    { path: 'userManagement', loadChildren: '../../project/pages/userManagement/userManagement.module#UserManagementPageModule' },
    { path: 'add-userManagement', loadChildren: '../../project/pages/userManagement/userManagement-detail.module#UserManagementDetailPageModule' },
    { path: 'edit-userManagement', loadChildren: '../../project/pages/userManagement/userManagement-detail.module#UserManagementDetailPageModule' },

    { path: 'analysis', loadChildren: '../../project/pages/analysis/analysis.component.module#AnalysisPageModule' },
    { path: 'tax-audit-manage', loadChildren: '../../project/pages/reports/tax-audit-reporting/tax-audit-reporting.module#TaxAuditReportingComponentModule' },
    { path: 'result-analysis', loadChildren: '../../project/pages/result-analysis/result-analysis.module#ResultAnalysisPageModule' },
    { path: 'select-form', loadChildren: '../../project/pages/select-form/select-form.module#SelectFormComponentModule' },
    { path: 'create-form', loadChildren: '../../project/pages/create-form/create-form.module#CreateFormComponentModule' },
    { path: 'select-new-form', loadChildren: '../../project/pages/select-new-form/select-new-form.module#SelectNewFormComponentModule' },
    { path: 'create-new-form', loadChildren: '../../project/pages/create-new-form/create-new-form.module#CreateNewFormComponentModule' },

    { path: 'check-receipt-tax', loadChildren: '../../project/pages/check-receipt-tax/check-receipt-tax.module#CheckReceiptTaxComponentModule' },
    { path: 'check-receipt-license', loadChildren: '../../project/pages/check-receipt-license/check-receipt-license.module#CheckReceiptLicenseComponentModule' },
    { path: 'trader-selection', loadChildren: '../../project/tax-audit/trader-selection/create-trader/create-trader.module#CreateTraderComponentModule' },
    { path: 'analyst-basic-data-trader', loadChildren: '../../project/tax-audit/trader-selection/analyst-basic-data-trader/analyst-basic-data-trader.module#AnalystBasicDataTraderComponentModule' },
    { path: 'create-working-paper-trader', loadChildren: '../../project/tax-audit/trader-selection/create-working-paper-trader/create-working-paper-trader.module#CreateWorkingPaperTraderComponentModule' },
    { path: 'working-paper-1-trader', loadChildren: '../../project/tax-audit/trader-selection/working-paper-1-trader/working-paper-1-trader.module#WorkingPaper1TraderComponentModule' },
    { path: 'working-paper-1-full-trader', loadChildren: '../../project/tax-audit/trader-selection/working-paper-1-full-trader/working-paper-1-full-trader.module#WorkingPaper1FullTraderComponentModule' },
    { path: 'summary-amount-product-trader', loadChildren: '../../project/tax-audit/trader-selection/summary-amount-product-trader/summary-amount-product-trader.module#SummaryAmountProductTraderComponentModule' },
    { path: 'create-save-message-trader', loadChildren: '../../project/tax-audit/trader-selection/create-save-message-trader/create-save-message-trader.module#CreateSaveMessageTraderComponentModule' },

    { path: 'epa01', loadChildren: '../../project/export-audit/epa01/epa01.module#Epa01Module' },
    { path: 'epa02', loadChildren: '../../project/export-audit/epa02/epa02.module#Epa02Module' },
    
    { path: 'mgcontrol', loadChildren: '../../project/management-control/mgcontrol/mgcontrol.module#MgcontrolComponentModule' },
    { path: 'mgReportResult', loadChildren: '../../project/management-control/mgc02/mgReportResult.module#MgReportResultComponentModule' },

    { path: 'cop01', loadChildren: '../../project/check-operation/cop01/cop01.module#Cop01Module' },
    { path: 'cop02', loadChildren: '../../project/check-operation/cop02/cop02.module#Cop02Module' },
    { path: 'cop03', loadChildren: '../../project/check-operation/cop03/cop03.module#Cop03Module' },
    { path: 'cop04', loadChildren: '../../project/check-operation/cop04/cop04.module#Cop04Module' },
    { path: 'cop05', loadChildren: '../../project/check-operation/cop05/cop05.module#Cop05Module' },
    
    { path: 'int01', loadChildren: '../../project/internal-audit/int01/int01.module#Int01Module' },
    { path: 'int05', loadChildren: '../../project/internal-audit/int05/int05.module#Int05Module' },
    { path: 'int06', loadChildren: '../../project/internal-audit/int06/int06.module#Int06Module' },
    { path: 'int07', loadChildren: '../../project/internal-audit/int07/int07.module#Int07Module' },
    { path: 'int08', loadChildren: '../../project/internal-audit/int08/int08.module#Int08Module' },
    { path: 'int09', loadChildren: '../../project/internal-audit/int09/int09.module#Int09Module' },
    
    { path: 'ope04', loadChildren: '../../project/tax-audit/operate-tax-audit/ope04/ope04.module#Ope04Module' },
    { path: 'ope05', loadChildren: '../../project/tax-audit/operate-tax-audit/ope05/ope05.module#Ope05Module' },

    { path: 'add-external-data', loadChildren: '../../project/tax-audit/trader-selection/add-external-data/add-external-data.module#AddExternalDataModule' },

    { path: 'report', loadChildren: '../../project/management-control/reports/reports.module#ReportsModule' },
];

@NgModule({
    imports: [RouterModule.forRoot(routes, { useHash: true })],
    exports: [RouterModule]
})
export class AppRoutingModule { }