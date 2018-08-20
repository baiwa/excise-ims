import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Location, NgIf } from "@angular/common";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { Alert } from "../../../../../../../../node_modules/@types/selenium-webdriver";

declare var $: any;
declare var $: any;

@Component({
  selector: 'app-int08-2-4',
  templateUrl: './int08-2-4.component.html',
  styleUrls: ['./int08-2-4.component.css']
})
export class Int0824Component implements OnInit {
  showData: boolean = false;

  budgetYear: any;


  constructor(
    private router: Router,
    private ajax: AjaxService,
    private messageBarService: MessageBarService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    $('.menu .item').tab()
;
  }
  ngAfterViewInit() {
    $("#select1").hide();
    $("#select2").hide();
    $("#select3").hide();
    $("#selectCondition1").dropdown();
    $("#selectCondition2").dropdown();
    $("#selectCondition3").dropdown();
    $("#selectColor1").dropdown();
    $("#selectColor2").dropdown();
    $("#selectColor3").dropdown();
  }

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }

  popupEditData() {
    $("#select1").show();
    $("#select2").show();
    $("#select3").show();
    $("#modalInt082").modal("show");
  }

  closePopupEdit() {
    $("#select1").hide();
    $("#select2").hide();
    $("#select3").hide();
    $("#modalInt082").modal("hide");
  }
  
  modalConditionRL() {
    $("#ConditionRL").modal("show");
  }

  closeConditionRL() {
    $("#ConditionRL").modal("hide");
  }
  
  cancelFlow() {
    this.messageBarService.comfirm(foo => {
      // let msg = "";
      if (foo) {
        this.router.navigate(["/int08/2/2"], {
          queryParams: { budgetYear: this.budgetYear }
        });
      }
    }, "คุณต้องการยกเลิกการทำงานใช่หรือไม่ ? ");
  }

}
