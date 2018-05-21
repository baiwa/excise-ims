import { Component, OnInit } from '@angular/core';

import { TextDateTH, formatter } from '../../../../../../common/helper/datepicker';
declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-int05-1-1-3',
  templateUrl: './int05-1-1-3.component.html',
  styleUrls: ['./int05-1-1-3.component.css']
})
export class Int05113Component implements OnInit {

  constructor() { }

  ngOnInit() {
    $('#date-pay').calendar({
      type: 'date',
      formatter: formatter,
      text: TextDateTH
    });

    $('#date-withdraw').calendar({
      type: 'date',
      formatter: formatter,
      text: TextDateTH
    });

    $('#date-check').calendar({
      type: 'date',
      formatter: formatter,
      text: TextDateTH
    });

    $('#date-send').calendar({
      type: 'date',
      formatter: formatter,
      text: TextDateTH
    });
  }

}
