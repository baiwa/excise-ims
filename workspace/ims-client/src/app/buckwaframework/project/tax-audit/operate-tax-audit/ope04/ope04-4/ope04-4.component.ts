import { Component, OnInit, AfterViewInit } from '@angular/core';
import { TextDateTH, DecimalFormat, digit, formatter } from '../../../../../common/helper';
import { AjaxService, MessageBarService, AuthService } from '../../../../../common/services';
import { BreadCrumb } from 'models/breadcrumb';
import { Ope044Service } from './ope04-4.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Ope044Form } from './ope04-4.model';
declare var $: any;
@Component({
  selector: 'ope04-4',
  templateUrl: './ope04-4.component.html',
  styleUrls: ['./ope04-4.component.css'],
  providers: [Ope044Service]
})
export class Ope044Component implements OnInit, AfterViewInit {

  breadcrumb: BreadCrumb[] = [
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'การตรวจสอบภาษี', route: '#' },
    { label: 'สร้างกระดาษทำการตรวจสอบภาษี', route: '#' },
    { label: 'กระดาษทำการรับสินค้าสำเร็จรูป', route: '#' },
  ];
  submitted: boolean = false;
  form: Ope044Form = new Ope044Form();
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
    private ope044Service: Ope044Service
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
    this.ope044Service.findExciseId().then((res) => {
      this.exciseIdList = res;
    });
  }

  callDropdown() {
    $(".ui.dropdown").dropdown();
  }

  search = () => {
    
    this.dform = this.formControl.controls.dateFrom.value;
    this.dTo = this.formControl.controls.dateTo.value;
    this.company = this.formControl.controls.entrepreneur.value;
    this.submitted = true;
    if (this.formControl.invalid) {
      return;
    }
    $("#showData").show();
    this.loading = true;
    $("#Dtable").show();
    this.ope044Service.search().then(res => {
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
    this.ope044Service.claer();
    $("#showData").hide();
  }
  changeExciseId = (e) => {
    let exciseId = e.target.value;

    this.ope044Service.findByExciseId(exciseId).then((res) => {

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
    this.ope044Service.onChangeUpload(file).then(res => {
      this.uploadDisabled = false;
    });
  }

  upload = () => {
    this.loading = true;
    const form = $("#upload-form")[0];
    let formBody = new FormData(form);
    this.ope044Service.upload(formBody).then(() => {
      this.loading = false;
      this.buttonDisabled = false;
    });
  }

  save = async () => {
    this.buttonDisabled = await true;
    await this.ope044Service.save(this.formControl.value).then(async (res) => {
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
    this.ope044Service.dataTable();
  }
}
