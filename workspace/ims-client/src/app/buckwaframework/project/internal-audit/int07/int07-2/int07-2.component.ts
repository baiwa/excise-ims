import { Component, OnInit } from "@angular/core";
import { Int072Service } from "./int07-2.service";
import { ActivatedRoute } from "@angular/router";
import { BreadCrumb } from "models/breadcrumb";
import { DecimalFormat } from "helpers/decimalformat";
import { AuthService } from "services/auth.service";

declare var $: any;
@Component({
  selector: "int07-2",
  templateUrl: "./int07-2.component.html",
  styleUrls: ["./int07-2.component.css"],
  providers: [Int072Service]
})
export class Int072Component implements OnInit {
  breadcrumb: BreadCrumb[] = [];
  verifyAccountHeaderId: any;
  loadingTable: boolean;
  footer: any;
  testData: void;

  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private selfService: Int072Service
  ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบบัญชี", route: "#" },
      { label: "ตรวจสอบข้อมูลบัญชี", route: "#" }
    ];

    this.footer = {
      ledgerAccountNumber: "",
      ledgerAccountName: "",
      bringForward: 0.0,
      debit: "",
      credit: "",
      carryForward: 0.0,
      loadingTable: ""
    };
    this.loadingTable = true; //Loading dataTable
  }

  ngAfterViewInit() {}

  async ngOnInit() {
    this.authService.reRenderVersionProgram('INT-07200');
    this.verifyAccountHeaderId = this.route.snapshot.queryParams[
      "verifyAccountHeaderId"
    ];
    await this.selfService
      .onInitDataTable(this.verifyAccountHeaderId)
      .then(val => {
        if (val) {
          this.footer = val;
          this.loadingTable = false;
        }
      });
  }

  onExport = () => {
    console.log("this.testData: ", this.testData);
  };

  DF(what) {
    const df = new DecimalFormat("###,###.00");
    return df.format(what);
  }

  toFixed(x) {
    if (Math.abs(x) < 1.0) {
      var e = parseInt(x.toString().split("e-")[1]);
      if (e) {
        x *= Math.pow(10, e - 1);
        x = "0." + new Array(e).join("0") + x.toString().substring(2);
      }
    } else {
      var e = parseInt(x.toString().split("+")[1]);
      if (e > 20) {
        e -= 20;
        x /= Math.pow(10, e);
        x += new Array(e + 1).join("0");
      }
    }
    return x;
  }
}
