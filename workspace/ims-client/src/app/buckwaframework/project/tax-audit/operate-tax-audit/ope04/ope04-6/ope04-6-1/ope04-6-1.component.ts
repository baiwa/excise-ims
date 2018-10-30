import { Component, OnInit } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';
import { Ope0461Service } from './ope04-6-1.service';
import { TextDateTH, formatter } from 'helpers/datepicker';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
declare var $: any;
@Component({
  selector: 'app-ope04-6-1',
  templateUrl: './ope04-6-1.component.html',
  styleUrls: ['./ope04-6-1.component.css'],
  providers: [Ope0461Service]
})
export class Ope0461Component implements OnInit {

  breadcrumb: BreadCrumb[] = [
    { label: 'หน้าแรก', route: '#' },
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'การตรวจสอบภาษี', route: '#' },
    { label: 'ผลการตรวจสอบรายการวัตถุดิบที่ขอลดหย่อนภาษี', route: '#' },
  ]

  // ==> params
  formControl: FormGroup;

  constructor(
    private ope0461Service: Ope0461Service,
    private formBuilder: FormBuilder,
  ) { }

  ngOnInit() {
    this.newFormControl();
    this.calenda();
    this.ope0461Service.datatable();
  }

  claer = () => {
    this.newFormControl();
  }

  newFormControl = () => {
    this.formControl = this.formBuilder.group({
      dateFrom: ["", Validators.required],
      dateTo: ["", Validators.required],
    });
  }

  datatable = () => {
    this.ope0461Service.datatable();
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
}
