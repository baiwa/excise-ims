import { Component, OnInit, AfterViewInit, OnDestroy } from "@angular/core";
import {
  TravelCostHeader,
  TravelCostDetail,
  Contract
} from "../../../../../common/models";
import { AjaxService, MessageBarService, AuthService } from "../../../../../common/services";
import { Prices } from "../../../../../common/helper/travel";
import { Router, ActivatedRoute } from "@angular/router";
import {
  digit,
  numberWithCommas,
  TextDateTH,
  formatter,
  DecimalFormat
} from "../../../../../common/helper";
import { TravelService } from "../../../../../common/services/travel.service";
import { Headers } from "@angular/http/src/headers";
import { File } from "app/buckwaframework/common/models/file";
import { BreadCrumb } from 'models/index';

declare var $: any;
@Component({
  selector: "app-cop07-1-1",
  templateUrl: "./cop07-1-1.component.html",
  styleUrls: ["./cop07-1-1.component.css"]
})
export class Cop0711Component implements OnInit, OnDestroy {

  searchFlag: String;
  fileUpload:File[];

  id:any;
  fiscalYear:any;
  fiscalYearText:any;
  asPlanNumber:any;
  asPlanSuccess:any;
  outsidePlanNumber:any;
  outsidePlanSuccess:any;


  entrepreneurNoList:any;
  actionPlanList:any;

  btnModal:any;
  breadcrumb: BreadCrumb[];


  constructor(
    private message: MessageBarService,
    private ajax: AjaxService,
    private router: Router,
    private authService: AuthService,
    private route: ActivatedRoute,
    private travelService: TravelService
  ) {
    this.breadcrumb = [
      { label: "ตรวจปฏิบัติการ", route: "#" },
      { label: "แผนการตรวจปฏิบัติการประจำปีงบประมาณ", route: "#" },
      { label: "รายละเอียดแผนปฏิบัติการ", route: "#" }
 
    ];

    this.fileUpload = new Array<File>(); // initial file array
  }

  
  ngOnInit() {
    this.authService.reRenderVersionProgram('COP-07110');

    this.id = this.route.snapshot.queryParams["id"];
    this.fiscalYear = this.route.snapshot.queryParams["fiscalYear"];
    this.fiscalYearText = TextDateTH.months[parseInt(this.fiscalYear.split("/")[0])-1] +" "+ this.fiscalYear.split("/")[1];

    this.asPlanNumber = this.route.snapshot.queryParams["asPlanNumber"];
    this.asPlanSuccess= this.route.snapshot.queryParams["asPlanSuccess"];
    this.outsidePlanNumber = this.route.snapshot.queryParams["outsidePlanNumber"];
    this.outsidePlanSuccess= this.route.snapshot.queryParams["outsidePlanSuccess"];
    this.entrepreneurNoDropdown();
    this.actionPlanDropdown();
    this.getHead();
    this.dataTable();
    this.dataTable2();
  }

  ngOnDestroy() {
    $('#modalAddDocument').remove();
  }

  calenda = function () {
    let promise = new Promise((resolve, reject) => {
      $("#modalDate1").calendar('clear');
      setTimeout(() => resolve(true), 200);
    });
    promise.then(resolve => {
      if (resolve) {
        $("#modalDate1").calendar({
          type: "date",
          text: TextDateTH,
          formatter: formatter()
        });
      }
    });
  }

  entrepreneurNoDropdown = () =>{
  
    const URL = "combobox/controller/getExciseId";
    this.ajax.post(URL, {}, res => {
      this.entrepreneurNoList = res.json();
    });
  }

  actionPlanDropdown = () =>{
  
    const URL = "combobox/controller/getDropByTypeAndParentId";
    this.ajax.post(URL, { type: "CHECK_OPERATION",lovIdMaster: 1870}, res => {
      this.actionPlanList = res.json();
    });
  }

  modalAdd() {
    this.btnModal = 'S';
    $('#modalAdd').modal({
      onShow: ()=>{
        this.calenda();
      }
    }).modal('show');
    
  }

  modalEdit=(data)=> {
    console.log("data edit : ",data);

    this.btnModal = 'E';
    $('#modalAdd').modal({
      onShow: ()=>{
         this.calenda();
         $("#id").val(data.id);
         $("#entrepreneurNo").dropdown('set selected',data.entrepreneurNo);
         $("#checkDate").val(data.checkDate);
         $("#actionPlan").dropdown('set selected',data.actionPlan);
      }
    }).modal('show');
   
  }

  saveData() {
    if ($("#actionPlan").val()=='1871'&&$('#tableData').DataTable().column(0).data().length>=this.asPlanNumber) {
      this.message.alert("ไม่สามารถเพิ่มจำนวน ตามแผนปฏิบัติการ   เกิน "+this.asPlanNumber+" ราย");
      return false;
    }

    if ($("#actionPlan").val()=='1872'&&$('#tableData2').DataTable().column(0).data().length>=this.outsidePlanNumber) {
      this.message.alert("ไม่สามารถเพิ่มจำนวน นอกแผนปฏิบัติการ   เกิน "+this.outsidePlanNumber+" ราย");
      return false;
    }

    console.log("Save");
    $('modalAdd').modal('hide');
    const URL = "cop/cop0711/save";
    this.ajax.post(URL, { cop0711Vo:{
      idMaster: this.id,
      fiscalyear:this.fiscalYear,
      entrepreneurNo: $("#entrepreneurNo").val(),
      checkDate: $("#checkDate").val(),
      actionPlan: $("#actionPlan").val()
      }
    },res => {
      const commonMessage = res.json();
      
    if (commonMessage.msg.messageType == "C") {
      this.message.successModal(commonMessage.msg.messageTh);
      console.log("commonMessage.data : ",commonMessage.data);
    } else {
      this.message.errorModal(commonMessage.msg.messageTh);
    }
    $("#searchFlag").val("TRUE");
    $('#tableData').DataTable().ajax.reload();
    $('#tableData2').DataTable().ajax.reload();
    });
  }

  editData() {
    console.log("Edit");
    $('modalAdd').modal('hide');
    const URL = "cop/cop0711/edit";
    this.ajax.post(URL, { cop0711Vo:{
      id:$("#id").val(),
      entrepreneurNo:$("#entrepreneurNo").val(),
      checkDate:$("#checkDate").val(),
      actionPlan:$("#actionPlan").val()
     }
    },res => {
      const commonMessage = res.json();
      
    if (commonMessage.msg.messageType == "C") {
      this.message.successModal(commonMessage.msg.messageTh);
      console.log("commonMessage.data : ",commonMessage.data);
    } else {
      this.message.errorModal(commonMessage.msg.messageTh);
    }
    $("#searchFlag").val("TRUE");
    $('#tableData').DataTable().ajax.reload();
    $('#tableData2').DataTable().ajax.reload();
    });
  }

  dataTable = function(){
    var table = $('#tableData').DataTable({
      "lengthChange":true,
      "serverSide": false,
      "searching": false,
      "ordering": false,
      "processing": true,
      "scrollX": true,      
      "ajax" : {
        "url" : '/ims-webapp/api/cop/cop0711/list',
        "contentType": "application/json",
        "type" : "POST",
        "data" : (d) => {
          return JSON.stringify($.extend({}, d, {
            "searchFlag" : "TRUE",
            "id" : this.id
          }));
        },  
      },
      "columns": [
        {
          "data": "id",
          "className": "ui center aligned",
          "render": function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          }
        }, {
          "data": "entrepreneurNo","className": "ui center aligned"
        }, {
          "data": "entrepreneurName","className": "ui center aligned"
        }, {
          "data": "entrepreneurLoca","className": "ui center aligned"
        }, {
          "data": "checkDate","className": "ui center aligned"
        }, {
          "data": "actionPlan","className": "ui center aligned",
          "render": function (data, type, row, meta) {
            let s = '';
            if(data=='1871'){
              s = 'ตามแผนปฏิบัติการ';
            }else{
              s = 'นอกแผนปฏิบัติการ';
            }
            return s;
          }
        }, {
          "data": "status","className": "ui center aligned",
          "render": function (data, type, row, meta) {
            let s = '';
            if(data=='1874'){
              s = 'เสร็จสิ้น';
            }else{
              s = 'รอการดำเนินการ';
            }
            return s;
          }
        },{
          "data": "id",
          "className": "ui center aligned",
          "render" : function(data,type,row){
            var btn = '';
            btn += '<button class="mini ui yellow button btn-edit" type="button" ><i class="edit icon"></i>แก้ไข</button>';
            btn +='<button class="mini ui red button btn-delete" type="button" ><i class="trash alternate icon"></i>ลบ</button>';
              return btn;
          }
        }
      ]
    });
  //button edit>
  table.on('click', 'tbody tr button.btn-edit',(e)=> {
    var closestRow = $(e.target).closest('tr');
    var data = table.row(closestRow).data();
    this.modalEdit(data);
  });
    //button delete>
    table.on('click', 'tbody tr button.btn-delete', (e) => {
      var closestRow = $(e.target).closest('tr');
      var data = table.row(closestRow).data();
     
      const URL = "cop/cop0711/delete";
      this.message.comfirm((res) => {
        if(res){
      
      this.ajax.post(URL, {id:data.id}, res => {        
        const msg = res.json();
      if (msg.messageType == "C") {
        this.message.successModal(msg.messageTh);
      } else {
        this.message.errorModal(msg.messageTh);
      }
      $("#searchFlag").val("TRUE");
      $('#tableData').DataTable().ajax.reload();
      });
     }
    },"ลบรายการ");
      
    });
  }

  dataTable2 = function(){
    var table2 = $('#tableData2').DataTable({
      "lengthChange":true,
      "serverSide": false,
      "searching": false,
      "ordering": false,
      "processing": true,
      "scrollX": true,      
      "ajax" : {
        "url" : '/ims-webapp/api/cop/cop0711/list2',
        "contentType": "application/json",
        "type" : "POST",
        "data" : (d) => {
          return JSON.stringify($.extend({}, d, {
            "searchFlag" : "TRUE",
            "id" : this.id
          }));
        },  
      },
      "columns": [
        {
          "data": "id",
          "className": "ui center aligned",
          "render": function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          }
        }, {
          "data": "entrepreneurNo","className": "ui center aligned"
        }, {
          "data": "entrepreneurName","className": "ui center aligned"
        }, {
          "data": "entrepreneurLoca","className": "ui center aligned"
        }, {
          "data": "checkDate","className": "ui center aligned"
        }, {
          "data": "actionPlan","className": "ui center aligned",
          "render": function (data, type, row, meta) {
            let s = '';
            if(data=='1871'){
              s = 'ตามแผนปฏิบัติการ';
            }else{
              s = 'นอกแผนปฏิบัติการ';
            }
            return s;
          }
        }, {
          "data": "status","className": "ui center aligned",
          "render": function (data, type, row, meta) {
            let s = '';
            if(data=='1874'){
              s = 'เสร็จสิ้น';
            }else{
              s = 'รอการดำเนินการ';
            }
            return s;
          }
        },{
          "data": "id",
          "className": "ui center aligned",
          "render" : function(data,type,row){
            var btn = '';
            btn += '<button class="mini ui yellow button btn-edit" type="button" ><i class="edit icon"></i>แก้ไข</button>';
            btn +='<button class="mini ui red button btn-delete" type="button" ><i class="trash alternate icon"></i>ลบ</button>';
              return btn;
          }
        }
      ]
    });
  //button edit>
  table2.on('click', 'tbody tr button.btn-edit',(e)=> {
    var closestRow = $(e.target).closest('tr');
    var data = table2.row(closestRow).data();
    this.modalEdit(data);
  });
    //button delete>
    table2.on('click', 'tbody tr button.btn-delete', (e) => {
      var closestRow = $(e.target).closest('tr');
      var data = table2.row(closestRow).data();
     
      const URL = "cop/cop0711/delete";
      this.message.comfirm((res) => {
        if(res){
      
      this.ajax.post(URL, {id:data.id}, res => {        
        const msg = res.json();
      if (msg.messageType == "C") {
        this.message.successModal(msg.messageTh);
      } else {
        this.message.errorModal(msg.messageTh);
      }
      $("#searchFlag").val("TRUE");
      $('#tableData2').DataTable().ajax.reload();
      });
     }
    },"ลบรายการ");
      
    });
    
  }
  modalAddDocument (){
    $('#modalAddDocument').modal('show');
  }

  clickBack(){
    this.router.navigate(['/cop07/1']);
  }

  getHead = () =>{
  
 
  }

}