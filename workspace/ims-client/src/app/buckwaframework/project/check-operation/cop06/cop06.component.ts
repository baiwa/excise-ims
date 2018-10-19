import { Component, OnInit } from '@angular/core';
import { MessageBarService, AjaxService } from '../../../common/services';
import { ActivatedRoute, Router } from '@angular/router';
import { BreadCrumb } from 'models/breadcrumb';
declare var jQuery: any;
declare var $: any;
@Component({
  selector: 'app-cop06',
  templateUrl: './cop06.component.html',
  styleUrls: ['./cop06.component.css']
})
export class Cop06Component implements OnInit {
  private showSubMenuMat: boolean = false;
  private showSubMenuIns: boolean = false;
  private showSubMenuTax: boolean = false;
  
breadcrumb: BreadCrumb[];
  private coordinate: String;
  private routerOpe051: String;
  constructor(
    private messageBarService: MessageBarService,
    private router: Router,
    private route: ActivatedRoute,
    private ajax: AjaxService
  ) {    this.breadcrumb = [
    { label: "รายงานการตรวจปฏิบัติการ", route: "#" },
  
  ];}
  ngOnInit(): void {
    this.coordinate = this.route.snapshot.params["coordinate"];
    if (this.coordinate == "สนามกอล์ฟ") {
      this.routerOpe051 = "/ope05-1-1";
    } else {
      this.routerOpe051 = "/ope05-1";
    }
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ope04-1").css("width", "100%");
  }
  ngAfterViewInit(): void {
    $("#showData").hide();
  }

  clickMenuMat() {
    this.showSubMenuMat = true;
    this.showSubMenuIns = false;
    this.showSubMenuTax = false;
  }
  clickMenuIns() {
    this.showSubMenuMat = false;
    this.showSubMenuIns = true;
    this.showSubMenuTax = false;
  }
  clickMenuTax() {
    this.showSubMenuMat = false;
    this.showSubMenuIns = false;
    this.showSubMenuTax = true;
  }
}
