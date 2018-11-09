import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Location } from "@angular/common";
import { AjaxService } from "../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { BaseModel, ManageReq, BreadCrumb } from 'models/index';
import { TextDateTH, formatter } from '../../../../common/helper/datepicker';
import { AuthService } from "services/auth.service";
declare var $: any;
@Component({
  selector: "int08-5",
  templateUrl: "./int08-5.component.html",
  styleUrls: ["./int08-5.component.css"]
})
export class Int085Component implements OnInit {
  startDate:any;
  endDate:any;
  startDateTM:any;
  endDateTM:any;

  breadcrumb: BreadCrumb[];
  constructor(
    private router: Router,
    private ajax: AjaxService,
    private route: ActivatedRoute,
    private messageBarService: MessageBarService,

    private authService: AuthService
  ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบรายได้", route: "#" },
      { label: "ติดตามผลการตรวจสอบ", route: "#" },
    ];
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-08500');
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    this.carenda();
    this.dataTable();

  }

  carenda(){
    $("#calendar").calendar({
      endCalendar: $("#calendar1"),
      maxDate: new Date(),
      type: "month",
      text: TextDateTH,
      formatter: formatter('ดป'),
      onChange: (date,text) => {
        let yyyy = text.split(" ")[1];
        this.startDate = (date.getMonth()<9)?"0"+(date.getMonth()+1)+"/"+yyyy:(date.getMonth()+1)+"/"+yyyy;

        let sd = new Date((parseInt(this.startDate.split("/")[1])-543) + "-" + (parseInt(this.startDate.split("/")[0])));

        console.log("sd : ",sd);
        let dateTM = (sd.getDate()<10)?"0"+(sd.getDate()):sd.getDate();
        this.startDateTM=this.startDate.split("/")[0]+"/"+dateTM+"/"+(parseInt(this.startDate.split("/")[1])-543);
       
      
      }
    });
    $("#calendar1").calendar({
      startCalendar: $("#calendar"),
      maxDate: new Date(),
      type: "month",
      text: TextDateTH,
      formatter: formatter('ดป'),
      onChange: (date,text) => {
        let yyyy = text.split(" ")[1];
        this.endDate =  (date.getMonth()<9)?"0"+(date.getMonth()+1)+"/"+yyyy:(date.getMonth()+1)+"/"+yyyy;

        let ed = new Date((parseInt(this.endDate.split("/")[1])-543) + "-" + (parseInt(this.endDate.split("/")[0])));
        ed.setMonth(ed.getMonth()+1);
        ed.setDate(ed.getDate()-1);
      
        console.log("ed : ",ed);
        let dateTM = (ed.getDate()<10)?"0"+(ed.getDate()):ed.getDate();
        this.endDateTM=this.endDate.split("/")[0]+"/"+dateTM+"/"+(parseInt(this.endDate.split("/")[1])-543);
      }
    });
  }

  clickSearch = function () {
    $("#searchFlag").val("TRUE");
   this.dataTable();
  }

  dataTable = function(){
    if ($('#tableData').DataTable() != null) {$('#tableData').DataTable().destroy();};
    var table = $('#tableData').DataTableTh({
      "lengthChange":true,
      "serverSide": false,
      "searching": false,
      "ordering": false,
      "processing": true,
      "scrollX": true,    

      "ajax" : {
        "url" : '/ims-webapp/api/ia/int085/list',
        "contentType": "application/json",
        "type" : "POST",
        "data" : (d) => {
          return JSON.stringify($.extend({}, d, {
            "searchFlag" : $("#searchFlag").val(),
            "startDate" : this.startDate,
            "endDate"  : this.endDate,
            "startDateTM" : this.startDateTM,
            "endDateTM"  : this.endDateTM,
            "billLost"  : $("#billLost").val()
            
          }));
        },  
      },
      "columns": [
        {
          "data": "officeCode","className":"center"
        }, {
          "data": "officeName"
        }, {
          "data": "startDate","className":"center"
        }, {
          "data": "endDate","className":"center"
        }, {
          "data": "riskNumber","className":"center"
        }, {
          "data": "riskList"
        }, {
          "data": "statusM","className":"center",
          "render": function (data, type, row, meta) {
            let s = '';
            if(data=='Y'){
              s = 'ผ่าน';
            }else{
              s = 'รอดำเนินการ';
            }
            return s;
          }
        }
      ]
    });
  }

}

