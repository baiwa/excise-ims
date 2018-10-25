import { Router, ActivatedRoute } from '@angular/router';
import { AjaxService } from '../../../../common/services/ajax.service';
import { MessageBarService } from '../../../../common/services/message-bar.service';
import { BaseModel, ManageReq, BreadCrumb } from 'models/index';
import { AuthService } from 'services/auth.service';
import { Component, OnInit } from '@angular/core';

class Int111Form {
  note: string = "";
  id: string = "";
}

declare var $: any;

@Component({
  selector: 'int11-1',
  templateUrl: './int11-1.component.html',
  styleUrls: ['./int11-1.component.css']
})

export class Int111Component implements OnInit {

  // id : string = "";
  isSearch: boolean = false;
  datatable: any;
  statusList: any[];
  projectName: string = "";
  status: string = "";
  $form: any;
  $page: any;
  form: Int111Form = new Int111Form();
  searchFlag: string = "FALSE";
  // BreadCrumb
  breadcrumb: BreadCrumb[];

  constructor(
    private ajaxService: AjaxService,
    private ajax: AjaxService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private messageBarService: MessageBarService,
  ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ทะเบียนคุมการติดตามงาน", route: "#" },
      { label: "ค้นหาการติดตามผลการตรวจสอบของโครงการ", route: "#" },
    ];
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-11100');
    this.$form = $('#followUpProjectForm');
    this.$page = $('#followUpProjectPage');
    this.initDatatable();
  }

  async ngAfterViewInit() {
    await this.getStatusDropdown();
    await this.initDropdown();
  }

  initDropdown() {
    $(".follow-project-dropdown").dropdown().css('width', '100%');
  }

  initDatatable = () => {
    const URL = AjaxService.CONTEXT_PATH + "ia/int111/search";
    this.datatable = $("#dataTable").DataTable({
      "serverSide": true,
      "searching": false,
      "processing": true,
      "ordering": false,
      "scrollX": true,
      "language": {
        "info": "แสดงจาก_START_ ถึง _END_ จากทั้งหมด _TOTAL_ รายการ",
        "paginate": {
          "first": "หน้าแรก",
          "last": "หน้าสุดท้าย",
          "next": "ถัดไป",
          "previous": "ก่อนหน้า"
        },
        "lengthMenu": "แสดง _MENU_ รายการ",
        "loadingRecords": "กำลังดาวน์โหลด...",
        "processing": "กำลังประมวลผล...",
        "search": "ค้นหาทั้งหมด",
        "infoEmpty": "แสดงจาก 0 ถึง 0 จากทั้งหมด 0 รายการ",
        "emptyTable": "ไม่พบข้อมูล",
      },
      "ajax": {
        "type": "POST",
        "url": URL,
        "contentType": "application/json",
        "data": (d) => {
          return JSON.stringify($.extend({}, d, {
            "projectName": $('#projectName').val(),
            "status": $('#status').val(),
            "searchFlag": $("#searchFlag").val()
          }));
        }
      },
      "columns": [
        {
          "data": "followUpProjectId",
          "className": "ui center aligned",
          "render": function (data) {
            return (
              '<div class="ui checkbox follow-proj-chkbox"><input name="checkId" value="' +
              data +
              '"type="checkbox"><label></label></div>'
            );
          }
        },
        {
          "data": "projectName",
          "className": "ui center aligned",
        },
        {
          "data": "informRectorBnum",
          "className": "ui center aligned",
        },
        {
          "data": "informRectorDate",
          "className": "ui center aligned",
        },
        {
          "data": "followUp1Bnum",
          "className": "ui center aligned",
        },
        {
          "data": "followUp1Date",
          "className": "ui center aligned",
        },
        {
          "data": "maturity145",
          "className": "ui center aligned",
        },
        {
          "data": "maturity160",
          "className": "ui center aligned",
        },
        {
          "data": "performance1Bnum",
          "className": "ui center aligned",
        },
        {
          "data": "performance1Date",
          "className": "ui center aligned",
        },
        {
          "data": "trackResult1Bnum",
          "className": "ui center aligned",
        },
        {
          "data": "trackResult1Date",
          "className": "ui center aligned",
        },
        {
          "data": "followUp2Bnum",
          "className": "ui center aligned",
        },
        {
          "data": "followUp2Date",
          "className": "ui center aligned",
        },
        {
          "data": "maturity260",
          "className": "ui center aligned",
        },
        {
          "data": "performance2Bnum",
          "className": "ui center aligned",
        },
        {
          "data": "performance2Date",
          "className": "ui center aligned",
        },
        {
          "data": "trackResult2Bnum",
          "className": "ui center aligned",
        },
        {
          "data": "trackResult2Date",
          "className": "ui center aligned",
        },
        {
          "data": "followUp3Bnum",
          "className": "ui center aligned",
        },
        {
          "data": "followUp3Date",
          "className": "ui center aligned",
        },
        {
          "data": "maturity360",
          "className": "ui center aligned",
        },
        {
          "data": "performance3Bnum",
          "className": "ui center aligned",
        },
        {
          "data": "performance3Date",
          "className": "ui center aligned",
        },
        {
          "data": "trackResult3Bnum",
          "className": "ui center aligned",
        },
        {
          "data": "trackResult3Date",
          "className": "ui center aligned",
        },
        {
          "data": "status",
          "className": "ui center aligned",
        },
        {
          "data": "note",
          "className": "ui center aligned",
        },
        {
          "data": "status",
          "className": "ui center aligned",
          "render": function (data, row) {
            var html = '';
            if (data != 'เสร็จสิ้น') {
              html += '<button type="button" class="ui mini yellow button edit-button"><i class="edit icon"></i>แก้ไข</button>';
              html += '<button type="button" class="ui mini blue button close-button"> <i class="power off icon"></i>ปิดงาน</button>';
            }
            return html;
          }
        }
      ],
      rowCallback: (row, data, index) => {
        $("td > .edit-button", row).bind("click", () => {
          this.router.navigate(["/int11/1/1"], {
            queryParams: { id: data.followUpProjectId }
          });
        });
      },
      createdRow: function (row, data, dataIndex) {
        if (data.status === 'เสร็จสิ้น') {
          $(row).find('td:eq(26),td:eq(27),td:eq(28)').addClass('bg-c-green');
        }
      }
    });

    this.datatable.on('click', 'tbody tr button.close-button', (e) => {
      var closestRow = $(e.target).closest('tr');
      var data = this.datatable.row(closestRow).data();
      console.log("data : ", data);
      this.form.id = data.followUpProjectId;
      $('#modolClose').modal('show');
    });
  }

  onClicksavenote = () => {
    this.form.note = $('#noteclosejob').val();
    var url = "ia/int111/notecloseJob";
    this.ajaxService.post(url, JSON.stringify(this.form), res => {
      $('#modolClose').modal('hide');
      $("#dataTable").DataTable().ajax.reload();
    });
  }

  getStatusDropdown() {
    const URL = "ia/int111/status";
    this.ajaxService.get(URL, res => {
      this.statusList = res.json();
    }, err => {
      let body: any = err.json();
      this.messageBarService.errorModal(body.error);
      this.$form.removeClass("loading");
    });
  }

  searchData() {
    $("#searchFlag").val("TRUE");
    this.datatable.ajax.reload();
    setTimeout(() => {
      this.datatable.columns.adjust().draw();
    }, 500);
  }

  clearData() {
    $('#projectName').val('');
    $("#status").dropdown('restore defaults');
    $("#searchFlag").val("FALSE");
    this.datatable.ajax.reload();
  }

  addData() {
    this.router.navigate(["/int11/1/1"]);
  }

  deleteData() {
    var deletes = [];
    let checkboxes = $(".ui.checkbox.follow-proj-chkbox");
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
        this.$page.addClass("loading");
        const URL = `ia/int111/delete/${deletes.join(",")}`;
        this.ajaxService.delete(
          URL, res => {
            this.messageBarService.successModal("ลบข้อมูลสำเร็จ");
            this.searchData();
            $('.check-all').checkbox("uncheck");
            this.$page.removeClass("loading");
          }, err => {
            let body: any = err.json();
            this.messageBarService.errorModal(body.error);
            this.$page.removeClass("loading");
          }
        );
      }
    }, "คุณต้องการลบข้อมูลใช่หรือไม่ ? ");
  }

  clickCheckAll = event => {
    if (event.target.checked) {
      $(".ui.checkbox.follow-proj-chkbox").checkbox("check");
    } else {
      $(".ui.checkbox.follow-proj-chkbox").checkbox("uncheck");
    }
  }

  // export() {
  //   var url = AjaxService.CONTEXT_PATH + "ia/int111/export?projectName=" + $('#projectName').val() +
  //     "&status=" + $('#status').val();
  //   window.open(url);
  // }

  exportFollowUpProject() {
    const URL = "ia/int111/exportFollowUpProject?projectName=" + $('#projectName').val() + "&status=" + $('#status').val();
    console.log("projectName :" + $('#projectName').val() + " , " + "status :" + $('#status').val());
    this.ajax.download(URL);
  }

} 