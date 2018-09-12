import { Component, OnInit } from "@angular/core";
import { TextDateTH, formatter } from "../../../../../common/helper";
declare var $: any;
@Component({
  selector: "app-int01-6-1",
  templateUrl: "./int01-6-1.component.html",
  styleUrls: ["./int01-6-1.component.css"]
})
export class Int0161Component implements OnInit {
  zoneList: any[];
  areaList: any[];
  subAreaList: any[];
  companyList: any[];

  showData: boolean = false;

  constructor() {}

  ngOnInit() {

          
    $("#calendar1").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()

    });

    $("#calendar2").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()

    });
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    this.zoneList = [
      { value: "สำนักงานสรรพสามิตภาคที่ 1" },
      { value: "สำนักงานสรรพสามิตภาคที่ 2" },
      { value: "สำนักงานสรรพสามิตภาคที่ 3" },
      { value: "สำนักงานสรรพสามิตภาคที่ 4" },
      { value: "สำนักงานสรรพสามิตภาคที่ 5" },
      { value: "สำนักงานสรรพสามิตภาคที่ 6" },
      { value: "สำนักงานสรรพสามิตภาคที่ 7" }
    ];

    this.areaList = [
      { value: "สำนักงานสรรพสามิตพื้นที่สมุทรสาคร" },
      { value: "สำนักงานสรรพสามิตพื้นที่สมุทรสงคราม" },
      { value: "สำนักงานสรรพสามิตพื้นที่สมุทรปราการ" }
    ];

    this.subAreaList = [
      { value: "สาขาเมือง 1" },
      { value: "สาขาเมือง 2" },
      { value: "สาขาเมือง 3" },
      { value: "สาขาเมือง 4" }
    ];

    this.companyList = [
      { value: "ทั้งหมด" },
      { value: "บ. ฮอนด้า มอเตอร์ ประเทศไทย" },
      { value: "บ. โตโยต้า มอเตอร์ ประเทศไทย" },
      { value: "บ. นิสสัน มอเตอร์ ประเทศไทย" }
    ];
  }

  ngAfterViewInit() {
    this.initDatatable();
  }

  initDatatable() {
    let tableMock = [
      {
        "1": "A",
        "2": "สร.ป2.VAT",
        "3": "05/09/1960",
        "4": "05/09/1960",
        "5": "05/02/1960",
        "6": "05/03/1960",
        "7": "",
        "8": "",
        "9": "",
        "10": "05/09/1960",
        "11": "05/09/1960",
        "12": "",
        "13": "05/09/1960"
      },
      {
        "1": "A",
        "2": "ยส.ป2.ผลิตภัณฑ์",
        "3": "05/09/1960",
        "4": "",
        "5": "",
        "6": "05/09/1960",
        "7": "05/09/1960",
        "8": "",
        "9": "05/09/1960",
        "10": "05/09/1960",
        "11": "",
        "12": "",
        "13": "05/09/1960"
      },
      {
        "1": "A",
        "2": "ไพ่ ป2. VAT",
        "3": "700",
        "4": "05/09/1960",
        "5": "",
        "6": "",
        "7": "05/09/1960",
        "8": "05/09/1960",
        "9": "05/09/1960",
        "10": "05/09/1960",
        "11": "",
        "12": "",
        "13": "05/09/1960"
      },
      {
        "1": "B",
        "2": "สร.ป2.VAT",
        "3": "05/09/1960",
        "4": "",
        "5": "",
        "6": "",
        "7": "05/09/1960",
        "8": "05/09/1960",
        "9": "",
        "10": "05/09/1960",
        "11": "",
        "12": "05/09/1960",
        "13": ""
      },
      {
        "1": "B",
        "2": "ยส.ป2.ผลิตภัณฑ์",
        "3": "",
        "4": "05/09/1960",
        "5": "05/09/1960",
        "6": "",
        "7": "05/09/1960",
        "8": "05/09/1960",
        "9": "05/09/1960",
        "10": "",
        "11": "05/09/1960",
        "12": "",
        "13": "05/09/1960"
      },
      {
        "1": "B",
        "2": "ไพ่ ป2. VAT",
        "3": "05/09/1960",
        "4": "",
        "5": "05/09/1960",
        "6": "",
        "7": "",
        "8": "",
        "9": "05/09/1960",
        "10": "05/09/1960",
        "11": "05/09/1960",
        "12": "05/09/1960",
        "13": "05/09/1960"
      }
    ];

    let tableId = "#table1";

    let backgroundRowColor = (data, type, row, meta) => {
      if (!data) {
        let table = $(tableId).DataTable();
        let cell = table.cell(meta.row, meta.col).node();
        $(cell).addClass("bg-row-highlight");
      }
      return data;
    };

    $("#table1").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: false,
      paging: false,
      info: false,
      pagingType: "full_numbers",
      data: tableMock,
      columns: [
        {
          data: "1"
        },
        {
          data: "2",
          render: backgroundRowColor
        },
        {
          data: "3",
          render: backgroundRowColor,
          className: "center"
        },
        {
          data: "4",
          render: backgroundRowColor,
          className: "center"
        },
        {
          data: "5",
          render: backgroundRowColor,
          className: "center"
        },
        {
          data: "6",
          render: backgroundRowColor,
          className: "center"
        },
        {
          data: "7",
          render: backgroundRowColor,
          className: "center"
        },
        {
          data: "8",
          render: backgroundRowColor,
          className: "center"
        },
        {
          data: "9",
          render: backgroundRowColor,
          className: "center"
        },
        {
          data: "10",
          render: backgroundRowColor,
          className: "center"
        },
        {
          data: "11",
          render: backgroundRowColor,
          className: "center"
        },
        {
          data: "12",
          render: backgroundRowColor,
          className: "center"
        },
        {
          data: "13",
          render: backgroundRowColor,
          className: "center"
        }
      ]
    });
  }

  searchData(): void {
    this.showData = true;
  }

  clearData(): void {
    this.showData = false;
  }
}
