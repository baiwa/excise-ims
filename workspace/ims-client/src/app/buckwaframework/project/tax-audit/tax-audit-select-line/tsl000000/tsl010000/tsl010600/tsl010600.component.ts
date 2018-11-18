import { Component, OnInit } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';
import { AjaxService } from 'services/ajax.service';
import { Router } from '@angular/router';
import { TextDateTH, formatter } from 'helpers/datepicker';
import { IaService } from 'services/ia.service';
import { AuthService } from 'services/auth.service';


declare var $: any;

@Component({
  selector: 'app-tsl010600',
  templateUrl: './tsl010600.component.html',
  styleUrls: ['./tsl010600.component.css']
})
export class Tsl010600Component implements OnInit {

  breadcrumb: BreadCrumb[] = [
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'การคัดเลือกราย', route: '#' },
    { label: 'ตารางผลการคัดเลือกรายประจำปี', route: '#' }
  ];
  activeModal :boolean = false;
  datatable: any;
  dateCalendar: String = "";
  searchFlag: string = "TRUE";

  constructor(
    private dataService: IaService,
    private router: Router,
    private authService : AuthService
  ) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('TSL-010600');
   }

  ngAfterViewInit() {
    this.initDatatable();
    this.calendar();
  }

  calendar = () => {
    $("#date").calendar({
      type: "month",
      text: TextDateTH,
      formatter: formatter("month-year"),
      onChange: (date, text) => {
        this.dateCalendar = text;
      }
    });
  };

  initDatatable = () => {
    const URL = AjaxService.CONTEXT_PATH + "taxAudit/display/search";
    this.datatable = $("#dataTable").DataTableTh({
      serverSide: true,
      searching: false,
      processing: true,
      ordering: false,
      scrollX: true,
      ajax: {
        type: "POST",
        url: URL,
        contentType: "application/json",
        data: (d) => {
          return JSON.stringify($.extend({}, d, {
            "searchFlag": this.searchFlag
          }));
        }
      },
      columns: [
        {
          data: "flag",
          className: "ui center aligned",
          render: function (data, row) {
            console.log(data, row)
            if (data == "2") {
              return '<i class="check icon" style="color:green"> </i>';
            }
            return '<a href="#" class="select-record"><u>เลือก</u></a>';
          }
        }, {
          data: "exciseId",
          className: "ui right aligned"
        }, {
          data: "companyName",
          className: "ui left aligned"
        }, {
          data: "companyAddress",
          className: "ui left aligned"
        }, {
          data: "exciseArea",
          className: "ui left aligned"
        }, {
          data: "exciseSubArea",
          className: "ui left aligned"
        }, {
          data: "product",
          className: "ui left aligned"
        }, {
          data: "riskTypeDesc",
          className: "ui left aligned"
        }, {
          data: "flagDesc",
          className: "ui left aligned"
        },
      ],
    });
    this.datatable.on('click', 'tbody tr .select-record', (e) => {
      event.preventDefault();
      var closestRow = $(e.target).closest('tr');
      var data = this.datatable.row(closestRow).data();
      console.log(data);      
      this.dataService.setData(data);
      this.activeModal = true;
      // $('#modalTsl111').modal({
      //   onShow: () => {
      //     //  this.calendar();
      //   }
      // }).modal('show');
    });
    // this.datatable.on("click", "td > .select-record", (event) => {
    //   event.preventDefault();
    //   var data = this.datatable.row($(event.currentTarget).closest("tr")).data();

    // });
  };

  onClickOK() {
    console.log("onClickOk");   
    this.activeModal = false;  
    this.router.navigate(["/tax-audit-select-line/tsl0107-00"],
      {
        queryParams: {
          "dateCalendar": this.dateCalendar,
          "searchFlag": "TRUE"
        }
      }
    );
  }

  onCancel(){
    this.activeModal = false;  
  }

}
