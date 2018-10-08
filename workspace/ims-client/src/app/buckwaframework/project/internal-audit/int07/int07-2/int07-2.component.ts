import { Component, OnInit } from "@angular/core";
import { Int072Service } from "./int07-2.service";
import { ActivatedRoute } from "@angular/router";
import { BreadCrumb } from "models/breadcrumb";

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

  constructor(
    private route: ActivatedRoute,
    private selfService: Int072Service
  ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบบัญชี", route: "#" },
      { label: "ตรวจสอบข้อมูลบัญชี", route: "/int07/1" }
    ];
  }

  ngAfterViewInit() {
    $("#export .dropdown").dropdown({
      transition: "drop"
    });
  }

  ngOnInit() {
    this.loadingTable = true; //Loading dataTable
    this.verifyAccountHeaderId = this.route.snapshot.queryParams[
      "verifyAccountHeaderId"
    ];
    this.selfService.onInitDataTable(
      this.verifyAccountHeaderId,
      this.loadTable
    );
  }

  loadTable = arg => {
    this.loadingTable = arg;
  };
}
