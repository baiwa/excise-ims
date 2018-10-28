import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/index';
import { TextDateTH } from 'helpers/datepicker';
import { formatter } from 'helpers/datepicker';
import { Utils } from 'helpers/utils';
import { Cop061Service } from 'projects/check-operation/cop06/cop06-1/cop06-1.service';
import { Cop061Form } from 'projects/check-operation/cop06/cop06-1/cop06-1.modesl';
declare var $: any;
@Component({
  selector: 'cop06-1',
  templateUrl: './cop06-1.component.html',
  styleUrls: ['./cop06-1.component.css'],
  providers: [Cop061Service]
})
export class Cop061Component implements OnInit {

  breadcrumb: BreadCrumb[] = [
    { label: "ตรวจปฏิบัติการ", route: "#" },
    { label: 'รายงานการตรวจปฏิบัติการ', route: '#' },
    { label: 'รายงานการตรวจปฏิบัติการรับวัตถุดิบ', route: '#' },
  ]

  // === > params
  form: Cop061Form = new Cop061Form();
  exciseIdList: any = [];
  loading: boolean = false;
  buttonDisabled: boolean = false;
  error: boolean = false;
  constructor(
    private authService: AuthService,
    private cop061Service: Cop061Service,
  ) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('COP-06100');
    $("#Dtable").hide();
    this.callDropdown();
    this.calenda();
    this.dataTable();
  }

  findExciseId = (fiscalYear) => {
   
    this.cop061Service.findExciseId(fiscalYear).then((res) => {
      this.exciseIdList = res;
      console.log("this.exciseIdList",this.exciseIdList);
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
      this.cop061Service.search();
    }
  }
  claer = () => {
    this.error = false;
    $("form-exciseId").removeClass('error');
    $("#Dtable").hide();
    this.cop061Service.claer();
  }
  changeExciseId = (e) => {
    let exciseId = e.target.value;
    this.cop061Service.findByExciseId(exciseId).then((res) => {
      this.form.exciseName = res.exciseName;
      this.form.productType = res.productType;
      this.form.exciseType = res.exciseType;
      this.form.userNumber = res.taxFeeId;
    });
  }
  onChangeUpload(file: any) {
    this.cop061Service.onChangeUpload(file);
  }

  upload = () => {
    this.loading = true;
    const form = $("#upload-form")[0];
    let formBody = new FormData(form);
    this.cop061Service.upload(formBody);
    setTimeout(() => {
      this.loading = false;
    }, 500);
  }

  save = async () => {
    this.buttonDisabled = await true;
    await this.cop061Service.save().then( async(res)=>{
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
    $("#date").calendar({
      type: "year",
      text: TextDateTH,
      formatter: formatter("ป"),   
      onChange: (date, text) => {
        this.form.fiscalYear = text;
        console.log("fiscalYear",text);
        $("#exciseId").dropdown('restore defaults');
        this.findExciseId(text); 
      }
    });
  }
  dataTable = () => {
    this.cop061Service.dataTable();
  }
}

