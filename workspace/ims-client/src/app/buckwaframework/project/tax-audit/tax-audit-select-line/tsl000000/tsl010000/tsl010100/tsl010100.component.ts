import { Component, OnInit } from '@angular/core';
import { Tsl010100Service } from './tsl010100.service';
import { BreadCrumb } from 'models/breadcrumb';
import { TextDateTH, formatter } from 'helpers/datepicker';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
declare var $: any;
@Component({
  selector: 'app-tsl010100',
  templateUrl: './tsl010100.component.html',
  styleUrls: ['./tsl010100.component.css'],
  providers: [Tsl010100Service]
})
export class Tsl010100Component implements OnInit {

  breadcrumb: BreadCrumb[] = [
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'คัดเลือกลาย', route: '#' },
  ];

  //==> params
  submitted: boolean = false;
  formControl: FormGroup;


  //==> params group
  symbol: any = [
    { label: 'มากกว่า', value: '>' },
    { label: 'น้อยกว่า', value: '<' },
    { label: 'เท่ากับ', value: '=' },
  ];
  constructor(
    private myService: Tsl010100Service,
    private formBuilder: FormBuilder,
  ) {
    this.newForm();
  }

  ngOnInit() {
    this.callDropdown();
    this.calenda();
  }

  newForm() {
    this.formControl = this.formBuilder.group({
      dateFrom: ["", Validators.required],
      dateTo: ["", Validators.required],
      monthNonPay: ["", Validators.required],
      symbol1: ["", Validators.required],
      symbol2: ["", Validators.required],
      percent1: ["", Validators.required],
      percent2: ["", Validators.required]
    });
  }

  save() {
    //==> Validate fields
    this.submitted = true;
    if (this.formControl.invalid) {
      return false;
    }
    //==> submit
    this.myService.save(this.formControl.value);
  }

  clear() {
    this.submitted = false;
    this.newForm();
    this.resetDropdown();
  }

  get f() {
    return this.formControl.controls;
  }
  calenda = () => {
    let date = new Date();
    $("#dateF").calendar({
      maxDate: new Date(date.getFullYear() + "-" + (date.getMonth())),
      endCalendar: $("#dateT"),
      type: "month",
      text: TextDateTH,
      formatter: formatter('month-year'),
      onChange: (date, text) => {
        this.formControl.controls.dateFrom.setValue(text);
      }
    });
    $("#dateT").calendar({
      maxDate: new Date(date.getFullYear() + "-" + (date.getMonth())),
      startCalendar: $("#dateF"),
      type: "month",
      text: TextDateTH,
      formatter: formatter('month-year'),
      onChange: (date, text) => {
        this.formControl.controls.dateTo.setValue(text);
      }
    });
  }

  callDropdown() {
    $(".ui.dropdown").dropdown();
  }
  resetDropdown() {
    $(".ui.dropdown").dropdown('restore defaults');
  }
}
