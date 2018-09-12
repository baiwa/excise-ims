import { Component, OnInit } from "@angular/core";
import { AjaxService } from "../../../../../common/services";
import { TextDateTH, formatter } from "../../../../../common/helper";
import { Router } from "@angular/router";
declare var $: any;

const URL = {
  DROPDOWN : "combobox/controller/getDropByTypeAndParentId"
};

@Component({
  selector: "int01-1-1",
  templateUrl: "./int01-1-1.component.html"
})
export class Int0111Component implements OnInit {
  private selectedProduct: string = "สำนักงานสรรพสามิตพื้นที่เมืองพิษณุโลก ";
  private productList: any[];

  travelTo1List: any;
  travelTo2List: any;
  travelTo3List: any;

  constructor(private ajax: AjaxService, private router: Router) { }

  ngOnInit() {

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
      formatter: formatter()
    });
    $("#calendar2").calendar({
      startCalendar: $("#calendar1"),
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
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

  search(event) {
    console.log(event);
    this.router.navigate(['int01/1/2']);
  }

}
