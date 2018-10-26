import { Component, OnInit } from "@angular/core";
import { TextDateTH, formatter ,stringToDate} from "../../../../common/helper/datepicker";
import { AjaxService, MessageBarService, AuthService } from "../../../../common/services";
import { TravelCostHeader } from "../../../../common/models";
import { Router, ActivatedRoute } from "@angular/router";
import { TravelCostDetail } from "app/buckwaframework/common/models/travelcostdetail";
import { IaService } from 'app/buckwaframework/common/services/ia.service';
import { BreadCrumb } from 'models/index';


declare var $: any;
@Component({
  selector: "app-cop07-1",
  templateUrl: "./cop07-1.component.html",
  styleUrls: ["./cop07-1.component.css"]
})
export class Cop071Component implements OnInit {

  searchFlag: String;
  breadcrumb: BreadCrumb[];

  totalAsPlanNumber:Number=0;
  totalAsPlanSuccess:Number=0;
  totalAsPlanWait:Number=0;
  totalOutsidePlanNumber:Number=0;
  totalOutsidePlanSuccess:Number=0;
  totalOutsidePlanWait:Number=0;   

  constructor(
    private message: MessageBarService,
    private ajax: AjaxService,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
    private iaService: IaService,
    private msg: MessageBarService
  ) {
    this.breadcrumb = [
      { label: "ตรวจปฏิบัติการ", route: "#" },
      { label: "แผนการตรวจปฏิบัติการประจำปีงบประมาณ", route: "#" }
    ];
    
   }
  calenda = function () {
   
    $("#date").calendar({
      type: "year",
      text: TextDateTH,
      formatter: formatter("ป")
    });
  }

  clickSearch = function () {
    $("#searchFlag").val("TRUE");
    this.setSum0();
    $('#tableData').DataTable().ajax.reload();
  }

  dataTable = function () {
    var table = $('#tableData').DataTable({
      "lengthChange": false,
      "paging": false,
      "info": false,
      "serverSide": true,
      "searching": false,
      "ordering": false,
      "processing": true,
      "scrollX": true,
      "ajax": {
        "url": '/ims-webapp/api/cop/cop071/list',
        "contentType": "application/json",
        "type": "POST",
        "data": (d) => {
          return JSON.stringify($.extend({}, d, {
            "searchFlag": $("#searchFlag").val(),
            "fiscalYear": $("#fiscalYear").val()
          }));
        },
      },

      "columns": [
        {
          "data": "fiscalYearText"
        }, {
          "data": "asPlanNumber","className":"right",
          "render": function (data, type, row) {
            return (data==null)?'-':data;
          }
        }, {
          "data": "asPlanSuccess","className":"right",
          "render": function (data, type, row) {
            return (data==null)?'-':data;
          }
        }, {
          "data": "asPlanWait","className":"right",
          "render": function (data, type, row) {
            return (data==null)?'-':data;
          }
        }, {
          "data": "outsidePlanNumber","className":"right",
          "render": function (data, type, row) {
            return (data==null)?'-':data;
          }
        }, {
          "data": "outsidePlanSuccess","className":"right",
          "render": function (data, type, row) {
            return (data==null)?'-':data;
          }
        }, {
          "data": "outsidePlanWait","className":"right",
          "render": function (data, type, row) {
            return (data==null)?'-':data;
          }
        }, {
          "data": "fiscalYear",
          "render": function (data, type, row) {
            var btn = '';
            btn += '<button class="mini ui yellow button btn-edit"><i class="edit icon"></i>แก้ไข</button>';
            btn += '<button class="mini ui primary button btn-description"><i class="eye icon"></i>รายละเอียด</button>';
           
            return btn;
          }
        }
      ],
      "rowCallback": this.sum
    });

    //button description>
    table.on('click', 'tbody tr button.btn-description', (e) => {
      var closestRow = $(e.target).closest('tr');
      var data = table.row(closestRow).data();
      this.router.navigate(['/cop07/1/1'], {
        queryParams: {
          id:data.id,
          fiscalYear:data.fiscalYear,
          asPlanNumber:data.asPlanNumber,
          outsidePlanNumber:data.outsidePlanNumber
        }
      });
      console.log(data);
    });

    table.on('click', 'tbody tr button.btn-edit',(e)=> {
      var closestRow = $(e.target).closest('tr');
      var data = table.row(closestRow).data();
      this.modalEdit(data);
    });

  }
  sum = ( row, data ) => {
    console.log("data : ",data);
    this.totalAsPlanNumber+=data.asPlanNumber;
    this.totalAsPlanSuccess+=data.asPlanSuccess;
    this.totalAsPlanWait+=data.asPlanWait;
    this.totalOutsidePlanNumber+=data.outsidePlanNumber;
    this.totalOutsidePlanSuccess+=data.outsidePlanSuccess;
    this.totalOutsidePlanWait+=data.outsidePlanWait;
  
  }

  setSum0 = () =>{
    this.totalAsPlanNumber=0;
    this.totalAsPlanSuccess=0;
    this.totalAsPlanWait=0;
    this.totalOutsidePlanNumber=0;
    this.totalOutsidePlanSuccess=0;
    this.totalOutsidePlanWait=0;
  }

modalEdit=(data)=> {
    console.log("data edit : ",data);
   
    $('#modalEdit').modal({
      onShow: ()=>{
         this.calenda();
         $("#id").val(data.id);
         $("#fiscalYearEdit").val(data.fiscalYear);
         $("#asPlanNumber").val(data.asPlanNumber);
         $("#asPlanSuccess").val(data.asPlanSuccess);
         $("#outsidePlanNumber").val(data.outsidePlanNumber);
         $("#outsidePlanSuccess").val(data.outsidePlanSuccess);

      }
    }).modal('show');
   
  }

  editData() {
    console.log("Edit");
    $('modalEdit').modal('hide');
    const URL = "cop/cop071/edit";
    this.ajax.post(URL, { 
      cop071Vo:{
        id:(!$("#id").val())?0:$("#id").val(),
        fiscalYear:$("#fiscalYearEdit").val(),
        asPlanNumber:$("#asPlanNumber").val(),
        asPlanSuccess:(!$("#asPlanSuccess").val())?0:$("#asPlanSuccess").val(),
        outsidePlanNumber:$("#outsidePlanNumber").val(),
        outsidePlanSuccess:(!$("#outsidePlanSuccess").val())?0:$("#outsidePlanSuccess").val()
      }
     
    },res => {
      const commonMessage = res.json();
      
    if (commonMessage.msg.messageType == "C") {
      this.msg.successModal(commonMessage.msg.messageTh);
    } else {
      this.msg.errorModal(commonMessage.msg.messageTh);
    }
    $("#searchFlag").val("TRUE");
    this.setSum0();
    $('#tableData').DataTable().ajax.reload();
    });
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-07100');
    this.dataTable();
    this.calenda();
    
  }

}
