import { Injectable } from '@angular/core';
import { AjaxService } from 'services/ajax.service';
import { Router } from '@angular/router';
import { MessageBarService } from 'services/message-bar.service';
import { IaService } from 'services/ia.service';


@Injectable()
export class Tsl010100Service {

  constructor(
    private ajax: AjaxService,
    private router: Router,
    private messege : MessageBarService,
    private obectService : IaService
  ) { }

  save(form): Promise<any> {
    let url = "taxAudit/selectList/findCondition1";
    return new Promise((resolve, reject) => {      
        resolve();        
        this.obectService.setData(form);
        this.router.navigate(['/tax-audit-select-line/tsl0102-00']);
  
    });
  }
}
