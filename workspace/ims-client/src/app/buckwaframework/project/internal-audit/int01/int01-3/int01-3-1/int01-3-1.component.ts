import { Component, OnInit } from "@angular/core";
declare var $: any;
@Component({
  selector: "app-int01-3-1",
  templateUrl: "./int01-3-1.component.html",
  styleUrls: ["./int01-3-1.component.css"]
})
export class Int0131Component implements OnInit {
  selectedProduct: string = "สำนักงานสรรพสามิตพื้นที่เมืองพิษณุโลก ";
  productList: any[];
  constructor() {}

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    this.productList = [
      { value: "สำนักงานสรรพสามิตพื้นที่เมืองพิษณุโลก " },
      { value: "สำนักงานสรรพสามิตพื้นที่เมืองพิษณุโลก " },
      { value: "สำนักงานสรรพสามิตพื้นที่เมืองพิษณุโลก " },
      { value: "สำนักงานสรรพสามิตพื้นที่เมืองพิษณุโลก " },
      { value: "สำนักงานสรรพสามิตพื้นที่เมืองพิษณุโลก " },
      { value: "สำนักงานสรรพสามิตพื้นที่เมืองพิษณุโลก " }
    ];
  }
  popupEditData() {
    $("#modalEditData").modal("show");
    $("#modalInt062").modal("show");
    $("#selectTrading").show();
  }

  closePopupEdit() {
    $("#selectTrading").show();
    $("#modalEditData").modal("hide");
    $("#modalInt062").modal("hide");
  }
}
