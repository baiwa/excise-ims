import { Component, OnInit } from "@angular/core";
import { TextDateTH, formatter } from "../../../../../common/helper";
declare var $: any;
@Component({
  selector: "app-int01-5-1",
  templateUrl: "./int01-5-1.component.html",
  styleUrls: ["./int01-5-1.component.css"]
})
export class Int0151Component implements OnInit {
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
        "2": "05/10/60",
        "3": "8100.00",
        "4": "05/11/60",
        "5": "5100.00",
        "6": "05/12/60",
        "7": "4500.00",
        "8": "05/01/60",
        "9": "6100.00",
        "10": "05/02/60",
        "11": "5300.00",
        "12": "05/03/61",
        "13": "8100.00"
      },
      {
        "1": "B",
        "2": "05/10/60",
        "3": "2501.00",
        "4": "",
        "5": "",
        "6": "",
        "7": "",
        "8": "05/01/60",
        "9": "4804.00",
        "10": "05/02/60",
        "11": "3204.00",
        "12": "",
        "13": ""
      },
      {
        "1": "C",
        "2": "05/10/60",
        "3": "10000.00",
        "4": "05/11/60",
        "5": "9000.00",
        "6": "",
        "7": "",
        "8": "",
        "9": "",
        "10": "",
        "11": "",
        "12": "",
        "13": ""
      },
      {
        "1": "D",
        "2": "05/10/60",
        "3": "1000.00",
        "4": "",
        "5": "",
        "6": "",
        "7": "",
        "8": "",
        "9": "",
        "10": "",
        "11": "",
        "12": "",
        "13": ""
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
          render: backgroundRowColor
        },
        {
          data: "4",
          render: backgroundRowColor
        },
        {
          data: "5",
          render: backgroundRowColor
        },
        {
          data: "6",
          render: backgroundRowColor
        },
        {
          data: "7",
          render: backgroundRowColor
        },
        {
          data: "8",
          render: backgroundRowColor
        },
        {
          data: "9",
          render: backgroundRowColor
        },
        {
          data: "10",
          render: backgroundRowColor
        },
        {
          data: "11",
          render: backgroundRowColor
        },
        {
          data: "12",
          render: backgroundRowColor
        },
        {
          data: "13",
          render: backgroundRowColor
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
