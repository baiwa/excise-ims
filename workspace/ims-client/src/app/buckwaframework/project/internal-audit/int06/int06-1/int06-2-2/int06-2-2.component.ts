import { Component, OnInit } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';
import { Int0622Service } from 'projects/internal-audit/int06/int06-1/int06-2-2/int06-2-2.service';

@Component({
  selector: 'app-int06-2-2',
  templateUrl: './int06-2-2.component.html',
  styleUrls: ['./int06-2-2.component.css'],
  providers: [Int0622Service]
})
export class Int0622Component implements OnInit {

  breadcrumb: BreadCrumb[] = [
    { label: "ตรวจสอบภายใน", route: "#" },
    { label: "ตรวจสอบเบิกจ่าย", route: "#" },
    { label: "ตรวจสอบค่าใช้จ่าย", route: "int06/1/2-1" },
    { label: "เพิ่มข้อมูลค่าใช้จ่าย", route: "#" },
  ];
  model: FormSave;
  loading: boolean = false;
  constructor(
    private int0622Service: Int0622Service
  ) {
    this.model = new FormSave();
  }

  ngOnInit() {
  }

  changeReceive() {
    this.model.sumReceive = this.model.serviceReceive + this.model.suppressReceive + this.model.budgetReceive;
    this.model.sumWithdraw = this.model.serviceWithdraw + this.model.suppressWithdraw + this.model.budgetWithdraw;

    this.model.serviceBalance = this.model.serviceReceive - this.model.serviceWithdraw;
    this.model.sumBalance = this.model.serviceBalance + this.model.suppressBalance + this.model.budgetBalance;

    this.model.moneyBudget = this.model.serviceBalance + this.model.suppressBalance
    this.model.moneyOut = this.model.budgetBalance;
  }

  changeSuppress() {
    this.model.sumReceive = this.model.serviceReceive + this.model.suppressReceive + this.model.budgetReceive;
    this.model.sumWithdraw = this.model.serviceWithdraw + this.model.suppressWithdraw + this.model.budgetWithdraw;

    this.model.suppressBalance = this.model.suppressReceive - this.model.suppressWithdraw;
    this.model.sumBalance = this.model.serviceBalance + this.model.suppressBalance + this.model.budgetBalance;

    this.model.moneyBudget = this.model.serviceBalance + this.model.suppressBalance
    this.model.moneyOut = this.model.budgetBalance;
  }

  changeBudget() {
    this.model.sumReceive = this.model.serviceReceive + this.model.suppressReceive + this.model.budgetReceive;
    this.model.sumWithdraw = this.model.serviceWithdraw + this.model.suppressWithdraw + this.model.budgetWithdraw;

    this.model.budgetBalance = this.model.budgetReceive - this.model.budgetWithdraw;
    this.model.sumBalance = this.model.serviceBalance + this.model.suppressBalance + this.model.budgetBalance;

    this.model.moneyBudget = this.model.serviceBalance + this.model.suppressBalance
    this.model.moneyOut = this.model.budgetBalance;
  }

  onSubmit() {
    this.loading = true;
    this.int0622Service.save(this.model);
    this.model = new FormSave();
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
  averageFrom: string = "";
  averageComeCost: number = 0;

  note: string = "";
}