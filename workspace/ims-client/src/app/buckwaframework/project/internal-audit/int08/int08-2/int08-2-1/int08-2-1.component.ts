import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Location } from "@angular/common";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { TextDateTH, formatter } from '../../../../../common/helper/datepicker';


declare var jQuery: any;
declare var $: any;

@Component({
  selector: "app-int08-2-1",
  templateUrl: "./int08-2-1.component.html",
  styleUrls: ["./int08-2-1.component.css"]
})
export class Int0821Component implements OnInit {

  data: String[];
  formatter: any;
  riskInfPaperName: any;
  riskAssInfHdrName: any;
  budgetYear: any;


  infRiskList: any[];
  datatable: any;


  riskList: any[];
  isSearch: any = false;

  constructor(
    private router: Router,
    private ajax: AjaxService,
    private messageBarService: MessageBarService,
  ) { }

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");

    this.riskAssInfHdrName = "";
    this.budgetYear = "";
    this.infRiskList = ["ปัจจัยเสี่ยงจำนวนครั้งการใช้งานไม่ได้ของระบบ"];

    $('#year').calendar({
      type: 'year',
      text: TextDateTH,
      formatter: formatter('ป'),
      onChange: (date) => {
        console.log(date.getFullYear())
        this.changeYear(date.getFullYear() + 543);
      }
    });


  }
  ngAfterViewInit() {

  }

  changeYear(year) {
    this.budgetYear = year;
    console.log(this.budgetYear);
    const URL = "combobox/controller/findByRiskInfName";
    this.ajax.post(URL, { budgetYear: this.budgetYear }, res => {

      this.riskList = res.json();
      console.log(this.riskList);
    });
  }



  createBudgetYear() {
    this.budgetYear = $('#budgetYear').val().trim();
    console.log(this.budgetYear);
    if (this.budgetYear == null || this.budgetYear == undefined || this.budgetYear == "") {
      this.messageBarService.errorModal("กรุณาเลือก ปีงบประมาณ ในการสร้างปัจจัยเสี่ยง");
    } else {
      const URL = "ia/int082/createBudgetYear";

      this.ajax.post(URL, { budgetYear: this.budgetYear }, res => {
        // var message = res.json();
        // this.messageBarService.successModal(message.messageTh, "สำเร็จ");

        this.router.navigate(["/int08/2/2"], {
          queryParams: { budgetYear: this.budgetYear }
        });
      }, errRes => {
        var message = errRes.json();
        this.messageBarService.errorModal(message.messageTh);

      });
    }
  }

  searchDataTable() {
    this.budgetYear = $('#budgetYear').val().trim();
    if (this.budgetYear != null && this.budgetYear != undefined && this.budgetYear != '') {
    
      this.initDatatable();
    } else {
      this.messageBarService.errorModal('กรุณาเลือก ปีงบประมาณ ในการค้นหาข้อมูล');
    }
  }

  initDatatable(): void {
    this.isSearch = true;
    if (this.datatable != null || this.datatable != undefined) {
      this.datatable.destroy();
    }
    const URL = AjaxService.CONTEXT_PATH + "ia/int082/searchRiskInfHdr";
    console.log(URL);
    console.log(this.budgetYear);
    this.datatable = $("#dataTable").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: true,
      paging: true,
      ajax: {
        type: "POST",
        url: URL,
        data: { budgetYear: this.budgetYear }
      },
      columns: [

        {
          render: function (data, type, row, meta) {
            return `<input type="checkbox" >`;
          },
          className: "center"
        },
        {
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "center"
        },

        { data: "riskInfPaperName" },
        { data: "riskAssInfHdrName" },
        { data: "budgetYear" },
        { data: "createdDate" },
        { data: "createdBy" },
        {
          data: "riskAssInfHdrId",
          render: function () {
            return '<button type="button" class="ui mini button dtl"><i class="pencil icon"></i> รายละเอียด</button>'
              + '<button type="button" class="ui mini button export"><i class="print icon"></i> Export</button>';
          }
        }
      ],
      columnDefs: [
        { targets: [1, 2, 3, 4, 5], className: "center aligned" }
      ],
      rowCallback: (row, data, index) => {
        $("td > .dtl", row).bind("click", () => {
          // console.log("dtl");
          // console.log(data.riskAssInfHdrId);

          // if (this.infRiskList.indexOf(data.riskAssInfHdrName) >= 0) {
          //   this.router.navigate(["/int08/2/3"], {
          //     queryParams: { id: data.riskAssInfHdrId }
          //   });
          // } else {
          //   this.router.navigate(["/int08/2/4"], {
          //     queryParams: { id: data.riskAssInfHdrId }
          //   });
          // }
        })

        $("td > .export", row).bind("click", () => {
          // console.log("del");
          // console.log(data.riskAssInfHdrId);

          // const URL = "ia/int082/deleteRiskInfHdr";

          // this.ajax.post(URL, { riskAssInfHdrId: data.riskAssInfHdrId }, res => {
          //   var message = res.json();
          //   this.riskAssInfHdrName = "";
          //   this.messageBarService.successModal(message.messageTh, "สำเร็จ");
          //   this.datatable.destroy();
          //   this.initDatatable();
          // }, errRes => {
          //   var message = errRes.json();

          //   this.messageBarService.errorModal(message.messageTh);
          // });
        });

      }
    });
  }

}
