import { Component, OnInit, AfterViewInit } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';
import { AuthService } from 'services/auth.service';
import { RentHouseRule, Int0613Service } from './int06-13.service';
import { FormGroup, FormArray, FormBuilder, FormControl } from '@angular/forms';
import { AjaxService } from 'services/ajax.service';
import { Utils } from 'helpers/utils';
declare var $: any;

@Component({
  selector: 'app-int06-13',
  templateUrl: './int06-13.component.html',
  styleUrls: ['./int06-13.component.css']
})
export class Int0613Component implements OnInit, AfterViewInit {

  breadcrumb: BreadCrumb[];
  data: RentHouseRule[];
  dataEdit: RentHouseRule[];

  datatable1: any;
  datatable2: any;
  datatable3: any;
  datatable4: any;

  constructor(
    private authService: AuthService,
    private service: Int0613Service,
    private ajax: AjaxService,
    private formBuilder: FormBuilder
  ) {

    // BreadCrumb
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบเบิกจ่าย", route: "#" },
      { label: "กำหนดค่าเช่าบ้านข้าราชการ", route: "#" },
    ];

  }

  ngOnInit(): void {
    this.dataEdit = [];
    this.authService.reRenderVersionProgram('INT-061300'); // Page Version

    // this.service.getRent().then(data => {

    //   this.data = data; // Data from Backend
    //   console.log(this.data); 
    //   //this.initDatatable()
    //  });

  }

  ngAfterViewInit(): void {
    this.initDatatable1();
    this.initDatatable2();
    this.initDatatable3();
    this.initDatatable4();
  }

  //  initDatatable1
  initDatatable1(): void {
    const URL = AjaxService.CONTEXT_PATH + "ia/int061300/findAdministrativePosition";
    this.datatable1 = $("#dataTable1").DataTableTh({
      lengthChange: false,
      searching: false,
      loading: true,
      ordering: false,
      pageLength: false,
      processing: true,
      serverSide: true,
      paging: false,
      ajax: {
        type: "POST",
        url: URL,
        data: {}
      },
      columns: [
        { data: "levels" },
        {
          data: "salaryFrom",
          render: (data) => {
            let money = '';
            money = data !== null ? Utils.moneyFormatDecimal(data) : '-';
            return money;
          }
        },
        {
          data: "salaryTo",
          render: (data) => {
            let money = '';
            money = data !== null ? Utils.moneyFormatDecimal(data) : '-';
            return money;
          }
        },
        {
          data: "rentAmount",
          render: (data) => {
            let money = '';
            money = data !== null ? Utils.moneyFormatDecimal(data) : '-';
            return money;
          }
        },
        {
          data: "ruleId",
          render: function () {
            return '<button type="button" class="ui mini button yellow edit"><i class="edit icon"></i> แก้ไข</button>';
          }
        }
      ],
      columnDefs: [
        { targets: [], className: "left aligned" },
        { targets: [0, 4], className: "center aligned" },
        { targets: [1, 2, 3], className: "right aligned" },
      ],

      createdRow: function (row, data, dataIndex) {
      },
      rowCallback: (row, data, index) => {
        $("td > .edit", row).bind("click", () => {
          console.log(data);
          console.log("edit");
          console.log(data.ruleId);

          $('#editData').modal('show');
          this.dataEdit = data;
         
        });
      }

    });
  }

  //  initDatatable2
  initDatatable2(): void {
    const URL = AjaxService.CONTEXT_PATH + "ia/int061300/findDirectorPosition";
    this.datatable2 = $("#dataTable2").DataTableTh({
      lengthChange: false,
      searching: false,
      loading: true,
      ordering: false,
      pageLength: false,
      processing: true,
      serverSide: true,
      paging: false,
      ajax: {
        type: "POST",
        url: URL,
        data: {}
      },
      columns: [
        { data: "levels" },
        {
          data: "salaryFrom",
          render: (data) => {
            let money = '';
            money = data !== null ? Utils.moneyFormatDecimal(data) : '-';
            return money;
          }
        },
        {
          data: "salaryTo",
          render: (data) => {
            let money = '';
            money = data !== null ? Utils.moneyFormatDecimal(data) : '-';
            return money;
          }
        },
        {
          data: "rentAmount",
          render: (data) => {
            let money = '';
            money = data !== null ? Utils.moneyFormatDecimal(data) : '-';
            return money;
          }
        },
        {
          data: "ruleId",
          render: function () {
            return '<button type="button" class="ui mini button yellow edit"><i class="edit icon"></i> แก้ไข</button>';
          }
        }
      ],
      columnDefs: [
        { targets: [], className: "left aligned" },
        { targets: [0, 4], className: "center aligned" },
        { targets: [1, 2, 3], className: "right aligned" },
      ],

      createdRow: function (row, data, dataIndex) {
      },
      rowCallback: (row, data, index) => {
        $("td > .edit", row).bind("click", () => {
          console.log("edit");
          console.log(data.ruleId);

          $('#editData').modal('show');
          this.dataEdit = data
        });
      }

    });
  }


  //  initDatatable3
  initDatatable3(): void {
    const URL = AjaxService.CONTEXT_PATH + "ia/int061300/findAcademicPosition";
    this.datatable3 = $("#dataTable3").DataTableTh({
      lengthChange: false,
      searching: false,
      loading: true,
      ordering: false,
      pageLength: false,
      processing: true,
      serverSide: true,
      paging: false,
      ajax: {
        type: "POST",
        url: URL,
        data: {}
      },
      columns: [
        { data: "levels" },
        {
          data: "salaryFrom",
          render: (data) => {
            let money = '';
            money = data !== null ? Utils.moneyFormatDecimal(data) : '-';
            return money;
          }
        },
        {
          data: "salaryTo",
          render: (data) => {
            let money = '';
            money = data !== null ? Utils.moneyFormatDecimal(data) : '-';
            return money;
          }
        },
        {
          data: "rentAmount",
          render: (data) => {
            let money = '';
            money = data !== null ? Utils.moneyFormatDecimal(data) : '-';
            return money;
          }
        },
        {
          data: "ruleId",
          render: function () {
            return '<button type="button" class="ui mini button yellow edit"><i class="edit icon"></i> แก้ไข</button>';
          }
        }
      ],
      columnDefs: [
        { targets: [], className: "left aligned" },
        { targets: [0, 4], className: "center aligned" },
        { targets: [1, 2, 3], className: "right aligned" },
      ],

      createdRow: function (row, data, dataIndex) {
      },
      rowCallback: (row, data, index) => {
        $("td > .edit", row).bind("click", () => {
          console.log("edit");
          console.log(data.ruleId);

          $('#editData').modal('show');
          this.dataEdit = data
        });
      }

    });
  }

  //  initDatatable3
  initDatatable4(): void {
    const URL = AjaxService.CONTEXT_PATH + "ia/int061300/findGeneralPosition";
    this.datatable4 = $("#dataTable4").DataTableTh({
      lengthChange: false,
      searching: false,
      loading: true,
      ordering: false,
      pageLength: false,
      processing: true,
      serverSide: true,
      paging: false,
      ajax: {
        type: "POST",
        url: URL,
        data: {}
      },
      columns: [
        { data: "levels" },
        {
          data: "salaryFrom",
          render: (data) => {
            let money = '';
            money = data !== null ? Utils.moneyFormatDecimal(data) : '-';
            return money;
          }
        },
        {
          data: "salaryTo",
          render: (data) => {
            let money = '';
            money = data !== null ? Utils.moneyFormatDecimal(data) : '-';
            return money;
          }
        },
        {
          data: "rentAmount",
          render: (data) => {
            let money = '';
            money = data !== null ? Utils.moneyFormatDecimal(data) : '-';
            return money;
          }
        },
        {
          data: "ruleId",
          render: function () {
            return '<button type="button" class="ui mini button yellow edit"><i class="edit icon"></i> แก้ไข</button>';
          }
        }
      ],
      columnDefs: [
        { targets: [], className: "left aligned" },
        { targets: [0, 4], className: "center aligned" },
        { targets: [1, 2, 3], className: "right aligned" },
      ],

      createdRow: function (row, data, dataIndex) {
      },
      rowCallback: (row, data, index) => {
        $("td > .edit", row).bind("click", () => {
          console.log("edit");
          console.log(data.ruleId);

          $('#editData').modal('show');
          this.dataEdit = data
        });
      }

    });
  }

  editData(ruleId, code, salaryFrom, salaryTo, rentAmount) {
    $('#editData').modal('hide');
    
    console.log(ruleId, code, salaryFrom, salaryTo, rentAmount);

    const URL = "ia/int061300/update";

    var salaryFromEdit = salaryFrom === '-' || salaryFrom === '' ? null : salaryFrom;
    var salaryToEdit = salaryTo === '-' || salaryTo === '' ? null : salaryTo;
    var rentAmountEdit = rentAmount === '-' || rentAmount === '' ? null : rentAmount;

    this.ajax.post(URL, { ruleId: ruleId, salaryFrom: salaryFromEdit, salaryTo: salaryToEdit, rentAmount: rentAmountEdit }, res => {

     
      if (code === "1001") {
        this.datatable1.destroy();
        this.initDatatable1();
      } else if (code === "1002") {
        this.datatable2.destroy();
        this.initDatatable2();
      } else if (code === "1003") {
        this.datatable3.destroy();
        this.initDatatable3();
      } else if (code === "1004") {
        this.datatable4.destroy();
        this.initDatatable4();
      }

     
    });
  }


  onKey = (e) => {
  return Utils.moneyFormatDecimal(e);
  }


  typeNumber(e) {
    return Utils.onlyNumber(e);
  }

}
