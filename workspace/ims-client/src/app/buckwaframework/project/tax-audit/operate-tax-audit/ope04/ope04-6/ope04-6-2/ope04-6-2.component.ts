import { Component, OnInit } from '@angular/core';
import { Ope0462Service } from './ope04-6-2.service';
import { BreadCrumb } from 'models/breadcrumb';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { TextDateTH, formatter } from 'helpers/datepicker';
declare var $: any;
@Component({
  selector: 'app-ope04-6-2',
  templateUrl: './ope04-6-2.component.html',
  styleUrls: ['./ope04-6-2.component.css'],
  providers: [Ope0462Service]
})
export class Ope0462Component implements OnInit {

  breadcrumb: BreadCrumb[] = [
    { label: 'หน้าแรก', route: '#' },
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'การตรวจสอบภาษี', route: '#' },
    { label: 'ผลการตรวจสอบรายการวัตถุดิบที่ขอลดหย่อนภาษี', route: '#' },
  ]

  // ==> params
  formControl: FormGroup;
  exciseIdList: any;
  submitted: boolean = false;
  
  constructor(
    private ope0461Service: Ope0462Service,
    private formBuilder: FormBuilder,
  ) { }

  ngOnInit() {
    this.findExciseId();
    this.callDropdown();
    this.newFormControl();
    this.calenda();
    this.ope0461Service.datatable();
  }

  newFormControl = () => {
    this.formControl = this.formBuilder.group({
      exciseId: ["", Validators.required],
      startDate: [""],
      endDate: [""],
      searchFlag: [""],
    });
  }
  get f() {
    return this.formControl.controls;
  }
  claer = () => {
    this.submitted = false;
    this.newFormControl();
    this.ope0461Service.claer(this.formControl.value);
  }

  search = () => {
    this.submitted = true;

    if (this.formControl.invalid) {
      return;
    }
    this.ope0461Service.search(this.formControl.value);
  }
  datatable = () => {
    this.ope0461Service.datatable();
  }

  findExciseId = () => {
    this.ope0461Service.findExciseId().then(res => {
      this.exciseIdList = res;
    })
  }
  callDropdown() {
    $(".ui.dropdown").dropdown();
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
        this.formControl.controls.startDate.setValue(text);
      }
    });
    $("#dateT").calendar({
      maxDate: new Date(date.getFullYear() + "-" + (date.getMonth())),
      startCalendar: $("#dateF"),
      type: "month",
      text: TextDateTH,
      formatter: formatter('month-year'),
      onChange: (date, text) => {
        this.formControl.controls.endDate.setValue(text);
      }
    });
  }
}
