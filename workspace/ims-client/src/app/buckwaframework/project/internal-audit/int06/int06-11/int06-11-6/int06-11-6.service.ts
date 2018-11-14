import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ComboBox } from "models/combobox";
import { AjaxService } from "services/ajax.service";
import { Utils } from "helpers/utils";
import { utils } from "protractor";
import { MessageBarService } from "services/message-bar.service";
import { resolve, reject } from "q";
import { Router, ActivatedRoute } from "@angular/router";

const URL = {
  DROPDOWN: "combobox/controller/getDropByTypeAndParentId",
  SEARCH: AjaxService.CONTEXT_PATH + "ia/int061106/search",
  APPROVE: "ia/int061106/approve"
};

declare var $: any;
@Injectable()
export class Int06116Service {
  dataTable: any;
  formData: any;
  idToWithdrawRequest: any;
  dataApprove:any=[];
  
  constructor(
    private ajax: AjaxService, 
    private msg: MessageBarService,
    private router: Router) {
    this.formData = {
      withdrawRequest: "",
      dataApprove:[]
    };
  }

  dropdown = (type: string, id?: number): Observable<any> => {
    const DATA = { type: type, lovIdMaster: id || null };
    return new Observable<ComboBox[]>(obs => {
      this.ajax
        .post(URL.DROPDOWN, DATA, res => {
          this[type] = res.json();
        })
        .then(() => {
          obs.next(this[type]);
        });
    });
  };

  search(formData: any) {
    this.formData = {
      withdrawRequest: formData.withdrawRequest
    };
    this.idToWithdrawRequest = formData.withdrawRequest;
    this.Datatable();
  }

  Datatable(): void {
    if (this.dataTable != null || this.dataTable != undefined) {
      this.dataTable.destroy();
    }

    //render check number is null or empty
    let renderNumber = function(data, type, row, meta) {
      return Utils.isNull($.trim(data))
        ? "-"
        : $.fn.dataTable.render.number(",", ".", 2, "").display(data);
    };

    this.dataTable = $("#dataTable").DataTableTh({
      lengthChange: true,
      searching: false,
      ordering: false,
      processing: true,
      serverSide: false,
      paging: true,
      scrollX: true,
      ajax: {
        type: "POST",
        url: URL.SEARCH,
        data: this.formData
      },
      columns: [  
        { data: "id" },
        { data: "billLading" },
        { data: "createdBy" },
        // { data: "position" },
        // { data: "affiliation" },
        { data: "createdDateStr",  className:"center"},
        { data: "amount",render: renderNumber, className:"right"},
        { data: "requestType",
          render: function(data) {
            var requestType = "";
            if (data === "2058") {
              requestType = "แบบขอเบิกเงินค่าเช่าบ้าน (แบบ 6006)";
            } else if (data === "2059") {
              requestType = "ใบเบิกเงินสวัสดิการเกี่ยวกับการรักษาพยาบาล (แบบ 7131)";
            } else if (data === "2060") {
              requestType = "ใบเบิกเงินสวัสดิการเกี่ยวกับการศึกษาบุตร (แบบ 7223)";
            } else {
              requestType = "-";
            }
            return requestType;
          }
        },
        { data: "status",
        render: function(data) {
          var status = "";
          if (data === "2062") {
            status = "อนุมัติขอเบิก (รอเลขอ้างอิงขอเบิก)";
          } else if (data === "2063") {
            status = "รอเลขอ้างอิงขอจ่าย";
          } else if (data === "2064") {
            status = "รออนุมัติขอจ่าย";
          } else if (data === "2065") {
            status = "อนุมัติขอจ่าย";
          } else if (data === "2066") {
            status = "ไม่อนุมัติขอจ่าย";
          } else {
            status = "-";
          }
          return status;
        }
        },{
          data: "status",
          className:"center",
          render: function(data, type, full, meta) {
            var btn = '';

            if (data === "2062") {
              // btn += `<button type="button" class="ui mini button primary btn-description"><i class="edit icon"></i>รายละเอียด</button>`;
              btn += `<button type="button" class="ui mini button yellow btn-edit"><i class="edit icon"></i>แก้ไขเลขที่ใบขอเบิก</button>`;

            } else if (data === "2063") {
              // btn += `<button type="button" class="ui mini button primary btn-description"><i class="edit icon"></i>รายละเอียด</button>`;
              btn += `<button type="button" class="ui mini button yellow btn-edit"><i class="edit icon"></i>แก้ไขเลขที่ใบขอเบิก</button>`;
              btn += `<button type="button" class="ui mini button green btn-add"><i class="plus icon"></i>ทำใบขอจ่าย</button>`;

            }

            return btn;
          }
        }
      ]
    });

    this.dataTable.on('click', 'tbody tr button.btn-edit',(e)=> {
      var closestRow = $(e.target).closest('tr');
      var data = this.dataTable.row(closestRow).data();
      this.modalEdit(data);
    });
    this.dataTable.on('click', 'tbody tr button.btn-add',(e)=> {
      var closestRow = $(e.target).closest('tr');
      var data = this.dataTable.row(closestRow).data();
      this.modalAdd(data);
    });
    this.dataTable.on('click', 'tbody tr button.btn-approve',(e)=> {
      var closestRow = $(e.target).closest('tr');
      var data = this.dataTable.row(closestRow).data();
      this.approve(data);
      $("#nameCall").click();
    });
    this.dataTable.on('click', 'tbody tr button.btn-description',(e)=> {
      var closestRow = $(e.target).closest('tr');
      var data = this.dataTable.row(closestRow).data();
      this.router.navigate(['/int06/11/7'], {
        queryParams: {
          id:data.id,
          withdrawRequest:this.formData.withdrawRequest

        }
      });
     
    });

    
  }
  modalEdit=(data)=> {
    console.log("data edit : ",data);
    $('#modalEdit').modal({
      onShow: ()=>{
         $("#id").val(data.id);
         $("#billLading").val(data.billLading);
      }
    }).modal('show');
  }

  modalAdd=(data)=> {
    console.log("data add : ",data);
    $('#modalAdd').modal({
      onShow: ()=>{
         $("#id").val(data.id);
         $("#billPay").val(data.billPay);
         $("#amountPay").val(data.amountPay);
      }
    }).modal('show');
  }

  approve=(data)=> {
    console.log("data Approve : ",data);
   return  this.dataApprove=data;
  }
  
 
}
