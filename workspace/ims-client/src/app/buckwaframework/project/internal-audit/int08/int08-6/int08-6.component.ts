import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Int0806Service } from "./int08-6.service";
import { AuthService } from "services/auth.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { BreadCrumb } from "models/breadcrumb";
import { MessageBarService } from "services/message-bar.service";
import { ComboBoxService } from "helpers/comboBox";
import { TextDateTH, formatter, toDateLocale } from "helpers/datepicker";
import { Utils } from "helpers/utils";
import { AjaxService } from "services/ajax.service";
import { Observable } from "rxjs";
import { ComboBox } from "models/combobox";

const URL = {
  SEARCH: AjaxService.CONTEXT_PATH + "ia/int0806/search"
};

declare var $: any;
@Component({
  selector: "app-int08-6",
  templateUrl: "./int08-6.component.html",
  styleUrls: ["./int08-6.component.css"],
  providers: [Int0806Service, ComboBoxService]
})
export class Int086Component implements OnInit, AfterViewInit {
  breadcrumb: BreadCrumb[];
  searchForm: FormGroup;
  submitted: boolean = false;
  departmentList: any;
  accounts: any;
  dataTable: any;
  comboBox1: any;
  comboBox2: any;
  searchData: any;

  constructor(
    private selfService: Int0806Service,
    private combobox: ComboBoxService,
    private authService: AuthService,
    private ajax: AjaxService,
    private fb: FormBuilder,
    private msg: MessageBarService
  ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบรายได้", route: "#" },
      {
        label: "การตรวจสอบการนำเงินฝากเข้าบัญชีพักหน่วยงานและกองทุน",
        route: "#"
      }
    ];
    this.departmentList = this.selfService.LovGetValue1("SECTOR_VALUE");
    this.accounts = this.combobox.Lov("ACCOUNT", null);
    this.selfService
      .pullComboBox("SECTOR_VALUE", "comboBox1")
      .subscribe(data => {
        this.comboBox1 = data;
      });
    this.searchData = {
      startDate: "",
      endDate: "",
      account: "",
      combo1: "",
      combo2: ""
    };
  }
  setVariable() {
    this.searchForm = this.fb.group({
      startDate: [""],
      endDate: [""],
      account: ["", Validators.required],
      combo1: ["", Validators.required],
      combo2: ["", Validators.required]
    });
  }

  //func check validator
  validateField(value: string) {
    return this.submitted && this.searchForm.get(value).errors;
  }

  ngOnInit() {
    this.Datatable();
    this.authService.reRenderVersionProgram("INT-0806");
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown ai").css("width", "100%");
    this.setVariable();
    this.calendar();
  }

  ngAfterViewInit(): void {
    this.Datatable();
  }

  calendar = function() {
    $("#calendar").calendar({
      endCalendar: $("#calendar1"),
      type: "month",
      text: TextDateTH,
      formatter: formatter("ดป"),
      onChange: (date, mmyyyy) => {
        let month = date.getMonth();
        let year = toDateLocale(date)[0].split("/")[2];
        let dateStr = "1" + "/" + (month + 1) + "/" + year;
        this.searchForm.get("startDate").setValue(dateStr);
      }
    });

    $("#calendar1").calendar({
      // minDate: new Date(),
      startCalendar: $("#calendar"),
      type: "month",
      text: TextDateTH,
      formatter: formatter("ดป"),
      onChange: (date, mmyyyy) => {
        let month = date.getMonth();
        let year = toDateLocale(date)[0].split("/")[2];
        let dateStr = "1" + "/" + (month + 1) + "/" + year;
        this.searchForm.get("endDate").setValue(dateStr);
      }
    });
  };

  dropdown(e, combo: string) {
    e.preventDefault();
    this.selfService
      .pullComboBox("SECTOR_VALUE", combo, e.target.value)
      .subscribe(data => {
        this.comboBox2 = data;
      });
  }

  handleSearch(e) {
    e.preventDefault();
    this.submitted = true;
    // stop here if form is invalid
    if (this.searchForm.invalid) {
      this.msg.errorModal("กรุณากรอกข้อมูลให้ครบ");
      return;
    }
    let subT1 = this.comboBox1.filter(
      obj => obj.lovId == this.searchForm.value.combo1
    );
    let subT2 = this.comboBox2.filter(
      obj => obj.lovId == this.searchForm.value.combo2
    );

    this.searchData = {
      startDate: this.searchForm.value.startDate,
      endDate: this.searchForm.value.endDate,
      account: this.searchForm.value.account,
      combo1: subT1[0].subType,
      combo2: subT2[0].subType
    };
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

    //render check string is null or empty
    let renderString = function(data, type, row, meta) {
      if (Utils.isNull(data)) {
        data = "-";
      }
      return data;
    };

    this.dataTable = $("#dataTable").DataTableTh({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: false,
      paging: true,
      ajax: {
        type: "POST",
        url: URL.SEARCH,
        data: this.searchData
      },
      columns: [
        {
          render: function(data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          }
        },
        {
          data: "receiptNo",
          render: renderString
        },
        {
          data: "depositDateStr",
          render: renderString
        },
        {
          data: "nettaxAmount",
          render: renderNumber
        },
        {
          data: "netlocAmount",
          render: renderNumber
        },
        {
          render: function(data, type, full, meta) {
            let icon = "-";
            if (full.statusDate === "S") {
              icon = `<i class="check icon" style="color:green"> </i>`;
            } else if (full.statusDate === "F") {
              icon = `<i class="close icon" style="color:red"> </i>`;
            }
            return icon;
          }
        },
        {
          render: function(data, type, full, meta) {
            let icon = "-";
            if (full.statusMoney === "S") {
              icon = `<i class="check icon" style="color:green"> </i>`;
            } else if (full.statusMoney === "F") {
              icon = `<i class="close icon" style="color:red"> </i>`;
            }
            return icon;
          }
        }
      ],
      columnDefs: [
        {
          targets: [0, 1, 2, 5, 6],
          className: "center"
        },
        {
          targets: [3, 4],
          className: "right"
        }
      ]
    });
  }
}
