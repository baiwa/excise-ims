import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";

declare var $: any;
@Component({
  selector: "analysis",
  templateUrl: "analysis.component.html"
})
export class AnalysisPage implements OnInit {
  showSelectCoordinate: boolean = false;
  coordinateList: any[];

  productList: any[];
  serviceList: any[];

  constructor(private router: Router) {}

  ngOnInit(): void {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    this.productList = [
      { value: "สินค้าน้ำมันและผลิตภัณฑ์น้ำมัน" },
      { value: "สินค้าเครื่องดื่ม" },
      { value: "สินค้าเครื่องไฟฟ้า" },
      { value: "สินค้าแบตเตอร์รี่" },
      { value: "สินค้าแก้วและเครื่องแก้ว" },
      { value: "สินค้ารถยนต์" },
      { value: "สินค้ารถจักรยานยนต์" },
      { value: "สินค้าเรือ" },
      { value: "สินค้าผลิตภัณฑ์เครื่องหอมและเครื่องสำอาง" },
      { value: "สินค้าพรมและสิ่งทอปูพื้นอื่นๆ" },
      { value: "สินค้าหินอ่อนและหินแกรนิต" },
      { value: "สินค้าสารทำลายชั้นบรรยากาศ" },
      { value: "สินค้าไพ่" }
    ];

    this.serviceList = [
      { value: "กิจการบันเทิงหรือหย่อนใจ" },
      { value: "กิจการเสี่ยงโชค" },
      { value: "กิจการที่มีผลกระทต่อสิ่งแวดล้อม" },
      { value: "กิจการที่ได้รับอนุญาตหรือสัมปทานจากรัฐ" },
      { value: "สนามกอล์ฟ" }
    ];
  }

  selectCatagory() {
    var value = $("#selectCatagory").val();
    if (value == 1) {
      this.showSelectCoordinate = true;
      this.coordinateList = this.productList;
    } else if (value == 2) {
      this.showSelectCoordinate = true;
      this.coordinateList = this.serviceList;
    } else {
      this.showSelectCoordinate = false;
    }
  }

  goToAnalysisResult() {
    var coordinate = $("#selectCoordinate").val();
    var category = $("#selectCatagory").val();
    if (!coordinate) {
      coordinate = "สินค้าน้ำมันและผลิตภัณฑ์น้ำมัน";
    }

    if (category) {
      if (category == 1) {
        category = "สินค้า";
      } else {
        category = "บริการ";
      }
    } else {
      category = "สินค้า";
    }

    this.router.navigate(["/result-analysis", category, coordinate]);
  }
}
