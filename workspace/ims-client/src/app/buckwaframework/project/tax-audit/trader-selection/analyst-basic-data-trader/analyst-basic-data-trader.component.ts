import { Component, OnInit } from '@angular/core';
import { AjaxService } from '../../../../common/services/ajax.service';
import {Router, ActivatedRoute, Params} from '@angular/router';

import { TextDateTH, digit } from '../../../../common/helper/datepicker';
declare var jQuery: any;
declare var $: any;
@Component({
  selector: 'app-analyst-basic-data-trader',
  templateUrl: './analyst-basic-data-trader.component.html',
  styleUrls: []
})
export class AnalystBasicDataTraderComponent implements OnInit {
  listMenu:any[] = [];
  showmenu:boolean = true;
  userManagementDt: any;
  router: any;
  month: any;
  from: any;
  form1: any;
  form2: any;
  private listItem: any[];
  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.listMenu = ["น้ำมัน"	
      , "เครื่องดื่ม"	
      , "ยาสูบ"	
      , "ไพ่"	
      , "แก้วและเครื่องแก้ว"	
     , "รถยนต์"	
     , 'พรมและสิ่งทอปูพื้น'	
     , "แบตเตอรี่"	
     , "ไนท์คลับและดิสโกเธค"	
     , "สถานอาบน้ำหรืออบตัวและนวด"	
     , "สนามแข่งม้า"	
     , 'สนามกอล์ฟ'	
     , "รวม"]	;

    // subscribe to router event
    this.from = this.route.snapshot.queryParams["from"];
    this.month = this.route.snapshot.queryParams["month"];
    //split function
    var from_split = this.from.split("/");

    //default month & year
    var month = from_split[0];
    var year_before = from_split[1];

    var m = parseInt(month)+1;
    var mm = parseInt(this.month);
    var yy = parseInt(year_before);
    // console.log('mm ',m);
    for(var i=1;i<=mm;i++){
      m = m-1;
      if(m==0){m = 12;yy = yy-1;}
    }
    
    // console.log('เดือนปีตั้ง ',parseInt(month)+'/',parseInt(year_before));
    // console.log('ย้อนไป ',parseInt(this.month));
    // console.log('เดือนปีย้อน ',m+'/',yy);

    //show values
    var sum_month = TextDateTH.months[m-1];
    this.form1 = sum_month + " " + yy;
    var sum_month2 = TextDateTH.months[parseInt(month)-1];
    this.form2 = sum_month2 + " " + parseInt(year_before);
    
    this.initDatatable();
  }
  ngAfterViewInit() {

  }

  initDatatable(): void {
    var d = new Date();
    d.setFullYear(parseInt(this.from[1]));
    d.setMonth(parseInt(this.from[0]));
    const URL = AjaxService.CONTEXT_PATH + "/working/test/list";
    this.userManagementDt = $('#userManagementDt').DataTable({
      "lengthChange": false,
      "searching": false,
      "ordering": true,
      "pageLength": 10,
      "processing": true,
      "serverSide": true,
      "paging": true,
      "pagingType": "full_numbers",

      "ajax": {
        "type": "POST",
        "url": URL,
        "data": {
          "startBackDate": d.getTime(),
          "month": this.month
        }
      },
      "columns": [
     
        { "data": "exciseRegisttionNumberId","className":"center" },
        { "data": "exciseId","className":"center" },
        { "data": "exciseOperatorName" },
        { "data": "exciseFacName" },
        { "data": "exciseArea" },
        { "data": "exciseFacAddress" ,"className":"center" },
        { "data": "exciseRegisCapital","className":"center" },
        { "data": "change","className":"center" },   
        { "data": "payingtax" ,"className":"center"},        
        { "data": "no1" },
        { "data": "no2" },
        { "data": "no3" },       
        { "data": "coordinates" },
        { "data": "sector" },
        { "data": "industrialAddress" },
        { "data": "registeredCapital" },
        { "data": "status" },
        { "data": "exciseFirstTaxReceiveAmount1" ,"className":"center"},
        { "data": "exciseFirstTaxReceiveAmount2","className":"center" },
        { "data": "exciseFirstTaxReceiveAmount3","className":"center" },
        { "data": "exciseFirstTaxReceiveAmount4","className":"center" },
        { "data": "exciseFirstTaxReceiveAmount5","className":"center" },
        { "data": "exciseFirstTaxReceiveAmount6","className":"center" },
        { "data": "exciseFirstTaxReceiveAmount7" ,"className":"center"},
        { "data": "exciseFirstTaxReceiveAmount8","className":"center" },
        { "data": "exciseFirstTaxReceiveAmount9","className":"center" },
        { "data": "exciseFirstTaxReceiveAmount10","className":"center" },
        { "data": "exciseFirstTaxReceiveAmount11","className":"center" },
        { "data": "exciseFirstTaxReceiveAmount12","className":"center" },
        { "data": "exciseLatestTaxReceiveAmount1","className":"center" },
        { "data": "exciseLatestTaxReceiveAmount2","className":"center" },
        { "data": "exciseLatestTaxReceiveAmount3","className":"center"},
        { "data": "exciseLatestTaxReceiveAmount4","className":"center" },
        { "data": "exciseLatestTaxReceiveAmount5","className":"center" },
        { "data": "exciseLatestTaxReceiveAmount6","className":"center" },
        { "data": "exciseLatestTaxReceiveAmount7","className":"center" },
        { "data": "exciseLatestTaxReceiveAmount8","className":"center" },
        { "data": "exciseLatestTaxReceiveAmount9","className":"center" },
        { "data": "exciseLatestTaxReceiveAmount10" ,"className":"center"},
        { "data": "exciseLatestTaxReceiveAmount11","className":"center" },
        { "data": "exciseLatestTaxReceiveAmount12","className":"center" },
        {
          "data":"exciseId",
          "render" : function ( data, type, row, meta ) {
            return `<a class="ui blue button" href="/#/add-data?id=${data}">เพิ่มข้อมูล</a>`;
          }
        }
      ],

    });
  }


}
