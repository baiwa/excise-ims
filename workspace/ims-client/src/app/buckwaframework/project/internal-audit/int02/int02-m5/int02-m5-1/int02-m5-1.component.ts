import { Component, OnInit, OnDestroy } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";

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
    subSectionName: any;
    endDate: any;

    constructor(
        private router: Router,
        private route: ActivatedRoute, 
        private ajax: AjaxService, 
        private messageBarService: MessageBarService
    ) {
    }

    ngOnDestroy(): void {
        
    }

    ngOnInit(): void {
        this.subSectionName = "สำนักงานสรรพสามิตพื้นที่เชียงราย";
        
    }

    

    create(): void {
        this.router.navigate(['/int02/m5/1/1']);
    }

    // search(): void {
    //     this.router.navigate(['/int02/m5/2']);
    // }

    search() {
      this.subSectionName =  $("#subSectionName").val();
      console.log(this.subSectionName);
      this.router.navigate(["/int02/m5/2"], {
        queryParams: { subSectionName: this.subSectionName }
      });
    }

}
