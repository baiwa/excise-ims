import { Component, OnInit, ViewChild, AfterViewInit } from "@angular/core";
import { Router } from "@angular/router";
import { NgForm } from "@angular/forms";
import { Observable } from "rxjs";

import { BreadCrumb, ComboBox } from "models/index";
import { AjaxService } from "services/index";
import { Int011Service } from "../int01-1.services";
import { Int0111Service } from "./int01-1-1.service";

declare var $: any;

@Component({
  selector: "int01-1-1",
  templateUrl: "./int01-1-1.component.html",
  styleUrls: ["./int01-1-1.component.css"],
  providers: [Int0111Service]
})
export class Int0111Component implements OnInit, AfterViewInit {
  @ViewChild("f") form: NgForm; // #f

  comboBox1: Observable<ComboBox>;
  comboBox2: Observable<ComboBox>;
  comboBox3: Observable<ComboBox>;
  breadcrumb: BreadCrumb[] = [];

  constructor(
    private ajax: AjaxService,
    private router: Router,
    private main: Int011Service,
    private self: Int0111Service,
  ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบพัสดุ", route: "#" },
      { label: "ตรวจสอบพัสดุภาคพื้นที่", route: "#" }
    ];
    this.comboBox1 = this.self.pullComboBox('SECTOR_VALUE', 'comboBox1');
  }

  ngOnInit() {
    // Start End Calendars
    this.self.calendar("calendar1", "calendar2", this.form);
  }

  ngAfterViewInit() {
    // Dropdowns
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.search").css("width", "100%");
  }

  async onSubmit(form: NgForm) {
    const { travelTo1, travelTo2, travelTo3, startDate, endDate } = await form.controls;
    const data = await {
      travelTo1: travelTo1,
      travelTo2: travelTo2,
      travelTo3: travelTo3,
      startDate: startDate,
      endDate: endDate,
    };
    await this.main.setData(data);
    this.router.navigate(['int01/1/2']);
    // const { travelTo1, startDate, endDate } = form.controls;
    // if (form.valid || (travelTo1.valid && startDate.valid && endDate.valid)) {
      // this.router.navigate(['int01/1/2']);
    // }
  }

  dropdown(e, combo: string) {
    e.preventDefault();
    this[combo] = this.self.pullComboBox('SECTOR_VALUE', combo, e.target.value);
  }

}
