import { Component, OnInit, AfterViewInit, OnDestroy } from "@angular/core";
import {
  TravelCostHeader,
  TravelCostDetail,
  Contract
} from "../../../../../common/models";
import { AjaxService, MessageBarService } from "../../../../../common/services";
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
declare var $: any;
@Component({
  selector: "app-int09-1-1",
  templateUrl: "./int09-1-1.component.html",
  styleUrls: ["./int09-1-1.component.css"]
})
export class Int0911Component implements OnInit, OnDestroy {

  searchFlag: String;
  documentTypeList:any;
  idProcess:any;
  head:any;
  fileUpload:File[];

  pickedType:any;
  fiscalYear:any;
  departureDate:any;
  returnDate:any;
  travelToDescription:any;

  z


  constructor(
    private message: MessageBarService,
    private ajax: AjaxService,
    private router: Router,
    private route: ActivatedRoute,
    private travelService: TravelService
  ) {

    this.fileUpload = new Array<File>(); // initial file array
  }

  documentTypeDropdown = () =>{
    const URL = "combobox/controller/getDropByTypeAndParentId";
    this.ajax.post(URL, { type: "ACC_FEE", lovIdMaster: 1171 }, res => {
      this.documentTypeList = res.json();
      console.log(this.documentTypeList);
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
        "url" : '/ims-webapp/api/ia/int0911/list',
        "contentType": "application/json",
        "type" : "POST",
        "data" : (d) => {
          return JSON.stringify($.extend({}, d, {
            "searchFlag" : "TRUE",
            "idProcess" : this.idProcess
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
          "data": "createdDate",
          "className": "ui center aligned"
        }, {
          "data": "createdBy"
        }, {
          "data": "documentType"
        }, {
          "data": "subject"
        }, {
          "data": "status",
          "className": "ui center aligned",
          "render": function (data, type, row) {
            var s = '';

            if (data == 1196) {
              s = 'ไม่อนุมัติ';
            } else if (data == 1195) {
              s = 'อนุมัติ';
            }else {
              s = 'รออนุมัติ';
            }
            return s;
          }
        },{
          "data": "id",
          "className": "ui center aligned",
          "render" : function(data,type,row){
            var btn = '';
            btn +='<button class="mini ui primary button btn-download">ดาวน์โหลด</button>';
            btn +='<button class="mini ui red button btn-delete">ลบ</button>';
            btn +='<button class="mini ui green button btn-approve">อนุมัติ</button>';
            btn +='<button class="mini ui red button btn-unapproved">ไม่อนุมัติ</button>';
              return btn;
          }
        }
      ]
    });

    //button download>
    table.on('click', 'tbody tr button.btn-download', (e) => {
      var closestRow = $(e.target).closest('tr');
      var data = table.row(closestRow).data();
      this.router.navigate(['/int09/1/1'], {
        queryParams: {idProcess:data.id}
      });
      console.log(data);
    });
    //button delete>
    table.on('click', 'tbody tr button.btn-delete', (e) => {
      var closestRow = $(e.target).closest('tr');
      var data = table.row(closestRow).data();
     
      const URL = "ia/int0911/delete";
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

    table.on('click', 'tbody tr button.btn-approve', (e) => {
      var closestRow = $(e.target).closest('tr');
      var data = table.row(closestRow).data();
      const URL = "ia/int0911/approve";
      this.message.comfirm((res) => {
        if(res){
      
      this.ajax.post(URL, {id:data.id,approve:"1195"}, res => {        
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
    },"อนุมัติ");
    });
    table.on('click', 'tbody tr button.btn-unapproved', (e) => {
      var closestRow = $(e.target).closest('tr');
      var data = table.row(closestRow).data();
      const URL = "ia/int0911/approve";
      this.message.comfirm((res) => {
        if(res){
      
      this.ajax.post(URL, {id:data.id,approve:"1196"}, res => {        
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
    },"ไม่อนุมัติ");
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
        "url" : '/ims-webapp/api/ia/int0911/list2',
        "contentType": "application/json",
        "type" : "POST",
        "data" : (d) => {
          return JSON.stringify($.extend({}, d, {
            "searchFlag" : "TRUE",
            "idProcess" : this.idProcess
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
          "data": "createdDate",
          "className": "ui center aligned"
        }, {
          "data": "createdBy"
        }, {
          "data": "documentName"
        }, {
          "data": "documantSize",
          "className": "ui right aligned"
        }, {
          "data": "status",
          "className": "ui center aligned",
          "render": function (data, type, row) {
            var s = '';
            if (data == 1196) {
              s = 'ไม่อนุมัติ';
            } else if (data == 1195) {
              s = 'อนุมัติ';
            }else {
              s = 'รออนุมัติ';
            }
            return s;
          }
        },{
          "data": "id",
          "className": "ui center aligned",
          "render" : function(data,type,row){
            var btn = '';
            btn +='<button class="mini ui primary button btn-download">ดาวน์โหลด</button>';
            btn +='<button class="mini ui red button btn-delete">ลบ</button>';
            btn +='<button class="mini ui green button btn-approve">อนุมัติ</button>';
            btn +='<button class="mini ui red button btn-unapproved">ไม่อนุมัติ</button>';
              return btn;
          }
        }
      ]
    });

    //button download>
    table2.on('click', 'tbody tr button.btn-download', (e) => {
      var closestRow = $(e.target).closest('tr');
      var data = table2.row(closestRow).data();
      this.router.navigate(['/int09/1/1'], {
        queryParams: {idProcess:data.id}
      });
      console.log(data);
    });
    //button delete>
    table2.on('click', 'tbody tr button.btn-delete', (e) => {
      var closestRow = $(e.target).closest('tr');
      var data = table2.row(closestRow).data();
     
      const URL = "ia/int0911/deleteT2";
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
    table2.on('click', 'tbody tr button.btn-approve', (e) => {
      var closestRow = $(e.target).closest('tr');
      var data = table2.row(closestRow).data();
      const URL = "ia/int0911/approveT2";
      this.message.comfirm((res) => {
        if(res){
      
      this.ajax.post(URL, {id:data.id,approve:"1195"}, res => {        
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
    },"อนุมัติ");
    });
    table2.on('click', 'tbody tr button.btn-unapproved', (e) => {
      var closestRow = $(e.target).closest('tr');
      var data = table2.row(closestRow).data();
      const URL = "ia/int0911/approveT2";
      this.message.comfirm((res) => {
        if(res){
      
      this.ajax.post(URL, {id:data.id,approve:"1196"}, res => {        
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
    },"ไม่อนุมัติ");
    });
    
  
  }
  modalAddDocument (){
    $('#modalAddDocument').modal('show');
  }
  addDocument (){
   console.log("Add Document : True");
   $('#modalAddDocument').modal('hide');
    if($('#documentType').val()==1172){
      this.router.navigate(['/int09/1/1/1'], {
        queryParams: {idProcess:this.idProcess}
      });
    }else if($('#documentType').val()==1173){
      this.router.navigate(['/int09/1/1/2'], {
        queryParams: {idProcess:this.idProcess}
      });
    }else if($('#documentType').val()==1174){
      this.router.navigate(['/int09/1/1/3'], {
        queryParams: {idProcess:this.idProcess}
      });
    }else if($('#documentType').val()==1175){
      this.router.navigate(['/int09/1/1/4'], {
        queryParams: {idProcess:this.idProcess}
      });
    }else if($('#documentType').val()==1176){
      this.router.navigate(['/int09/1/1/5'], {
        queryParams: {idProcess:this.idProcess}
      });
    }
   
  }
  clickBack(){
    this.router.navigate(['/int09/1']);
  }

  getHead = () =>{
  
    const URL = "ia/int0911/gethead";
    this.ajax.post(URL, { idProcess: this.idProcess}, res => {
      this.head = res.json();
      console.log("Head : ",this.head);

      this.pickedType = (this.head.pickedType==1162)?'ก่อนเดินทาง':'หลังเดินทาง';
      this.fiscalYear = this.head.fiscalYear;
      this.departureDate = this.head.departureDate;
      this.returnDate = this.head.returnDate;
      this.travelToDescription = this.head.travelToDescription;
    });
  }
  onUpload = (event: any) => {
    // Prevent actual form submission
    event.preventDefault();

    //send form data
    const form = $("#upload-form")[0];
    var formBody = new FormData(form);
      formBody.append("idProcess",this.idProcess);
    

    const url = "ia/int0911/uploadFile";
    this.ajax.upload(
      url,
      formBody,
      res => {   
        const msg = res.json();
        if (msg.messageType == "C") {
          this.message.successModal(msg.messageTh);
        } else {
          this.message.errorModal(msg.messageTh);
        }
        $("#searchFlag").val("TRUE");
        $('#tableData2').DataTable().ajax.reload();
      },
      err => {
        this.message.errorModal(
          "ไม่สามารถอัพโหลดข้อมูลได้",
          "เกิดข้อผิดพลาด"
        );
      }
    );
  };
  onChangeUpload = (event: any) => {
    if (event.target.files && event.target.files.length > 0) {
      var reader = new FileReader();

      reader.onload = (e: any) => {
        const f = {
          name: event.target.files[0].name,
          type: event.target.files[0].type,
          value: e.target.result
        };
        this.fileUpload = [f];
      };
      reader.readAsDataURL(event.target.files[0]);
    }
  };

  ngOnDestroy() {
    $('#modalAddDocument').remove();
  }

  
  ngOnInit() {
    this.idProcess = this.route.snapshot.queryParams["idProcess"];
    console.log("idProcess : ",this.idProcess);
    this.getHead();
    this.documentTypeDropdown();
    this.dataTable();
    this.dataTable2();
  }
  
}
