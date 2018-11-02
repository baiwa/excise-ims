import { Component, OnInit } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';
import { Ope0411Service } from './ope04-1-1.service';
import { AjaxService } from 'services/ajax.service';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { TextDateTH, formatter } from 'helpers/datepicker';
declare var $: any;
@Component({
  selector: 'app-ope04-1-1',
  templateUrl: './ope04-1-1.component.html',
  styleUrls: ['./ope04-1-1.component.css'],
  providers:[Ope0411Service]
})
export class Ope0411Component implements OnInit {

  breadcrumb: BreadCrumb[] = [
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'การตรวจสอบภาษี', route: '#' },
    { label: 'ผลการตรวจสอบภาษี', route: '#' },
    { label: 'ผลการตรวจสอบกระดาษทำการรับวัตถุดิบ', route: '#' },
  ]

  // ==> params
  formControl: FormGroup;
  exciseIdList: any;
  submitted: boolean = false;
  
  constructor(
    private ope0411Service: Ope0411Service,
    private formBuilder: FormBuilder,
    private ajax : AjaxService
  ) { }

  ngOnInit() {   
    this.findExciseId();
    this.callDropdown();
    this.newFormControl();
    this.calenda();
    this.ope0411Service.datatable();
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
    this.ope0411Service.claer(this.formControl.value);
  }

  search = () => {
    this.submitted = true;

    if (this.formControl.invalid) {
      return;
    }
    this.ope0411Service.search(this.formControl.value);
  }
  datatable = () => {
    this.ope0411Service.datatable();
  }

  findExciseId = () => {
    this.ope0411Service.findExciseId().then(res => {
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
