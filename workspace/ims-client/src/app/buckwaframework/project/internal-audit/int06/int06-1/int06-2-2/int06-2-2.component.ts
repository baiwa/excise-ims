import { Component, OnInit, ViewChild } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';

@Component({
  selector: 'app-int06-2-2',
  templateUrl: './int06-2-2.component.html',
  styleUrls: ['./int06-2-2.component.css']
})
export class Int0622Component implements OnInit {

  breadcrumb: BreadCrumb[] = [
    { label: "ตรวจสอบภายใน", route: "#" },
    { label: "ตรวจสอบเบิกจ่าย", route: "#" },
    { label: "ตรวจสอบค่าใช้จ่าย", route: "int06/1/2-1" },
    { label: "เพิ่มข้อมูลค่าใช้จ่าย", route: "#" },
  ];
  model: Model;

  constructor() {
    this.model = new Model();
  }

  ngOnInit() {
  }

  changeReceive() {
    this.model.sumReceive = this.model.serviceReceive + this.model.suppressReceive + this.model.budgetReceive;
    this.model.sumWithdrawe = this.model.serviceWithdrawe + this.model.suppressWithdrawe + this.model.budgetWithdrawe;

    this.model.serviceBalance = this.model.serviceReceive - this.model.serviceWithdrawe;
    this.model.sumBalance = this.model.serviceBalance + this.model.suppressBalance + this.model.budgetBalance;

    this.model.moneyBudget = this.model.serviceBalance + this.model.suppressBalance
    this.model.moneyOut = this.model.budgetBalance;
  }

  changeSuppress() {
    this.model.sumReceive = this.model.serviceReceive + this.model.suppressReceive + this.model.budgetReceive;
    this.model.sumWithdrawe = this.model.serviceWithdrawe + this.model.suppressWithdrawe + this.model.budgetWithdrawe;

    this.model.suppressBalance = this.model.suppressReceive - this.model.suppressWithdrawe;
    this.model.sumBalance = this.model.serviceBalance + this.model.suppressBalance + this.model.budgetBalance;

    this.model.moneyBudget = this.model.serviceBalance + this.model.suppressBalance
    this.model.moneyOut = this.model.budgetBalance;
  }

  changeBudget() {
    this.model.sumReceive = this.model.serviceReceive + this.model.suppressReceive + this.model.budgetReceive;
    this.model.sumWithdrawe = this.model.serviceWithdrawe + this.model.suppressWithdrawe + this.model.budgetWithdrawe;

    this.model.budgetBalance = this.model.budgetReceive - this.model.budgetWithdrawe;
    this.model.sumBalance = this.model.serviceBalance + this.model.suppressBalance + this.model.budgetBalance;

    this.model.moneyBudget = this.model.serviceBalance + this.model.suppressBalance
    this.model.moneyOut = this.model.budgetBalance;
  }

  onSubmit() {
    console.log(this.model);
  }

}

class Model {
  accountId: string = "";
  accountName: string = "";

  serviceReceive: number = 0;
  suppressReceive: number = 0;
  budgetReceive: number = 0;
  sumReceive: number = 0;

  serviceWithdrawe: number = 0;
  suppressWithdrawe: number = 0;
  budgetWithdrawe: number = 0;
  sumWithdrawe: number = 0;

  serviceBalance: number = 0;
  suppressBalance: number = 0;
  budgetBalance: number = 0;
  sumBalance: number = 0;

  moneyBudget: number = 0;
  moneyOut: number = 0;

  averageCost: number = 0;
  averageGive: string = "";
  averrageFrom: string = "";
  averrateComeCost: number = 0;
}
