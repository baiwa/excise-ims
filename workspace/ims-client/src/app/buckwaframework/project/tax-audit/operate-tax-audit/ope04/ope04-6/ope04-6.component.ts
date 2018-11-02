import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/index';
import { Opeo46Service } from './opeo4-6.service';
import { Ope046Form } from './ope04-6.modesl';
import { TextDateTH } from 'helpers/datepicker';
import { formatter } from 'helpers/datepicker';
import { Utils } from 'helpers/utils';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
declare var $: any;
@Component({
  selector: 'ope04-6',
  templateUrl: './ope04-6.component.html',
  styleUrls: ['./ope04-6.component.css'],
  providers: [Opeo46Service]
})
export class Ope046Component implements OnInit {

  breadcrumb: BreadCrumb[] = [    
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'การตรวจสอบภาษี', route: '#' },
    { label: 'สร้างกระดาษทำการตรวจสอบภาษี', route: '#' },
    { label: 'กระดาษทำการตรวจสอบรายการวัตถุดิบที่ขอลดหย่อนภาษีที่ยื่นต่อกรมสรรพสามิต (ภส.05 - 02)', route: '#' }
  ]

  // === > params
  submitted: boolean = false;
  form: Ope046Form = new Ope046Form();
  exciseIdList: any;
  loading: boolean = false;
  buttonDisabled: boolean = false;
  error: boolean = false;
  uploadDisabled: boolean = true;
  formControl: FormGroup;
  constructor(
    private authService: AuthService,
    private opeo46Service: Opeo46Service,
    private formBuilder: FormBuilder,
  ) { }

  ngOnInit() {
    this.newForm();
    // this.authService.reRenderVersionProgram('OPE-04600');
    $("#Dtable").hide();
    this.findExciseId();
    this.callDropdown();
    this.calenda();
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
    this.opeo46Service.findExciseId().then((res) => {
      this.exciseIdList = res;
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
    this.opeo46Service.search().then(res => {
      setTimeout(() => {
        this.loading = false;
      }, 300);
    });

  }

  claer = () => {
    this.error = false;
    $("form-exciseId").removeClass('error');
    $("#Dtable").hide();
    this.newForm();
    this.uploadDisabled = true;
    this.opeo46Service.claer();
  }
  changeExciseId = (e) => {
    let exciseId = e.target.value;

    this.opeo46Service.findByExciseId(exciseId).then((res) => {

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
    this.opeo46Service.onChangeUpload(file).then(res => {
      this.uploadDisabled = false;
    });
  }

  upload = () => {
    this.loading = true;
    const form = $("#upload-form")[0];
    let formBody = new FormData(form);
    this.opeo46Service.upload(formBody);
    setTimeout(() => {
      this.loading = false;
    }, 500);
  }

  save = async () => {
    this.buttonDisabled = await true;
    await this.opeo46Service.save(this.formControl.value).then(async (res) => {
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
    this.opeo46Service.dataTable();
  }
}

