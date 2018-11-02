import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { AjaxService } from '../../../../common/services/ajax.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ExportCheckingModel } from 'models/exportCheckingModel';

declare var $: any;

@Component({
  selector: 'app-epa02-6',
  templateUrl: './epa02-6.component.html',
  styleUrls: ['./epa02-6.component.css']
})
export class Epa026Component implements OnInit {

  datatable: any;
  exciseId: string = "";
  exciseName: string = "";
  taxStampNo = [""];
  factoryStampNo = [""];
  datas: ExportCheckingModel = new ExportCheckingModel();
  saveDatas: ExportCheckingModel = new ExportCheckingModel();

  constructor(
    private authService: AuthService,
    private ajaxService: AjaxService,
    private ajax: AjaxService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('EXP-02600');
  }

  addTaxField() {

  }

  delTaxField(index) {

  }

  addFactoryField() {

  }

  delFactoryField(index) {

  }

  onClickSave() {

  }

  onClickCancel() {
    this.router.navigate(["/epa02/5"], {
      queryParams: {
        exciseId: this.exciseId,
        exciseName: this.exciseName,
        searchFlag: "TRUE"
      }
    });
  }

}
