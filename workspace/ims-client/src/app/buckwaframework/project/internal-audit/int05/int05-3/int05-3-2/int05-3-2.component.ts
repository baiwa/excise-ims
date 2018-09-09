import { Component, OnInit } from '@angular/core';
import { AjaxService } from "../../../../../common/services/ajax.service";
import { TextDateTH, formatter } from '../../../../../common/helper/datepicker';
import { Router, ActivatedRoute } from '@angular/router';
import { MessageBarService } from "../../../../../common/services/message-bar.service";

declare var $: any;
@Component({
  selector: 'int05-3-2',
  templateUrl: './int05-3-2.component.html',
  styleUrls: ['./int05-3-2.component.css']
})
export class Int0532Component implements OnInit {

  assetTypeList: any[];
  dataTable: any;

  constructor(private ajax: AjaxService,
    private router: Router,
    private route: ActivatedRoute,
    private messageBarService: MessageBarService, ) { }

  ngOnInit() {
  }

  ngAfterViewInit() {
    this.initDropdown();
    this.initCalendar();
    this.initDatatable();
  }

  initCalendar() {
    $(".asset-dt-calendar").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
  }

  initDropdown() {
    $(".asset-dt-dropdown").dropdown().css('width', '100%');
  }

  initDatatable(): void {
    this.dataTable = $("#dataTable").DataTable({
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
            html += '<button type="button" class="ui mini primary button desc-button"><i class="edit icon"></i>รายละเอียด</button>';
            html += '<button type="button" class="ui mini primary button edit-button"><i class="edit icon"></i>แก้ไข</button>';
            html += '<button type="button" class="ui mini primary button history-button"><i class="edit icon"></i>เพิ่มประวัตการซ่อมบำรุง</button>';
            return html;
          }
        }
      ],
      rowCallback: (row, data, index) => {
        $("td > .edit-button", row).bind("click", () => {
        
        });
      }
    });
  }

  addData() {
    this.router.navigate(["/int05/3/2/1"]);
  }

}
