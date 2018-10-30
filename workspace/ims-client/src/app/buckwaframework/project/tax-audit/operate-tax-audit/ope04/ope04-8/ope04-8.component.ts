import { Component, OnInit, AfterViewInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { TextDateTH, formatter } from 'helpers/datepicker';
import { BreadCrumb } from 'models/breadcrumb';
import { Ope048Service } from './ope04-8.service';
import { Ope048Form } from './ope04-8.modesl';
import { Utils } from 'helpers/utils';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
declare var $: any;
@Component({
  selector: 'ope04-8',
  templateUrl: './ope04-8.component.html',
  styleUrls: ['./ope04-8.component.css'],
  providers: [Ope048Service]
})
export class Ope048Component implements OnInit, AfterViewInit {

  breadcrumb: BreadCrumb[] = [
    { label: 'หน้าแรก', route: '#' },
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'การตรวจสอบภาษี', route: '#' },
    { label: 'สร้างกระดาษทำการตรวจสอบด้านราคา', route: '#' },
  ];

  // ==> params
  submitted: boolean = false;
  form: Ope048Form = new Ope048Form();
  exciseIdList: any;
  loading: boolean = false;
  error: boolean = false;
  formControl: FormGroup;
  uploadDisabled : boolean = true;
  constructor(
    private authService: AuthService,
    private ope048Service: Ope048Service,
    private formBuilder: FormBuilder,
  ) { }

  ngOnInit() {
    $("#Dtable").hide();
    this.newForm();
    
    //this.authService.reRenderVersionProgram('OPE-04800');
  }

  newForm(){
    this.formControl = this.formBuilder.group({
      dateFrom: ["", Validators.required],
      dateTo: ["", Validators.required],
      exciseId: ["", Validators.required],
      entrepreneur: [""],
      anlysisNumber: [""],
      type: [""],
      coordinates: [""],
      uploadFlag: [""],
      userNumber: [""],
      fileUpload: [""],
      searchFlag: [""],
    });
  }
  ngAfterViewInit() {
    this.calenda();
    this.callDropdown();
    this.findExciseId();
    this.dataTable();
  }

  get f() {
    return this.formControl.controls;
  }

  findExciseId = () => {
    this.ope048Service.findExciseId().then((res) => {
      this.exciseIdList = res;
    });
  }
  changeExciseId = (e) => {
    let exciseId = e.target.value;
    this.ope048Service.findByExciseId(exciseId).then((res) => {
      this.formControl.controls.entrepreneur.setValue(res.exciseName);
      this.formControl.controls.coordinates.setValue(res.productType);
      this.formControl.controls.userNumber.setValue(res.taxFeeId);
      this.form.entrepreneur = res.exciseName;
      // this.form.coordinates = res.productType;
      // this.form.userNumber = res.taxFeeId;
    });
  }
  callDropdown() {
    $(".ui.dropdown").dropdown();
  }
  search = () => {    
    this.submitted = true;
    if (this.formControl.invalid) {
      return;
    }
    this.loading = true;
    $("#Dtable").show();
    this.ope048Service.search().then(res=>{
      setTimeout(() => {
        this.loading = false;
      }, 300);
    });
  }

  onChangeUpload(file: any) {
    this.ope048Service.onChangeUpload(file).then(res=>{
        this.uploadDisabled = false;
    });
  }

  upload = () => {
    this.loading = true;
    const form = $("#upload-form")[0];
    let formBody = new FormData(form);
    this.ope048Service.upload(formBody);
    setTimeout(() => {
      this.loading = false;
    }, 500);
  }


  claer = () => {
    this.error = false;
    this.uploadDisabled = true;
    $("form-exciseId").removeClass('error');
    $("#Dtable").hide();
    this.form = new Ope048Form();
    this.newForm();
    this.ope048Service.claer();

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
        let array = text.split("/");
        let _month = array[0];
        let _year = array[1];
        let month = TextDateTH.months[parseInt(_month) - 1];
        console.log(month);
        this.form.dateFrom = month + " " + _year;
        this.formControl.controls.dateFrom.setValue(month + " " + _year);
      }
    });
    $("#dateT").calendar({
      maxDate: new Date(date.getFullYear() + "-" + (date.getMonth())),
      startCalendar: $("#dateF"),
      type: "month",
      text: TextDateTH,
      formatter: formatter('month-year'),
      onChange: (date, text) => {
        let array = text.split("/");
        let _month = array[0];
        let _year = array[1];
        let month = TextDateTH.months[parseInt(_month) - 1];
        console.log(month);
        this.form.dateTo = month + " " + _year;
        this.formControl.controls.dateTo.setValue(month + " " + _year);
      }
    });
  }

  dataTable = () => {
    this.ope048Service.dataTable();
  }
}
