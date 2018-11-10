import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';
import { Ope0441Service } from './ope04-4-1.service';
import { Validators, FormGroup, FormBuilder } from '@angular/forms';
import { TextDateTH, formatter } from 'helpers/datepicker';
declare var $: any;
@Component({
  selector: 'ope04-4-1',
  templateUrl: './ope04-4-1.component.html',
  styleUrls: ['./ope04-4-1.component.css'],
  providers : [Ope0441Service]
})
export class Ope0441Component implements OnInit {

  public showData: boolean = false;
  breadcrumb: BreadCrumb[] = [    
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'การตรวจสอบภาษี', route: '#' },
    { label: 'สร้างกระดาษทำการตรวจสอบภาษี', route: '#' },
    { label: 'ผลการตรวจกระดาษทำการรับสินค้าสำเร็จรูป', route: '#' },    
  ];
    // ==> params
    formControl: FormGroup;
    exciseIdList: any;
    submitted: boolean = false;
    
  constructor(
    private authService: AuthService,
    private myService: Ope0441Service,
    private formBuilder: FormBuilder,    
    ) { }

  ngOnInit() {   
    this.authService.reRenderVersionProgram('OPE-04410');
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