import { Component, OnInit } from "@angular/core";
import { TextDateTH, formatter } from "../../../../../common/helper";
import { AjaxService, MessageBarService } from "../../../../../common/services";
import { Router, ActivatedRoute } from "@angular/router";
import { TravelService } from "../../../../../common/services/travel.service";

declare var $: any;
@Component({
  selector: "app-int01-5-1",
  templateUrl: "./int01-5-1.component.html",
  styleUrls: ["./int01-5-1.component.css"]
})
export class Int0151Component implements OnInit {

  dataT: any[];

  constructor(
    private ajax: AjaxService,
    private router: Router,
    private route: ActivatedRoute,
    private msg: MessageBarService,
    private travelService: TravelService
  ) {}

  ngOnInit() {
    this.dataTable();
  }

  ngAfterViewInit() {
    this.initDatatable();
  }


  initDatatable() {
    
  }
  getTableData = () =>{
    this.dataT=[{
      "1": "A",
      "2": "5/02/1960",
      "3": "20",
    },{
      "1": "B",
      "2": "",
      "3": "",
    }];
  }
    dataTable = () =>{
      this.getTableData();
      let backgroundRowColor = (data, type, row, meta) => {
        if (!data) {
          let table = $('#table').DataTable();
          let cell = table.cell(meta.row, meta.col).node();
          $(cell).addClass("bg-row-highlight");
        }
        return data;
      };

      $('#table').DataTable({
        "lengthChange": true,
        "serverSide": false,
        "searching": false,
        "ordering": false,
        "processing": true,
        "scrollX": true,
        "data": this.dataT,
        "columns": [
          {
            "data": "1"
          }, {
            "data": "2",
            render: backgroundRowColor
          }, {
            "data": "3",
            render: backgroundRowColor
          }
        ]
      });
  
    }
    

}
