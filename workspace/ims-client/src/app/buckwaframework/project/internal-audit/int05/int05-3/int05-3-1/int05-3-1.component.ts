import { Component, OnInit } from "@angular/core";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { TextDateTH, formatter } from '../../../../../common/helper/datepicker';
import { Router, ActivatedRoute } from '@angular/router';
import { MessageBarService } from "../../../../../common/services/message-bar.service";

declare var $: any;
@Component({
  selector: "int05-3-1",
  templateUrl: "./int05-3-1.component.html",
  styleUrls: ["./int05-3-1.component.css"]
})
export class Int0531Component implements OnInit {

  datatable: any;
  count: any;
  assetWorkSheet: AssetWorkSheet = new AssetWorkSheet();
  $form: any;
  datas: AssetWorkSheet[];

  constructor(private ajax: AjaxService,
    private router: Router,
    private route: ActivatedRoute,
    private messageBarService: MessageBarService, ) {
    this.datas = [];
    this.count = 0;
  }

  ngOnInit() {
    this.$form = $('#addAssetWorkForm');
  }

  ngAfterViewInit() {
    this.initCalendar();
    this.initDatatable();
  }

  initCalendar() {
    $(".asset-work-calendar").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
  }

  initDatatable(): void {
    this.datatable = $("#dataTable").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      processing: true,
      serverSide: false,
      paging: false,
      columns: [
        {
          data: "indexValue",
          className: "center aglined",
          render: function (data) {
            return (
              '<div class="ui checkbox add-asset-wk-chkbox"><input value="' +
              data +
              '" type="checkbox"><label></label></div>'
            );
          }
        },
        {
          data: "assetNumber",
          className: "right aglined"
        },
        {
          data: "assetSubNumber",
          className: "right aglined"
        },
        {
          data: "fundTransferDate",
          className: "center aglined"
        },
        {
          data: "assetDescription"
        },
        {
          data: "acquisitionValue",
          className: "right aglined"
        },
        {
          data: "accumulatedDepreciation",
          className: "right aglined"
        },
        {
          data: "bookValue",
          className: "right aglined"
        },
        {
          data: "action",
          className: "center aglined",
          render: function (data) {
            var html = '';
            html += '<button type="button" class="ui mini yellow button edit-button"><i class="edit icon"></i>แก้ไข</button>';
            return html;
          }
        }
      ],
      rowCallback: (row, data, index) => {
        $("td > .edit-button", row).bind("click", () => {
          this.editData(data);
        });
      }
    });
  }

  addData() {
    this.$form.addClass("loading");

    this.assetWorkSheet = this.$form.form("get values") as AssetWorkSheet;

    if (this.assetWorkSheet.indexValue) {
      for (let i = 0; i < this.datas.length; i++) {
        const e = this.datas[i];
        if (e.indexValue == this.assetWorkSheet.indexValue) {
          this.datas[i] = this.assetWorkSheet;
          break;
        }
      }
    } else {
      this.assetWorkSheet.indexValue = this.count;
      this.datas.push(this.assetWorkSheet);
      this.count++;
    }

    this.datatable.clear().draw();
    this.datatable.rows.add(this.datas).draw();
    this.datatable.columns.adjust().draw();

    this.assetWorkSheet = new AssetWorkSheet();
    this.$form.form("clear");
    this.$form.removeClass("loading");

    this.messageBarService.successModal("เพิ่มข้อมูลสำเร็จ", "แจ้งเตือน");
  }

  clearData() {
    this.$form.form("clear");
    this.datas = [];
    this.datatable.clear().draw();
  }

  editData(data: any) {
    let editData = data as AssetWorkSheet;

    this.$form.form("set values", editData);
  }

  deleteData() {
    let deletes = [];
    let checkboxes = $(".ui.checkbox.add-asset-wk-chkbox");
    for (var i = 0; i < checkboxes.length; i++) {
      if (checkboxes.length == 1) {
        if (checkboxes.checkbox("is checked")) {
          deletes.push(checkboxes.find("[type=checkbox]").val());
        }
      } else {
        if (checkboxes.checkbox("is checked")[i]) {
          deletes.push(checkboxes.find("[type=checkbox]")[i].value);
        }
      }
    }

    if (deletes.length == 0) {
      this.messageBarService.alert("กรุณาเลือกที่รายการที่ต้องการลบ", "แจ้งเตือน");
      return;
    }

    this.messageBarService.comfirm(ok => {
      if (ok) {
        this.$form.addClass("loading");
        loop:
        for (let i = 0; i < deletes.length; i++) {
          for (let j = 0; j < this.datas.length; j++) {
            if (deletes[i] == this.datas[j].indexValue) {
              this.datas.splice(j, 1);
              continue loop;
            }
          }
        }
        this.datatable.clear().draw();
        this.datatable.rows.add(this.datas).draw();
        this.datatable.columns.adjust().draw();
        this.$form.removeClass("loading");
        $('.check-all').checkbox('uncheck');
        this.messageBarService.successModal("ลบข้อมูลสำเร็จ", "แจ้งเตือน");
      }
    }, "คุณต้องการลบข้อมูลใช่หรือไม่ ? ");
  }

  clickCheckAll = event => {
    if (event.target.checked) {
      $(".ui.checkbox.add-asset-wk-chkbox").checkbox("check");
    } else {
      $(".ui.checkbox.add-asset-wk-chkbox").checkbox("uncheck");
    }
  }

  cancel() {
    this.messageBarService.comfirm(ok => {
      if (ok) {
        this.router.navigate(["/int05/3"]);
      }
    }, "คุณต้องการยกเลิกใช่หรือไม่ ? ");
  }

  processCheckData() {
    
  }

}

class AssetWorkSheet {
  assetWorkSheetId: string;
  accumulatedDepreciation: string;
  acquisitionValue: string;
  assetDescription: string;
  assetNumber: string;
  assetSubNumber: string;
  bookValue: string;
  fundTransferDate: any;
  indexValue: any;
}