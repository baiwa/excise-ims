import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/index';
import { TextDateTH } from 'helpers/datepicker';
import { formatter } from 'helpers/datepicker';
import { Utils } from 'helpers/utils';
import { Cop063Service } from 'projects/check-operation/cop06/cop06-3/cop06-3.service';
import { Cop063Form } from 'projects/check-operation/cop06/cop06-3/cop06-3.modesl';
declare var $: any;
@Component({
  selector: 'cop06-3',
  templateUrl: './cop06-3.component.html',
  styleUrls: ['./cop06-3.component.css'],
  providers: [Cop063Service]
})
export class Cop063Component implements OnInit {

  breadcrumb: BreadCrumb[] = [
    { label: "ตรวจปฏิบัติการ", route: "#" },
    { label: 'รายงานการตรวจปฏิบัติการ', route: '#' },
    { label: 'รายงานการตรวจปฏิบัติการการรับสินค้าสำเร็จรูป', route: '#' },
  ]

  // === > params
  form: Cop063Form = new Cop063Form();
  exciseIdList: any;
  loading: boolean = false;
  buttonDisabled: boolean = false;
  error: boolean = false;
  constructor(
    private authService: AuthService,
    private cop063Service: Cop063Service,
  ) { }

  ngOnInit() {
    // this.authService.reRenderVersionProgram('OPE-04600');
    $("#Dtable").hide();
    this.findExciseId();
    this.callDropdown();
    this.calenda();
    this.dataTable();
  }

  findExciseId = () => {
    this.cop063Service.findExciseId().then((res) => {
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
      this.cop063Service.search();
    }
  }
  claer = () => {
    this.error = false;
    $("form-exciseId").removeClass('error');
    $("#Dtable").hide();
    this.cop063Service.claer();
  }
  changeExciseId = (e) => {
    let exciseId = e.target.value;
    this.cop063Service.findByExciseId(exciseId).then((res) => {
      this.form.entrepreneur = res.exciseName;
      this.form.coordinates = res.productType;
      this.form.userNumber = res.taxFeeId;
    });
  }
  onChangeUpload(file: any) {
    this.cop063Service.onChangeUpload(file);
  }

  upload = () => {
    this.loading = true;
    const form = $("#upload-form")[0];
    let formBody = new FormData(form);
    this.cop063Service.upload(formBody);
    setTimeout(() => {
      this.loading = false;
    }, 500);
  }

  save = async () => {
    this.buttonDisabled = await true;
    await this.cop063Service.save().then( async(res)=>{
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
    this.cop063Service.dataTable();
  }
}

