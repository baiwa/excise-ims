import { Component, OnInit, OnDestroy } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { BaseModel, ManageReq, BreadCrumb } from 'models/index';
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
    styleUrls: ["./int02-m5-1.component.css"]
})
export class Int02M51Component implements OnInit, OnDestroy {

    private SECTOR_VALUE: string = 'SECTOR_VALUE';
    subSectionName: any;
    endDate: any;

    // BreadCrumb
    breadcrumb: BreadCrumb[];
    sector: any[] = [];
    area: any[] = [];
    local: any[] = [];

    sectorSelectted: any;
    areaSelectted: any;
    localSelectted: any;


    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private ajax: AjaxService,
        private messageBarService: MessageBarService
    ) {
        this.breadcrumb = [
            { label: "ตรวจสอบภายใน", route: "#" },
            { label: "แบบสอบถามระบบการควบคุมภายใน", route: "#" },
            { label: "รายงานการประเมินผลและการปรับปรุงการควบคุมภายใน (แบบ ปย.2)", route: "#" },
        ];
    }

    ngOnDestroy(): void {

    }

    ngOnInit(): void {
        this.subSectionName = "สำนักงานสรรพสามิตพื้นที่เชียงราย";
        this.sectorCombobox(null);

    }

    sectorCombobox = (lovIdMaster) => {

        const URL = "combobox/controller/getDropByTypeAndParentId";
        this.ajax.post(URL, { type: this.SECTOR_VALUE, lovIdMaster: lovIdMaster }, res => {
            this.sector = res.json();
            console.log(this.sector);
        });

    }

    areaCombobox = (lovIdMaster) => {

        const URL = "combobox/controller/getDropByTypeAndParentId";
        this.ajax.post(URL, { type: this.SECTOR_VALUE, lovIdMaster: lovIdMaster }, res => {
            this.area = res.json();
            //console.log(this.sector);
        });

    }

    localCombobox = (lovIdMaster) => {

        const URL = "combobox/controller/getDropByTypeAndParentId";
        this.ajax.post(URL, { type: this.SECTOR_VALUE, lovIdMaster: lovIdMaster }, res => {
            this.local = res.json();
            //console.log(this.sector);
        });

    }



    create(): void {
        this.router.navigate(['/int02/m5/1/1']);
    }

    // search(): void {
    //     this.router.navigate(['/int02/m5/2']);
    // }

    search() {
        this.subSectionName = $("#subSectionName").val();
        console.log(this.subSectionName);
        this.router.navigate(["/int02/m5/2"], {
            queryParams: { subSectionName: this.subSectionName }
        });
    }


    changeSector() {
        //console.log(this.sectorSelectted);
        this.areaCombobox(this.sectorSelectted);
        this.local = [];
    }

    changeArea() {
        //console.log(this.sectorSelectted);
        this.localCombobox(this.sectorSelectted);
    }

}
