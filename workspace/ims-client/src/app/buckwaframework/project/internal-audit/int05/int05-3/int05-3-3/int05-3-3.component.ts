import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Location } from "@angular/common";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { Alert } from "../../../../../../../../node_modules/@types/selenium-webdriver";
import { AssetBalance, AssetMaintenance } from "../../../../../common/models";

declare var jQuery: any;
declare var $: any;
@Component({
  selector: 'app-int05-3-3',
  templateUrl: './int05-3-3.component.html',
  styleUrls: ['./int05-3-3.component.css']
})
export class Int0533Component implements OnInit {


  assetBalance: AssetBalance;
  assetMaintenance: AssetMaintenance;
  startDate: any;
  endDate: any;
  deleteList: any[] = [];
  datatable: any;
  id: any;
  act: any;
  constructor(private router: Router,
    private route: ActivatedRoute,
    private ajax: AjaxService,
    private messageBarService: MessageBarService) {

    this.assetBalance = new AssetBalance();
    this.assetMaintenance = new AssetMaintenance();
  }

  ngOnInit() {

    this.initDatatable();
  }

  createAssetBalance() {
    this.router.navigate(['int05/3/3/1'], {

    });
  }
  initDatatable(): void {
    if (this.datatable != null && this.datatable != undefined) {
      this.datatable.destroy();
    }
    const URL = AjaxService.CONTEXT_PATH + "ia/int0533/dataTableAssetBalance";
    console.log(this.assetBalance);
    this.datatable = $("#dataTable").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: true,
      paging: false,
      ajax: {
        type: "POST",
        url: URL,
        data: { assetType: this.assetBalance.assetType == '0' ? '' : this.assetBalance.assetType }
      },
      columns: [

        {
          data: "assetBalanceId",
          render: function (data, type, full, meta) {
            return (
              '<div class="ui checkbox tableDt"><input name="checkDelId" value="' +
              data +
              '" type="checkbox"><label></label></div>'
            );
          }
        }, { data: "assetBalanceId" },
        { data: "assetType" },
        { data: "assetDescription" },
        { data: "assetAmount" },
        { data: "depreciationRate" },
        { data: "accumulatedDepreciation" },


        {
          data: "assetBalanceId",
          render: function (data, type, row, meta) {

            return '<button type="button" class="ui mini primary button  dtl"><i class="eye icon"></i>รายละเอียด</button>' +
              '<button type="button" class="ui mini yellow button  edit"><i class="edit icon"></i>แก้ไข</button>' +
              '<button type="button" class="ui mini green button  addHis"><i class="plus icon"></i>เพิ่มประวัติ</button>';
          }
        }

      ],
      columnDefs: [
        { targets: [0, 1, 4, 5, 6, 7], className: "center aligned" },

      ], rowCallback: (row, data, index) => {
        $("td > .tableDt", row).bind("change", () => {
          let isDelete = false;
          for (let index = 0; index < this.deleteList.length; index++) {
            const element = this.deleteList[index];
            if (element == data.assetBalanceId) {
              isDelete = true;
              if (index == 0) {
                let temp = [];
                for (let j = 1; j < this.deleteList.length; j++) {
                  temp.push(this.deleteList[j]);
                }
                this.deleteList = temp;
              } else {
                this.deleteList.splice(index);
              }
            }
          }
          if (!isDelete) {
            this.deleteList.push(data.assetBalanceId);
          }
          console.log(this.deleteList);

        })
        $("td > .dtl", row).bind("click", () => {
          this.router.navigate(['int05/3/3/2'], {
            queryParams: { act: 'addDetail', id: data.assetBalanceId }
          });
        })
        $("td > .edit", row).bind("click", () => {
          console.log(data);
          this.router.navigate(['int05/3/3/1'], {
            queryParams: { act: 'edit', id: data.assetBalanceId }
          });
        })
        $("td > .addHis", row).bind("click", () => {
          console.log(data);
          this.router.navigate(['int05/3/3/1'], {
            queryParams: { act: 'addDetail', id: data.assetBalanceId }
          });
        });
      }


    });
  }

  deleteAssetBalance() {
    var url = "ia/int0533/deleteAssetBalanceList";
    this.ajax.post(url, { assetBalanceIdList: this.deleteList }, res => {
      var message = res.json();

      if (message.messageType == 'E') {
        this.messageBarService.errorModal(message.messageTh, 'แจ้งเตือน');
      } else {
        this.messageBarService.successModal(message.messageTh, 'บันทึกข้อมูลสำเร็จ');
        this.initDatatable();
      }
    });
  }



  searchAssetBalance() {
    this.initDatatable();
  }

}

