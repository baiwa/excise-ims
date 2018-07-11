import { Component, OnInit } from "@angular/core";

declare var $: any;

@Component({
  selector: "app-int01-1-2",
  templateUrl: "./int01-1-2.component.html",
  styleUrls: ["./int01-1-2.component.css"]
})
export class Int0112Component implements OnInit {
  private codeList: any[];
  private productList: any[];
  private printMonthList: any[];
  private showData: boolean = false;

  private selectedProduct: string = "เครื่องดื่ม";

  constructor() {}

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    this.codeList = [
      { value: "1 : ภาษีสุรา ยาสูบ เครื่องดื่ม" },
      { value: "2 : ภาษีอื่น(นอกจากสุรา ยาสูบ เครื่องดื่ม)" },
      { value: "3 : รายได้อื่นนอกจากภาษี" }
    ];

    this.productList = [
      { value: "เครื่องดื่ม" },
      { value: "สุราแช่" },
      { value: "สุราแช่ชุมชน" },
      { value: "สุราแช่ชุมชน" },
      { value: "สุรากลั่นชุมชน" },
      { value: "ยาสูบ" }
    ];

    this.printMonthList = [
      { value: "ตุลาคม" },
      { value: "พฤศจิกายน" },
      { value: "ธันวาคม" },
      { value: "มกราคม" },
      { value: "กุมภาพันธ์" },
      { value: "มีนาคม" },
      { value: "เมษายน" },
      { value: "พฤษภาคม" },
      { value: "มิถุนายน" },
      { value: "กรกฎาคม" },
      { value: "สิงหาคม" },
      { value: "กันยายน" }
    ];
  }

  onChange(newValue) {
    this.selectedProduct = newValue; // don't forget to update the model here
  }
  ngAfterViewInit() {
    this.initDatatable();
    $("#export .dropdown").dropdown({
      transition: "drop"
    });
    $("#idint").hide();
    $("#id").hide();
  }

  initDatatable() {}

  popupEditData() {
    $("#modalEditData").modal("show");
    $("#modalInt062").modal("show");
    $("#idint").show();
    $("#id").show();
    $("#selectTrading").show();
  }

  closePopupEdit() {
    $("#selectTrading").show();
    $("#modalEditData").modal("hide");
    $("#modalInt062").modal("hide");
  }

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }
}
