import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AjaxService } from '../../../../common/services/ajax.service';
import { MessageBarService } from '../../../../common/services/message-bar.service';
import { BaseModel, ManageReq, BreadCrumb } from 'models/index';
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
  form : Int111Form = new Int111Form();

  // BreadCrumb
  breadcrumb: BreadCrumb[];

  constructor(
    private ajaxService: AjaxService,
    private ajax: AjaxService,
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
    this.$form = $('#followUpProjectForm');
    this.$page = $('#followUpProjectPage');
  }

  ngAfterViewInit() {
    this.getStatusDropdown();
    this.initDatatable();
    this.initDropdown();
  }

  initDropdown() {
    $(".follow-project-dropdown").dropdown().css('width', '100%');
  }

  initDatatable =()=> {
    const URL = AjaxService.CONTEXT_PATH + "ia/int111/search";
    this.datatable = $("#dataTable").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: false,
      paging: true,
      scrollX: true,

      ajax: {
        type: "POST",
        url: URL,
        contentType: "application/json",
        data: function (d) {
          return JSON.stringify($.extend({
            "projectName": $('#projectName').val(),
            "status": $('#status').val()
          }, d, {}));
        }
      },
      columns: [
        {
          data: "followUpProjectId",
          className: "center aglined",
          render: function (data) {
            return (
              '<div class="ui checkbox follow-proj-chkbox"><input name="checkId" value="' +
              data +
              '" type="checkbox"><label></label></div>'
            );
          }
        },
        {
          data: "projectName",
          render: function (data) {
            var v = '-';
            if (data) {
              v = data;
            }
            return v;
          }
        },
        {
          data: "informRectorBnum",
          className: "center aglined",
          render: function (data) {
            var v = '-';
            if (data) {
              v = data;
            }
            return v;
          }
        },
        {
          data: "informRectorDate",
          className: "center aglined",
          render: function (data) {
            var v = '-';
            if (data) {
              v = data;
            }
            return v;
          }
        },
        {
          data: "followUp1Bnum",
          className: "center aglined",
          render: function (data) {
            var v = '-';
            if (data) {
              v = data;
            }
            return v;
          }
        },
        {
          data: "followUp1Date",
          className: "center aglined",
          render: function (data) {
            var v = '-';
            if (data) {
              v = data;
            }
            return v;
          }
        },
        {
          data: "maturity145",
          className: "center aglined",
          render: function (data) {
            var v = '-';
            if (data) {
              v = data;
            }
            return v;
          }
        },
        {
          data: "maturity160",
          className: "center aglined",
          render: function (data) {
            var v = '-';
            if (data) {
              v = data;
            }
            return v;
          }
        },
        {
          data: "performance1Bnum",
          className: "center aglined",
          render: function (data) {
            var v = '-';
            if (data) {
              v = data;
            }
            return v;
          }
        },
        {
          data: "performance1Date",
          className: "center aglined",
          render: function (data) {
            var v = '-';
            if (data) {
              v = data;
            }
            return v;
          }
        },
        {
          data: "trackResult1Bnum",
          className: "center aglined",
          render: function (data) {
            var v = '-';
            if (data) {
              v = data;
            }
            return v;
          }
        },
        {
          data: "trackResult1Date",
          className: "center aglined",
          render: function (data) {
            var v = '-';
            if (data) {
              v = data;
            }
            return v;
          }
        },
        {
          data: "followUp2Bnum",
          className: "center aglined",
          render: function (data) {
            var v = '-';
            if (data) {
              v = data;
            }
            return v;
          }
        },
        {
          data: "followUp2Date",
          className: "center aglined",
          render: function (data) {
            var v = '-';
            if (data) {
              v = data;
            }
            return v;
          }
        },
        {
          data: "maturity260",
          className: "center aglined",
          render: function (data) {
            var v = '-';
            if (data) {
              v = data;
            }
            return v;
          }
        },
        {
          data: "performance2Bnum",
          className: "center aglined",
          render: function (data) {
            var v = '-';
            if (data) {
              v = data;
            }
            return v;
          }
        },
        {
          data: "performance2Date",
          className: "center aglined",
          render: function (data) {
            var v = '-';
            if (data) {
              v = data;
            }
            return v;
          }
        },
        {
          data: "trackResult2Bnum",
          className: "center aglined",
          render: function (data) {
            var v = '-';
            if (data) {
              v = data;
            }
            return v;
          }
        },
        {
          data: "trackResult2Date",
          className: "center aglined",
          render: function (data) {
            var v = '-';
            if (data) {
              v = data;
            }
            return v;
          }
        },
        {
          data: "followUp3Bnum",
          className: "center aglined",
          render: function (data) {
            var v = '-';
            if (data) {
              v = data;
            }
            return v;
          }
        },
        {
          data: "followUp3Date",
          className: "center aglined",
          render: function (data) {
            var v = '-';
            if (data) {
              v = data;
            }
            return v;
          }
        },
        {
          data: "maturity360",
          className: "center aglined",
          render: function (data) {
            var v = '-';
            if (data) {
              v = data;
            }
            return v;
          }
        },
        {
          data: "performance3Bnum",
          className: "center aglined",
          render: function (data) {
            var v = '-';
            if (data) {
              v = data;
            }
            return v;
          }
        },
        {
          data: "performance3Date",
          className: "center aglined",
          render: function (data) {
            var v = '-';
            if (data) {
              v = data;
            }
            return v;
          }
        },
        {
          data: "trackResult3Bnum",
          className: "center aglined",
          render: function (data) {
            var v = '-';
            if (data) {
              v = data;
            }
            return v;
          }
        },
        {
          data: "trackResult3Date",
          className: "center aglined",
          render: function (data) {
            var v = '-';
            if (data) {
              v = data;
            }
            return v;
          }
        },
        {
          data: "status",
          render: function (data) {
            var v = '-';
            if (data) {
              v = data;
            }
            return v;
          }
        },
        {
          data: "note",
          render: function (data) {
            var v = '-';
            if (data) {
              v = data;
            }
            return v;
          }
        },
        {
          data: "status",
          className: "center aglined",
          render: function (data,row) {
            var html = '';
            if (data != 'เสร็จสิ้น') {
              html += '<button type="button" class="ui mini yellow button edit-button"><i class="edit icon"></i>แก้ไข</button>';
              html += '<button type="button" class="ui mini blue button close-button"> <i class="close outline icon"></i>ปิดงาน</button>';
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
      }, createdRow: function (row, data, dataIndex) {
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

  onClicksavenote =()=>{
    this.form.note = $('#noteclosejob').val();
    var url = "ia/int111/notecloseJob";
    this.ajaxService.post(url,JSON.stringify(this.form), res=>{
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
    $("#dataTable").DataTable().ajax.reload();
  }

  clearData() {
    $('#projectName').val('');
    $('#status').val('');
    $(".follow-project-dropdown").dropdown('restore defaults');
    this.searchData()
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

class Int111Form{
  note : string ="";
  id : string = "";
} 