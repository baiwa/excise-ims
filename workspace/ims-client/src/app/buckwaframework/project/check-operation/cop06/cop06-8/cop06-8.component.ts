import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/index';
import { TextDateTH } from 'helpers/datepicker';
import { formatter } from 'helpers/datepicker';
import { Utils } from 'helpers/utils';
import { Cop068Form } from 'projects/check-operation/cop06/cop06-8/cop06-8.modesl';
import { Cop068Service } from 'projects/check-operation/cop06/cop06-8/cop06-8.service';
declare var $: any;
@Component({
  selector: 'cop06-8',
  templateUrl: './cop06-8.component.html',
  styleUrls: ['./cop06-8.component.css'],
  providers: [Cop068Service]
})
export class Cop068Component implements OnInit {

  breadcrumb: BreadCrumb[] = [
    { label: "ตรวจปฏิบัติการ", route: "#" },
    { label: 'รายงานการตรวจปฏิบัติการ', route: '#' },
    { label: 'รายงานการตรวจปฏิบัติการตรวจสอบด้านราคา', route: '#' },
  ]

  // === > params
  form: Cop068Form = new Cop068Form();
  exciseIdList: any;
  loading: boolean = false;
  buttonDisabled: boolean = false;
  error: boolean = false;
  constructor(
    private authService: AuthService,
    private cop068Service: Cop068Service,
  ) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('COP-06800');
    $("#Dtable").hide();
    this.findExciseId();
    this.callDropdown();
    this.calenda();
    this.dataTable();
  }

  findExciseId = () => {
    this.cop068Service.findExciseId().then((res) => {
      this.exciseIdList = res;
    });
  }

  callDropdown() {
    $(".ui.dropdown").dropdown();
  }

  search = () => {
    if (Utils.isNull(this.form.exciseId)) {
      this.error = true;
    } else {
      this.error = false;
    }
    console.log($("exciseId").val());
    if (Utils.isNotNull(this.form.exciseId)) {
      $("#Dtable").show();
      this.cop068Service.search();
    }
  }
  claer = () => {
    this.error = false;
    $("form-exciseId").removeClass('error');
    $("#Dtable").hide();
    this.cop068Service.claer();
  }
  changeExciseId = (e) => {
    let exciseId = e.target.value;
    this.cop068Service.findByExciseId(exciseId).then((res) => {
      this.form.entrepreneur = res.exciseName;
      this.form.coordinates = res.productType;
      this.form.userNumber = res.taxFeeId;
    });
  }
  onChangeUpload(file: any) {
    this.cop068Service.onChangeUpload(file);
  }

  upload = () => {
    this.loading = true;
    const form = $("#upload-form")[0];
    let formBody = new FormData(form);
    this.cop068Service.upload(formBody);
    setTimeout(() => {
      this.loading = false;
    }, 500);
  }

  save = async () => {
    this.buttonDisabled = await true;
    await this.cop068Service.save().then( async(res)=>{
      if("C" == res){
        this.buttonDisabled = await false;
      }
      setTimeout( async() => {
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
        let month = TextDateTH.months[parseInt(_month)-1];
        console.log(month);
        this.form.dateFrom = month + " " + _year;
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
        let month = TextDateTH.months[parseInt(_month)-1];
        console.log(month);
        this.form.dateTo = month + " " + _year;
      }
    });
  }
  dataTable = () => {
    this.cop068Service.dataTable();
  }
}

