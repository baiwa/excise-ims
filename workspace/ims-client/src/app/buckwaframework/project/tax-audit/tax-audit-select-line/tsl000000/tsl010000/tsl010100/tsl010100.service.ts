import { Injectable } from '@angular/core';
import { AjaxService } from 'services/ajax.service';
import { Router } from '@angular/router';
import { MessageBarService } from 'services/message-bar.service';

@Injectable()
export class Tsl010100Service {

  constructor(
    private ajax: AjaxService,
    private router: Router,
    private messege : MessageBarService
  ) { }

  save(form): Promise<any> {
    let url = "taxAudit/selectList/saveToTaPlanSearchRick";
    return new Promise((resolve, reject) => {
      this.ajax.post(url, JSON.stringify(form), res => {
        resolve(res.json());
        this.messege.successModal("บันทึกรายการ");
        this.router.navigate(['/tax-audit-select-line/tsl0102-00']);
      },err=>{
        this.messege.errorModal("ทำรายการไม่สำเร็จ");
        reject(err.json());
      });
    });
  }
}
