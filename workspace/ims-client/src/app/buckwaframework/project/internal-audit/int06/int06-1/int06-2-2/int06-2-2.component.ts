import { Component, OnInit } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';
import { Int0622Service } from 'projects/internal-audit/int06/int06-1/int06-2-2/int06-2-2.service';
import { IaService } from 'services/ia.service';
import { MessageBarService } from 'services/message-bar.service';
import { AjaxService } from 'services/ajax.service';

@Component({
  selector: 'app-int06-2-2',
  templateUrl: './int06-2-2.component.html',
  styleUrls: ['./int06-2-2.component.css'],
  providers: [Int0622Service]
})
export class Int0622Component implements OnInit {

  breadcrumb: BreadCrumb[];
  model: FormSave;
  loading: boolean = false;
  constructor(
    private int0622Service: Int0622Service,
    private iaService: IaService,
    private message: MessageBarService,
    private ajax: AjaxService
  ) {
    this.model = new FormSave();
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบเบิกจ่าย", route: "#" },
      { label: "บันทึกข้อมูลค่าใช้จ่าย", route: "int06/1/2-1" },
      { label: "เพิ่มข้อมูลค่าใช้จ่าย", route: "#" },
    ];
  }

  ngOnInit() {

    if (this.iaService.getData() != null) {
      console.log(this.iaService.getData());
      this.model = this.iaService.getData();
      this.breadcrumb = [
        { label: "ตรวจสอบภายใน", route: "#" },
        { label: "ตรวจสอบเบิกจ่าย", route: "#" },
        { label: "ตรวจสอบค่าใช้จ่าย", route: "int06/1/2-1" },
        { label: "แก้ไขข้อมูลค่าใช้จ่าย", route: "#" },
      ];
    }

  }

  checkNullModel() {
    if (this.model.serviceReceive == null) this.model.serviceReceive = 0;
    if (this.model.suppressReceive == null) this.model.suppressReceive = 0;
    if (this.model.budgetReceive == null) this.model.budgetReceive = 0;
    if (this.model.serviceWithdraw == null) this.model.serviceWithdraw = 0;
    if (this.model.suppressWithdraw == null) this.model.suppressWithdraw = 0;
    if (this.model.budgetWithdraw == null) this.model.budgetWithdraw = 0;
    if (this.model.averageCost == null) this.model.averageCost = 0;
    if (this.model.averageFrom == null) this.model.averageFrom = 0;
  }

  changeReceive() {
    this.checkNullModel();

    this.model.sumReceive = this.model.serviceReceive + this.model.suppressReceive + this.model.budgetReceive;
    this.model.sumWithdraw = this.model.serviceWithdraw + this.model.suppressWithdraw + this.model.budgetWithdraw;

    this.model.serviceBalance = this.model.serviceReceive - this.model.serviceWithdraw;
    this.model.sumBalance = this.model.serviceBalance + this.model.suppressBalance + this.model.budgetBalance;

    this.model.moneyBudget = this.model.serviceBalance + this.model.suppressBalance
    this.model.moneyOut = this.model.budgetBalance;
  }

  changeSuppress() {
    this.checkNullModel();

    this.model.sumReceive = this.model.serviceReceive + this.model.suppressReceive + this.model.budgetReceive;
    this.model.sumWithdraw = this.model.serviceWithdraw + this.model.suppressWithdraw + this.model.budgetWithdraw;

    this.model.suppressBalance = this.model.suppressReceive - this.model.suppressWithdraw;
    this.model.sumBalance = this.model.serviceBalance + this.model.suppressBalance + this.model.budgetBalance;

    this.model.moneyBudget = this.model.serviceBalance + this.model.suppressBalance
    this.model.moneyOut = this.model.budgetBalance;
  }

  changeBudget() {
    this.checkNullModel();
    this.model.sumReceive = this.model.serviceReceive + this.model.suppressReceive + this.model.budgetReceive;
    this.model.sumWithdraw = this.model.serviceWithdraw + this.model.suppressWithdraw + this.model.budgetWithdraw;

    this.model.budgetBalance = this.model.budgetReceive - this.model.budgetWithdraw;
    this.model.sumBalance = this.model.serviceBalance + this.model.suppressBalance + this.model.budgetBalance;

    this.model.moneyBudget = this.model.serviceBalance + this.model.suppressBalance
    this.model.moneyOut = this.model.budgetBalance;
  }

  changeAverageCost = (e) => {
    if (e.target.value == "") {
      this.model.averageCost = 0;
    }
  }
  changeAverageFrom = (e) => {
    if (e.target.value == "") {
      this.model.averageFrom = 0;
    }
  }

  blurAccountId=(e)=>{
    this.loading = true;
    this.int0622Service.blurAccountId(e.target.value).then(then=>{
      this.loading = false;
    });
  }

  onSubmit() {
    this.checkNullModel();

    //edit
    if (this.model.editStatus == 'edit') {
      this.int0622Service.edit(this.model);
    } else {
      // this.int0622Service.save(this.model);

      //save
      this.message.comfirm((res) => {
        if (res) {
          let url = 'ia/int06122/save';
          this.ajax.post(url, JSON.stringify(this.model), res => {
            this.message.successModal(res.json());
          }, error => {
            this.message.error(error.json());
          }).then(data => {
            this.model = new FormSave();
          })
        }
      }, 'บันทึกรายการ');
    }
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
  editStatus: string = "FLASE";
}