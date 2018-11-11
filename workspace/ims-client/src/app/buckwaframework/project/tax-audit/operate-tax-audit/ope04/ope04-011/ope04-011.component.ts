import { Component, OnInit } from '@angular/core';
import { Ope04011Service } from './ope04-011.service';
import { BreadCrumb } from 'models/breadcrumb';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
declare var $: any;
@Component({
  selector: 'app-ope04-011',
  templateUrl: './ope04-011.component.html',
  styleUrls: ['./ope04-011.component.css'],
  providers: [Ope04011Service]
})
export class Ope04011Component implements OnInit {

  breadcrumb: BreadCrumb[] = [
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'การตรวจสอบภาษี', route: '#' },
    { label: 'รายงานผลการตรวจสอบ', route: '#' },
  ];

  // ==> boolean
  submitted: boolean = false;
  loading: boolean = false;

  //=>params
  exciseIdList: any;

  // ==>form
  formControl: FormGroup;
  constructor(
    private myService: Ope04011Service,
    private formBuilder: FormBuilder,
  ) {
    this.newFormControl();
    this.findexciseI();
  }

  ngOnInit() {
    $(".ui.dropdown").dropdown();
  }

  newFormControl = () => {
    this.formControl = this.formBuilder.group({
      exciseId: ["", Validators.required],
      subType: [""],
      anlysisNumber: [""],
      companyName: [""],
      type: [""],
      userNumber: [""],
    });
  }
  get f() {
    return this.formControl.controls;
  }

  findexciseI() {
    this.myService.findexciseI().then(res => {
      this.exciseIdList = res;
    });
  }

  findByExciseId(e) {
    let exciseId = e.target.value;
    this.myService.findByExciseId(exciseId).then((res) => {
      console.log(res);

      this.formControl.patchValue({ 
        subType: res.subType,
        anlysisNumber: res.anlysisNumber,
        companyName: res.companyName,        
        subtype: res.subtype,
        type: res.type,
      })
    });
  }

}
