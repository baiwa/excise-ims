import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from "@angular/router";
declare var $: any;
@Component({
  selector: 'app-int06-11',
  templateUrl: './int06-11.component.html',
  styleUrls: ['./int06-11.component.css']
})
export class Int0611Component implements OnInit {
  selectedProduct: string;
  product: string;
  productList: any[];
  budgetYear: any;

  constructor(private router: Router) {
    this.productList = [
      "",
      "แบบขอเบิกเงินค่าเช่าบ้าน (แบบ 6006)",
      " ใบเบิกเงินสวัสดิการเกี่ยวกับการรักษาพยาบาล (แบบ 7131)",
      " ใบเบิกเงินสวัสดิการเกี่ยวกับการศึกษาบุตร (แบบ 7223)",
      " ใบเบิกค่าใช้จ่ายในการเดินทางไปราชการ (แบบ 8708)"

    ];
  }


  ngOnInit() {


  }

  changepage() {
   console.log(this.product);
   let page = "";
   if(this.product == '1'){
    page = 'int06/11/1';
  }else if(this.product == '2'){
    page = 'int06/11/2';
  }else if(this.product == '3'){
    page = 'int06/11/3';
  }else if(this.product == '4'){
    page = 'int06/11/4';
  }

  this.router.navigate([page], {    
  });
}
  onSelectactionStatus = event => {
    this.selectedProduct = this.productList[event.target.value];
  };



}

