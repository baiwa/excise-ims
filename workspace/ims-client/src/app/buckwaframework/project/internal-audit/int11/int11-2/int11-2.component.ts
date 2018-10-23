import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AjaxService } from '../../../../common/services/ajax.service';
import { MessageBarService } from '../../../../common/services/message-bar.service';
import { BaseModel, ManageReq, BreadCrumb } from 'models/index';
import { AuthService } from 'services/auth.service';

declare var $: any;
@Component({
  selector: 'int11-2',
  templateUrl: './int11-2.component.html',
  styleUrls: ['./int11-2.component.css']
})
export class Int112Component implements OnInit {

  datatable: any;
  exciseDepartment: string = "";
  exciseRegion: string = "";
  exciseDistrict: string = "";
  status: string = "";
  $form: any;
  $page: any;
  departmentList: any[];
  regionList: any[];
  distrList: any[];
  statusList: any[];
  form : Int112Form = new Int112Form();
  searchFlag: string = "FALSE";

 // BreadCrumb
 breadcrumb: BreadCrumb[];

  constructor(
    private ajaxService: AjaxService,
    private ajax: AjaxService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private messageBarService: MessageBarService, ) {
      this.breadcrumb = [
        { label: "ตรวจสอบภายใน", route: "#" },
        { label: "ทะเบียนคุมการติดตามงาน", route: "#" },
        { label: "ค้นหาการติดตามผลการตรวจสอบของหน่วยรับตรวจ", route: "#" },
      ];
     }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-11200');
    this.$form = $('#followUpDepartmentform');
    this.$page = $('#followUpDepartmentPage');
  }

  ngAfterViewInit() {
    this.getStatusDropdown();
    this.getDepartmentDropdown();
    this.initDatatable();
    this.initDropdown();
  }

  initDropdown() {
    $(".follow-department-dropdown").dropdown().css('width', '100%');
  }

  initDatatable = () => {
    const URL = AjaxService.CONTEXT_PATH + "ia/int112/search";
    this.datatable = $("#dataTable").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: true,
      paging: false,
      scrollX: true,
      // scrollY: '50vh',
      // scrollCollapse: true,
      deferLoading: 10,
      ajax: {
        type: "POST",
        url: URL,
        contentType: "application/json",
        data: (d) => {
          return JSON.stringify($.extend({}, d, {
            "exciseDepartment": $('#exciseDepartment').val(),
            "exciseRegion": $('#exciseRegion').val(),
            "exciseDistrict": $('#exciseDistrict').val(),
            "status": $('#status').val(),
            "searchFlag": $("#searchFlag").val()
          }));
        }
      },
      columns: [
        {
          data: "followUpDepartmentId",
          className: "center aglined",
          render: function (data) {
            return (
              '<div class="ui checkbox follow-dep-chkbox"><input name="checkId" value="' +
              data +
              '" type="checkbox"><label></label></div>'
            );
          }
        },
        {
          data: "exciseDepartment",
          render: function (data, row, type) {
            var v = '-';
            if (data) {
              v = data + ' ' + type.exciseRegion + ' ' + type.exciseDistrict;
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
          render: function (data) {
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
          this.router.navigate(["/int11/2/1"], {
            queryParams: { id: data.followUpDepartmentId }
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
      this.form.id = data.followUpDepartmentId;
      $('#modolClose').modal('show');
       
    });
  }

  onClicksavenote =()=>{
    this.form.note = $('#noteclosejob').val();
    var url = "ia/int112/notecloseJob";
    this.ajaxService.post(url,JSON.stringify(this.form), res=>{
      $('#modolClose').modal('hide');
      $("#dataTable").DataTable().ajax.reload();
    });
  }

  getStatusDropdown() {
    const URL = `ia/int112/status`;
    this.$page.addClass("loading");
    this.ajaxService.get(URL, res => {
      this.statusList = res.json();
      this.$page.removeClass("loading");
    }, err => {
      let body: any = err.json();
      this.messageBarService.errorModal(body.error);
      this.$page.removeClass("loading");
    });
  }

  getDepartmentDropdown() {
    this.$page.addClass("loading");
    const URL = `ia/int112/department`;
    this.ajaxService.get(URL, res => {
      this.departmentList = res.json();
      this.$page.removeClass("loading");
    }, err => {
      let body: any = err.json();
      this.messageBarService.errorModal(body.error);
      this.$page.removeClass("loading");
    });
  }

  changeDepartment = (e) => {
    this.exciseRegion = "";
    this.exciseDistrict = "";
    this.regionList = [];
    this.distrList = [];

    if (!$('#exciseDepartment').val()) return;

    this.getRegionDropdown(e.target.value);
  }

  getRegionDropdown(id) {
    this.$page.addClass("loading");
    const URL = `ia/int112/region/${id}`;
    this.ajaxService.get(URL, res => {
      this.regionList = res.json();
      this.$page.removeClass("loading");
    }, err => {
      let body: any = err.json();
      this.messageBarService.errorModal(body.error);
      this.$page.removeClass("loading");
    });
  }

  changeRegion = (e) => {
    this.exciseDistrict = "";
    this.distrList = [];
    if (!$('#exciseRegion').val()) return;

    this.getDistrictDropdown(e.target.value);
  }

  getDistrictDropdown(id) {
    this.$page.addClass("loading");
    const URL = `ia/int112/district/${id}`;
    this.ajaxService.get(URL, res => {
      this.distrList = res.json();
      this.$page.removeClass("loading");
    }, err => {
      let body: any = err.json();
      this.messageBarService.errorModal(body.error);
      this.$page.removeClass("loading");
    });
  }

  searchData() {
    $("#searchFlag").val("TRUE");
    $("#dataTable").DataTable().ajax.reload();
    $("#dataTable").DataTable().ajax.reload();
  }

  clearData() {
    this.getDepartmentDropdown();
    this.exciseDepartment = "";
    this.exciseRegion = "";
    this.exciseDistrict = "";
    this.status = "";
    this.regionList = [];
    this.distrList = [];
    $("#searchFlag").val("FALSE");
    $(".follow-department-dropdown").dropdown('restore defaults');
    this.searchData();
  }

  addData() {
    this.router.navigate(["/int11/2/1"]);
  }

  deleteData() {
    var deletes = [];
    let checkboxes = $(".ui.checkbox.follow-dep-chkbox");
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
        const URL = `ia/int112/delete/${deletes.join(",")}`;
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

  clickCheckAll = event =>  {
    if (event.target.checked) {
      $(".ui.checkbox.follow-dep-chkbox").checkbox("check");
    } else {
      $(".ui.checkbox.follow-dep-chkbox").checkbox("uncheck");
    }
  }

  // export() {
  //   var url = AjaxService.CONTEXT_PATH + "ia/int112/export?exciseDepartment=" + $('#exciseDepartment').val() +
  //     "&exciseRegion=" + $('#exciseRegion').val() + "&exciseDistrict=" + $('#exciseDistrict').val() +
  //     "&status=" + $('#status').val();
  //   window.open(url);
  // }

  exportFollowUpDepartment() {
    const URL = "ia/int112/exportFollowUpDepartment?exciseDepartment=" + $('#exciseDepartment').val() +"&exciseRegion=" + $('#exciseRegion').val() + "&exciseDistrict=" + $('#exciseDistrict').val() +"&status=" + $('#status').val();
    console.log("exciseDepartment :" + $('#exciseDepartment').val()+ " , exciseRegion :"+$('#exciseRegion').val()+" , exciseDistrict :"+$('#exciseDistrict').val()+" , status :"+$('#status').val() );
    this.ajax.download(URL);
  }
}

class Int112Form{
  note : string ="";
  id : string = "";
} 