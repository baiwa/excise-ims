import { Component, OnInit } from '@angular/core';
import { MessageBarService } from 'app/buckwaframework/common/services';

declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-int05-1-1-2',
  templateUrl: './int05-1-1-2.component.html',
  styleUrls: ['./int05-1-1-2.component.css']
})
export class Int05112Component implements OnInit {

  table: any;
  data: FormModal[];
  constructor(
    private message: MessageBarService
  ) {
    this.data = []
   }

  ngOnInit() {
    $(".ui.dropdown").dropdown();
  }
  ngAfterViewInit() {    
    this.dataTable();
  }

  spilt=(req)=>{    
    return req.split( ".");
  }

  dataTable = () => {
    this.table = $("#dataTable").DataTable({
      "pageLength": 25,
      "serverSide": false,
      "searching": false,
      "ordering": false,
      "processing": true,
      "scrollX": true,  
      "scrollCollapse": true,
      "ajax": {
        "url": '/ims-webapp/api/ia/int05112/findAll',
        "contentType": "application/json",
        "type": "POST",
        "data": (d) => {
          return JSON.stringify($.extend({}, d, {
          }));
        },
      },
      "drawCallback": () => {
        $('.mark-th').closest('tr').addClass('active');        
      },      
      "columns": [
        {
          "data": "order",
          "render": function (data, type, row, meta) {
            if(data.split(".").length==1){
              $(row).addClass('active');               
              return '<span class="mark-th">'+data+'</span>';
            }
            return data;
          },
          "className": "ui center aligned"}, 
        { "data": "stampType"}, 

        { "data": "branchLastYeatNumberOfStamp", "className": "ui center aligned"}, 
        { "data": "branchLastYeatMoneyOfStamp", "className": "ui center aligned"},

        { "data": "octoberRecieve", "className": "ui center aligned"}, 
        { "data": "octoberPay", "className": "ui center aligned"}, 

        { "data": "novemberRecieve", "className": "ui center aligned"}, 
        { "data": "novemberPay", "className": "ui center aligned"}, 

        { "data": "decemberRecieve", "className": "ui center aligned"}, 
        { "data": "decemberPay", "className": "ui center aligned"},

        { "data": "januaryRecieve", "className": "ui center aligned"}, 
        { "data": "januaryPay", "className": "ui center aligned"},

        { "data": "februaryRecieve", "className": "ui center aligned"}, 
        { "data": "februaryPay", "className": "ui center aligned"}, 

        { "data": "marchRecieve", "className": "ui center aligned"}, 
        { "data": "marchPay", "className": "ui center aligned"}, 

        { "data": "aprilRecieve", "className": "ui center aligned"}, 
        { "data": "aprilPay", "className": "ui center aligned"}, 

        { "data": "mayRecieve", "className": "ui center aligned"}, 
        { "data": "mayPay", "className": "ui center aligned"}, 

        { "data": "juneRecieve", "className": "ui center aligned"}, 
        { "data": "junePay", "className": "ui center aligned"}, 

        { "data": "julyRecieve", "className": "ui center aligned"}, 
        { "data": "julyPay", "className": "ui center aligned"}, 

        { "data": "augustRecieve", "className": "ui center aligned"}, 
        { "data": "augustPay", "className": "ui center aligned"}, 

        { "data": "septemberRecieve", "className": "ui center aligned"}, 
        { "data": "septemberPay", "className": "ui center aligned"}, 

        { "data": "summaryYearRecieve", "className": "ui center aligned"}, 
        { "data": "summaryYearMoneyRecieve", "className": "ui center aligned"}, 
        { "data": "summaryYearPay", "className": "ui center aligned"}, 
        { "data": "summaryYearMoneyPay", "className": "ui center aligned"}, 

        { "data": "summaryTotalRecieve", "className": "ui center aligned"}, 
        { "data": "summaryTotalPay", "className": "ui center aligned"}, 
        { "data": "summaryTotalMoneyRecieve", "className": "ui center aligned"}, 
        { "data": "summaryTotalMoneyPay", "className": "ui center aligned"}, 

        { "data": "branchUpToDateNumberOfStamp", "className": "ui center aligned"}, 
        { "data": "branchUpToDateMoneyOfStamp", "className": "ui center aligned"}, 

        { "data": "note", "className": "ui center aligned"}, 

      ]
    });    
  }
}
class FormModal {
  workSheetDetailId: string = null;
  exciseDepartment: string = null;
  exciseRegion: string = null;
  exciseDistrict: string = null;
  dateOfPay: string = null;
  status: string = null;
  departmentName: string = null;
  bookNumberWithdrawStamp: string = null;
  dateWithdrawStamp: string = null;
  bookNumberDeliverStamp: string = null;
  dateDeliverStamp: string = null;
  fivePartNumber: string = null;
  fivePartDate: string = null;
  stampCheckDate: string = null;
  stampChecker: string = null;
  stampType: string = null;
  stampBrand: string = null;
  numberOfBook: string = null;
  numberOfStamp: string = null;
  valueOfStampPrinted: string = null;
  sumOfValue: string = null;
  serialNumber: string = null;
  taxStamp: string = null;
  stampCodeStart: string = null;
  stampCodeEnd: string = null;
  note: string = null;
  createdDate: string = null;
  fileName: [any];
  idRandom: number = 0;
  file : File[];
}