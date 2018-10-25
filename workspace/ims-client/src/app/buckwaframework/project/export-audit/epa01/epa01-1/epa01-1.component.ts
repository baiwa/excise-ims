import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { AjaxService } from '../../../../common/services/ajax.service';
import { Router } from '@angular/router';

declare var $: any;

@Component({
  selector: 'app-epa01-1',
  templateUrl: './epa01-1.component.html',
  styleUrls: ['./epa01-1.component.css']
})

export class Epa011Component implements OnInit {

  datatable: any;
  $form: any;
  $page: any;
  exciseId: string = "";
  searchFlag: string = "FALSE";

  constructor(
    private authService: AuthService,
    private ajaxService: AjaxService,
    private ajax: AjaxService,
    private router: Router,
  ) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('EXP-01100');
    this.$form = $('#exportForm');
  }

  ngAfterViewInit(): void {
    this.initDatatable();
  }

  initDatatable = () => {
    const URL = AjaxService.CONTEXT_PATH + "epa/epa011/search";
    this.datatable = $("#dataTable").DataTable({
      serverSide: true,
      searching: false,
      processing: true,
      ordering: false,
      scrollX: true,
      language: {
        info: "แสดงจาก_START_ ถึง _END_ จากทั้งหมด _TOTAL_ รายการ",
        paginate: {
          first: "หน้าแรก",
          last: "หน้าสุดท้าย",
          next: "ถัดไป",
          previous: "ก่อนหน้า"
        },
        lengthMenu: "แสดง _MENU_ รายการ",
        loadingRecords: "กำลังดาวน์โหลด...",
        processing: "กำลังประมวลผล...",
        search: "ค้นหาทั้งหมด",
        infoEmpty: "แสดงจาก 0 ถึง 0 จากทั้งหมด 0 รายการ",
        emptyTable: "ไม่พบข้อมูล",
      },
      ajax: {
        type: "POST",
        url: URL,
        contentType: "application/json",
        data: (d) => {
          return JSON.stringify($.extend({}, d, {
            "exciseId": this.exciseId,
            "searchFlag": this.searchFlag
          }));
        }
      },
      columns: [
        {
          className: "ui center aligned",
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          }
        }, {
          data: "exciseName",
          className: "ui center aligned",
        }, {
          data: "destination",
          className: "ui center aligned",
        }, {
          data: "dateDestination",
          className: "ui center aligned",
        }, {
          data: "dateDestination",
          className: "ui center aligned",
        }, {
          data: "dateDestination",
          className: "ui center aligned",
          render: function (data, row) {
            return '<button type="button" class="ui mini primary button checking-button"><i class="edit icon"></i>ตรวจสอบ</button>';
          }
        },
      ],
      rowCallback: (row, data, index) => {
        $("td > .checking-button", row).bind("click", () => {
          console.log("[Data]: ", data);
          this.router.navigate(["/epa01/2"], {
            queryParams: { exciseId: data.exciseId }
          });
        });
      },
    });
  };

  onClickSearch() {
    this.searchFlag = "TRUE";
    this.datatable.ajax.reload();
  };

  onClickClear() {
    this.exciseId = "";
    this.searchFlag = "FALSE";
    this.datatable.ajax.reload();
  };

}
