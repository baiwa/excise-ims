import { Component, OnInit } from '@angular/core';

import { TextDateTH, formatter } from '../../../../../../common/helper/datepicker';
declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-int05-1-1-3',
  templateUrl: './int05-1-1-3.component.html',
  styleUrls: ['./int05-1-1-3.component.css']
})
export class Int05113Component implements OnInit {

  formModal: FormModal = new FormModal();
  constructor() { }

  ngOnInit() {
    this.calenda();

  }

  calenda = () => {
    $("#dateF").calendar({
      maxDate: new Date(),
      endCalendar: $("#dateTo"),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#dateT").calendar({
      maxDate: new Date(),
      startCalendar: $("#dateForm"),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#dateOfPayForm").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    }, 'blur');
    $("#dateWithdrawStampForm").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#dateDeliverStampForm").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#fivePartDateForm").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#stampCheckDateForm").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
  }

}
class FormModal {
  workSheetDetailId: string = null;
  exciseDepartment: string = null;
  exciseRegion: string = null;
  exciseDistrict: string = null;
  dateOfPay: string = null;
  status: string = null;
  departmentName: string = null;
  bookNumberWithdrawStamp: string = null;
  dateWithdrawStamp: string = null;
  bookNumberDeliverStamp: string = null;
  dateDeliverStamp: string = null;
  fivePartNumber: string = null;
  fivePartDate: string = null;
  stampCheckDate: string = null;
  stampChecker: string = null;
  stampType: string = null;
  stampBrand: string = null;
  numberOfBook: string = null;
  numberOfStamp: string = null;
  valueOfStampPrinted: string = null;
  sumOfValue: string = null;
  serialNumber: string = null;
  taxStamp: string = null;
  stampCodeStart: string = null;
  stampCodeEnd: string = null;
  note: string = null;
  createdDate: string = null;
  fileName: string = null;
}
