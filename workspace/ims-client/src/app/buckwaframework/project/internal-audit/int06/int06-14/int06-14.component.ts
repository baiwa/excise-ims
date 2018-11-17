import { Component, OnInit } from "@angular/core";
import { BreadCrumb } from "models/breadcrumb";
import { Int0614Service } from "./int06-14.service";
import { Observable } from "rxjs/Observable";
import { ComboBox } from "models/combobox";
import { Utils } from "helpers/utils";

import { TextDateTH, formatter, toDateLocale } from "helpers/datepicker";
import { ComboBoxService } from "helpers/comboBox";
import { AjaxService } from "services/ajax.service";
import { FormGroup, Validators, FormBuilder } from "@angular/forms";
import { AuthService } from "services/auth.service";
import { MessageBarService } from "services/message-bar.service";
import { MessegeConstants } from "helpers/messegeConstants";
declare var $: any;

const URL = {
  SEARCH: AjaxService.CONTEXT_PATH + "ia/int0614/search"
};

@Component({
  selector: "app-int06-14",
  templateUrl: "./int06-14.component.html",
  styleUrls: ["./int06-14.component.css"],
  providers: [Int0614Service, ComboBoxService]
})
export class Int0614Component implements OnInit {
  [x: string]: any;
  breadcrumb: BreadCrumb[];
  searchForm: FormGroup;
  // withdrawRequestList: Observable<ComboBox>;
  sectorList: any;
  areaList: any;
  submitted: boolean = false;
  departmentList: any;
  accounts: any;

  travelTo1List: Observable<ComboBox>;
  travelTo2List: Observable<ComboBox>;
  travelTo3List: any;

  dataTable: any;
  comboBox1: any;
  comboBox2: any;
  searchData: any;

  constructor(
    private selfService: Int0614Service,
    private ajax: AjaxService,
    private authService: AuthService,
    private fb: FormBuilder,
    private msg: MessageBarService,
    private combobox: ComboBoxService
  ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบเบิกจ่าย", route: "#" },
      { label: "ตรวจสอบจำนวนครั้งในการเบิก", route: "#" }
    ];
    this.searchData = {
      combo1: "",
      combo2: "",
      dateStart: "",
      dateEnd: "",
      withdrawAmount: ""
    };
    // this.withdrawRequestList = selfService.dropdown("WITHDRAW_REQUEST", null);
    this.travelTo1List = this.selfService.dropdown("SECTOR_VALUE", null);
  }

  setVariable() {
    this.searchForm = this.fb.group({
      combo1: ["", Validators.required],
      combo2: ["", Validators.required],
      dateStart: ["", Validators.required],
      dateEnd: ["", Validators.required],
      withdrawAmount: ["", Validators.required]
    });
  }

  //func check validator
  validateField(value: string) {
    return this.submitted && this.searchForm.get(value).errors;
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram("INT-0614");
    this.setVariable();
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown ai").css("width", "100%");
    // this.selfService.superdataTable();
  }

  ngAfterViewInit(): void {
    this.Datatable();
    this.calendar();
  }

  travelTo2Dropdown = e => {
    let id = e.target.value;
    this.travelTo2List = this.selfService.dropdown("SECTOR_VALUE", id);
  };

  calendar = () => {
    $("#dateF").calendar({
      endCalendar: $("#dateT"),
      type: "month",
      text: TextDateTH,
      formatter: formatter("month-year"),
      onChange: (date, mm_yyyy) => {
        this.searchForm.get("dateStart").setValue(mm_yyyy);
      }
    });

    $("#dateT").calendar({
      startCalendar: $("#dateF"),
      type: "month",
      text: TextDateTH,
      formatter: formatter("month-year"),
      onChange: (date, mm_yyyy) => {
        this.searchForm.get("dateEnd").setValue(mm_yyyy);
      }
    });
  };

  handleSearch(e) {
    console.log(this.searchForm.value.combo1);
    e.preventDefault();
    this.submitted = true;
    // stop here if form is invalid
    if (this.searchForm.invalid) {
      this.msg.errorModal("กรุณากรอกข้อมูลให้ครบ");
      return;
    }

    this.searchData = {
      combo1: this.searchForm.value.combo1,
      combo2: this.searchForm.value.combo2,
      dateStart: this.searchForm.value.dateStart,
      dateEnd: this.searchForm.value.dateEnd,
      withdrawAmount: this.searchForm.value.withdrawAmount
    };

    this.Datatable();
  }

  onReset() {
    $("#combo1").dropdown("restore defaults");
    $("#combo2").dropdown("restore defaults");
    $("#dateStart").dropdown("restore defaults");
    this.searchForm.get("withdrawAmount").setValue("");
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

    let renderType = function(data, type, row, meta) {
      console.log(data);
      var result = "";
      if (data == MessegeConstants.WIDTHDRAW.HOME_CODE) {
        result = MessegeConstants.WIDTHDRAW.HOME;
      }
      if (data == MessegeConstants.WIDTHDRAW.MEDICAL_CODE) {
        result = MessegeConstants.WIDTHDRAW.MEDICAL;
      }
      if (data == MessegeConstants.WIDTHDRAW.TUITO_CODE) {
        result = MessegeConstants.WIDTHDRAW.TUITO;
      }
      return result;
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
          render: function(data, type, full, meta) {
            return `<div class="ui checkbox"> <input type="checkbox" name="chk-${
              meta.row
            }" id="chk-${meta.row}" value="${$("<div/>")
              .text(full.qtnHeaderId)
              .html()}"> <label></label></div>`;
          }
        },
        {
          render: function(data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          }
        },
        {
          data: "name",
          render: renderString
        },
        {
          data: "order",
          render: renderType
        },
        {
          data: "withdrawAmount",
          render: renderNumber
        }
      ],
      columnDefs: [
        {
          targets: [0, 1],
          className: "center"
        },
        {
          targets: [4],
          className: "right"
        }
      ]

      // rowCallback: (row, data, index) => {
      //   console.log(data);
      // }
    });
  }

  clickCheckAll = event => {
    if (event.target.checked) {
      var node = $("#dataTable")
        .DataTable()
        .rows()
        .nodes();
      $.each(node, function(index, value) {
        $(this)
          .find("input")
          .prop("checked", true);
      });
    } else {
      var node = $("#dataTable")
        .DataTable()
        .rows()
        .nodes();
      $.each(node, function(index, value) {
        $(this)
          .find("input")
          .prop("checked", false);
      });
    }
  };

  export = e => {
    e.preventDefault();
    let dataSave = [];

    let node = this.dataTable.rows().nodes();
    $.each(node, (index, value) => {
      if ($('input[type="checkbox"]').is(":checked")) {
        let data = this.dataTable.rows().data()[index];
        dataSave.push(data);
        console.log("dataSave : ", dataSave);
      }
    });
  };
}
