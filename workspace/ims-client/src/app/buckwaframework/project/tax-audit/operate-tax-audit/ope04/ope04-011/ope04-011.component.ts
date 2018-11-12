import { Component, OnInit } from '@angular/core';
import { Ope04011Service } from './ope04-011.service';
import { BreadCrumb } from 'models/breadcrumb';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MessageBarService } from 'services/message-bar.service';
import { Utils } from 'helpers/utils';

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
    private messege: MessageBarService
  ) {
    this.newFormControl();
    this.findexciseI();
  }

  ngOnInit() {
    $(".ui.dropdown").dropdown();
  }

  newFormControl = () => {
    this.formControl = this.formBuilder.group({
      exciseId: [{value: ''}, Validators.required],
      subtype: [""],
      anlysisNumber: [""],
      companyName: [""],
      type: [""],

      addedTax: ["", Validators.required],
      fine: ["", Validators.required],
      extraMoney: ["", Validators.required]
    });
  }
  get f() {
    return this.formControl.controls;
  }

  claer() {
    this.submitted = false;
    this.newFormControl();
    $("#exciseId").dropdown('restore defaults');
  }

  save() {
    this.submitted = true;
    if (this.formControl.invalid) {
      return false;
    }

    this.loading = true;
    this.messege.comfirm((res) => {
      if (res) {
        this.myService.save(this.formControl.value).then((res)=>{
          this.messege.successModal("บันทึกรายสำเร็จ");
          this.loading = false;
          this.submitted = false;
          this.newFormControl();
          $("#exciseId").dropdown('restore defaults');
        });
      }else{
        this.loading = false;
      }
    }, "บันทึกรายการ");
  }

  findexciseI() {
    this.loading = true;
    this.myService.findexciseI().then(res => { 
      this.exciseIdList = res;
      this.loading = false;
    });
  }

  findByExciseId(e) {
    let exciseId = e.target.value;
   if(Utils.isNotNull(exciseId)){
    this.myService.findByExciseId(exciseId).then((res) => {
      console.log(res);

      this.formControl.patchValue({
        subtype: res.subtype,
        anlysisNumber: res.anlysisNumber,
        companyName: res.companyName,
        type: res.type,
      })
    });
   }
  }

}
