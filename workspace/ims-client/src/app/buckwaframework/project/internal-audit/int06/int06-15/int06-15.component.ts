import { Component, OnInit, AfterViewInit } from '@angular/core';
import { TextDateTH, formatter } from 'helpers/datepicker';
import { BreadCrumb } from 'models/breadcrumb';
import { Observable } from 'rxjs/Observable';
import { ComboBox } from 'models/combobox';
import { Int0615Service } from './int06-15.service';
import { AjaxService } from 'services/ajax.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
declare var $: any;
const URL = {
  DROPDOWN: "combobox/controller/getDropByTypeAndParentId"
};
@Component({
  selector: 'app-int06-15',
  templateUrl: './int06-15.component.html',
  styleUrls: ['./int06-15.component.css'],
  providers: [Int0615Service]
})
export class Int0615Component implements OnInit, AfterViewInit {
  [x: string]: any;
  breadcrumb: BreadCrumb[];
  withdrawRequestList: Observable<ComboBox>;
  checkBtn1: boolean = false;
  checkBtn2: boolean = false;
  sectorList: any;
  areaList: any;
  submitted: boolean = false;
  departmentList: any;
  accounts: any;

  travelTo1List: any;
  travelTo2List: any;
  travelTo3List: any;

  dataTable: any;
  comboBox1: any;
  comboBox2: any;

  // ==> params
  formControl: FormGroup;
  constructor(
    private selfService: Int0615Service,
    private ajax: AjaxService,
    private formBuilder: FormBuilder
  ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบเบิกจ่าย", route: "#" },
      { label: "ตรวจสอบจำนวนเงินที่เบิกเกิน", route: "#" }
    ];
    this.withdrawRequestList = selfService.dropdown("WITHDRAW_REQUEST", null);
    this.travelTo1Dropdown();

    this.newForm();
  }

  ngOnInit() {
    this.calenda();
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown ai").css("width", "100%");
  }

  ngAfterViewInit() {
    this.datatable();
  }

  get f() {
    return this.formControl.controls;
  }

  newForm() {
    this.formControl = this.formBuilder.group({
      sector: ["", Validators.required],
      area: ["", Validators.required],
      startDate: ["", Validators.required],
      endDate: ["", Validators.required],
      money: ["", Validators.required]
    });
  }

  search = () => {
    this.submitted = true;

    if (this.formControl.invalid) {
      return false;
    }

    let sector = $('#sector').dropdown('get text');
    let area = $('#area').dropdown('get text');
    this.formControl.patchValue({
      sector: sector,
      area: area,
    });
    console.log(this.formControl.value);

    this.selfService.search(this.formControl.value);
  }

  clear = () => {
    this.submitted = false;
    this.newForm();
    $(".ui.dropdown").dropdown('restore defaults');
    this.selfService.clear(this.formControl.value);
  }

  travelTo1Dropdown = () => {
    this.ajax.post(URL.DROPDOWN, { type: "SECTOR_VALUE" }, res => {
      this.travelTo1List = res.json();
    });
  }

  travelTo2Dropdown = e => {
    $("#area").dropdown('restore defaults');
    var id = e.target.value;
    if (id != "") {
      this.ajax.post(URL.DROPDOWN, { type: "SECTOR_VALUE", lovIdMaster: id }, res => {
        this.travelTo2List = res.json();
      });
    }
  }

  disabledBtn = () => {
    this.checkBtn1 = false;
    this.checkBtn2 = false;
  };

  datatable() {
    this.selfService.datatable();
  }
  checkAll = (e) => {
    this.selfService.checkAll(e);
  }
  export = () => {
    this.selfService.export();
  }
  calenda = () => {
    $("#dateF").calendar({
      maxDate: new Date(),
      endCalendar: $("#dateT"),
      type: "month",
      text: TextDateTH,
      formatter: formatter("month-year"),
      onChange: (date, text) => {
        this.formControl.controls.startDate.setValue(text)
      },

    });
    $("#dateT").calendar({
      maxDate: new Date(),
      startCalendar: $("#dateF"),
      type: "month",
      text: TextDateTH,
      formatter: formatter("month-year"),
      onChange: (date, text) => {
        this.formControl.controls.endDate.setValue(text)
      },
    });
  }

}
