import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Location } from "@angular/common";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { Alert } from "../../../../../../../../node_modules/@types/selenium-webdriver";
import { AssetBalance, AssetMaintenance } from "../../../../../common/models";

declare var jQuery: any;
declare var $: any;
@Component({
  selector: 'app-int05-3-3',
  templateUrl: './int05-3-3.component.html',
  styleUrls: ['./int05-3-3.component.css']
})
export class Int0533Component implements OnInit {


  assetBalance: AssetBalance;
  assetMaintenance: AssetMaintenance;
  startDate: any;
  endDate: any;
  constructor(private router: Router,
    private route: ActivatedRoute,
    private ajax: AjaxService,
    private messageBarService: MessageBarService) {

    this.assetBalance = new AssetBalance();
    this.assetMaintenance = new AssetMaintenance();
  }

  ngOnInit() {

  }

  createAssetBalance() {
    this.router.navigate(['int05/3/3/1'], {

    });
  }

}
