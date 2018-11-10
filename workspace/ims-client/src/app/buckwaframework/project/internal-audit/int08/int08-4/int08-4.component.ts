import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Location } from "@angular/common";
import { AjaxService } from "../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { BaseModel, ManageReq, BreadCrumb } from 'models/index';
import { TextDateTH, formatter } from '../../../../common/helper/datepicker';
import { AuthService } from "services/auth.service";
import { async } from "@angular/core/testing";
declare var $: any;
@Component({
  selector: "int08-4",
  templateUrl: "./int08-4.component.html",
  styleUrls: ["./int08-4.component.css"]
})
export class Int084Component implements OnInit {
  startDate:any="";
  endDate:any="";
  startDateTM:any;
  endDateTM:any;
  int084VoList:any=[];
  idHead:any;

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
      { label: "การประเมินความเสี่ยงรายได้", route: "#" },
    ];
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-08400');
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

    if (this.startDate==""||this.endDate=="") {
      this.messageBarService.alert("กรุณาระบุ ระยะเวลาที่ตรวจสอบ");
      return false;
    }
   
    if ($("#billLost").val()<0||$("#billLost").val()>100||$("#billLost").val()=="") {
      this.messageBarService.alert("จำนวนเปอร์เซ้นใบเสร็จเสีย ไม่ถูกต้อง");
      return false;
    }
    $("#searchFlag").val("TRUE");
   this.dataTable();
  }

  dataTable = () => {
    if ($('#tableData').DataTable() != null) {$('#tableData').DataTable().destroy();};
    this.int084VoList = [];
    var table = $('#tableData').DataTableTh({
      "serverSide": false,
      "searching": false,
      "processing": true,
      "ordering": false,
      "scrollX": true,    

      "ajax" : {
        "url" : '/ims-webapp/api/ia/int084/list',
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
          "data": "officeCode","className":"center","orderable": false
        }, {
          "data": "officeName"
        }, {
          "data": "startDate","className":"center"
        }, {
          "data": "endDate","className":"center"
        }, {
          "data": "billAll","className":"right"
        }, {
          "data": "billWaste","className":"right"
        }, {
          "data": "riskNumber","className":"center"
        }, {
          "data": "riskRemark"
        }
      ],"createdRow": ( row, data, dataIndex )=> {
        this.int084VoList.push(data);
        this.idHead = data.idHead;
      }
    });
  }



  exportFile=()=>{
    let param = "";
    let url = "/ia/int084/exportFile";
    
    param +="?idHead=" + this.idHead;
    param +="&startDateTM=" + this.startDateTM;
    param +="&endDateTM=" + this.endDateTM;

    console.log("idHead : ",this.idHead);
    this.ajax.download(url+param);
    
  }

  saveData=()=>{
    let url = "/ia/int084/save";
    this.ajax.post(url,this.int084VoList, res => {        
      const msg = res.json();
      console.log("exportFile");
      this.exportFile();
    });
  }

}

