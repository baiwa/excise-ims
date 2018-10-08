import { Component, OnInit, AfterViewInit } from "@angular/core";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
declare var $: any;
@Component({
  selector: "int01-2-2",
  templateUrl: "./int01-2-2.component.html",
  styleUrls: ["./int01-2-2.component.css"]
})
export class Int0122Component implements OnInit {
  private showData: boolean = false;
  offCode: any;
  startDate: any;
  endDate: any;
  dataTableList: any[];

  datatable: any;
  constructor(private router: Router,
    private ajax: AjaxService,
    private route: ActivatedRoute,
    private messageBarService: MessageBarService) {
    this.dataTableList = [];
    this.offCode = this.route.snapshot.queryParams["ofCode"];
    this.startDate = this.route.snapshot.queryParams["startDate"];
    this.endDate = this.route.snapshot.queryParams["endDate"];


  }

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    $("#idint").hide();
    $("#id").hide();
    console.log('ofCode', this.offCode);
    this.ajax.post("ia/int012/searchLicense", {
      offCode: this.offCode, startDate: this.startDate, endDate: this.endDate
    }, res => {
      this.dataTableList = res.json();
      console.log(this.dataTableList);
      this.initDatatable();
    });
  }


  popupEditData() {
    $("#modalEditData").modal("show");
    $("#modalInt062").modal("show");
    $("#idint").show();
    $("#id").show();
    $("#selectTrading").show();
  }

  closePopupEdit() {
    $("#selectTrading").show();
    $("#modalEditData").modal("hide");
    $("#modalInt062").modal("hide");
  }

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }

  initDatatable(): void {
    if (this.datatable != null) {
      this.datatable.destroy();
    }
    const URL = AjaxService.CONTEXT_PATH + "ia/int083/searchRiskAssExcAreaHdr";
    this.datatable = $("#dataTable").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: false,
      paging: false,
      data: this.dataTableList,
      columns: [

        {
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "center"
        },
        { data: "licDate" },
        { data: "sendDate" },
        { data: "licName" },
        { data: "printCode" },
        { data: "licNo" },
        { data: "licPrice" },
        { data: "licAmount" },
        { data: "licFee" },
        { data: "licInterior" },
        { data: "licName" },
        { data: "startDate" },
        { data: "expDate" }


      ],
      columnDefs: [
        { targets: [0, 1, 2], className: "center aligned" }
      ]
    });
  }
}
