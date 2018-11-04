import { Component, OnInit } from '@angular/core';
import { Ope0431Service } from './ope04-3-1.service';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { AjaxService } from 'services/ajax.service';
import { TextDateTH, formatter } from 'helpers/datepicker';
import { BreadCrumb } from 'models/breadcrumb';
declare var $: any;
@Component({
  selector: 'app-ope04-3-1',
  templateUrl: './ope04-3-1.component.html',
  styleUrls: ['./ope04-3-1.component.css'],
  providers:[Ope0431Service]
})
export class Ope0431Component implements OnInit {
  breadcrumb: BreadCrumb[] = [
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'การตรวจสอบภาษี', route: '#' },
    { label: 'ผลการตรวจสอบภาษี', route: '#' },
    { label: 'ผลการตรวจสอบกระดาษทำการตรวจนับวัตถุดิบคงเหลือ', route: '#' },
  ]

  // ==> params
  formControl: FormGroup;
  exciseIdList: any;
  submitted: boolean = false;
  
  constructor(
    private ope0431Service: Ope0431Service,
    private formBuilder: FormBuilder,
    private ajax : AjaxService
  ) { }

  ngOnInit() {   
    this.findExciseId();
    this.callDropdown();
    this.newFormControl();
    this.calenda();
    this.ope0431Service.datatable();
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
    this.ope0431Service.claer(this.formControl.value);
  }

  search = () => {
    this.submitted = true;

    if (this.formControl.invalid) {
      return;
    }
    this.ope0431Service.search(this.formControl.value);
  }
  datatable = () => {
    this.ope0431Service.datatable();
  }

  findExciseId = () => {
    this.ope0431Service.findExciseId().then(res => {
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
