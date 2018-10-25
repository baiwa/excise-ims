import { Component, OnInit } from '@angular/core';
import { IaFollowUpProject } from '../../../../../common/models/IaFollowUpProject';
import { TextDateTH, formatter } from '../../../../../common/helper/datepicker';
import { AjaxService } from '../../../../../common/services/ajax.service';
import { Router, ActivatedRoute } from "@angular/router";
import { MessageBarService } from '../../../../../common/services/message-bar.service';
import { IaFollowUpDepartment } from '../../../../../common/models/IaFollowUpDepartment';
import { BaseModel, ManageReq, BreadCrumb } from 'models/index';
import { AuthService } from 'services/auth.service';

declare var $: any;
@Component({
  selector: 'int11-2-1',
  templateUrl: './int11-2-1.component.html',
  styleUrls: ['./int11-2-1.component.css']
})
export class Int1121Component implements OnInit {

  iaFollowUpDepartment: IaFollowUpDepartment;
  $form: any;
  id: any;
  showCloseJob: boolean = false;
  exciseDepartment: string = "";
  exciseRegion: string = "";
  exciseDistrict: string = "";
  departmentList: any[];
  regionList: any[];
  distrList: any[];
  // BreadCrumb
  breadcrumb: BreadCrumb[];

  constructor(
    private ajaxService: AjaxService,
    private router: Router,
    private authService: AuthService,
    private route: ActivatedRoute,
    private messageBarService: MessageBarService,
  ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ทะเบียนคุมการติดตามงาน", route: "#" },
      { label: "เพิ่มรายการติดตามผลการตรวจสอบของหน่วยรับตรวจ", route: "#" },
    ];
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-11210');
    this.$form = $("#followUpDepartmentForm");
    this.id = this.route.snapshot.queryParams["id"];
  }

  ngAfterViewInit() {
    if (this.id) {
      this.showCloseJob = true;
      this.getFollowUpDepartment();
    } else {
      this.getDepartmentDropdown();
    }
    this.initCalendar();

    $('#performance1Bnum').prop('disabled', 'disabled');
    $('#trackResult1Bnum').prop('disabled', 'disabled');
    $('#followUp2Bnum').prop('disabled', 'disabled');
    $('#performance2Bnum').prop('disabled', 'disabled');
    $('#trackResult2Bnum').prop('disabled', 'disabled');
    $('#followUp3Bnum').prop('disabled', 'disabled');
    $('#performance3Bnum').prop('disabled', 'disabled');
    $('#trackResult3Bnum').prop('disabled', 'disabled');

    $('#performance1Date').prop('disabled', 'disabled');
    $('#trackResult1Date').prop('disabled', 'disabled');
    $('#followUp2Date').prop('disabled', 'disabled');
    $('#performance2Date').prop('disabled', 'disabled');
    $('#trackResult2Date').prop('disabled', 'disabled');
    $('#followUp3Date').prop('disabled', 'disabled');
    $('#performance3Date').prop('disabled', 'disabled');
    $('#trackResult3Date').prop('disabled', 'disabled');

    this.initDropdown();
  }

  getFollowUpDepartment() {
    this.$form.addClass("loading");
    const URL = `ia/int112/get/followUpDepartment/${this.id}`;
    this.ajaxService.get(URL, res => {
      let body: any = res.json();
      this.iaFollowUpDepartment = body as IaFollowUpDepartment;
      this.$form.form("set values", this.iaFollowUpDepartment);

      this.getDepartmentDropdown();
      this.getRegionDropdown(this.iaFollowUpDepartment.exciseDepartment);
      this.getDistrictDropdown(this.iaFollowUpDepartment.exciseRegion);

      this.exciseDepartment = this.iaFollowUpDepartment.exciseDepartment;
      this.exciseRegion = this.iaFollowUpDepartment.exciseRegion;
      this.exciseDistrict = this.iaFollowUpDepartment.exciseDistrict;

      this.changeStatus();
      this.$form.removeClass("loading");
    }, err => {
      let body: any = err.json();
      this.messageBarService.errorModal(body.error);
      this.$form.removeClass("loading");
    });
  }

  initDropdown() {
    $(".save-follow-department-dropdown").dropdown().css('width', '100%');
  }

  initCalendar() {
    $(".calendar-picker").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
  }

  getDepartmentDropdown() {
    this.$form.addClass("loading");
    const URL = `ia/int112/department`;
    this.ajaxService.get(URL, res => {
      this.departmentList = res.json();
      this.$form.removeClass("loading");
    }, err => {
      let body: any = err.json();
      this.messageBarService.errorModal(body.error);
      this.$form.removeClass("loading");
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
    this.$form.addClass("loading");
    const URL = `ia/int112/region/${id}`;
    this.ajaxService.get(URL, res => {
      this.regionList = res.json();
      this.$form.removeClass("loading");
    }, err => {
      let body: any = err.json();
      this.messageBarService.errorModal(body.error);
      this.$form.removeClass("loading");
    });
  }

  changeRegion = (e) => {
    this.exciseDistrict = "";
    this.distrList = [];
    if (!$('#exciseRegion').val()) return;

    this.getDistrictDropdown(e.target.value);
  }

  getDistrictDropdown(id) {
    this.$form.addClass("loading");
    const URL = `ia/int112/district/${id}`;
    this.ajaxService.get(URL, res => {
      this.distrList = res.json();
      this.$form.removeClass("loading");
    }, err => {
      let body: any = err.json();
      this.messageBarService.errorModal(body.error);
      this.$form.removeClass("loading");
    });
  }

  saveData() {
    if (!$('#exciseDepartment').val()) {
      this.messageBarService.alert("กรุณาเลือกสรรพสามิตภาค", "แจ้งเตือน");
      return;
    }

    if (!$('#exciseRegion').val()) {
      this.messageBarService.alert("กรุณาเลือกสรรพสามิตพื้นที่", "แจ้งเตือน");
      return;
    }

    if (!$('#exciseDistrict').val()) {
      this.messageBarService.alert("กรุณาเลือกสรรพสามิตสาขา", "แจ้งเตือน");
      return;
    }

    if (!$('#informRectorBnum').val()) {
      this.messageBarService.alert("กรุณากรอกรายงานผลอธิบดีเลขหนังสือ", "แจ้งเตือน");
      return;
    }

    if (!$('#informRectorDate').val()) {
      this.messageBarService.alert("กรุณากรอกรายงานผลอธิบดีวันที่", "แจ้งเตือน");
      return;
    }

    this.messageBarService.comfirm(ok => {
      if (ok) {
        this.$form.addClass("loading");
        let formValues = this.$form.form("get values");
        let followUpProjectForm = formValues as IaFollowUpProject;
        if (this.id) {
          const URL = `ia/int112/update`;
          this.ajaxService.post(
            URL,
            followUpProjectForm,
            (success: Response) => {
              this.messageBarService.successModal("บันทึกข้อมูลสำเร็จ");
              this.$form.removeClass("loading");
              this.cancel();
            },
            (error: Response) => {
              let body: any = error.json();
              this.messageBarService.errorModal(body.error);
              this.$form.removeClass("loading");
            }
          );
        } else {
          const URL = `ia/int112/save`;
          this.ajaxService.put(
            URL,
            followUpProjectForm,
            (success: Response) => {
              this.messageBarService.successModal("บันทึกข้อมูลสำเร็จ");
              this.$form.removeClass("loading");
              this.cancel();
            },
            (error: Response) => {
              let body: any = error.json();
              this.messageBarService.errorModal(body.error);
              this.$form.removeClass("loading");
            }
          );
        }

      }
    }, "คุณต้องการบันทึกใช่หรือไม่ ? ");
  }

  clearData() {
    if (this.id) {
      this.showCloseJob = true;
      this.getFollowUpDepartment();
    } else {
      this.$form.form("clear");
      $(".save-follow-department-dropdown").dropdown('restore defaults');
    }
    this.changeStatus();
  }

  cancel() {
    this.router.navigate(["/int11/2"]);
  }

  autoShiftDateFollowFirst() {
    if (!$('#followUp1Date').val()) {
      $('#maturity145').val('');
      $('#maturity160').val('');
      return;
    }

    this.$form.addClass("loading");
    const URL = `ia/int112/shiftDate`;
    this.ajaxService.post(
      URL,
      JSON.stringify({
        "date": $('#followUp1Date').val()
      }), res => {
        var resJson = res.json();
        $('#maturity145').val(resJson.shift45Date);
        $('#maturity160').val(resJson.shift60Date);
        this.$form.removeClass("loading");
      }, err => {
        let body: any = err.json();
        this.messageBarService.errorModal(body.error);
        this.$form.removeClass("loading");
      }
    );
  }

  autoShiftDateFollowSecond() {
    if (!$('#followUp2Date').val()) {
      $('#maturity260').val('');
      return;
    }

    this.$form.addClass("loading");
    const URL = `ia/int112//shiftDate`;
    this.ajaxService.post(
      URL,
      JSON.stringify({
        "date": $('#followUp2Date').val()
      }), res => {
        var resJson = res.json();
        $('#maturity260').val(resJson.shift60Date);
        this.$form.removeClass("loading");
      }, err => {
        let body: any = err.json();
        this.messageBarService.errorModal(body.error);
        this.$form.removeClass("loading");
      }
    );
  }

  autoShiftDateFollowThird() {
    if (!$('#followUp3Date').val()) return;

    this.$form.addClass("loading");
    const URL = `ia/int111/shiftDate`;
    this.ajaxService.post(
      URL,
      JSON.stringify({
        "date": $('#followUp3Date').val()
      }), res => {
        var resJson = res.json();
        $('#maturity360').val(resJson.shift60Date);
        this.$form.removeClass("loading");
      }, err => {
        let body: any = err.json();
        this.messageBarService.errorModal(body.error);
        this.$form.removeClass("loading");
      }
    );
  }

  closeJob() {
    this.messageBarService.comfirm(ok => {
      if (ok) {
        this.$form.addClass("loading");
        const URL = `ia/int112/closeJob`;
        this.ajaxService.post(
          URL,
          JSON.stringify({
            "followUpDepartmentId": this.id
          }), res => {
            this.messageBarService.successModal("บันทึกข้อมูลสำเร็จ");
            this.$form.removeClass("loading");
            this.cancel();
          }, err => {
            let body: any = err.json();
            this.messageBarService.errorModal(body.error);
            this.$form.removeClass("loading");
          }
        );
      }
    }, "คุณต้องการปิดงานใช่หรือไม่ ? ");
  }

  changeStatus() {
    if ($('#followUp1Bnum').val()) {
      $('#status').val('แจ้งติดตามครั่งที่ 1');

      $('#performance1Bnum').prop('disabled', '');
      $('#performance1Date').prop('disabled', '');
    } else {
      $('#performance1Bnum').prop('disabled', 'disabled');
      $('#trackResult1Bnum').prop('disabled', 'disabled');
      $('#followUp2Bnum').prop('disabled', 'disabled');
      $('#performance2Bnum').prop('disabled', 'disabled');
      $('#trackResult2Bnum').prop('disabled', 'disabled');
      $('#followUp3Bnum').prop('disabled', 'disabled');
      $('#performance3Bnum').prop('disabled', 'disabled');
      $('#trackResult3Bnum').prop('disabled', 'disabled');

      $('#performance1Date').prop('disabled', 'disabled');
      $('#trackResult1Date').prop('disabled', 'disabled');
      $('#followUp2Date').prop('disabled', 'disabled');
      $('#performance2Date').prop('disabled', 'disabled');
      $('#trackResult2Date').prop('disabled', 'disabled');
      $('#followUp3Date').prop('disabled', 'disabled');
      $('#performance3Date').prop('disabled', 'disabled');
      $('#trackResult3Date').prop('disabled', 'disabled');
    }

    if ($('#performance1Bnum').val()) {
      $('#status').val('แจ้งผลการดำเนินงานครั้งที่ 1');

      $('#trackResult1Bnum').prop('disabled', '');
      $('#trackResult1Date').prop('disabled', '');
    } else {
      $('#trackResult1Bnum').prop('disabled', 'disabled');
      $('#followUp2Bnum').prop('disabled', 'disabled');
      $('#performance2Bnum').prop('disabled', 'disabled');
      $('#trackResult2Bnum').prop('disabled', 'disabled');
      $('#followUp3Bnum').prop('disabled', 'disabled');
      $('#performance3Bnum').prop('disabled', 'disabled');
      $('#trackResult3Bnum').prop('disabled', 'disabled');

      $('#trackResult1Date').prop('disabled', 'disabled');
      $('#followUp2Date').prop('disabled', 'disabled');
      $('#performance2Date').prop('disabled', 'disabled');
      $('#trackResult2Date').prop('disabled', 'disabled');
      $('#followUp3Date').prop('disabled', 'disabled');
      $('#performance3Date').prop('disabled', 'disabled');
      $('#trackResult3Date').prop('disabled', 'disabled');
    }

    if ($('#trackResult1Bnum').val()) {
      $('#status').val('รายงานการติดตามครั้งที่ 1');

      $('#followUp2Bnum').prop('disabled', '');
      $('#followUp2Date').prop('disabled', '');
    } else {
      $('#followUp2Bnum').prop('disabled', 'disabled');
      $('#performance2Bnum').prop('disabled', 'disabled');
      $('#trackResult2Bnum').prop('disabled', 'disabled');
      $('#followUp3Bnum').prop('disabled', 'disabled');
      $('#performance3Bnum').prop('disabled', 'disabled');
      $('#trackResult3Bnum').prop('disabled', 'disabled');

      $('#followUp2Date').prop('disabled', 'disabled');
      $('#performance2Date').prop('disabled', 'disabled');
      $('#trackResult2Date').prop('disabled', 'disabled');
      $('#followUp3Date').prop('disabled', 'disabled');
      $('#performance3Date').prop('disabled', 'disabled');
      $('#trackResult3Date').prop('disabled', 'disabled');
    }

    if ($('#followUp2Bnum').val()) {
      $('#status').val('แจ้งติดตามครั้งที่ 2');

      $('#performance2Bnum').prop('disabled', '');
      $('#performance2Date').prop('disabled', '');
    } else {
      $('#performance2Bnum').prop('disabled', 'disabled');
      $('#trackResult2Bnum').prop('disabled', 'disabled');
      $('#followUp3Bnum').prop('disabled', 'disabled');
      $('#performance3Bnum').prop('disabled', 'disabled');
      $('#trackResult3Bnum').prop('disabled', 'disabled');

      $('#performance2Date').prop('disabled', 'disabled');
      $('#trackResult2Date').prop('disabled', 'disabled');
      $('#followUp3Date').prop('disabled', 'disabled');
      $('#performance3Date').prop('disabled', 'disabled');
      $('#trackResult3Date').prop('disabled', 'disabled');
    }

    if ($('#performance2Bnum').val()) {
      $('#status').val('แจ้งผลการดำเนินงานครั้งที่ 2');

      $('#trackResult2Bnum').prop('disabled', '');
      $('#trackResult2Date').prop('disabled', '');
    } else {
      $('#trackResult2Bnum').prop('disabled', 'disabled');
      $('#followUp3Bnum').prop('disabled', 'disabled');
      $('#performance3Bnum').prop('disabled', 'disabled');
      $('#trackResult3Bnum').prop('disabled', 'disabled');

      $('#trackResult2Date').prop('disabled', 'disabled');
      $('#followUp3Date').prop('disabled', 'disabled');
      $('#performance3Date').prop('disabled', 'disabled');
      $('#trackResult3Date').prop('disabled', 'disabled');
    }

    if ($('#trackResult2Bnum').val()) {
      $('#status').val('รายงานการติดตามครั้งที่ 2');

      $('#followUp3Bnum').prop('disabled', '');
      $('#followUp3Date').prop('disabled', '');
    } else {
      $('#followUp3Bnum').prop('disabled', 'disabled');
      $('#performance3Bnum').prop('disabled', 'disabled');
      $('#trackResult3Bnum').prop('disabled', 'disabled');

      $('#followUp3Date').prop('disabled', 'disabled');
      $('#performance3Date').prop('disabled', 'disabled');
      $('#trackResult3Date').prop('disabled', 'disabled');
    }

    if ($('#followUp3Bnum').val()) {
      $('#status').val('แจ้งติดตามครั้งที่ 3');

      $('#performance3Bnum').prop('disabled', '');
      $('#performance3Date').prop('disabled', '');
    } else {
      $('#performance3Bnum').prop('disabled', 'disabled');
      $('#trackResult3Bnum').prop('disabled', 'disabled');

      $('#performance3Date').prop('disabled', 'disabled');
      $('#trackResult3Date').prop('disabled', 'disabled');
    }

    if ($('#performance3Bnum').val()) {
      $('#status').val('แจ้งผลการดำเนินงานครั้งที่ 3');

      $('#trackResult3Bnum').prop('disabled', '');
      $('#trackResult3Date').prop('disabled', '');
    } else {
      $('#trackResult3Bnum').prop('disabled', 'disabled');
      $('#trackResult3Date').prop('disabled', 'disabled');
    }

    if ($('#trackResult3Bnum').val()) {
      $('#status').val('รายงานการติดตามครั้งที่ 3');
    }

  }
}
