import { Component, OnInit, OnDestroy } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { BaseModel, ManageReq, BreadCrumb } from 'models/index';
import { Int02m51Service } from "projects/internal-audit/int02/int02-m5/int02-m5-1/int02-m5-1.service";
import { promise } from "protractor";
var jQuery: any;
declare var jQuery: any;
declare var $: any;

const URL = {
    //DATATABLE: `${AjaxService.CONTEXT_PATH}oa/cop013/oa_oper_plan/datatable`
    DATATABLE: `${AjaxService.CONTEXT_PATH}oa/02m052/searchIaIceReportHdr`
}


@Component({
    selector: "app-int02-m5-1",
    templateUrl: "./int02-m5-1.component.html",
    styleUrls: ["./int02-m5-1.component.css"],
    providers: [Int02m51Service]
})
export class Int02M51Component implements OnInit, OnDestroy {

    private SECTOR_VALUE: string = 'SECTOR_VALUE';
    subSectionName: any;
    endDate: any;

    // BreadCrumb
    breadcrumb: BreadCrumb[];
    sectorList: any;
    areaList: any;
    localList: any;

    sectorSelectted: any;
    areaSelectted: any;
    localSelectted: any;


    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private ajax: AjaxService,
        private messageBarService: MessageBarService,
        private int02m51Service:Int02m51Service
    ) {

    }

    ngOnDestroy(): void {

    }

    ngOnInit(): void {
        this.hideData();
        $(".ui.dropdown").dropdown();
        this.sector();
        $(".ui.dropdown.ai").css("width", "100%");
      
    }

    hideData() {
        $('#data').hide();
    }

    showData() {
        $('#data').show();
    }
    sector=()=>{
        this.int02m51Service.sector().then(res=>{
            console.log(res);
            this.sectorList = res;
        });
        
    }



}
