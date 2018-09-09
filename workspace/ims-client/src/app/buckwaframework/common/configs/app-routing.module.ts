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
    component: HomePage,
    canActivate: [AuthGuard]
  },
  {
    path: "message",
    loadChildren: "../../project/pages/message/message.module#MessageModule",
    canActivate: [AuthGuard]
  },
  {
    path: "add-message",
    loadChildren:
      "../../project/pages/message/message-detail.module#MessageDetailModule",
    canActivate: [AuthGuard]
  },
  {
    path: "edit-message",
    loadChildren:
      "../../project/pages/message/message-detail.module#MessageDetailModule",
    canActivate: [AuthGuard]
  },

  {
    path: "parameterInfo",
    loadChildren:
      "../../project/pages/parameterInfo/parameterInfo.module#ParameterInfoPageModule",
    canActivate: [AuthGuard]
  },
  {
    path: "parameterGroup",
    loadChildren:
      "../../project/pages/parameterGroup/parameterGroup.module#ParameterGroupPageModule",
    canActivate: [AuthGuard]
  },
  {
    path: "parameterInfoDetail",
    loadChildren:
      "../../project/pages/parameterInfo/parameterInfoDetail.module#ParameterInfoDetailPageModule",
    canActivate: [AuthGuard]
  },

  {
    path: "userManagement",
    loadChildren:
      "../../project/pages/userManagement/userManagement.module#UserManagementPageModule",
    canActivate: [AuthGuard]
  },
  {
    path: "add-userManagement",
    loadChildren:
      "../../project/pages/userManagement/userManagement-detail.module#UserManagementDetailPageModule",
    canActivate: [AuthGuard]
  },
  {
    path: "edit-userManagement",
    loadChildren:
      "../../project/pages/userManagement/userManagement-detail.module#UserManagementDetailPageModule",
    canActivate: [AuthGuard]
  },

  {
    path: "analysis",
    loadChildren:
      "../../project/pages/analysis/analysis.component.module#AnalysisPageModule",
    canActivate: [AuthGuard]
  },
  {
    path: "tax-audit-manage",
    loadChildren:
      "../../project/pages/reports/tax-audit-reporting/tax-audit-reporting.module#TaxAuditReportingComponentModule",
    canActivate: [AuthGuard]
  },
  {
    path: "result-analysis",
    loadChildren:
      "../../project/pages/result-analysis/result-analysis.module#ResultAnalysisPageModule",
    canActivate: [AuthGuard]
  },
  {
    path: "select-form",
    loadChildren:
      "../../project/pages/select-form/select-form.module#SelectFormComponentModule",
    canActivate: [AuthGuard]
  },
  {
    path: "create-form",
    loadChildren:
      "../../project/pages/create-form/create-form.module#CreateFormComponentModule",
    canActivate: [AuthGuard]
  },
  {
    path: "select-new-form",
    loadChildren:
      "../../project/pages/select-new-form/select-new-form.module#SelectNewFormComponentModule",
    canActivate: [AuthGuard]
  },
  {
    path: "create-new-form",
    loadChildren:
      "../../project/pages/create-new-form/create-new-form.module#CreateNewFormComponentModule",
    canActivate: [AuthGuard]
  },

  {
    path: "check-receipt-tax",
    loadChildren:
      "../../project/pages/check-receipt-tax/check-receipt-tax.module#CheckReceiptTaxComponentModule",
    canActivate: [AuthGuard]
  },
  {
    path: "check-receipt-license",
    loadChildren:
      "../../project/pages/check-receipt-license/check-receipt-license.module#CheckReceiptLicenseComponentModule",
    canActivate: [AuthGuard]
  },
  {
    path: "trader-selection",
    loadChildren:
      "../../project/tax-audit/trader-selection/create-trader/create-trader.module#CreateTraderComponentModule",
    canActivate: [AuthGuard]
  },
  {
    path: "analyst-basic-data-trader",
    loadChildren:
      "../../project/tax-audit/trader-selection/analyst-basic-data-trader/analyst-basic-data-trader.module#AnalystBasicDataTraderComponentModule",
    canActivate: [AuthGuard]
  },
  {
    path: "create-working-paper-trader",
    loadChildren:
      "../../project/tax-audit/trader-selection/create-working-paper-trader/create-working-paper-trader.module#CreateWorkingPaperTraderComponentModule",
    canActivate: [AuthGuard]
  },
  {
    path: "working-paper-1-trader",
    loadChildren:
      "../../project/tax-audit/trader-selection/working-paper-1-trader/working-paper-1-trader.module#WorkingPaper1TraderComponentModule",
    canActivate: [AuthGuard]
  },
  {
    path: "working-paper-1-full-trader",
    loadChildren:
      "../../project/tax-audit/trader-selection/working-paper-1-full-trader/working-paper-1-full-trader.module#WorkingPaper1FullTraderComponentModule",
    canActivate: [AuthGuard]
  },
  {
    path: "summary-amount-product-trader",
    loadChildren:
      "../../project/tax-audit/trader-selection/summary-amount-product-trader/summary-amount-product-trader.module#SummaryAmountProductTraderComponentModule",
    canActivate: [AuthGuard]
  },
  {
    path: "create-save-message-trader",
    loadChildren:
      "../../project/tax-audit/trader-selection/create-save-message-trader/create-save-message-trader.module#CreateSaveMessageTraderComponentModule",
    canActivate: [AuthGuard]
  },

  {
    path: "epa01",
    loadChildren: "../../project/export-audit/epa01/epa01.module#Epa01Module",
    canActivate: [AuthGuard]
  },
  {
    path: "epa02",
    loadChildren: "../../project/export-audit/epa02/epa02.module#Epa02Module",
    canActivate: [AuthGuard]
  },

  {
    path: "mgcontrol",
    loadChildren:
      "../../project/management-control/mgcontrol/mgcontrol.module#MgcontrolComponentModule",
    canActivate: [AuthGuard]
  },
  {
    path: "mgReportResult",
    loadChildren:
      "../../project/management-control/mgc02/mgReportResult.module#MgReportResultComponentModule",
    canActivate: [AuthGuard]
  },

  {
    path: "cop01",
    loadChildren:
      "../../project/check-operation/cop01/cop01.module#Cop01Module",
    canActivate: [AuthGuard]
  },
  {
    path: "cop02",
    loadChildren:
      "../../project/check-operation/cop02/cop02.module#Cop02Module",
    canActivate: [AuthGuard]
  },
  {
    path: "cop03",
    loadChildren:
      "../../project/check-operation/cop03/cop03.module#Cop03Module",
    canActivate: [AuthGuard]
  },
  {
    path: "cop04",
    loadChildren:
      "../../project/check-operation/cop04/cop04.module#Cop04Module",
    canActivate: [AuthGuard]
  },
  {
    path: "cop05",
    loadChildren:
      "../../project/check-operation/cop05/cop05.module#Cop05Module",
    canActivate: [AuthGuard]
  },

  {
    path: "int01",
    loadChildren: "../../project/internal-audit/int01/int01.module#Int01Module",
    canActivate: [AuthGuard]
  },
  {
    path: "int02",
    loadChildren: "../../project/internal-audit/int02/int02.module#Int02Module",
    canActivate: [AuthGuard]
  },
  {
    path: "int05",
    loadChildren: "../../project/internal-audit/int05/int05.module#Int05Module",
    canActivate: [AuthGuard]
  },
  {
    path: "int06",
    loadChildren: "../../project/internal-audit/int06/int06.module#Int06Module",
    canActivate: [AuthGuard]
  },
  {
    path: "int07",
    loadChildren: "../../project/internal-audit/int07/int07.module#Int07Module",
    canActivate: [AuthGuard]
  },
  {
    path: "int08",
    loadChildren: "../../project/internal-audit/int08/int08.module#Int08Module",
    canActivate: [AuthGuard]
  },
  {
    path: "int09",
    loadChildren: "../../project/internal-audit/int09/int09.module#Int09Module",
    canActivate: [AuthGuard]
  }, {
    path: "int10",
    loadChildren: "../../project/internal-audit/int10/int10.module#Int10Module",
    canActivate: [AuthGuard]
  },

  {
    path: "ope04",
    loadChildren:
      "../../project/tax-audit/operate-tax-audit/ope04/ope04.module#Ope04Module",
    canActivate: [AuthGuard]
  },
  {
    path: "ope05",
    loadChildren:
      "../../project/tax-audit/operate-tax-audit/ope05/ope05.module#Ope05Module",
    canActivate: [AuthGuard]
  },

  {
    path: "add-external-data",
    loadChildren:
      "../../project/tax-audit/trader-selection/add-external-data/add-external-data.module#AddExternalDataModule",
    canActivate: [AuthGuard]
  },

  {
    path: "report",
    loadChildren:
      "../../project/management-control/reports/reports.module#ReportsModule",
    canActivate: [AuthGuard]
  },

  {
    path: "int11",
    loadChildren: "../../project/internal-audit/int11/int11.module#Int11Module",
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
