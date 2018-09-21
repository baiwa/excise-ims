import { Injectable } from "@angular/core";
import { AjaxService } from "services/ajax.service";
import { MessageBarService } from "services/message-bar.service";
import { Router } from "@angular/router";
import { async } from "q";

@Injectable()
export class Int0622Service {
  model: FormSave;

  constructor(
    private ajax: AjaxService,
    private message: MessageBarService,
    private router: Router
  ) {
    this.model = new FormSave();
  }

  save(model: FormSave) {
    // this.message.comfirm((res) => {
    //   if (res) {
    //     let url = 'ia/int06122/save';
    //     this.ajax.post(url, JSON.stringify(model), res => {
    //       this.message.successModal(res.json());
    //     }, error => {
    //       this.message.error(error.json());
    //     }).then()
    //   }
    // }, 'บันทึกรายการ');
  }
  edit(model: FormSave) {
    this.message.comfirm((res) => {
      if (res) {
        let url = 'ia/int06122/edit';
        this.ajax.post(url, model, res => {
          this.message.successModal(res.json());
          this.router.navigate(["/int06/1/2-1"], {
            queryParams: { searchFlag: "TRUE" }
          });
        }, error => {
          this.message.error(error.json());
        })
      }
    }, 'บันทึกการแก้ไขรายการ');
  }
}

class FormSave {
  accountId: string = "";
  accountName: string = "";

  serviceReceive: number = 0;
  suppressReceive: number = 0;
  budgetReceive: number = 0;
  sumReceive: number = 0;

  serviceWithdraw: number = 0;
  suppressWithdraw: number = 0;
  budgetWithdraw: number = 0;
  sumWithdraw: number = 0;

  serviceBalance: number = 0;
  suppressBalance: number = 0;
  budgetBalance: number = 0;
  sumBalance: number = 0;

  moneyBudget: number = 0;
  moneyOut: number = 0;

  averageCost: number = 0;
  averageGive: string = "";
  averageFrom: number = 0;
  averageComeCost: string = "";

  note: string = "";
  editStatus: string = "";
}
