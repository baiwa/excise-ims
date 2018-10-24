import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/index';
import { Opeo46Service } from './opeo4-6.service';
import { Ope046Form } from './ope04-6.modesl';
import { TextDateTH } from 'helpers/datepicker';
import { formatter } from 'helpers/datepicker';
declare var $: any;
@Component({
  selector: 'ope04-6',
  templateUrl: './ope04-6.component.html',
  styleUrls: ['./ope04-6.component.css'],
  providers: [Opeo46Service]
})
export class Ope046Component implements OnInit {

  breadcrumb: BreadCrumb[] = [
    { label: 'หน้าแรก', route: '#' },
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'การตรวจสอบภาษี', route: '#' },
    { label: 'สร้างกระดาษทำการตรวจสอบภาษี', route: '#' },
  ]

  // === > params
  form: Ope046Form = new Ope046Form();
  exciseIdList: any;
  loading: boolean = false;
  constructor(
    private authService: AuthService,
    private opeo46Service: Opeo46Service,
  ) { }

  ngOnInit() {
    // this.authService.reRenderVersionProgram('OPE-04600');
    this.findExciseId();
    this.callDropdown();
    this.calenda();
    this.dataTable();
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
    this.opeo46Service.search();

  }
  claer = () => {
    this.opeo46Service.claer();
  }
  changeExciseId = (e) => {
    let exciseId = e.target.value;
    this.opeo46Service.findByExciseId(exciseId).then((res) => {
      this.form.entrepreneur = res.exciseName;
      this.form.coordinates = res.productType;
      this.form.userNumber = res.taxFeeId;
    });
  }
   onChangeUpload(file: any) {
     this.opeo46Service.onChangeUpload(file);
  }

  upload=()=>{
    const form = $("#upload-form")[0];
    let formBody = new FormData(form);
    this.opeo46Service.upload(formBody);
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
        this.form.dateFrom = text;
      }
    });
    $("#dateT").calendar({
      maxDate: new Date(date.getFullYear() + "-" + (date.getMonth())),
      startCalendar: $("#dateF"),
      type: "month",
      text: TextDateTH,
      formatter: formatter('month-year'),
      onChange: (date, text) => {
        this.form.dateTo = text;
      }
    });
  }
  dataTable = () => {
    this.opeo46Service.dataTable();
  }
}

