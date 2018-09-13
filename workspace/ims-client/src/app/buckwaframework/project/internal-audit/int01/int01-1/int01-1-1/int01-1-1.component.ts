import { Component, OnInit, ViewChild } from "@angular/core";
import { AjaxService } from "../../../../../common/services";
import { TextDateTH, formatter } from "../../../../../common/helper";
import { Router } from "@angular/router";
import { NgForm } from "@angular/forms";
import { toDateLocale, digit } from "../../../../../common/helper/datepicker";
import { BreadCrump } from "../../../../../common/models";
declare var $: any;

const URL = {
  DROPDOWN: "combobox/controller/getDropByTypeAndParentId"
};

@Component({
  selector: "int01-1-1",
  templateUrl: "./int01-1-1.component.html"
})
export class Int0111Component implements OnInit {
  @ViewChild("f") form: NgForm; // #f

  fields: any = [
    { name: "1" },
    { name: "2" },
    { name: "3" },
  ];

  travelTo1List: any;
  travelTo2List: any;
  travelTo3List: any;
  breadcrump: BreadCrump[];

  constructor(private ajax: AjaxService, private router: Router) {
    this.breadcrump = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบพัสดุ", route: "#" },
      { label: "ตรวจสอบพัสดุภาคพื้นที่", route: "#" }
    ];
  }

  ngOnInit() {
    // this.options = {
    //   searching: false,
    //   paging: false,
    //   scrollY: '300px',
    //   info: false
    // };
    // Dropdowns
    this.travelTo1Dropdown();
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.search").css("width", "100%");

    // Start End Calendars
    $("#calendar1").calendar({
      endCalendar: $("#calendar2"),
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter(),
      onChange: (date) => {
        let day = date.getDate();
        let _month = toDateLocale(date)[0].split("/")[1];
        let _year = toDateLocale(date)[0].split("/")[2];
        this.form.controls.startDate.setValue(digit(day) + "/" + digit(_month) + "/" + _year.toString());
      }
    });
    $("#calendar2").calendar({
      startCalendar: $("#calendar1"),
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter(),
      onChange: (date) => {
        let day = date.getDate();
        let _month = toDateLocale(date)[0].split("/")[1];
        let _year = toDateLocale(date)[0].split("/")[2];
        this.form.controls.endDate.setValue(digit(day) + "/" + digit(_month) + "/" + _year.toString());
      }
    });

  }

  travelTo1Dropdown = () => {
    this.ajax.post(URL.DROPDOWN, { type: "SECTOR_VALUE" }, res => {
      this.travelTo1List = res.json();
    });
  }

  travelTo2Dropdown = e => {
    var id = e.target.value;
    if (id != "") {
      this.ajax.post(URL.DROPDOWN, { type: "SECTOR_VALUE", lovIdMaster: id }, res => {
        this.travelTo2List = res.json();
      });
    }
  }

  travelTo3Dropdown = e => {
    var id = e.target.value;
    if (id != "") {
      this.ajax.post(URL.DROPDOWN, { type: "SECTOR_VALUE", lovIdMaster: id }, res => {
        this.travelTo3List = res.json();
      });
    }
  }

  search(form: NgForm) {
    if (form.valid || (form.controls.travelTo1.valid && form.controls.startDate.valid && form.controls.endDate.valid)) {
      this.router.navigate(['int01/1/2']);
    }
  }

  onSubmit(form: NgForm) {
    console.log(form);
  }

}
