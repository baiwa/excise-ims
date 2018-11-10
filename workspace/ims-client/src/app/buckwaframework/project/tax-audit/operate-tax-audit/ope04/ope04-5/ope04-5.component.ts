import { Component, OnInit, AfterViewInit } from '@angular/core';
import { TextDateTH, formatter } from '../../../../../common/helper';
import { AjaxService, AuthService } from '../../../../../common/services';
import { BreadCrumb } from 'models/breadcrumb';
import { Ope045Service } from './ope04-5.service';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { Ope045Form } from './ope04-5.model';
declare var $: any;
@Component({
  selector: 'ope04-5',
  templateUrl: './ope04-5.component.html',
  styleUrls: ['./ope04-5.component.css'],
  providers:[Ope045Service]
})
export class Ope045Component implements OnInit, AfterViewInit {

  breadcrumb: BreadCrumb[] = [    
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'การตรวจสอบภาษี', route: '#' },
    { label: 'สร้างกระดาษทำการตรวจสอบภาษี', route: '#' },
    { label: 'กระดาษทำการจ่ายสินค้าสำเร็จรูป', route: '#' },
  ];
  submitted: boolean = false;
  form: Ope045Form = new Ope045Form();
  exciseIdList: any;
  loading: boolean = false;
  buttonDisabled: boolean = true;
  error: boolean = false;
  uploadDisabled: boolean = true;
  formControl: FormGroup;

  dform: string = '';
  dTo: string = '';
  company: string = '';

  constructor(
    private authService: AuthService,
    private formBuilder: FormBuilder,
    private myService: Ope045Service
  ) {

  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-04400');
    $("#showData").hide();
    this.newForm();
    // this.authService.reRenderVersionProgram('OPE-04600');
    $("#Dtable").hide();
    this.findExciseId();
    this.callDropdown();
    this.calenda();

  }
  ngAfterViewInit() {
    this.dataTable();
  }
  newForm() {
    this.formControl = this.formBuilder.group({
      dateFrom: ["", Validators.required],
      dateTo: ["", Validators.required],
      dateFromDesc: [""],
      dateToDesc: [""],
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

  get f() {
    return this.formControl.controls;
  }


  findExciseId = () => {
    this.myService.findExciseId().then((res) => {
      this.exciseIdList = res;
    });
  }

  callDropdown() {
    $(".ui.dropdown").dropdown();
  }

  search = () => {

    this.company = this.formControl.value.entrepreneur;

    this.dform = this.formatDateThai(this.formControl.value.dateFrom);
    this.dTo = this.formatDateThai(this.formControl.value.dateTo);    

    this.submitted = true;
    if (this.formControl.invalid) {
      return;
    }
    $("#showData").show();
    this.loading = true;
    $("#Dtable").show();
    this.myService.search().then(res => {
      setTimeout(() => {
        this.loading = false;
      }, 300);
    });

  }

  claer = () => {
    this.error = false;
    this.submitted = false;
    $("form-exciseId").removeClass('error');
    $("#Dtable").hide();
    this.newForm();
    this.uploadDisabled = true;
    this.buttonDisabled = true;
    this.myService.claer();
    $("#showData").hide();
  }
  changeExciseId = (e) => {
    let exciseId = e.target.value;

    this.myService.findByExciseId(exciseId).then((res) => {

      this.formControl.controls.entrepreneur.setValue(res.exciseName);
      this.formControl.controls.coordinates.setValue(res.productType);
      this.formControl.controls.userNumber.setValue(res.taxFeeId);
      this.formControl.controls.type.setValue(this.checkTypeExciseId(exciseId));
      this.form.entrepreneur = res.exciseName;
      // this.form.coordinates = res.productType;
      // this.form.userNumber = res.taxFeeId;
    });
  }
  onChangeUpload(file: any) {
    this.myService.onChangeUpload(file).then(res => {
      this.uploadDisabled = false;
    });
  }

  upload = () => {
    this.loading = true;
    const form = $("#upload-form")[0];
    let formBody = new FormData(form);
    this.myService.upload(formBody).then(() => {
      this.loading = false;
      this.buttonDisabled = false;
    });
  }

  export = () => {
    let dataSum = this.myService.getSummaryData();
    console.log(dataSum);
    let formExcel = $("#form-data-excel").get(0);
    formExcel.action = AjaxService.CONTEXT_PATH + "ta/opo044/export";
    formExcel.dataJson.value = JSON.stringify({ voList: dataSum });
    formExcel.submit();
  }

  save = async () => {
    this.buttonDisabled = await true;
    await this.myService.save(this.formControl.value).then(async (res) => {
      if ("C" == res) {
        this.buttonDisabled = await false;
      }
      setTimeout(async () => {
        this.buttonDisabled = await false;
      }, 500);
    });
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
        this.form.dateFromDesc = month + " " + _year;
        this.formControl.controls.dateFrom.setValue(text);
        this.formControl.controls.dateFromDesc.setValue(month + " " + _year);
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
        this.form.dateToDesc = month + " " + _year;
        this.formControl.controls.dateTo.setValue(text);
        this.formControl.controls.dateToDesc.setValue(month + " " + _year);
      }
    });
  }

  checkTypeExciseId(exciseId) {
    let type = exciseId.substring(14, 15);
    let result = "";
    if (type == 1) {
      result = "สินค้า";
    }
    else if (type == 2) {
      result = "บริการ";
    }
    else {
      result = "สินค้านำเข้า";
    }
    return result;
  }

  dataTable = () => {
    this.myService.dataTable();
  }

  formatDateThai(text){  
    let array = text.split("/");
    let _month = array[0];
    let _year = array[1];
    let month = TextDateTH.months[parseInt(_month) - 1];
    return month + " " + _year;
  }
}
