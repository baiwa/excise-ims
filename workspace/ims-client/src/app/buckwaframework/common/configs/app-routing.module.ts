import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { HomePage } from "../../project/pages/home/home";
import { LoginPage } from "../../project/pages/login/login";
import { AuthGuard } from "../services";

const routes: Routes = [
  { path: "", redirectTo: "/home", pathMatch: "full" },
  {
    path: "login",
    component: LoginPage
  },
  {
    path: "home",
    loadChildren: "projects/pages/home/home.module#HomeModule",
    canActivate: [AuthGuard]
  },
  {
    path: "message",
    loadChildren: "projects/pages/message/message.module#MessageModule",
    canActivate: [AuthGuard]
  },
  {
    path: "add-message",
    loadChildren: "projects/pages/message/message-detail.module#MessageDetailModule",
    canActivate: [AuthGuard]
  },
  {
    path: "edit-message",
    loadChildren: "projects/pages/message/message-detail.module#MessageDetailModule",
    canActivate: [AuthGuard]
  },

  {
    path: "parameterInfo",
    loadChildren: "projects/pages/parameterInfo/parameterInfo.module#ParameterInfoPageModule",
    canActivate: [AuthGuard]
  },
  {
    path: "parameterGroup",
    loadChildren: "projects/pages/parameterGroup/parameterGroup.module#ParameterGroupPageModule",
    canActivate: [AuthGuard]
  },
  {
    path: "parameterInfoDetail",
    loadChildren: "projects/pages/parameterInfo/parameterInfoDetail.module#ParameterInfoDetailPageModule",
    canActivate: [AuthGuard]
  },

  {
    path: "userManagement",
    loadChildren: "projects/pages/userManagement/userManagement.module#UserManagementPageModule",
    canActivate: [AuthGuard]
  },
  {
    path: "add-userManagement",
    loadChildren: "projects/pages/userManagement/userManagement-detail.module#UserManagementDetailPageModule",
    canActivate: [AuthGuard]
  },
  {
    path: "edit-userManagement",
    loadChildren: "projects/pages/userManagement/userManagement-detail.module#UserManagementDetailPageModule",
    canActivate: [AuthGuard]
  },

  {
    path: "analysis",
    loadChildren: "projects/pages/analysis/analysis.component.module#AnalysisPageModule",
    canActivate: [AuthGuard]
  },
  {
    path: "tax-audit-manage",
    loadChildren: "projects/pages/reports/tax-audit-reporting/tax-audit-reporting.module#TaxAuditReportingComponentModule",
    canActivate: [AuthGuard]
  },
  {
    path: "result-analysis",
    loadChildren: "projects/pages/result-analysis/result-analysis.module#ResultAnalysisPageModule",
    canActivate: [AuthGuard]
  },
  {
    path: "select-form",
    loadChildren: "projects/pages/select-form/select-form.module#SelectFormComponentModule",
    canActivate: [AuthGuard]
  },
  {
    path: "create-form",
    loadChildren: "projects/pages/create-form/create-form.module#CreateFormComponentModule",
    canActivate: [AuthGuard]
  },
  {
    path: "select-new-form",
    loadChildren: "projects/pages/select-new-form/select-new-form.module#SelectNewFormComponentModule",
    canActivate: [AuthGuard]
  },
  {
    path: "create-new-form",
    loadChildren: "projects/pages/create-new-form/create-new-form.module#CreateNewFormComponentModule",
    canActivate: [AuthGuard]
  },

  {
    path: "check-receipt-tax",
    loadChildren: "projects/pages/check-receipt-tax/check-receipt-tax.module#CheckReceiptTaxComponentModule",
    canActivate: [AuthGuard]
  },
  {
    path: "check-receipt-license",
    loadChildren: "projects/pages/check-receipt-license/check-receipt-license.module#CheckReceiptLicenseComponentModule",
    canActivate: [AuthGuard]
  },
  {
    path: "trader-selection",
    loadChildren: "projects/tax-audit/trader-selection/create-trader/create-trader.module#CreateTraderComponentModule",
    canActivate: [AuthGuard]
  },
  {
    path: "analyst-basic-data-trader",
    loadChildren: "projects/tax-audit/trader-selection/analyst-basic-data-trader/analyst-basic-data-trader.module#AnalystBasicDataTraderComponentModule",
    canActivate: [AuthGuard]
  },
  {
    path: "create-working-paper-trader",
    loadChildren: "projects/tax-audit/trader-selection/create-working-paper-trader/create-working-paper-trader.module#CreateWorkingPaperTraderComponentModule",
    canActivate: [AuthGuard]
  },
  {
    path: "working-paper-1-trader",
    loadChildren: "projects/tax-audit/trader-selection/working-paper-1-trader/working-paper-1-trader.module#WorkingPaper1TraderComponentModule",
    canActivate: [AuthGuard]
  },
  {
    path: "working-paper-1-full-trader",
    loadChildren: "projects/tax-audit/trader-selection/working-paper-1-full-trader/working-paper-1-full-trader.module#WorkingPaper1FullTraderComponentModule",
    canActivate: [AuthGuard]
  },
  {
    path: "summary-amount-product-trader",
    loadChildren: "projects/tax-audit/trader-selection/summary-amount-product-trader/summary-amount-product-trader.module#SummaryAmountProductTraderComponentModule",
    canActivate: [AuthGuard]
  },
  {
    path: "create-save-message-trader",
    loadChildren: "projects/tax-audit/trader-selection/create-save-message-trader/create-save-message-trader.module#CreateSaveMessageTraderComponentModule",
    canActivate: [AuthGuard]
  },

  {
    path: "epa01",
    loadChildren: "projects/export-audit/epa01/epa01.module#Epa01Module",
    canActivate: [AuthGuard]
  },
  {
    path: "epa02",
    loadChildren: "projects/export-audit/epa02/epa02.module#Epa02Module",
    canActivate: [AuthGuard]
  },
  {
    path: "epa03",
    loadChildren: "projects/export-audit/epa03/epa03.module#Epa03Module",
    canActivate: [AuthGuard]
  },
  {
    path: "mgcontrol",
    loadChildren: "projects/management-control/mgcontrol/mgcontrol.module#MgcontrolComponentModule",
    canActivate: [AuthGuard]
  },
  {
    path: "mgReportResult",
    loadChildren: "projects/management-control/mgc02/mgReportResult.module#MgReportResultComponentModule",
    canActivate: [AuthGuard]
  },

  {
    path: "cop01",
    loadChildren: "projects/check-operation/cop01/cop01.module#Cop01Module",
    canActivate: [AuthGuard]
  },
  {
    path: "cop02",
    loadChildren: "projects/check-operation/cop02/cop02.module#Cop02Module",
    canActivate: [AuthGuard]
  },
  {
    path: "cop03",
    loadChildren: "projects/check-operation/cop03/cop03.module#Cop03Module",
    canActivate: [AuthGuard]
  },
  {
    path: "cop04",
    loadChildren: "projects/check-operation/cop04/cop04.module#Cop04Module",
    canActivate: [AuthGuard]
  },
  {
    path: "cop05",
    loadChildren: "projects/check-operation/cop05/cop05.module#Cop05Module",
    canActivate: [AuthGuard]
  },
  {
    path: "cop06",
    loadChildren: "projects/check-operation/cop06/cop06.module#Cop06Module",
    canActivate: [AuthGuard]
  },
  {
    path: "cop07",
    loadChildren: "projects/check-operation/cop07/cop07.module#Cop07Module",
    canActivate: [AuthGuard]
  },
  {
    path: "cop08",
    loadChildren: "projects/check-operation/cop08/cop08.module#Cop08Module",
    canActivate: [AuthGuard]
  },
  {
    path: "cop09",
    loadChildren: "projects/check-operation/cop09/cop09.module#Cop09Module",
    canActivate: [AuthGuard]
  },
  {
    path: "cop10",
    loadChildren: "projects/check-operation/cop10/cop10.module#Cop10Module",
    canActivate: [AuthGuard]
  },

  {
    path: "int01",
    loadChildren: "projects/internal-audit/int01/int01.module#Int01Module",
    canActivate: [AuthGuard]
  },
  {
    path: "int02",
    loadChildren: "projects/internal-audit/int02/int02.module#Int02Module",
    canActivate: [AuthGuard]
  },
  {
    path: "int05",
    loadChildren: "projects/internal-audit/int05/int05.module#Int05Module",
    canActivate: [AuthGuard]
  },
  {
    path: "int06",
    loadChildren: "projects/internal-audit/int06/int06.module#Int06Module",
    canActivate: [AuthGuard]
  },
  {
    path: "int07",
    loadChildren: "projects/internal-audit/int07/int07.module#Int07Module",
    canActivate: [AuthGuard]
  },
  {
    path: "int08",
    loadChildren: "projects/internal-audit/int08/int08.module#Int08Module",
    canActivate: [AuthGuard]
  },
  {
    path: "int09",
    loadChildren: "projects/internal-audit/int09/int09.module#Int09Module",
    canActivate: [AuthGuard]
  }, {
    path: "int10",
    loadChildren: "projects/internal-audit/int10/int10.module#Int10Module",
    canActivate: [AuthGuard]
  },
  {
    path: "ope04",
    loadChildren: "projects/tax-audit/operate-tax-audit/ope04/ope04.module#Ope04Module",
    canActivate: [AuthGuard]
  },
  {
    path: "ope05",
    loadChildren: "projects/tax-audit/operate-tax-audit/ope05/ope05.module#Ope05Module",
    canActivate: [AuthGuard]
  },
  {
    path: "add-external-data",
    loadChildren: "projects/tax-audit/trader-selection/add-external-data/add-external-data.module#AddExternalDataModule",
    canActivate: [AuthGuard]
  },
  {
    path: "tax-audit-select-line",
    loadChildren: "projects/tax-audit/tax-audit-select-line/tax-audit-select-line.module#TaxAuditSelectLine",
    canActivate: [AuthGuard]
  },

  {
    path: "report",
    loadChildren: "projects/management-control/reports/reports.module#ReportsModule",
    canActivate: [AuthGuard]
  },

  {
    path: "int11",
    loadChildren: "projects/internal-audit/int11/int11.module#Int11Module",
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
