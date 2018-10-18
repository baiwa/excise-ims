import { Component, OnInit } from "@angular/core";
import { AjaxService } from "services/ajax.service";
import { TextDateTH, formatter } from "helpers/datepicker";
import { Router, ActivatedRoute } from "@angular/router";
import { MessageBarService } from "services/message-bar.service";
import { AuthService } from "services/auth.service";
import { BreadCrumb } from "models/breadcrumb";
declare var $: any;
const URL = {
  DROPDOWN: "combobox/controller/getDropByTypeAndParentId",
  DATA_WS: "ia/int018/exciseWebService8020"
};
@Component({
  selector: "app-int01-8-1",
  templateUrl: "./int01-8-1.component.html",
  styleUrls: ["./int01-8-1.component.css"]
})


export class Int0181Component implements OnInit {
  private selectedProduct: string = "สำนักงานสรรพสามิตพื้นที่เมืองพิษณุโลก ";
  private productList: any[];

  travelTo1List: any[] = [];
  travelTo2List: any[] = [];
  travelTo3List: any[] = [];
  sector: string;
  area: string;
  local: string;
  startDate: string;
  endDate: string;

  comboboxType: string = 'SECTOR_VALUE';
  startDatePic: any;
  endDatePic: any;
  listWs8020List: any[];
  pageWs8020List: any[];
  pagesize: number = 1000;
  breadcrumb: BreadCrumb[] = [
    { label: "ตรวจสอบภายใน", route: "#" },
    { label: "การตรวจสอบรายได้", route: "#" },
    { label: "ตรวจสอบการนำฝาก/ส่งเงินรายได้", route: "#" },
  ];
  datatable: any;
  constructor(private router: Router,
    private ajax: AjaxService,
    private route: ActivatedRoute,
    private messageBarService: MessageBarService,
    private authService: AuthService) {


  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-01810');
    this.hideData();

    this.startDatePic = $("#calendar1").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter(),
      onChange: (date, text) => {

        var dd = date.getDate() + "".length == 1 ? "0" + date.getDate() : date.getDate();
        console.log(dd);
        var mm = ((date.getMonth() + 1) + "").length == 1 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1;
        var yyyy = date.getFullYear();
        this.startDate = yyyy + "" + mm + "" + dd;
        console.log(this.startDate);
      }

    });

    this.endDatePic = $("#calendar2").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter(),
      onChange: (date, text) => {

        var dd = date.getDate() + "".length == 1 ? "0" + date.getDate() : date.getDate();
        console.log(dd);
        var mm = ((date.getMonth() + 1) + "").length == 1 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1;
        var yyyy = date.getFullYear();
        this.endDate = yyyy + "" + mm + "" + dd;
        console.log(this.endDate);
      }
    });


    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.search").css("width", "100%");

    this.travelTo1Dropdown();
  }

  travelTo1Dropdown = () => {
    this.ajax.post(URL.DROPDOWN, { type: this.comboboxType }, res => {
      this.travelTo1List = res.json();
    });
  }

  travelTo2Dropdown = e => {
    this.area = '00';
    this.local = '00';
    var id = e.target.value;
    if (id != "" && id != "00") {
      this.ajax.post(URL.DROPDOWN, { type: this.comboboxType, lovIdMaster: id }, res => {
        this.travelTo2List = res.json();
        this.travelTo3List = [];
      });
    }
  }

  travelTo3Dropdown = e => {
    this.local = '00';
    var id = e.target.value;
    if (id != "" && id != "00") {
      this.ajax.post(URL.DROPDOWN, { type: this.comboboxType, lovIdMaster: id }, res => {
        this.travelTo3List = res.json();
      });
    }
  }
  hideData() {
    this.area = '00';
    this.local = '00';
    this.sector = '00';
    this.travelTo1List = [];
    this.travelTo2List = [];
    this.travelTo3List = [];
    $('calendar1').calendar('setDate', null);
    this.startDate = '';
    this.endDate = '';
    this.travelTo1Dropdown();
    $('#Int0181').hide();

  }
  async showData() {
    console.log();
    $('#Int0181').show();

    let officeCode = this.sector + this.area + this.local;
    this.listWs8020List = [];
    this.wsService(officeCode, 1);

  }
  wsService(officeCode, index) {
    this.ajax.post(URL.DATA_WS, {
      officeCode: officeCode,
      yearMonthFrom: this.startDate,
      yearMonthTo: this.endDate,
      dateType: "Income",
      pageNo: index

    }, res => {
      let wsData = res.json();
      wsData.forEach(element => {
        //console.log(element);
        this.listWs8020List.push(element);
      });
      if (wsData.length == 1000) {
        index++;
        this.wsService(officeCode, index);
        console.log(this.listWs8020List.length);
      } else {
        console.log("End : " + this.listWs8020List.length);
        this.initDatatable();
      }


    });
  }

  initDatatable(): void {
    if (this.datatable != null) {
      this.datatable.destroy();
    }
    console.log(this.listWs8020List[0]);
    this.datatable = $("#dataTable").DataTable({
      lengthChange: true,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: false,
      paging: true,
      data: this.listWs8020List,
      columns: [

        {
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "center"
        },
        {
          render: function (data, type, row, meta) {

            console.log("ROW", row.TrnDate);
            if (row.TrnDate != null && row.TrnDate != undefined && row.TrnDate != '') {
              return row.TrnDate.substr(6) + '/' + row.TrnDate.substr(5, 6) + '/' + row.TrnDate.substr(0, 4);
            } else {
              return '-';
            }


          },
          className: "center"
        },
        //{ data: "TrnDate" },
        {
          render: function (data, type, row, meta) {

            return row.NettaxAmount + row.NetLocAmount;

          },
          className: "right"
        },
        {
          render: function (data, type, row, meta) {

            console.log("ROW", row.DepositDate);
            if (row.DepositDate != null && row.DepositDate != undefined && row.DepositDate != '') {
              return row.DepositDate.substr(6) + '/' + row.DepositDate.substr(5, 6) + '/' + row.DepositDate.substr(0, 4);
            } else {
              return '-';
            }
          },
          className: "center"
        },
        {
          render: function (data, type, row, meta) {

            console.log("ROW", row.SendDate);
            if (row.SendDate != null && row.SendDate != undefined && row.SendDate != '') {
              return row.SendDate.substr(6) + '/' + row.SendDate.substr(5, 6) + '/' + row.SendDate.substr(0, 4);
            } else {
              return '-';
            }
          },
          className: "center"
        },


        { data: "SendAmount", render: $.fn.dataTable.render.number(',', '.', 2, '') },


      ],
      columnDefs: [
        { targets: [0], className: "center aligned" },
        { targets: [2], className: "right aligned" }
      ], rowCallback: (row, data, index) => {

        // $("td > .licNo", row).bind("click", () => {
        //   console.log('row', row);
        //   console.log('data', data);
        //   console.log('index', index);
        //   this.lic = data;

        // })
      }, createdRow: function (row, data, dataIndex) {
        // console.log(row);
        // console.log(data);
        // if (data.licAmount != null && data.licAmount != data.licPrice) {
        //   $(row).find('td:eq(6)').addClass('bg-c-red');
        //   $(row).find('td:eq(7)').addClass('bg-c-red');
        // }


      }
    });
  }




}
