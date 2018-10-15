import { Component, OnInit, AfterViewInit } from "@angular/core";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
declare var $: any;
@Component({
  selector: 'int01-2-3',
  templateUrl: './int01-2-3.component.html',
  styleUrls: ['./int01-2-3.component.css']
})
export class Int0123Component implements OnInit {
  datatable: any;
  offCode: any;
  startDate: any;
  endDate: any;
  dataTable1: any[];
  dataTableList: any[];
  dataTable2: any[];
  dataTable3: any[];
  table1: any;
  table2: any;
  table3: any;
  constructor(private router: Router,
    private ajax: AjaxService,
    private route: ActivatedRoute,
    private messageBarService: MessageBarService) {

  }

  ngOnInit() {
    this.offCode = this.route.snapshot.queryParams["offCode"];
    this.startDate = this.route.snapshot.queryParams["startDate"];
    this.endDate = this.route.snapshot.queryParams["endDate"];
    this.dataTable1 = [];
    this.dataTable2 = [];
    this.dataTable3 = [];
    this.ajax.post("ia/int012/searchCurrentLicense", {
      offCode: this.offCode, startDate: this.startDate, endDate: this.endDate
    }, res => {
      this.dataTableList = res.json();
      this.dataTableList.forEach(element => {
        console.log(element);
        if (element.licName.indexOf("สุรา") >= 0) {
          this.dataTable1.push(element);
        } else if (element.licName.indexOf("ยาสูบ") >= 0) {
          this.dataTable2.push(element);
        } else if (element.licName.indexOf("ไพ่") >= 0) {
          this.dataTable3.push(element);
        }
      });
      this.inittable1();
      this.inittable2();
      this.inittable3();

    });

  }

  inittable1() {
    if (this.table1 != null && this.table1 != undefined) {
      this.table1.destroy();
    }
    console.log(this.dataTable1);
    this.table1 = $("#table1").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: false,
      paging: false,
      data: this.dataTable1,
      columns: [

        {
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "center"
        },
        //  { data: "incomeCode" },
        { data: "licName" },
        { data: "printCode" },
        { data: "licType" },
        { data: "licPrice" },
        { data: "licAmount" }




      ], columnDefs: [

        { targets: [4], className: "right aligned" }

      ]

    });
  }
  inittable2() {
    if (this.table2 != null && this.table2 != undefined) {
      this.table2.destroy();
    }
    console.log(this.dataTable2);
    this.table2 = $("#table2").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: false,
      paging: false,
      data: this.dataTable2,
      columns: [

        {
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "center"
        },
        //  { data: "incomeCode" },
        { data: "licName" },
        { data: "printCode" },
        { data: "licType" },
        { data: "licPrice" },
        { data: "licAmount" }




      ], columnDefs: [

        { targets: [4], className: "right aligned" }

      ]

    });
  }
  inittable3() {
    if (this.table3 != null && this.table3 != undefined) {
      this.table3.destroy();
    }
    console.log(this.dataTable3);
    this.table3 = $("#table3").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: false,
      paging: false,
      data: this.dataTable3,
      columns: [

        {
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "center"
        },
        { data: "licName" },
        { data: "printCode" },
        { data: "licType" },
        { data: "licPrice" },
        { data: "licAmount" }




      ], columnDefs: [

        { targets: [4], className: "right aligned" }

      ]

    });
  }

  backPage() {
    this.router.navigate(["/int01/2/2"], {
      queryParams: {
        ofCode: this.offCode,
        startDate: this.startDate,
        endDate: this.endDate
      }
    });
  }

}
