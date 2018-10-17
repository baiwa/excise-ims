import { Component, OnInit } from "@angular/core";
import { AuthService } from "services/auth.service";

declare var $: any;
@Component({
  selector: "int04-3",
  templateUrl: "./int04-3.component.html",
  styleUrls: ["./int04-3.component.css"]
})
export class Int043Component implements OnInit {
  private zoneList: any[];
  private areaList: any[];
  private subAreaList: any[];
  private companyList: any[];

  constructor(private authService: AuthService) {}

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-04300');
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
        "1": "ยส.ป1.ปลูกเอง",
        "2": "สภ. 08-17",
        "3": "239821",
        "4": "1490",
        "5": "4"
      },
      {
        "1": "ไพ่ ป1.",
        "2": "สภ. 04-23",
        "3": "239822",
        "4": "1491",
        "5": "2"
      },
      {
        "1": "ยส.ผลิต ยาเส้นปรุง",
        "2": "สภ. 04-06",
        "3": "239823",
        "4": "1492",
        "5": "2"
      }
    ];

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
          data: "2"
        },
        {
          data: "3",
          className: "right"
        },
        {
          data: "4",
          className: "right"
        },
        {
          data: "5",
          className: "right"
        }
      ]
    });
  }
}
