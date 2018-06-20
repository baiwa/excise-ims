import { Component, OnInit } from '@angular/core';
import { TextDateTH, formatter } from '../../../../common/helper/datepicker';

declare var $: any;
@Component({
  selector: 'app-report-tax',
  templateUrl: './report-tax.component.html',
  styleUrls: ['./report-tax.component.css']
})
export class ReportTaxComponent implements OnInit {

  year: any;

  constructor() { }

  ngOnInit() {
    // calendar
    $('#year').calendar({
      maxDate: new Date(),
      type: 'year',
      text: TextDateTH,
      formatter: formatter('year')
    });
  }

}
