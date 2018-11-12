import { Component, OnInit } from "@angular/core";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { ExciseService } from "../../../../../common/services/excise.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { BreadCrumb } from "models/breadcrumb";
import { AuthService } from "services/auth.service";

declare var jQuery: any;
declare var $: any;
@Component({
  selector: 'app-receive-line-user',
  templateUrl: './receive-line-user.component.html',
  styleUrls: ['./receive-line-user.component.css']
})
export class ReceiveLineUserComponent implements OnInit {
  breadcrumb: BreadCrumb[] = [
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'การคัดเลือกราย', route: '#' },
    { label: 'รับกระดาษทำการคัดเลือกราย', route: '#' },
  ]
  sendLineUser: any;
  listItem: any[];
  before: any;
  last: any;
  month: any;
  from: any;
  analysNumber: any;
  exciseProductType: any;
  flag: any;
  coordinates: any;
  coordinatesArr: any;
  workSheetNumber: any;
  exciseId: any[];
  sector: any;
  sectorArr: any;
  role: string;
  loading: boolean = true;
  jobTitle: String;
  constructor(
    private route: ActivatedRoute,
    private messageBarService: MessageBarService,
    private ex: ExciseService,
    private router: Router,
    private ajax: AjaxService,
    private authService: AuthService
  ) {
    this.exciseId = [];
    const URL = "filter/exise/getOfficeCodeByUserLogin";
    this.ajax.post(URL, {}, res => {
      let officeCode = res.json();
      console.log(officeCode);
      if (officeCode.substr(0, 2) == "00") {
        this.role = 'C'
      } else {
        this.role = 'S'
        this.sector = 'ภาคที่ ' + (Number(officeCode.substr(0, 2)));
      }
    });
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('TAX-01040');
    console.log("ngOnInit");
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    //get coordinates in select option
    const URL = "combobox/controller/getCoordinates";
    this.ajax.post(URL, {}, res => {
      this.coordinatesArr = res.json();
    });

    //get Sector in select option
    const URL2 = "combobox/controller/getDropByTypeAndParentId";
    this.ajax.post(URL2, { type: "SECTOR_VALUE" }, res => {
      this.sectorArr = res.json();
    });

    const analysUrl = "combobox/controller/getAnalysNumber";
    this.ajax
      .post(analysUrl, {}, res => {
        this.analysNumber = res.json()[0];
      })
      .then(() => {
        this.directAccess(true);
      })
      .then(() => {
        $(".ui.dropdown").dropdown();
      });

    var {
      before,
      last,
      analysNumber,
      workSheetNumber,
      from,
      month,
      currYear
    } = this.ex.getToSendlineUser();

    //set values
    this.before = before;
    this.last = last;
    this.from = from;
    this.month = month;
    this.analysNumber = analysNumber;
    this.workSheetNumber = workSheetNumber;
  }

  userDetail() {
    this.ajax.get("restful/userDetail", res => {
      console.log("userDetail", res.json());
      let detail = res.json();
      this.jobTitle = detail.title;

    });
  }

  directAccess = (withOut?: any) => {
    const headerUrl = AjaxService.CONTEXT_PATH + "filter/exise/getStartEndDate";
    let analysNumber: any;
    if (withOut) {
      analysNumber = this.analysNumber;
      this.analysNumber = analysNumber;
    } else {
      analysNumber = this.analysNumber;
    }
    this.getWorkSheetNumber();
    $.post(headerUrl, { analysNumber: analysNumber }, res => {
      this.before = res[0];
      this.last = res[1];
      this.from = res[2];
      this.month = res[3];

      var currDate = new Date();
      var currYear = currDate.getFullYear() + 543;

      document.getElementById("trDrinamic").innerHTML =
        '<tr><th rowspan="2" style="text-align: center !important"><input type="checkbox" name="select-all" id="select-all"></th>' +
        '<th rowspan="2" style="text-align: center !important">ลำดับที่</th>' +
        '<th rowspan="2" style="text-align: center !important">ทะเบียนสรรพสามิต เดิม/ใหม่</th> ' +
        '<th rowspan="2" style="text-align: center !important">ชื่อผู้ประกอบการ</th> ' +
        '<th rowspan="2" style="text-align: center !important">ชื่อโรงอุตสาหกรรม/สถานบริการ</th> ' +
        '<th rowspan="2" style="text-align: center !important">ภาค</th> ' +
        '<th rowspan="2" style="text-align: center !important">พื้นที่</th> ' +
        '<th colspan="2" style="text-align: center !important">การชำระภาษีในสภาวะปกติ (บาท)</th> ' +
        '<th rowspan="2" style="text-align: center !important">เปลี่ยนแปลง (ร้อยละ)</th> ' +
        '<th rowspan="2" style="text-align: center !important">ชำระภาษี(เดือน)</th> ' +
        '<th colspan="3" style="text-align: center !important">การตรวจสอบภาษีย้อนหลัง 3 ปีงบประมาณ</th> ' +

        '<th rowspan="2" style="text-align: center !important">พิกัด</th> ' +
        '<th rowspan="2" style="text-align: center !important">ที่อยู่โรงอุตสาหกรรม/สถานบริการ</th> ' +
        '<th rowspan="2" style="text-align: center !important" >เลขทะเบียนสรรพสามิตเก่า</th> ' +
        '<th rowspan="2" style="text-align: center !important">สถานะล่าสุด</th> ' +
        '<th rowspan="2" style="text-align: center !important">สถานะ/วันที่</th> ' +
        "</tr>" +
        '<tr><th style="border-left: 1px solid rgba(34,36,38,.1);">' +
        this.month / 2 +
        " เดือนแรก</th>" +
        '<th style="text-align: center !important">' +
        this.month / 2 +
        " เดือนหลัง </th>" +
        '<th style="text-align: center !important">' +
        (currYear - 3) +
        "</th>" +
        '<th style="text-align: center !important">' +
        (currYear - 2) +
        "</th>" +
        '<th style="text-align: center !important">' +
        (currYear - 1) +
        "</th>" +
        "</tr>";

      return res;
    }).then(() => {
      this.initDatatable();
      this.loading = false;
    });
  };

  getWorkSheetNumber = () => {
    const workSheetUrl = "filter/exise/getWorkSheetNumber/";
    this.ajax.get(`${workSheetUrl}${this.analysNumber}`, res => {
      this.workSheetNumber = res.json();
    });
  };



  ngAfterViewInit() { }

  initDatatable(): void {
    const URL = AjaxService.CONTEXT_PATH + "/filter/exise/list";
    var sendLineUserCheckbox = (this.sendLineUser = $("#sendLineUser").DataTableTh({
      lengthChange: false,
      searching: false,
      ordering: false,
      processing: true,
      serverSide: true,
      scrollX: true,
      paging: false,
      pagingType: "full_numbers",

      ajax: {
        type: "POST",
        url: URL,
        data: {
          paging: false,
          flag: "S",
          viewStatus: this.role,
          productType: this.coordinates == undefined ? "" : this.coordinates,
          analysNumber: this.analysNumber,
          sector: this.sector
        }
      },
      columns: [
        {
          data: "exciseId",
          render: function (data, type, full, meta) {
            console.log(data);
            return `<input type="checkbox" name="chk${meta.row}" id="chk${
              meta.row
              }" value="${$("<div/>")
                .text(data)
                .html()}">`;

          },
          className: "center"
        },
        {
          data: "exciseId",
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "center"
        },
        { data: "exciseId", className: "center" },
        { data: "companyName" },
        { data: "companyName" },
        { data: "exciseOwnerArea1" },
        { data: "exciseOwnerArea" },
        { data: "firstMonth", className: "center" },
        { data: "lastMonth", className: "center" },
        { data: "percentage", className: "center" },
        { data: "totalMonth", className: "center" },
        { data: "no1" },
        { data: "no2" },
        { data: "no3" },

        { data: "productType" },
        { data: "factoryAddress" },
        { data: "registeredCapital" },
        { data: "exciseIdOld", className: "center" },
        { data: "status" }
      ]
    }));

    let tableId = "#userManagementDt";

    let backgroundRowColor = (data, type, row, meta) => {
      if (!data) {
        let table = $(tableId).DataTable();
        let cell = table.cell(meta.row, meta.col).node();
        $(cell).addClass("bg-row-highlight");
      }

      return data;
    };

    $("#select-all").on("click", function () {
      var rows = sendLineUserCheckbox.rows({ search: "applied" }).nodes();
      $('input[type="checkbox"]', rows).prop("checked", this.checked);
    });
  }

  linkToDetail() {
    this.router.navigate(["/add-external-data/add-data"], {
      queryParams: {
        id: (<HTMLInputElement>document.getElementById("exciseId")).value,
        num: this.analysNumber
      }
    });
  }

  onSend = e => {
    // Prevent actual form submission
    e.preventDefault();

    var flag = 'S';
    var viewStatus = '';
    if (this.role == 'C') {
      viewStatus = 'C';
      flag = 'F'
    } else {
      flag = 'C'
      viewStatus = 'S';
    }
    var data = this.sendLineUser.rows().data();
    for (let i = 0; i < data.length; i++) {
      if ((<HTMLInputElement>document.getElementById(`chk${i}`)).checked) {
        if (data[i].central == 'Y' && data[i].sector == 'Y') {

          flag = 'F';
        }
        this.exciseId.push(data[i].exciseId);

      }
    } //end for loops

    if (this.exciseId.length != 0) {
      const URL = "filter/exise/approveExciseId";
      this.ajax.post(
        URL,
        {
          analysNumber: this.analysNumber,
          exiceList: this.exciseId,
          flag: flag,

          viewStatus: viewStatus
        },
        res => {
          var data = res.json();
          if (data.messageId == 3) {
            this.router.navigate(['/add-external-data/approve-line-user']);
            // this.messageBarService.successModal(data.messageTh, "สำเร็จ");
            // this.sendLineUser.destroy();
            // this.initDatatable();
          } else {
            this.messageBarService.errorModal(data.messageTh, "เกิดข้อผิดพลาด");
          }
        }
      );
    } else {
      this.messageBarService.errorModal(
        "กรุณาเลือกข้อมูลอย่างน้อย 1 ชุด",
        "เกิดข้อผิดพลาด"
      );
    }
  };

  changeCoordinates = () => {
    this.coordinates = (<HTMLInputElement>(
      document.getElementById("coordinates")
    )).value;
    this.sendLineUser.destroy();
    this.initDatatable();
  };

  changeSector = () => {
    this.sector = (<HTMLInputElement>document.getElementById("sector")).value;
    this.sendLineUser.destroy();
    this.initDatatable();
  };

  appriveData = () => {

    this.messageBarService.comfirm((res) => {
      if (res) {
        this.router.navigate(['/add-external-data/approve-line-user']);
      }
    }, '', "ยืนยันการลบ");
  }
}
