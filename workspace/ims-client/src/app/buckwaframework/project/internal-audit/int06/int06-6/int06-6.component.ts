import { Component, OnInit } from '@angular/core';
import { AjaxService } from 'services/ajax.service';
import { TextDateTH, formatter } from 'helpers/datepicker';
import { FormSearch } from 'projects/internal-audit/int06/int06-6/form-search.model';
import { BreadCrumb } from 'models/breadcrumb';
import { Int066Service } from 'projects/internal-audit/int06/int06-6/int06-6.service';
import { Utils } from 'helpers/utils';

declare var $: any;

const URL = {
  DROPDOWN: "combobox/controller/getDropByTypeAndParentId"
};
@Component({
  selector: 'int06-6',
  templateUrl: './int06-6.component.html',
  styleUrls: ['./int06-6.component.css'],
  providers: [Int066Service]
})
export class Int066Component implements OnInit {

  breadcrumb: BreadCrumb[] = [
    { label: "ตรวจสอบภายใน", route: "#" },
    { label: "ตรวจสอบเบิกจ่าย", route: "#" },
    { label: "ค้นหารายการคุม KTB-Corporate", route: "#" }
  ];
  form: FormSearch = new FormSearch();
  startDate: any = "";
  endDate: any = "";
  sectorList: any;
  areaList: any;
  branchList: any;

  constructor(
    private ajax: AjaxService,
    private int066Service: Int066Service
  ) { }

  ngAfterViewInit() {
    this.calenda();
    this.dataTable();
  }

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    this.sector();
  }

  sector = () => {
    this.int066Service.sector().then((res) => {
      this.sectorList = res;
    });
  }
  area = (e) => {
    $("#area").dropdown('restore defaults');
    $("#branch").dropdown('restore defaults');
    this.areaList = null;
    this.branchList = null
    if (Utils.isNotNull(e.target.value)) {
      this.int066Service.area(e.target.value).then((res) => {
        this.areaList = res;;
      });
    }
  }
  branch = (e) => {
    this.branchList = null
    $("#branch").dropdown('restore defaults');
    if (Utils.isNotNull(e.target.value)) {
      this.int066Service.branch(e.target.value).then((res) => {
        this.branchList = res;
      });
    }

  }

  dataTable = () => {
    this.int066Service.dataTable();
  }
  clear = () => {
    $(".ui.dropdown").dropdown('restore defaults');
    $("#dateFrom").val("");
    $("#dateTo").val("");
  }
  calenda = () => {
    $("#dateF").calendar({
      maxDate: new Date(),
      endCalendar: $("#dateT"),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#dateT").calendar({
      maxDate: new Date(),
      startCalendar: $("#dateF"),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
  }
}
