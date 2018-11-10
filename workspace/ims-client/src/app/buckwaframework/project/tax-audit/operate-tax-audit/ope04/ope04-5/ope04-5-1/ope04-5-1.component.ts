import { Component, OnInit } from '@angular/core';
import { Ope451Service } from './ope04-5-1.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { TextDateTH, formatter } from 'helpers/datepicker';
import { BreadCrumb } from 'models/breadcrumb';
declare var $: any;
@Component({
  selector: 'app-ope04-5-1',
  templateUrl: './ope04-5-1.component.html',
  styleUrls: ['./ope04-5-1.component.css'],
  providers : [Ope451Service]  
})
export class Ope0451Component implements OnInit {
  
  breadcrumb: BreadCrumb[] = [
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'การตรวจสอบภาษี', route: '#' },
    { label: 'ผลการตรวจสอบภาษี', route: '#' },
    { label: 'ผลการตรวจสอบกระดาษทำการจ่ายสินค้าสำเร็จรูป', route: '#' },
  ]

  // ==> params
  formControl: FormGroup;
  exciseIdList: any;
  submitted: boolean = false;
  
  constructor(
    private myService : Ope451Service,
    private formBuilder: FormBuilder,    
  ) { }

  ngOnInit() {   
    this.findExciseId();
    this.callDropdown();
    this.newFormControl();
    this.calenda();
    this.myService.datatable();
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
    this.myService.claer(this.formControl.value);
  }

  search = () => {
    this.submitted = true;

    if (this.formControl.invalid) {
      return;
    }
    this.myService.search(this.formControl.value);
  }
  datatable = () => {
    this.myService.datatable();
  }

  findExciseId = () => {
    this.myService.findExciseId().then(res => {
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

