import { Injectable } from '@angular/core';
import { AjaxService } from 'services/ajax.service';
import { resolve } from 'path';
import { reject } from 'q';
import { Router } from '@angular/router';

@Injectable()
export class Tsl010100Service {

  constructor(
    private ajax: AjaxService,
    private router: Router,
  ) { }

  search(form): Promise<any> {
    let url = "";
    return new Promise((resolve, reject) => {
      this.ajax.post(url, form, res => {
        console.log("search : ", res);
        this.router.navigate(['/tax-audit-select-line/tsl0102-00'])
      })
    });
  }
}
