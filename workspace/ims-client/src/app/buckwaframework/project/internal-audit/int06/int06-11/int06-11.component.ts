import { Component, OnInit } from '@angular/core';
declare var $: any;
@Component({
  selector: 'app-int06-11',
  templateUrl: './int06-11.component.html',
  styleUrls: ['./int06-11.component.css']
})
export class Int0611Component implements OnInit {
  selectedProduct: string;
  productList: any[];

  constructor() {
    this.productList = [
      "แบบขอเบิกเงินค่าเช่าบ้าน (แบบ 6006) ",
      " ใบเบิกเงินสวัสดิการเกี่ยวกับการรักษาพยาบาล (แบบ 7131) ",
      " ใบเบิกเงินสวัสดิการเกี่ยวกับการศึกษาบุตร (แบบ 7223) ",
      " ใบเบิกค่าใช้จ่ายในการเดินทางไปราชการ (แบบ 8708) "

    ];
  }


  ngOnInit() {


  }

  onSelectactionStatus = event => {
    this.selectedProduct = this.productList[event.target.value];
  };



}
