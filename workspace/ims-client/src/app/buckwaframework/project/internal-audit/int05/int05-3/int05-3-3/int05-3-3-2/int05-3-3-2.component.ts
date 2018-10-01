import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Location } from "@angular/common";
import { AjaxService } from "../../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { Alert } from "../../../../../../../../../node_modules/@types/selenium-webdriver";
import { AssetBalance, AssetMaintenance } from "../../../../../../common/models";

declare var $: any;
@Component({
  selector: 'app-int05-3-3-2',
  templateUrl: './int05-3-3-2.component.html',
  styleUrls: ['./int05-3-3-2.component.css']
})
export class Int05332Component implements OnInit {


  assetBalance: AssetBalance;

  startDate: any;
  endDate: any;
  deleteList: any[] = [];
  datatable: any;
  id: any;
  act: any;
  constructor(private router: Router,
    private route: ActivatedRoute,
    private ajax: AjaxService,
    private messageBarService: MessageBarService) {

    this.assetBalance = new AssetBalance();

    this.id = this.route.snapshot.queryParams["id"];
  }

  ngOnInit() {
    this.ajax.post("ia/int0533/findAssetBalance", { assetBalanceId: this.id }, res => {
      let value = res.json();
      this.assetBalance = value.assetBalance;
    });

    this.initDatatable();
  }

  createAssetBalance() {
    this.router.navigate(['int05/3/3/1'], {

    });
  }
  initDatatable(): void {
    if (this.datatable != null && this.datatable != undefined) {
      this.datatable.destroy();
    }
    const URL = AjaxService.CONTEXT_PATH + "ia/int0533/dataTableAssetMaintenance";
    console.log(this.assetBalance);
    this.datatable = $("#dataTable").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: true,
      paging: false,
      ajax: {
        type: "POST",
        url: URL,
        data: { assetBalanceId: this.id }
      },
      columns: [

        {
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "center"
        },
        // { data: "maintenanceDate" },
        {
          render: function (data, type, row, meta) {

            if (row.maintenanceDate != null && row.maintenanceDate != undefined && row.maintenanceDate != '') {
              var dateTime = new Date(row.maintenanceDate).toLocaleString("th-TH");
              return dateTime.split(' ')[0];
            } else {
              return row.maintenanceDate;
            }
          },
          className: "center"
        },
        { data: "maintenanceDescription" },
        { data: "maintenancePrice" },
        { data: "maintenanceNote" }




      ],
      columnDefs: [
        { targets: [0, 1, 3, 4], className: "center aligned" },

      ]


    });
  }



}

