import { Component, OnInit } from "@angular/core";
import { AuthService } from "services/auth.service";
import { ActivatedRoute } from "@angular/router";
import { Int0621Service } from "./int06-2-1.service";
import { BreadCrumb } from "models/breadcrumb";
import { Observable } from "rxjs";
import { MessageBarService } from "services/message-bar.service";

declare var $: any;
@Component({
  selector: "int06-2-1",
  templateUrl: "./int06-2-1.component.html",
  styleUrls: ["./int06-2-1.component.css"],
  providers: [Int0621Service]
})
export class Int0621Component implements OnInit {
  breadcrumb: BreadCrumb[] = [];
  budgetDropdown: Promise<any>;
  budgetCodeList: any;
  loadingInit: boolean = true;
  loadingTable: boolean = false;

  constructor(
    private authService: AuthService,
    private route: ActivatedRoute,
    private selfService: Int0621Service,
    private msg: MessageBarService
  ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบเบิกจ่าย", route: "#" },
      { label: "ตรวจสอบการเบิกและจ่ายเงิน", route: "#" },
      { label: "เปรียบเทียบรายงานสรุปรายการขอเบิก", route: "#" }
    ];
  }

  ngAfterViewInit() {}

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    this.authService.reRenderVersionProgram("INT-06210");
    const idExcel1 = this.route.snapshot.queryParams["idExcel1"];
    const idExcel2 = this.route.snapshot.queryParams["idExcel2"];
    const idSortsys = this.route.snapshot.queryParams["comboBoxId"];
    this.selfService
      .getBudgetCode(idExcel1)
      .then(data => {
        this.budgetCodeList = data;
        setTimeout(() => {
          $(".ui.dropdown").dropdown();
          $(".ui.dropdown.ai").css("width", "100%");
          this.loadingInit = false;
        }, 30);
      })
      .catch(() => {
        setTimeout(() => {
          this.loadingInit = false;
          this.msg.errorModal("เกิดข้อผิดพลาด", "ล้มเหลว");
        }, 100);
      });
    this.selfService.getDropdownT(idExcel2).then(dropdown => {
      this.budgetDropdown = dropdown;
    });
    this.hideData();
  }

  hideData() {
    $("#Int0621").hide();
  }
  showData(e: any) {
    e.preventDefault();
    this.loadingTable = true;
    this.selfService
      .compareDataExcel(e)
      .then(() => {
        setTimeout(() => {
          this.loadingTable = false;
        }, 400);
      })
      .catch(() => {
        setTimeout(() => {
          this.loadingTable = false;
          this.msg.errorModal("เกิดข้อผิดพลาด", "ล้มเหลว");
        }, 400);
      });
  }
}
