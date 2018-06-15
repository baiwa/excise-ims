import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ExciseService } from '../../../../common/services/excise.service';
import { AjaxService } from '../../../../common/services/ajax.service';

declare var jQuery: any;
declare var $: any;
@Component({
  selector: 'app-add-external-data',
  templateUrl: './add-external-data.component.html',
})
export class AddExternalDataComponent implements OnInit {
  from: any;
  before: any;
  last: any;
  analysNumbers: any;
  month: any;
  analysNumber: any;

  constructor(
    private router: Router,
    private ex: ExciseService
  ) {

  }

  ngOnInit() {
    //call ExciseService
    var { before, last, from, month } = this.ex.getformValues();
    var { analysNumber } = this.ex.getformNumber();

    //set values
    this.before = before;
    this.last = last;
    this.from = from;
    this.month = month;
    this.analysNumber = analysNumber;
  }
}
